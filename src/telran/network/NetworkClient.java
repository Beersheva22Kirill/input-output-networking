package telran.network;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public interface NetworkClient extends Closeable {
	
	<T> T send(String type, Serializable requestData);
	

}
