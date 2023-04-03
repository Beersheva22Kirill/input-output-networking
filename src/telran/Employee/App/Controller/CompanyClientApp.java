package telran.Employee.App.Controller;

import java.io.*;
import java.util.*;
import telran.Employee.*;
import telran.nework.app.NetworkCompanyTCP;
import telran.view.*;


public class CompanyClientApp {

	private static final String FILE_NAME = "CompanyLocal";

	public static void main(String[] args) {
		CompanyClientTCP company = new CompanyClientTCP("localhost", NetworkCompanyTCP.PORT);
		StandartInputOutput io = new StandartInputOutput();
		
		Item[] companyItems = CompanyController.getCompanyItems
				(company, new String[] {"QA", "Development", "Audit",
						"Management", "Accounting"});
		ArrayList<Item> items= new ArrayList<>(Arrays.asList(companyItems));
		items.add(Item.of("Exit & save", io1 -> company.save(FILE_NAME), true));
		Menu menu = new Menu("Company Application", items);
		menu.perform(io);
		try {
			company.close();
		} catch (IOException e) {
			e.getMessage();
		}

	}

}
