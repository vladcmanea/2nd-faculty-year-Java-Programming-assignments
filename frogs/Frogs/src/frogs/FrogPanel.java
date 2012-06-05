/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frogs;

/**
 * FrogPanel class
 * @author vladm
 */
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

class FrogPanel extends Canvas implements Runnable {

    // image
    private static BufferedImage image = null;
    // active
    private boolean active = true;
    // window
    private FrogWindow window = null;
    // color
    private Color color;
    // name
    private String name = null;
    // current position
    private double position = 0;
    // old position
    private double oldpos = 0;
    // current speed
    private double speed;
    // current fps
    private long fps;

    /**
     * constructor method
     * @param speed initial speed of frog
     * @param name name of frog
     * @param photo photo of frog
     * @param color background color
     * @param window window where is panel
     * @param fps milliseconds to wait
     */
    public FrogPanel(double speed, String name, String photo,
            Color color, FrogWindow window, long fps) {

        // set color
        this.color = color;

        // set fps
        this.fps = fps;

        // set window
        this.window = window;

        // set name
        this.name = name;

        // set speed
        this.speed = speed;

        // load image
        loadImage(photo);

        // set background color
        this.setBackground(this.color);

        // set visible
        this.setVisible(true);

    }

    /**
     * loadImage method
     * @param address address of image
     */
    private static void loadImage(String address) {

        try {

            // read image
            image = ImageIO.read(new File(address));

        } catch (Exception e) {

            // print friendly message
            System.err.println("Could not find image " + address + ".");

            // terminate application
            System.exit(0);

        }

    }

    /**
     * paint method
     * @param context graphics context
     */
    @Override
    public void paint(Graphics context) {

        if (position - oldpos > 1) {

            // set color
            context.setColor(color);

            // draw a rectangle for background color
            context.fillRect(0, 0, this.getWidth(), this.getHeight());

            // set color
            context.setColor(color.brighter());

            // draw a rectangle for background color
            context.drawRect(0, 0, this.getWidth(), this.getHeight());

            // get graphics
            Graphics2D graphics = (Graphics2D) context;

            // draw image
            graphics.drawImage(image, (int) position - image.getWidth(), 0, this);

            // dispose graphics
            graphics.dispose();

        }

    }

    /**
     * recalculation method
     */
    public void reCalc() {

        // test active
        if (active) {

            // get probability
            double prob = Math.random();

            // probability allows change
            if (prob <= 0.2) {

                // get change amount
                double amount = Math.random();

                // change speed
                speed += speed * (amount - 0.5) * (double) 2 / (double) 3;

            }

            // old position
            oldpos = position;

            // change position
            position += speed;

            // image exists
            if (image != null) {

                // test ended
                if (position >= this.getWidth()) {

                    // unset this active
                    active = false;

                    // finish activity for all
                    window.unsetActive(this);

                }

            }

        }

    }

    /**
     * run method
     */
    public void run() {

        while (active) {

            // repaint
            repaint();

            // recompute
            reCalc();

            try {

                // stay and wait
                Thread.sleep(fps);

            } catch (InterruptedException e) {

                // print friendly message
                System.err.println("Thread " + name + " could not sleep.");

                // terminate application
                System.exit(0);

            }

        }

    }

    /**
     * setWinner method
     * @param color color to be used
     */
    public synchronized void setWinner(Color color) {

        // unset active
        this.active = false;

        // set color
        this.color = color;

        // repaint
        repaint();

    }
}
