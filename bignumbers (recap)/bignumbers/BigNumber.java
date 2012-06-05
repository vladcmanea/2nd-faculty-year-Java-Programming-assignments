/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bignumbers;

import java.util.Vector;

/**
 * just for natural numbers, 
 * trying to be fast :)
 * @author vladm
 */
public class BigNumber implements Cloneable {

    private Vector numbers = new Vector();

    public BigNumber(String string) {
        try {
            for (int i = string.length() - 1; i >= 0; i--) {
                if ('0' <= string.charAt(i) && string.charAt(i) <= '9') {
                    numbers.add(string.charAt(i) - '0');
                } else {
                    throw new Exception("Input string is invalid...");
                }
            }
            normalize();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public BigNumber(byte[] bytes) {
        try {
            for (int i = bytes.length - 1; i >= 0; i--) {
                if (0 <= bytes[i] && bytes[i] <= 9) {
                    numbers.add(bytes[i]);
                } else {
                    throw new Exception("Input bytes is invalid...");
                }
            }
            normalize();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private void normalize() {
        for (int i = numbers.size() - 1; i >= 0; i--) {
            if (Integer.parseInt(numbers.elementAt(i).toString()) == 0) {
                numbers.remove(i);
            } else {
                break;
            }
        }
        if (numbers.isEmpty()) {
            numbers.add(0);
        }
    }

    public void add(BigNumber other) {
        Vector result = new Vector();
        for (int i = 0, t = 0; i < numbers.size()
                || i < other.numbers.size() || t > 0; ++i, t /= 10) {
            if (i < numbers.size()) {
                t += Integer.parseInt(numbers.elementAt(i).toString());
            }
            if (i < other.numbers.size()) {
                t += Integer.parseInt(other.numbers.elementAt(i).toString());
            }
            result.add(t % 10);
        }
        numbers = result;
        normalize();
    }

    public void multiply(BigNumber other) {
        Vector result = new Vector();
        for (int i = 0; i < numbers.size() + other.numbers.size(); ++i) {
            result.add(0);
        }
        for (int i = 0; i < numbers.size(); ++i) {
            for (int j = 0; j < other.numbers.size(); ++j) {
                int e = Integer.parseInt(other.numbers.elementAt(j).toString())
                        * Integer.parseInt(numbers.elementAt(i).toString());
                int f = Integer.parseInt(result.get(i + j).toString());
                result.setElementAt(e + f, i + j);
            }
        }
        for (int i = 0, t = 0; i < result.size() || t > 0; ++i, t /= 10) {
            if (i < result.size()) {
                t += Integer.parseInt(result.elementAt(i).toString());
            } else {
                result.add(0);
            }
            result.setElementAt(t % 10, i);
        }
        numbers = result;
        normalize();
    }

    public static BigNumber add(BigNumber first, BigNumber second) {
        try {
            BigNumber firstn = (BigNumber) first.clone();
            firstn.add(second);
            return firstn;
        } catch (CloneNotSupportedException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public static BigNumber multiply(BigNumber first, BigNumber second) {
        try {
            BigNumber firstn = (BigNumber) first.clone();
            firstn.multiply(second);
            return firstn;
        } catch (CloneNotSupportedException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other.getClass() != this.getClass()) {
            return false;
        }
        BigNumber others = (BigNumber) other;
        if (numbers.size() != others.numbers.size()) {
            return false;
        }
        for (int i = 0; i < numbers.size(); ++i) {
            if (Integer.parseInt(others.numbers.elementAt(i).toString())
                    != Integer.parseInt(numbers.elementAt(i).toString())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.numbers != null ? this.numbers.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = numbers.size() - 1; i >= 0; --i) {
            builder.append(numbers.elementAt(i).toString());
        }
        return builder.toString();
    }
}
