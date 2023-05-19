package GraphFramework;

import java.util.LinkedList;

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

    /**
     * PQPrimAlg Constructor
     *
     * @param graph
     */
    public MHPrimAlg(Graph graph) {
        MSTresultList = new LinkedList<>(); // MST List
    }

    @Override
    public void findMST(Graph graph) {

        Vertex vc = graph.vertices.getFirst(); // Current vertex will hold vertex 0.
        Heap minHeap = new Heap(graph.vertices.size());

        for (Vertex i : graph.vertices) {    //go thro all vertice

            for (int j = 0; j < i.adjList.size(); j++) {//loop thro all source vertices

                minHeap.insert(i.adjList.get(j));
            }
        }//store ALL edges in edges<>()}

        /**
         * PQPrim Algorithm Scenario: loop through vertex 0 (as in array
         * vertices index)-it- adjacent list (what vertex 0 connected to) Before
         * adding to PQ check if vertex isVisited or not then Add adjacent in
         * Priority Queue. Check if vertex target already visited (yes) just
         * ignore it, else add the edge to the MST Result Mark existing vertex
         * target in MST as isVisited (true) Get the next chosen vertex and
         * break from PQ since we added 1 MST to it
         *
         */
        // Loop through vertices array (|V|-1)
        for (int i = 0; i < graph.vertices.size() - 1; i++) {

            // Loop through adjacent vertices of this vertex
            for (int j = 0; j < vc.adjList.size(); j++) {
                Edge edge = vc.adjList.get(j);
                edge.source.isVisited = true;
                // Check if its visited or not before adding it to the queue
                if (!edge.target.isVisited) {
                    minHeap.insert(edge); // Remaining edges

                } // End of if-statement
            } // End loop through adjacent vertices
            while (!minHeap.isEmpty()) {
                Edge edge = minHeap.remove(); // Remove edge with minimum-weight edge e*=(v*, u*)

                if (!edge.target.isVisited) {

                    edge.target.isVisited = true; // Mark u* (target) as visited now 

                    MSTresultList.add(i, edge); // Add the target edge to the MST list

                    this.cost += MSTresultList.get(i).weight; // Get cost of minimum-weight edges (MST)

                    vc = edge.target; // Next Vertex to check adjacent of it
                    break; // exit after adding 1 result to the MST
                } // End of if-statement
            } // End of while loop 
        } // End For loop of vertices array 
    } // End of Method

    @Override
    public void displayResultingMST() {
        // Office No. A – Office No. B :
        // Output as required: line length: x
        for (int i = 0; i < MSTresultList.size(); i++) {
            MSTresultList.get(i).displayInfo();

            System.out.println();
        }
        System.out.println("The cost of designed phone network: " + this.cost);

    }

}
