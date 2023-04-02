package telran.Employee.App.Controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import telran.Employee.CompanyImpl;
import telran.view.StandartInputOutput;

public class CompanyApp {

	private static final String FILE_NAME = "CompanyLocal";;

	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		StandartInputOutput io = new StandartInputOutput();
		CompanyImpl company = new CompanyImpl();
		try {
			company.restore(FILE_NAME);
		} catch (Exception e) {
			e.getMessage();
		}
		CompanyController controller = new CompanyController(io, company);
		company.save(FILE_NAME);
		

	}

}
