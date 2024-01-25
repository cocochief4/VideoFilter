package KMeansClustering;

import java.util.ArrayList;

public class Cluster {
    
    private ArrayList<Point> points;
    
    private Point center;
    
    private Point prevCenter;
    
    public Cluster() {
       
        int r = (int) (Math.random() * 256);
        int g = (int) (Math.random() * 256);
        int b = (int) (Math.random() * 256);
        
        center = new Point(r, g, b);
        prevCenter = new Point (r+10, g, b);
        
        points = new ArrayList<Point>();
    }
    
    public void clearPoints() {
        points.clear();
    }
    
    public void addPoint(Point point) {
        points.add(point);
    }

    public void calculateCenter() {
        
        if (points.size() == 0) {
            int r = (int) (Math.random() * 256);
            int g = (int) (Math.random() * 256);
            int b = (int) (Math.random() * 256); 

            center.setPos(r, g, b);
            prevCenter.setPos(r+10, g, b);
        

            

            return;
        }
        
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
        
        prevCenter.setPos(center);
        center.setPos((int) r, (int) g, (int) b);
        
    }

    public Point getPos() {
        return center;
    }
    
    public ArrayList<Point> getPoints() {
        return points;
    }
    
    public boolean isStable() {
        
        if (center.getPos()[0] != prevCenter.getPos()[0]) {
            return false;
        }
        if (center.getPos()[1] != prevCenter.getPos()[1]) {
            return false;
        }
        if (center.getPos()[1] != prevCenter.getPos()[1]) {
            return false;
        }
        return true;

    }

}
