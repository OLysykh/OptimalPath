package firstTry;

import java.util.List;

public class Executor {



    public static void main(String[] args) {

        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        Node nodeF = new Node("F");

//will be added from SQL
        nodeA.addDestination(nodeB, 10); //adding neighboors
        nodeA.addDestination(nodeC, 15);

        nodeB.addDestination(nodeD, 12);
        nodeB.addDestination(nodeF, 15);

        nodeC.addDestination(nodeE, 10);

        nodeD.addDestination(nodeE, 2);
        nodeD.addDestination(nodeF, 1);

        nodeF.addDestination(nodeE, 5);

        Graph graph = new Graph();

        graph.addNode(nodeA);
        graph.addNode(nodeB);
        graph.addNode(nodeC);
        graph.addNode(nodeD);
        graph.addNode(nodeE);
        graph.addNode(nodeF);

        graph  = DijkstraAlgorithm.calculateShortestPathFromSource(graph, nodeA);

      for (Node nod:graph.getNodes()){
          System.out.print(nod.getDistance()+" "+ nod.getName());
          List<Node> list = nod.getShortestPath();
          for (Node ele: list){
              System.out.println(ele.getName());
          }
          System.out.println("-------------");
        }

    }
}
