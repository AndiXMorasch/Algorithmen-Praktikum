public class BST {

    // Deklaration des Wurzelknotens vom Typ BSTNode
    private BSTNode root;

    // Konstruktor mit Initialwert "null" für den Wurzelknoten
    public BST() {
        root = null;
    }

    // Quelle: https://www.softwaretestinghelp.com/binary-search-tree-in-java/
    // Methode zum Einfügen von Knoten
    public void insert(int k, String s) {
        root = insert_Recursive(root, k, s);
    }

    // Quelle: https://www.softwaretestinghelp.com/binary-search-tree-in-java/
    // Rekursive Hilfsmethode zum Einfügen von Knoten
    private BSTNode insert_Recursive(BSTNode node, int key, String data) {

        // Prüft ob der Baum leer ist
        if (node == null) {
            return new BSTNode(key, data);
        }
        // Traversierung durch den Baum
        if (key < node.key) {     //insert in the left subtree
            node.left = insert_Recursive(node.left, key, data);
            node.left.parent = node;
        }else if (key > node.key) {    //insert in the right subtree
            node.right = insert_Recursive(node.right, key, data);
            node.right.parent = node;
        }
        return node;
    }

    public String search(int k) {
        return search_Recursive(root, k).val;
    }

    // Quelle: https://www.softwaretestinghelp.com/binary-search-tree-in-java/
    // Rekursive Suchmethode zum Suchen des Strings eines bestimmten Knotens
    private BSTNode search_Recursive(BSTNode node, int key) {
        // Knoten ist null oder der gesuchte Schlüssel befindet sich im Knoten
        if (node == null || node.key == key)
            return node;
        // Gesuchter Schlüssel ist kleiner als der Schlüssel des Knotens
        if (node.key > key)
            return search_Recursive(node.left, key);
        // Gesuchter Schlüssel größer als der Schlüssel des Knotens
        return search_Recursive(node.right, key);
    }

    public int height() {
        return height_Recursive(root);
    }

    // Quelle: https://stackoverflow.com/questions/2597637/finding-height-in-binary-search-tree
    // Rekursive Methode zur Berechnung der Höhe
    private int height_Recursive(BSTNode node) {
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

    public boolean isValidBST() {
        int untergrenze = Integer.MIN_VALUE;
        int obergrenze = Integer.MAX_VALUE;
        return isValidBST_Recursive(root, untergrenze, obergrenze);
    }

    // Quelle: https://www.geeksforgeeks.org/a-program-to-check-if-a-binary-tree-is-bst-or-not/
    // Methode zur Prüfung der Gültigkeit des BST
    private boolean isValidBST_Recursive(BSTNode node, int untergrenze, int obergrenze) {

        // Prüfung ob node überhaupt vorhanden / initialisiert, ist...
        if (node == null) {
            return true;
        }
        // Falsch wenn left.key > als node.key
        if (node.key < untergrenze || node.key > obergrenze) {
            return false;
        }
        // Ansonsten rekursiver Aufruf mit veränderten Ober- bzw. Untergrenzen
        return (isValidBST_Recursive(node.left, untergrenze, node.key-1) &&
                isValidBST_Recursive(node.right, node.key+1, obergrenze));
    }

    // Quelle: https://www.techiedelight.com/deletion-from-bst/
    // Methode zum Entfernen eines Knotens k
    public void remove(int k) throws Exception {
        // Hilfsvariable zum Zwischenspeichern des Parent-Knotens
        BSTNode parent = null;

        // Suche nach dem Schlüssel im BST
        BSTNode curr = search_Recursive(root, k);

        // Exception; falls der gesuchte Knoten nicht gefunden wurde
        if (curr == null) {
            throw new Exception("Der gesuchte Knoten konnte nicht gefunden werden!");
        }

        // Aufruf der Methode welche die drei verschiedenen Fälle prüft
        removeDreiFaellePruefung(curr, k);
    }

    // Quelle: https://www.techiedelight.com/deletion-from-bst/
    // Hilfsmethode zum Ermitteln des kleinsten Schlüssels / Blattes (ganz links)
    public static BSTNode getMinimumKey(BSTNode curr) {
        while (curr.left != null) {
            curr = curr.left;
        }
        return curr;
    }

    // Quelle: https://www.techiedelight.com/deletion-from-bst/
    // Hilfsmethode zur Prüfung der drei möglichen auftretenden Fälle
    private void removeDreiFaellePruefung(BSTNode curr, int k) throws Exception {

        // 1. Fall: Knoten hat keine Nachfolger. Daraus folgt: Es handelt sich um ein Blatt
        if (curr.left == null && curr.right == null) {
            // Wenn es sich bei diesem Knoten nicht um den Wurzelknoten handelt, dann setze die Kinder-Knoten des Parent-Knotens auf "null"
            if (curr != root) {
                if (curr.parent.left == curr) {
                    curr.parent.left = null;
                } else {
                    curr.parent.right = null;
                }
            }
            // Wenn es sich doch um einen Wurzelknoten handelt, dann setze diesen auf "null"
            else {
                root = null;
            }

            // 2. Fall: Knoten hat zwei Nachfolger
        } else if (curr.left != null && curr.right != null) {
            // Finde den inorder Nachfolger des rechten Kindknotens des aktuellen curr-Knotens
            BSTNode successor = getMinimumKey(curr.right);

            // Rekursiver Aufruf der remove Methode (diesmal mit dem Schlüssel des inorder Nachfolgers)
            remove(successor.key);

            // Überschreibe den Schlüssel des aktuellen Knotens mit dem Schlüssel des inorder Nachfolgers
            curr.key = successor.key;
            curr.val = successor.val;

            // 3. Fall: Knoten hat genau einen Nachfolger
        } else {
            // Auswahl eines Kind-Knotens: (if curr.left != null) -> true, dann nimm das left-Child ansonsten nimm das right-Child
            BSTNode child = (curr.left != null) ? curr.left : curr.right;

            // Falls der zu löschende Knoten nicht der Wurzelknoten ist, setze sein Parent-Knoten auf den des Kindes
            if (curr != root) {
                if (curr == curr.parent.left) {
                    curr.parent.left = child;
                } else {
                    curr.parent.right = child;
                }
            }
            // Wenn der zu löschende Knoten ein Wurzelknoten ist, dann setze den Wurzelknoten auf den Kindknoten
            else {
                root = child;
            }
        }
    }

    // Methode zur Manipulation eines Baumes
    void manipulator() {
        // Vorgehen: Linker Knoten(key) der Wurzel wird auf eine Zahl gesetzt die gar nicht infrage kommen kann,
        // und einen fehlerhaften Binärbaum verursacht
        root.left.setKey(10001);
    }
}



