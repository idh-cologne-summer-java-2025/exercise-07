package idh.java.searchtrees;

public class BinaryIntegerTree {

    class BinaryIntegerTreeNode {
        int value;
        BinaryIntegerTreeNode left, right;

        BinaryIntegerTreeNode(int value) {
            this.value = value;
        }
        

        boolean addValue(int newValue) {
            if (newValue == value) return false;
            if (newValue < value) {
                if (left == null) left = new BinaryIntegerTreeNode(newValue);
                else return left.addValue(newValue);
            } else {
                if (right == null) right = new BinaryIntegerTreeNode(newValue);
                else return right.addValue(newValue);
            }
            return true;
        }

        void printInOrder() {
            if (left != null) left.printInOrder();
            System.out.print(value + " ");
            if (right != null) right.printInOrder();
        }

        boolean contains(int searchValue) {
            if (searchValue == value) return true;
            return (searchValue < value ? left : right) != null
                && (searchValue < value ? left : right).contains(searchValue);
        }

        boolean delete(int val) {
        	
 // ----------------- if in left tree --------
        	// The value to delete is smaller, so we look in the left subtree,
    	    // since by BST rules all smaller values go left.
        	if (val < value) {
        	    if (left == null) {
        	        // Left subtree doesn’t exist, so the value isn’t in the tree.
        	        // Stop searching here and return false.
        	        return false;
        	    }
        	    if (left.value == val) {
        	        // We found the node to delete as the left child of current node.
        	        // Now handle deletion based on how many children this node has.

        	        if (left.left == null) {
        	            /* Case 1: No left child,
        	            * safe to replace node by its right subtree.
        	            * This works because all right subtree values are greater than deleted node.
        	            * Btw this also handles the case if the to be deleted node is a leaf! 
        	            * if the left is null, the node will be overwritten with the right subtree which is also null in this case!
        	            * so the node is just deleted. */
        	            left = left.right;  
        	        } 
        	        
        	        else if (left.right == null) {
        	            /* Case 2: No right child,
        	            * replace node by its left subtree,
        	            *preserving BST property since all left subtree values are smaller.*/
        	            left = left.left;   
        	        }
        	        
        	        else {
        	        	/* case 3:
        	        	 * Start from the right child of the left subtree node to delete,
        	        	 * because the successor (next larger value) is the smallest node
        	        	 * in that right subtree. (which contains the bigger values)
        	        	 */
        	        	BinaryIntegerTreeNode min = left.right;
        	        	/* 
        	        	 * Traverse down to the leftmost child of this right subtree to find the smallest value.
        	        	 * In a BST, left children are always smaller, so going left as far as possible
        	        	 * finds the minimum node.
        	        	 */
        	        	while (min.left != null) {
        	        	    min = min.left; /* Move one step further left */
        	        	}
        	        	/* 
        	        	 * Now 'min' points to the smallest node in the right subtree of the left child,
        	        	 * which is the inorder successor of the node we want to delete.
        	        	 * We use its value to replace the deleted node's value later,
        	        	 * preserving the binary search tree ordering.
        	        	 */

        	            // Replace the value of the node to delete with successor's value,
        	            // keeping the BST structure correct.
        	            left.value = min.value;
        	            // Now delete the successor node, which we just copied,
        	            // from the right subtree recursively.
        	            // The successor node has at most one child, so it´s easier to delete.
        	            left.delete(min.value);
        	        }
        	        return true;
        	    }
        	    // If the node to delete is not found at the left child,
        	    // continue searching the left subtree recursively.
        	    return left.delete(val);
        	    
  // ------------------ if in right tree --------------
        	    
        	    //Mirror of above logic for the right subtree.
        	    //Value to delete is greater, so check the right subtree.
        	} else if (val > value) {
        	    if (right == null) {
        	        // Right subtree empty, the value is not found here.
        	        return false;
        	    }
        	    if (right.value == val) {
        	        // if the Node to delete was found as right child.
        	        if (right.left == null) {
        	            // case 1: replace node by its right subtree. 
        	            right = right.right;
        	        } else if (right.right == null) {
        	            // case 2: replace node by its left subtree.
        	            right = right.left;
        	        } else {
        	            /* case 3:
        	             * Start from the right child of the node to delete, because 
        	             * the successor (next larger value) is the smallest node 
        	             * in the right subtree. (which contains the bigger values)
        	             */
        	            BinaryIntegerTreeNode min = right.right;
        	            /* 
        	             * Traverse down to the leftmost child of this subtree to find the smallest value.
        	             * In a BST, left children are always smaller, so going left as far as possible
        	             * finds the minimum node.
        	             */
        	            while (min.left != null) {
        	                min = min.left; /* Move one step further left */
        	            }
        	            /* 
        	             * Now 'min' points to the smallest node in the right subtree,
        	             * which is the inorder successor of the node we want to delete.
        	             * We use its value to replace the deleted node's value later,
        	             * preserving the binary search tree ordering.
        	             */
        	            
        	            // Replace the value of the node to delete with successor's value,
        	            // keeping the BST structure correct.
        	            right.value = min.value;
        	            /* Now delete the successor node, which we just copied,
        	            * from the right subtree recursively.
        	            * The successor node has at most one child, so it´s easier to delete.*/
        	            right.delete(min.value);
        	        }
        	        return true;
        	    }
        	    // Continue searching right subtree recursively if node not found here
        	    return right.delete(val);
        	}
        	return false;
    }
}

    private BinaryIntegerTreeNode root;

    public boolean addValue(int value) {
        if (root == null) root = new BinaryIntegerTreeNode(value);
        else return root.addValue(value);
        return true;
    }

    public void printInOrder() {
        if (root != null) root.printInOrder();
        else System.out.println("Der Baum ist leer.");
    }

    public boolean contains(int value) {
        return root != null && root.contains(value);
    }

    public boolean delete(int value) {
        if (root == null) return false; // No nodes to delete in an empty tree
        
// ----------- root is to be deleted --------- (note: the code works just like above)
     
        if (root.value == value) // This handles the special case: the root is the node to delete!
        {                        
            if (root.left == null) // root has no left child: replace root with right subtree
            {
            	// case 1
            	root = root.right;
            }  else if (root.right == null) { // root has no right child: replace root with left subtree
            	// case 2
            	root = root.left; 
            } else { 
            	// case 3
                BinaryIntegerTreeNode min = root.right;  // find smallest node in right subtree (successor)
                while (min.left != null) 
                {
                	min = min.left; // Move left to find the smallest value in the right subtree
                }
                root.value = min.value;
                root.delete(min.value);
            }
            return true; // Deletion done for the root node! Only the root node!
        }
        return root.delete(value); // Delegate deletion of non-root nodes recursively! -> So if the deleted node is not the root!
    }
}
