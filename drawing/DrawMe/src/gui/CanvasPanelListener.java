/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;

/**
 * CanvasPanelListener class
 * @author vladm
 */
// class for drawing listening
public class CanvasPanelListener extends MouseInputAdapter {

    // observer object
    private CanvasPanel panel;

    /**
     * constructor method
     * @param panel panel listened
     */
    public CanvasPanelListener(CanvasPanel panel) {

        // set panel
        this.panel = panel;
    }

    /**
     * mouse pressed method
     * @param event event happened
     */
    @Override
    public void mousePressed(MouseEvent event) {

        // does tool exist?
        if (panel.current != null) {

            // create action
            panel.current.mousePressed(event);

        }

    }

    /**
     * mouse dragged method
     * @param event event happened
     */
    @Override
    public void mouseDragged(MouseEvent event) {

        // does tool exist?
        if (panel.current != null) {

            // create action
            panel.current.mouseDragged(event);

        }

    }

    /**
     * mouse moved method
     * @param event event happened
     */
    @Override
    public void mouseMoved(MouseEvent event) {

        // does tool exist?
        if (panel.current != null) {

            // create action
            panel.current.mouseMoved(event);

        }

    }

    /**
     * mouse released method
     * @param event event happened
     */
    @Override
    public void mouseReleased(MouseEvent event) {

        // does tool exist?
        if (panel.current != null) {

            // create action
            panel.current.mouseReleased(event);

        }

    }
}
