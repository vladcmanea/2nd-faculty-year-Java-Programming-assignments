/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BasicStroke;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * ControlPanel class
 * @author vladm
 */
public class ControlPanel extends JPanel {

    // drawing panel
    private CanvasPanel panel;

    /**
     * constructor method
     * @param panel canvas panel which uses it
     */
    public ControlPanel(CanvasPanel panel) {

        // set panel
        this.panel = panel;

        // set border with title colors
        setBorder(BorderFactory.createTitledBorder("Stroke"));

        // set layout
        setLayout(new GridLayout(0, 1));

        // initialize options
        initializeOptions();

    }

    /**
     * initialize options method
     */
    private void initializeOptions() {

        // set layout
        setLayout(new GridLayout(0, 1));

        // initialize size
        initializeSize();

        // initialize cap
        initializeCap();

        // initialize join
        initializeJoin();

        // initialize dash
        initializeDash();

    }

    /*
     * initialize size method
     */
    private void initializeSize() {

        // create slider for brush size
        JSlider size = new JSlider(JSlider.HORIZONTAL, 0, 100, 5);

        // set tooltip
        size.setToolTipText("Drag to set brush size");

        // set major tick spacing
        size.setMajorTickSpacing(20);

        // set minor tick spacing
        size.setMinorTickSpacing(5);

        // set paint ticks
        size.setPaintTicks(true);

        // set paint labels
        size.setPaintLabels(true);

        // create change listener for slider
        size.addChangeListener(new ChangeListener() {

            /**
             * state changed method
             * @param event event happened
             */
            public void stateChanged(ChangeEvent event) {

                // get event source
                JSlider source = (JSlider) event.getSource();

                // get value
                panel.setStrokeSize((Integer) source.getValue());

                // set label
                panel.setLabel("Stroke size is now changed to " + source.getValue() + ".");

            }
        });

        // panel for stroke size
        JPanel strokeSize = new JPanel();

        // set border with title colors
        strokeSize.setBorder(BorderFactory.createTitledBorder("Size"));

        // add slider to panel
        strokeSize.add(size);

        // add panel to this
        add(strokeSize);

    }

    /**
     * initialize cap method
     */
    private void initializeCap() {

        // options
        String[] options = {"Butt", "Square", "Round"};

        // create combo box
        JComboBox select = new JComboBox(options);

        // set tooltip
        select.setToolTipText("Click to select cap type");

        // add action listener
        select.addActionListener(new ActionListener() {

            /**
             * action performed event
             * @param event
             */
            public void actionPerformed(ActionEvent event) {

                // get source
                JComboBox source = (JComboBox) event.getSource();

                // get option
                String option = (String) source.getSelectedItem();

                // get option
                if (option.equals("Butt")) {

                    // set butt
                    panel.setStrokeCap(BasicStroke.CAP_BUTT);

                } else if (option.equals("Square")) {

                    // set butt
                    panel.setStrokeCap(BasicStroke.CAP_SQUARE);

                } else if (option.equals("Round")) {

                    // set butt
                    panel.setStrokeCap(BasicStroke.CAP_ROUND);

                }

                // set label
                panel.setLabel("Stroke cap is now changed to " + option + ".");

            }
        });

        // panel for stroke size
        JPanel strokeCap = new JPanel();

        // set border with title colors
        strokeCap.setBorder(BorderFactory.createTitledBorder("Cap"));

        // add slider to panel
        strokeCap.add(select);

        // add panel to this
        add(strokeCap);

    }

    /**
     * initialize join method
     */
    private void initializeJoin() {

        // options
        String[] options = {"Bevel", "Miter", "Round"};

        // create combo box
        JComboBox select = new JComboBox(options);

        // set tooltip
        select.setToolTipText("Click to select join type");

        // add action listener
        select.addActionListener(new ActionListener() {

            /**
             * action performed event
             * @param event
             */
            public void actionPerformed(ActionEvent event) {

                // get source
                JComboBox source = (JComboBox) event.getSource();

                // get option
                String option = (String) source.getSelectedItem();

                // get option
                if (option.equals("Bevel")) {

                    // set butt
                    panel.setStrokeJoin(BasicStroke.JOIN_BEVEL);

                } else if (option.equals("Miter")) {

                    // set butt
                    panel.setStrokeJoin(BasicStroke.JOIN_MITER);

                } else if (option.equals("Round")) {

                    // set butt
                    panel.setStrokeJoin(BasicStroke.JOIN_ROUND);

                }

                // set label
                panel.setLabel("Stroke join is now changed to " + option + ".");

            }
        });

        // panel for stroke size
        JPanel strokeJoin = new JPanel();

        // set border with title colors
        strokeJoin.setBorder(BorderFactory.createTitledBorder("Join"));

        // add slider to panel
        strokeJoin.add(select);

        // add panel to this
        add(strokeJoin);

    }

