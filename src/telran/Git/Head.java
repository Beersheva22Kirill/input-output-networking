package telran.Git;

import java.io.Serializable;

public class Head implements Serializable{

	private static final long serialVersionUID = 1L;
	
	Branch currentBranch;
	Commit currentCommit;
	
	public Head(Branch currentBranch, Commit currentCommit) {
		this.currentBranch = currentBranch;
		this.currentCommit = currentCommit;
	}
	
	

}
