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
		public int getValue() {
			return value;
		}
		public BinaryIntegerTreeNode getLeft() {
			return left;
		}
		public BinaryIntegerTreeNode getRight() {
			return right;
		}
		public BinaryIntegerTreeNode getBigL() {
			if((right!=null)&&(right.getRight()==null)&&(right.getLeft()==null)) {
				BinaryIntegerTreeNode temp = right;
				right = null;
				return temp;
			}
			else if((right!=null)) {
				return right.getBigL();
			}
			else return this;
			
		}

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
		
		public boolean delete(int value) {
			if (!contains(value))	return false;
			
			  
	               
	        if (left.getValue() == value) {
	           if((left.getLeft()== null)&&(left.getRight()== null)) {left=null; return true;}
	           if((left.getLeft()!= null)&&(left.getRight()== null)) {left=left.getLeft(); return true;}
	           if((left.getLeft()== null)&&(left.getRight()!= null)) {left=left.getRight(); return true;}
	           if((left.getLeft()!= null)&&(left.getRight()!= null)) {
	        	   left=left.getBigL(); 
	        	   return true;}
	           
	        }  else  if (right.getValue() == value) { 
	        	 if((right.getLeft()== null)&&(right.getRight()== null)) {right=null; return true;}
	        	 if((right.getLeft()!= null)&&(right.getRight()== null)) {right=right.getLeft(); return true;}
		         if((right.getLeft()== null)&&(right.getRight()!= null)) {right=right.getRight(); return true;}
		         if((right.getLeft()!= null)&&(right.getRight()!= null)) {
		        	   right=left.getBigL(); 
		        	   return true;}
	        }
	        else{
	            		if (left != null) return left.delete(value);
	            		if (right != null) return right.delete(value);
	            		
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
        return root != null && root.contains(value);
    }
	
	
	/**
	 * Löscht den übergebenen Wert aus dem Baum.
	 */
	public boolean delete(int value) {
		root.delete(value);
		return false;
	}

}
