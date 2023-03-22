package telran.Employee;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystemLoopException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class CompanyImpl implements Company {

	private static final long serialVersionUID = 1L;
	
	Map<String, Set<Employee>> employeesDep = new HashMap<>();
	TreeMap<Integer, Set<Employee>> emplSalary = new TreeMap<>();
	Map<Long, Employee> emplId = new HashMap<>();
	Map<Integer, Set<Employee>> emplMonth = new HashMap<>();

	@Override
	public Iterator<Employee> iterator() { 
		
		return getAllEmployees().iterator();
	}

	@Override
	public boolean addEmployee(Employee employee) {
		
		Employee temp = emplId.put(employee.id, employee);
		addOtherMap(employeesDep, employee.department, employee);
		addOtherMap(emplMonth, employee.birthDate.getMonthValue(), employee);
		addOtherMap(emplSalary, employee.salary, employee);
					
		return temp == null;
	}
	
	private <T> void addOtherMap(Map<T, Set<Employee>> map, T key, Employee empl) {
		map.computeIfAbsent(key, k->new HashSet<>()).add(empl);
		
	}
	
	@Override
	public Employee removeEmployee(long id) {
		Employee removed = getEmployee(id);
		if (removed != null) {
			removedToOtherMaps(emplMonth, removed.birthDate.getMonthValue(), removed);
			removedToOtherMaps(employeesDep, removed.department, removed);
			removedToOtherMaps(emplSalary, removed.salary, removed);
			emplId.remove(removed.id);
		}
		
		return removed;
	}

	private <T> void removedToOtherMaps(Map<T, Set<Employee>> empl, T key, Employee employee) {
		Set<Employee> set = empl.get(key);
		set.remove(employee);
		if (set.isEmpty()) {
			empl.remove(key);
		}
		
	}


	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> result = new LinkedList(emplId.values());
		
		return result;
	}

	@Override
	public List<Employee> getEmployeesByMonth(int month) {
		List<Employee> list = new LinkedList<>(emplMonth.getOrDefault(month, Collections.emptySet()));
		return list;
	}

	@Override
	public List<Employee> getEmployeesBySalary(int SalaryFrom, int SalaryTo) {
		
		List<Employee> result = new ArrayList<>();
		SortedMap<Integer,Set<Employee>> map = emplSalary.subMap(SalaryFrom, true, SalaryTo, true);
		Set<Integer> set = map.keySet();
		Iterator<Integer> iterator = set.iterator();
			while (iterator.hasNext()) {
				int salary = iterator.next();
					Iterator<Employee> emplItr = emplSalary.get(salary).iterator();
					while(emplItr.hasNext()) {
						result.add(emplItr.next());
					}		
			}
		return result;
	}

	@Override
	public List<Employee> getEmployesByDepartment(String department) {
		List<Employee> list = new LinkedList<>(employeesDep.getOrDefault(department, Collections.emptySet()));
		return list;
	}

	@Override
	public Employee getEmployee(long id) {
		
		return emplId.get(id);
	}

	@Override
	public void save(String pathName) {
		try(ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(pathName))){
			os.writeObject(this.getAllEmployees());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void restore(String pathName) {	
		
	List<Employee> employeesTemp = new ArrayList<>();
	
	try(ObjectInputStream is = new ObjectInputStream(new FileInputStream(pathName))) {
		employeesTemp = (List<Employee>) is.readObject(); 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				}catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
	
		Iterator<Employee>iterator = employeesTemp.iterator();
			while (iterator.hasNext()) {
				this.addEmployee((Employee)iterator.next());
		
			}
	}
	@Override
	public boolean contains(long id) {
		
		return emplId.containsKey(id);
	}


}
