package telran.nework.app;

import telran.nework.*;

public class TcpClientObjectApp {

	private static final String HOST_NAME = "localhost";

	public static void main(String[] args) {
		
		int[] wrongData = null;
		TcpClient client;
		
		try {
			client = new TcpClient(HOST_NAME, ServerObjectApp.PORT);
			
			Response response = client.send("reverse", "1234567890");
			System.out.printf("response Server:%s DATA:%s\n",response.code,response.data);
			
			response = client.send("length", "1234");
			System.out.printf("response Server:%s DATA:%s\n",response.code,response.data);
			
			response = client.send("WrongType", "0123456789");
			System.out.printf("response Server:%s DATA:%s\n",response.code,response.data);
			
			response = client.send("reverse", wrongData);
			System.out.printf("response Server:%s DATA:%s\n",response.code,response.data);
	
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
	

	}

}
