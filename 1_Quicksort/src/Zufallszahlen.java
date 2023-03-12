import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Zufallszahlen {

    // Anlegen eines Scanner Objektes zum Auslesen der jeweiligen .txt Dateien
    Scanner scan;

    // Quelle: https://stackoverflow.com/questions/7357852/write-int-to-text-file-using-writer#:~:text=There%20is%20also%20a%20very,123%20%2B%20%22%22)%3B%20wr.
    // Anlegen eines PrintWriter Objektes zum Schreiben der randomisierten Werte in eine .txt Datei
    PrintWriter fileout1 = new PrintWriter(new FileWriter("hunderttausendZahlen.txt"));
    PrintWriter fileout2 = new PrintWriter(new FileWriter("eineMillionZahlen.txt"));
    PrintWriter fileout3 = new PrintWriter(new FileWriter("fünfMillionenZahlen.txt"));

    // Deklaration der einzelnen Arrays für die randomisierten Zahlenwerte
    int[] arr1;
    int[] arr2;
    int[] arr3;

    // Quelle: https://docs.oracle.com/javase/8/docs/api/java/util/Random.html
    // Anlegen eines Random Objektes zur Generierung von Zufallszahlen
    Random rand = new Random();

    // Quelle: https://stackoverflow.com/questions/7357852/write-int-to-text-file-using-writer#:~:text=There%20is%20also%20a%20very,123%20%2B%20%22%22)%3B%20wr.
    // Methode zur Generierung von 100.000 Zufallszahlen
    public void hunderttausendZahlen() {
        for (int i = 1; i <= 100000; i++) {
            int upperbound = 5000000;
            int int_random = rand.nextInt(upperbound);
            fileout1.println(int_random);
        }
        fileout1.close();
    }

    // Quelle: https://stackoverflow.com/questions/7357852/write-int-to-text-file-using-writer#:~:text=There%20is%20also%20a%20very,123%20%2B%20%22%22)%3B%20wr.
    // Methode zur Generierung von 1.000.000 Zufallszahlen
    public void eineMillionZahlen() {
        for (int i = 1; i <= 1000000; i++) {
            int upperbound = 5000000;
            int int_random = rand.nextInt(upperbound);
            fileout2.println(int_random);
        }
        fileout2.close();
    }

    // Quelle: https://stackoverflow.com/questions/7357852/write-int-to-text-file-using-writer#:~:text=There%20is%20also%20a%20very,123%20%2B%20%22%22)%3B%20wr.
    // Methode zur Generierung von 5.000.000 Zufallszahlen
    public void fuenfMillionZahlen() {
        for (int i = 1; i <= 5000000; i++) {
            int upperbound = 5000000;
            int int_random = rand.nextInt(upperbound);
            fileout3.println(int_random);
        }
        fileout3.close();
    }

    // Quelle: https://www.youtube.com/watch?v=lHFlAYaNfdo&t=229s&ab_channel=AlexLee
    // Methode zum Einlesen von 100.000 Zufallszahlen in ein Array
    public int[] leseHunderttausendZahlenInArrayEin() throws FileNotFoundException {
        File file = new File("hunderttausendZahlen.txt");
        scan = new Scanner(file);
        int[] arr = new int[100000];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = scan.nextInt();
        }
        arr1 = arr;
        return arr;
    }

    // Quelle: https://www.youtube.com/watch?v=lHFlAYaNfdo&t=229s&ab_channel=AlexLee
    // Methode zum Einlesen von 1.000.000 Zufallszahlen in ein Array
    public int[] leseEineMillionZahlenInArrayEin() throws FileNotFoundException {
        File file = new File("eineMillionZahlen.txt");
        scan = new Scanner(file);
        int[] arr = new int[1000000];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = scan.nextInt();
        }
        arr2 = arr;
        return arr;
    }

    // Quelle: https://www.youtube.com/watch?v=lHFlAYaNfdo&t=229s&ab_channel=AlexLee
    // Methode zum Einlesen von 5.000.000 Zufallszahlen in ein Array
    public int[] leseFuenfMillionenZahlenInArrayEin() throws FileNotFoundException {
        File file = new File("fünfMillionenZahlen.txt");
        scan = new Scanner(file);
        int[] arr = new int[5000000];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = scan.nextInt();
        }
        arr3 = arr;
        return arr;
    }

    // Quelle: https://www.youtube.com/watch?v=rRbxoR270Uk&t=2000s&ab_channel=MingTechMedia + Vorlesungsfolien
    // Partitionierungsmethode zur Trennung / Anordnung vom Pivot Element
    public int partition(int[] arr, int p, int r) {
        int pivot = arr[r];
        int i = p-1;
        for (int j = p; j <= r-1; j++) {
            if (arr[j] <= pivot) {
                i = i+1;
                vertausche(arr, i, j);
            }
        }
        vertausche(arr, i+1, r);
        return i+1;
    }

    // Quelle: https://www.youtube.com/watch?v=rRbxoR270Uk&t=2000s&ab_channel=MingTechMedia + Vorlesungsfolien
    // Partitionierungsmethode zur Trennung / Anordnung vom Pivot Element (mithilfe von Median-Of-Three-Verfahren)
    public int partitionMedianOfThree(int[] arr, int p, int r) {
        // Berechnung vom Median-Of-Three (lt. Vorlesungsfolien)
        int m = (p + r) / 2;

        if(arr[m] < arr[p]) {
            vertausche(arr, p, m);
        }
        if(arr[r] < arr[p]) {
            vertausche(arr, r, p);
        }
        if(arr[m] < arr[r]) {
            vertausche(arr, m, r);
        }

        int pivot = arr[r];
        int i = p-1;
        for (int j = p; j <= r-1; j++) {
            if (arr[j] <= pivot) {
                i = i+1;
                vertausche(arr, i, j);
            }
        }
        vertausche(arr, (i+1), r);
        return i+1;
    }

    // Methode zum Vertauschen von i und j bzw. i+1 und r
    public void vertausche(int[] arr, int pos1, int pos2) {
        int temp = arr[pos1];
        arr[pos1] = arr[pos2];
        arr[pos2] = temp;
    }

    // Quelle: Vorlesungsfolien
    // Rekursive Methode des Quicksorts (Standartvariante)
    public void quickSort(int[] arr, int p, int r) {
        if (p < r) {
            int s = partition(arr, p, r);
            quickSort(arr, p, s - 1);
            quickSort(arr, s + 1, r);
        }
    }

    // Quelle: Vorlesungsfolien
    // Rekursive Methode des Quicksorts (Median-Of-Three-Variante)
    public void quickSortMedianOfThree(int[] arr, int p, int r) {
        if (p < r) {
            int s = partitionMedianOfThree(arr, p, r);
            quickSortMedianOfThree(arr, p, s - 1);
            quickSortMedianOfThree(arr, s + 1, r);
        }
    }

    // Methode für die Ausgabe der Elemente des Arrays (Zeilenweise)
    public void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    // Methode für die Wiedergabe der aktuellen Zeit in Millisekunden (seit 1. Jan 1970 UTC)
    public long aktuelleZeit() {
        return System.currentTimeMillis();
    }

    // Quelle: https://docs.oracle.com/javase/7/docs/api/java/lang/System.html#currentTimeMillis()
    // Methode zur Berechnung der Sortierdauer eines Arrays
    public long sortierdauer(long startzeit, long endzeit) {
        return endzeit - startzeit;
    }

    // Exception für die Klasse Zufallszahlen (falls .txt Files nicht vorhanden sein sollten)
    public Zufallszahlen() throws IOException {
    }

}

