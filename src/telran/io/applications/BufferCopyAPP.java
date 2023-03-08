package telran.io.applications;


import telran.io.Copy;
import telran.io.FileCopyBuilder;

public class BufferCopyAPP {

	public static void main(String[] args) throws Exception {

		FileCopyBuilder creator = new FileCopyBuilder();
		 Copy bufferCopy = (Copy) creator.build("bufferCopy", args);
		 bufferCopy.copyRun();

	}
}


