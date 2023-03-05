package telran.io.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class InputOutputTest {
	
	private static String OFFSET = "  ";
	private static String OFFSET_DIR_NAME = "";
	
	String fileName = "newFile";
	String directoryName = "newDirectory1/newDirectory2";
	@BeforeEach
	void setUp() throws Exception {
		new File(fileName).delete();
		new File(directoryName).delete();
	}

	@Test
	@Disabled
	void testFile() throws IOException {
		File file1 = new File("newDirectory1/" + fileName + "1");
		assertTrue(file1.createNewFile());
		File directory = new File(directoryName);
		directory.mkdirs();
		assertTrue(directory.mkdirs());
		File file2 = new File(directoryName + "/" + fileName + "2");
		file2.createNewFile();
		System.out.println(directory.getAbsolutePath());
	}
	
	@Test
	@Disabled
	void testFiles() {
		Path path = Path.of(".");
		System.out.println(path.toAbsolutePath().getNameCount());	
	}
	
	@Test
	void printDirectoryTest() throws IOException {
		printDirectoryFile(".", 0);
		printDirectoryFiles(".", 0);
	}
	
	//---------------------- class File --------------------------------------
	void printDirectoryFile(String pathParam, int maxLevel) {
		Path path = Path.of(pathParam);
		System.out.println("-------- used class 'File' ---------");
			printMainDirectory(pathParam, path);
		
		if (maxLevel < 1) {
			allPathPrinting(pathParam, OFFSET);
		} else {
			levelPathPrinting(pathParam, maxLevel, OFFSET);
		}		
	}

	private void printMainDirectory(String pathParam, Path path) {
		if (pathParam.equals(".")) {
			System.out.println(OFFSET_DIR_NAME + path.toAbsolutePath().getParent().getFileName() + "|dir");
		} else {
			System.out.println(OFFSET_DIR_NAME + path.getFileName() + "|dir");
		}
	}

	private void levelPathPrinting(String pathParam, int maxLevel, String offset) {
		File file = new File(pathParam);
		File[] arrFile = file.listFiles();
		if (maxLevel > 0) {
			for (File fl : arrFile) {
				if (fl.isDirectory()) {
					System.out.println(offset + fl.getName() + "| dir");
					String newPath = pathParam + "/" + fl.getName();
					levelPathPrinting(newPath, --maxLevel, offset + "   " );
				} else {
					System.out.println(offset + fl.getName() + "| file");
				}
			}		
		}
	}

	private void allPathPrinting(String pathParam, String offset) {
		File file = new File(pathParam);
		File[] arrFile = file.listFiles();
		for (File fl : arrFile) {
			if (fl.isDirectory()) {
				System.out.println(offset + fl.getName() + "| dir");
				String newPath = pathParam + "/" + fl.getName();
				allPathPrinting(newPath, offset + "   " );
			} else {
				System.out.println(offset + fl.getName() + "| file");
			}
		}
	}
	
	
	//------------------------- class Files--------------------------------------
	void printDirectoryFiles(String pathParam, int maxLevel) throws IOException {
		Path path = Path.of(pathParam);
		System.out.println("-------- used class 'Files' ---------");
			printMainDirectory(pathParam, path);
		
		if (maxLevel < 1) {
			printAllPathFiles(path);
		} else {
			printLevelPathFiles(maxLevel, path);
		}
	
	}

	private void printLevelPathFiles(int maxLevel, Path path) throws IOException {
		Files.walk(path, maxLevel, FileVisitOption.FOLLOW_LINKS)
		.filter(n -> n != path)
		.forEach(n -> fileOrDirPrint(n));
	}

	private void printAllPathFiles(Path path) throws IOException {
		Files.walk(path, FileVisitOption.FOLLOW_LINKS)
		.filter(n -> n != path)
		.forEach(n -> fileOrDirPrint(n));
	}
	
	private void fileOrDirPrint(Path path) {
		if (Files.isDirectory(path)) {
			System.out.println(" ".repeat(path.getNameCount()) +  path.getFileName() + "| dir");
		} else {
			System.out.println(" ".repeat(path.getNameCount()) +  path.getFileName() + "|file");
		}
	}
	
}
