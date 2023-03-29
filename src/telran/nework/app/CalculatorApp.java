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
		subMenu.add(new Menu("arifmeticMenu", constructArifmeticMenu()));
		subMenu.add(new Menu("Date menu", constructDateMenu()));
		subMenu.add(new Menu("Exit", new ArrayList<Item>()));
		new ArrayList<>();
		mainMenu = new Menu("mainMenu", subMenu);
	}

	private static ArrayList<Item> constructDateMenu() {
		ArrayList<Item> subDateMenu = new ArrayList<>();
		
		Item plusDate = new Item() {
			
			@Override
			public void perform(InputOutput io) {
				LocalDate date = io.readDate("Enter date by format dd-MM-yyyy", "Wrong date", "dd-MM-yyyy", LocalDate.MIN, LocalDate.MAX);
				Long num = io.readLong("Enter number days for plus", "Not number",Long.MIN_VALUE,Long.MAX_VALUE);
				io.writeLine(date.plusDays(num));
				
			}
			
			@Override
			public boolean isExit() {
				return false;
			}
			
			@Override
			public String displayName() {
				return "Plus date";
			}
		};
		Item minusDate = new Item() {
			
			@Override
			public void perform(InputOutput io) {
				LocalDate date = io.readDate("Enter date by format dd-MM-yyyy", "Wrong date", "dd-MM-yyyy", LocalDate.MIN, LocalDate.MAX);
				Long num = io.readLong("Enter number days for minus", "Not number",Long.MIN_VALUE,Long.MAX_VALUE);
				io.writeLine(date.minusDays(num));
				
			}
			
			@Override
			public boolean isExit() {
				return false;
			}
			
			@Override
			public String displayName() {
				return "Minus date";
			}
		};
		Item exit = constructExit();
		
		subDateMenu.add(plusDate);
		subDateMenu.add(minusDate);
		subDateMenu.add(exit);
		
		return subDateMenu;
	}

	private static Item constructExit() {
		Item exit = new Item() {
			
			@Override
			public void perform(InputOutput io) {	
			}
			
			@Override
			public boolean isExit() {
				return true;
			}
			
			@Override
			public String displayName() {
				
				return "Exit";
			}
		};
		return exit;
	}

	private static ArrayList<Item> constructArifmeticMenu() {
		ArrayList<Item> subArifmeticMenu = new ArrayList<>();
		Item plus = new Item() {
			
			@Override
			public void perform(InputOutput io) {
				Double num1 = io.readNumber("Enter number 1", "Not number", Double.MIN_VALUE, Double.MAX_VALUE);
				Double num2 = io.readNumber("Enter number 2", "Not number", Double.MIN_VALUE, Double.MAX_VALUE);
				io.writeLine(num2 + num1);
				
			}
			
			@Override
			public boolean isExit() {
				return false;
			}
			
			@Override
			public String displayName() {
				
				return "Plus";
			}
		};	
		Item minus = new Item() {
			
			@Override
			public void perform(InputOutput io) {
				Double num1 = io.readNumber("Enter number 1", "Not number", Double.MIN_VALUE, Double.MAX_VALUE);
				Double num2 = io.readNumber("Enter number 2", "Not number", Double.MIN_VALUE, Double.MAX_VALUE);
				io.writeLine(num1 - num2);
				
			}
			
			@Override
			public boolean isExit() {
				return false;
			}
			
			@Override
			public String displayName() {
				
				return "Minus";
			}
		};		
		Item divide = new Item() {
			
			@Override
			public void perform(InputOutput io) {
				Double num1 = io.readNumber("Enter number 1", "Not number", Double.MIN_VALUE, Double.MAX_VALUE);
				Double num2 = io.readNumber("Enter number 2", "Not number", Double.MIN_VALUE, Double.MAX_VALUE);
				io.writeLine(num1 * num2);
				
			}
			
			@Override
			public boolean isExit() {
				return false;
			}
			
			@Override
			public String displayName() {
				
				return "Divide";
			}
		};
		Item multiple = new Item() {
			
			@Override
			public void perform(InputOutput io) {
				Double num1;
				Double num2;
				num1 = io.readNumber("Enter number 1", "Not number", Double.MIN_VALUE, Double.MAX_VALUE);
				num2 = io.readNumber("Enter number 2", "Not number", Double.MIN_VALUE, Double.MAX_VALUE);
				io.writeLine(num1 / num2);	
			}
			
			@Override
			public boolean isExit() {
				return false;
			}
			
			@Override
			public String displayName() {
				
				return "Multiple";
			}
		};
		Item exit = constructExit();
		
		subArifmeticMenu.add(plus);
		subArifmeticMenu.add(minus);
		subArifmeticMenu.add(divide);
		subArifmeticMenu.add(multiple);
		subArifmeticMenu.add(exit);
		
		return subArifmeticMenu;
	}

}
