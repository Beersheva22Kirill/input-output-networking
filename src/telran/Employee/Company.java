package telran.Employee;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public interface Company extends Iterable<Employee>, Serializable{
	//return true if added (no two employees with the same ID))
	boolean addEmployee(Employee employee);
	//return reference to removed Employees or null
	Employee removeEmployee(long id);
	List<Employee> getAllEmployees();
	//return employees born at given month
	List<Employee> getEmployeesByMonth(int month);
	//employees with salary in a given salary
	List<Employee> getEmployeesBySalary(int SalaryFrom, int SalaryTo);

	List<Employee> getEmployesByDepartment(String department);
	
	Employee getEmployee(long id);
	//save all employee objects
	void save(String pathName);
	//restore all employee objects
	void restore (String pathName);
	boolean contains (long id);
}
