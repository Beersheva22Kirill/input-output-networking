package telran.io.objects;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Persons implements Serializable, Iterable<Person>{
	
	private static final long serialVersionUID = 1L;
	
	ArrayList<Person> persons = new ArrayList<Person>();

	@Override
	public Iterator<Person> iterator() {
		
		return persons.iterator();
	}
	
	void addPerson(Person person) {
		
		persons.add(person);
	}
		
	
}
