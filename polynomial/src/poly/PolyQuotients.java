package poly;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import gui.*;
import misc.*;

/**
 * PolyQuotients class
 * @author Vlad Manea
 * @author Vlad Tudose
 */
public class PolyQuotients extends Poly {

	// serial version UID
	private static final long serialVersionUID = 3535761112739557606L;
	// quotients
	float quotients[];
	// text field
	JTextField text;
	
	/**
	 * PolyQuotients method
	 * @param show
	 */
	public PolyQuotients(Show show) {
		super(show);
		
		// show copy
		final Show showing = show;
		
		// poly copy
		final PolyQuotients quot = this;

		// create label
		JLabel label = new JLabel("<html>Use <i>\"digit(s).3digits\"</i> format,<br />start from quotient for <b>a<sub>n</sub></b></html>");
		
		// set label visible
		label.setVisible(true);
		
		// add to this
		add(label);
		
        // set border with title colors
        setBorder(BorderFactory.createTitledBorder("Quotients"));
		
		// create text field
        text = new JTextField(20);
        
        // set visible
        text.setVisible(true);
        
        // add to this
        add(text);
        
        // create clear button
		JButton draw = new JButton("Draw");
		
		// add action listener
		draw.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				// get scanner
				process();
				
				// set show poly
				showing.poly = quot;
				
				// repaint showing
				showing.repaint();
				
			}
			
		});
		
		// set visible
		draw.setVisible(true);
		
		// add button to poly
		add(draw);
		
	}
	
	/**
	 * process method
	 */
	private void process() {
		
		// create input
		String input = new String(text.getText());
		
		// create scanner
		Scanner s = new Scanner(input);
		
		// initialize count
		int count = 0;
		
		// while ok
		while (s.hasNextFloat()) {
				
			// read next float
			s.nextFloat();
				
			// add count
			count++;
				
		}
		
		// create quotients
		quotients = new float[count];
		
		// create scanner
		s = new Scanner(input);
		
		// initialize count
		count--;
		
		while (s.hasNextFloat()) {
			
			// read next float
			quotients[count] = s.nextFloat();
			
			// add count
			count--;
			
		}
		
	}
	
	/**
	 * design method
	 * @param g
	 */
	@Override
	public void design(Graphics g) {
		
		// points exist?
		if (quotients != null && quotients.length > 0) { 
		
			// create polynomial
			Polynomial pol = new Polynomial(quotients);
			
		    // create graphics 2D
	        Graphics2D graph = (Graphics2D) g;
	        
	        // pointsX array
	        int pointsX[] = new int[801];
	        
	        // pointsY array
	        int pointsY[] = new int[801];
	        
	        // create array of points
	        for (int i = -400; i <= 400; i++) {
	        	
	        	// set X
	        	pointsX[i+400] = i + 400;
	        	
	        	// set Y
	        	pointsY[i+400] = 300-(int)pol.get((float)i);
	        	
	        }
	        
	        // set color green
	        graph.setColor(new Color(0.0f, 0.0f, 0.0f));
	        
	        // design polyline
	        graph.drawPolyline(pointsX, pointsY, pointsX.length);
	        
			// dispose graph
			graph.dispose();
			
			// set info
			show.application.showStatus("Polynomial has been drawn.");
			
		} else {
			
			// set info
			show.application.showStatus("Polynomial has not been drawn.");
			
		}
		
	}	

}
