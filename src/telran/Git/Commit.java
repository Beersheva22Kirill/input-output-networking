package telran.Git;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Commit implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private static final long MIN_VALUE = 0;
	private static final long MAX_VALUE = 9999999;
	
	String name;
	String pathRepository;
	LocalDateTime time;
	CommitMessage message;
	Branch branch;
	Map<FileState, String[]> castRepository;
	
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	public Commit(CommitMessage massage, Branch branch, String pathRepository, String regex) {
		time = LocalDateTime.now();
		castRepository = new HashMap<>();
		commit(pathRepository,regex);
		name = getName();
		this.message = massage;
		this.branch = branch;
	}


	private String getName() {	
		
		boolean uniq = false;
		String nameCommit = null;;
		 while (!uniq) {
			 nameCommit = getRandom() + "";
			 uniq = GitRepositoryImpl.names.add(nameCommit);
		}	
		return nameCommit;
	}
	
	private Integer getRandom() {
		return (int) (MIN_VALUE + Math.random() * (MAX_VALUE - MIN_VALUE + 1));
	}


	private void commit(String pathRepository, String regex) {
		File file = new File(pathRepository.toString());
		File[] arrFile = file.listFiles();
		//List<FileState> filesInDir = new LinkedList<>();
		
		for (File fl : arrFile) {
			if (fl.isDirectory()) {
				String newPath = pathRepository + "\\" + fl.getName();
				commit(newPath,regex);
			} else if(fl.getName().matches(regex)) {
				castRepository.put(new FileState(fl, FileStatus.COMMITED, fl.lastModified()), null);
				//filesInDir.add(new FileState(pathRepository, fl.getName(), FileStatus.COMMITED, fl.lastModified()));
			}
		}
		
	//castRepository.put(pathRepository, filesInDir);	
		
	}
	
	
}
