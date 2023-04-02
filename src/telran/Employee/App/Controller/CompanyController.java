package telran.Employee.App.Controller;

import java.time.LocalDate;
import java.util.*;

import telran.Employee.Company;
import telran.Employee.Employee;
import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;
import telran.view.StandartInputOutput;


public class CompanyController {

	InputOutput inputOutput;
	Company client;
	ArrayList<Item> subMenu;
	Item mainMenu;
	Item userItemMenu;
	Item adminItemMenu;
	
	void contructMenu(){
		subMenu = new ArrayList<>();
		Menu userOperation = new Menu("User operation", constructUserItemMenu());
		Menu adminOperation = new Menu("Admin operation", constructAdminItemMenu());
		subMenu.add(userOperation);
		subMenu.add(adminOperation);
		subMenu.add(Item.exit());
		mainMenu = new Menu("Main Menu", subMenu);
		mainMenu.perform(inputOutput);
		inputOutput.writeString("Application closed");
	}


	private ArrayList<Item> constructAdminItemMenu() {
		ArrayList<Item> subAdminItemMenu = new ArrayList<>();
		Item addEmployee = Item.of("Add employee", (io ->{
			long id = io.readLong("Enter Id", "Not number", Long.MIN_VALUE, Long.MAX_VALUE);
			String name = io.readString("Enter name");
			LocalDate birthDate = io.readDateISO("Enter date in format yyyy-MM-dd", "Wrong date or format");
			String department = io.readString("Enter department");
			int salary = io.readInt("Enter salary", "Not number");
			Employee employeeForAdd = new Employee(id, name, birthDate, department, salary);
			io.writeLine(client.addEmployee(employeeForAdd));
		}));
		Item removeEmployee = Item.of("Remove employee", (io -> {
			long id = io.readLong("Enter id for remove employee", "Not number", Long.MIN_VALUE, Long.MAX_VALUE);
			Employee removeEmpl = client.removeEmployee(id);
			if ( removeEmpl !=null) {
				io.writeLine("Employee: " + removeEmpl.toString() + " removed");
			} else {
				io.writeLine("Not Emloyee with id " + id + " in company");
			}
			
		}));	
		Item exit = Item.exit();
		
		subAdminItemMenu.add(addEmployee);
		subAdminItemMenu.add(removeEmployee);
		subAdminItemMenu.add(exit);
		
		return subAdminItemMenu;
	}

	private ArrayList<Item> constructUserItemMenu() {
		ArrayList<Item> subUserItemMenu = new ArrayList<>();
		Item getAllEmployee = Item.of("get All Employees", (io ->{
			List<Employee> empl = client.getAllEmployees();
			io.writeLine(empl.toString());}));
		Item getBySalary = Item.of("get Employees by salary", (io -> {
			int fromSalary = io.readInt("Enter from salary", "Not number");
			int toSalary = io.readInt("Enter to salary", "Not number");
			io.writeLine(client.getEmployeesBySalary(fromSalary, toSalary));
		}));	
		Item getByMonth = Item.of("get Employees by month", (io -> {
			int month = io.readInt("Enter month birthDate", "Not number");
			io.writeLine(client.getEmployeesByMonth(month));
		}));
		Item getByDepartment = Item.of("get Employees by department", (io -> {
			String department = io.readString("Enter department");
			io.writeLine(client.getEmployesByDepartment(department));
		}));
		Item exit = Item.exit();
		
		subUserItemMenu.add(getAllEmployee);
		subUserItemMenu.add(getBySalary);
		subUserItemMenu.add(getByMonth);
		subUserItemMenu.add(getByDepartment);
		subUserItemMenu.add(exit);
		
		return subUserItemMenu;
	}


	public CompanyController(StandartInputOutput inputOutput, Company client) {
		this.inputOutput = inputOutput;
		this.client = client;
		contructMenu();
	}
	


}
