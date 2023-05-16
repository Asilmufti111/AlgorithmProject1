package GraphFramework;

import java.util.LinkedList;

/*
 *  @authors Asil, Qamar, Aroub,Khalida
 * B9A
 * CPCS-324
 * Project Code
 * 18th may. 2023
 */
public class Vertex {

    String label;
    Boolean isVisited;
    LinkedList<Edge> adjList;

    public Vertex() {
        adjList = new LinkedList<Edge>();
    }

    public Vertex(String label) { //creating new vertex
        this.label = label; //value of vertex
        this.isVisited = false; //vertex not visited yet 
        adjList = new LinkedList<Edge>(); //create new linkedlist for each vertex (for its edges)
    }

    public void displayInfo() {
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
