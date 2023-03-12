class BSTNode {
    public int key;
    public String val;
    public BSTNode left, right, parent;

    public BSTNode(int k, String s) {
        this.key = k;
        this.val = s;
        this.left = null;
        this.right = null;
        this.parent = null;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public BSTNode getLeft() {
        return left;
    }

    public void setLeft(BSTNode left) {
        this.left = left;
    }

    public BSTNode getRight() {
        return right;
    }

    public void setRight(BSTNode right) {
        this.right = right;
    }

    public BSTNode getParent() {
        return parent;
    }

    public void setParent(BSTNode parent) {
        this.parent = parent;
    }

}


