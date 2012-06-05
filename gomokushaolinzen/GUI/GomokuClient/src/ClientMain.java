import java.net.*;
import java.io.*;

import javax.swing.JOptionPane;

/**
 * ClientMain class
 * @author Vlad Manea
 * @author Vlad Tudose
 */
public class ClientMain {
	
	// id, port, height & width
	private int id, height, width, port;
	// address
	private String address;
	// board
	private int board[][];
	// frame
	private final ClientFrame frame;
	// player
	int player = 0;
	// socket
	Socket socket;
	// input stream
	DataInputStream in;
	// output stream
	DataOutputStream out;
	// line, column
	private int line = -1, column = -1;

	public synchronized int getLine() {
		return line;
	}

	public synchronized int getColumn() {
		return column;
	}
	
	public synchronized void setLine(int line) {
		this.line = line;
	}
	
	public synchronized void setColumn(int column) {
		this.column = column;
	}
	
	
	public synchronized int getId() {
		return id;
	}

	public synchronized int getHeight() {
		return height;
	}

	public synchronized int getWidth() {
		return width;
	}

	public synchronized void setPort(int port) {
		this.port = port;
	}

	public synchronized void setAddress(String address) {
		this.address = address;
	}

	public synchronized int getBoard(int i, int j) {
		return board[i][j];
	}

	/**
	 * ClientMain method
	 */
	public ClientMain() {
		
		// create frame
		frame = new ClientFrame(this);

	}
	
	/**
	 * interact method
	 */
	void interact() {
		
		// declare socket
		socket = null;

		// declare input stream
		in = null;
		
		// declare output stream
		out = null;
		
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
			player = 0; 
			
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
			
			// set waiting for the other
			frame.setInfo("Initializing board...");
			
			// initialize play
			frame.initialize();
			
		} catch(UnknownHostException exception) {

			// write error
			System.err.println("Server not found: " + exception);
			
			// finish
			finish();

		} catch(IOException exception) {

			// write error
			System.err.println("I/O Exception in client: " + exception);

			// finish
			finish();
		}
			
	}

	/**
	 * communicate method
	 * @param line
	 * @param column
	 */
	public void communicate() {
		
		// declare inner variables
		int first, second, third;
		
		try {
		
			System.out.println(player + " me " + id);
			
			// do I have to move?
			if (player == id) {
				
				// info
				frame.setInfo("Your move...");
				
				// enable all
				frame.enableAll();
				
				// ask for variable existence
				while (getLine() == -1 || getColumn() == -1) {
					
				}
				
				// write line
				out.writeInt(getLine());
				
				// write line
				out.writeInt(getColumn());
				
				// flush output
				out.flush();
				
				// set line
				setLine(-1);				
				
				// set column
				setColumn(-1);
				
				// write status to standard output
				System.out.println("[" + id + "] Sent " 
						+ line + " " + column + " to s!");
				
			} else {
				
				// enable all
				frame.disableAll();
				
				// info
				frame.setInfo("Wait for opponent...");
				
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
				
				// update
				frame.fill(second, third, player == id);
				
				// I am the proud winner?
				if (player == id) {
				
					JOptionPane.showMessageDialog(frame,
						"You Win!", "WIN", JOptionPane.INFORMATION_MESSAGE);
					
				} else {
					
					JOptionPane.showMessageDialog(frame,
							"You Lose!", "LOSE", JOptionPane.ERROR_MESSAGE);
					
				}	
				
				// break
				break;
				
			// draw
			case 2:
				
				// update move
				board[second][third] = player;		
				
				// update
				frame.fill(second, third, player == id);
				
				JOptionPane.showMessageDialog(frame,
						"Draw Game!", "DRAW", JOptionPane.INFORMATION_MESSAGE);
			
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
				
				// update
				frame.fill(second, third, player == id);
				
				// break
				break;
			
			}
			
			// change player
			player ^= 1;
			
			// must I finish?
			if (first != 4) {
				
				// finish
				finish();
				
			}
			
		} catch (IOException exception) {
			
			// write error
			System.err.println("I/O Exception in client: " + exception);
			
		}
		
	}

	
	/**
	 * finish method
	 */
	public void finish() {
		
		// out exists?
		if (out != null) {
	
			try {
				
				// close output
				out.close();
				
			} catch (IOException exception) {
	
				// write error
				System.err.println("I/O Exception in client: " + exception);
	
			}
			
		}

		// in exists?
		if (in != null) {
	
			try {
				
				// close input
				in.close();
				
			} catch (IOException exception) {
	
				// write error
				System.err.println("I/O Exception in client: " + exception);
	
			}
			
		}

		// socket exists?
		if (socket != null) {
	
			try {
				
				// close socket
				socket.close();
				
			} catch (IOException exception) {
	
				// write error
				System.err.println("I/O Exception in client: " + exception);
				
			}
		
		}
		
		// exit
		System.exit(0);
	
	}	
	
}
