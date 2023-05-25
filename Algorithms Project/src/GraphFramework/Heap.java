package GraphFramework;


import java.util.Arrays;

import java.util.Map;

import java.util.Set;

/*
 *  @authors Asil, Qamar, Aroub,Khalida
 * B9A
 * CPCS-324
 * Project Code
 * 18th may. 2023
 */
// Java Program to Implement Heaps
// by Illustrating Min Heap
//class Heap {
//
//    // Member variables of this class
//   HashMap<Vertex, Integer> Heap;
//    int size;
//    int maxsize;
//    //Graph graph = new Graph();
//    // Initializing front as static with unity
//    static final int FRONT = 1;
//
//    // Constructor of this class
//    public Heap(int maxsize) {
//
//        // This keyword refers to current object itself
//        this.maxsize = maxsize;
//        this.size = 0;
//
//        Heap = new HashMap<>();
//        for(int i=0; i<maxsize;i++){
//        Heap.put(null, Integer.MAX_VALUE);}
//
//    }
//
//    // Method 1
//    // Returning the position of
//    // the parent for the node currently
//    // at pos
//    private int parent(int pos) {
//        return pos / 2;
//    }
//
//    // Method 2
//    // Returning the position of the
//    // left child for the node currently at pos
//    private int leftChild(int pos) {
//        return (2 * pos);
//    }
//
//    // Method 3
//    // Returning the position of
//    // the right child for the node currently
//    // at pos
//    private int rightChild(int pos) {
//        return (2 * pos) + 1;
//    }
//
//    // Method 4
//    // Returning true if the passed
//    // node is a leaf node
//    private boolean isLeaf(int pos) {
//
//        return (pos > (size / 2));
//    }
//
//    // Method 5
//    // To swap two nodes of the heap
//    private void swap(int fpos, int spos) {
//
//        Edge tmp;
//        tmp = Heap[fpos];
//
//        Heap[fpos] = Heap[spos];
//        Heap[spos] = tmp;
//    }
//
//    // Method 6
//    // To heapify the node at pos
//    private void minHeapify(int pos) {
//        if (!isLeaf(pos)) {
//            int swapPos = pos;
//            // swap with the minimum of the two children
//            // to check if right child exists. Otherwise default value will be '0'
//            // and that will be swapped with parent node.
//            if (rightChild(pos) <= size) {
//                swapPos = Heap.get(leftChild(pos)) < Heap.get(rightChild(pos)) ? leftChild(pos) : rightChild(pos);
//            } else {
//                swapPos = leftChild(pos);
//            }
//
//            if (Heap.get(leftChild(pos))!=null && Heap.get((pos)) > Heap.get(leftChild(pos)) || Heap.get(rightChild(pos))!=null && Heap.get((pos)) > Heap.get(rightChild(pos))) {
//                swap(pos, swapPos);
//                minHeapify(swapPos);
//            }
//
//        }
//    }
//
//    // Method 7
//    // To insert a node into the heap
//    public void insert(Vertex target, int weight) {
//
//        if (size >= maxsize) {
//            return;
//        }
//
//        Heap.put(target,weight);
//        int current = size;
//
//        
//       //if(Heap[current].weight==0){
//        while (Heap.get(current) < Heap.get(parent(current))) {
//            swap(current, parent(current));
//            current = parent(current);
//        }//}
//    }
//
//    // Method 8
//    // To print the contents of the heap
//    public void print() {
//        for (int i = 1; i <= size / 2; i++) {
//
//            // Printing the parent and both childrens
//            System.out.print(
//                    " PARENT : " + Heap.get(i)
//                    + " LEFT CHILD : " + Heap.get(2 * i)
//                    + " RIGHT CHILD :" + Heap.get(2 * i + 1));
//
//            // By here new line is required
//            System.out.println();
//        }
//    }
//
//    // Method 9
//    // To remove and return the minimum
//    // element from the heap
//    public Vertex remove() {
//        minHeapify(FRONT);
//
//        Vertex popped =Heap.get(FRONT);
//        Heap[FRONT] = Heap[size--];
//        minHeapify(FRONT);
//
//        return popped;
//    }
//
//    boolean isEmpty() {
//        return size == 0;
//    }
//
//}
//
class MinHeap {

    Map<String, Integer> vertexWeight;
    String[] verticesKeyArray;

    MinHeap(Map<String, Integer> vertexWeight){

        this.vertexWeight = vertexWeight;
    }

    public void heapify(String[] vertices,int root, int length) {

        int left = (2*root)+1;
        int right = (2*root)+2;
        int min = root;

        if (left < length && right <= length && vertexWeight.get(vertices[right]) < vertexWeight.get(vertices[left])) {

            min = right;
        }
        else if (left <= length){

            min = left;
        }

        if (vertexWeight.get(vertices[root]) > vertexWeight.get(vertices[min])) {

            String tmp = vertices[root];
            vertices[root] = vertices[min];
            vertices[min] = tmp;

            heapify(vertices, min, length);
        }
    }

    public void buildHeap() {

        Set<String> verticesSet = vertexWeight.keySet();

        // Now convert the above keys to an Array.
        String[] vertices = new String[verticesSet.size()];
        verticesSet.toArray(vertices);

        int length = vertices.length-1;

        for (int parent = (length-1)/ 2; parent >= 0; parent--)
            heapify(vertices, parent, length);

        verticesKeyArray = vertices;

    }

    public void updateHeap(String vertex, int length) {

        vertexWeight.put(vertex, length);

        // Get all the keys (i.e. Vertices ) for the Map.
        Set<String> verticesSet = vertexWeight.keySet();

        // Now convert the above keys to an Array.
        String[] vertices = new String[verticesSet.size()];
        verticesSet.toArray(vertices);

        int len = vertices.length-1;

        for (int parent = (len-1)/ 2; parent >= 0; parent--)
            heapify(vertices, parent, len);

        verticesKeyArray = vertices;
    }

    boolean containsVertex(String vertex){

        return vertexWeight.containsKey(vertex);
    }

    public String deleteMin() {

        String temp = verticesKeyArray[0];

        int len = verticesKeyArray.length-1;

        verticesKeyArray[0] = verticesKeyArray[len];

        vertexWeight.remove(temp);

        verticesKeyArray = Arrays.copyOf(verticesKeyArray, len);

        if (len>0)
            heapify(verticesKeyArray, 0, len-1);

        return temp;
    }

    int getWeight(String vertex){

        return vertexWeight.get(vertex);
    }

    public boolean empty() {

        return verticesKeyArray.length <= 0;

    }
}
