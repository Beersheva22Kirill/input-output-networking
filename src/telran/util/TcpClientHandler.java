package telran.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import telran.nework.app.ServerLogApp;

public class TcpClientHandler implements Handler {
	private static final String OK = "OK";
	
	private Socket socket;
	private PrintStream writer;
	private BufferedReader reader;
	
	@Override
	public void publish(LoggerRecord loggerRecord) {
			String line = ServerLogApp.LOG_PROTOCOL + "#" + loggerRecord.toString() + "#" + loggerRecord.level.toString();	
				writer.println(line);
				try {
					String response = reader.readLine();
					if (!response.equals(OK)) {
						throw new RuntimeException("Response from Logger Server is " + response);
					}
				} catch (IOException e) {
					new RuntimeException(e.getMessage());
				}
	}

	public TcpClientHandler(Socket socket, PrintStream writer, BufferedReader reader) {
		this.socket = socket;
		this.writer = writer;
		this.reader = reader;
	}
	
	public TcpClientHandler(String hostName, int port) {
		
		try {
			socket = new Socket(hostName, port);
			writer = new PrintStream(socket.getOutputStream());
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			throw new RuntimeException(e.toString());
		}
	}
	
	public BufferedReader getReader() {
		return reader;
	}

	@Override
	public void close() {
		try {
			socket.close();
		} catch (IOException e) {
			throw new RuntimeException("not closed " + e.getMessage());
		}
	}

}
