
public class main {
    public static void main(String[] args) throws Exception {


        DirectedGraph graph = DirectedGraph.readGraph("OS_Map.txt");

        // Bester Pfad mit BFS:
        boolean pfadExistiert = graph.BFS("Ladbergen", "Osnabrueck", 6);
        if (pfadExistiert) {
            graph.printPath("Osnabrueck");
        } else {
            System.out.println("Pfad existiert nicht, bitte prüfen Sie Ihre 'max' Eingabe!");
        }

        // Bester Pfad mit Dijkstra:
        System.out.println();
        graph.dijkstra("Ladbergen");
        graph.printPath("Osnabrueck");
    }
}
