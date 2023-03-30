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
		StandartInputOutput InputOutput = new StandartInputOutput();
		constructMenu();
		mainMenu.perform(InputOutput);		
	}

	private static void constructMenu() {
		Item exit;
		subMenu.add(new Menu("Arifmetic operation", constructArifmeticMenu()));
		subMenu.add(new Menu("Date operation", constructDateMenu()));
		subMenu.add(exit = Item.of("Exit", s ->{},true));
		mainMenu = new Menu("Main Menu", subMenu);
	}

	private static ArrayList<Item> constructDateMenu() {
		ArrayList<Item> subDateMenu = new ArrayList<>();
		Item plusDate = Item.of("Plus Date", (io -> {
			LocalDate date  = io.readDate("Enter date by format dd-MM-yyyy", "Wrong date", "dd-MM-yyyy", LocalDate.MIN, LocalDate.MAX);
			Long num = io.readLong("Enter number days for plus", "Not number",Long.MIN_VALUE,Long.MAX_VALUE);
			io.writeLine(date.plusDays(num));
		}));
		
		Item minusDate = Item.of("Minus Date", (io -> {
			LocalDate date  = io.readDate("Enter date by format dd-MM-yyyy", "Wrong date", "dd-MM-yyyy", LocalDate.MIN, LocalDate.MAX);
			Long num = io.readLong("Enter number days for plus", "Not number",Long.MIN_VALUE,Long.MAX_VALUE);
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
		Item plus = Item.of("Plus", (io -> {
			Double num1  = io.readNumber("Enter first number", "Not number",  Double.MIN_VALUE, Double.MAX_VALUE);
			Double num2 = io.readNumber("Enter second number", "Not number", Double.MIN_VALUE, Double.MAX_VALUE);
			io.writeLine(num2 + num1);
		}));
		
		Item minus = Item.of("Minus", (io -> {
			Double num1  = io.readNumber("Enter first number", "Not number",  Double.MIN_VALUE, Double.MAX_VALUE);
			Double num2 = io.readNumber("Enter second number", "Not number", Double.MIN_VALUE, Double.MAX_VALUE);
			io.writeLine(num1 - num2);
		}));
		
		Item divide = Item.of("Divide", (io -> {
			Double num1  = io.readNumber("Enter first number", "Not number",  Double.MIN_VALUE, Double.MAX_VALUE);
			Double num2 = io.readNumber("Enter second number", "Not number", Double.MIN_VALUE, Double.MAX_VALUE);
			io.writeLine(num1 / num2);
		}));
		
		Item multiply = Item.of("Multiply", (io -> {
			Double num1  = io.readNumber("Enter first number", "Not number",  Double.MIN_VALUE, Double.MAX_VALUE);
			Double num2 = io.readNumber("Enter second number", "Not number", Double.MIN_VALUE, Double.MAX_VALUE);
			io.writeLine(num1 * num2);
		}));
		
		Item exit = constructExit();
		
		subArifmeticMenu.add(plus);
		subArifmeticMenu.add(minus);
		subArifmeticMenu.add(divide);
		subArifmeticMenu.add(multiply);
		subArifmeticMenu.add(exit);
		
		return subArifmeticMenu;
	}

}
