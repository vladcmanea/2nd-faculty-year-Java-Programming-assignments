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
 * ToolDrawPolygon class
 * @author vladm
 */
public class ToolDrawPolygon extends Tool {

    // current point
    private Point current = null;
    // X axis points
    private int pointX[] = new int[1024];
    // Y axis points
    private int pointY[] = new int[1024];
    // N points
    private int pointN = 0;

    /**
     * constructor method
     * @param panel canvas panel to be used
     */
    public ToolDrawPolygon(CanvasPanel panel) {

        // ask super
        super(panel);

        // set label
        panel.setLabel("Click where you want the first point.");

    }

    /**
     * action moved method
     * @param event mouse event
     */
    @Override
    public void mouseMoved(MouseEvent event) {

        // already pressed?
        if (pointN > 0) {

            // set label
            panel.setLabel("Keep on moving and Left Click for a new point of Right click for an end point.");

            // set panel temp
            panel.temp = new BufferedImage(panel.image.getWidth(), panel.image.getHeight(), BufferedImage.TYPE_INT_ARGB);

            // set current
            current = new Point(event.getX(), event.getY());

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

        // set current
        current = new Point(event.getX(), event.getY());

        // have space
        if (pointN < 1024) {

            // set label
            panel.setLabel("New point created. Now Move.");

            // add X coordinate
            pointX[pointN] = new Integer(event.getX());

            // add y coordinate
            pointY[pointN] = new Integer(event.getY());

            // increment number of points
            pointN++;

        }

        if (event.getButton() == MouseEvent.BUTTON3) {

            // set label
            panel.setLabel("Yay! Click where you want the first point for more.");

            // end story
            design(panel.image, false);

            // create null temp
            panel.temp = null;

            // set no length
            pointN = 0;

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

        // is clean true?
        if (clean == false) {

            // create anti aliasing for graphics
            graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // there are points
            if (pointN > 0) {

                // get minimum X
                int minx = pointX[0];

                // get minimum Y
                int miny = pointY[0];

                // get maximum X
                int maxx = pointX[0];

                // get maximum Y
                int maxy = pointY[0];

                // iterate all points
                for (int i = 0; i < pointN; ++i) {

                    // get minimum X
                    minx = (pointX[i] < minx ? pointX[i] : minx);

                    // get minimum Y
                    miny = (pointY[i] < miny ? pointY[i] : miny);

                    // get maximum X
                    maxx = (pointX[i] > maxx ? pointX[i] : maxx);

                    // get maximum Y
                    maxy = (pointY[i] > maxy ? pointY[i] : maxy);

                }

                // draw poly
                graph.drawPolygon(pointX, pointY, pointN);

                // size
                int size = panel.getStrokeSize();

                // repaint
                panel.repaint(minx - size, miny - size, maxx - minx + size * 2, maxy - miny + size * 2);

            }

        } else {

            // do points exist?
            if (pointN > 0) {

                // draw polygon
                graph.drawPolyline(pointX, pointY, pointN);

                // draw current line
                graph.drawLine(pointX[pointN - 1], pointY[pointN - 1], current.x, current.y);

                // repaint all
                panel.repaint();

            }

        }

    }
}
