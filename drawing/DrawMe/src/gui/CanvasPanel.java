/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import tool.Tool;

/**
 * CanvasPanel class
 * @author vladm
 */
public class CanvasPanel extends JPanel {

    // color properties
    private Color color = Color.black;
    // set array
    private float[] strokeDash = {100f, 0f};
    // stroke width
    private int strokeSize = 5;
    // stroke cap
    private int strokeCap = BasicStroke.CAP_BUTT;
    // stroke join
    private int strokeJoin = BasicStroke.JOIN_BEVEL;
    // stroke space
    private float strokeOffset = 0f;
    // current tool
    Tool current = null;
    // buffered image
    public BufferedImage image = null;
    // buffered temp
    public BufferedImage temp = null;
    // frame
    private MainFrame frame;

    /**
     * constructor method
     * @param frame main frame
     */
    public CanvasPanel(MainFrame frame) {

        // set frame
        this.frame = frame;

        // create listener
        CanvasPanelListener listener = new CanvasPanelListener(this);

        // add mouse click & stuff listener to free hand
        addMouseListener(listener);

        // add mouse motion listener  to free hand
        addMouseMotionListener(listener);

        // create possibilities
        Object[] possibilities = new Integer[1970];

        // iterate to create
        for (int i = 0; i < 1970; ++i) {

            // iterate
            possibilities[i] = i + 31;
        }

        // set preferred size
        this.setPreferredSize(new Dimension(700, 500));

        // initialize image
        initializeImage();

    }

    /**
     * initialize image method
     */
    private void initializeImage() {

        // create buffered image of width, height
        image = new BufferedImage(700, 500, BufferedImage.TYPE_INT_RGB);

        Graphics2D graphics = image.createGraphics();

        // set background color
        graphics.setColor(Color.white);

        // fill rectangle of image
        graphics.fillRect(0, 0, image.getWidth(), image.getHeight());

        // dispose grapics
        graphics.dispose();

    }

    /**
     * paint component method
     * @param graphics graphics context used
     */
    @Override
    protected void paintComponent(Graphics graphics) {

        // paint component in JPanel superclass
        super.paintComponent(graphics);

        // create graphics 2D
        Graphics2D graph = (Graphics2D) graphics;

        // draw temp image
        if (image != null) {

            // draw image
            graph.drawImage(image, 0, 0, null);

        }

        // draw temp image
        if (temp != null) {

            // draw image
            graph.drawImage(temp, 0, 0, null);

        }

        // dispose graphics
        graph.dispose();

    }

    /**
     * Stroke size setter
     * @param size size of stroke
     */
    public void setStrokeSize(int size) {

        // set size
        strokeSize = (size <= 0 ? 1 : size);

    }

    /**
     * stroke size getter
     * @return stroke size
     */
    public int getStrokeSize() {

        // return
        return strokeSize;

    }


    /**
     * Stroke cap setter
     * @param cap cap of stroke
     */
    public void setStrokeCap(int cap) {

        // set cap
        strokeCap = cap;

    }

    /**
     * Stroke join setter
     * @param join join of stroke
     */
    public void setStrokeJoin(int join) {

        // set join
        strokeJoin = join;

    }

    /**
     * Stroke color setter
     * @param color color of stroke
     */
    public void setStrokeColor(Color color) {

        // set color
        this.color = color;

    }

    /**
     * Stroke dash0 setter
     * @param dash0 line pause length
     */
    public void setDash0(float dash0) {

        // set dash
        strokeDash[1] = (dash0 <= 0 ? 0.1f : dash0);

    }

    /**
     * Stroke dash1 setter
     * @param dash1 line length
     */
    public void setDash1(float dash1) {

        // set dash
        strokeDash[0] = (dash1 <= 0 ? 0.1f : dash1);

    }

    /**
     * Stroke offset setter
     * @param offset line dash offset
     */
    public void setOffset(float offset) {

        // set dash
        strokeOffset = (offset <= 0 ? 0.1f : offset);

    }

    /**
     * color getter method
     * @return color
     */
    public Color getStrokeColor() {

        // return color
        return new Color(color.getRGB());

    }

    /**
     * stroke getter method
     * @return stroke
     */
    public BasicStroke getStroke() {

        // return stroke
        return new BasicStroke(strokeSize, strokeCap, strokeJoin, 1f, strokeDash, strokeOffset);

    }

    /**
     * set label method
     * @param string string to be shown on label
     */
    public void setLabel(String string) {
        
        // set label for frame
        frame.setLabel(string);
        
    }

}
