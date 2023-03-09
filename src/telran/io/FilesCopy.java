package telran.io;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.time.LocalDateTime;

public class FilesCopy extends Copy {
	
	FilesCopy(String srcFilePath, String destFilePath, boolean owerwrite) {
		super(srcFilePath, destFilePath, owerwrite);
		
	}
	
	@Override
	long copy() throws Exception {
		long timeBegin = System.currentTimeMillis();
		if (Files.notExists(Path.of(destFilePath),LinkOption.NOFOLLOW_LINKS) || owerwrite) {
			try(OutputStream outputStream = new FileOutputStream(destFilePath);){
						Files.copy(Path.of(srcFilePath), outputStream);
						long timeEnd = System.currentTimeMillis();
						timeCopyring = timeEnd - timeBegin;
			}
		}
		return Files.size(Path.of(destFilePath));
	}

	@Override
	DisplayResult getDisplayResult(long sizeFile) {
		
		return new DisplayResult(sizeFile, timeCopyring);
	}

}
