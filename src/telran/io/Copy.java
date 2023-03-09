package telran.io;

import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;

public abstract class Copy {
	
	String srcFilePath;
	String destFilePath;
	boolean owerwrite;
	long timeCopyring;
	
	
	abstract long copy() throws Exception;
	abstract DisplayResult getDisplayResult(long sizeFile);
	
	public void copyRun() throws Exception{
		long sizeFile = 0L;
		if (Files.notExists(Path.of(destFilePath),LinkOption.NOFOLLOW_LINKS) || owerwrite) {
			long timeBegin = System.currentTimeMillis();
			sizeFile = this.copy();
			long timeEnd = System.currentTimeMillis();
			timeCopyring = timeEnd - timeBegin;		
		}
		DisplayResult result = this.getDisplayResult(sizeFile);
		System.out.println(result.toString());
	}
	
	public Copy(String srcFilePath, String destFilePath, boolean owerwrite) {
		this.srcFilePath = srcFilePath;
		this.destFilePath = destFilePath;
		this.owerwrite = owerwrite;
	}
	

}
