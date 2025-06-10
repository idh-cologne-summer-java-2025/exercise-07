package idh.java.searchtrees;

public class BinaryIntegerTree {

    class BinaryIntegerTreeNode {

        BinaryIntegerTreeNode left;
        BinaryIntegerTreeNode right;
        int value;

        public BinaryIntegerTreeNode(int value) {
            this.value = value;
        }

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
            System.out.println();
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

        DeleteResult result = deleteNode(root, value);
        root = result.node;
        return result.deleted;
    }

    private static class DeleteResult {
        BinaryIntegerTreeNode node;
        boolean deleted;

        DeleteResult(BinaryIntegerTreeNode node, boolean deleted) {
            this.node = node;
            this.deleted = deleted;
        }
    }

    private DeleteResult deleteNode(BinaryIntegerTreeNode current, int value) {
        if (current == null) {
            return new DeleteResult(null, false);
        }

        if (value < current.value) {
            DeleteResult result = deleteNode(current.left, value);
            current.left = result.node;
            return new DeleteResult(current, result.deleted);
        } else if (value > current.value) {
            DeleteResult result = deleteNode(current.right, value);
            current.right = result.node;
            return new DeleteResult(current, result.deleted);
        } else {
            // Knoten gefunden
            if (current.left == null && current.right == null) {
                return new DeleteResult(null, true); // Fall 1: Blatt
            } else if (current.left == null) {
                return new DeleteResult(current.right, true); // Fall 2: nur rechtes Kind
            } else if (current.right == null) {
                return new DeleteResult(current.left, true); // Fall 2: nur linkes Kind
            } else {
                // Fall 3: Zwei Kinder
                BinaryIntegerTreeNode successor = findMin(current.right);
                current.value = successor.value;
                DeleteResult result = deleteNode(current.right, successor.value);
                current.right = result.node;
                return new DeleteResult(current, true);
            }
        }
    }

    private BinaryIntegerTreeNode findMin(BinaryIntegerTreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}
