package GraphFramework;

import java.io.File;
import java.io.FileNotFoundException;
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
    Boolean isDigraph;
    Vertex[] vertices;

    public Graph(int verticesNo, int edgeNo, boolean isDigraph) {
        this.verticesNo = verticesNo;
        this.edgeNo = edgeNo;
        this.isDigraph = isDigraph;
        this.vertices = new Vertex[verticesNo];
    }

    public Graph() {
    }

    public void makeGraph(int verticesNo, int edgeNo) {

        for (Vertex i : vertices) {
            int label = Integer.parseInt(i.label);
            vertices[label] = new Vertex(i.label);
        } //store all vertices 

        for (int i = 0; i < verticesNo - 1; i++) { //at least connect all vertices
            //addEdge(source vertex, target vertex, random weight)	
            //ensure last vertex is only a target and not a source vertex
            addEdge(vertices[i].label, vertices[i + 1].label, (int) (1 + Math.random() * 10));
        }

        // fill rest of edges (to comly with number of edges in file)
        for (int i = 0; i < (edgeNo - (verticesNo - 1)); i++) {
            int U = (int) (Math.random() * verticesNo); //random source vertex
            int V = (int) (Math.random() * verticesNo); //random target vertex

            if (U != V) {//if source not equal target

                for (int j = 0; j < vertices[U].adjList.size(); j++) { //go through source adjacency list to check all edges

                    if (!(vertices[U].adjList.get(j).target.label.equals(V))) //if none of it is targets are equal to current target
                    {
                        break;
                    }//if source and target don't have an edge break out of loop
                }
            } else {
                continue; //if source == target go through next iteration 
            }
            //if soure and target don't have an edge and they are not equal, create new edge
            addEdge(vertices[U].label, vertices[V].label, (int) (1 + Math.random() * 10));

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

        if (!isDigraph) { //if graph is undirected     
            All_Edges *= 2;
        } //the edges go from source to target and from target to source

        vertices = new Vertex[All_Vertices]; // Create array of verices to store all vertices 

        while (edgeNo < All_Edges) {
            String v = (scan.next().charAt(0)) - 65 + ""; //read source label and assign it a number e.g A-->1 B-->2 ...etc.
            String u = (scan.next().charAt(0)) - 65 + ""; // read target label and assign it a number 
            int w = scan.nextInt();//read weight of edge between source and target vertices        
            addEdge(v, u, w); //add edge between source and target vertex with specific weight 

        }
        scan.close();

    }

    public Vertex createVertex(String label) {
        return new Vertex(label);
    }

    public Edge createEdge(Vertex v, Vertex u, int w) {
        return new Edge(v, u, w);
    }

    public Edge addEdge(String v, String u, int w) {

        int intV = Integer.parseInt(v); //typecast source label (String->int) to be able to access its index in vertices[]

        int intU = Integer.parseInt(u); //typecast target label (String->int) to be able to access its index in vertices[]

        if (vertices[intV] == null) { 	 //if source vertex is not in array 	

            verticesNo++;           //increment no of vertices

            vertices[intV] = new Vertex(v); // add this source vertex in vertice[]
        }

        if (vertices[intU] == null) {//if target vertex is not in array 	
            verticesNo++;  //increment no of vertices
            vertices[intU] = new Vertex(u); // add this target vertex in vertice[]     		
        }

        Edge createEdge = new Edge(vertices[intV], vertices[intU], w); //create new edge object between source and target 

        edgeNo++; //increment no of edegs

        vertices[intV].adjList.add(createEdge); //add this edge to the adjacency list of the source vertex

        if (!isDigraph) { //if graph is undirected 	

            createEdge = new Edge(vertices[intU], vertices[intV], w);  //add edge from target to source (opposite way)  		 

            edgeNo++; // increment no of edegs

            vertices[intU].adjList.add(createEdge);//add this edge to the adjacency list of the target vertex
        }

        return createEdge; //if edge created, created edge will be returned by method

    }
}

