package telran.Employee;

import java.util.Comparator;

public class ComparatorEmpl implements Comparator<Employee> {

	@Override
	public int compare(Employee o1, Employee o2) {
		int res = 0;
		if (o1.id < o2.id) {
			res = -1;
		} else {
			res = 1;
		}
		return res;
	}


}
