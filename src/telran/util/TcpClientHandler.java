package telran.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class TcpClientHandler implements Handler {
	
	private Socket socket;
	private PrintStream writer;
	private BufferedReader reader;
	
	@Override
	public void publish(LoggerRecord loggerRecord) {
			String line = "log#" + loggerRecord.toString() + "#" + loggerRecord.level.toString();	
				writer.println(line);
				String response = null;
				try {
					response = reader.readLine();
				} catch (IOException e) {
					System.out.println("Not response from Server");
				}
				System.out.println(response);
	}

	public TcpClientHandler(Socket socket, PrintStream writer, BufferedReader reader) {
		this.socket = socket;
		this.writer = writer;
		this.reader = reader;
	}
	
	@Override
	public void close() {
		writer.println("exit");
	}

}
