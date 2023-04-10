package telran.Git;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

public class GitRepositoryImpl implements GitRepository {
	
	private static final long serialVersionUID = 1L;

	
	static String pathRepository;
	
	String regex;
	Head head;
	static Set<String> names = new HashSet<>();
	Map<Branch, LinkedList<Commit>> commits = new LinkedHashMap<>();
	
	
	public GitRepositoryImpl(String path) {
		pathRepository = path;
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
		String response;
		Commit commit;
		LinkedList<Commit> temp;
		try {
			if (head == null) {
				commit = commitAndCreateMaster(commitMessage);
				head = new Head(commit.branch, commit);
			} else {
				commit = new Commit(new CommitMessage(commitMessage), head.currentBranch, pathRepository, regex);
				head.currentCommit = commit;
			}
			temp = commits.getOrDefault(head.currentBranch, new LinkedList<>());
			if (temp.isEmpty()) {
				temp.add(commit);
				commits.put(head.currentBranch, temp);
			} else {
				temp.add(commit);
			}	
			response = "Commited: " + commit.message;
		} catch (Exception e) {
			response = "Error commited:" + e.getMessage() ;
		}
		
		return response;
	}

	private Commit commitAndCreateMaster(String commitMessage) {
		CommitMessage message = new CommitMessage(commitMessage);
		Commit commit = new Commit(message, new Branch("master", message, pathRepository, regex), pathRepository, regex);
		return commit;
	}

	@Override
	public String createBranch(String branchName) {
		String response;
		try {
			if (head != null) {
				Branch newBranch = new Branch(branchName, head.currentCommit);
				commits.put(newBranch, new LinkedList<>());
				head.currentBranch = newBranch;
				head.currentCommit = null;
			} else {
				commit("Create Branch");
			}
			response = "Branch name : " + branchName + " created";
		} catch (Exception e) {
			response = "Error creating branche";
		}
		
		return response;
	}

	@Override
	public String renameBranch(String branchName, String newName) {
		Set<Branch> set = commits.keySet();
		Iterator<Branch> iterator = set.iterator();
		while (iterator.hasNext()) {
			Branch branch = iterator.next();
			if(branch.getName().equals(branchName)) {
				branch.setName(newName);
			}	
		}
		return null;
	}

	@Override
	public String deleteBranch(String branchName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> branches() {
		LinkedList<String> branches = new LinkedList<>();
		Set<Branch> temp = commits.keySet();
		for (Branch branch : temp) {
			branches.add(branch.getName());
		}
		return branches;
	}

	@Override
	public List<Path> commitContent(String commitName) {
		LinkedList<String> temp = new LinkedList<>();
		LinkedList<Path> res = new LinkedList<>();
		List<Commit> list = commits.get(head.currentBranch);
		Iterator iterator = list.iterator();
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
	// проверить на следующий коммит и добавить в Хед поле некст коммит
	@Override
	public String switchTo(String name) { 
		String res = "";
		boolean searchBranchOk = searchBranch(name);
		if (!searchBranchOk) {
			boolean searchCommitOk = searchCommit(name);
			if (searchCommitOk) {
				res = "Switching to a commit: " + head.currentCommit.name  + " branch: " + head.currentBranch.getName();
			}
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
				res = true;
			}			
		}
		return res;
	}

	@Override
	public String getHead() {
		String commitName;
		String commitMessage;
		String branchName;
		branchName = head.currentBranch == null ?  "No branches in repository" : head.currentBranch.getName();
		commitName = head.currentCommit == null ? "No commits in this branch" : head.currentCommit.name + "";
		commitMessage = head.currentCommit == null ? "No commits and massege in this branch" : head.currentCommit.message + "";
		String str = "Branch: " + branchName + " Commit: " + commitName + " Commit message: " + commitMessage;
		return str;
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
		infoRecurs(pathRepository,files);
		return files;
	}

	private List<FileState> infoRecurs(String pathRepository, List<FileState> Allfiles) {
		String path = pathRepository.toString();
		File file = new File(path);
		File[] arrFile = file.listFiles();
		for (File fl : arrFile) {
			if (fl.isDirectory()) {
				String newPath = path + "\\" + fl.getName();
				infoRecurs(newPath,Allfiles);
			} else if(regexFiles(fl.getName())){
				FileState fileState = getFileFromHead(fl.getName(), fl.getParent());
				if (fileState == null) {
					fileState = new FileState(fl, FileStatus.UNTRACKED, fl.lastModified());
				} else if(fileState.time != fl.lastModified()) {
					fileState.status = FileStatus.MODIFIED;	
				}
				Allfiles.add(fileState);					
			}	
		}		
		return Allfiles;
		
	}


	private boolean regexFiles(String name) {
		
		return name.matches(regex);
	}

	private FileState getFileFromHead(String name, String path) {
		if (head != null && head.currentCommit != null) {
			List<FileState> temp = head.currentCommit.castRepository.get(path);
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
		Set<Branch> setBranch = commits.keySet();
		for (Branch branch : setBranch) {
			LinkedList<Commit> commitsList = commits.get(branch);
			ListIterator<Commit> iterator = commitsList.listIterator(commitsList.size());
			while(iterator.hasPrevious()) {
				list.add(iterator.previous().message);
			}
				
		}
		return list;
	}
	

}
