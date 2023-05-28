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

class MinHeap {

    private Map<String, Integer> vertexWeight;
    private String[] verticesKeyArray;

    MinHeap(Map<String, Integer> vertexWeight){//add vertex + the weight of the edge from source to this vertex

        this.vertexWeight = vertexWeight;
    }

    public void heapify(String[] vertices,int root, int length) {

        int left = (2*root)+1; //left element 
        int right = (2*root)+2; //right element
        int min = root; //root element

        if (left < length && right <= length && vertexWeight.get(vertices[right]) < vertexWeight.get(vertices[left])) {

            min = right; //both left and right on left side of array and rigth is smaller than left, maek min = right
        }
        else if (left <= length){

            min = left;//left is smaller than right, make min = left
        }

        if (vertexWeight.get(vertices[root]) > vertexWeight.get(vertices[min])) { //check if root is smaller then the 'min'

            String tmp = vertices[root];
            vertices[root] = vertices[min];
            vertices[min] = tmp;

            heapify(vertices, min, length); //recursive call to fix all minheap
        }
    }

    public void buildHeap() {

        Set<String> verticesSet = vertexWeight.keySet(); //map is not perfect for heap implementation so we use set for it 

        // Now convert the above keys to an Array.
        String[] vertices = new String[verticesSet.size()];
        verticesSet.toArray(vertices);

        int length = vertices.length-1; //length of array

        for (int parent = (length-1)/ 2; parent >= 0; parent--) //divide array into 2 parts and heapify 
            heapify(vertices, parent, length);

        verticesKeyArray = vertices;

    }

    public void updateHeap(String vertex, int length) {

        vertexWeight.put(vertex, length); //update value in map

        // Get all the keys (i.e. Vertices ) for the Map.
        Set<String> verticesSet = vertexWeight.keySet();

        // Now convert the above keys to an Array.
        String[] vertices = new String[verticesSet.size()];
        verticesSet.toArray(vertices);

        int len = vertices.length-1;//length of array 

        for (int parent = (len-1)/ 2; parent >= 0; parent--) //get left and right 
            heapify(vertices, parent, len); //fix heap orders

        verticesKeyArray = vertices;
    }

    boolean containsVertex(String vertex){ //vertex found?

        return vertexWeight.containsKey(vertex);
    }

    public String deleteMin() {

        String temp = verticesKeyArray[0];//delete from root

        int len = verticesKeyArray.length-1; //calculet new length 

        verticesKeyArray[0] = verticesKeyArray[len];

        vertexWeight.remove(temp); //remove root

        verticesKeyArray = Arrays.copyOf(verticesKeyArray, len);//copy arraY with new length as capacity 

        if (len>0)
            heapify(verticesKeyArray, 0, len-1);//if there are elements ->heapify

        return temp; //return removed vertex label 
    }

    int getWeight(String vertex){

        return vertexWeight.get(vertex);
    }

    public boolean empty() {

        return verticesKeyArray.length <= 0;

    }
}
