/*
 * Author: Ben Miller
 * Student ID: 300297574
 * 
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class KNN {

    private int K;
    private ArrayList<LabelledPoint> pointList;

    public KNN(ArrayList<LabelledPoint> points) {
        this.pointList = points;
    }

    public void setK(int k) {
        K = k;
    }

    /*
     * ====== READ ME !!!!!! ( :3 ) ======
     *
     * The following functions 'findNN_(1-3)' were heavily inspired by the
     * KNearestNeighbors.java file
     */

    /**
     * finds the K nearest neighbours given a point.
     * Uses PriorityQueue1
     * 
     * @param targetPoint: Labelled point
     * @return k nearest LabelledPoints
     */
    public ArrayList<LabelledPoint> findNN_1(LabelledPoint targetPoint) {

        PriorityQueue1 queue = new PriorityQueue1(pointList.size());
        for (LabelledPoint p : pointList) {
            Pair pair = new Pair(p.distanceTo(targetPoint), p);
            queue.offer(pair);
            // take out the biggest only if there's no more space
            if (queue.size() > K) {
                queue.poll();
            }
        }

        ArrayList<LabelledPoint> neighbours = new ArrayList<LabelledPoint>();
        while (!queue.isEmpty()) {
            // adds the K nearest neighbours from largest --> smallest
            neighbours.add(queue.poll().getPoint());
        }
        return neighbours;
    }

    /**
     * finds the K nearest neighbours given a point.
     * Uses PriorityQueue2
     * 
     * @param targetPoint: Labelled point
     * @return k nearest LabelledPoints
     */
    public ArrayList<LabelledPoint> findNN_2(LabelledPoint targetPoint, ArrayList<LabelledPoint> pointList) {

        PriorityQueue2 heap = new PriorityQueue2(pointList.size());

        for (LabelledPoint p : pointList) {
            Pair pair = new Pair(p.distanceTo(targetPoint), p);
            heap.offer(pair);

            // take out the biggest only if there's no more space
            if (heap.size() > K) {
                heap.poll();
            }
        }

        ArrayList<LabelledPoint> neighbours = new ArrayList<LabelledPoint>();

        while (!heap.isEmpty()) {
            // adds the K nearest neighbours from largest --> smallest
            neighbours.add(heap.poll().getPoint());
        }
        return neighbours;
    }

    /**
     * finds the K nearest neighbours given a point.
     * Uses PriorityQueue3
     * 
     * @param targetPoint: Labelled point
     * @return k nearest LabelledPoints
     */
    public ArrayList<LabelledPoint> findNN_3(LabelledPoint targetPoint, ArrayList<LabelledPoint> pointList) {

        PriorityQueue3 standardQueue = new PriorityQueue3(pointList.size());

        for (LabelledPoint p : pointList) {
            Pair pair = new Pair(p.distanceTo(targetPoint), p);
            standardQueue.offer(pair);

            // take out the biggest only if there's no more space
            if (standardQueue.size() > K) {
                standardQueue.poll();
            }
        }

        ArrayList<LabelledPoint> neighbours = new ArrayList<LabelledPoint>();

        while (!standardQueue.isEmpty()) {
            // adds the K nearest neighbours from largest --> smallest
            neighbours.add(standardQueue.poll().getPoint());
        }
        return neighbours;
    }

    /**
     * returns a string of all the nearest neighbours labels
     * 
     * @param neighbours
     */
    public static String getNNLabels(ArrayList<LabelledPoint> neighbours) {
        StringBuffer s = new StringBuffer();

        for (LabelledPoint n : neighbours) {
            s.append(n.getLabel() + ", ");
        }

        s.setLength(s.length() - 2);

        return s.toString();
    }

    /*
     * NOTE TO SELF:
     * Query vectors are the target vectors
     * dataset vectors are the vectors searched through
     */
    public static void main(String[] args) throws IOException {

        args = new String[] { "3", "100", "CSI2110_A1\\sift_base.fvecs", "CSI2110_A1\\siftsmall_query.fvecs" };

        int versionNumber = Integer.valueOf(args[0]);
        int valueOfK = Integer.valueOf(args[1]);
        String dataSetFileName = args[2];
        String queryPointsFileName = args[3];

        PointSet targetVectors = new PointSet(PointSet.read_ANN_SIFT(queryPointsFileName));
        PointSet searchedVectors = new PointSet(PointSet.read_ANN_SIFT(dataSetFileName));

        KNN knn = new KNN(searchedVectors.getPointsList());
        knn.setK(valueOfK);


        switch (versionNumber) {
            case 1:
                ArrayList<LabelledPoint> targets = targetVectors.getPointsList();
                double start = System.currentTimeMillis();


                for (LabelledPoint target : targets) {
                    ArrayList<LabelledPoint> nearestNeighbours = knn.findNN_1(target);
                    String display = target.getLabel() + " : " + getNNLabels(nearestNeighbours);
                    System.out.println(display);
                }

                double end = System.currentTimeMillis();

                System.out.println("\n\nRuntime: " + (end - start) + " milliseconds");
                break;

            case 2:
                ArrayList<LabelledPoint> targets2 = targetVectors.getPointsList();
                double start2 = System.currentTimeMillis();

                for (LabelledPoint target : targets2) {

                    ArrayList<LabelledPoint> nearestNeighbours = knn.findNN_1(target);
                    String display = target.getLabel() + " : " + getNNLabels(nearestNeighbours);
                    System.out.println(display);
                }

                double end2 = System.currentTimeMillis();

                System.out.println("\n\nRuntime: " + (end2 - start2) + " milliseconds");
                break;

            case 3:
                ArrayList<LabelledPoint> targets3 = targetVectors.getPointsList();
                double start3 = System.currentTimeMillis();

                for (LabelledPoint target : targets3) {
                    ArrayList<LabelledPoint> nearestNeighbours = knn.findNN_1(target);
                    String display = target.getLabel() + " : " + getNNLabels(nearestNeighbours);
                    System.out.println(display);
                }

                double end3 = System.currentTimeMillis();

                System.out.println("\n\nRuntime: " + (end3 - start3) + " milliseconds");
                break;
        }

    }
}
