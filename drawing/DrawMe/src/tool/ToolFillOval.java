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
 * ToolFillOval class
 * @author vladm
 */
public class ToolFillOval extends Tool {

    // last point
    private Point last = null;
    // current point
    private Point current = null;

    /**
     * constructor method
     * @param panel canvas panel to be used
     */
    public ToolFillOval(CanvasPanel panel) {

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

        // set label
        panel.setLabel("Now Drag.");

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

        // set label
        panel.setLabel("Keep on Dragging if you don't like it.");

        // create new temp
        panel.temp = new BufferedImage(panel.image.getWidth(), panel.image.getHeight(), BufferedImage.TYPE_INT_ARGB);

        // set current
        current = new Point(event.getX(), event.getY());

        // design in temp
        design(panel.temp, true);

    }

    /**
     * action released method
     * @param event mouse event
     */
    @Override
    public void mouseReleased(MouseEvent event) {

        // set label
        panel.setLabel("Yay! Click & Drag for more.");

        // create null temp
        panel.temp = null;

        // is left button pressed?
        if (event.getButton() == MouseEvent.BUTTON1) {

            // set current
            current = new Point(event.getX(), event.getY());

        }

        // design in temp
        design(panel.image, false);

    }

    /**
     * design method
     * @param image image where designed
     * @param clean specifies if image should be cleaned off
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

        // minx
        int minx = last.x < current.x ? last.x : current.x;

        // minx
        int maxx = last.x > current.x ? last.x : current.x;

        // minx
        int miny = last.y < current.y ? last.y : current.y;

        // minx
        int maxy = last.y > current.y ? last.y : current.y;

        // draw oval
        graph.fillOval(minx, miny, maxx - minx, maxy - miny);

        // dispose graph
        graph.dispose();

        // switch clean
        if (clean == false) {

            // size
            int size = panel.getStrokeSize();

            // repaint
            panel.repaint(minx - size, miny - size, maxx - minx + size * 2, maxy - miny + size * 2);

        } else {

            // repaint
            panel.repaint();

        }

    }
}
