package telran.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

import javax.swing.text.DateFormatter;

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
	
	default String readStringPredicate(String prompt, String errorPrompt, Predicate<String> predicat) throws Exception {
		String res = readString(prompt);
		if (!predicat.test(res)) {
			throw new Exception(errorPrompt);
		}
	return res;	
	}
	
	default String readStringOptions(String prompt, String errorPrompt, Set<String> options) throws Exception {
		String res = readString(prompt);
		if (!options.contains(res)) {
			throw new Exception(errorPrompt);
		}
		return res;
		
	}
	
	default int readInt(String prompt, String errorPrompt) {
		int res = 0;
		try {
			res = Integer.valueOf(readString(prompt));
		} catch (Exception e) {
			System.out.println(errorPrompt + " - " + e.getMessage());
		}
		return res;
	}
	
	default int readInt(String prompt, String errorPrompt, int min, int max) {
		int res = 0;
		try {
			res = Integer.valueOf(readString(prompt));
			if (res < min || res > max) {
				 throw new Exception("Wrong range");
			}
		} catch (Exception e) {
			System.out.println(errorPrompt + " - " + e.getMessage());
		}
		return res;
	}
	
	default long readLong(String prompt, String errorPrompt, long min, long max) {
		long res = 0;
		try {
			res = Long.valueOf(readString(prompt));
		} catch (Exception e) {
			System.out.println(errorPrompt + " - " + e.getMessage());
		}
		return res;
	}
	
	default LocalDate readDateISO(String prompt, String errorPrompt) {
		LocalDate res = null;
		try {
			res = LocalDate.parse(readString(prompt));
		} catch (Exception e) {
			System.out.println(errorPrompt + " - " + e.getMessage());
		}
		return res;
	}
	
	default LocalDate readDate(String prompt, String errorPrompt, String format,
			LocalDate min, LocalDate max) {

		LocalDate res = null;
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
			res = LocalDate.parse(readString(prompt + ": " + format),formatter);
			if (res.compareTo(min) < 0 || res.compareTo(max) > 0) {
				 throw new Exception("Wrong range");
			}
		} catch (Exception e) {
			System.out.println(errorPrompt + " - " + e.getMessage());
		}
		return res;
	}
	
	default double readNumber (String prompt, String errorPrompt, double min, double max) {
		double res = 0;
		try {
			res = Double.valueOf(readString(prompt));
			if (res < min || res > max) {
				 throw new Exception("Wrong range");
			}
		} catch (Exception e) {
			System.out.println(errorPrompt + " - " + e.getMessage());
		}
		return res;
		
	}

}
