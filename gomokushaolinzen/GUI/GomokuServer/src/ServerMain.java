import java.io.*;
import java.net.*;

/**
 * ServerMain class
 * @author Vlad Manea
 * @author Vlad Tudose
 */
public class ServerMain {

	// server socket
	ServerSocket socket = null;

	/*
	 * ServerMain method
	 * @param port
	 * @param height
	 * @param width
	 * @param winning
	 */
	public ServerMain(int port, int height, int width, int winning) {
		
		try {
		
			// create socket
			socket = new ServerSocket(port);
			
			// iterate forever
			while (true) {
				
				// create two sockets
				Socket sockets[] = new Socket[2];
			
				// write waiting for first client
				System.out.println("[s] First client expected!");
			
				// create a first socket
				sockets[0] = socket.accept();
				
				// write waiting for second client
				System.out.println("[s] Second client expected!");
			
				// create a second socket
				sockets[1] = socket.accept();
				
				// write waiting for second client
				System.out.println("[s] Both clients connected!");

				// create a thread
				ServerThread thread = new ServerThread(sockets, width, height, winning);
				
				// start thread
				thread.start();
			
				// write waiting for second client
				System.out.println("[s] Thread started!");

			}
		
		} catch(IOException exception) {
			
			// print error happened
			System.err.println("I/O Error at socket use: " + exception);
			
		} finally {
		
			// does socket exist?
			if (socket != null) {
				
				try {
			
				// close socket
				socket.close();
				
				} catch(IOException exception) {
					
					// print error happened
					System.err.println("I/O Error at socket close: " + exception);
					
				}
			
			}
		
		}
	
	}
	
}
