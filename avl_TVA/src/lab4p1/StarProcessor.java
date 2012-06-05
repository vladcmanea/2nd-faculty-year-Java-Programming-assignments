package lab4p1;

public class StarProcessor implements Processor {

	public void process(AVLNode n) {
		if (n != null) {
			System.out.println(n.getHeight());
		}
	}
}
