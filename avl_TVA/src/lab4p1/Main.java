package lab4p1;

public class Main {

	public static void main(String[] args) {
		AVL tree = new AVL();
		tree.add(1);
		tree.add(6);
		tree.add(4);
		tree.add(5);
		tree.add(2);
		tree.add(3);
		tree.add(19);
		tree.add(18);
		tree.add(17);
		tree.add(16);
		tree.add(15);
		System.out.println(tree.search(16));
		tree.remove(6);
		System.out.println(tree.search(16));
		
		System.out.println("PreOrder Heights");
		tree.explore(new ExplorerPreOrder(), new StarProcessor());

		System.out.println("InOrder Values");
		tree.explore(new ExplorerInOrder(), new WriterProcessor());

		System.out.println("PostOrder Values");
		tree.explore(new ExplorerPostOrder(), new WriterProcessor());
		
		System.out.println("Print Tree");
		System.out.println(tree);
	}
}
