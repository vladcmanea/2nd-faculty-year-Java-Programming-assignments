package poly;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;

import gui.*;
import misc.*;

/**
 * PolyLagrange class
 * @author Vlad Manea
 * @author Vlad Tudose
 */
public class PolyLagrange extends Poly {

	// serial version UID
	private static final long serialVersionUID = 3535761112739557606L;

	// declare points
	Point points[] = null;
	
	/**
	 * PolyLagrange method
	 * @param show
	 */
	public PolyLagrange(Show show) {
		super(show);
		
		// show copy
		final Show showing = show;
		
		// poly copy
		final PolyLagrange quot = this;

        // set border with title colors
        setBorder(BorderFactory.createTitledBorder("Lagrange"));
		
		// create clear button
		JButton draw = new JButton("Start");
		
		// add action listener
		draw.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				// delete points
				points = null;
				
				// set show poly
				showing.poly = quot;
				
				// repaint showing
				showing.repaint();
				
				// set info
				showing.application.showStatus("Click on graph to add points.");
				
			}
			
		});
		
		// set visible
		draw.setVisible(true);
		
		// add button to poly
		add(draw);
		
		// create label
		JLabel label = new JLabel("<html>After start,<br />click on surface</html>");
		
		// set label visible
		label.setVisible(true);
		
		// add to this
		add(label);
		
	}

	/**
	 * design method
	 * @param g
	 */
	@Override
	public void design(Graphics g) {
		
		// points exist?
		if (points != null && points.length > 0) { 
		
			// create lagrange
			Lagrange lag = new Lagrange(points);
			
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
	        	pointsY[i+400] = 300-(int)lag.get((float)i);
	        	
	        }
	        
	        // set color green
	        graph.setColor(new Color(0.0f, 0.0f, 0.0f));
	        
	        // design polyline
	        graph.drawPolyline(pointsX, pointsY, pointsX.length);
	        
	        // set color red
	        graph.setColor(new Color(1.0f, 0.0f, 0.0f));
	        
	        // iterate points
	        for (int i = 0; i < points.length; ++i) {
	        	
	        	// draw a circle
	        	graph.drawOval((int)points[i].getX() + 395, -(int)points[i].getY() + 295, 10, 10);
	        
	        }
	
			// dispose graph
			graph.dispose();
			
		} 
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		// set info
		show.application.showStatus("New point has been added.");
		
		// is points null?
		if (points == null) {
			
			// create points
			points = new Point[1];
			
			// add point
			points[0] = new Point((float)(e.getX() - 400), (float)(-e.getY() + 300));
			
		} else {
		
			// auxiliary
			Point auxi[] = points;
		
			// save points
			points = new Point[auxi.length + 1];
			
			// iterate contents
			for (int i = 0; i < auxi.length; ++i) {
			
				// copy content
				points[i] = auxi[i];
	
			}
			
			// put new point
			points[auxi.length] = new Point((float)(e.getX() - 400), (float)(-e.getY() + 300));	
		
		}
		
		// repaint show
		show.repaint();
		
	}	

}
