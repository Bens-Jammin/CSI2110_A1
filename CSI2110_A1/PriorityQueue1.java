/*
 * Author: Ben Miller
 * Student ID: 300297574
 * 
 */


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class PriorityQueue1 implements PriorityQueueIF<Pair>{

    private Pair[] queue;
    private int tailIndex; // index of the last element

    public PriorityQueue1(int capactity){
        queue = new Pair[capactity];
        tailIndex = -1;
    }


    public PriorityQueue1(ArrayList<Pair> list, int capactity){
        queue = new Pair[capactity];
        tailIndex = -1;
        for(Pair p : list){
            offer(p);
        }
    }



    // Inserts the specified element into this queue if it is possible to do so immediately 
	// without violating capacity restrictions.
    public boolean offer(Pair e){

        if( size()+1 > queue.length) return false;  

        tailIndex++;
        queue[tailIndex] = e;

        if ( size() < 2 ) return true;  // no need to sort if theres 1 elem

        sortByDistanceToTarget();

        return true;
    }


    // Retrieves and removes the head of this queue, or returns null if this queue is empty.
    public Pair poll(){
        Pair data = queue[0];

        // moves everything forward 1 spot
        for(int i=0; i<tailIndex; i++){
            queue[i] = queue[i+1];
        }

        tailIndex--;
        
        return data;
    }


    // Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty.
    public Pair peek(){
        return queue[0];
    }


    // Returns the number of elements in this queue.
    public int size(){
        return tailIndex+1;
    }


    // Returns true if this queue contains no elements.
    public boolean isEmpty(){
        return tailIndex == -1;
    }


    // For test use only
    // simply puts the queue into string format
    // and prints it
    public void see(){
        String s = "";

        for(int i=0; i<=tailIndex; i++){
            s += queue[i].getDistance() + " ("+queue[i].getPoint().getLabel() + ") ";
        }

        System.out.println(s);
    }

    // sorts the queue based on thier distances to the target point
    // this sorting algorithm uses bubble sort, and was inspired by 
    // the wikipedia article for bubble sort: https://en.wikipedia.org/wiki/Bubble_sort
    private void sortByDistanceToTarget(){

        for(int i=0; i< tailIndex; i++){

            for(int j=0; j<= tailIndex-1; j++){

                // swap only if the current is smaller than the next point
                if( queue[j].compareTo(queue[j+1]) < 0){
                    Pair temp = queue[j];
                    queue[j] = queue[j+1];
                    queue[j+1] = temp;
                }
            }
        }
    }

}