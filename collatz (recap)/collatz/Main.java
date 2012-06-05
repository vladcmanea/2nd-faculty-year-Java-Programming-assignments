/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package collatz;

import java.util.Random;

/**
 *
 * @author vladm
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Random generator = new Random();
        long N = generator.nextInt(Integer.MAX_VALUE);
        System.out.println(N);
        boolean S = (N > 100);
        long count = 0;
        long time = System.currentTimeMillis();
        if (N != 100) {
            do {
                if (N % 2 == 0) {
                    N /= 2;
                } else {
                    N = 3 * N + 1;
                }
                if (S) {
                    ++count;
                } else {
                    System.out.println(N);
                }
            } while (N > 1);
            if (S) {
                System.out.println((System.currentTimeMillis() - time) + " " + count);
            }
        }
    }
}
