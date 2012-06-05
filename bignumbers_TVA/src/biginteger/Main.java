/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package biginteger;

/**
 *
 * @author vrac
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    /*private static byte[] add(byte a[], byte b[]) {
    if (a.length < b.length) {
    byte aux[] = a;
    a = b;
    b = aux;
    }
    byte result[] = new byte[a.length + 1];
    int i;
    short sum;
    byte t = 0;

    for (i = 0; i < a.length; ++i) {
    sum = (short) ((a[i] & 255) + (i < b.length ? (b[i] & 255) : 0) + t);
    result[i] = (byte) (sum & 255);
    t = (byte) (sum / 256);
    }
    result[i] = t;

    return result;
    }

    private static byte[] subtract(byte a[], byte b[]) {
    byte result[] = new byte[a.length];
    int i;
    short sub;
    byte t = 0;

    for (i = 0; i < a.length; ++i) {
    sub = (short) ((a[i] & 255) - (i < b.length ? (b[i] & 255) : 0) - t);
    result[i] = (byte) (sub < 0 ? sub + 256 : sub);
    t = (byte) (sub < 0 ? 1 : 0);
    }
    return result;
    }

    private static byte[] multiply(byte a[], byte b[]) {
    byte result[] = new byte[a.length + b.length];
    int i;
    int j;
    int aux;
    int t;

    for (i = 0; i < result.length; ++i) {
    result[i] = 0;
    }
    for (i = 0; i < a.length; ++i) {
    for (t = j = 0; j < b.length || t != 0; ++j) {
    aux = (result[i + j] & 255)
    + (a[i] & 255) * (j < b.length ? b[j] & 255 : 0) + t;
    result[i + j] = (byte) (aux & 255);
    t = aux >>> 8;
    }
    }
    return result;
    }*/
    public static void main(String[] args) {
        //byte[] a = {(byte) 200, (byte) 155, (byte) 39, (byte) 211};
        //byte[] b = {(byte) 255, (byte) 55, (byte) 133, (byte) 200, (byte) 128};
        //byte[] c = multiply(a, b);

        BigInteger B1 = new BigInteger("3542588360");
        BigInteger B2 = new BigInteger("553119987711");
        BigInteger B3 = B1.multiply(B2);

        //BigInteger B4 = new BigInteger("1959476430148331");
        //BigInteger B5 = B4.divide(BigInteger.TEN);

        BigInteger A = new BigInteger("99999999");
        BigInteger B = A.divide(BigInteger.TEN);

        System.out.println(B);
    }
}
