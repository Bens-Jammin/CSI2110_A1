/*
 * Author: Ben Miller
 * Student ID: 300297574
 * 
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class PriorityQueue3 implements PriorityQueueIF<Pair>{
    
    private PriorityQueue<Pair> q;

    public PriorityQueue3(int capactity){
        // Collections.reverseOrder() makes the pQueue into a max pQueue (min by default :(  )
        // https://www.geeksforgeeks.org/priority-queue-in-reverse-order-in-java/
        q = new PriorityQueue<Pair>(capactity, Collections.reverseOrder());
    }


    public PriorityQueue3(ArrayList<Pair> list, int capacity){
        q = new PriorityQueue<Pair>(Collections.reverseOrder());

        for(Pair p : list){
            q.add(p);
        }
    }
    

    // Inserts the specified element into this queue if it is possible to do so immediately 
	// without violating capacity restrictions.
    public boolean offer(Pair e){
        try{
            return q.add(e);    // return whatever condition the premade PriorityQueue method returns
        }catch(Exception x){
            return false;
        }
    }


    // Retrieves and removes the head of this queue, or returns null if this queue is empty.
    public Pair poll(){
        return (Pair)q.poll();
    }


    // Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty.
    public Pair peek(){
        return (Pair)q.peek();
    }


    // Returns the number of elements in this queue.
    public int size(){
        return q.size();
    }


    // Returns true if this queue contains no elements.
    public boolean isEmpty(){
        return q.isEmpty();
    }
}