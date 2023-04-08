package telran.Git;

import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GitRepositoryImpl implements GitRepository {
	
	private static final long serialVersionUID = 1L;
	
	Commit head;
	Map<Branch, List<Commit>> allCommit = new HashMap<>();
	Map<Path, List<File>> allFiles;
	
	public static GitRepositoryImpl init() {
		
		return null;
	}

	@Override
	public String commit(String commitMessage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createBranch(String branchName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String renameBranch(String branchName, String newName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteBranch(String branchName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> branches() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Path> commitContent(String commitName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String switchTo(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getHead() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub

	}

	@Override
	public String addIgnoredFileNameExp(String regex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FileState> info() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CommitMessage> log() {
		// TODO Auto-generated method stub
		return null;
	}

}
