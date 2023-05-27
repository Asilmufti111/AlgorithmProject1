package GraphFramework;

import java.util.*;

/*
 *  @authors Asil, Qamar, Aroub,Khalida
 * B9A
 * CPCS-324
 * Project Code
 * 18th may. 2023
 */

public class KruskalAlg extends MSTAlgorithm {
    
    private int cost = 0; //initialize cost of weights with zero 

    Map<String, Node> subsets = new HashMap< >(); //create hashmap to create subsets of vertices 

    ArrayList<Edge> edges = new ArrayList<>(); //create arraylist to store all edges in ascending order 
    
    
    	public KruskalAlg(Graph graph) {
		MSTresultList = new LinkedList<>(); // MST List to stor MST 
	}


    void insertEdges(Graph graph) { //insert all edges in edges arraylist 
        for(int i= 0; i<graph.verticesNo-1;i++){ //go thro all vertices 
            for (Edge edge : graph.vertices.get(i).adjList) { //go thro each vertex adjaceny list 
                edges.add(edge); }} //add each edge to the arraylist 
    }

    // Create groups with only one vertex.
    public void singletonOf(String data){ //crate initial set (Singletons)

        Node node = new Node(); //new node 
        node.data = data; //vertex label 
        node.rank = 0; //each vertex leader of its own group
        node.representative = node; //vertex representative is itself first
        subsets.put(data, node); //add this label and this node to subsets to initially create singleton 
    }

    //Combines two groups into one. Does union by rank.
    public void union(String vertex1, String vertex2){

        Node node1 = subsets.get(vertex1); //get vertex 1 and vertex 2
        Node node2 = subsets.get(vertex2);

        Node representative1 = findSet(node1); //find representative of each node an dreturn it 
        Node representative2 = findSet(node2);

        //If they are in the same group, do nothing.
        if(representative1.data.equals(representative2.data)) {

            return;
        }
        //Else whose rank is higher becomes parent of other.
        if (representative1.rank <= representative2.rank) {

            //Increment rank only if both sets have same rank.
            representative1.rank = (representative1.rank == representative2.rank) ? representative1.rank + 1 : representative1.rank; //increment rank of source if both equal
            representative1.representative = representative2; //make rep of 1 = rep of 2 (2 is parent of 1)
        }
        else {

            representative2.representative = representative1;//if rank1 > rank 2 make rep of 2 = rep of 1 (1 is parent of 2)
        }
    }

    // Find the representative of this set

    public String findSet(String data){

        return findSet(subsets.get(data)).data;//get data of certain node using vertex label 
    }

    // Finds the leader/representative recursivly and does PATH COMPRESSION as well.
    private Node findSet(Node node){

        Node representative = node.representative; //get rep of node 
        if (representative == node) { //if node is representative of itself  (base case)

            return representative;//return representative 
        }
        node.representative = findSet(node.representative);//else fin its representitive recursively 
        return node.representative;//return representative
    }


    // Find Minimum Spanning Tree using Kruskal's Algorithm.
    @Override
    public void findMST(Graph graph) {
        
        insertEdges(graph);  //add all edges

        
        Collections.sort(edges);//Sort all edges in Ascending order.

       
        for (Vertex vertex : graph.vertices) { //Create singletons of all vertices in my graph

            singletonOf(vertex.label);
        }

        for (Edge edge : edges) { //for all edges

            //Get each set of the two vertices of the edge.
            String root1 = findSet(edge.source.label);
            String root2 = findSet(edge.target.label);

            //check if the vertices are on the same or different set.
           
            if (root1.equals(root2)) { //If vertices are in the same set then don't add edge because vertex alreadt in MST 
            }
            else {

                // If vertices are in different sets then add the edge to result and union 
                // these two sets into one.
                
                MSTresultList.add(edge);//add in MST
                cost+=edge.getWeight();
                union(edge.source.label, edge.target.label); //create union between source and edge
            }
        }
    }
    
 @Override
    public void displayResultingMST() {

        for (int i = 0; i < MSTresultList.size(); i++) { //for all MST 
            
            MSTresultList.get(i).displayInfo(); //display info for each edge

            System.out.println();
        }
        System.out.println("The cost of designed phone network: " + this.cost); //total cost

    }
}
    class Node { //representative of a vertex

    String data; //vertex label 
    int rank;
    Node representative; //node parent (who is its representative?)
}
