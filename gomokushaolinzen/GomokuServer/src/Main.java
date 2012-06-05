/**
 * Main class
 * @author Vlad Manea
 * @author Vlad Tudose
 */
public class Main {
	
	/**
	 * main method
	 * @param args
	 */
	public static void main(String args[]) {
		
		// arguments as params?
		if (args.length > 3) {
		
			// create server
			new ServerMain(
				Integer.parseInt(args[0]), 
				Integer.parseInt(args[1]), 
				Integer.parseInt(args[2]),
				Integer.parseInt(args[3])
			);
		
		} else {
			
			// create server
			new ServerMain(8100, 19, 19, 5);
			
		}
		
	}

}
