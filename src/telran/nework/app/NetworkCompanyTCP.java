package telran.nework.app;

import telran.network.CompanyProtocol;
import telran.network.ProtocolString;
import telran.network.TcpServer;
import telran.network.UdpServer;

public class NetworkCompanyTCP {

	public static final int PORT = 4000;
	private static final String FILE_NAME = "CompanyLocal";

	public static void main(String[] args) {
			
		try {
			CompanyProtocol protocol = new CompanyProtocol();
			protocol.restore(FILE_NAME);
			TcpServer server = new TcpServer(protocol, PORT);
			server.run();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

}
