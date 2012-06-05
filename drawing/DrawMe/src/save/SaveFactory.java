/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package save;

import java.util.TreeMap;
import java.util.Map;

/**
 * SaveFactory class
 * @author vladm
 */
public abstract class SaveFactory {

    // set of types
    private static Map<String, String> types = new TreeMap<String, String>();

    /**
     * register method
     * @param type type to be added
     * @param name name to be added
     * @return success status
     */
    public static boolean register(String type, String name) {

        // register to map
        return types.put(type, name) == null;

    }

    /**
     * unregister method
     * @param type to be removed
     * @return success status
     */
    public static boolean unregister(String type) {

        // register to map
        return types.remove(type) == null;

    }

    /**
     * get method
     * @param type to be got
     * @return object or null, if not exists
     */
    public static Save get(String type) {

        // does type exist?
        if (types.containsKey(type)) {

            try {

                // get class object
                Class forName = Class.forName("save." + type);

                // return instance
                return (Save) forName.newInstance();

            } catch (InstantiationException exception) {

                // nothing to return
                return null;

            } catch (IllegalAccessException exception) {

                // nothing to return
                return null;

            } catch (ClassNotFoundException exception) {

                // nothing to return
                return null;

            }

        } else {

            // nothing to return
            return null;

        }

    }

    /**
     * list strings method
     * @return list of strings
     */
    public static Map<String, String> list() {

        // create map
        Map<String, String> copy = new TreeMap<String, String>();

        // copy content
        copy.putAll(types);

        // return types
        return copy;
    }
}
