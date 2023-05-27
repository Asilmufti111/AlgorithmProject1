package GraphFramework;

import java.util.LinkedList;


/*
 *  @authors Asil, Qamar, Aroub,Khalida
 * B9A
 * CPCS-324
 * Project Code
 * 18th may. 2023
 */
public abstract class MSTAlgorithm {

    LinkedList<Edge> MSTresultList; //to stor MST 

    Graph graph; //graph to store all vertices 

    public MSTAlgorithm() { //perform each algorithm
        MSTresultList = new LinkedList<>();

        graph = new Graph();
    }

    public abstract void findMST(Graph Graph);//to be implemented 

    public abstract void displayResultingMST();

}
