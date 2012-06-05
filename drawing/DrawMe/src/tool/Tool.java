/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tool;

import gui.CanvasPanel;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;

/**
 * Tool class
 * @author vladm
 */
public abstract class Tool extends MouseAdapter {

    // panel
    CanvasPanel panel = null;

    /**
     * design method
     * @param image image where designed
     * @param clean specifies if all image should be cleaned off
     */
    public abstract void design(BufferedImage image, boolean clean);

    /**
     * panel method
     * @param panel canvas panel that uses it
     */
    public Tool(CanvasPanel panel) {

        // set panel
        this.panel = panel;

    }
}
