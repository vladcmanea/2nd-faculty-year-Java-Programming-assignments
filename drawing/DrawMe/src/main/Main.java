/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import tool.*;
import gui.MainFrame;
import save.SaveFactory;

/**
 *
 * @author vladm
 */
public class Main {

    /**
     * main function
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // save factory register
        SaveFactory.register("SaveJPG", "Save as JPG");
        SaveFactory.register("SavePNG", "Save as PNG");

        // tool factory register
        ToolFactory.register("ToolDrawFree", "Draw Freely");
        ToolFactory.register("ToolDrawLine", "Draw Line");
        ToolFactory.register("ToolDrawOval", "Draw Oval");
        ToolFactory.register("ToolDrawRect", "Draw Rect");
        ToolFactory.register("ToolFillRect", "Fill Rect");
        ToolFactory.register("ToolFillOval", "Fill Oval");
        ToolFactory.register("ToolDrawPolygon", "Draw Poly");
        ToolFactory.register("ToolFillPolygon", "Fill Poly");
        ToolFactory.register("ToolDrawRoundRect", "Draw Round Rect");
        ToolFactory.register("ToolFillRoundRect", "Fill Round Rect");
        
        // draw me
        MainFrame mainFrame = new MainFrame();

    }
}
