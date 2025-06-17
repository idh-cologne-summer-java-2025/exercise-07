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
		 /** First, check if the tree even contains the value.
		 * Traverse the Tree node by node, until value is found:
		 * On each node: value < node -> go left -> repeat | value > node -> go right -> repeat
		 * On targetNode: no child -> targetNode = null | 1 child -> targetNode = child | 2 children -> targetNode compare -> targetNode = biggerCild
		 **/
		public boolean delete(int value) {
			//TODO: Implement
			if(this.contains(value)==true) { //check if tree even contains said value in the first place
				//Target node has 0 children
				if(value==right.value & right.right==null && right.left==null) {
					this.right = null;
					return true;
				} else {
					right.delete(value); //if targetNode is not the current node, nor are his children --> repeat
				}if(value==left.value & left.right == null && left.left == null) {
					this.left = null;
					return true;
				}else {
					left.delete(value);//if targetNode is not the this node, nor are his children --> repeat
				}
//				Target node has 1 child
				if(value==this.value & this.right != null || this.left != null) {
					if(this.left!=null) {
					this.value = left.value;
					left.left = null;
					return true;
					} else {
						this.value = right.value;
						this.right = null;
						return true;
					} 
				}	else {
					if(this.right !=null) {
						right.delete(value);
					} else { 
						left.delete(value);
				}
			}
//				TODO:Target Node has 2 children
				if(this.value == value && this.right != null & this.left != null) {
					
				}
		}
			return false;
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
        return root != null && root.contains(value);//but what does .contains by itself do?
    }
	
	
	/**
	 * Löscht den übergebenen Wert aus dem Baum. --> supposed to delete the root node..., 
	 */
	public boolean delete(int value) { //Why does the BIT itself also possess a delete method that is supposed to delete a single value?
		//TODO: Implement
		if(root == null) {
			return false;
		}
		if(!contains(value))
		return false;
		
		root.delete(value);
		return true;
	}
	
	}