    /*
     * initialize dash method
     */
    private void initializeDash() {

        // create slider for brush size
        JSlider dash1 = new JSlider(JSlider.HORIZONTAL, 0, 10, 5);

        // set tooltip
        dash1.setToolTipText("Drag to set dash full length");

        // set major tick spacing
        dash1.setMajorTickSpacing(2);

        // set minor tick spacing
        dash1.setMinorTickSpacing(1);

        // set paint ticks
        dash1.setPaintTicks(true);

        // set paint labels
        dash1.setPaintLabels(true);

        // create change listener for slider
        dash1.addChangeListener(new ChangeListener() {

            /**
             * state changed method
             * @param event event happened
             */
            public void stateChanged(ChangeEvent event) {

                // get event source
                JSlider source = (JSlider) event.getSource();

                // get value
                panel.setDash1(((Integer) source.getValue()).floatValue());

                // set label
                panel.setLabel("Stroke dash full is now changed to " + source.getValue() + ".");

            }
        });

        // panel for stroke size
        JPanel strokeDash1 = new JPanel();

        // set border with title colors
        strokeDash1.setBorder(BorderFactory.createTitledBorder("Full"));

        // add dash 1
        strokeDash1.add(dash1);

        // create slider for brush size
        JSlider dash0 = new JSlider(JSlider.HORIZONTAL, 0, 1000, 0);

        // set tooltip
        dash0.setToolTipText("Drag to set dash empty length");

        // set major tick spacing
        dash0.setMajorTickSpacing(200);

        // set minor tick spacing
        dash0.setMinorTickSpacing(10);

        // set paint ticks
        dash0.setPaintTicks(true);

        // set paint labels
        dash0.setPaintLabels(true);

        // create change listener for slider
        dash0.addChangeListener(new ChangeListener() {

            /**
             * state changed method
             * @param event event happened
             */
            public void stateChanged(ChangeEvent event) {

                // get event source
                JSlider source = (JSlider) event.getSource();

                // get value
                panel.setDash0(((Integer) source.getValue()).floatValue());

                // set label
                panel.setLabel("Stroke dash empty is now changed to " + source.getValue() + ".");

            }
        });

        // panel for stroke size
        JPanel strokeDash0 = new JPanel();

        // set border with title colors
        strokeDash0.setBorder(BorderFactory.createTitledBorder("Empty"));

        // add dash 1
        strokeDash0.add(dash0);

        // create slider for brush size
        JSlider dash2 = new JSlider(JSlider.HORIZONTAL, 0, 1000, 0);

        // set tooltip
        dash2.setToolTipText("Drag to set dash offset length");

        // set major tick spacing
        dash2.setMajorTickSpacing(200);

        // set minor tick spacing
        dash2.setMinorTickSpacing(10);

        // set paint ticks
        dash2.setPaintTicks(true);

        // set paint labels
        dash2.setPaintLabels(true);

        // create change listener for slider
        dash2.addChangeListener(new ChangeListener() {

            /**
             * state changed method
             * @param event event happened
             */
            public void stateChanged(ChangeEvent event) {

                // get event source
                JSlider source = (JSlider) event.getSource();

                // get value
                panel.setOffset(((Integer) source.getValue()).floatValue());

                // set label
                panel.setLabel("Stroke dash offset is now changed to " + source.getValue() + ".");

            }
        });

        // panel for stroke size
        JPanel strokeDash2 = new JPanel();

        // set border with title colors
        strokeDash2.setBorder(BorderFactory.createTitledBorder("Offset"));

        // add dash 1
        strokeDash2.add(dash2);

        // panel for stroke size
        JPanel strokeDash = new JPanel();

        // add slider to panel
        add(strokeDash1);

        // add slider to panel
        add(strokeDash0);

        // add slider to panel
        add(strokeDash2);

    }
}
