package telran.view.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.view.StandartInputOutput;

class InputOutputTests {
	
	StandartInputOutput inOut = new StandartInputOutput();

	@Test
	void testDate() throws Exception {
		Predicate<String> predicate = (s) -> s.matches("([a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.[a-zA-Z0-9_-]+)");
		LocalDate expectedDate = LocalDate.of(2022, 4, 12);
		assertEquals(1234, inOut.readInt("Enter number", "notNumber"));
		assertEquals(expectedDate, inOut.readDateISO("Enter date", "notDate"));
		assertEquals(12345, inOut.readInt("Enter number", "notIntNumber", 1, 23456));
		inOut.readStringPredicate("Enter E-mail", "Not Email", predicate);
	}
	
	@Test
	void testPredicateEmail() throws Exception {
		Predicate<String> predicate = (s) -> s.matches("([a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.[a-zA-Z0-9_-]+)");
		inOut.readStringPredicate("Enter E-mail", "Not Email", predicate);
	}
	
	@Test
	void testNumber() throws Exception {
		assertEquals(1234, inOut.readInt("Enter number", "notNumber"));
		assertEquals(12345, inOut.readInt("Enter number", "notIntNumber", 1, 23456));
	}
	
	@Test
	void testReadOption() throws Exception {
		Set<String> options = new HashSet<>();
		options.add("qwerty");
		options.add("trewq");
		assertEquals("qwerty",inOut.readStringOptions("Enter option", "This option not in set", options));
		
	}
	
	@Test
	void testReadDate() throws Exception {
		LocalDate dateExpected;
		System.out.println(inOut.readDate("Enter date format", "Not date", "dd-MM-yyyy", LocalDate.of(2022, 04, 01), LocalDate.of(2022, 04, 10)));
	}

}
