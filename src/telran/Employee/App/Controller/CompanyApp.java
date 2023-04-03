package telran.Employee.App.Controller;

import java.util.*;
import telran.Employee.*;
import telran.view.*;

public class CompanyApp {

	private static final String FILE_PATH = "CompanyLocal.data";

	public static void main(String[] args) {
		InputOutput io = new StandartInputOutput();
		Company company = new CompanyImpl();
		company.restore(FILE_PATH);
		Item[] companyItems = CompanyController.getCompanyItems
				(company, new String[] {"QA", "Development", "Audit",
						"Management", "Accounting"});
		ArrayList<Item> items= new ArrayList<>(Arrays.asList(companyItems));
		items.add(Item.of("Exit & save", io1 -> company.save(FILE_PATH), true));
		Menu menu = new Menu("Company Application", items);
		menu.perform(io);

	}

}
