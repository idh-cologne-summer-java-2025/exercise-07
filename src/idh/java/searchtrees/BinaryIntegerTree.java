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

        public BinaryIntegerTreeNode delete(int value) {
            //TODO: Implement

            // Value smaller thancurrent Knot
            if (value < this.value) {
                if (left != null) {
                    left = left.delete(value);
                }
            //Value greater than current Knot
            } else if (value > this.value) {
                if (right != null) {
                    right = right.delete(value);
                }
            //Value is equal to current Knot
            } else {
                if (left == null) {   // Right Child
                    return right;
                }
                if (right == null) { // Left Child
                    return left;
                }
            // two Childs
                BinaryIntegerTreeNode current = right;
                while (current.left != null) {
                    current = current.left;
                }
                this.value = current.value;  // replace current Value
                right = right.delete(current.value); //delete
            }
            return this; //current Knot
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
            // tree empty or no value
            if (root == null || !contains(value)) {
                return false;
            }
             // delete value
            root = root.delete(value);
            return true;
        }

    }






