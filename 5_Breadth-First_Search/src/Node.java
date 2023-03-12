import java.util.ArrayList;
import java.util.List;

public class Node {

    public String name;
    public List<Edge> neighbors; // Adjazenzliste
    boolean visited;
    Node prev;
    double dist;

    public Node(String n) {
        this.name = n;
        this.neighbors = new ArrayList<>();
    }
}
