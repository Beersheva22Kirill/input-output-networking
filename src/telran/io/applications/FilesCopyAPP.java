package telran.io.applications;

import telran.io.Copy;
import telran.io.FileCopyBuilder;

public class FilesCopyAPP {

	public static void main(String[] args) throws Exception {

		 FileCopyBuilder creator = new FileCopyBuilder();
		 Copy filesCopy = (Copy) creator.build("filesCopy", args);
		 filesCopy.copyRun();

	}

}
