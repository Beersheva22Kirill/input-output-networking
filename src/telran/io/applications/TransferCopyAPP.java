package telran.io.applications;

import telran.io.Copy;
import telran.io.FileCopyBuilder;

public class TransferCopyAPP {

	public static void main(String[] args) throws Exception {
		 FileCopyBuilder creator = new FileCopyBuilder();
		 Copy transferCopy = (Copy) creator.build("transferTo", args);
		 transferCopy.copyRun();

	}

}
