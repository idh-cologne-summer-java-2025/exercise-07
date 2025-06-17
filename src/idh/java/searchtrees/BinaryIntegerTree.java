package idh.java.searchtrees;

/**
 * Klasse zum Speichern von int-Werten in einem sortierten Binärbaum
 */
public class BinaryIntegerTree {

	/**
	 * Innere Klasse, die einen int-Binärbaumknoten repräsentiert
	 */
	class BinaryIntegerTreeNode {

		public BinaryIntegerTreeNode(int value) {
			this.value = value;
		}

		BinaryIntegerTreeNode left;
		BinaryIntegerTreeNode right;
		int value;

		/**
		 * Rekursive Methode für das Hinzufügen von int-Werten
		 * 
		 * @param value int-Wert
		 * @return true, wenn Wert hinzugefügt wurde, false, wenn er schon vorhandem war
		 */
		public boolean addValue(int newValue) {
            if (newValue == value) {
                return false; // Wert bereits vorhanden
            } else if (newValue < value) {
                if (left == null) {
                    left = new BinaryIntegerTreeNode(newValue);
                    return true;
                } else {
                    return left.addValue(newValue);
                }
            } else { // newValue > value
                if (right == null) {
                    right = new BinaryIntegerTreeNode(newValue);
                    return true;
                } else {
                    return right.addValue(newValue);
                }
            }
        }

        public void printInOrder() {
            if (left != null) {
                left.printInOrder();
            }
            System.out.print(value + " ");
            if (right != null) {
                right.printInOrder();
            }
        }

		public boolean contains(int searchValue) {
            if (searchValue == value) {
                return true;
            } else if (searchValue < value) {
                return (left != null) && left.contains(searchValue);
            } else {
                return (right != null) && right.contains(searchValue);
            }
        }
		
	    BinaryIntegerTreeNode deleteNode(int val) {
	        if (val < value) {
	            left = left.deleteNode(val); // Search in the left subtree
	        } else if (val > value) {
	            right = right.deleteNode(val); // Search in the right subtree
	        } else {
	            // Node to be deleted found
	            if (left == null) {
	                return right; // Replace with right child if left is null
	            } else if (right == null) {
	                return left; // Replace with left child if right is null
	            }
	            // Node with two children: Get the in-order successor (smallest in the right subtree)
	            BinaryIntegerTreeNode min = right;
	            while (min.left != null) {
		            min = min.left;  // move as far left as possible to find the minimum of the right subtree.
		        }
               	value = min.value;
 	            // Replace with right child
	            right = right.deleteNode(min.value);
	        }
	        return this;
	    }
}	

	/**
	 * Die Wurzel des Baums
	 */
	private BinaryIntegerTreeNode root;

	/**
	 * Methode für das Hinzufügen von int-Werten in den Baum
	 * 
	 * @param value int-Wert
	 * @return true, wenn Wert hinzugefügt wurde, false, wenn er schon vorhandem war
	 */
	public boolean addValue(int value) {
        if (root == null) {
            root = new BinaryIntegerTreeNode(value);
            return true;
        } else {
            return root.addValue(value);
        }
    }

	/**
	 * Soll den Baum in der sortierten Reihenfolge ausgeben
	 */
	public void printInOrder() {
        if (root != null) {
            root.printInOrder();
        } else {
            System.out.println("Der Baum ist leer.");
        }
        System.out.println();
    }

	/**
	 * Überprüft, ob der spezifizierte Wert enthalten ist.
	 */
	public boolean contains(int value) {
        return root != null && root.contains(value);
    }
	
	
	/**
	 * Löscht den übergebenen Wert aus dem Baum.
	 */
	 public boolean delete(int value) {
	        if (root == null) {
	            return false; // Tree is empty
	        }
	        if (!contains(value)) {
	        	return false; // Element is not in the tree
	        }
	        root.deleteNode(value);
	        return true;    
    }
}
