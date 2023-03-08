package telran.io.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class InputOutputStreamTest {

	private static final String FILE_NAME = "newFileTest";
	private static final String FILE_NAME_COPY = "newFileTestCopy";
	private String hello = "Hello world1";

	@BeforeAll
	static void setUp() throws Exception {
		Path path = Path.of(FILE_NAME);
		Files.deleteIfExists(path);
	}

	@Order(1)
	@Test
	void outputStreamTest() throws Exception {
	
		try(OutputStream outputStream = new FileOutputStream(FILE_NAME)) {
			byte[] helloBytes = hello.getBytes();
			outputStream.write(helloBytes);	
		}
	}
	
	@Test
	void inputStreamTest() throws Exception {
		String file = FILE_NAME;
		readFileTest(file);
	}

	private void readFileTest(String file) throws IOException, FileNotFoundException {
		try(FileInputStream inputStream = new FileInputStream(file)){
			byte[] helloBytes = inputStream.readAllBytes();
			String string = new String(helloBytes);
			assertEquals(hello, string);
		}
	}
	@Order(2)
	@Test
	void transferTest() throws FileNotFoundException, IOException {
		try(InputStream inputStream = new FileInputStream(FILE_NAME);
			OutputStream outputStream = new FileOutputStream(FILE_NAME_COPY);){
		inputStream.transferTo(outputStream);	
		}
	}
	
	@Test
	void copyTest () throws FileNotFoundException, IOException {
		readFileTest(FILE_NAME_COPY);
	}
	//transferTo
	//Files.Copy
	//getRunTime.FreeMemory

	
}
