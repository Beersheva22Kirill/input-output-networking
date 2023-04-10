package telran.Git;

import java.io.Serializable;

public class Branch implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;
	private Commit lastCommit;
	
	public Branch(String name, CommitMessage message, String pathRepository, String regex) {
		this.name = name;
		this.lastCommit = new Commit(message, this, pathRepository, regex);
	}
	
	public Branch(String name, Commit lastCommit) {
		this.name = name;
		this.lastCommit = lastCommit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
