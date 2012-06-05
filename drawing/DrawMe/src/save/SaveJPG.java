/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package save;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * SaveJPG class
 * @author vladm
 */
public class SaveJPG extends Save {

    /**
     * Method save
     * @param image rendered image to be created
     * @param file file to be created
     * @return success status
     */
    public boolean save(RenderedImage image, File file) {

        // create answer
        boolean answer = true;

        // try to save an image
        try {

            // write to provided data
            ImageIO.write(image, "jpg", file);

        } catch (IOException exception) {

            // could not write image
            answer = false;

        }

        // answer
        return answer;

    }

}
