package telran.io.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import telran.nework.Response;
import telran.nework.ResponseCode;
import telran.nework.TcpClient;
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

		Response response = client.send("reverse", "1234567890");
		assertEquals(ResponseCode.OK, response.code);
		assertEquals("0987654321", response.data);
		System.out.printf("response Server:%s DATA:%s\n",response.code,response.data);
		
		response = client.send("length", "1234");
		assertEquals(ResponseCode.OK, response.code);
		assertEquals("4", response.data);
		System.out.printf("response Server:%s DATA:%s\n",response.code,response.data);
		
		response = client.send("WrongType", "0123456789");
		assertEquals(ResponseCode.WRONG_REQUEST, response.code);
		assertEquals("0123456789", response.data);
		System.out.printf("response Server:%s DATA:%s\n",response.code,response.data);
		
		response = client.send("reverse", wrongData);
		assertEquals(ResponseCode.WRONG_DATA, response.code);
		assertNull(response.data);
		System.out.printf("response Server:%s DATA:%s\n",response.code,response.data);
	}

}
