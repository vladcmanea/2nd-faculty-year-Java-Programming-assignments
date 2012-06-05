package lab4p2;

import java.util.HashSet;
import java.util.Set;

public class Main {

    @SuppressWarnings("empty-statement")
    public static void main(String[] args) {

        Conway C = new Conway();
        Set<Point> S = new HashSet<Point>();

        S.add(new Point(0l, 0l));
        S.add(new Point(1l, 1l));
        S.add(new Point(2l, 2l));
        S.add(new Point(3l, 3l));
        S.add(new Point(1l, 3l));
        S.add(new Point(2l, 3l));

        C.setState(20, 20, S);

        System.out.println(C);
        C.nextState();
        System.out.println(C);
        C.nextState();
        System.out.println(C);
        C.nextState();
        System.out.println(C);
        C.nextState();
        System.out.println(C);
        C.nextState();
        System.out.println(C);
        C.nextState();
        System.out.println(C);
        C.nextState();
        System.out.println(C);
        C.nextState();
        System.out.println(C);
        C.nextState();
        System.out.println(C);
        C.nextState();
        System.out.println(C);
        C.nextState();
        System.out.println(C);
        C.nextState();
        System.out.println(C);
        C.nextState();
        System.out.println(C);
        C.nextState();
        System.out.println(C);
        C.nextState();
        System.out.println(C);
        C.nextState();
        System.out.println(C);
        C.nextState();
        System.out.println(C);

        C.write("serial.txt");

        Conway C2 = new Conway();
        C2.read("serial.txt");

        System.out.println();
        System.out.println(C2);

    }
}
