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
		
		BinaryIntegerTreeNode delete(int val) {
		    
// ----------------- if value is in the left subtree (the smaller side) -----------------
			
		    if (val < value) {
		        if (left != null) {
		            /**
		             * Continue searching in the left subtree recursively.
		             * We must update the left child reference, because the structure
		             * of the subtree might change after deletion.
		             */
		            left = left.delete(val);
		        }
		        return this; // return current node unchanged
		    }
		    
// ----------------- if value is in the right subtree -----------------------------------
		    else if (val > value) {
		        if (right != null) {
		            /**
		             * Continue searching in the right subtree recursively.
		             * Again, we must reassign the right child in case the subtree structure changes.
		             */
		            right = right.delete(val);
		        }
		        return this; // return current node unchanged
		    }

// ----------------- the to be deleted value == current node value → delete this node ---
		    else {
		    	
		        // Case 1 + 2: No left subtree or no subtree at all
		        if (left == null) {
		            /**
		             * No left child means we can safely replace this node
		             * with its right subtree (which may be null, which covers the first case at once!) 
		             * This handles leaf nodes too, since both children would be null.
		             */
		            return right;
		        }

		        // Case 1 + 2: No right subtree or no subtree at all)
		        if (right == null) {
		            /**
		             * No right child means we can safely replace this node
		             * with its left subtree (or null)
		             */
		            return left;
		        }

		        /**
		         * Case 3: Two Children
		         * We now have to handle the case where both left and right children exist.
		         * For that we find the in order successor, which is the smallest node
		         * in the right subtree (so the the next greater value).
		         */
		        BinaryIntegerTreeNode min = right;
		        while (min.left != null) {
		            min = min.left;  // move as far left as possible to find the minimum of the right subtree.
		        }

		        /**
		         * Replace the current node's value with the inorder successor's value.
		         * This keeps the binary search tree property intact.
		         */
		        value = min.value;

		        /**
		         * Now we have a duplicate of the successor node.
		         * We remove the original successor node from the right subtree.
		         * Since the successor node has at most one child (because it's the smallest node in that subtree), it's easy to remove:
                 * = if it has no children: it's a leaf → delete it.
                 * = if it has one child: bypass it by pointing its parent to its child.
		         */
		        right = right.delete(min.value);

		        return this; // return updated node
		    }
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
	public boolean delete(int val) {
	    if (root == null) return false;

	    
	    //Check if the value exists in the tree before deleting.
	    if (!contains(val)) return false;

	    /**
	     * Start deletion from the root. The root reference may be updated,
	     * especially if the root node itself is being deleted. The delete method
	     * in the node returns the updated subtree after deletion.
	     */
	    root = root.delete(val);

	    return true;
	}

}