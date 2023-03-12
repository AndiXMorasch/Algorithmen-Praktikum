public class RedBlackTree {
    private RBTNode nil = new RBTNode(-1, "nil");
    private RBTNode root;

    // Konstruktor mit Standardwurzelzuweisung = nil
    // sowie Zuweisung des Farbwertes schwarz (false) an alle nil Knoten
    public RedBlackTree() {
        root = nil;
        nil.color = RBTNode.black;
    }

    // Methode zum Einfügen neuer Knoten in den RB-Baum
    public void insert(int k, String s) {
        RBTNode z = new RBTNode(k, s);
        RBTNode y = nil;
        RBTNode x = root;

        while(x != nil) {
            y = x;
            if (z.key < x.key) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        z.parent = y;

        if(y == nil) {
            root = z;
        }
        else if (z.key < y.key){
            y.left = z;
        }
        else {
            y.right = z;
        }
        z.left = nil;
        z.right = nil;
        z.color = RBTNode.red;
        rb_Insert_Fixup(z);
    }

    // Hilfsmethode zum Reparieren des RB-Baumes, falls die 2. und oder 4. Eigenschaft verletzt wurde
    private void rb_Insert_Fixup(RBTNode z) {
        while(z.parent.color == RBTNode.red) {
            if (z.parent == z.parent.parent.left) {
                RBTNode y = z.parent.parent.right;
                if(y.color == RBTNode.red) {
                    z.parent.color = RBTNode.black;
                    y.color = RBTNode.black;
                    z.parent.parent.color = RBTNode.red;
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.right) {
                        z = z.parent;
                        left_Rotate(z);
                    }
                    z.parent.color = RBTNode.black;
                    z.parent.parent.color = RBTNode.red;
                    right_Rotate(z.parent.parent);
                }
            // Analog zu oberer if-Abfrage mit left und right vertauscht
            } else {
                if (z.parent == z.parent.parent.right) {
                    RBTNode y = z.parent.parent.left;
                    if(y.color == RBTNode.red) {
                        z.parent.color = RBTNode.black;
                        y.color = RBTNode.black;
                        z.parent.parent.color = RBTNode.red;
                        z = z.parent.parent;
                    } else {
                        if (z == z.parent.left) {
                            z = z.parent;
                            right_Rotate(z);
                        }
                        z.parent.color = RBTNode.black;
                        z.parent.parent.color = RBTNode.red;
                        left_Rotate(z.parent.parent);
                    }
                }
            }
        }
        root.color = RBTNode.black;
    }

    // Hilfsmethode zur Linksrotation
    private void left_Rotate(RBTNode z) {
        if(z.right == nil) {
            return;
        }
        RBTNode y = z.right;
        z.right = y.left;
        if(y.left != nil) {
            y.left.parent = z;
        }
        y.parent = z.parent;
        if(z.parent == nil) {
            root = y;
        } else if (z == z.parent.left) {
            z.parent.left = y;
        } else {
            z.parent.right = y;
        }
        y.left = z;
        z.parent = y;
    }

    // Hilfsmethode zur Rechtsrotation
    // (analog zur Hilfsmethode "left_Rotate" mit dem Unterschied, dass left mit right vertauscht wurde)
    private void right_Rotate(RBTNode z) {
        if(z.left == nil) {
            return;
        }
        RBTNode y = z.left;
        z.left = y.right;
        if(y.right != nil) {
            y.right.parent = z;
        }
        y.parent = z.parent;
        if(z.parent == nil) {
            root = y;
        } else if (z == z.parent.right) {
            z.parent.right = y;
        } else {
            z.parent.left = y;
        }
        y.right = z;
        z.parent = y;
    }

    // Methode zur Berechnung der Höhe des RB-Baumes
    public int height() {
        return height_Recursive(root);
    }

    // Quelle: https://stackoverflow.com/questions/2597637/finding-height-in-binary-search-tree
    // Rekursive Methode zur Berechnung der Höhe des RB-Baumes
    private int height_Recursive(RBTNode node) {
        if (node == null) {
            return -1;
        }
        int leftheight = height_Recursive(node.left);
        int rightheight = height_Recursive(node.right);

        if (leftheight > rightheight) {
            return leftheight + 1;
        } else {
            return rightheight + 1;
        }
    }

    // Quelle: https://stackoverflow.com/questions/2597637/finding-height-in-binary-search-tree
    // Rekursive Methode zur Berechnung der Schwarzhöhe des RB-Baumes
    private int black_Height_Recursive(RBTNode node) {
        if (node == nil) {
            return 1;
        }
        int leftheight = black_Height_Recursive(node.left);
        int rightheight = black_Height_Recursive(node.right);

        if (leftheight != rightheight) {
            return -1;
        }
        if(node.color == RBTNode.black) {
            return leftheight+1;
        } else {
            return leftheight;
        }
    }

    // Methode zum Suchen eines bestimmten Strings mithilfe des eindeutigen Schlüssels
    public String search(int k) {
        return search_Recursive(root, k).val;
    }

    // Quelle: https://www.softwaretestinghelp.com/binary-search-tree-in-java/
    // Rekursive Suchmethode zum Suchen des Strings eines bestimmten Knotens
    private RBTNode search_Recursive(RBTNode node, int key) {
        // Knoten ist null oder der gesuchte Schlüssel befindet sich im Knoten
        if (node == null || node.key == key)
            return node;
        // Gesuchter Schlüssel ist kleiner als der Schlüssel des Knotens
        if (node.key > key)
            return search_Recursive(node.left, key);
        // Gesuchter Schlüssel größer als der Schlüssel des Knotens
        return search_Recursive(node.right, key);
    }

    // Methode zur Prüfung der Gültigkeit des BST
    public boolean checkRB() {
        int untergrenze = Integer.MIN_VALUE;
        int obergrenze = Integer.MAX_VALUE;
        // 5. Regel: Falsch, wenn die Schwarzhöhe des Baumes (von der Wurzel betrachtet) zu jedem Blatt nicht immer gleich ist
        if(black_Height_Recursive(root) != -1) {
            return checkRB_Recursive(root, untergrenze, obergrenze);
        }
        return false;
    }

    // Quelle: https://www.geeksforgeeks.org/a-program-to-check-if-a-binary-tree-is-bst-or-not/
    // Hilfsmethode zur Prüfung der Gültigkeit des BST
    private boolean checkRB_Recursive(RBTNode node, int untergrenze, int obergrenze) {

        // Prüfung ob node überhaupt vorhanden / initialisiert, ist
        if (node == nil) {
            return true;
        }
        // Falsch wenn left.key > als node.key
        if (node.key < untergrenze || node.key > obergrenze) {
            return false;
        }
        // 1. Regel: Falsch wenn der Knoten weder schwarz noch rot ist
        if(node.color == null) {
            return false;
        }
        // 2. Regel: Falsch wenn die Wurzel nicht schwarz ist
        if(root.color != RBTNode.black) {
            return false;
        }
        // 3. Regel: Falsch wenn der NIL Knoten nicht schwarz ist
        if((node.val.equals("nil")) && (node.color != RBTNode.black)) {
            return false;
        }
        // 4. Regel: Falsch wenn die Kindknoten des nodes nicht schwarz sind
        if((node.color == RBTNode.red) && ((node.left.color && node.right.color) != RBTNode.black)) {
            return false;
        }
        // Ansonsten rekursiver Aufruf mit veränderten Ober- bzw. Untergrenzen
        return (checkRB_Recursive(node.left, untergrenze, node.key-1) &&
                checkRB_Recursive(node.right, node.key+1, obergrenze));
    }

    // Quelle: https://www.techiedelight.com/deletion-from-bst/
    // Hilfsmethode zum Ermitteln des kleinsten Schlüssels / Blattes (ganz links)
    // (wird bei der Manipulation des Baumes benötigt)
    public void getLeftLeaf() {
        RBTNode curr = root;
        while (curr.left != null) {
            curr = curr.left;
        }
        curr.color = RBTNode.red;
    }
}
