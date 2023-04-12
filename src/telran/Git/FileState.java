package telran.Git;

import java.io.File;
import java.io.Serializable;


public class FileState implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	FileStatus status;
	File file;
	long time;
	
	public FileState(File fileName, FileStatus status, long time) {
	
		this.time = time;
		this.file = fileName;
		this.status = status;
	}

	@Override
	public String toString() {
		return "[fileName=" + file.getName() + ", status=" + status + "]";
	}
	
	

}
