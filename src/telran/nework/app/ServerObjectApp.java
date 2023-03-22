package telran.nework.app;

import telran.nework.ProtocolString;
import telran.nework.TcpServer;

public class ServerObjectApp {

	public static final int PORT = 4000;

	public static void main(String[] args) {
			
		try {
			TcpServer server = new TcpServer(new ProtocolString(), PORT);
			server.run();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

}
