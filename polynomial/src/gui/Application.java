package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.JPanel;

import poly.*;

/**
 * Quadrant class
 * @author Vlad
 */
public class Application extends JApplet implements Runnable {
	
	// version id
	private static final long serialVersionUID = 0L;
	// thread
	Thread thread = null;
	// running
	boolean running = false;
	
	/**
	 * init method
	 */
	public void init() {
		
		// create frame
		// JFrame frame = new JFrame();
		
		// set layout
		this.setLayout(new BorderLayout());
		
		// set frame size
		this.setSize(new Dimension(800, 600));
		
		// create show
		Show show = new Show(this);
		
		// set show visible
		show.setVisible(true);
		
		// add show to frame
		this.add(show, BorderLayout.CENTER);
		
		// panel
		JPanel actions = new JPanel();
		
		// set visible
		actions.setVisible(true);
		
		// create quotients poly
		PolyQuotients polyQuotients = new PolyQuotients(show);
		
		// add quotients poly
		actions.add(polyQuotients);
		
		// create lagrange poly
		PolyLagrange polyLagrange = new PolyLagrange(show);
		
		// add lagrange poly
		actions.add(polyLagrange);
		
		// create clear poly
		PolyClear polyClear = new PolyClear(show);
		
		// add clear poly
		actions.add(polyClear);
		
		// add panel
		this.add(actions, BorderLayout.NORTH);
		
		// create info
		showStatus("Use one of the options above.");

		// set frame visible
		this.setVisible(true);
	
	}
	
	/**
	 * start method
	 */
	public void start() {
		
		// thread does not exist?
		if (thread == null) {
			
			// create thread
			thread = new Thread(this);
			
			// set running to true
			running = true;
			
			// start thread
			thread.start();
			
		}	
		
	}

	/**
	 * stop method
	 */
	public void stop() {
		
		// set running to false
		running = false;

		// kill thread
		thread = null;
	
	}	

	/**
	 * run method
	 */
	public void run() {

	}

}
