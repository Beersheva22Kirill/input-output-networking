package telran.nework.app;

import telran.network.ProtocolString;
import telran.network.TcpServer;
import telran.network.UdpServer;

public class ServerUdpObjectApp {

	public static final int PORT = 4000;

	public static void main(String[] args) {
			
		try {
			UdpServer server = new UdpServer(PORT, new ProtocolString());
			server.run();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

}
