package GraphFramework;


/*
 *  @authors Asil, Qamar, Aroub,Khalida,Huda
 * B9A
 * CPCS-324
 * Project Code
 * 18th may. 2023
 */
public class Edge implements Comparable<Edge> {

    private int weight;
    private Vertex source;
    private Vertex target;
    private Vertex parent;



    public Edge(Vertex source, Vertex target, int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Vertex getSource() {
        return source;
    }

    public void setSource(Vertex source) {
        this.source = source;
    }

    public Vertex getTarget() {
        return target;
    }

    public void setTarget(Vertex target) {
        this.target = target;
    }

    public Vertex getParent() {
        return parent;
    }

    public void setParent(Vertex parent) {
        this.parent = parent;
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
        System.out.print("Office No. " + source.getLabel());
        System.out.print(" - ");
        System.out.print("Office No. " + target.getLabel());
        System.out.print(" : line length: " + weight + " ");
    }

}
