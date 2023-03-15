package telran.io.objects;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class RestorePerson {

	public static void main(String[] args) throws Exception {
		
		String filePath = "persons.data";		
		Persons persons = null;
	
		try(ObjectInputStream is = new ObjectInputStream(new FileInputStream(filePath))){
			persons = (Persons) is.readObject();
		}
		
		persons.forEach(System.out::println);
		
	}

}
