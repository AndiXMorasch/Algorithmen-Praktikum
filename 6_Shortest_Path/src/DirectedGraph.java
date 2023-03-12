import java.io.*;
import java.util.*;

public class DirectedGraph {
    public static final double INFINITY = Double.MAX_VALUE;
    private Map<String,Node> nodes = new HashMap<String,Node>();

    // Methode zum Einlesen von Knoten, Nachbarknoten, Gewichtung aus einer .txt Datei
    public static DirectedGraph readGraph(String file) throws FileNotFoundException {
        DirectedGraph dg = new DirectedGraph();
        File text = new File(file);
        Scanner scan = new Scanner(text);

        // Scanner Objekt welches über das file scannt und 3 Variablen (knoten, nachbar, gewichtung) ausliest
        while(scan.hasNext()) {
            // Quelle: Beide if-Abfragen in der while-Schleife von Hendrik Purschke
            String knoten = scan.next();
            String nachbar = scan.next();
            Double gewichtung = Double.parseDouble(scan.next());

            Node startknoten = dg.nodes.get(knoten);
            Node endknoten = dg.nodes.get(nachbar);
            // Prüfen ob der Knoten bereits angelegt ist
            if (dg.nodes.get(knoten) == null) {
                startknoten = new Node(knoten);
                dg.nodes.put(knoten, startknoten);
            }
            // Prüfen, ob der Nachbar(knoten) bereits angelegt ist
            if (dg.nodes.get(nachbar) == null) {
                endknoten = new Node(nachbar);
                dg.nodes.put(nachbar, endknoten);
            }
            // Zum Schluss dem Startknoten eine Kante mit der dem Endknoten als Destination sowie der Gewichtung zuweisen
            Edge edge = new Edge(endknoten, gewichtung);
            dg.nodes.get(startknoten.name).neighbors.add(edge);
        }
        return dg;
    }

    // Methode zur Breitensuche (Breadth First Search) im Graphen (Kommentar: wird bei Dijkstra nicht mehr benötigt!)
    public boolean BFS(String start, String dest, int max) {
        // Quelle: Lambda Ausdruck von Hendrik Purschke
        nodes.forEach((s,node)->{
            node.prev = null;
            node.dist = INFINITY;
            node.visited = false;
        });
        nodes.get(start).prev = null;
        nodes.get(start).dist = 0;
        nodes.get(start).visited = true;

        // Quelle: LinkedList aus: http://tutorials.jenkov.com/java-collections/queue.html
        Queue<Node> pQueue = new LinkedList<Node>();
        pQueue.add(nodes.get(start));

        // Solange die Queue nicht leer ist...
        while(!pQueue.isEmpty()) {
            Node u = pQueue.remove();
            if(u.dist >= max) {
                return false;
            }
            for(Edge v : u.neighbors) {
                // Wenn die visited Markierung false ist, dann setze sie auf true und erhöhe den
                // dist-counter um +1. Anschließend setze den Vorgänger auf den Node u.
                if(v.dest.visited == false) {
                    v.dest.visited = true;
                    v.dest.dist = u.dist + 1;
                    v.dest.prev = u;
                    pQueue.add(v.dest);
                }
                // Prüfen ob der Knoten über den gerade iteriert wird, bereits der Zielknoten ist und ob die Distanz kleiner als max ist
                if(v.dest.name.equals(dest) && v.dest.dist <= max) {
                    return true;
                }
            }
        }
        return false;
    }

    // Dijkstra Algorithmus
    public void dijkstra(String start) throws Exception {
        MinPQ minPQ = new MinPQ(17);    // 17, da wir 17 Knoten haben!
        // Quelle: Lambda Ausdruck von Hendrik Purschke
        nodes.forEach((v,node)->{
            node.prev = null;
            node.dist = INFINITY;
            node.visited = false;
            if(node == nodes.get(start)){
                node.dist = 0;
            }
            try {
                minPQ.insert(node.name, node.dist);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        while(!minPQ.isEmpty()) {
            Node u = nodes.get(minPQ.extractData());
            u.visited = true;
            for(Edge v : u.neighbors) {
                if((v.dest.visited == false) && (v.dest.dist > (u.dist + v.weight))) {
                    v.dest.dist = u.dist + v.weight;
                    v.dest.prev = u;
                    minPQ.update(v.dest.name, v.dest.dist);
                }
            }
        }
    }

    public void printPath(String dest) {
        printPathRecursive(dest);
        int a = (int) (nodes.get(dest).dist*10);
        double b = a / 10.0;
        System.out.println("Gesamtdistanz: " + b);
    }

    // Rekursive Methode zur Ausgabe des Pfades vom Startknotens zum Endknoten
    private void printPathRecursive(String dest) {

        if(nodes.get(dest).prev != null) {
            printPathRecursive(nodes.get(dest).prev.name);
        }
        System.out.println(nodes.get(dest).name + " ");
    }
}

