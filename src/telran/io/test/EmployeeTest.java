package telran.io.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import junit.framework.Assert;
import telran.Employee.Company;
import telran.Employee.CompanyImpl;
import telran.Employee.Employee;

class EmployeeTest {
	String fileName = "company.data";
	String fileName1 = "company_new.data";
	Company company;
	Employee empl1 = new Employee(123, "Ivan", LocalDate.parse("1989-04-04"), "Department1", 24999);
	Employee empl2 = new Employee(124, "Sergey", LocalDate.parse("1984-02-21"), "Department2", 35000);
	Employee empl3 = new Employee(125, "Ilan", LocalDate.parse("1991-06-02"), "Department1", 35000);
	Employee empl4 = new Employee(126, "Adam", LocalDate.parse("1985-12-01"), "Department2", 25450);
	Employee empl5 = new Employee(127, "Olga", LocalDate.parse("1995-10-02"), "Department3", 22300);
	Employee empl6 = new Employee(128, "Ruti", LocalDate.parse("1988-04-08"), "Department3", 21000);
	Employee empl7 = new Employee(129, "Sara", LocalDate.parse("1983-03-05"), "Department3", 25033);

	@BeforeEach
	void setUp() {
		company = new CompanyImpl();
		company.addEmployee(empl1);
		company.addEmployee(empl2);
		company.addEmployee(empl3);
		company.addEmployee(empl4);
	}


	@Test
	void AddTest() throws FileNotFoundException, IOException {
		assertTrue(company.addEmployee(empl5)); 
		assertTrue(company.addEmployee(empl6)); 
		assertTrue(company.addEmployee(empl7)); 
		assertFalse(company.addEmployee(empl1)); 
	}
	
	@Test
	void removeTest() {
		assertEquals(empl1, company.removeEmployee(123));
		assertEquals(null, company.removeEmployee(123));
		List<Employee> employees = company.getAllEmployees();
		assertEquals(3, employees.size());
		Employee[] expected = {empl2,empl3,empl4};
		for (Employee employee : expected) {
			assertTrue(company.contains(employee.getId()));
		}
		assertEquals(empl3, company.removeEmployee(125));
		assertNull(company.getEmployesByDepartment(empl3.getDepartment()));
	}
	
	@Test
	void getEmployeesBySalary() {
		company.addEmployee(empl5);
		company.addEmployee(empl6);
		company.addEmployee(empl7);
		
		List<Employee> employees = company.getEmployeesBySalary(21000, 25000);
		assertEquals(3, employees.size());
		Employee[] expected = {empl1,empl5,empl6};
		for (Employee employee : expected) {
			assertTrue(company.contains(employee.getId()));
		}
	}
	
	@Test
	void getEmployeesByMonthTest() {
		company.addEmployee(empl5);
		company.addEmployee(empl6);
		company.addEmployee(empl7);
		
		List<Employee> employees = company.getEmployeesByMonth(4);
		assertEquals(2, employees.size());
		Employee[] expected = {empl1,empl6};
		for (Employee employee : expected) {
			assertTrue(company.contains(employee.getId()));
		}
	}
	
	@Test
	void getEmployeesByDepartmentTest() {
		company.addEmployee(empl5);
		company.addEmployee(empl6);
		company.addEmployee(empl7);
		
		List<Employee> employees = company.getEmployesByDepartment("Department3");
		assertEquals(3, employees.size());
		Employee[] expected = {empl5,empl6,empl7};
		for (Employee employee : expected) {
			assertTrue(company.contains(employee.getId()));
		}
	}
	
	@Test
	void saveRestoreTest() {
		company.addEmployee(empl5);
		company.addEmployee(empl6);
		company.addEmployee(empl7);	
		company.save(fileName);
		Company newCompany = new CompanyImpl();
		newCompany.restore(fileName);
		Employee[] expected = {empl1,empl2,empl3,empl4,empl5,empl6,empl7};
		for (Employee employee : expected) {
			assertTrue(newCompany.contains(employee.getId()));
		}
		
	}

}
