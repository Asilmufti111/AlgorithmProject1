package GraphFramework;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/*
 *  @authors Asil, Qamar, Aroub,Khalida
 * B9A
 * CPCS-324
 * Project Code
 * 18th may. 2023
 */

public class MHPrimAlg extends MSTAlgorithm {
	
	// Data fields
	private int cost = 0;
                
                
                Map<String,Integer> vertexVal;

    // Stores the Minimum spanning Tree
                List<Edge> result;
	
	/**
	 * PQPrimAlg Constructor
	 * @param graph
	 */
	public MHPrimAlg(Graph graph) {
            
        	MSTresultList = new LinkedList<>(); // Array holds the edges of MST

	}
	
	
	/**
	 * Shows Resulting MST
     * @param graph
	 */

        @Override
	public void findMST(Graph graph) {
            vertexVal = new LinkedHashMap<>();

        // Stores the Minimum spanning Tree
           
            
            vertexVal = new LinkedHashMap<>();

        // Vertex to Edge Map
        Map<String, Edge> vertexToEdge = new HashMap<>();

        // Assign all the initial values as infinity for all the Vertices.
        for(int i = 0 ;i<graph.verticesNo;i++){
            String v=graph.vertices.get(i).getLabel();
            vertexVal.put(v,Integer.MAX_VALUE);//initialize to infinity (unknown)
        }

        MinHeap minHeap = new MinHeap(vertexVal);

        // Call buildHeap() to create the MinHeap
        minHeap.buildHeap();

        // Replace the value of start vertex to 0.
        minHeap.updateHeap("0",0);

        // Continue until the Min-Heap is not empty.
        while(!minHeap.empty()){
            // Extract minimum value vertex from Map in Heap
           
            String currentVertex = minHeap.deleteMin();
            // Need to get the edge for the vertex and add it to the Minimum Spanning Tree..
            // Just note, the edge for the source vertex will not be added.
            Edge spanningTreeEdge = vertexToEdge.get(currentVertex);
            if(spanningTreeEdge != null) {
                MSTresultList.add(spanningTreeEdge);//add to MST 
                cost+= spanningTreeEdge.getWeight();//add cost
            }

            // Get all the adjacent vertices and iterate through them.
            for(Edge edge : getEdges(currentVertex,graph)){
            
                String adjacentVertex = edge.getTarget().getLabel();
                
                // We check if adjacent vertex exist in 'Map in Heap' and length of the edge is with this vertex
                // is greater than this edge length.
                if(minHeap.containsVertex(adjacentVertex) && minHeap.getWeight(adjacentVertex) > edge.getWeight()){
                
                    // Replace the edge length with this edge weight.
                    minHeap.updateHeap(adjacentVertex, edge.getWeight());
                    
                    vertexToEdge.put(adjacentVertex, edge);
                }
            }
        }
    }

    List<Edge> getEdges(String vertex,Graph graph){

        List<Edge> edgeList = new LinkedList<>();

       Vertex vv = graph.vertices.get(Integer.parseInt(vertex));
                    
	for(int j=0; j<vv.getAdjList().size() ; j++) {
               
            edgeList.add(vv.getAdjList().get(j));} 

        return edgeList;
    }

	
        

 @Override
    public void displayResultingMST() {

        for (int i = 0; i < MSTresultList.size(); i++) {
            MSTresultList.get(i).displayInfo();

            System.out.println();
        }
        System.out.println("The cost of designed phone network: " + this.cost);

    }
	   

} 
