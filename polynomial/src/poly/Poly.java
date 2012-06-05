package poly;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import gui.*;

/**
 * Tool class
 * @author vladm
 */
public abstract class Poly extends JPanel implements MouseListener {

	// serial version UID
	private static final long serialVersionUID = 3535761112739557606L;
    // panel
    Show show = null;

    /**
     * design method
     * @param image image where designed
     * @param clean specifies if all image should be cleaned off
     */
    public abstract void design(Graphics g);

    /**
     * panel method
     * @param panel canvas panel that uses it
     */
    public Poly(Show show) {

        // set panel
        this.show = show;

    }

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
