/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * MainFrame class
 * @author vladm
 */
public class MainFrame extends JFrame {

    // color panel variable
    private ColorPanel colorPanel;
    // save panel variable
    private OpenSavePanel savePanel;
    // control panel variable
    private ControlPanel controlPanel;
    // tool panel variable
    private ToolPanel toolPanel;
    // switch variable
    private CanvasPanel canvasPanel;
    // label variable
    private JLabel labelPanel;

    /**
     * constructor method
     */
    public MainFrame() {

        // super
        super();

        // initialize objects
        initializeObjects();

        // initialize properties
        initializeProperties();

    }

    /**
     * initialize objects property
     */
    private void initializeObjects() {

        // create jlabel
        labelPanel = new JLabel(" ");

        // create canvas panel
        canvasPanel = new CanvasPanel(this);

        // create save panel
        savePanel = new OpenSavePanel(canvasPanel);

        // create control panel
        controlPanel = new ControlPanel(canvasPanel);

        // create tool panel
        toolPanel = new ToolPanel(canvasPanel);

        // create color panel
        colorPanel = new ColorPanel(canvasPanel);

    }

    /**
     * initialize properties property
     */
    private void initializeProperties() {

        // close operation for frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create scroll
        JScrollPane scroll = new JScrollPane(canvasPanel);

        // create center frame
        JPanel centerFrame = new JPanel(new BorderLayout());
        
        // add to center the free hand object
        centerFrame.add(scroll, BorderLayout.CENTER);

        // jpanel
        JPanel leftContainer = new JPanel(new GridLayout(0, 1));

        // add to left the work & manage
        centerFrame.add(toolPanel, BorderLayout.WEST);

        // add to right the edit & colorize
        centerFrame.add(controlPanel, BorderLayout.EAST);

        // add to right the edit & colorize
        centerFrame.add(colorPanel, BorderLayout.SOUTH);

        // add to right the edit & colorize
        centerFrame.add(savePanel, BorderLayout.NORTH);

        // add center frame
        getContentPane().add(centerFrame);

        // add panel
        JPanel labelContainer = new JPanel();

        // set border with title colors
        labelContainer.setBorder(BorderFactory.createTitledBorder("Tips & Tricks"));

        // add to label
        labelContainer.add(labelPanel);

        // add label
        getContentPane().add(labelContainer, BorderLayout.SOUTH);

        // set location on the screen
        setLocation(10, 10);

        // pack
        pack();

        // set visibility true for frame
        setVisible(true);

    }

    /**
     * set label method
     * @param string string to be shown on label
     */
    public void setLabel(String string) {

        // set text for label
        labelPanel.setText(string);

    }


}
