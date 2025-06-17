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

        public BinaryIntegerTreeNode delete(int value) {
            if (value < this.value) {
                // Gesuchter Wert ist kleiner, also links suchen
                if (left != null) {
                    left = left.delete(value);
                }
            } else if (value > this.value) {
                // Wert ist größer, rechts suchen
                if (right != null) {
                    right = right.delete(value);
                }
            } else {
                // Hier sind wir: Knoten gefunden, den wir löschen wollen!

                // Fall 1: Kein Kind - einfach wegwerfen
                if (left == null && right == null) {
                    return null;
                }

                // Fall 2: Nur ein Kind - Kind rückt nach
                if (left == null) {
                    return right;
                }
                if (right == null) {
                    return left;
                }

                // Fall 3: Zwei Kinder - wir müssen den kleinsten Wert im rechten Teilbaum holen
                int minRight = right.findMinValue();
                this.value = minRight; // Wert austauschen
                // Und den kleinsten Wert aus dem rechten Teilbaum löschen
                right = right.delete(minRight);
            }
            return this; // Knoten (ggf. aktualisiert) zurückgeben
        }

        public int findMinValue() {
            if (left == null) {
                return this.value;
            } else {
                return left.findMinValue();
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
     * Soll den Baum in der sortierten Reihenfolge ausgeben
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
        if (root == null) {
            // Baum ist leer, da kann nix gelöscht werden
            return false;
        }
        // Schauen, ob der Wert überhaupt da ist
        boolean found = root.contains(value);
        if (found) {
            // Wenn ja, dann löschen wir den Wert und passen den Baum an
            root = root.delete(value);
        }
        return found;
    }

}
