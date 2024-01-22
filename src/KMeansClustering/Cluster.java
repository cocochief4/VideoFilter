package KMeansClustering;

import java.util.ArrayList;

public class Cluster extends Point {
    
    private ArrayList<Point> points;
    
    public Cluster() {
       
        int r = (int) (Math.random() * 256);
        int g = (int) (Math.random() * 256);
        int b = (int) (Math.random() * 256);
        
        pos = new int[3];
        pos[0] = r;
        pos[1] = g;
        pos[2] = b;
        
        points = new ArrayList<Point>();
    }

    public Cluster (int r, int g, int b) {
        super(r, g, b);

        points = new ArrayList<Point>();
    }
    
    public void clearPoints() {
        points.clear();
    }
    
    public void addPoint(Point point) {
        points.add(point);
    }

    public void calculateCenter() {

    }
}
