package PhoneNetworkApp;

import GraphFramework.Edge;
import GraphFramework.Graph;
import GraphFramework.Vertex;
import java.io.File;
import java.io.FileNotFoundException;

/*
 *  @authors Asil, Qamar, Aroub,Khalida,Huda
 * B9A
 * CPCS-324
 * Project Code
 * 18th may. 2023
 */
public class BluePrintGraph extends Graph {

    
    
    BluePrintGraph(int verticesNo, int edgesNo, boolean b) {
        super(verticesNo, edgesNo, b);
    }
    
    BluePrintGraph(File graphfile) throws FileNotFoundException{
    
        super(graphfile);}
    
    BluePrintGraph() {
        
    }

    // Create Vertex
    @Override
    public Office createVertex(String label) {
        return new Office(label);
    }

    // Create Edge
    @Override
    public Line createEdge(Vertex source, Vertex target, int weight) {
        return new Line(source, target, weight);
    }

}
