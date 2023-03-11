package telran.io.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.io.PrintWriter;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class LineOrientedStreams {

	static final String fileNamePrintStream = "LinesStream.txt";
	static final String fileNamePrintWriter = "LinesWriter.txt";
	static final String line = "Hello World";
	static final String helloFileName = "test.txt";
	
	@Test
	@Disabled
	void printStreamTest() throws FileNotFoundException {
		PrintStream printStream = new PrintStream(fileNamePrintStream);
		printStream.println(line);
	}
	
	@Test
	@Disabled
	void printWriterTest() throws FileNotFoundException {
		PrintWriter printWriter = new PrintWriter(fileNamePrintWriter);
		printWriter.println(line);
		printWriter.close();
	}
	
	@Test
	void bufferedReaderTest() throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(helloFileName));
		//variant 1
		while (true) {
			String nextline = reader.readLine(); 
			if (nextline == null) {
				break;
			}
			assertEquals(line, nextline);
		}
		//variant 2
		reader.lines().forEach(l -> assertEquals(line, l));
	}
}
