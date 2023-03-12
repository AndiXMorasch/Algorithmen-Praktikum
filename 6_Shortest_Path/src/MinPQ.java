// *** Alle Methoden ohne Quellenangabe habe ich aus den Vorlesungsfolien abgeleitet ***

public class MinPQ {

    private PQElement[] heap;
    private int maxsize; // analog zu length
    private int currentsize; // analog zu heap-size

    // Quelle: https://www.geeksforgeeks.org/min-heap-in-java/
    public MinPQ(int max) {
        this.maxsize = max;
        this.currentsize = 0;
        heap = new PQElement[max+1];
    }

    // Getter für den Heap (benötige ich bei der Hilfsklasse)
    public PQElement[] getHeap() {
        return heap;
    }

    // Getter für die currentsize (benötige ich bei der Hilfsklasse)
    public int getCurrentsize() { return currentsize;}

    // Quelle: https://www.geeksforgeeks.org/min-heap-in-java/
    // Methode zum Herausfinden der Position des Elternelementes
    private int parent(int pos) {
        return pos / 2;
    }

    // Quelle: https://www.geeksforgeeks.org/min-heap-in-java/
    // Methode zum Herausfinden des linken Kindelementes
    private int leftChild(int pos) {
        return (2 * pos);
    }

    // Quelle: https://www.geeksforgeeks.org/min-heap-in-java/
    // Methode zum Herausfinden des rechten Kindelementes
    private int rightChild(int pos) {
        return (2 * pos) + 1;
    }

    // Methode zur Prüfung ob die Priority Queue leer ist
    public boolean isEmpty() {
        if (currentsize < 1) {
            return true;
        }
        return false;
    }

    // Methode zum Einfügen eines bestehenden Objektes (Typ: PQElement) in die Priority Queue
    public boolean insert(PQElement n) throws Exception {
        // Pruefung ob die Array voll ist
        if (currentsize == maxsize) {
            throw new Exception("heap overflow");
        }
        currentsize = currentsize + 1;
        heap[currentsize] = n;
        int i = currentsize;

        bedingungZumVertauschen(i);

        return true;
    }

    // Methode zum Einfügen eines neuen Objektes (Typ: PQElement) in die Priority Queue
    public boolean insert(String d, double p) throws Exception {
        // Pruefung ob die Array voll ist
        if (currentsize == maxsize) {
            throw new Exception("heap overflow");
        }
        currentsize = currentsize + 1;
        heap[currentsize] = new PQElement(d, p);
        int i = currentsize;

        bedingungZumVertauschen(i);

        return true;
    }

    public void bedingungZumVertauschen(int i) {
        while (i > 1 && heap[parent(i)].getPriority() > heap[i].getPriority()) {
            vertausche(i, parent(i));
            i = parent(i);
        }
    }

    // Methode zum Entfernen (und zurückgeben) des Elementes mit der geringsten Prioritaet
    public PQElement extractElement() throws Exception {

        if (currentsize == maxsize) {
            throw new Exception("heap overflow");
        }
        PQElement min = heap[1];
        heap[1] = heap[currentsize];
        currentsize = currentsize - 1;
        minHeapify(1);
        return min;
    }

    // Methode zum Entfernen des Elementes mit der geringsten Prioritaet und Rückgabe dessen Wert bzw. String
    public String extractData() throws Exception {
        if (currentsize < 1) {
            throw new Exception("heap underflow");
        }
        PQElement min = heap[1];
        heap[1] = heap[currentsize];
        currentsize = currentsize - 1;
        minHeapify(1);
        return min.getData();
    }

    // Quelle: https://www.geeksforgeeks.org/min-heap-in-java/
    // Methode zum Vertauschen zweier Objekte an verschiedenen Positionen
    private void vertausche(int fpos, int spos) {
        PQElement tmp;
        tmp = heap[fpos];
        heap[fpos] = heap[spos];
        heap[spos] = tmp;
    }

    // Methode zur Wiederherstellung des Min-Heaps
    private void minHeapify(int i) {
        int l = leftChild(i);
        int r = rightChild(i);
        int smallest = 0;

        if (l <= currentsize && heap[l].getPriority() < heap[i].getPriority())
        {
            smallest = l;
        } else {
            smallest = i;
        }
        if (r <= currentsize && heap[r].getPriority() < heap[smallest].getPriority())
        {
            smallest = r;
        }
        if (smallest != i) {
            vertausche(i, smallest);
            minHeapify(smallest);
        }
    }

    // Methode zum aendern des Prioritaetswertes des Elementes mit dem Datenstring s
    public void update(String s, double n) {
        int i = 1;
        while (s != heap[i].getData()) {
            i++;
        }
        // Prioritätswert n soll kleiner sein als der bisherige Prioritätswert von s (also heap an der Stelle i)
        if (n < heap[i].getPriority()) {
            heap[i].setPriority(n);
        }
        bedingungZumVertauschen(i);
    }
}

