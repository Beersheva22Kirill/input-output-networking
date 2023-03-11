package telran.io.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import java.lang.System.Logger.Level;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import telran.util.Logger;
import telran.util.SimpleStreamHandler;

class LoggerTest {
	
	static final String fileNameLog = "Log";
		
	@Test
	void loggerErrTest() throws IOException {
		String nameErr = fileNameLog + "_" + LocalDate.now().toString() +"_err.txt";
		try(PrintStream printLog = new PrintStream(nameErr);){
			SimpleStreamHandler handler = new SimpleStreamHandler(printLog);
			Logger LOG = new Logger(handler, "logger");
			LOG.setLevel(Level.ERROR);
			LOG.error("ErrorTestValid");
			LOG.warn("WarningTestNotValid");
			LOG.trace("WarningTestNotValid");
			LOG.error("ErrorTestValid");
		}
		
		BufferedReader reader = new BufferedReader(new FileReader(nameErr));
		int countString = 0;
		while (true) {
			String nextline = reader.readLine(); 
			if (nextline == null) {
				break;
			}	
			countString++;
		}
		assertEquals(2, countString);
	}
		
		@Test
		void loggerWarnTest() throws FileNotFoundException {
			String nameDbg = fileNameLog + "_" + LocalDate.now().toString() +"_dbg.txt";
			try(PrintStream printLog = new PrintStream(nameDbg);){
				SimpleStreamHandler handler = new SimpleStreamHandler(printLog);
				Logger LOG = new Logger(handler, "logger");
				LOG.setLevel(Level.DEBUG);
				LOG.debug("debugTestValid");
			}
	}

}
