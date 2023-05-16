package GraphFramework;

/*
 *  @authors Asil, Qamar, Aroub,Khalida
 * B9A
 * CPCS-324
 * Project Code
 * 18th may. 2023
 */
// Java Program to Implement Heaps by Illustrating Min edges
public class Heap {

    // variables of this class
    Graph graph;
    Edge[] edges;
    int size = 0;

    // Initializing front as static with unity
    private static final int FRONT = 1;

    // Constructor of this class
    public Heap() {
        // This keyword refers to current object itself
        this.size = 0;
        edges = new Edge[graph.verticesNo + 1];;
        edges[0].weight = Integer.MIN_VALUE;
    }

    // Returning the position of the
    // left child for the node currently at pos
    private int leftChild(int pos) {
        return (2 * pos);
    }

    // Returning the position of
    // the right child for the node currently at pos
    private int rightChild(int pos) {
        return (2 * pos) + 1;
    }

    // Returning true if the passed
    // node is a leaf node
    private boolean isLeaf(int pos) {

        if (pos > (size / 2)) {
            return true;
        }

        return false;
    }

    // To swap two nodes of the edges
    private void swap(int fpos, int spos) {

        int tmp;
        tmp = edges[fpos].weight;
        edges[fpos] = edges[spos];
        edges[spos].weight = tmp;
    }

    // To heapify the node at pos
    private void minHeapify(int pos) {
        if (!isLeaf(pos)) {
            int swapPos = pos;
            // swap with the minimum of the two children
            // to check if right child exists. Otherwise default value will be '0'
            // and that will be swapped with parent node.
            if (rightChild(pos) <= size) {
                swapPos = edges[leftChild(pos)].weight < edges[rightChild(pos)].weight ? leftChild(pos) : rightChild(pos);
            } else {
                swapPos = leftChild(pos);
            }

            if (edges[pos].weight > edges[leftChild(pos)].weight || edges[pos].weight > edges[rightChild(pos)].weight) {
                swap(pos, swapPos);
                minHeapify(swapPos);
            }

        }
    }

    // To insert a node into the edges
    public void insert(Edge element) {

        if (size >= edges.length) {
            return;
        }

        edges[++size] = element;
        int current = size;

        while (edges[current].weight < edges[edges[current].parent(current)].weight) {
            swap(current, edges[current].parent(current));
            current = edges[current].parent(current);
        }
    }

    // To print the contents of the edges
    public void displayInfo() {
        for (int i = 1; i <= size / 2; i++) {

            // Printing the parent and both childrens
            System.out.print(" PARENT : " + edges[i]
                    + " LEFT CHILD : " + edges[2 * i]
                    + " RIGHT CHILD :" + edges[2 * i + 1]);

            // By here new line is required
            System.out.println();
        }
    }

    // To remove and return the minimum
    // element from the edges
    public Edge remove() {

        Edge popped = edges[FRONT];
        edges[FRONT] = edges[size--];
        minHeapify(FRONT);

        return popped;
    }

    boolean isEmpty() {
        return size == 0;
    }

}

