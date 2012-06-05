/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frogs;

import java.awt.Color;
import java.awt.Dimension;

/**
 * Main Class
 * @author vladm
 */
public class Main {

    /**
     * main function
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // create frog window
        new FrogWindow("Frogs", "turtle.png", new Color(200, 200, 255),
                new Dimension(800, 800), 20, 10, 10);
        
    }
}
