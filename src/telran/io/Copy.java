package telran.io;

public abstract class Copy {
	
	String srcFilePath;
	String destFilePath;
	boolean owerwrite;
	
	
	abstract long copy() throws Exception;
	abstract DisplayResult getDisplayResult();
	
	public void copyRun() throws Exception{
		this.copy();
		DisplayResult result = this.getDisplayResult();
		System.out.println(result.toString());
	}
	
	public Copy(String srcFilePath, String destFilePath, boolean owerwrite) {
		this.srcFilePath = srcFilePath;
		this.destFilePath = destFilePath;
		this.owerwrite = owerwrite;
	}
	

}
