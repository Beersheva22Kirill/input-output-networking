package telran.network;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public  class UdpUtils {
	
	public static final int MAX_BUFFER_LENGTH = 100000;
	
	public static byte[] toBytesArray(Serializable object) throws Exception {
		try(ByteArrayOutputStream outputBytes = new ByteArrayOutputStream();
				ObjectOutputStream outputObject = new ObjectOutputStream(outputBytes);){
			outputObject.writeObject(object);
			return outputBytes.toByteArray();
		}		
	}
	
	public static Serializable toSerializable(byte[] array, int length) throws Exception {
		try(ByteArrayInputStream inputBytes = new ByteArrayInputStream(array, 0, length);
				ObjectInputStream inputObject = new ObjectInputStream(inputBytes);){
			return (Serializable) inputObject.readObject(); 
		}		
	}

}
