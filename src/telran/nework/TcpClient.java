package telran.nework;

import java.io.*;
import java.net.*;

public class TcpClient implements NetworkClient {
	private Socket socket;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	
	public TcpClient (String hostname, int port) throws Exception {
		socket = new Socket(hostname,port);
		output = new ObjectOutputStream(socket.getOutputStream());
		input = new ObjectInputStream(socket.getInputStream());
	}
	
	@Override
	public void close() throws IOException {
		
		socket.close();
	}

	@Override
	public <T> T send(String type, Serializable requestData) {
			Request request = new Request(type, requestData);
			T response = null;
			try {
				output.writeObject(request);
				response = (T) input.readObject();
			} catch (IOException e) {
				System.out.println("Send error");
			}catch (ClassNotFoundException e) {
				System.out.println(e.toString());
			} 
				
		return response;
	}

}
