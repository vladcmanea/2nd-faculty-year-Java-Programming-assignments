/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import save.*;

/**
 * OpenSavePanel class
 * @author vladm
 */
public class OpenSavePanel extends JPanel {

    // drawing panel
    private CanvasPanel panel;

    /**
     * constructor method
     * @param panel canvas panel which uses it
     */
    public OpenSavePanel(CanvasPanel panel) {

        // set panel
        this.panel = panel;

        // set border with title colors
        setBorder(BorderFactory.createTitledBorder("Options"));

        // initialize options
        initializeOptions();

    }

    /**
     * initialize options method
     */
    private void initializeOptions() {

        // button for clearing the thing
        JButton open = new JButton("Open");

        // set tooltip
        open.setToolTipText("Click to Open File");

        // add action listener for clear
        open.addActionListener(new ActionListener() {

            /**
             * action performed event
             * @param event event happened
             */
            public void actionPerformed(ActionEvent event) {

                try {

                    //In response to a button click:
                    int status = Chooser.getOpen(new JComponent() {
                    });

                    // is approved?
                    if (status == JFileChooser.APPROVE_OPTION) {

                        // set image
                        panel.image = ImageIO.read(Chooser.getFile());

                        // set label
                        panel.setLabel("File " + Chooser.getFile().getAbsolutePath() + " is now open.");

                        // repaint everything
                        panel.repaint();

                        // set preferred size
                        panel.setPreferredSize(new Dimension(panel.image.getWidth(), panel.image.getHeight()));

                        // revalidate panel
                        panel.revalidate();

                    }

                } catch (IOException ex) {
                }

            }
        });

        // panel for stroke size
        JPanel openPanel = new JPanel();

        // add clear to clear panel
        openPanel.add(open);

        // set border with title colors
        openPanel.setBorder(BorderFactory.createTitledBorder("Open"));

        // add to current
        add(openPanel);

        // button for clearing the thing
        JButton clear = new JButton("Clear");

        // add tooltip
        clear.setToolTipText("Click to Clear Image");

        // add action listener for clear
        clear.addActionListener(new ActionListener() {

            /**
             * action performed event
             * @param event event happened
             */
            public void actionPerformed(ActionEvent event) {

                // set label
                panel.setLabel("Image is now clear.");

                // create graphics 2D
                Graphics2D graph = (Graphics2D) panel.image.getGraphics();

                // set color
                graph.setColor(Color.white);

                // draw image
                graph.fillRect(0, 0, panel.image.getWidth(), panel.image.getHeight());

                // dispose graphics
                graph.dispose();

                // repaint everything
                panel.repaint();

            }
        });

        // panel for stroke size
        JPanel clearPanel = new JPanel();

        // add clear to clear panel
        clearPanel.add(clear);

        // set border with title colors
        clearPanel.setBorder(BorderFactory.createTitledBorder("Clear"));

        // add to current
        add(clearPanel);

        // methods get
        Map<String, String> types = SaveFactory.list();

        // panel for stroke size
        JPanel savePane = new JPanel();

        // set border with title colors
        savePane.setBorder(BorderFactory.createTitledBorder("Save"));

        // create iterator
        Iterator it = types.entrySet().iterator();

        // iterate
        while (it.hasNext()) {

            // create current entry
            final Map.Entry pair = (Map.Entry) it.next();

            // button for clearing the thing
            JButton save = new JButton((String) pair.getValue());

            // set tooltip
            save.setToolTipText("Click to " + pair.getValue());

            // add action listener for clear
            save.addActionListener(new ActionListener() {

                /**
                 * action performed event
                 * @param event event happened
                 */
                public void actionPerformed(ActionEvent event) {

                    // save variable
                    Save saving = SaveFactory.get((String) pair.getKey());

                    if (saving != null) {

                        //In response to a button click:
                        int status = Chooser.getSave(new JComponent() {
                        });

                        // is approved?
                        if (status == JFileChooser.APPROVE_OPTION) {

                            // save file
                            saving.save(panel.image, Chooser.getFile());

                            // set label
                            panel.setLabel("File " + Chooser.getFile().getAbsolutePath() + " is saved.");

                        }

                    }

                }
            });

            // add to panel
            savePane.add(save);

        }

        // add to this
        add(savePane);

    }
}
