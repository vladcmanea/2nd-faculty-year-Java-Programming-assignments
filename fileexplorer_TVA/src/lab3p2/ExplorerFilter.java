package lab3p2;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;

/**
 * @author Vlad Manea
 * @author Vlad Tudose
 * @version 0.1
 * <b>Description</b>: ExplorerFilter class
 */
public class ExplorerFilter implements FileFilter {

    /*
     * Data
     */
    String regex = new String("");

    /**
     * <b>Description</b>: ExplorerFilter constructor method
     * @param regex in String format
     */
    public ExplorerFilter(String regex) {
        /* creates ExplorerFilter */
        this.regex = regex; // set regex
    }

    /**
     * <b>Description</b>: accept method
     * @param file in File format
     */
    public boolean accept(File file) {

        if (file.isDirectory() == true) {
            /* file will be accepted */
            return true; // success
        }

        if (file.isFile() == true
                && Pattern.matches(regex, file.getName())) {
            /* file is of type file and matches regex */
            return true; // success
        }

        return false; // failure
    }
}
