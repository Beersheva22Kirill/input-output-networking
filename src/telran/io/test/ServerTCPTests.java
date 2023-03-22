package telran.io.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.System.Logger.Level;
import java.net.Socket;
import java.time.Instant;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import telran.util.Logger;
import telran.util.TcpClientHandler;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ServerTCPTests {

	private static final String HOSTNAME = "localhost";
	private static final int PORT = 4000;
	
	static Socket socket;
	static BufferedReader reader;
	static PrintStream writer;
	static TcpClientHandler client;
	static Logger LOG;

	@BeforeAll
	static void setUp() throws Exception {
			socket = new Socket(HOSTNAME, PORT);
			writer = new PrintStream(socket.getOutputStream());
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			client = new TcpClientHandler(socket, writer, reader);
			LOG = new Logger(client, "loggetTest");	
	}

	@Test
	void recordTraceTest() throws IOException {
		
		LOG.setLevel(Level.TRACE);
		LOG.trace("Test send trace");
		writer.println("count#trace");
		System.out.println(reader.readLine() + " records. Time: " + Instant.now().toString());
		LOG.trace("Test send trace 2");
		writer.println("count#trace");
		System.out.println(reader.readLine() + " records. Time: " + Instant.now().toString());
	}
	
	@Test
	void recordErrorTest() throws IOException {
		LOG.setLevel(Level.ERROR);
		LOG.trace("Not_valid");
		LOG.error("Valid_record");
		writer.println("count#error");
		System.out.println(reader.readLine() + " records. Time: " + Instant.now().toString());
	}
	
	@AfterAll
	public static void close() {
		client.close();
	}

}