package telran.Employee;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Spliterator;
import java.util.TreeMap;
import java.util.TreeSet;

public class CompanyImpl implements Company {

	private static final long serialVersionUID = 1L;
	
	Map<String, List<Employee>> employeesDep = new HashMap<>();
	Map<Integer, List<Employee>> emplSalary = new TreeMap<>();
	Map<Long, Employee> emplId = new HashMap<>();
	Map<Integer, List<Employee>> emplMonth = new HashMap<>();

	@Override
	public Iterator<Employee> iterator() {
		List<Employee> list = getAllEmployees();
		
		return list.iterator();
	}

	@Override
	public boolean addEmployee(Employee employee) {
		
		Employee temp = emplId.put(employee.id, employee);
		
		List<Employee> listDep = getEmployesByDepartment(employee.department);
		if (listDep != null) {
			listDep.add(employee);
		} else {
			listDep = new LinkedList<>();
			listDep.add(employee);
			employeesDep.put(employee.department, listDep);
		}
						
		List<Employee> listSalary = getEmployeesBySalary(employee.salary, employee.salary);
		if (!listSalary.isEmpty()) {
			listSalary.add(employee);
		}	else {
			listSalary = new LinkedList<>();
			listSalary.add(employee);
			emplSalary.put(employee.salary, listSalary);
		}
		
		List<Employee> listMounth = getEmployeesByMonth(employee.birthDate.getMonthValue());
		if (listMounth != null) {
			listMounth.add(employee);
		}	else {
			listMounth = new LinkedList<>();
			listMounth.add(employee);
			emplMonth.put(employee.birthDate.getMonthValue(), listMounth);
		}
					
		return temp == null;
	}
	
	@Override
	public Employee removeEmployee(long id) {
		Employee removed = getEmployee(id);
		if (removed != null) {
			removedToAdditionalMaps(removed);
			emplId.remove(removed.id);
		}
		
		return removed;
	}

	private void removedToAdditionalMaps(Employee removed) {
		List<Employee> temp = getEmployesByDepartment(removed.department);

		if (temp.size() > 1) {
			temp.remove(removed);
			} else {
				employeesDep.remove(removed.department);
			}		
		
		temp = getEmployeesBySalary(removed.salary, removed.salary);
		
		if(temp.size() > 1) {
			temp.remove(removed);
			} else {
				emplSalary.remove(removed.salary);
			}
			
		temp = getEmployeesByMonth(removed.birthDate.getMonthValue());
		if (temp.size() > 1) {
			temp.remove(removed);
			} else {
				emplMonth.remove(removed.birthDate.getMonthValue());
			}
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> result = new ArrayList<>();
		Set<Long> set = emplId.keySet();
		Iterator<Long> iterator = set.iterator();
		
		while (iterator.hasNext()) {
			result.add(getEmployee(iterator.next()));	
		}
		return result;
	}

	@Override
	public List<Employee> getEmployeesByMonth(int month) {
		
		return emplMonth.get(month);
	}

	@Override
	public List<Employee> getEmployeesBySalary(int SalaryFrom, int SalaryTo) {
		
		List<Employee> result = new ArrayList<>();
		Set<Integer> set = emplSalary.keySet();
		Iterator<Integer> iterator = set.iterator();
			while (iterator.hasNext()) {
				int salary = iterator.next();
				if (salary > SalaryTo) 	return result;
				if ( salary >= SalaryFrom && salary <= SalaryTo) {
					Iterator<Employee> emplItr = emplSalary.get(salary).iterator();
					while(emplItr.hasNext()) {
						result.add(emplItr.next());
					}
				}		
			}
		return result;
	}

	@Override
	public List<Employee> getEmployesByDepartment(String department) {
		
		return employeesDep.get(department);
	}

	@Override
	public Employee getEmployee(long id) {
		
		return emplId.get(id);
	}

	@Override
	public void save(String pathName) {
		try(ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(pathName))){
			os.writeObject(this);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void restore(String pathName) {	
		
	Company companyRestoreTemp = new CompanyImpl();
	
	try(ObjectInputStream is = new ObjectInputStream(new FileInputStream(pathName))) {
			companyRestoreTemp = (CompanyImpl)is.readObject(); 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				}catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
	
		Iterator<Employee>iterator = companyRestoreTemp.iterator();
			while (iterator.hasNext()) {
				this.addEmployee((Employee)iterator.next());
		
			}
	}
	@Override
	public boolean contains(long id) {
		
		return emplId.containsKey(id);
		
	}


}
