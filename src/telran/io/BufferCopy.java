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
	private long timeCopyring;
	private long fileSize;

	BufferCopy(String srcFilePath, String destFilePath, boolean owerwrite ,long bufferSize) {
		super(srcFilePath, destFilePath, owerwrite);
		this.bufferSize = bufferSize;
		
	}

	@Override
	long copy() throws IOException {
		
		LocalDateTime timeBegin = LocalDateTime.now();
		if (Files.notExists(Path.of(destFilePath),LinkOption.NOFOLLOW_LINKS) || owerwrite) {
			try(InputStream inputStream = new FileInputStream(srcFilePath);
					OutputStream outputStream = new FileOutputStream(destFilePath);){		
					byte[] buffer = new byte[(int) bufferSize];
					int length = inputStream.read(buffer);
						while (length > 0) {
							outputStream.write(buffer, 0, length);
							length = inputStream.read(buffer);
						
						}
					LocalDateTime timeEnd = LocalDateTime.now();
					timeCopyring = timeEnd.getNano() - timeBegin.getNano();
					fileSize = Files.size(Path.of(destFilePath));
			}
						
			}
		
		return fileSize;
	}

	@Override
	DisplayResult getDisplayResult() {
		
		return new DisplayResultBuffer(fileSize, timeCopyring, bufferSize);
	}

}
