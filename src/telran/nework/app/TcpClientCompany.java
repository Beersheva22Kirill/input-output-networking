package telran.nework.app;

import java.time.LocalDate;

import telran.Employee.Employee;
import telran.network.Response;
import telran.network.TcpClient;
import telran.network.UdpClient;
import telran.nework.app.ServerObjectApp;

public class TcpClientCompany {
	
	private static final long ID1 = 123;
	private static final int MONTH1 = 1;
	private static final String DEPARTMENT1 = "department1";
	private static final int SALARY1 = 1000;
	
	private static final long ID2 = 124;
	private static final int MONTH2 = 2;
	private static final String DEPARTMENT2 = "department2";
	private static final int SALARY2 = 2000;

	public static void main(String[] args) throws Exception {
		    TcpClient client = new TcpClient("localhost",ServerObjectApp.PORT);
			Employee empl1 = new Employee(ID1, "name", LocalDate.of(2000, MONTH1, 1), DEPARTMENT1, SALARY1);
			Employee empl2 = new Employee(ID2, "name2", LocalDate.of(2000, MONTH2, 1), DEPARTMENT2, SALARY2);
			Response response = client.send("addEmploee", empl1);
			System.out.println(response.code);
			response = client.send("addEmploee", empl2);
			System.out.println(response.code);
			//System.out.println(response.code);
		

	}

}
