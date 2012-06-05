package gui;

import gui.ShowListener;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import poly.*;

/**
 * Show class
 * @author Vlad Manea
 * @author Vlad Tudose
 */
public class Show extends JPanel {

	// serial version UID
	private static final long serialVersionUID = 3535761112739557606L;
	// state of show
	public String state = "";
	// current poly
	public Poly poly = null;
	// listener
	private ShowListener listener;
	// application
	public Application application;
	
    /**
     * constructor method
     * @param application
     */
    public Show(Application application) {
   
    	// set visible
    	this.setVisible(true);
    	
    	// set application
    	this.application = application;
    	
        // set preferred size
        this.setPreferredSize(new Dimension(800, 600));
        
        // create listener
        listener = new ShowListener(this);
        
        // add mouse listener
        this.addMouseListener(listener);
 
    }

    /**
     * paint component method
     * @param graphics graphics context used
     */
    protected void paintComponent(Graphics graphics) {

        // paint component in JPanel superclass
        super.paintComponent(graphics);

        // create graphics 2D
        Graphics2D graph = (Graphics2D) graphics;

        // set anti alias
        graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // clear rectangle
        graph.clearRect(0, 0, 800, 600);
        
        // set color
        graph.setColor(new Color(0.5f, 0.5f, 0.5f));

        // set stroke
        graph.setStroke(new BasicStroke(0.1f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        // set font
        graph.setFont(new Font("Arial", Font.PLAIN, 5));
        
        // iterate horizontal positions
        for (int i = 10; i < 600; i += 10) {
        
        	// draw line
        	graph.drawLine(0, i, 800, i);
        	
        	// draw string
        	graph.drawString(new Integer(i-300).toString(), 400, i);
        
        }        
        
        // iterate vertical positions
        for (int i = 10; i < 800; i += 10) {
        
        	// draw line
        	graph.drawLine(i, 0, i, 600);
        
        }      
        
        // set color
        graph.setColor(new Color(0.5f, 1.0f, 0.5f));

        // set stroke
        graph.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        // draw horizontal line
        graph.drawLine(0, 300, 800, 300);
        
        // draw vertical line
        graph.drawLine(400, 0, 400, 600);
        
        // current poly exists?
        if (poly != null) {
        	
        	// draw with poly
        	poly.design(graphics);
        
        }
        
        // dispose graphics
        graph.dispose();

    }

}
