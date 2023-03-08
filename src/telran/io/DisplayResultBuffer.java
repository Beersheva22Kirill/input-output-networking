package telran.io;

public class DisplayResultBuffer extends DisplayResult {
	
	private long bufferSize;

	DisplayResultBuffer(long fileSize, long copyTime, long bufferSize) {
		super(fileSize, copyTime);
		this.bufferSize = bufferSize;
		
	}
	
	@Override
	public String toString() {
		String res = "fileSize: " + fileSize + 
				" byte\ncopyTime: " + copyTime + " ml_seconds\n" + "bufferSize: " + bufferSize + " byte";
		return res;
	}

}
