package telran.nework.app;

import java.io.*;
import java.lang.System.Logger.Level;
import java.net.*;
import java.util.*;

import telran.util.LoggerRecord;

public class ServerLogApp {

	private static final int PORT = 4000;
	private static Map<String, Integer> counter = new HashMap<>();
	private static List<String> logMessanges = new ArrayList<>();


	public static void main(String[] args) throws Exception {

		ServerSocket serverSocket = new ServerSocket(PORT);
		System.out.println("Server starting - Port: " + PORT);
		while (true) {
			Socket socket = serverSocket.accept();
			try {
				runServerClient(socket);
			} catch (Exception e) {
				System.out.println("abnormal closing connection");
			}
		}

	}

	private static void runServerClient(Socket socket) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintStream writer = new PrintStream(socket.getOutputStream());
		
		while (true) {
			String request = reader.readLine();
			if (request == null) {
				break;
			}
			if (request.toUpperCase().equals("EXIT")) {
				break;
			}	
			String response = getResponse(request);
			writer.println(response);
		}
		System.out.println("Client closed connection");
		
	}

	private static String getResponse(String request) {
		String res = "Wrong request";
		String tokens[] = request.split("#");
		if (tokens.length > 1) {
			res = switch (tokens[0]) {
			case "log" -> tokens.length ==3 ? writeLogRecord(tokens[1],tokens[2]) : "Wrong number of arguments";
			case "count" -> getLogCount(tokens[1]);
			default -> "Wrong type: " + tokens[0];
			};
		}
		return res;
	}

	private static String getLogCount(String level) {
		 String res;
		 try {
			res = counter.get(level.toUpperCase()).toString() + " " + level;
		} catch (Exception e) {
			res = "Wrong level logging";
		}
		
		return res;
	}

	private static String writeLogRecord(String message, String level) {
		String res;
		try {
			logMessanges.add(message);
			level = level.toUpperCase();
			Integer count = counter.get(level);
			count = count == null ? count = 1 : ++count;
			counter.put(level, count);
			res = "OK";
			
		} catch (Exception e) {
			res = "Not recorded in base";
		}
		return res;
	}


}
