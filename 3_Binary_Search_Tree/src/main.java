public class main {

    public static void main(String[] args) throws Exception {
        Zufallsknoten zufallsknoten = new Zufallsknoten();

        zufallsknoten.generiereZufallsknotenkeys();
        zufallsknoten.generiereZufallsknoten();
        System.out.println("Höhe des Baumes vor dem Löschen: " + zufallsknoten.pruefeHoeheDesBaumes());
        System.out.println("Gültigkeit des Baumes vor dem Löschen: " + zufallsknoten.pruefeIsValid() + '\n');

        zufallsknoten.ungeradeKnotenLoeschen();
        System.out.println("Höhe des Baumes nach dem Löschen: " + zufallsknoten.pruefeHoeheDesBaumes());
        System.out.println("Gültigkeit des Baumes nach dem Löschen: " + zufallsknoten.pruefeIsValid() + '\n');

        zufallsknoten.baumManipulieren();
        System.out.println("Gültigkeit des Baumes nach der Manipulation: " + zufallsknoten.pruefeIsValid());
    }
}
