package KMeansClustering;

public class Point {
    /** r, g, b position of point */
    protected int[] pos;
    
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
    
    public void assignPoint(Cluster[] clusters) {

    }
}