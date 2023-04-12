package telran.Git;

import java.io.Serializable;

public class CommitMessage implements Serializable{
	
	private static final long serialVersionUID = 1L;
	String massage;;
	

	public CommitMessage(String massage) {
		this.massage = massage;
	}


	@Override
	public String toString() {
		return "CommitMessage [massage=" + massage + "]";
	}
	
	

}
