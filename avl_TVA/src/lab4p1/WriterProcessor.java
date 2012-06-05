package lab4p1;

public class WriterProcessor implements Processor {

	public void process(AVLNode n) {
		if (n != null) {
			System.out.println(n.getKey());
		}
	}
}
