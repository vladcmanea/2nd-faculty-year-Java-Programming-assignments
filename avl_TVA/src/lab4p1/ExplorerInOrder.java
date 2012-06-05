package lab4p1;

public class ExplorerInOrder implements Explorer {

	public void explore(AVLNode n, Processor processor) {
		if (n != null) {
			explore(n.getLeft(), processor);
			processor.process(n);
			explore(n.getRight(), processor);
		}
	}
}
