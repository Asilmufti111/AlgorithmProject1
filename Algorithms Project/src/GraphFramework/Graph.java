package GraphFramework;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

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
    Vector<Vertex> vertices = new Vector();

    public Graph(int verticesNo, int edgeNo, boolean isDigraph) {
        this.verticesNo=verticesNo;
        this.edgeNo=edgeNo;
        this.isDigraph = isDigraph;
        makeGraph();
    }
    public Graph(File graphFile) throws FileNotFoundException{
        readGraphFromFile(graphFile);
    }

    public Graph() {
    }

    public void makeGraph() {
        
        vertices=new Vector(verticesNo);

        for (int i = 0; i < verticesNo; i++) {
            vertices.add(createVertex(i + ""));} //store all vertices 

        for (int i = 0; i < verticesNo - 1; i++) { //at least connect all vertices
            //addEdge(source vertex, target vertex, random weight)	
            //ensure last vertex is only a target and not a source vertex
            addEdge(vertices.get(i), vertices.get(i + 1), (int) (1 + Math.random() * 10));
            if (i == verticesNo - 2) {
                addEdge(vertices.get(verticesNo - 1), vertices.get(0), (int) (1 + Math.random() * 10));

            }

        }

        // fill rest of edges (to comly with number of edges in file)
        for (int i = 0; i < (edgeNo - (verticesNo)); i++) {
            int sourceInd; //random source vertex
            int targetInd;

            do {
                sourceInd = (int) (Math.random() * verticesNo); //random source vertex
                targetInd = (int) (Math.random() * verticesNo); //random target vertex
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

        verticesNo = scan.nextInt(); // read and store number of vertice in total 
        
        edgeNo = scan.nextInt(); // read and store number of edges in total 
      
        vertices = new Vector(verticesNo);
        
        if(!isDigraph){
        
            edgeNo*=2;}
                 
        for(int i= 0; i<edgeNo;i++){

            String source = (int)(scan.next().charAt(0)) -65 + ""; //read source label and assign it a number e.g A-->1 B-->2 ...etc.
            
            String target = (int)(scan.next().charAt(0)) - 65 + ""; // read target label and assign it a number 
            
            int weight = scan.nextInt();//read weight of edge between source and target vertices        
            
            addEdge(createVertex(source), createVertex(target), weight); //add edge between source and target vertex with specific weight 

        }
        scan.close();

    }

    public Vertex createVertex(String label) {
        return null;
    }

    public Edge createEdge(Vertex v, Vertex u, int w) {
        return  null;
    }

    public Edge addEdge(Vertex v, Vertex u, int w) {
        
        boolean srcfound=false;
        boolean trgtfound=false;
        
        for(int i = 0; i<vertices.size();i++){
        
            if(vertices.get(i).getLabel().equals(v.label)){
            
                srcfound=true;}
        
            else if(vertices.get(i).getLabel().equals(u.label)){
            
                trgtfound=true;}}
        
        if(!srcfound){
        
            vertices.add(v);}
                
        if(!trgtfound){
        
            vertices.add(u);}

        Edge newEdge = createEdge(vertices.get(vertices.indexOf(v)), vertices.get(vertices.indexOf(u)), w); //create new edge object between v and u 

        vertices.get(vertices.indexOf(v)).adjList.add(newEdge); //add this edge to the adjacency list of the v vertex

        if (!isDigraph) { //if graph is undirected 	

            newEdge = createEdge(vertices.get(vertices.indexOf(u)), vertices.get(vertices.indexOf(v)), w);  //add edge from u to v (opposite way)  		 
            
            vertices.get(vertices.indexOf(u)).adjList.add(newEdge); //add this edge to the adjacency list of the v vertex

        }

        return newEdge; //if edge created, created edge will be returned by method

    }
}

