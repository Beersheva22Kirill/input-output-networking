package telran.Git;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class Commit implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	String name;
	LocalDateTime time;
	CommitMessage message;
	Branch branch;
	Map<FileState, ArrayList<String>> castRepository;
	
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	public Commit(CommitMessage massage, Branch branch,Map<FileState, ArrayList<String>> castRepository, String name) {

			this.castRepository = castRepository;
			time = LocalDateTime.now();	
			this.name = name;
			this.message = massage;
			this.branch = branch;

	}
	
}