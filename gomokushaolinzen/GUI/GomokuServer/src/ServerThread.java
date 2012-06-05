import java.net.*;
import java.io.*;

/**
 * ServerThread class
 * @author Vlad Manea
 * @author Vlad Tudose
 */
class ServerThread extends Thread {
	
	// sockets variable
	Socket sockets[] = null;
	// width
	int width;
	// height
	int height;
	// winning
	int winning;
	// declare board
	int board[][];
	
	/**
	 * ServerThread method
	 * @param sockets
	 * @param height
	 * @param width
	 * @param winning
	 */
	public ServerThread(Socket sockets[], int height, int width, int winning) {
		
		// create sockets
		this.sockets = new Socket[2];
		
		// create first socket
		this.sockets[0] = sockets[0];
		
		// create second socket
		this.sockets[1] = sockets[1];
		
		// set height
		this.height = height;
		
		// set width
		this.width = width;
		
		// set winning
		this.winning = winning;
		
	}

	/**
	 * run method
	 */
	public void run() {
	
		try {
	
			// input stream from client
			DataInputStream in[] = {
				new DataInputStream(sockets[0].getInputStream()),
				new DataInputStream(sockets[1].getInputStream())
			};
	
			// output stream to client
			DataOutputStream out[] = {
				new DataOutputStream(sockets[0].getOutputStream()),
				new DataOutputStream(sockets[1].getOutputStream())
			};
			
			// iterate players
			for (int i = 0; i <= 1; ++i) {
				
				// receive -1
				int one = in[i].readInt();
				
				// receive -1
				int two = in[i].readInt();
				
				// write status to standard output
				System.out.println("[s] Received " 
						+ one + " " + two + " from " + i + "!");
				
				// write height
				out[i].writeInt(height);
				
				// write width
				out[i].writeInt(width);
				
				// write id
				out[i].writeInt(i);
								
				// flush output
				out[i].flush();
				
				// write status to standard output
				System.out.println("[s] Sent " 
						+ height + " " + width + " " + i + " to " + i + "!");

			}
			
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
			
			// current player
			int player = 1;
			
			// current moves
			int moves = 0;
			
			// iterate
			do {
				
				// invert player
				player ^= 1;
				
				// parse line int
				int line = in[player].readInt();
				
				// parse column int
				int column = in[player].readInt();
				
				// write status to standard output
				System.out.println("[s] Received " 
						+ line + " " + column + " from " + player + "!");
				
				// test validity
				if (0 <= line && line < height && 0 <= column && column < width) {
					
					// test existence
					if (board[line][column] == -1) {
						
						// update board
						board[line][column] = player;
						
						// add to moves
						moves++;
						
						// test win
						if (win(line, column)) {
							
							// iterate players
							for (int i = 0; i <= 1; ++i) {
								
								// write win
								out[i].writeInt(1);
								
								// write line
								out[i].writeInt(line);
								
								// write column
								out[i].writeInt(column);
							
								// flush output
								out[i].flush();
							
								// write status to standard output
								System.out.println("[s] Sent -1 " 
										+ line + " " + column + " to " + i + "!");

							}
														
							// finish loop
							break;
							
						} else {
							
							// reached draw?
							if (moves >= width * height) {
								
								// iterate players
								for (int i = 0; i <= 1; ++i) {
									
									// write draw
									out[i].writeInt(2);
									
									// write line
									out[i].writeInt(line);
									
									// write column
									out[i].writeInt(column);
								
									// flush output
									out[i].flush();
								
									// write status to standard output
									System.out.println("[s] Sent 0 " 
											+ line + " " + column + " to " + i + "!");

								}	
								
								// finish loop
								break;
								
							} else {				
							
								// iterate players
								for (int i = 0; i <= 1; ++i) {
									
									// write move
									out[i].writeInt(4);

									// write line
									out[i].writeInt(line);
									
									// write column
									out[i].writeInt(column);
								
									// flush output
									out[i].flush();
								
									// write status to standard output
									System.out.println("[s] Sent 4 " 
											+ line + " " + column + " to " + i + "!");
	
								}	
								
							}
							
						}
						
					} else {
						
						// iterate players
						for (int i = 0; i <= 1; ++i) {
						
							// write lost
							out[i].writeInt(3);
							
							// write player
							out[i].writeInt(player);
							
							// write player
							out[i].writeInt(player ^ 1);

							// flush output
							out[i].flush();
						
							// write status to standard output
							System.out.println("[s] Sent 3 " 
									+ player + " " + (player ^ 1) + " to " + i + "!");
							
						}
						
						// finish loop
						break;
						
					}
				
				} else {
					
					// iterate players
					for (int i = 0; i <= 1; ++i) {
						
						// write lost
						out[i].writeInt(3);
						
						// write player
						out[i].writeInt(player);
						
						// write player
						out[i].writeInt(player ^ 1);

						// flush output
						out[i].flush();
					
						// write status to standard output
						System.out.println("[s] Sent 3 " 
								+ player + " " + (player ^ 1) + " to " + i + "!");

					}	

					// finish loop
					break;
					
				}
								
			} while(true);

		} catch(IOException exception) {
	
			// write error
			System.err.println("I/O Error at socket communication: " + exception);
	
		} finally {
	
			// iterate players
			for (int i = 0; i <= 1; i++) {

				try {
		
					// close sockets
					sockets[i].close();
						
				} catch(IOException exception) {
		
					// write error
					System.err.println("I/O Error at socket " 
							+ i + " close: " + exception);
		
				}

			}
		
		}
	
	}
	
