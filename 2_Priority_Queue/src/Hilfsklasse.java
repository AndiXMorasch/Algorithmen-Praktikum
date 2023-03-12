import java.util.Random;

public class Hilfsklasse {

    int pqLaenge = 1000;
    PQElement pqElement;
    Random rand = new Random();
    MinPQ minPQ = new MinPQ(pqLaenge);

    // Methode zum Generieren von 1000 Zufallselementen (String + Priorität)
    public void generiere1000Elemente() throws Exception {
        for (int i = 1; i <= pqLaenge; i++) {
            int upperbound = 100000;

            double prioritaet = rand.nextInt(upperbound);
            String string = generiereRandomString();

            // System.out.println(string + " | " + prioritaet);
            minPQ.insert(string, prioritaet);
        }
        //ausgabeDerArray();
    }

    // Quelle: https://stackoverflow.com/questions/41107/how-to-generate-a-random-alpha-numeric-string
    // Methode zum Generieren eines random Strings
    private String generiereRandomString() {
        // create a string of all characters
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        // create random string builder
        StringBuilder sb = new StringBuilder();

        // specify length of random string
        int length = 7;

        for (int i = 0; i < length; i++) {

            // generate random index number
            int index = rand.nextInt(alphabet.length());

            // get character specified by index
            // from the string
            char randomChar = alphabet.charAt(index);

            // append the character to string builder
            sb.append(randomChar);
        }
        String randomString = sb.toString();
        return randomString;
    }

    // Methode zum Entfernen aller Elemente der Array
    public void entferneElement() throws Exception {
        while (minPQ.getCurrentsize() > 0) {
            minPQ.extractData();
        }
        //ausgabeDerArray();
    }

    // Methode zum Aendern der Priorität jedes zweiten Elementes der Array
    public void aenderePrioritaet() throws Exception {
        for (int i = 1; i <= pqLaenge; ) {
            minPQ.update(minPQ.getHeap()[i].getData(), minPQ.getHeap()[i].getPriority() - 1);
            i += 2;
        }
        //ausgabeDerArray();
    }

    // Methode zum Ausgeben der Array
    public void ausgabeDerArray() {
        if (minPQ.getCurrentsize() > 0) {
            for (int i = 1; i < minPQ.getCurrentsize() + 1; i++) {
                System.out.println(minPQ.getHeap()[i].getData() + " | " + minPQ.getHeap()[i].getPriority() + " -> Ausgabe");
            }
        }
    }
}
