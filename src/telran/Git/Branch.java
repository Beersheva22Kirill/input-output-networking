package telran.Git;

import java.io.Serializable;

public class Branch implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;
	
	public Branch(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
