import java.io.FileNotFoundException;

public class main {
    public static void main(String[] args) throws FileNotFoundException {
        DirectedGraph graph = DirectedGraph.readGraph("OS_Map.txt");
        boolean pfadExistiert = graph.BFS("Bissendorf", "Wallenhorst", 6);
        if (pfadExistiert) {
            graph.printPath("Wallenhorst");
        } else {
            System.out.println("Pfad existiert nicht, bitte prüfen Sie Ihre 'max' Eingabe!");
        }
    }
}
