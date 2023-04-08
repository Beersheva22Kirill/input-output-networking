package telran.Git;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class FileState {
	
	Path path;
	Map<FileStatus, List<File>> files;

}
