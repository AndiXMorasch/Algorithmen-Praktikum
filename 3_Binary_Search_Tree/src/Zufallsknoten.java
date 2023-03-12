import java.util.Random;
import java.util.Arrays;

public class Zufallsknoten {

    BST bst = new BST();
    Random r = new Random();
    int n = 10000;
    int[] arr;

    // Methode zum Generieren von 0 bis 9999 Zufallsschlüsseln
    public void nAnzahlKnoten() {
        int[] arr1 = new int[n];
        for(int i = 0; i < n; i++) {
            arr1[i] = i;
        }
        arr = arr1;
    }

    // Quelle: https://www.geeksforgeeks.org/shuffle-a-given-array-using-fisher-yates-shuffle-algorithm/
    // Methode zum Generieren von Zufallsknotenschlüsseln (mittels Fisher Yates Shuffle)
    public void generiereZufallsknotenkeys() {
        nAnzahlKnoten();

        for (int i = n-1; 0 <= i; i--) {

            // Pick a random index from 0 to i
            int j = r.nextInt(i+1);

            // Swap arr[i] with the element at random index
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        // System.out.println(Arrays.toString(arr));
    }

    // Hilfsmethode für die Zuweisung der Zufallsschlüssel an die einzelnen Knoten
    public void generiereZufallsknoten() {

        for(int i = 0; i < n; i++) {
            bst.insert(arr[i],generiereRandomString());
        }
    }

    // Quelle: https://stackoverflow.com/questions/41107/how-to-generate-a-random-alpha-numeric-string
    // Methode zum Generieren eines random Strings
    private String generiereRandomString() {
        // Variable String alphabet die das gesamte Alphabet umfasst
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        // Anlegen eines Stringbuilder Objektes
        StringBuilder sb = new StringBuilder();

        // Angabe über die gewünschte Länge des Strings
        int length = 7;

        // Schleifendurchlauf für 7 Zufallsbuchstaben
        for (int i = 0; i < length; i++) {

            // Suche einen zufälligen Index aus der Länge des Alphabets raus und speichere ihn in index
            int index = r.nextInt(alphabet.length());

            // Hole den Buchstaben an dieser Indexposition und speichere ihn in randomChar
            char randomChar = alphabet.charAt(index);

            // Übergebe den zufälligen Buchstaben an den Stringbuilder sb
            sb.append(randomChar);
        }
        // Anlegen einer Variable String die das neue generierte Wort aus 7 Buchstaben hält
        String randomString = sb.toString();

        // Rückgabe des Strings randomString
        return randomString;
    }

    // Methode zum Löschen eines ungeraden Knotenschlüssels
    public void ungeradeKnotenLoeschen() throws Exception {
        for(int i = 1; i < n; i+=2) {
            bst.remove(i);
        }
    }

    // Methode zur Prüfung der Höhe des Baumes
    public int pruefeHoeheDesBaumes() {
        return bst.height();
    }

    // Methode zur Manipulation eines Baumes
    public void baumManipulieren() {
        bst.manipulator();
    }

    // Methode zur Prüfung der Gültigkeit des BST
    public boolean pruefeIsValid() {
        return bst.isValidBST();
    }
}
