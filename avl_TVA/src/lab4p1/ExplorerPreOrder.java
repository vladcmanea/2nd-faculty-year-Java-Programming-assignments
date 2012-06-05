package lab4p1;

public class ExplorerPreOrder implements Explorer {

	public void explore(AVLNode n, Processor processor) {
		if (n != null) {
			processor.process(n);
			explore(n.getLeft(), processor);
			explore(n.getRight(), processor);
		}
	}
}
