/*
 *  @authors Asil, Qamar, Aroub,Khalida
 * B9A
 * CPCS-324
 * Project Code
 * 18th may. 2023
 */

public class Heap {
    
    public Heap(int noOfVertices) { // public constructor
	heapArray = new int[noOfVertices+1];
	p_v = new int[noOfVertices+1]; 
	key_v = new int[noOfVertices+1];
	}
	
	public void insert(int v, int key_value) { // insert new value into heap
		s = s+1; // tree pointer
		heapArray[s] = v;
		p_v[v] = s;
		key_v[v] = key_value;
		heapify_up(s);
	}
		
	private void heapify_up(int i) { // organize tree from least to greatest
		int j = 0, temp = 0;
		while(i>1) {
			j = (int) Math.floor(i/2);
			if (key_v[heapArray[i]]<key_v[heapArray[j]]) { // if the current element is smaller than the entered element
				temp = heapArray[i];                   // swap the elements 
				heapArray[i] = heapArray[j];
				heapArray[j] = temp;
				p_v[heapArray[i]] = i;
				p_v[heapArray[j]] = j;
				i=j;				
			} else break;
		}	
	}

	public int extract_min() { 
		int ret = heapArray[1]; // extract the first element (meaning the smallest element)
		heapArray[1] = heapArray[s];
		p_v[heapArray[1]] = 1;
		s = s-1;
		if (s >=1) {
			heapify_down(1);
			}
		return ret;
	}

	private void heapify_down(int i) { // removes last element from tree
		int j,temp;                // replaces it with last element from the bottom
		while(2*i<=s) {            // perculates down until it reaches proper position
			if(2*i==s || key_v[heapArray[2*i]]<=key_v[heapArray[2*i+1]]) {
				j = 2*i;
			}
			else j = 2*i+1;
			if(key_v[heapArray[j]]<key_v[heapArray[i]]) {
				temp = heapArray[i];
				heapArray[i] = heapArray[j];
				heapArray[j] = temp;
				p_v[heapArray[i]] = i;
				p_v[heapArray[j]] = j;
				i=j;
			}
			else break;
		}
	}
	
	public void decrease_key(int v, int key_value) {
		key_v[v] = key_value;
		heapify_up(p_v[v]);
	}
	
	protected int s;
	protected int[] heapArray;
	protected int[] p_v;
	protected int[] key_v;
}
