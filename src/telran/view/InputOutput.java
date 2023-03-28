package telran.view;

import java.time.LocalDate;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

public interface InputOutput {
	
	String readString(String promt);
	void writeString(Object object);
	
	default void writeLine(Object object) {
		writeString(object.toString() + "\n");
	}
	
	default <R> R readObject(String prompt, String errorPrompt, Function<String, R> mapper) {
		boolean running = true;
		R result = null;
		while (running) {
			try {
				String str = readString(prompt);
				result = mapper.apply(str);
				running = false;
			} catch (Exception e) {
				writeLine(errorPrompt + " - " + e.getMessage());
			}
		}
		return result;
		
	}
	
	default String readStringPredicate(String prompt, String errorPrompt, Predicate<String> predicat) {
		//TODO	
		return null;	
	}
	
	default String readStringOptions(String prompt, String errorPrompt, Set<String> options) {
		//TODO
		//Entering string must be one the options
		return null;
		
	}
	
	default int readInt(String prompt, String errorPrompt) {
		//TODO
		return -1;
	}
	
	default int readInt(String prompt, String errorPrompt, int min, int max) {
		//TODO
		return -1;
	}
	
	default long readLong(String prompt, String errorPrompt, long min, long max) {
		//TODO
		return -1;
	}
	
	default LocalDate readDateISO(String prompt, String errorPrompt) {
		//TODO
		return null;
	}
	
	default LocalDate readDate(String prompt, String errorPrompt, String format,
			LocalDate min, LocalDate max) {
		//TODO
		return null;
	}
	
	default double readNumber (String prompt, String errorPrompt, double min, double max) {
		//TODO
		return -1;
		
	}

}
