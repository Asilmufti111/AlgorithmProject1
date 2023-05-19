package PhoneNetworkApp;

import GraphFramework.Edge;
import GraphFramework.Graph;
import GraphFramework.Vertex;

/*
 *  @authors Asil, Qamar, Aroub,Khalida
 * B9A
 * CPCS-324
 * Project Code
 * 18th may. 2023
 */
public class BluePrintGraph extends Graph {

    BluePrintGraph(int verticesNO, int edgesNO, boolean b) {
        super(verticesNO, edgesNO, b);
    }

    // Create Vertex
    @Override
    public Vertex createVertex(String label) {
        return new Office(label);
    }

    // Create Edge
    @Override
    public Edge createEdge(Vertex source, Vertex target, int weight) {
        return new Line(source, target, weight);
    }

}
