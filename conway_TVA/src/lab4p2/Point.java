package lab4p2;

import java.io.Serializable;

/**
 * @author Vlad Manea
 * @author Vlad Tudose
 * @version 0.1
 * <b>Description</b>: Point class
 */
public class Point implements Serializable{

    /*
     * Data
     */
    long x;
    long y;

    /**
     * <b>Description</b>: Constructs point
     * @param x vertical position
     * @param y horizontal position
     */
    public Point(long x, long y) {
        this.x = x;
        this.y = y;
    }

    /**
     * <b>Description</b>: Gets x
     * @return x in Long format
     */
    public long getX() {
        return x;
    }

    /**
     * <b>Description</b>: Gets y
     * @return y in Long format
     */
    public long getY() {
        return y;
    }

    /**
     * <b>Description</b>: Gets equal
     * @return equality status in boolean format (true for equal)
     */
    public boolean equals(Object obj) {
        if (obj.getClass().equals(this.getClass())) {
            if (((Point) obj).getX() == this.getX() && ((Point) obj).getY() == this.getY()) {
                return true;
            }
        }

        return false;
    }

    /**
     * <b>Description</b>: Gets hash code
     * @return hash code in int format
     */
    public int hashCode() {
        return (int) (x * 666013 + y);
    }
}
