package telran.io.applications;

import java.io.*;
import java.util.Arrays;
import java.util.logging.*;

public class SourceDestinationsApp {
	
	static Logger LOG = Logger.getLogger("logger");

	public static void main(String[] args) throws IOException {
		Handler handler = new ConsoleHandler();
		LOG.addHandler(handler);
		handler.setLevel(Level.FINE);
		LOG.setLevel(Level.FINE);
		
		try {
			if (args.length > 1) {
				LOG.fine(String.format("arguments %s", Arrays.deepToString(args)));
				try(Reader reader = args[0].equalsIgnoreCase("console") ? 
						new InputStreamReader(System.in) : new FileReader(args[0]);
				PrintWriter writer = args[1].equalsIgnoreCase("console") 
						? new PrintWriter(new OutputStreamWriter(System.out)) : new PrintWriter(args[1]);){
					sourceToDestinations(reader, writer);
				}
			} else {
				System.out.println("too few arguments");
				LOG.severe("No arguments provider");
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}

	}
	
	static private void sourceToDestinations(Reader reader, PrintWriter writer) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(reader);
		while(true) {
			String line = bufferedReader.readLine();
			if (line == null) {
				break;
			}
			writer.println(line);
		}
	}

}
