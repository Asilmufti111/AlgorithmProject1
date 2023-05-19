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

    LinkedList<Edge> MSTresultList;

    Graph graph;

    public MSTAlgorithm() {
        MSTresultList = new LinkedList<Edge>();

        graph = new Graph();
    }

    public abstract void findMST(Graph Graph);

    public abstract void displayResultingMST();

}
