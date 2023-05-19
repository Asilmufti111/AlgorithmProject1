package GraphFramework;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

/*
 *  @authors Asil, Qamar, Aroub,Khalida
 * B9A
 * CPCS-324
 * Project Code
 * 18th may. 2023
 */
public class Graph {

    int verticesNo;
    int edgeNo;
    Boolean isDigraph = false;
    LinkedList< Vertex> vertices = new LinkedList<>();

    public Graph(int verticesNo, int edgeNo, boolean isDigraph) {
        this.isDigraph = isDigraph;
    }

    public Graph() {
    }

    public void makeGraph(int Vnum, int Enum) {

        for (int i = 0; i < Vnum; i++) {
            vertices.add(createVertex(i + ""));
            verticesNo++;

        } //store all vertices 

        for (int i = 0; i < Vnum - 1; i++) { //at least connect all vertices
            //addEdge(source vertex, target vertex, random weight)	
            //ensure last vertex is only a target and not a source vertex
            addEdge(vertices.get(i), vertices.get(i + 1), (int) (1 + Math.random() * 10));
            if (i == Vnum - 2) {
                addEdge(vertices.get(Vnum - 1), vertices.get(0), (int) (1 + Math.random() * 10));

            }

        }

        // fill rest of edges (to comly with number of edges in file)
        for (int i = 0; i < (Enum - (Vnum)); i++) {
            int sourceInd; //random source vertex
            int targetInd;

            do {
                sourceInd = (int) (Math.random() * Vnum); //random source vertex
                targetInd = (int) (Math.random() * Vnum); //random target vertex
                if (sourceInd != targetInd) {
                    for (int j = 0; j < vertices.get(sourceInd).adjList.size(); j++) { //go through source adjacency list to check all edges

                        if (!(vertices.get(sourceInd).adjList.get(j).target.label.equals(vertices.get(targetInd).label))) //if none of it is targets are equal to current target
                        {
                            break;
                        }//if source and target don't have an edge break out of loop
                    }
                }
            } while (sourceInd == targetInd);

            //if soure and target don't have an edge and they are not equal, create new edge
            addEdge(vertices.get(sourceInd), vertices.get(targetInd), (int) (1 + Math.random() * 10));

        }
    }

    public void readGraphFromFile(File fileName) throws FileNotFoundException {

        Scanner scan = new Scanner(fileName); // Create Scanner object to read from our file called fileName 

        String graphClassification = scan.nextLine(); // what is the type of the graph (directed/undirected)
        if (graphClassification.equalsIgnoreCase("digraph 0")) { //if graph is digraph 0 
            isDigraph = false; // then it is undirected 
        } else if (graphClassification.equalsIgnoreCase("digraph 1")) {//if graph is digraph 1
            isDigraph = true; //// then it is directed 
        }

        int All_Vertices = scan.nextInt(); // read and store number of vertice in total 
        int All_Edges = scan.nextInt(); // read and store number of edges in total 

        while (edgeNo < All_Edges) {
            String source = (scan.next().charAt(0)) - 65 + ""; //read source label and assign it a number e.g A-->1 B-->2 ...etc.
            String target = (scan.next().charAt(0)) - 65 + ""; // read target label and assign it a number 
            int weight = scan.nextInt();//read weight of edge between source and target vertices        
//            addEdge(source, target, weight); //add edge between source and target vertex with specific weight 

        }
        scan.close();

    }

    public Vertex createVertex(String label) {
        return new Vertex(label);
    }

    public Edge createEdge(Vertex v, Vertex u, int w) {
        return new Edge(v, u, w);
    }

    public Edge addEdge(Vertex v, Vertex u, int w) {

        Edge newEdge = createEdge(vertices.get(vertices.indexOf(v)), vertices.get(vertices.indexOf(u)), w); //create new edge object between v and u 

        vertices.get(vertices.indexOf(v)).adjList.add(newEdge); //add this edge to the adjacency list of the v vertex
        edgeNo++; //increment no of edegs

        if (!isDigraph) { //if graph is undirected 	

            newEdge = createEdge(vertices.get(vertices.indexOf(u)), vertices.get(vertices.indexOf(v)), w);  //add edge from u to v (opposite way)  		 
            vertices.get(vertices.indexOf(u)).adjList.add(newEdge); //add this edge to the adjacency list of the v vertex
            edgeNo++; //increment no of edegs

        }

        return newEdge; //if edge created, created edge will be returned by method

    }
}

