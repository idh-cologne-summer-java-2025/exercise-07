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
        
        public boolean delete(int value) {
            return false; // Ursprüngliche Platzhalter-Implementierung
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
        if (root == null) {
            return false; 
        }
        
        
        if (root.value == value) {
            root = deleteNode(root);
            return true;
        }
        
       
        BinaryIntegerTreeNode parent = null;
        BinaryIntegerTreeNode current = root;
        
        
        while (current != null && current.value != value) {
            parent = current;
            if (value < current.value) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        
        if (current == null) {
            return false; 
        }
        
        
        if (parent.left == current) {
            parent.left = deleteNode(current);
        } else {
            parent.right = deleteNode(current);
        }
        
        return true;
    }
    
    
    private BinaryIntegerTreeNode deleteNode(BinaryIntegerTreeNode node) {
        
        if (node.left == null && node.right == null) {
            return null;
        }
        
       
        if (node.left == null) {
            return node.right;
        }
        if (node.right == null) {
            return node.left;
        }
        
       
        BinaryIntegerTreeNode minParent = node;
        BinaryIntegerTreeNode minNode = node.right;
        
        while (minNode.left != null) {
            minParent = minNode;
            minNode = minNode.left;
        }
        
        
        node.value = minNode.value;
        
        
        if (minParent == node) {
            minParent.right = minNode.right;
        } else {
            minParent.left = minNode.right;
        }
        
        return node;
    }
}
