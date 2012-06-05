/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package save;

import java.awt.Component;
import java.io.File;
import javax.swing.JFileChooser;

/**
 * Chooser class
 * @author vladm
 */
public abstract class Chooser {

    // private chooser
    private static JFileChooser chooser;

    /**
     * get open method
     * @param component parent component
     * @return status
     */
    public static int getOpen(Component component) {

        // get instance
        if (chooser == null) {

            // create instance
            chooser = new JFileChooser();

        }

        // return open status
        return chooser.showOpenDialog(component);

    }

    /**
     * get save method
     * @param component parent component
     * @return status
     */
    public static int getSave(Component component) {

        // get instance
        if (chooser == null) {

            // create instance
            chooser = new JFileChooser();

        }

        // return open status
        return chooser.showSaveDialog(component);

    }

    /**
     * get file method
     * @return file chosen
     */
    public static File getFile() {

        // get instance
        if (chooser == null) {

            // create instance
            chooser = new JFileChooser();

        }

        // return open status
        return chooser.getSelectedFile();

    }

}
