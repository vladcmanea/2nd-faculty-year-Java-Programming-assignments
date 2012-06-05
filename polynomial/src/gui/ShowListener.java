package gui;

import gui.Show;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * ShowListener class
 * @author Vlad Manea
 * @author Vlad Tudose
 */
public class ShowListener extends MouseAdapter {

	// show
	Show show;
	
	/**
	 * ShowListener method
	 * @param show
	 */
	public ShowListener(Show show) {
		
		// show copy
		this.show = show;
	
	}
	
	/**
	 * mouseClicked method
	 * @param e
	 */
	public void mouseClicked(MouseEvent e) {
	
		// show poly exists?
		if (show.poly != null) {
			
			// set mouse clicked
			show.poly.mouseClicked(e);
			
		}
	}
	
}
