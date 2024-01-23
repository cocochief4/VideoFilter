package KMeansClustering;

import java.util.ArrayList;

public class Point {
    /** r, g, b position of point */
    private int[] pos;
    
    public Point (int[] position) {
        pos = position;
    }
    
    public Point (int r, int g, int b) {
        pos = new int[3];
        pos[0] = r;
        pos[1] = g;
        pos[2] = b;
    }

    public int[] getPos() {
        return pos;
    }
    
    /**
     * assign the point to a cluster
     * @param clusters
     */
    public void assignPoint(ArrayList<Cluster> clusters) {
        
        double closestDist = Double.MAX_VALUE;
        Cluster closestCluster = clusters.get(0);

        for (Cluster cluster : clusters) {
            double dist = calcDist(cluster.getPos());
            if (dist < closestDist) {
                closestDist = dist;
                closestCluster = cluster;
            }
        }
        
        closestCluster.addPoint(this);

    }

    private double calcDist(Point pos2) {
        double deltaR = pos2.pos[0] - pos[0];
        double deltaG = pos2.pos[1] - pos[1];
        double deltaB = pos2.pos[2] - pos[2];
        
        double dist = Math.sqrt(deltaR*deltaR + deltaG*deltaG + deltaB*deltaB);

        return dist;

    }

    public void setPos(int r, int g, int b) {
        pos[0] = r;
        pos[1] = g;
        pos[2] = b;
    }

}