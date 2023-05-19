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
    // Map<Vertex, String> subset = new HashMap<>();

    //ArrayList<String> subset = new ArrayList<>();
    LinkedList<Edge> edges;
    private int cost = 0;

    public KruskalAlg(Graph graph) {
        MSTresultList = new LinkedList<>(); // MST List
    }

    public void findMST(Graph graph) {

        edges = new LinkedList<>();

        for (Vertex i : graph.vertices) {    //go thro all vertice

            for (int j = 0; j < i.adjList.size(); j++) {//loop thro all source vertices

                edges.add(i.adjList.get(j));
            }
        }//store ALL edges in edges<>()}

        Collections.sort(edges);// Sort All Edges in decreasing Order  (using comparable interface in Edge class)

        // 1. (MakeSet) Make Set for Each Vertex
        Vertex[] subset = new Vertex[graph.verticesNo]; // Set the DS as the number of vertices 
        makeSet(subset); // Make set for each vertex

        // Loop through ALL edges
        for (int edgeCounter = 0; edgeCounter < graph.vertices.size() - 1;) {
            Edge edge = edges.removeFirst();
//    edge = edges.(edges.size()-1); //remove last element, which has the least weight
//            src = edge.source; //assign source to a vertex
//            trgt = edge.target;//assign target to a vertex
//
//            int srcLabel = Integer.parseInt(src.label); //convert label string->int
//            int trgtLabel = Integer.parseInt(trgt.label);//convert label string->int
            // if source and target vertex are not part of the same set -> perform union between them
            if (!findSet(subset[Integer.parseInt(edge.source.label)].label, subset[Integer.parseInt(edge.target.label)].label)) {

                // make source and target vertex in same subset + update their representative values in the array 
                union(subset, edge.source, edge.target);

                MSTresultList.add(edge); // Add  edge to the MST list	 
                cost += edge.weight; // add edge weight(cost) to total weight

                edgeCounter++; // increment number of edges added
            }
        }

    }

    public void makeSet(Vertex[] quickFindDS) { //make all vertices into singleton sets

        for (int i = 0; i < quickFindDS.length; i++) { //loop through all vertices
            quickFindDS[i] = new Vertex(i + ""); //create singleton set 

        } //edit label with singleton set

    }

    public boolean findSet(String vertex1, String vertex2) { //if element in one subset is found in the other

        return vertex1.equals(vertex2);// return true
    }

    public void union(Vertex[] subsets, Vertex src, Vertex trgt) {

        int srcLabel = Integer.parseInt(src.label);//convert label (String ->int)

        int trgtLabel = Integer.parseInt(trgt.label);//convert label (String ->int)

        int srcRep = Integer.parseInt(subsets[srcLabel].label); // get representative of source vertex

        int trgtRep = Integer.parseInt(subsets[trgtLabel].label); //// get representative of target vertex

        boolean src_Self_Rep = findSet(src.label, subsets[srcLabel].label); // Find if VV have representative or not

        boolean trgt_Self_Rep = findSet(trgt.label, subsets[trgtLabel].label); // Find if VU have representative or not

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

