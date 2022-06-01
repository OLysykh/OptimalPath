package firstTry;


import java.util.HashSet;
import java.util.Set;

public class Graph {

    //https://www.baeldung.com/java-dijkstra


    public Graph() {
    }

    private Set<Node> nodes = new HashSet<>();

    public Set<Node> getNodes() {
        return nodes;
    }

    public Graph(Set<Node> nodes) {
        this.nodes = nodes;
    }

    public void setNodes(Set<Node> nodes) {
        this.nodes = nodes;
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
