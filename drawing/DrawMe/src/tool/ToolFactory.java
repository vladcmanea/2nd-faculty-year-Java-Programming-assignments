/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tool;

import gui.CanvasPanel;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Constructor;
import java.util.TreeMap;
import java.util.Map;

/**
 * ToolFactory class
 * @author vladm
 */
public abstract class ToolFactory {

    // map of types
    private static Map<String, String> types = new TreeMap<String, String>();

    /**
     * register method
     * @param type type to be added
     * @param name name to be added
     * @return success status
     */
    public static boolean register(String type, String name) {

        // register to map
        return types.put(type, name) != null;

    }

    /**
     * unregister method
     * @param type to be removed
     * @return success status
     */
    public static boolean unregister(String type) {

        // unregister to map
        return types.remove(type) != null;

    }

    /**
     * get method
     * @param type to be got
     * @param panel canvas panel to be used
     * @return object or null, if not exists
     */
    public static Tool get(String type, CanvasPanel panel) {

        // does type exist?
        if (types.get(type) != null && panel != null) {

            try {

                // get class
                Class forName = Class.forName("tool." + type);

                // get constructor
                Constructor constructor = forName.getConstructor(CanvasPanel.class);

                // get object
                return (Tool) constructor.newInstance(panel);

            } catch (InstantiationException exception) {

                // return nothing
                return null;

            } catch (IllegalAccessException exception) {

                // return nothing
                return null;

            } catch (IllegalArgumentException exception) {

                // return nothing
                return null;

            } catch (InvocationTargetException exception) {

                // return nothing
                return null;

            } catch (NoSuchMethodException exception) {

                // return nothing
                return null;

            } catch (SecurityException exception) {

                // return nothing
                return null;

            } catch (ClassNotFoundException exception) {

                // return nothing
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
