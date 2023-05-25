package GraphFramework;

import java.util.*;

/*
 *  @authors Asil, Qamar, Aroub,Khalida
 * B9A
 * CPCS-324
 * Project Code
 * 18th may. 2023
 */

// This Class Support Kruskal Algorithm Using Quick Find implementations

public class KruskalAlg extends MSTAlgorithm {
		// Data fields
		private int cost = 0;
		
	/**
	 * KruskalAlg Constructor
	 * @param graph
	 */
	public KruskalAlg(Graph graph) {
		MSTresultList = new LinkedList<>(); // MST List
	}
	
	/**
	 * Shows Resulting MST
     * @param graph
	 */
	@Override
	public void findMST(Graph graph) {
		
		Vertex vv; // Vertex source
		Vertex vu; // Vertex target
		Edge edge; // Vertex edge
		ArrayList<Edge> edges = new ArrayList<>(); //PriorityQueue to store edges weights
		
		// Loop through ALL vertices
		for(int i=0; i< graph.verticesNo; i++) {
			vv = graph.vertices.get(i);
			// Loop through adjacent list of this vertex
			for(int j=0; j<vv.adjList.size() ; j++) {
				edges.add(vv.adjList.get(j));	
			} // end of inner for-loop
		} // end of outer for-loop
		Collections.sort(edges);

		// Sort All Edges in non-decreasing Order 
		//Collections.sort(edges); // Edge class implement Java Comparable interface (compare weight)
		
		/* Kruskal2 Algorithm Scenario: Loop through minimum-weight edges,
		 * check each set of these vertices, if they're in different sets we merge them,
		 * and add them to the MST as disjoint subset of Vertex u* & v* with weight x (as edge)
		 * if they're in the same set (discovered by findSet) we ignore it simply and continue to the next.
		 * So,
		 * 1. makeSet of All vertices
		 * 2. findSet if they're in different disjoint subsets do (3), else ignore.
		 * 3. Union sets
		 */

		// 1. (MakeSet) Make Set for Each Vertex
		Vertex[] subset = new Vertex[graph.verticesNo]; // Set the DS as the number of vertices 
		makeSet(subset,graph); // Make set for each vertex
		int encounter = 0; 
		
		// Loop through ALL edges
		while(encounter <graph.verticesNo-1) {
			
			// Get Minimum-weight Edge & its source & target
			edge = edges.remove(encounter);
			vv = edge.source;
			vu = edge.target;
			
			// 2. (findSet)Find Representative Subset from the QuickFind Disjoint Sets
			if(!findSet(Integer.parseInt(subset[Integer.parseInt(vv.label)].label), Integer.parseInt(subset[Integer.parseInt(vu.label)].label))) {

				// 3. (Union) Append VT to VU & and update their representative value; 
				union(subset, vv, vu);			
				
				MSTresultList.add(encounter, edge); // Add the target edge to the MST list	 
				cost += MSTresultList.get(encounter).weight; // Get cost of minimum-weight edges (MST)

				encounter++; // increment number of edges encountered
			} // End of if-statement
		} // End of while-loop
		
	} // End of Method
	
	/**
     * this method used to create one-element set{x} for all the V in the graph 
     * @param subset 
     * @param graph 
     */
    public void makeSet(Vertex[] subset,Graph graph) {
    	
    	/* loop through # of vertex
    	   create vertex of each vertex in the array
    	   making sets means making A alone in set and so on each index hold its own value vertex    
    	 */
    	for(int i=0; i < subset.length; i++) {
    		Vertex vn = graph.vertices.get(i);
    		subset[i] = vn;
    	}
    } // End of makeSet Method
    
    /**
     * 
     * @param v1
     * @param v2
     * @return
     */
    public boolean findSet(int v1, int v2){
    	return v1 == v2;
    } // End of FindSet Method
    
    /**
     * 
     * @param subset
     * @param src
     * @param trgt
     */
    public void union(Vertex[] subset, Vertex src, Vertex trgt) {	
    	int src_Rep = Integer.parseInt(subset[Integer.parseInt(src.label)].label); // get VV representative 
    	int trgt_Rep = Integer.parseInt(subset[Integer.parseInt(trgt.label)].label); // get VU representative
    	
    	boolean is_src_self_Rep = findSet(Integer.parseInt(src.label), src_Rep); // Find if VV have representative or not
    	boolean is_trgt_self_Rep = findSet(Integer.parseInt(trgt.label), trgt_Rep); // Find if VU have representative or not
    		
    	/** Loop Scenario:
    	 * We found for example vvNoRepresentative value (true) when it actually had its own as representative
    	 * Because VV actually was the representative of the set. therefore, it had it own number,
    	 * So, performing the loop below will let us know if this is actually happened or not.
    	 * (if VV is the representative of the set so that's why VV have its own number),
    	 * this is why we set the booleans variable as false again because -> VV has representative, and its VV itself.
    	 */
    	
    	// Check if current VV & VU are representative of set 
    	for(int i=0; i<subset.length; i++) {
    		
    		// Check the (quickFindDs array if VV is representative of other vertex) && (excluding their own)
    		if(src_Rep == Integer.parseInt(subset[i].label) && (i != Integer.parseInt(src.label))) {
    			is_src_self_Rep = false; // false when VV have itself is other vertex representative
    		} // End of if-statement
    		
    		// Check the (quickFindDs array if VU is representative of other vertex) && (excluding their own)
    		if(trgt_Rep == Integer.parseInt(subset[i].label) && (i != Integer.parseInt(trgt.label))) {
    			is_trgt_self_Rep = false; // false when VV have itself is other vertex representative
    			
    		} // End of if-statement
    		
    	} // End of for-loop
    	
    	
    	// if VV have -a- representative and VU have -no- representative OR VV & VU (both) have -no- representative
    	if( ((!is_src_self_Rep) && (is_trgt_self_Rep)) || (is_src_self_Rep && is_trgt_self_Rep)) {
    		
    		// Make VV is the new representative
    		subset[Integer.parseInt(src.label)] = subset[Integer.parseInt(src.label)];
    		subset[Integer.parseInt(trgt.label)] = subset[Integer.parseInt(src.label)];
    	} // End of if-statement
    	
    	
    	// if VV have -no- representative and VU have -a- representative
    	else if (is_src_self_Rep && (!is_trgt_self_Rep)) {
    		subset[Integer.parseInt(src.label)] = subset[Integer.parseInt(trgt.label)];
    	} // End of else-if
    	
    	
    	// VV & VU (both) have -a- representative
    	else {
    		
       	    int max_Rep = Math.max(src_Rep, trgt_Rep); // Get max representative to overwrite its children
    		int min_Rep = Math.min(src_Rep, trgt_Rep); // Get minimum to set it as the new representative
    		
    		// Loop through the QuickFind Disjoint Subset
	    	 for(int i=0; i<subset.length; i++) {
	    		 
	    		 // Find all the children of the max representative
	    		 if(Integer.parseInt(subset[i].label) == max_Rep) {
	    			 subset[i] = subset[min_Rep]; // Update all representatives to the minimum Representative
	    			 
	    		 } // End of if-statement
	    	 } // End of for-loop
    	} // End of else
    } // End of Union method
    
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
