package telran.nework.app;

import telran.network.CompanyProtocol;
import telran.network.ProtocolString;
import telran.network.TcpServer;
import telran.network.UdpServer;

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
