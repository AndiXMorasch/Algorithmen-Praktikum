public class main {
    public static void main(String[] args) {

        Hilfsklasse hilfsklasse = new Hilfsklasse();

        hilfsklasse.generiereZufallsknotenkeys();
        hilfsklasse.generiereZufallsknoten();
        System.out.println("Höhe des Baumes nach dem Einfügen: " + hilfsklasse.pruefeHoeheDesBaumes());
        System.out.println("CheckRB(): Gültigkeit des Baumes (vor der Manipulation): " + hilfsklasse.gueltigkeitDesBaumes());

        hilfsklasse.manipulationDesBaumes();
        System.out.println("CheckRB(): Gültigkeit des Baumes (nach der Manipulation): " + hilfsklasse.gueltigkeitDesBaumes());

        hilfsklasse.generiereAufsteigendeKnoten();
        System.out.println("Höhe des Baumes nach dem Einfügen (aufsteigender Werte): " + hilfsklasse.pruefeHoeheDesAufsteigendenBaumes());
    }
}
