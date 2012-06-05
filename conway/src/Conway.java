package lab4p2;

import java.io.*;
import java.util.*;

/**
 * @author Vlad Manea
 * @author Vlad Tudose
 * @version 0.1
 * <b>Description</b>: Conway class
 */
public class Conway implements Serializable {

    /*
     * Data
     */
    private long n = 1;
    private long m = 1;
    private Set<Point> alive = new HashSet<Point>();

    /**
     * <b>Description</b>: Sets state
     * @param n number of lines in long format
     * @param m number of columns in long format
     * @param map initial matrix in Boolean[][] format
     */
    public void setState(long n, long m, Set<Point> alive) {
        this.alive.clear();
        if (n < 1) {
            return;
        } else if (m < 1) {
            return;
        } else {
            this.n = n;
            this.m = m;
            Iterator<Point> it = alive.iterator();
            while (it.hasNext()) {
                Point point = it.next();
                if (isInside(point) == true) {
                    this.alive.add(point);
                }
            }
        }
    }

    /**
     * <b>Description</b>: Gets string
     * @return string representation of status in String number
     */
    public String toString() {
        StringBuilder answer = new StringBuilder("");
        answer.append('+');
        for (long j = 0; j < m; ++j) {
            answer.append('-');
        }
        answer.append("+\n");
        for (long i = 0; i < n; ++i) {
            answer.append('+');
            for (long j = 0; j < m; ++j) {
                if (alive.contains(new Point(i, j))) {
                    answer.append("X");
                } else {
                    answer.append(" ");
                }
            }
            answer.append("+\n");
        }
        answer.append('+');
        for (long j = 0; j < m; ++j) {
            answer.append('-');
        }
        answer.append("+\n");
        return answer.toString();
    }

    /**
     * <b>Description</b>: Is inside
     * @author Vlad Manea
     * @return boolean value (true if position is inside or false otherwise)
     */
    private boolean isInside(Point point) {
        /* is inside? */
        return (0 <= point.getX() && point.getX() < n && 0 <= point.getY() && point.getY() < m);
    }

    /**
     * <b>Description</b>: steps
     * @return boolean value (true if next step is still or false otherwise)
     */
    public boolean nextState() {
        /* steps */
        byte is[] = {-1, -1, -1, 0, 1, 1, 1, 0, 0}; // position arrays
        byte js[] = {-1, 0, 1, 1, 1, 0, -1, -1, 0}; // position arrays

        Set<Point> newAlive = new HashSet<Point>();

        /* iterate old set */
        Iterator<Point> it = alive.iterator();
        while (it.hasNext()) {
            /* iterate points */

            Point point = it.next();
            long line = point.getX();
            long column = point.getY();

            for (int i = 0; i < 9; ++i) {

                long newLine = line + is[i];
                long newColumn = column + js[i];
                Point newPoint = new Point(newLine, newColumn);

                if (isInside(newPoint)) {
                    /* pozitia respectiva exista si poate fi adaugata */

                    int count = 0;
                    for (int j = 0; j < 8; ++j) {

                        long newestLine = newLine + is[j];
                        long newestColumn = newColumn + js[j];
                        Point newestPoint = new Point(newestLine, newestColumn);

                        if (isInside(newestPoint)) {
                            /* pozitia vecina este in matrice */
                            if (alive.contains(newestPoint)) {
                                ++count;
                            }
                        }
                    }

                    if (alive.contains(newPoint)) {
                        if (count >= 2 && count <= 3) {
                            newAlive.add(newPoint);
                        }
                    } else if (count == 3) {
                        newAlive.add(newPoint);
                    }
                }
            }
        }

        boolean ans = true;

        it = alive.iterator();
        while (it.hasNext() && ans == true) {
            if (newAlive.contains(it.next()) == false) {
                ans = false;
            }
        }
        it = newAlive.iterator();
        while (it.hasNext() && ans == true) {
            if (alive.contains(it.next()) == false) {
                ans = false;
            }
        }

        if (ans == false) {
            alive = newAlive;
        }

        /* there is still */
        return ans; // return
    }

    /**
     * <b>Description</b>: Serializes status from file
     * @author Vlad Manea
     * @param file filename of file from which data is serialized
     */
    public void read(String file) {
        /* reads status */

        FileInputStream fis = null; // primitive stream
        ObjectInputStream in = null; // processing stream

        try {
            fis = new FileInputStream(file); // primitive stream
            in = new ObjectInputStream(fis); // processing stream
            Conway C = (Conway) in.readObject();
            this.m = C.m;
            this.n = C.n;
            this.alive = C.alive;
        } catch (FileNotFoundException e) {
            n = m = 1;
        } catch (IOException e) {
            n = m = 1;
        } catch (ClassNotFoundException e) {
            n = m = 1;
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * <b>Description</b>: Serializes status to file
     * @author Vlad Manea
     * @param file filename of file to which data is serialized
     */
    public void write(String file) {
        /* writes status */

        FileOutputStream fos = null; // primitive stream
        ObjectOutputStream out = null; // processing stream

        try {
            fos = new FileOutputStream(file); // primitive stream
            out = new ObjectOutputStream(fos); // processing stream
            out.writeObject(this);
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
