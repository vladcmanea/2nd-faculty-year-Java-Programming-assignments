package lab4p1;

import java.util.TreeMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

public class AVL {
	
	private AVLNode root = null;
	
	public AVL() {}

	public void explore(Explorer explorer, Processor processor) {
		explorer.explore(root, processor);
	}
	
	private int pass(Integer space, AVLNode n, Integer lev, 
			Map<Integer, TreeSet<Integer>> level, Map<Integer, Integer> position) {
		
		if (n != null) {
			
			int ans = 0;
		
			if (n.getLeft() != null) {
				ans += pass(space, n.getLeft(), lev + 1, level, position);
			}
			
			if (level.get(lev) == null) {
				level.put(lev, new TreeSet<Integer>());
			}
			
			level.get(lev).add(n.getKey());
			position.put(n.getKey(), ans + space);
			
			ans += new Integer(n.getKey()).toString().length();
			
			if (n.getRight() != null) {
				ans += pass(ans + space, n.getRight(), lev + 1, level, position);
			}
			
			return ans;
		}
		
		return 0;
	}
	
	public String toString() {
		
		Map<Integer, TreeSet<Integer>> level = new TreeMap<Integer, TreeSet<Integer>>();
		Map<Integer, Integer> position = new TreeMap<Integer, Integer>();
		
		pass(new Integer(0), root, 0, level, position);
		
		StringBuilder answer = new StringBuilder("");
		
		Iterator<TreeSet<Integer>> it = level.values().iterator();
		while (it.hasNext()) {
			int margin = 0;

			Iterator<Integer> jt = it.next().iterator();
			while (jt.hasNext()) {
				Integer node = jt.next();
				
				for (int j = margin + 1; j <= position.get(node); ++j) {
					answer.append(" ");
				}
				
				answer.append(node);
				margin += position.get(node) - margin + node.toString().length();
			}
			answer.append("\n");
		}
		
		level.clear();
		position.clear();

		return answer.toString();
	}
	
	private AVLNode leftRotation(AVLNode node) {
		if (node != null) {
			AVLNode answer = node.getRight();
			node.setRight(answer.getLeft());
			if (node.getParent() != null) {
				if (answer != null && answer.getKey() < node.getParent().getKey()) {
					node.getParent().setLeft(answer);
				} else if (answer != null && answer.getKey() > node.getParent().getKey()) {
					node.getParent().setRight(answer);
				} 
			}
			if (answer != null) {
				answer.setLeft(node);
			}
			
			return answer;
		} else {
			return null;
		}
	}
	
	private AVLNode rightRotation(AVLNode node) {
		if (node != null) {
			AVLNode answer = node.getLeft();
			node.setLeft(answer.getRight());
			if (node.getParent() != null) {
				if (answer != null && answer.getKey() < node.getParent().getKey()) {
					node.getParent().setLeft(answer);
				} else if (answer != null && answer.getKey() > node.getParent().getKey()) {
					node.getParent().setRight(answer);
				} 
			}
			if (answer != null) {
				answer.setRight(node);
			}
			
			return answer;
		} else {
			return null;
		}
	}
	
	private AVLNode doubleLeftRotation(AVLNode node) {
		if (node != null) {
			rightRotation(node.getRight());
			return leftRotation(node);
		} else {
			return null;
		}
	}

	private AVLNode doubleRightRotation(AVLNode node) {
		if (node != null) {
			leftRotation(node.getLeft());
			return rightRotation(node);
		} else {
			return null;
		}
	}
	
	private void reset(AVLNode node) {
		if (node != null) {
			int left = (node.getLeft() != null? node.getLeft().getHeight(): 0);
			int right = (node.getRight() != null? node.getRight().getHeight(): 0);
			int left1;
			int right1;
			AVLNode newRoot = null;
			if (left - right <= -2) {
				left1 = (node.getRight().getLeft() != null? node.getRight().getLeft().getHeight(): 0);
				right1 = (node.getRight().getRight() != null? node.getRight().getRight().getHeight(): 0);
				if (left1 - right1 == -1) {
					newRoot = leftRotation(node);
				} else if (left1 - right1 == 1) {
					newRoot = doubleLeftRotation(node);
				}
				if (root == node) {
					newRoot.setParent(null);
					root = newRoot;
				} else {
					reset(node.getParent());
				}
			} else if (left - right >= 2) {
				left1 = (node.getLeft().getLeft() != null? node.getLeft().getLeft().getHeight(): 0);
				right1 = (node.getLeft().getRight() != null? node.getLeft().getRight().getHeight(): 0);
				if (left1 - right1 == 1) {
					newRoot = rightRotation(node);
				} else if (left1 - right1 == -1) {
					newRoot = doubleRightRotation(node);
				}
				if (root == node) {
					newRoot.setParent(null);
					root = newRoot;
				} else {
					reset(node.getParent());
				}
			} else {
				reset(node.getParent());
			}
		}
	}
	
	public void add(int value) {
		add(root, null, value);
	}
	private void add(AVLNode node, AVLNode parent, int value) {
		if (node == null) {
			AVLNode child = new AVLNode(value);
			child.setParent(parent);
			if (parent != null && value < parent.getKey()) {
				parent.setLeft(child);
			} else if (parent != null && value > parent.getKey()) {
				parent.setRight(child);
			}
			if (root == null) {
				root = child;
			}
			reset(parent);
		} else {
			if (value < node.getKey()) {
				add(node.getLeft(), node, value);
			} else if (value > node.getKey()) {
				add(node.getRight(), node, value);
			} 
		}
	}
	
	public void remove(int value) {
		remove(root, null, value);
	}
	private void remove(AVLNode node, AVLNode parent, int value) {
		if (node != null) {
			if (value < node.getKey()) {
				remove(node.getLeft(), node, value);
			} else if (value > node.getKey()) {
				remove(node.getRight(), node, value);
			} else {
				if (node.getLeft() == null && node.getRight() == null) {
					/* no son */
					if (parent != null && value < parent.getKey()) {
						parent.setLeft(null);
					} else if (parent != null && value > parent.getKey()) {
						parent.setRight(null);
					}
					if (root == node) {
						root = null;
					}
					reset(parent);
				} else if (node.getLeft() == null && node.getRight() != null) {
					/* right son */
					if (parent != null && value < parent.getKey()) {
						parent.setLeft(node.getRight());
					} else if (parent != null && value > parent.getKey()) {
						parent.setRight(node.getRight());
					}
					if (root == node) {
						root = node.getRight();
					}
					reset(parent);
				} else if (node.getLeft() != null && node.getRight() == null){
					/* left son */
					if (parent != null && value < parent.getKey()) {
						parent.setLeft(node.getLeft());
					} else if (parent != null && value > parent.getKey()) {
						parent.setRight(node.getLeft());
					}
					if (root == node) {
						root = node.getLeft();
					}
					reset(parent);
				} else {
					/* has two sons */
					AVLNode pare = node;
					AVLNode elem = node.getLeft();
					while (elem.getRight() != null) {
						pare = elem;
						elem = elem.getRight();
					}
					node.setKey(elem.getKey());
					if (pare == node) {
						pare.setLeft(null);
					} else {
						pare.setRight(null);
					}
					reset(pare);
				}
			}
		}
	}
	
	public boolean search(int value) {
		return search(root, null, value);
	}
	private boolean search(AVLNode node, AVLNode parent, int value) {
		if (node == null) {
			return false;
		} else {
			if (value < node.getKey()) {
				return search(node.getLeft(), node, value);
			} else if (value > node.getKey()) {
				return search(node.getRight(), node, value);
			} else {
				return true;
			}
		}
	}
}