package poly;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import gui.*;

/**
 * PolyClear class
 * @author Vlad Manea
 * @author Vlad Tudose
 */
public class PolyClear extends Poly {
	
	// serial version UID
	private static final long serialVersionUID = 3535761112739557606L;

	/**
	 * PolyClear method
	 * @param show
	 */
	public PolyClear(Show show) {
		super(show);

		// show copy
		final Show showing = show; 

        // set border with title colors
        setBorder(BorderFactory.createTitledBorder("Clear"));
		
		// create clear button
		JButton clear = new JButton("Clear");
		
		// add action listener
		clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				// remove show poly
				showing.poly = null;
				
				// repaint showing
				showing.repaint();
				
				// set info
				showing.application.showStatus("Graph has been cleared.");
				
			}
			
		});
		
		// set visible
		clear.setVisible(true);
		
		// add button to poly
		this.add(clear);
		
	}

	/**
	 * design method
	 * @param g
	 */
	@Override
	public void design(Graphics g) {
		// TODO Auto-generated method stub

	}

}
