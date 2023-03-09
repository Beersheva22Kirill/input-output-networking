package telran.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.time.LocalDateTime;

public class BufferCopy extends Copy {
	
	private long bufferSize;

	BufferCopy(String srcFilePath, String destFilePath, boolean owerwrite ,long bufferSize) {
		super(srcFilePath, destFilePath, owerwrite);
		this.bufferSize = bufferSize;
		
	}

	@Override
	long copy() throws IOException {
		
		
		if (Files.notExists(Path.of(destFilePath),LinkOption.NOFOLLOW_LINKS) || owerwrite) {
			try(InputStream inputStream = new FileInputStream(srcFilePath);
					OutputStream outputStream = new FileOutputStream(destFilePath);){		
					byte[] buffer = new byte[(int) bufferSize];
					int length = inputStream.read(buffer);
						while (length > 0) {
							outputStream.write(buffer, 0, length);
							length = inputStream.read(buffer);
						
						}
			}
						
			}
		
		return Files.size(Path.of(destFilePath));
	}

	@Override
	DisplayResult getDisplayResult(long sizeFile) {
		
		return new DisplayResultBuffer(sizeFile, timeCopyring, bufferSize);
	}

}
