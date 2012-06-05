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
 * ToolDrawRoundRect
 * @author vladm
 */
public class ToolDrawRoundRect extends Tool {

    // last point
    private Point last = null;
    // current point
    private Point current = null;
    // created
    boolean created = false;
    // distance X
    int distanceX = 0;
    // distance Y
    int distanceY = 0;

    /**
     * constructor method
     * @param panel canvas panel to be used
     */
    public ToolDrawRoundRect(CanvasPanel panel) {

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

        // check created
        if (created == false) {

            // is left button pressed?
            if (event.getButton() == MouseEvent.BUTTON1) {

                // set last
                last = new Point(event.getX(), event.getY());

            }

        }

    }

    /**
     * action dragged method
     * @param event mouse event
     */
    @Override
    public void mouseDragged(MouseEvent event) {

        // is created?
        if (created == false) {

            // set label
            panel.setLabel("Keep on dragging if you don't like it.");

            // create new temp
            panel.temp = new BufferedImage(panel.image.getWidth(), panel.image.getHeight(), BufferedImage.TYPE_INT_ARGB);

            // set current
            current = new Point(event.getX(), event.getY());

            // design in temp
            design(panel.temp, true);

        }

    }

    /**
     * action moved method
     * @param event mouse event
     */
    @Override
    public void mouseMoved(MouseEvent event) {

        // is created?
        if (created == true) {

            // set label
            panel.setLabel("Keep on Moving if you don't like it.");

            // create new temp
            panel.temp = new BufferedImage(panel.image.getWidth(), panel.image.getHeight(), BufferedImage.TYPE_INT_ARGB);

            // set current distance X
            distanceX = (current.x > event.getX() ? current.x - event.getX() : event.getX() - current.x);

            // set current distance Y
            distanceY = (current.y > event.getY() ? current.y - event.getY() : event.getY() - current.y);

            // design in temp
            design(panel.temp, true);

        }

    }

    /**
     * action released method
     * @param event mouse event
     */
    @Override
    public void mouseReleased(MouseEvent event) {

        // created
        if (created == false) {

            // set label
            panel.setLabel("Now Move.");

            // is left button pressed?
            if (event.getButton() == MouseEvent.BUTTON1) {

                // set current
                current = new Point(event.getX(), event.getY());

                // set created
                created = true;

            }

        } else {

            // set label
            panel.setLabel("Yay! Click & Drag for more.");

            // draw to image
            design(panel.image, false);

            // delete panel temp
            panel.temp = null;

            // uncreate
            created = false;

        }

    }

    /**
     * design method
     * @param image image where designed
     * @param clean specifies if image should be cleaned off
     */
    public void design(BufferedImage image, boolean clean) {

        // get graph from image
        Graphics2D graph = image.createGraphics();

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

        // is clean true?
        if (clean == false) {

            // create anti aliasing for graphics
            graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // is created?
            if (created == false) {

                // draw rect
                graph.drawRect(minx, miny, maxx - minx, maxy - miny);

            } else {

                // draw oval
                graph.drawRoundRect(minx, miny, maxx - minx, maxy - miny, distanceX, distanceY);

            }

            // size
            int size = panel.getStrokeSize();

            // repaint
            panel.repaint(minx - size, miny - size, maxx - minx + size * 2, maxy - miny + size * 2);

        } else {

            // draw oval
            graph.drawRoundRect(minx, miny, maxx - minx, maxy - miny, distanceX, distanceY);

            // repaint
            panel.repaint();

        }

        // dispose graph
        graph.dispose();

    }
}
