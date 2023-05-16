package GraphFramework;

import java.util.PriorityQueue;


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
     * MHPrimAlg Constructor
     *
     * @param graph
     */
    public MHPrimAlg(Graph graph) {
        MSTresultList = new Edge[graph.verticesNo]; // Array holds the edges of MST
    }

    /**
     * Shows Resulting MST
     */
    @Override
    public void findMST(Graph graph) {

        Vertex vc = graph.vertices[0]; // Current vertex will hold vertex 0.

        Heap minHeap = new Heap();

        /**
         * MHPrim Algorithm Scenario: loop through vertex 0 (as in array
         * vertices index)-it- adjacent list (what vertex 0 connected to) Before
         * adding to THE MIN HEAP check if vertex isVisited or not then Add
         * adjacent in the MH. Check if vertex target already visited (yes) just
         * ignore it, else add the edge to the MST Result Mark existing vertex
         * target in MST as isVisited (true) Get the next chosen vertex and
         * break from MH since we added 1 MST to it
			 *
         */
        // Loop through vertices array (|V|-1)
        for (int i = 0; i < MSTresultList.length - 1; i++) {

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
                Edge edge = minHeap.remove(); // Remove edge with minimum-weight

                if (!edge.target.isVisited) {

                    edge.target.isVisited = true; // Mark (target) as visited now 

                    MSTresultList[i] = edge; // Add the target edge to the MST list

                    cost += MSTresultList[i].weight; // Get the cost of minimum-weight edges (MST)

                    vc = edge.target; // Next Vertex to check adjacent of it
                    break; // exit after adding 1 result to the MST
                } // End of if-statement
            } // End of while loop 
        } // End For loop of vertices array 
    } // End of Method

    public void displayResultingMST() {
        // Office No. A – Office No. B :
        // Output as required: line length: x
        for (int i = 0; i < MSTresultList.length - 1; i++) {
            Vertex vf = MSTresultList[i].source;
            vf.displayInfo();
            System.out.print(" - ");
            Vertex vs = MSTresultList[i].target;
            vs.displayInfo();
            System.out.print(" : line length: " + (MSTresultList[i].weight) + " ");
            Edge e = MSTresultList[i];
            e.displayInfo();
            System.out.println();
        }
    }

    /**
     * Shows only the cost calculated during the displayResultingMST Method
     */
    @Override
    public void displayMSTcost() {
        System.out.println("The cost of designed phone network: " + this.cost);
    } // End of Method

} // End of Class
