public class main {

    public static void main(String[] args) throws Exception {

        Hilfsklasse hilfsklasse = new Hilfsklasse();

        hilfsklasse.generiere1000Elemente();
        hilfsklasse.entferneElement();

        hilfsklasse.generiere1000Elemente();
        hilfsklasse.aenderePrioritaet();
    }
}
