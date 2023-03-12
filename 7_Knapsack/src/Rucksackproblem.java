import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Rucksackproblem {

    int n = 0;                                          // Anzahl der Pakete (insgesamt)
    ArrayList<Integer> a = new ArrayList<>();           // ArrayList mit den Preisen
    ArrayList<Integer> w = new ArrayList<>();           // ArrayList mit dem Krach (Durchmesser * Anzahl an Knallern in den Päckchen)
    int G = 1000;                                       // Gesamtkapazität in Euro
    ArrayList<Integer> artNr = new ArrayList<>();       // ArrayList mit Artikelnummern der jeweiligen Päckchen

    // Default Konstruktor
    public Rucksackproblem() {}

    // Konstruktor, falls eine andere Gesamtkapazität als 1000 benötigt wird
    public Rucksackproblem(int kapazitaet) {
        this.G = kapazitaet;
    }

    // Methode zum Einlesen und übergeben der Daten aus dem gegebenen Textdokument
    public void werteEinlesen(String file) throws FileNotFoundException {
        File text = new File(file);
        Scanner scan = new Scanner(text);
        int artNr = 0;

        while(scan.hasNext()) {
            int n = scan.nextInt(); // Anzahl Päckchen
            int a = scan.nextInt(); // Anzahl der Knaller im Päckchen
            int w = scan.nextInt(); // Durchmesser eines Knallers
            int g = scan.nextInt(); // Preis eines Päckchens
            this.n += n;
            artNr++;

            for(int i = 0; i < n; i++) {
                this.a.add(g);                  // ArrayList mit den Preisen befüllen
                this.w.add(a*w);                // ArrayList mit dem Krach befüllen
                this.artNr.add(artNr);          // ArrayList mit den Artikelnummern (angefangen bei Nr. 1) befüllen
            }
        }

        int preise[] = new int[this.a.size()+1];
        int krach[] = new int[this.w.size()+1];

        for(int i = 1; i < this.a.size()+1; i++) {
            preise[i] = this.a.get(i-1);
            krach[i] = this.w.get(i-1);
        }

        // Aufruf der rucksack Methode zur Ermittlung der optimalen Einkaufsliste
        ArrayList<Integer> optimaleEinkaufliste = rucksack(this.n, preise, krach, this.G);

        // Aufruf der ausgabeDerEinkaufliste Methode zur Ausgabe der optimalen Einkaufliste
        ausgabeDerEinkaufsliste(optimaleEinkaufliste);
    }

    // Methode zur Ermittlung der optimalen Einkaufsliste
    public ArrayList<Integer> rucksack(int n, int a[], int w[], int G) {
        int W[][] = new int[n+1][G+1];
        for (int i = 0; i <= G; i++) {
            W[0][i] = 0;
        }
        for(int k = 1; k <= n; k++) {
            for(int j = 0; j <= G; j++) {
                if ((a[k] <= j) && (W[k-1][j-a[k]] + w[k] > W[k-1][j])) {
                    W[k][j] = W[k-1][j-a[k]] + w[k];
                } else {
                    W[k][j] = W[k-1][j];
                }
            }
        }
        System.out.println("Der maximale Krach beträgt: " + W[n][G] + "\n");

        ArrayList <Integer> optimaleEinkaufsliste = new ArrayList<>();
        int j = G;
        for(int k = n; k > 0; k--) {
            if(W[k][j] > W[k-1][j]) {
                optimaleEinkaufsliste.add(k);
                j -= a[k];
            }
        }

        return optimaleEinkaufsliste;
    }

    // Methode zur Ausgabe der optimalen Einkaufliste
    public void ausgabeDerEinkaufsliste(ArrayList<Integer> optEinkaufsliste) {

        ArrayList<Integer> arr = new ArrayList<>();

        for (int i = 0; i < optEinkaufsliste.size(); i++) {
            int a = optEinkaufsliste.get(i);
            int b = artNr.get(a-1);
            arr.add(b);
        }

        // Quelle der HashMap Funktion: https://www.geeksforgeeks.org/counting-frequencies-of-array-elements/

        Map<Integer, Integer> mp = new HashMap<>();

        for (int i = 0; i < arr.size(); i++)
        {
            if (mp.containsKey(arr.get(i)))
            {
                mp.put(arr.get(i), mp.get(arr.get(i)) + 1);
            }
            else
            {
                mp.put(arr.get(i), 1);
            }
        }

        System.out.println("Die Artikelliste zum erreichen dieses Krachs ist folgende:");

        for (Map.Entry<Integer, Integer> entry : mp.entrySet())
        {
            System.out.println("Artikel Nr: " + entry.getKey() + " \t | " + entry.getValue() + "x kaufen");
        }
    }
}