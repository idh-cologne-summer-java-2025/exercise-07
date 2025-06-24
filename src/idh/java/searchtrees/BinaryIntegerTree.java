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

        public boolean delete(int value) {
            if (value < this.value) {
                if (left != null) {
                    if (left.value == value) {
                                                if (left.left == null) {
                            left = left.right;
                        } else if (left.right == null) {
                            left = left.left;
                        } else {
                            BinaryIntegerTreeNode maxNode = left.left;
                            while (maxNode.right != null) {
                                maxNode = maxNode.right;
                            }
                            this.value = maxNode.value;
                            left.left.delete(maxNode.value);                         }
                        return true;
                    } else {
                        return left.delete(value);
                    }
                }
            } else if (value > this.value) {
                if (right != null) {
                    if (right.value == value) {
                        if (right.left == null && right.right == null) {
                            right = null;
                        } else if (right.left == null) {
                            right = right.right;
                        } else if (right.right == null) {
                            right = right.left;
                        } else {
                            BinaryIntegerTreeNode minNode = right.right;
                            while (minNode.left != null) {
                                minNode = minNode.left;
                            }
                                                        right.value = minNode.value;
                            right.right.delete(minNode.value);
                        }
                        return true;
                    } else {
                        return right.delete(value);
                    }
                }
            } else {
                if (left == null && right == null) {
                    return false;
                }
                if (left == null) {
                    this.value = right.value;
                    this.right = right.right;
                    this.left = right.left;
                } else if (right == null) {
                    this.value = left.value;
                    this.left = left.left;
                    this.right = left.right;
                } else {
                    BinaryIntegerTreeNode minNode = right;
                    while (minNode.left != null) {
                        minNode = minNode.left;
                    }
                    this.value = minNode.value;
                    right.delete(minNode.value);
                }
                return true;
            }
            return false;
        }
    }

    private BinaryIntegerTreeNode root;

    public boolean delete(int value) {
        if (root == null) {
            return false;
        } else {
                        return root.delete(value);
        }
    }

        public boolean addValue(int value) {
        if (root == null) {
            root = new BinaryIntegerTreeNode(value);
            return true;
        } else {
            return root.addValue(value);
        }
    }
}
