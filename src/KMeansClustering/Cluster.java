package KMeansClustering;

import java.util.ArrayList;

public class Cluster {
    
    private ArrayList<Point> points;
    
    private Point center;
    
    public Cluster() {
       
        int r = (int) (Math.random() * 256);
        int g = (int) (Math.random() * 256);
        int b = (int) (Math.random() * 256);
        
        center = new Point(r, g, b);
        
        points = new ArrayList<Point>();
    }
    
    public void clearPoints() {
        points.clear();
    }
    
    public void addPoint(Point point) {
        points.add(point);
    }

    public void calculateCenter() {
        double r = 0;
        double g = 0;
        double b = 0;
        
        for (Point point : points) {
            r += point.getPos()[0];
            g += point.getPos()[1];
            b += point.getPos()[2];
        }
        
        r /= points.size();
        g /= points.size();
        b /= points.size();
        
        center.setPos((int) r, (int) g, (int) b);
        
    }

    public Point getPos() {
        return center;
    }
    
    public ArrayList<Point> getPoints() {
        return points;
    }
}
