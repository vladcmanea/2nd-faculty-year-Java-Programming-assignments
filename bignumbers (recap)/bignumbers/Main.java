/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bignumbers;

/**
 *
 * @author vladm
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BigNumber number1 = new BigNumber("000010");
        BigNumber number2 = new BigNumber("00000019999");
        BigNumber sum = BigNumber.add(number1, number2);
        System.out.println(sum);
        BigNumber product = BigNumber.multiply(number1, number2);
        System.out.println(product);
    }
}
