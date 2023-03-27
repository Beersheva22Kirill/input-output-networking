package telran.network.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import telran.network.NetworkClient;
import telran.network.TcpClient;
import telran.network.UdpClient;
import telran.nework.app.ServerUdpObjectApp;

class UdpServerObjectTest {
	
	private static final String HOST_NAME = "localhost";
	static NetworkClient client;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
			client = new UdpClient(HOST_NAME, ServerUdpObjectApp.PORT );

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
			client.close();
	}

	@Test
	void ResponseTest() {

		assertEquals("olleH", client.send("reverse", "Hello"));
		Integer expected = 5;
		assertEquals(expected, client.send("length", "Hello"));
		
	}

}
