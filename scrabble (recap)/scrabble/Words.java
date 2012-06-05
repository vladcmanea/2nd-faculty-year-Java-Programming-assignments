/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scrabble;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author vladm
 */
public class Words {

    private List<String> words = new LinkedList<String>();

    public void addWords(String... words) {
        this.words.addAll(Arrays.asList(words));
        Collections.sort(this.words, new Comparator() {

            public int compare(Object o1, Object o2) {
                return ((String) o1).compareTo((String) o2);
            }
        });
    }

    public int size() {
        return words.size();
    }

    public String wordAt(int i) {
        return words.get(i);
    }

    public int lowerIndex(int l, int r, int p, char c) {
        try {
            if (l > r || l < 0 || r >= words.size()) {
                throw new Exception("indexes l and r are invalid");
            }
            int a = Integer.MAX_VALUE;
            while (l <= r) {
                int m = (l + r) / 2;
                if (words.get(m).length() <= p) {
                    l = m + 1;
                } else if (words.get(m).charAt(p) > c) {
                    r = m - 1;
                } else if (words.get(m).charAt(p) < c) {
                    l = m + 1;
                } else {
                    r = m - 1;
                    a = m;
                }
            }
            return a;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return Integer.MAX_VALUE;
        }
    }

    public int higherIndex(int l, int r, int p, char c) {
        try {
            if (l > r || l < 0 || r >= words.size()) {
                throw new Exception("indexes l and r are invalid");
            }
            int a = Integer.MIN_VALUE;
            while (l <= r) {
                int m = (l + r) / 2;
                if (words.get(m).length() <= p) {
                    l = m + 1;
                } else if (words.get(m).charAt(p) > c) {
                    r = m - 1;
                } else if (words.get(m).charAt(p) < c) {
                    l = m + 1;
                } else {
                    l = m + 1;
                    a = m;
                }
            }
            return a;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return Integer.MIN_VALUE;
        }
    }
}
