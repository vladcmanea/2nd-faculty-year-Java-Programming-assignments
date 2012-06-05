/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tool;

import gui.CanvasPanel;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * ToolDrawFree class
 * @author vladm
 */
public class ToolDrawFree extends Tool {

    // last point
    private Point last = null;
    // current point
    private Point current = null;

    /**
     * constructor method
     * @param panel canvas panel to be used
     */
    public ToolDrawFree(CanvasPanel panel) {

        // ask super
        super(panel);

        // set label
        panel.setLabel("Click & Drag.");

    }

    /**
     * action pressed method
     * @param event mouse event
     */
    @Override
    public void mousePressed(MouseEvent event) {

        // set temp to null
        panel.temp = null;

        // set label
        panel.setLabel("Now Drag and release when Done.");

        // is left button pressed?
        if (event.getButton() == MouseEvent.BUTTON1) {

            // set last
            last = new Point(event.getX(), event.getY());

        }

    }

    /**
     * action dragged method
     * @param event mouse event
     */
    @Override
    public void mouseDragged(MouseEvent event) {

        // set temp to null
        panel.temp = null;

        // set label
        panel.setLabel("Keep on dragging.");

        // set current
        current = new Point(event.getX(), event.getY());

        // design in temp
        design(panel.image, false);

    }

    /**
     * action released method
     * @param event mouse event
     */
    @Override
    public void mouseReleased(MouseEvent event) {

        // set label
        panel.setLabel("Yay! Click & Drag for more.");

        // set temp to null
        panel.temp = null;

    }

    /**
     * design method
     * @param image image where designed
     * @param clean specifies if all image should be cleaned off
     */
    public void design(BufferedImage image, boolean clean) {

        // get graph from image
        Graphics2D graph = image.createGraphics();

        // is clean true?
        if (clean == false) {

            // create anti aliasing for graphics
            graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        }

        // set color
        graph.setColor(panel.getStrokeColor());

        // set stroke
        graph.setStroke(panel.getStroke());

        // draw line in graph
        graph.drawLine(last.x, last.y, current.x, current.y);

        // dispose graph
        graph.dispose();

        // switch clean
        if (clean == false) {

            // minx
            int minx = last.x < current.x ? last.x : current.x;

            // minx
            int maxx = last.x > current.x ? last.x : current.x;

            // minx
            int miny = last.y < current.y ? last.y : current.y;

            // minx
            int maxy = last.y > current.y ? last.y : current.y;

            // size
            int size = panel.getStrokeSize();

            // repaint
            panel.repaint(minx - size, miny - size, maxx - minx + size * 2, maxy - miny + size * 2);

        } else {

            // repaint
            panel.repaint();

        }

        // set last as current
        last = current;

    }
}
