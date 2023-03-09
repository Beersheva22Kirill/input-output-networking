package telran.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.time.LocalDateTime;

public class TransferCopy extends Copy {

	TransferCopy(String srcFilePath, String destFilePath, boolean owerwrite) {
		super(srcFilePath, destFilePath, owerwrite);
	}
	
	@Override
	long copy() throws Exception {
		if (Files.notExists(Path.of(destFilePath),LinkOption.NOFOLLOW_LINKS) || owerwrite) {
			try(InputStream inputStream = new FileInputStream(srcFilePath);
					OutputStream outputStream = new FileOutputStream(destFilePath);){
						inputStream.transferTo(outputStream);			
			}
		}
		return Files.size(Path.of(destFilePath));	
	}

	@Override
	public DisplayResult getDisplayResult(long sizeFile) {
		
		return new DisplayResult(sizeFile, timeCopyring);
	}
	
}
