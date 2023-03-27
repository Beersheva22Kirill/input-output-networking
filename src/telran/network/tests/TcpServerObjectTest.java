package telran.network.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import telran.network.TcpClient;
import telran.nework.app.ServerObjectApp;

class TcpServerObjectTest {
	
	private static final String HOST_NAME = "localhost";
	int[] wrongData = null;
	static TcpClient client;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
			client = new TcpClient(HOST_NAME, ServerObjectApp.PORT);

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
