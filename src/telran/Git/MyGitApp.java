package telran.Git;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import telran.view.Item;
import telran.view.Menu;
import telran.view.StandartInputOutput;

public class MyGitApp {
	private static final String PATH_REPOSITORY = "D:\\TestGit";
	
	static ArrayList<Item> subMenu = new ArrayList();
	static Item mainMenu;
	static Item arifmeticMenu;
	static Item dateMenu;
	static GitRepositoryImpl repository;
	
	public static void main(String[] args) {
		
		StandartInputOutput inputOutput = new StandartInputOutput();
		repository = new GitRepositoryImpl(PATH_REPOSITORY);
		repository = repository.init();
		
		constructMenu();
		
		mainMenu.perform(inputOutput);
		repository.save();
		inputOutput.writeString("Application closed");

	}

	private static void constructMenu() {
		subMenu.add(new Menu("Operation in files", constructFilesMenu()));
		subMenu.add(new Menu("Operation in branch", constructBranchMenu()));
		subMenu.add(Item.exit());
		mainMenu = new Menu("Main Menu", subMenu);
		
	}
	
	private static ArrayList<Item> constructFilesMenu() {
		
		ArrayList<Item> subFilesMenu = new ArrayList<>();
		Item info = Item.of("Info", (io -> {
			List<FileState> files = repository.info();
			for (FileState file : files) {
				io.writeLine(file);
			}
			
		}));
		
		Item commit = Item.of("Commit", (io -> {
			io.writeLine(repository.commit(io.readString("Enter commit message")));
		}));
		
		Item log = Item.of("Get log (Commit massege)", (io -> {
			List<CommitMessage> allCommits = repository.log();
			for (CommitMessage message : allCommits) {
				io.writeLine(message);
			}
			
		}));
		
		Item getHead = Item.of("Get Head", (io -> {
			io.writeLine(repository.getHead());	
		}));
		
		Item commitContent = Item.of("Commit content", (io -> {
			List<Path> commitContentPath = repository.commitContent(io.readString("Enter commit name"));
			for (Path path : commitContentPath) {
				io.writeLine(path);
			}	
		}));
		
		Item swithTo = Item.of("Swith to", (io -> {
			io.writeLine(repository.switchTo(io.readString("Enter name branch or commit")));	
		}));
		
		Item testRestore = Item.of("Test restore", (io -> {
			try {
				repository.restore();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}));
		
		Item exit = Item.exit();
		subFilesMenu.add(info);
		subFilesMenu.add(commit);
		subFilesMenu.add(log);
		subFilesMenu.add(getHead);
		subFilesMenu.add(commitContent);
		subFilesMenu.add(swithTo);
		subFilesMenu.add(testRestore);
		subFilesMenu.add(Item.exit());
		
		return subFilesMenu;
	}

	private static ArrayList<Item> constructBranchMenu() {
		ArrayList<Item> subBranchMenu = new ArrayList<>();
		Item CreateBranch = Item.of("Create branch", (io -> { 
			io.writeLine(repository.createBranch(io.readString("Enter branch name")));
			}));
		
		Item RenameBbranch = Item.of("Rename branch", (io -> {
			repository.renameBranch(io.readString("Enter name branch"), io.readString("Enter new name branche"));
		}));
		Item AllBbranches = Item.of("All branches", (io -> {
			List<String> br = repository.branches();
			for (String nameBranch : br) {
				io.writeLine(nameBranch);
			}
		}));
		Item exit = Item.exit();
		subBranchMenu.add(CreateBranch);
		subBranchMenu.add(RenameBbranch);
		subBranchMenu.add(AllBbranches);
		subBranchMenu.add(exit);
	
		return subBranchMenu;
	}



}
