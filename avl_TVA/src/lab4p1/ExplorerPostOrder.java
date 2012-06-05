package lab4p1;

public class ExplorerPostOrder implements Explorer {

	public void explore(AVLNode n, Processor processor) {
		if (n != null) {
			explore(n.getLeft(), processor);
			explore(n.getRight(), processor);
			processor.process(n);
		}
	}
}
