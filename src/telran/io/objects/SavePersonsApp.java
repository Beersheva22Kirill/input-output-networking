package telran.io.objects;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class SavePersonsApp {

	public static void main(String[] args) throws Exception {
		
		String filePath = "persons.data";
		
		Person person = new Person(123, "Yura");
		Person person2 = new Person(124, "Yura2");
		person.person = person2;
		person2.person = person;
		
		Persons persons = new Persons();
		persons.addPerson(person);
		persons.addPerson(person2);
		
		try(ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(filePath))){
			os.writeObject(persons);
		}
		
		

	}

}
