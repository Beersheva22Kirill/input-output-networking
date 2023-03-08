package telran.io;

public class DisplayResult {
	long fileSize;
	long copyTime;
	
	
	DisplayResult(long fileSize, long copyTime) {	
		this.fileSize = fileSize;
		this.copyTime = copyTime;
	}
	
	public String toString() {
		String res = "fileSize: " + fileSize + " byte\ncopyTime: " 
					+ copyTime + " ml_seconds";
		return res;
	}
	
	

}
