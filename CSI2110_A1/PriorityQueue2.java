/*
 * Author: Ben Miller
 * Student ID: 300297574
 * 
 */

import java.util.ArrayList;

public class PriorityQueue2 implements PriorityQueueIF<Pair>{
    
    private Pair[] heap; // max heap (the root/max element is always at index 0)
    private int tailIndex;

    public PriorityQueue2(int capactity){
        heap = new Pair[capactity];
        tailIndex = -1;
    }


    public PriorityQueue2(ArrayList<Pair> list, int capactity){
        heap = new Pair[capactity];

        for(Pair e : list){
            offer(e);
        }
    
    }
    

    // Inserts the specified element into this queue if it is possible to do so immediately 
	// without violating capacity restrictions.
    public boolean offer(Pair e){
        if( size()+1 > heap.length) return false;

        tailIndex++;
        heap[tailIndex] = e;

        upHeap(tailIndex); // push the element up the heap in necessary

        return true;
    }


    // Retrieves and removes the head of this queue, or returns null if this queue is empty.
    public Pair poll(){

        if( isEmpty() ) return null;

        Pair data = heap[0];

        heap[0] = heap[tailIndex];  // put end elem at head
        heap[tailIndex] = null;     // erase duplicated data
        tailIndex--;

        downHeap(0);   

        return data;
    }


    // Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty.
    public Pair peek(){
        return ( size() == 0 ) ? null : heap[0];
    }


    // Returns the number of elements in this queue.
    public int size(){
        return tailIndex+1;
    }


    // Returns true if this queue contains no elements.
    public boolean isEmpty(){
        return tailIndex == -1;
    }

    /*
     * HELPER METHODS
     */

    /**
     * algorithm inspired by my Lab4 submission from HeapPriorityQueue.java
     * @param index
     */
    private void upHeap(int index){
        while (index > 0) {
            int parentIndex = (index-1)/2;

            // only breaks from while loop when current index isn't bigger than
            // either of its parents (it can't go any higher)
            if( heap[index].compareTo(heap[parentIndex]) < 1){
                break;
            }
            // swap
            Pair temp = heap[index];
            heap[index] = heap[parentIndex];
            heap[parentIndex] = temp;

            index = parentIndex;
        }
    }


    /**
     * algorithm inspired by my Lab4 submission from HeapPriorityQueue.java
     * @param index
     */
    private void downHeap(int index){
        int leftChildIndex = index * 2 + 1;
        int rightChildIndex = index * 2 + 2;
        int biggest = index;

        // changes biggest to left or right, as long as either one is:
        // a) a real index in the heap
        // b) bigger than the current (paramaterized) index
        if (leftChildIndex <= tailIndex && heap[leftChildIndex].compareTo(heap[index]) > 0 ){
            biggest = leftChildIndex;
        }
        if (rightChildIndex <= tailIndex && heap[rightChildIndex].compareTo(heap[index]) > 0 ) {
            biggest = rightChildIndex;
        }

        // only need to swap if either child is bigger than the current 'node'
        if (biggest != index) {
            //swap
            Pair temp = heap[biggest];
            heap[biggest] = heap[index];
            heap[index] = temp;

            downHeap(biggest);
        }

    }

    // FOR TEST USE ONLY
    // a method to see what the queue looks like
    // similar to toString(), but it prints it out for me 
    public void see(){
        String s = "[";

        for(int i=0; i<=tailIndex; i++){
            s += heap[i] + ", ";
        }
        s.substring(0, s.length()-2);
        s += "]";
        System.out.println(s);
    }

}