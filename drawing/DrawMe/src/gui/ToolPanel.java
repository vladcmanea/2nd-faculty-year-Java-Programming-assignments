/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import tool.*;

/**
 * ToolPanel class
 * @author vladm
 */
public class ToolPanel extends JPanel {

    // drawing canvas
    private CanvasPanel panel;

    /**
     * constructor method
     * @param panel canvas panel which uses it
     */
    public ToolPanel(CanvasPanel panel) {

        // set panel
        this.panel = panel;

        // set border with title colors
        setBorder(BorderFactory.createTitledBorder("Tools"));

        // set layout
        setLayout(new FlowLayout(FlowLayout.RIGHT));

        // set size
        setPreferredSize(new Dimension(150, 600));

        // property
        applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        // initialize options
        initializeOptions();

    }

    /**
     * initialize options method
     */
    private void initializeOptions() {

        // methods get
        Map<String, String> types = ToolFactory.list();

        // iterator
        Iterator it = types.entrySet().iterator();

        // iterate
        while (it.hasNext()) {

            // create current entry
            final Map.Entry pair = (Map.Entry) it.next();

            // panel for tool
            JPanel toolPane = new JPanel();

            // button for clearing the thing
            JButton tool = new JButton((String) pair.getValue());

            // set tooltip
            tool.setToolTipText("Click to " + pair.getValue());

            // add action listener for clear
            tool.addActionListener(new ActionListener() {

                /**
                 * action performed method
                 * @param event event happened
                 */
                public void actionPerformed(ActionEvent event) {

                    // get button pressed
                    JButton button = (JButton) event.getSource();

                    // set tool for canvas
                    panel.current = ToolFactory.get((String) pair.getKey(), panel);

                }
            });

            // add to panel
            toolPane.add(tool);

            // add to this
            add(toolPane);

        }

    }
}
