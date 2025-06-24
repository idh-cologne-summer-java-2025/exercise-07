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
         * @param newValue int-Wert
         * @return true, wenn Wert hinzugefügt wurde, false, wenn er schon vorhanden war
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

        
        public boolean delete(int value, BinaryIntegerTreeNode parent) {
            if (value < this.value) {
                if (left != null) {
                    return left.delete(value, this);
                } else {
                    return false;
                }
            } else if (value > this.value) {
                if (right != null) {
                    return right.delete(value, this);
                } else {
                    return false;
                }
            } else {
               
                if (left != null && right != null) {
                    this.value = right.findMin().value;
                    right.delete(this.value, this);
                } else if (parent.left == this) {
                    parent.left = (left != null) ? left : right;
                } else if (parent.right == this) {
                    parent.right = (left != null) ? left : right;
                }
                return true;
            }
        }

        private BinaryIntegerTreeNode findMin() {
            if (left == null) {
                return this;
            } else {
                return left.findMin();
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
     * @return true, wenn Wert hinzugefügt wurde, false, wenn er schon vorhanden war
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
     * Gibt den Baum in sortierter Reihenfolge aus
     */
    public void printInOrder() {
        if (root != null) {
            root.printInOrder();
            System.out.println();
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
        if (root == null) return false;

        if (root.value == value) {
            // Spezialfall: root selbst löschen
            BinaryIntegerTreeNode dummy = new BinaryIntegerTreeNode(0);
            dummy.left = root;
            boolean result = root.delete(value, dummy);
            root = dummy.left;
            return result;
        } else {
            return root.delete(value, null);
        }
    }
}
