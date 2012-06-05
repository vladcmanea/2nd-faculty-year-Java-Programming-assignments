/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frogs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.*;

/**
 * FrogWindow class
 * @author vladm
 */
class FrogWindow extends Frame {

    // frog panel array
    private FrogPanel frogs[];
    // count
    private int count;
    // won
    private boolean won = false;
    // color
    private Color color = null;

    /**
     * constructor method
     * @param name name of frog window
     * @param photo photo of frog
     * @param color background color
     * @param dimension dimension of window
     * @param count count of frogs
     * @param speed speed of frogs
     * @param fps milliseconds to wait
     */
    public FrogWindow(String name, String photo, Color color,
            Dimension dimension, int count, int speed, long fps) {

        // set title for Frame superclass
        super(name);

        // add window listener
        this.addWindowListener(new WindowAdapter() {

            /**
             * window closing event handler
             */
            @Override
            public void windowClosing(WindowEvent e) {

                // finish application
                System.exit(0);

            }
        });

        // set window layout to gridLayout
        setLayout(new GridLayout(0, 1));

        // set absolute count
        this.count = (count < 0 ? -count : count);

        // set speed
        speed = (speed < 0 ? -speed : speed);

        // create string
        frogs = new FrogPanel[this.count];

        // set color
        this.color = color;

        // iterate to 10 frogs
        for (int i = 0; i < this.count; ++i) {

            // create frog panel
            frogs[i] = new FrogPanel(speed,
                    new Integer(i).toString(), photo, color, this, fps);

            // add panel to layout
            add(frogs[i]);

        }

        // pack layout
        pack();

        // iterate to 10 threads
        for (int i = 0; i < this.count; ++i) {

            // create thread
            new Thread(frogs[i]).start();

        }

        // set visible
        this.setVisible(true);

        // set size
        this.setSize(dimension);

        // set background color
        this.setBackground(color);

    }

    /**
     * unsetActive method
     * @param panel panel sender
     */
    public synchronized void unsetActive(FrogPanel panel) {

        // set winner
        panel.setWinner(color);

        // set color
        color = color.darker();

    }
}
