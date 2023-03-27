package telran.network;

import static telran.network.UdpUtils.*;

import java.io.IOException;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UdpClient implements NetworkClient {

	private DatagramSocket socket;
	private Integer port;
	private String hostName;

	@Override
	public void close() throws IOException {
		socket.close();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T send(String type, Serializable requestData) {
		Request request = new Request(type, requestData);
		try {
			byte[] bufferSend = toBytesArray(request);
			byte[] bufferRecive = new byte[MAX_BUFFER_LENGTH];
			DatagramPacket packetSend = new DatagramPacket(bufferSend, bufferSend.length, 
					InetAddress.getByName(hostName), port);
			DatagramPacket packetRecive = new DatagramPacket(bufferRecive, MAX_BUFFER_LENGTH);
			socket.send(packetSend);
			socket.receive(packetRecive);
			Response response = (Response) toSerializable(packetRecive.getData(), packetRecive.getLength());
			if (response.code != ResponseCode.OK) {
				throw new Exception(response.data.toString());
			}
			return (T) response.data;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}	
	}
		

	public UdpClient(String hostName, Integer port) throws Exception {
		this.port = port;
		this.hostName = hostName;
		try {
			socket = new DatagramSocket();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
	}
	
	

}
