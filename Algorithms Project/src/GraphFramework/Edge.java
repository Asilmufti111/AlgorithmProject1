package GraphFramework;


/*
 *  @authors Asil, Qamar, Aroub,Khalida
 * B9A
 * CPCS-324
 * Project Code
 * 18th may. 2023
 */
public class Edge implements Comparable<Edge> {

    int weight;
    Vertex source;
    Vertex target;
    Vertex parent;

    public Edge() {
        source = new Vertex();
        target = new Vertex();
        parent = new Vertex();
    }

    public Edge(Vertex source, Vertex target, int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }

    public int parent(int pos) { // return the parent of the edge's node in the heap
        return pos / 2;
    }

    @Override
    public int compareTo(Edge e) { // compare based on weights
        if (this.weight > e.weight) {
            return 1;
        } else if (this.weight == e.weight) {
            return 0;
        } else {
            return -1;
        }
    }

    public void displayInfo() {

    }

}
