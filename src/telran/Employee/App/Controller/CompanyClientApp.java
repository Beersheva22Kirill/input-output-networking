package telran.Employee.App.Controller;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import telran.Employee.*;
import telran.view.*;


public class CompanyClientApp {

	private static final String FILE_NAME = "CompanyLocal.data";
	private static final String PACKAGE_CLIENT = "telran.Employee.";

	public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		if (args.length == 0) {
			System.out.println("Must be at least one argument");
		} else {
			StandartInputOutput io = new StandartInputOutput();
			Properties properties = new Properties();
			try {
				FileInputStream iStream = new FileInputStream(args[0]);
				properties.load(iStream);
			} catch (FileNotFoundException e) {
				System.out.println("File configuration not found");
			}
			
			String configTransportType = getTransport(properties);
			String configHostName = properties.getProperty("hostname");
			int configPort = Integer.valueOf(properties.getProperty("port"));
			String[] configDepartments = properties.getProperty("departments").split(",");
			
			@SuppressWarnings("unchecked")
			Class<Company> clientClazz = 
					(Class<Company>) Class.forName(PACKAGE_CLIENT + configTransportType);
			Constructor<Company> constructor = args.length > 1 ? clientClazz.getConstructor(String.class, int.class) 
					: clientClazz.getConstructor();
			Company company = args.length > 1 ? (Company) constructor.newInstance(configHostName, configPort) 
					: (Company) constructor.newInstance();
			if (company instanceof CompanyImpl) {
				company.restore(FILE_NAME);
			}
			Item[] companyItems = CompanyController.getCompanyItems(company, configDepartments);
			ArrayList<Item> items = new ArrayList<>(Arrays.asList(companyItems));
			items.add(Item.of("Exit & save", io1 -> company.save(FILE_NAME), true));
			
			Menu menu = new Menu("Company Application:" + properties.getProperty("transport"), items);
			menu.perform(io);

			
			if (company instanceof CompanyClientTCP) {
				((CompanyClientTCP) company).close();
			} else if (company instanceof CompanyClientUDP) {
				((CompanyClientUDP) company).close();
			}
		io.writeLine("Aplication closed");
		}
			

	}

	private static String getTransport(Properties properties) {
		String res = null;
		res = switch (properties.getProperty("transport")) {
		case "TCP" -> "CompanyClientTCP";
		case "UDP" ->"CompanyClientUDP";
		case "local" ->"CompanyImpl";
		default -> throw new RuntimeException("Wrong type transport in file CompanyConfig.txt, valid type TCP or UDP");
		};
		return res;
	}

}
