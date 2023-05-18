package GraphFramework;

import java.util.ArrayList;
import java.util.Collections;


/*
 *  @authors Asil, Qamar, Aroub,Khalida
 * B9A
 * CPCS-324
 * Project Code
 * 18th may. 2023
 */
public class KruskalAlg extends MSTAlgorithm {

    private int cost = 0;

    public KruskalAlg(Graph graph) {
        MSTresultList = new Edge[graph.verticesNo]; // MST List
    }

    @Override
    public void findMST(Graph graph) {

        Vertex src;

        Vertex trgt;

        Edge edge;

        ArrayList<Edge> edges = new ArrayList<>();

        for (Vertex i : graph.vertices) {    //go thro all vertices

            int label = Integer.parseInt(i.label);

            src = graph.vertices[label];

            for (int j =0 ;j<src.adjList.size();j++) {//loop thro all source vertices
                
                edges.add(j,src.adjList.get(j));
            }
        }//store ALL edges in edges<>()}

         Collections.sort(edges,Collections.reverseOrder()); // Sort All Edges in decreasing Order  (using comparable interface in Edge class) 

        // 1. (MakeSet) Make Set for Each Vertex
        Vertex[] subset = new Vertex[graph.verticesNo]; // Set the DS as the number of vertices 
        makeSet(subset); // Make set for each vertex

        // Loop through ALL edges
        for (int edgeCounter = 0; edgeCounter < MSTresultList.length - 1;) {

            edge = edges.remove(edges.size()-1); //remove last element, which has the least weight
            src = edge.source; //assign source to a vertex
            trgt = edge.target;//assign target to a vertex

            int srcLabel = Integer.parseInt(src.label); //convert label string->int
            int trgtLabel = Integer.parseInt(trgt.label);//convert label string->int

            // if source and target vertex are not part of the same set -> perform union between them
            if (!findSet(Integer.parseInt(subset[srcLabel].label), Integer.parseInt(subset[trgtLabel].label))) {

                // make source and target vertex in same subset + update their representative values in the array 
                union(subset, src, trgt);

                MSTresultList[edgeCounter] = edge; // Add  edge to the MST list	 
                cost += MSTresultList[edgeCounter].weight; // add edge weight(cost) to total weight

                edgeCounter++; // increment number of edges added
            }
        }

    }

    public void makeSet(Vertex[] quickFindDS) { //make all vertices into singleton sets

        for (int i= 0;i<quickFindDS.length;i++) { //loop through all vertices


            Vertex singleton = new Vertex(i+""); //create singleton set 

            quickFindDS[i] = singleton;
        } //edit label with singleton set

    }

    public boolean findSet(int vertex1, int vertex2) { //if element in one subset is found in the other

        return vertex1 == vertex2;// return true
    }

    public void union(Vertex[] subsets, Vertex src, Vertex trgt) {

        int srcLabel = Integer.parseInt(src.label);//convert label (String ->int)

        int trgtLabel = Integer.parseInt(trgt.label);//convert label (String ->int)

        int srcRep = Integer.parseInt(subsets[srcLabel].label); // get representative of source vertex

        int trgtRep = Integer.parseInt(subsets[trgtLabel].label); //// get representative of target vertex

        boolean src_Self_Rep = findSet(srcLabel, srcRep); // Find if VV have representative or not

        boolean trgt_Self_Rep = findSet(trgtLabel, trgtRep); // Find if VU have representative or not

        // Check if current VV & VU are representative of set 
        for (int i = 0; i < subsets.length; i++) {

            //check all element of subsets[] and if the src vertex has a representative that is not itself then 
            if (srcRep == Integer.parseInt(subsets[i].label) && (i != srcLabel)) {
                src_Self_Rep = false; //we set self_rep as false
            }

            //check all element of subsets[] and if the trgt vertex has a representative that is not itself then 
            if (trgtRep == Integer.parseInt(subsets[i].label) && (i != trgtLabel)) {
                trgt_Self_Rep = false;  //we set self_rep as false

            }
        }

        //if target or both are self representative
        if (((!src_Self_Rep) && (trgt_Self_Rep)) || (src_Self_Rep && trgt_Self_Rep)) {

            // then make src label as both of their representatives bcz source value is always less than target value 
            subsets[srcLabel] = subsets[srcLabel];
            subsets[trgtLabel] = subsets[srcLabel];
        } //source is self represntative and target not self representative 
        else if (src_Self_Rep && (!trgt_Self_Rep)) {
            subsets[srcLabel] = subsets[trgtLabel]; //then change source representative to target representative
        } // End of else-if
        // if source and target vertices both have representative (that is not themselves)
        else {

            int maxRep = Math.max(srcRep, trgtRep); // Get max representative to change its elements subset

            int minRep = Math.min(srcRep, trgtRep); // Get minimum representative to set it for both src and trgt

            for (Vertex i : subsets) {

                int label = Integer.parseInt(i.label);

                // Find all the children of the max representative
                if (Integer.parseInt(subsets[label].label) == maxRep) { //all elements in subsets[] that have maxRep

                    subsets[label] = subsets[minRep]; // change their representative to the minRep

                }
            }

        }
    }
  
    public void displayResultingMST() {
        // Office No. A – Office No. B :
        // Output as required: line length: x
        for (int i = 0; i < MSTresultList.length - 1; i++) {
            
            System.out.print("Office No. ");
                
            Vertex vf = MSTresultList[i].source;
                   
            vf.displayInfo();
            
            System.out.print(" - ");
            
            System.out.print("Office No. ");
            
            Vertex vs = MSTresultList[i].target;
            
            vs.displayInfo();
            
            System.out.print(" : line length: " + (MSTresultList[i].weight) + " ");
            
            Edge e = MSTresultList[i];
            
            e.displayInfo();
            
            System.out.println();
        }
    }

    @Override
    public void displayMSTcost() {
        System.out.println("The cost of designed phone network: " + this.cost);
    }
}
