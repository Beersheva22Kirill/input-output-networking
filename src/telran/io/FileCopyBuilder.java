package telran.io;

public class FileCopyBuilder {
	
	public Copy build(String typeCopy, String[] args) throws Exception {
		Copy res;
		switch (typeCopy) {
			case "transferTo": {
				res = new TransferCopy(args[0], args[1], getReplaceParametr(args)); break;
			}
			case "filesCopy": {
				res = new FilesCopy (args[0], args[1], getReplaceParametr(args)); break;
			}
			case "bufferCopy": {
				res = new BufferCopy (args[0], args[1], getReplaceParametr(args), getBufferSize(args)); break;
			}
		default: {
			res = null;
			}
		}
		
		return res;
	
	}

	private long getBufferSize(String[] args) throws Exception {
		long res = 1048576;
		if (args.length > 3) {	
			try {
				res = Long.valueOf(args[3]);
			} catch (Exception e) {
				throw new Exception("BufferSize must be numeric");
			}
		}
		return res;
	}

	private boolean getReplaceParametr(String[] args) throws Exception {
		boolean res = false;
		if (args.length > 2) {	
		if (args[2].toUpperCase().equals("YES")) {
				res = true;
			} else if (args[2].toUpperCase().equals("NO")) {
				res = false;
			} else {
				throw new Exception("Parametr to replace file must Yes or No (case insensitive) ");
			}
		}
		return res;
	}
	

}
