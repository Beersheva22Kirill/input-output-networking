package telran.Git;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.Map.Entry;

public class GitRepositoryImpl implements GitRepository {
	
	private static final long serialVersionUID = 1L;
	
	private static final long MIN_VALUE = 0;
	private static final long MAX_VALUE = 9999999;

	
	static String pathRepository;	
	String regex;
	Set<String> names = new HashSet<>();
	Head head;
	Map<String,Branch> branches;
	Map<Branch, LinkedList<Commit>> commits;
	
	
	public GitRepositoryImpl(String path) {
		pathRepository = path;
		commits = new LinkedHashMap<>();
		branches = new LinkedHashMap<>();
	}
	
	public static GitRepositoryImpl init() {
		File gitFile = new File(pathRepository + "/testGit.mygit");
		GitRepositoryImpl repository = new GitRepositoryImpl(pathRepository);
		repository.regex = repository.addIgnoredFileNameExp("\\w+\\.txt$");	
		try {
			boolean fileExists = gitFile.createNewFile();
			if (!fileExists) { 
				try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(pathRepository.toString() + "\\testGit.mygit"))){
					repository = (GitRepositoryImpl) is.readObject();
					
				} catch (IOException e) {
					System.out.println("Error input file: " + e.getMessage());
				}
			}	
		} catch (Exception e) {
			e.getMessage();
		}
		return repository;
	}
	

	@Override
	public String commit(String commitMessage) {
		String response = "";	
		try {
			if (checkCommit()) {
				Commit commit = createdCommit(commitMessage);
				if (commit.time != null) {
					head.currentCommit = commit;
					LinkedList<Commit> listOfCommits = commits.getOrDefault(head.currentBranch, new LinkedList<>());
					if (listOfCommits.isEmpty()) {
						listOfCommits.add(commit);
						commits.put(head.currentBranch, listOfCommits);
					} else {
						listOfCommits.add(commit);
					}
					response = "Commited: " + commit.message;
				}
			} else {
				throw new RuntimeException("Can't commit not at the end of a branch");
			}	
		} catch (RuntimeException e) {
			response = "Error commited: " + e.getMessage() ;
		}		
		return response;
	}

	private boolean checkCommit() {
		boolean res = false;
		if (head != null && head.currentCommit != null) {
			LinkedList<Commit> listCommits = new LinkedList<>(commits.get(head.currentBranch));
			if (head.currentCommit == listCommits.getLast()) {
				res = true;
			}
		} else {
			res = true;
		}
		return res;
	}

	private Commit createdCommit(String commitMessage) throws RuntimeException {
		Commit commit;
		if (head == null) {
			head = new Head(new Branch("master"), null);
			branches.put(head.currentBranch.getName(), head.currentBranch);
			commit = new Commit(new CommitMessage(commitMessage), head.currentBranch, createCommitMap(), getName());
			head.currentCommit = commit;
		} else {
			commit = new Commit(new CommitMessage(commitMessage), head.currentBranch, createCommitMap(), getName());
			
		}
		return commit;
	}


	@Override
	public String createBranch(String branchName) {
		String response;
		try {
			Branch newBranch = new Branch(branchName);
			branches.put(newBranch.getName(), newBranch);
			commits.put(newBranch, new LinkedList<>());
			if (head != null) {
				head.currentBranch = newBranch;
				head.currentCommit = null;
			} else {
				head = new Head(newBranch, null);
			}
			response = "Branch name : " + branchName + " created";
		} catch (Exception e) {
			response = "Error creating branche: " + e.getMessage();
		}	
		return response;
	}

	@Override
	public String renameBranch(String branchName, String newName) {
		String res = "Branch: " + branchName;
		try {
			Branch branch = branches.get(branchName);
			branch.setName(newName);
			setNameBranchInMap(branchName, newName);
			res += " renamed: " + newName;
		} catch (Exception e) {
			res += " error rename: " + e.getMessage();
		}
		
		return res;
	}

	private void setNameBranchInMap(String branchName, String newName) {
		Branch branch;
		Set<Branch> set = commits.keySet();
		Iterator<Branch> iterator = set.iterator();
		while (iterator.hasNext()) {
			branch = iterator.next();
			if(branch.getName().equals(branchName)) {
				branch.setName(newName);
			}	
		}
	}

	@Override
	public String deleteBranch(String branchName) {
		Branch branch = branches.get(branchName);
		String res;
		if (branch != head.currentBranch) {
			commits.remove(branch);
			branches.remove(branchName);
			res = "Branch: " + branchName + " deleted";
		} else {
			res = "You can't delete a branch on which HEAD";
		}
		 
		return res;
	}

	@Override
	public List<String> branches() {

		return new LinkedList<>(this.branches.keySet());
	}

	@Override
	public List<Path> commitContent(String commitName) {
		LinkedList<Path> res = new LinkedList<>();
		List<Commit> list = commits.get(head.currentBranch);
		Iterator<Commit> iterator = list.iterator();
		while (iterator.hasNext()) {
			for (Commit commit : list) {
				if (commit.name.equals(commitName)) {
					Set<FileState> set = commit.castRepository.keySet();
					for (FileState fileState : set) {
						res.add(Path.of(fileState.file.getPath()));
					}
					return res;
				}
			}
		}
		return new LinkedList<>();
	}
	
	@Override
	public String switchTo(String name) { 
		String res = "";
		boolean searchBranchOk = searchBranch(name);
		if (!searchBranchOk) {
				boolean searchCommitOk = searchCommit(name);
				if (searchCommitOk) {
					res = "Switching to a commit: " + head.currentCommit.name  + " branch: " + head.currentBranch.getName();
				} else res = "Branch or commit with name: " + name + " not found";
		} else {
			res = "Switching to a branch: " + head.currentBranch.getName() + " commit: " + head.currentCommit.name;
		}	
		return res;
	}

	private boolean searchCommit(String name) {
		boolean res = false;
		Set<Branch> branches = commits.keySet();
		Iterator<Branch> iterator = branches.iterator();
		while (iterator.hasNext()) {
			LinkedList<Commit> listCommits= commits.get(iterator.next());
			Iterator<Commit> iteratorCommits = listCommits.iterator();
			while (iteratorCommits.hasNext()) {
				Commit expectedCommit = iteratorCommits.next();
				if (expectedCommit.name.equals(name)) {
					head.currentBranch = expectedCommit.branch;
					head.currentCommit = expectedCommit;
					Set<FileState> set = head.currentCommit.castRepository.keySet();
					try {
						restoreFiles(set);
					} catch (IOException e) {
						e.getMessage();
					}
					res = true;	
				}
			}	
		}
		return res;
	}

	private boolean searchBranch(String name) {
		boolean res = false;
		Set<Branch> branches = commits.keySet();
		Iterator<Branch> iterator = branches.iterator();
		while (iterator.hasNext()) {
			Branch expectedBranch = iterator.next();
			if (expectedBranch.getName().equals(name)) {
				LinkedList<Commit> listCommits= commits.get(expectedBranch);
				head.currentBranch = expectedBranch;
				head.currentCommit = listCommits.getLast();
				Set<FileState> set = head.currentCommit.castRepository.keySet();
				try {
					restoreFiles(set);
				} catch (IOException e) {
					e.getMessage();
				}
				
				res = true;
			}			
		}
		return res;
	}

	private void restoreFiles(Set<FileState> set) throws IOException {
		Iterator<FileState> iterator = set.iterator();
		Set<File> filesForDeleted = new HashSet<>();
		while (iterator.hasNext()) {
			FileState currentFile = iterator.next();
			filesForDeleted.add(currentFile.file);
			ArrayList<String> content = head.currentCommit.castRepository.get(currentFile);
			File file = new File(currentFile.file.getPath());
			if(file.isDirectory()) {
				file.mkdir();
			}else {
				file.createNewFile();
			}		
			try (PrintStream  ps = new PrintStream(file)) {
				for (String string : content) {
					ps.println(string);				
				}
			}
			file.setLastModified(currentFile.time);
		}
		deletedFiles(filesForDeleted, pathRepository);

		
	}

	private void deletedFiles(Set<File> filesForDeleted, String pathRepository) throws IOException {
		File file = new File(pathRepository);
		File[] arrFile = file.listFiles();
		for (File fl : arrFile) {
			if (fl.isDirectory()) {
				String newPath = pathRepository + "\\" + fl.getName();
				deletedFiles(filesForDeleted, newPath);
			} else {
				if(!filesForDeleted.contains(fl) && fl.getName().matches(regex)) {
					Files.deleteIfExists(Path.of(fl.getPath()));
				}
			}			
		}	
	}

	@Override
	public String getHead() {
		String branchName = head.currentBranch == null ?  "No branches in repository" : head.currentBranch.getName();
		String commitName = head.currentCommit == null ? "No commits in this branch" : head.currentCommit.name + "";
		String commitMessage = head.currentCommit == null ? "No commits and massege in this branch" : head.currentCommit.message + "";
	
		return "Branch: " + branchName + " Commit: " + commitName + " Commit message: " + commitMessage;
	}

	@Override
	public void save() {
		try(ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(pathRepository + "/testGit.mygit"))){
			os.writeObject(this);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public String addIgnoredFileNameExp(String regex) {
		String res = "Regex not correctly";
		try {
			"1.txt".matches(regex);
			res = regex;
		} catch (Exception e) {

		}
		return res;
	}

	@Override
	public List<FileState> info() {
		List<FileState> files = new ArrayList<>();
		infoRecurs(pathRepository,files, head);
		return files;
	}

	private List<FileState> infoRecurs(String pathRepository, List<FileState> Allfiles, Head head) {
		File file = new File(pathRepository);
		File[] arrFile = file.listFiles();
		for (File fl : arrFile) {
			if (fl.isDirectory()) {
				String newPath = pathRepository + "\\" + fl.getName();
				infoRecurs(newPath,Allfiles, head);
			} else if(regexFiles(fl.getName())){
				FileState fileState = getFileFromHead(fl.getName(), fl.getParent(), head);
				if (fileState == null) {
					fileState = new FileState(fl, FileStatus.UNTRACKED, fl.lastModified());
				} else if(fileState.time != fl.lastModified()) {
					fileState.status = FileStatus.MODIFIED;	
					fileState.time = fl.lastModified();
				}
				Allfiles.add(fileState);					
			}	
		}		
		return Allfiles;
		
	}


	private boolean regexFiles(String name) {
		
		return name.matches(regex);
	}

	private static FileState getFileFromHead(String name, String path, Head head) {
		if (head != null && head.currentCommit != null) {
			List<FileState> temp = new LinkedList<FileState>(head.currentCommit.castRepository.keySet());
			if (temp !=null) {
				for (FileState fileState : temp) {
					if (fileState.file.getName().equals(name)) {
						return fileState;
					}
				}
			}
		}
		return null;
	}

	@Override
	public List<CommitMessage> log() {
		LinkedList<CommitMessage> list = new LinkedList<>();
		LinkedList<Commit> commitsList = commits.get(head.currentBranch);
		ListIterator<Commit> iterator = commitsList.listIterator(commitsList.size());
		while(iterator.hasPrevious()) {
			list.add(iterator.previous().message);
		}
				
		return list;
	}
	
	
	
	
	private Map<FileState, ArrayList<String>> createCommitMap() throws RuntimeException {
		List<FileState> commitFiles = info();
		Map<FileState, ArrayList<String>> mapForCommit = new HashMap<>();
		if (checkFiles(commitFiles)) {
			Iterator<FileState> iteratorForCommit = commitFiles.iterator();
			while (iteratorForCommit.hasNext()) {
				FileState fileForCommit = iteratorForCommit.next();
				fileForCommit.status = FileStatus.COMMITED;
				ArrayList<String> contentFiles = new ArrayList<>();
				try {
					contentFiles = readingFile(fileForCommit);
				} catch (Exception e) {
					e.getMessage();
				}
				mapForCommit.put(fileForCommit, contentFiles);	
			}
		} else {		
			throw new RuntimeException("No files for commits");
		}
		return mapForCommit;		
	}
	
	private boolean checkFiles(List<FileState> commitFiles) {
		Iterator<FileState> iteratorForFlag = commitFiles.iterator();
		boolean flag = false;
		while (iteratorForFlag.hasNext() && !flag) {
			if(iteratorForFlag.next().status != FileStatus.COMMITED ) {
				flag = true;
			}	
		}
		return flag;
	}
	
	private ArrayList<String> readingFile(FileState fileForCommit) throws FileNotFoundException, IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader(fileForCommit.file))) {
			ArrayList<String> contentFiles = new ArrayList<>();
			while(true) {
				String nextline = reader.readLine(); 
				if (nextline == null) {
					break;
				}
				contentFiles.add(nextline);
			}
			return contentFiles;
		}
	}
	
	private String getName() {	
		
		boolean uniq = false;
		String nameCommit = null;
		 while (!uniq) {
			 nameCommit = getRandom() + "";
			 uniq = names.add(nameCommit);
		}	
		return nameCommit;
	}
	
	private Integer getRandom() {
		return (int) (MIN_VALUE + Math.random() * (MAX_VALUE - MIN_VALUE + 1));
	}

}
