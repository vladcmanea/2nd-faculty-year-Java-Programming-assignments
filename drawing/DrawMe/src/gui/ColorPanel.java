/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * ColorPanel class
 * @author vladm
 */
public class ColorPanel extends JPanel {

    // panel
    private CanvasPanel panel;
    // colors array
    private final Color[] colors = new Color[216];

    /**
     * constructor method
     * @param panel canvas panel which uses it
     */
    public ColorPanel(CanvasPanel panel) {

        // set frame
        this.panel = panel;

        // set border with title colors
        setBorder(BorderFactory.createTitledBorder("Colors"));

        // set layout
        setLayout(new GridLayout(0, 36));

        // initialize colors
        initializeColors();

        // initialize options
        initializeOptions();

    }

    /**
     * initialize colors method
     */
    private void initializeColors() {

        // iterator
        int m = 0;

        // red component
        for (int i = 0; i < 6; ++i) {

            // green component
            for (int j = 0; j < 6; ++j) {

                // blue component
                for (int k = 0; k < 6; ++k) {

                    // create color
                    Color color = new Color(i * 51, j * 51, k * 51, 255);

                    // insert into colors
                    colors[m++] = color;

                }

            }

        }
    }

    /**
     * initialize options method
     */
    private void initializeOptions() {

        // set opaque
        setOpaque(false);

        // iterate colors :D
        for (int j = 0; j < colors.length; ++j) {

            // final k
            final int k = j;

            // create button
            JButton button = new JButton("");

            // set background as current background
            button.setBackground(colors[j]);

            // set tooltip
            button.setToolTipText("Click to select this color "
                    + "(red = " + colors[j].getRed() + ","
                    + " green = " + colors[j].getGreen() + ","
                    + " blue = " + colors[j].getBlue() + ").");

            // add action listener
            button.addActionListener(new ActionListener() {

                /**
                 * action performed method
                 * @param event event happened
                 */
                public void actionPerformed(ActionEvent event) {

                    // get button pressed
                    JButton button = (JButton) event.getSource();

                    // set color
                    panel.setStrokeColor(button.getBackground());

                    // set label
                    panel.setLabel("Selected color is now "
                            + "(red = " + colors[k].getRed() + ","
                            + " green = " + colors[k].getGreen() + ","
                            + " blue = " + colors[k].getBlue() + ").");

                }
            });

            // hide border for button
            button.setBorderPainted(false);

            // add button to panel
            add(button);

        }

    }
}
