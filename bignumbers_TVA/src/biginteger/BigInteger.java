package biginteger;

/**
 *
 * @author vrac
 */
public class BigInteger {

    public static final BigInteger ZERO;
    public static final BigInteger ONE;
    public static final BigInteger TEN;
    //public static final BigInteger QUADRILLION;

    static {
        byte magnitude[] = new byte[1];
        ZERO = new BigInteger(0, new byte[0]);
        magnitude[0] = 1;
        ONE = new BigInteger(1, magnitude);
        magnitude[0] = 10;
        TEN = new BigInteger(1, magnitude);
        //QUADRILLION = new BigInteger(1000000000000000l);
    }
    private int sign;
    private byte magnitude[];

    public BigInteger(int sign, byte magnitude[]) { //PROBABIL MERGE
        if (sign < -1 || sign > 1) {
            throw new NumberFormatException("Invalid sign value");
        }
        this.magnitude = minimize(magnitude);
        if (this.magnitude.length == 0) {
            this.sign = 0;
        } else {
            if (sign == 0) {
                throw new NumberFormatException("Sign-magnitude mismatch");
            }
            this.sign = sign;
        }
    }

    public BigInteger(long value) { //PROBABIL MERGE
        this((new Long(value)).toString());
    }

    public BigInteger(String value) { //PROBABIL MERGE
        int valueLength = value.length();
        if (valueLength == 0) {
            throw new NumberFormatException("String is empty");
        }
        char ch = value.charAt(0);
        if (ch == '-' && valueLength == 1) {
            throw new NumberFormatException("String contain only '-'");
        }

        BigInteger result = ZERO;
        for (int i = (ch == '-' ? 1 : 0); i < valueLength; ++i) {
            char digit = value.charAt(i);
            if (digit < '0' || digit > '9') {
                throw new NumberFormatException("Illegal digit");
            }
            byte byteDigit[] = {(byte) (digit - '0')};
            result = result.multiply(TEN).add(new BigInteger(1, byteDigit));
        }
        if (ch == '-') {
            result = result.negate();
        }
        this.sign = result.sign;
        this.magnitude = result.magnitude;
    }

    public BigInteger add(BigInteger value) { //PROBABIL MERGE
        if (value.sign == 0) {
            return this;
        }
        if (sign == 0) {
            return value;
        }
        if (value.sign == sign) {
            return new BigInteger(sign, add(magnitude, value.magnitude));
        }

        int cmp = compareMagnitude(value);
        if (cmp == 0) {
            return ZERO;
        }
        byte resultMagnitude[] = (cmp > 0 ? subtract(magnitude, value.magnitude)
                : subtract(value.magnitude, magnitude));
        return new BigInteger((cmp == sign ? 1 : -1), resultMagnitude);
    }

    public BigInteger subtract(BigInteger value) { //PROBABIL MERGE
        return add(value.negate());
    }

    public BigInteger negate() { //PROBABIL MERGE
        return new BigInteger(-this.sign, this.magnitude);
    }

    public BigInteger abs() { //PROBABIL MERGE
        if (sign >= 0) {
            return this;
        } else {
            return new BigInteger(1, this.magnitude);
        }
    }

    public BigInteger multiply(BigInteger value) { //PROBABIL MERGE
        if (value.sign == 0 || sign == 0) {
            return ZERO;
        }

        byte resultMagnitude[] = multiply(magnitude, value.magnitude);
        return new BigInteger((sign == value.sign ? 1 : -1), resultMagnitude);
    }

    @SuppressWarnings("empty-statement")
    public BigInteger divide(BigInteger dividend) { //PROBABIL MERGE
        BigInteger left = ZERO;
        BigInteger right = this;
        BigInteger mid;

        while (!left.equals(right)) {
            mid = left.add(right).add(ONE);
            mid.div2();
            if (mid.multiply(dividend).compareTo(this) <= 0) {
                left = mid;
            } else {
                right = mid.subtract(ONE);
            }
        }
        return left;
    }

    public BigInteger mod(BigInteger dividend) { //PROBABIL MERGE
        return this.subtract(this.divide(dividend).multiply(dividend));
    }

    public BigInteger pow(int power) {
        return ONE;
    }

    public int compareTo(BigInteger value) { //PROBABIL MERGE
        if (value.sign == 0) {
            return this.sign;
        }
        if (this.sign == 0) {
            return -value.sign;
        }
        if (this.sign == 1) {
            if (value.sign == -1) {
                return 1;
            } else {
                return compareMagnitude(value);
            }
        } else {
            if (value.sign == 1) {
                return -1;
            } else {
                return -compareMagnitude(value);
            }
        }
    }

    public boolean equals(BigInteger value) {
        return this.compareTo(value) == 0;
    }

    public long longValue() { //PROBABIL MERGE
        return new Long(this.toString());
    }

    @Override
    public String toString() { //PROBABIL MERGE
        if (sign == 0) {
            return "0";
        }
        String result = "";

        BigInteger value = this.abs();
        BigInteger digit = null;

        while (value.sign > 0) {
            digit = value.mod(TEN);
            value = value.divide(TEN);
            result = (digit.sign == 0 ? 0 : digit.magnitude[0]) + result;
        }
        if (sign < 0) {
            result = "-" + result;
        }
        return result;
    }

    @SuppressWarnings("empty-statement")
    private byte[] minimize(byte a[]) { //PROBABIL MERGE
        int i;
        byte b[];

        for (i = a.length - 1; i >= 0 && a[i] == 0; --i);
        b = new byte[i + 1];
        for (; i >= 0; --i) {
            b[i] = a[i];
        }
        return b;
    }

    private static byte[] add(byte a[], byte b[]) { //TESTAT
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

    private static byte[] subtract(byte a[], byte b[]) { //TESTAT
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

    private static byte[] multiply(byte a[], byte b[]) { //TESTAT
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
    }

    public void div2() { //PROBABIL MERGE modifica
        int i;
        for (i = 0; i < magnitude.length - 1; ++i) {
            magnitude[i] = (byte) ((magnitude[i] & 255) >> 1);
            magnitude[i] |= (int) (magnitude[i + 1] & 1) << 7;
        }
        magnitude[i] = (byte) ((magnitude[i] & 255) >> 1);
    }

    private int compareMagnitude(BigInteger value) { //PROBABIL MERGE
        if (this.magnitude.length < value.magnitude.length) {
            return -1;
        } else if (this.magnitude.length > value.magnitude.length) {
            return 1;
        }
        int i;

        for (i = this.magnitude.length - 1; i >= 0; --i) {
            if ((this.magnitude[i] & 255) < (value.magnitude[i] & 255)) {
                return -1;
            }
            if ((this.magnitude[i] & 255) > (value.magnitude[i] & 255)) {
                return 1;
            }
        }
        return 0;
    }
}
