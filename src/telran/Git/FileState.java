package telran.Git;

import java.io.File;
import java.io.Serializable;


public class FileState implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//String path;
	FileStatus status;
	File file;
	Long time;
	
	public FileState(File fileName, FileStatus status, Long time) {
	
		this.time = time;
		//this.path = path;
		this.file = fileName;
		this.status = status;
	}

	@Override
	public String toString() {
		return "[fileName=" + file.getName() + ", status=" + status + "]";
	}
	
	

}
