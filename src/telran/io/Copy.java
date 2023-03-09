package telran.io;

public abstract class Copy {
	
	String srcFilePath;
	String destFilePath;
	boolean owerwrite;
	long timeCopyring;
	
	
	abstract long copy() throws Exception;
	abstract DisplayResult getDisplayResult(long sizeFile);
	
	public void copyRun() throws Exception{
		long timeBegin = System.currentTimeMillis();
		
		long sizeFile = this.copy();
		
		long timeEnd = System.currentTimeMillis();
		timeCopyring = timeEnd - timeBegin;
		
		DisplayResult result = this.getDisplayResult(sizeFile);
		System.out.println(result.toString());
	}
	
	public Copy(String srcFilePath, String destFilePath, boolean owerwrite) {
		this.srcFilePath = srcFilePath;
		this.destFilePath = destFilePath;
		this.owerwrite = owerwrite;
	}
	

}
