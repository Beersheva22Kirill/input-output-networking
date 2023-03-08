package telran.io.test;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;

import org.junit.jupiter.api.Test;

public class LineOrientedStreams {

	static final String fileNamePrintStream = "LinesStream.txt";
	static final String fileNamePrintWriter = "LinesWriter.txt";
	static final String line = "Hello World";
	
	@Test
	void printStreamTest() throws FileNotFoundException {
		PrintStream printStream = new PrintStream(fileNamePrintStream);
		printStream.println(line);
	}
	
	@Test
	void printWriterTest() throws FileNotFoundException {
		PrintWriter printWriter = new PrintWriter(fileNamePrintWriter);
		printWriter.println(line);
		printWriter.close();
	}
}
