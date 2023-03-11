package telran.util;

import java.io.*;


public class SimpleStreamHandler implements Handler {
	
	PrintStream stream;

	@Override
	public void publish(LoggerRecord loggerRecord) {
		String time = loggerRecord.timestamp.toString();
		String zone = loggerRecord.zoneId;
		String level = loggerRecord.level.toString();
		String message = loggerRecord.message;
		
		stream.printf("Time:%s TimeZone:%s Level: %s Message: %s\n",time,zone,level,message);	
	}
	
	public SimpleStreamHandler(PrintStream stream){
		this.stream = stream;
	}
	

}
