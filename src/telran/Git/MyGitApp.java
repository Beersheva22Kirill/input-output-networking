package telran.Git;

import java.util.ArrayList;

import telran.view.Item;
import telran.view.Menu;
import telran.view.StandartInputOutput;

public class MyGitApp {
	static ArrayList<Item> subMenu = new ArrayList();
	static Item mainMenu;
	static Item arifmeticMenu;
	static Item dateMenu;

	
	public static void main(String[] args) {
		GitRepositoryImpl repository = new GitRepositoryImpl();
		StandartInputOutput inputOutput = new StandartInputOutput();
		constructMenu();
		
		mainMenu.perform(inputOutput);
		
		inputOutput.writeString("Application closed");

	}

	private static void constructMenu() {
		subMenu.add(new Menu("Operation in files", constructFilesMenu()));
		subMenu.add(new Menu("Operation in branch", constructBranchMenu()));
		subMenu.add(Item.exit());
		mainMenu = new Menu("Main Menu", subMenu);
		
	}

	private static ArrayList<Item> constructBranchMenu() {
		ArrayList<Item> subBranchMenu = new ArrayList<>();
		Item plusDate = Item.of("Create branch", (io -> {}));
		Item minusDate = Item.of("Rename branch", (io -> {}));
		Item exit = Item.exit();
		subBranchMenu.add(plusDate);
		subBranchMenu.add(minusDate);
		subBranchMenu.add(exit);
	
		return subBranchMenu;
	}

	private static ArrayList<Item> constructFilesMenu() {
		
		ArrayList<Item> subFilesMenu = new ArrayList<>();
		Item plusDate = Item.of("Info", (io -> {}));
		Item minusDate = Item.of("Get Head", (io -> {}));
		Item exit = Item.exit();
		subFilesMenu.add(plusDate);
		subFilesMenu.add(minusDate);
		subFilesMenu.add(exit);
	
		return subFilesMenu;
	}

}
