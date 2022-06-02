package firstTry;


import java.util.HashSet;
import java.util.Set;

public class Graph {

    //https://www.baeldung.com/java-dijkstra

    private Set<Node> nodes = new HashSet<>();

    public Graph() {
    }

    public Graph(Set<Node> nodes) {
        this.nodes = nodes;
    }

    public Set<Node> getNodes() {
        return nodes;
    }

    public void addNode(Node nodeA) {
        nodes.add(nodeA);
    }

    @Override
    public String toString() {
        return "Graph{" +
                "nodes=" + nodes +
                '}';
    }
}
