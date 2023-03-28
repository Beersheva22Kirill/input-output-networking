package telran.network;

import java.io.Serializable;
import telran.Employee.Company;
import telran.Employee.CompanyImpl;
import telran.Employee.Employee;


public class CompanyProtocol implements Protocol {
	
	Company company = new CompanyImpl();

	@Override
	public Response getResponse(Request request) {
		
		Response response = switch (request.type) {
		case "addEmployee" -> addEmployeeT(request.data);
		case "removeEmployee" -> removeEmployee(request.data);
		case "getAllEmployees" -> getAllEmployees(request.data);
		case "getEmployeesByMonth" -> getEmployeesByMonth(request.data);
		case "getEmployeesBySalary" -> getEmployeesBySalary(request.data);
		case "getEmployesByDepartment" -> getEmployesByDepartment(request.data);
		case "getEmployee" -> getEmployee(request.data);
		case "save" -> save(request.data);
		case "restore" -> restore(request.data);
		case "contains" -> contains(request.data);
		default -> new Response(ResponseCode.WRONG_REQUEST, request.data);
		};
		return response;
	}

	private Response contains(Serializable data) {
		Response response = new Response(null, data);
		response.data = (Serializable)company.contains((long) data);
		response.code = ResponseCode.OK;
		return response;
	}

	private Response restore(Serializable data) {
		Response response = new Response(null, null);
		company.restore((String) data);
		response.code = ResponseCode.OK;
		
		return response;
	}

	private Response save(Serializable data) {
		Response response = new Response(null, null);
		company.save((String) data);
		response.code = ResponseCode.OK;
		return response;
	}

	private Response getEmployee(Serializable data) {
		Response response = new Response(null, data);
		response.data = (Serializable)company.getEmployee((long) data);
		response.code = ResponseCode.OK;
		return response;
	}

	private Response getEmployesByDepartment(Serializable data) {
		Response response = new Response(null, data);
		response.data = (Serializable)company.getEmployesByDepartment((String) data);
		response.code = ResponseCode.OK;
		return response;
	}

	private Response getEmployeesBySalary(Serializable data) {
		Response response = new Response(null, data);
		int[] range = (int[])data;
		response.data = (Serializable)company.getEmployeesBySalary(range[0],range[1]);
		response.code = ResponseCode.OK;
		return response;
	}

	private Response getEmployeesByMonth(Serializable data) {
		Response response = new Response(null, data);
		response.data = (Serializable)company.getEmployeesByMonth((int) data);
		response.code = ResponseCode.OK;
		return response;
	}

	private Response getAllEmployees(Serializable data) {
		Response response = new Response(null, data);
		response.data = (Serializable) company.getAllEmployees();
		response.code = ResponseCode.OK;
		return response;
	}

	private Response removeEmployee(Serializable data) {
		Response response = new Response(null, data);
		response.data = (Serializable)company.removeEmployee((long) data);
		response.code = ResponseCode.OK;
		
		return response;
	}


	private Response addEmployeeT(Serializable data) {
		Response response = new Response(null, data);
		response.data = (Serializable)company.addEmployee((Employee) data); 
		response.code = ResponseCode.OK;
		return response;
	}

	
}
