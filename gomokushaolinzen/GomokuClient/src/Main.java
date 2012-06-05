import java.io.IOException;

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
		
		try {
			
			// arguments as params?
			if (args.length > 1) {
			
				// create server
				new ClientMain(
					args[0], 
					Integer.parseInt(args[1])
				);
			
			} else {
				
				// create server
				new ClientMain("127.0.0.1", 8100);
				
			}
		
		} catch(IOException exception) {
			
			// write error
			System.err.println("I/O Error in server: " + exception);
			
			// exit with error
			System.exit(1);
			
		}
		
	}

}
