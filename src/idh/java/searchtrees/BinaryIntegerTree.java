package idh.java.searchtrees;

public class BinaryIntegerTree {

    class BinaryIntegerTreeNode {

        public BinaryIntegerTreeNode(int value) {
            this.value = value;
        }

        BinaryIntegerTreeNode left;
        BinaryIntegerTreeNode right;
        int value;

        public boolean addValue(int newValue) {
            if (newValue == value) {
                return false;
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

        // Hilfsklasse für Lösch-Ergebnis
        private class DeleteResult {
            BinaryIntegerTreeNode node;
            boolean deleted;

            DeleteResult(BinaryIntegerTreeNode node, boolean deleted) {
                this.node = node;
                this.deleted = deleted;
            }
        }

        // Findet kleinsten Wert im Teilbaum
        private int findMin() {
            BinaryIntegerTreeNode current = this;
            while (current.left != null) {
                current = current.left;
            }
            return current.value;
        }

        // Die eigentliche Löschlogik
        private DeleteResult deleteRecursive(int target) {
            if (target < value) {
                if (left != null) {
                    DeleteResult result = left.deleteRecursive(target);
                    left = result.node;
                    return new DeleteResult(this, result.deleted);
                } else {
                    return new DeleteResult(this, false);
                }
            } else if (target > value) {
                if (right != null) {
                    DeleteResult result = right.deleteRecursive(target);
                    right = result.node;
                    return new DeleteResult(this, result.deleted);
                } else {
                    return new DeleteResult(this, false);
                }
            } else {
                // Wert gefunden
                if (left == null && right == null) {
                    return new DeleteResult(null, true); // Fall 1: Blatt
                } else if (left == null) {
                    return new DeleteResult(right, true); // Fall 2: nur rechts
                } else if (right == null) {
                    return new DeleteResult(left, true); // Fall 2: nur links
                } else {
                    // Fall 3: Zwei Kinder
                    int smallest = right.findMin();
                    value = smallest;
                    DeleteResult result = right.deleteRecursive(smallest);
                    right = result.node;
                    return new DeleteResult(this, true);
                }
            }
        }

        // Öffentliche Methode wird nicht direkt verwendet
        public boolean delete(int value) {
            return false; // wird über BinaryIntegerTree aufgerufen
        }
    }

    private BinaryIntegerTreeNode root;

    public boolean addValue(int value) {
        if (root == null) {
            root = new BinaryIntegerTreeNode(value);
            return true;
        } else {
            return root.addValue(value);
        }
    }

    public void printInOrder() {
        if (root != null) {
            root.printInOrder();
        } else {
            System.out.println("Der Baum ist leer.");
        }
    }

    public boolean contains(int value) {
        return root != null && root.contains(value);
    }

    public boolean delete(int value) {
        if (root == null) {
            return false;
        }
        BinaryIntegerTreeNode.DeleteResult result = root.deleteRecursive(value);
        root = result.node;
        return result.deleted;
    }
}
