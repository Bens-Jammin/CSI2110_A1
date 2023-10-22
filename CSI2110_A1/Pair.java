/*
 * Author: Ben Miller
 * Student ID: 300297574
 * 
 */

public class Pair implements Comparable<Pair>{
    
    private double distance;
    private LabelledPoint point;

    public Pair(double d, LabelledPoint p){
        distance = d;
        point = p;
    }

    public double getDistance(){ return distance; }
    public LabelledPoint getPoint(){ return point; }


    // Pairs are compared using their distances to the target vector
    @Override
    public int compareTo(Pair o) {
        return Double.compare(this.distance, o.distance);
    }

}
