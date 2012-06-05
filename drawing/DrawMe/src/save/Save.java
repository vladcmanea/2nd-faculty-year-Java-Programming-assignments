/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package save;

import java.awt.image.RenderedImage;
import java.io.File;

/**
 * Save class
 * @author vladm
 */
public abstract class Save {

    /**
     * Method save
     * @param image rendered image to be created
     * @param file file to be created
     * @return success status
     */
    public abstract boolean save(RenderedImage image, File file);
}
