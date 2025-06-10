package idh.java.searchtrees;

/**
 * Klasse zum Speichern von int-Werten in einem sortierten Binärbaum
 */
public class BinaryIntegerTree {

    /**
     * Innere Klasse, die einen int-Binärbaumknoten repräsentiert
     */
    class BinaryIntegerTreeNode {

        BinaryIntegerTreeNode left;
        BinaryIntegerTreeNode right;
        int value;

        public BinaryIntegerTreeNode(int value) {
            this.value = value;
        }

        /**
         * Rekursive Methode für das Hinzufügen von int-Werten
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
            } else {
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

        /**
         * Methode zum Löschen eines Wertes aus dem Teilbaum
         */
        private BinaryIntegerTreeNodeWrapper deleteNode(int value) {
            if (value < this.value) {
                if (left != null) {
                    BinaryIntegerTreeNodeWrapper result = left.deleteNode(value);
                    left = result.node;
                    return new BinaryIntegerTreeNodeWrapper(this, result.deleted);
                } else {
                    return new BinaryIntegerTreeNodeWrapper(this, false);
                }
            } else if (value > this.value) {
                if (right != null) {
                    BinaryIntegerTreeNodeWrapper result = right.deleteNode(value);
                    right = result.node;
                    return new BinaryIntegerTreeNodeWrapper(this, result.deleted);
                } else {
                    return new BinaryIntegerTreeNodeWrapper(this, false);
                }
            } else {
                // Knoten gefunden
                if (left == null && right == null) {
                    return new BinaryIntegerTreeNodeWrapper(null, true);
                } else if (left == null) {
                    return new BinaryIntegerTreeNodeWrapper(right, true);
                } else if (right == null) {
                    return new BinaryIntegerTreeNodeWrapper(left, true);
                } else {
                    BinaryIntegerTreeNode successor = right.findMin();
                    this.value = successor.value;
                    BinaryIntegerTreeNodeWrapper result = right.deleteNode(successor.value);
                    right = result.node;
                    return new BinaryIntegerTreeNodeWrapper(this, true);
                }
            }
        }

        private BinaryIntegerTreeNode findMin() {
            BinaryIntegerTreeNode current = this;
            while (current.left != null) {
                current = current.left;
            }
            return current;
        }
    }

    /**
     * Hilfsklasse zur Rückgabe von gelöschtem Knoten + Status
     */
    private class BinaryIntegerTreeNodeWrapper {
        BinaryIntegerTreeNode node;
        boolean deleted;

        BinaryIntegerTreeNodeWrapper(BinaryIntegerTreeNode node, boolean deleted) {
            this.node = node;
            this.deleted = deleted;
        }
    }

    /**
     * Die Wurzel des Baums
     */
    private BinaryIntegerTreeNode root;

    /**
     * Hinzufügen eines Wertes
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
     * In-Order-Ausgabe des Baumes
     */
    public void printInOrder() {
        if (root != null) {
            root.printInOrder();
        } else {
            System.out.println("Der Baum ist leer.");
        }
    }

    /**
     * Prüft, ob ein Wert enthalten ist
     */
    public boolean contains(int value) {
        return root != null && root.contains(value);
    }

    /**
     * Löscht einen Wert aus dem Baum
     */
    public boolean delete(int value) {
        if (root == null) {
            return false;
        }

        BinaryIntegerTreeNodeWrapper result = root.deleteNode(value);
        if (result.deleted) {
            root = result.node;
            return true;
        } else {
            return false;
        }
    }
}