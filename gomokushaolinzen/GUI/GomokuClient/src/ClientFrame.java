import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * ClientFrame class
 * @author Vlad Manea
 * @author Vlad Tudose
 */
public class ClientFrame extends JFrame implements Runnable {

	// serial version
	private static final long serialVersionUID = 2172243821155986209L;
	// id, port, height & width
	private JTextField addrt, portt;
	// play panel
	private JPanel play;
	// buttons
	private JButton plays[][];
	// board
	private final ClientMain client;
	// frame
	private final ClientFrame frame;
	// info
	private JLabel info;
	// thread
	private Thread thread = null;
	
	/**
	 * setInfo method
	 * @param info
	 */
	public void setInfo(String info) {
	
		// set info
		this.info.setText(info);
	
	}
	
	/**
	 * ClientMain method
	 * @param client
	 */
	public ClientFrame(ClientMain client) {
		
		// thread exists?
		if (thread == null) {
			
			// thread
			thread = new Thread(this);
			
		}
		
		// set client
		this.client = client;
		
		// frame
		this.frame = this;
		
		// set size
		setSize(new Dimension(500, 600));
		
		// set layout
		setLayout(new BorderLayout());
		
		// create connect jpanel
		JPanel connect = new JPanel();
		
		// label for address
		JLabel addrl = new JLabel("Address");
		
		// set visible
		connect.setVisible(true);
		
		// add label to connect
		connect.add(addrl);
		
		// create text field
		addrt = new JTextField(15);
		
		// set text
		addrt.setText("127.0.0.1");
				
		// add text field to connect
		connect.add(addrt);
		
		// label for port
		JLabel portl = new JLabel("Port");
		
		// add label to connect
		connect.add(portl);
		
		// create text field
		portt = new JTextField(5);
		
		// set text
		portt.setText("8123");
		
		// add text field to connect
		connect.add(portt);
		
		// create button
		JButton connb = new JButton("connect");
		
		// add action listener
		connb.addActionListener(new ActionListener() {

			/**
			 * actionPerformed method
			 * @param event
			 */
			@Override
			public void actionPerformed(ActionEvent event) {
				
				// set address
				frame.client.setAddress(frame.addrt.getText());
				
				// set port
				frame.client.setPort(Integer.parseInt(frame.portt.getText()));
				
				// enable
				JButton source = (JButton)event.getSource();
				
				// set disabled
				source.setEnabled(false);
				
				// set waiting for the other
				frame.setInfo("Waiting for opponent...");
				
				// interact
				frame.client.interact();
				
			}

		});
		
		// add button
		connect.add(connb);
		
        // set border with title
        connect.setBorder(BorderFactory.createTitledBorder(""));
        
        // add connect to this
        add(connect, BorderLayout.NORTH);
        
        // set info
        info = new JLabel("Connect...");
        
        // set border with title colors
        info.setBorder(BorderFactory.createTitledBorder(""));
        
        // add info
        add(info, BorderLayout.SOUTH);
        
        // set play
        play = new JPanel();
        
        // set border with title colors
        play.setBorder(BorderFactory.createTitledBorder(""));
        
        // add info
        add(play, BorderLayout.CENTER);
        
        // set visible
		setVisible(true);
		
		// set close operation
		setDefaultCloseOperation(EXIT_ON_CLOSE);		
		
	}
	
	/**
	 * initialize method
	 */
	public void initialize() {
		
		System.out.println("initializing...");
		
		// remove from play
		play.removeAll();
		
		// set layout
		play.setLayout(new GridLayout(client.getWidth(), 0));
		
		// create play
		plays = new JButton[client.getWidth()][client.getHeight()];
		
		// for each i
		for (int i = 0; i < client.getWidth(); ++i) {
			
			// for each j
			for (int j = 0; j < client.getHeight(); ++j) {
				
				// final i, j
				final int ii = i, jj = j;
				
				// create plays element
				plays[i][j] = new JButton(" ");
				
				// set text
				plays[i][j].setBackground(new Color(1.0f, 1.0f, 1.0f));
				
				// set element visible
				plays[i][j].setVisible(true);
				
				// add action listener
				plays[i][j].addActionListener(new ActionListener() {

					/**
					 * actionPerformed method
					 * @param event
					 */
					@Override
					public void actionPerformed(ActionEvent event) {
						
						// test existence
						if (client.getBoard(ii, jj) == -1) {
							
							// set waiting for the other
							frame.setInfo("Sending data...");
							
							// set line
							client.setLine(ii);
							
							// set column
							client.setColumn(jj);
							
						}
						
						// disable all
						frame.disableAll();
						
					}

				});
								
				
				// add element to play
				play.add(plays[i][j]);
				
			}
			
		}
		
		// set play visible
		play.setVisible(true);
		
		// repaint
		play.repaint();
		
		// repaint
		this.pack();
		
		// start thread
		thread.start();
		
	}
	
	/**
	 * disableAll method
	 */
	public void disableAll() {
		
		for (int i = 0; i < client.getHeight(); ++i) {
			
			for (int j = 0; j < client.getWidth(); ++j) {
				
				plays[i][j].setEnabled(false);
				
			}
			
		}
		
	}
	
	/**
	 * enableAll method
	 */
	public void enableAll() {
		
		for (int i = 0; i < client.getHeight(); ++i) {
			
			for (int j = 0; j < client.getWidth(); ++j) {
				
				plays[i][j].setEnabled(client.getBoard(i, j) == -1);
				
			}
			
		}
		
	}
	
	/**
	 * fill method
	 * @param line
	 * @param column
	 * @param current
	 */
	public void fill(int line, int column, boolean current) {
		
		// disable buttons
		plays[line][column].setEnabled(false);
		
		// is me?
		if (current) {
		
			// set text
			plays[line][column].setBackground(new Color(0.0f, 1.0f, 0.0f));
			
		} else {
			
			// set text
			plays[line][column].setBackground(new Color(1.0f, 0.0f, 0.0f));
			
		}
		
	}

	@Override
	public void run() {
		
		while (true) {
			
			// communicate with server
			client.communicate();
			
		}
		
	}

}
