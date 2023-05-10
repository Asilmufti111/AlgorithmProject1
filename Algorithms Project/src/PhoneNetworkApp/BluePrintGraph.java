package PhoneNetworkApp;

import GraphFramework.Edge;
import GraphFramework.Graph;
import GraphFramework.Vertex;

public class BluePrintGraph extends Graph {

    /**
     * auto constructor 
     */
    public BluePrintGraph() {
        
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
