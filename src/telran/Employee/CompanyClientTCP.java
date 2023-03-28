package telran.Employee;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import telran.nework.app.NetworkCompanyTCP;
import telran.network.TcpClient;

public class CompanyClientTcp implements Company {

	private static final long serialVersionUID = 1L;
	
	TcpClient clientCompany;
	
	public CompanyClientTcp() {
		try {
			clientCompany = new TcpClient("localhost", NetworkCompanyTCP.PORT);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public Iterator<Employee> iterator() {
		
		return clientCompany.send("iterator", null);
	}

	@Override
	public boolean addEmployee(Employee employee) {
		
		return clientCompany.send("addEmployee", employee);
	}

	@Override
	public Employee removeEmployee(long id) {
		
		return clientCompany.send("removeEmployee", id);
	}

	@Override
	public List<Employee> getAllEmployees() {
		
		return clientCompany.send("getAllEmployees", null);
	}

	@Override
	public List<Employee> getEmployeesByMonth(int month) {
		
		return clientCompany.send("getEmployeesByMonth", month);
	}

	@Override
	public List<Employee> getEmployeesBySalary(int SalaryFrom, int SalaryTo) {
		int[] range = {SalaryFrom,SalaryTo};
		
		return clientCompany.send("getEmployeesBySalary", range);
	}

	@Override
	public List<Employee> getEmployesByDepartment(String department) {
		
		return clientCompany.send("getEmployesByDepartment", department);
	}

	@Override
	public Employee getEmployee(long id) {
	
		return clientCompany.send("getEmployee", id);
	}

	@Override
	public void save(String pathName) {
		clientCompany.send("save", pathName);

	}

	@Override
	public void restore(String pathName) {
		clientCompany.send("restore", pathName);

	}

	@Override
	public boolean contains(long id) {
		
		return clientCompany.send("contains", id);
	}
	
	public void close() throws IOException {
		clientCompany.close();
	}


}
