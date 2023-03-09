package telran.io;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class FilesCopy extends Copy {
	
	FilesCopy(String srcFilePath, String destFilePath, boolean owerwrite) {
		super(srcFilePath, destFilePath, owerwrite);
		
	}
	
	@Override
	long copy() throws Exception {
		
			try(OutputStream outputStream = new FileOutputStream(destFilePath);){
						Files.copy(Path.of(srcFilePath), outputStream);
			}
		
		return Files.size(Path.of(destFilePath));
	}

	@Override
	DisplayResult getDisplayResult(long sizeFile) {
		
		return new DisplayResult(sizeFile, timeCopyring);
	}

}
