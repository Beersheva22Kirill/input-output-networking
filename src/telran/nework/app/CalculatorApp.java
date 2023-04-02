package telran.nework.app;

import java.time.LocalDate;
import java.util.ArrayList;

import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;
import telran.view.StandartInputOutput;

public class CalculatorApp {
	static ArrayList<Item> subMenu = new ArrayList();
	static Item mainMenu;
	static Item arifmeticMenu;
	static Item dateMenu;

	public static void main(String[] args) {
		StandartInputOutput inputOutput = new StandartInputOutput();
		constructMenu();
		mainMenu.perform(inputOutput);
		
		inputOutput.writeString("Application closed");
		
	}

	private static void constructMenu() {
		subMenu.add(new Menu("Arifmetic operation", constructArifmeticMenu()));
		subMenu.add(new Menu("Date operation", constructDateMenu()));
		subMenu.add(constructExit());
		mainMenu = new Menu("Main Menu", subMenu);
	}

	private static ArrayList<Item> constructDateMenu() {
		ArrayList<Item> subDateMenu = new ArrayList<>();
		Item plusDate = Item.of("Plus Date", (io -> {LocalDate date  = getDate(io);	
			Long num = getNumberForDate(io);
			io.writeLine(date.plusDays(num));
		}));
		
		Item minusDate = Item.of("Minus Date", (io -> {
			LocalDate date  = getDate(io);
			Long num = getNumberForDate(io);
			io.writeLine(date.minusDays(num));
		}));
		
		Item exit = constructExit();
		subDateMenu.add(plusDate);
		subDateMenu.add(minusDate);
		subDateMenu.add(exit);
		
		return subDateMenu;
	}

	private static Item constructExit() {
		Item exit = Item.of("Exit", s -> {}, true);
		return exit;
	}

	private static ArrayList<Item> constructArifmeticMenu() {
		ArrayList<Item> subArifmeticMenu = new ArrayList<>();
		Item plus = Item.of("Plus", (io -> {Double[] numbers = getNumbers(io);io.writeLine(numbers[0] + numbers[1]);}));
		Item minus = Item.of("Minus", (io -> {Double[] numbers = getNumbers(io);io.writeLine(numbers[0] - numbers[1]);	}));	
		Item divide = Item.of("Divide", (io -> {Double[] numbers = getNumbers(io);io.writeLine(numbers[0] / numbers[1]);}));
		Item multiply = Item.of("Multiply", (io -> {Double[] numbers = getNumbers(io);io.writeLine(numbers[0] * numbers[1]);}));
		Item exit = constructExit();
		
		subArifmeticMenu.add(plus);
		subArifmeticMenu.add(minus);
		subArifmeticMenu.add(divide);
		subArifmeticMenu.add(multiply);
		subArifmeticMenu.add(exit);
		
		return subArifmeticMenu;
	}

	private static Double[] getNumbers(InputOutput io) {	
		Double[] numbers = new Double[2];
		numbers[0]  = io.readNumber("Enter first number", "Not number",  Double.NEGATIVE_INFINITY, Double.MAX_VALUE);
		numbers[1] = io.readNumber("Enter second number", "Not number", Double.NEGATIVE_INFINITY, Double.MAX_VALUE);
		return numbers;
	}
	
	private static long getNumberForDate(InputOutput io) {
		return io.readLong("Enter number days", "Not number",1,Long.MAX_VALUE);
	}

	private static LocalDate getDate(InputOutput io) {
		return io.readDate("Enter the date in the format dd-MM-yyyy", "Wrong date", "dd-MM-yyyy", LocalDate.MIN, LocalDate.MAX);
	}

}