	/**
	 * win method
	 * @param l
	 * @param c
	 * @return
	 */
	private boolean win(int l, int c) {
		
		// declare count
		int count;
		
		// horizontal count
		count = 1;
		
		// iterate to the left
		for (int j = c - 1; j >= 0 && board[l][c] == board[l][j]; j--) {
			
			// update count
			count++;
		
		}
		
		// iterate to the right
		for (int j = c + 1; j < width && board[l][c] == board[l][j]; j++) {
			
			// update count
			count++;
		
		}
		
		// test count
		if (count >= winning) {
			
			// we have a winner
			return true;
			
		}
		
		// vertical count
		count = 1;
		
		// iterate to the top
		for (int i = l - 1; i >= 0 && board[l][c] == board[i][c]; i--) {
			
			// update count
			count++;
		
		}
		
		// iterate to the bottom
		for (int i = l + 1; i < height && board[l][c] == board[i][c]; i++) {
			
			// update count
			count++;
		
		}
		
		// test count
		if (count >= winning) {
			
			// we have a winner
			return true;
			
		}		
		
		// first diagonal count
		count = 1;
		
		// iterate to the top
		for (int i = l - 1, j = c + 1; i >= 0 && j < width 
				&& board[l][c] == board[i][j]; i--, j++) {
			
			// update count
			count++;
		
		}
		
		// iterate to the bottom
		for (int i = l + 1, j = c - 1; i < height && j >= 0 
				&& board[l][c] == board[i][j]; i++, j--) {
			
			// update count
			count++;
		
		}
		
		// test count
		if (count >= winning) {
			
			// we have a winner
			return true;
			
		}		
		
		// second diagonal count
		count = 1;
		
		// iterate to the top
		for (int i = l - 1, j = c - 1; i >= 0 && j >= 0 
				&& board[l][c] == board[i][j]; i--, j--) {
			
			// update count
			count++;
		
		}
		
		// iterate to the bottom
		for (int i = l + 1, j = c + 1; i < height && j < width 
				&& board[l][c] == board[i][j]; i++, j++) {
			
			// update count
			count++;
		
		}
		
		// test count
		if (count >= winning) {
			
			// we have a winner
			return true;
			
		}		

		// nothing special
		return false;
		
	}	
	
}
