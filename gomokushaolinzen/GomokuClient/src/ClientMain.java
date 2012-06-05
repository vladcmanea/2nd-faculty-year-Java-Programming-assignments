import java.net.*;
import java.util.Scanner;
import java.io.*;

/**
 * ClientMain class
 * @author Vlad Manea
 * @author Vlad Tudose
 */
public class ClientMain {

	// id, height & width
	private int id, height, width;
	// board
	private int board[][];
	
	/**
	 * ClientMain method
	 * @param args
	 * @throws IOException
	 */
	public ClientMain(String address, int port) throws IOException {

		// declare socket
		Socket socket = null;

		// declare input stream
		DataInputStream in = null;
		
		// declare output stream
		DataOutputStream out = null;
		
		// console scanner
		Scanner console = new Scanner(System.in);

		try {

			// create socket on address and port
			socket = new Socket(address, port);

			// create input
			in = new DataInputStream(socket.getInputStream());
			
			// create output
			out = new DataOutputStream(socket.getOutputStream());

			// send a -1
			out.writeInt(-1);
			
			// send a -1
			out.writeInt(-1);
			
			// flush output
			out.flush();
			
			// write status to standard output
			System.out.println("[?] Sent -1 -1 to s!");
			
			// parse lines int
			height = in.readInt();
			
			// parse columns int
			width = in.readInt();
			
			// parse identity int
			id = in.readInt();

			// write status to standard output
			System.out.println("[?] Received " 
					+ height + " " + width + " " + id + " from s!");
			
			// player
			int player = 0; 
			
			// create board
			board = new int[height][width];
			
			// iterate i's
			for (int i = 0; i < height; ++i) {
				
				// iterate j's
				for (int j = 0; j < width; ++j) {
					
					// initialize board
					board[i][j] = -1;
					
				}
			}
			
			// declare inner variables
			int first, second, third;
			
			do {
				
				// do I have to move?
				if (player == id) {
					
					// get line from console
					int line = console.nextInt();
					
					// get column from console
					int column = console.nextInt();
					
					// write line
					out.writeInt(line);
					
					// write column
					out.writeInt(column);
					
					// flush output
					out.flush();
					
					// write status to standard output
					System.out.println("[" + id + "] Sent " 
							+ line + " " + column + " to s!");
					
				}
					
				// parse first int
				first = in.readInt();
				
				// parse second int
				second = in.readInt();
				
				// parse third int
				third = in.readInt();

				// write status to standard output
				System.out.println("[" + id + "] Received " 
						+ first + " " + second + " " + third + " " + " from s!");

				// switch situation
				switch(first) {
				
				// win
				case 1:
				
					// update move
					board[second][third] = player;			
					
					// I am the proud winner?
					if (player == id) {
					
						// write winner
						System.out.println("You won!");
						
					} else {
						
						// write loser
						System.out.println("You did not win!");
						
					}	
					
					// break
					break;
					
				// draw
				case 2:
					
					// update move
					board[second][third] = player;			
					
					// write draw
					System.out.println("Draw game!");						
				
					// break
					break;
				
				// lost
				case 3:
					
					// I am the proud winner?
					if (second != id) {
						
						// write winner
						System.out.println("You won!");
							
					} else {
							
						// write loser
						System.out.println("You did not win!");

					}
					
					// break
					break;
					
				// normal move
				case 4:
					
					// update move
					board[second][third] = player;			
					
					// break
					break;
				
				}
				
				// change player
				player ^= 1;
				
			} while (first == 4);

		} catch(UnknownHostException exception) {

			// write error
			System.err.println("Server not found: " + exception);
			
			// exit with error
			System.exit(1);

		} finally {

			// out exists?
			if (out != null) {

				// close out
				out.close();
			
			}

			// in exists?
			if (in != null) {
				
				// close in
				in.close();
			
			}

			// socket exists?
			if (socket != null) {
				
				// close socket
				socket.close();
				
			}
			
		}
		
	}
	
}
