package telran.Git;

import java.io.File;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Commit {
	
	int name;
	LocalDateTime time;
	CommitMessage massage;
	Branch branch;
	Map<Path, List<File>> castRepository;
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}


	public Commit(CommitMessage massage, Branch branch) {
		this.name = hashCode();
		this.massage = massage;
		this.branch = branch;
	}	
	
	

}
