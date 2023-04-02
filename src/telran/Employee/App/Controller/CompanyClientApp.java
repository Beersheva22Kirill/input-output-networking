package telran.Employee.App.Controller;

import java.io.IOException;

import telran.Employee.CompanyClientTCP;
import telran.view.StandartInputOutput;

public class CompanyClientApp {

	private static final String FILE_NAME = "CompanyLocal";

	public static void main(String[] args) {
		CompanyClientTCP company = new CompanyClientTCP();
		StandartInputOutput io = new StandartInputOutput();
		CompanyController controller = new CompanyController(io, company);
		company.save(FILE_NAME);
		try {
			company.close();
		} catch (IOException e) {
			e.getMessage();
		}

	}

}
