package lab4p1;

public class AVLNode extends Node {

	private AVLNode left = null;
	private AVLNode right = null;
	private AVLNode parent = null;
	private int height = 0;
	
	public AVLNode() {
		super();
	}
	
	public AVLNode(int key) {
		super(key);
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setLeft(AVLNode left) {
		this.left = left;
		if (this.left != null) {
			this.left.setParent(this);
		}
		this.update();
	}
	
	public AVLNode getLeft() {
		return left;
	}
	
	public void setRight(AVLNode right) {
		this.right = right;
		if (this.right != null) {
			this.right.setParent(this);
		}
		this.update();
	}
	
	public AVLNode getRight() {
		return right;
	}
	
	public void setParent(AVLNode parent) {
		this.parent = parent;
		if (this.parent != null) {
			this.parent.update();
		}
	}
	
	public AVLNode getParent() {
		if (parent != null) {
			parent.update();
		}
		return parent;
	}
	
	public void update() {
		this.height = 0;
		if (this.left != null && this.left.getHeight() > this.height) {
			this.height = this.left.getHeight();
		}
		if (this.right != null && this.right.getHeight() > this.height) {
			this.height = this.right.getHeight();
		}
		this.height++;
	}
}
