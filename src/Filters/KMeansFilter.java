package Filters;

import java.util.ArrayList;

import KMeansClustering.*;

import Interfaces.PixelFilter;
import core.DImage;

public class KMeansFilter implements PixelFilter{
     
    private static ArrayList<Cluster> clusters = null;
    
    @Override
    public DImage processImage(DImage img) {
        
        short[][] red = img.getRedChannel();
        short[][] green = img.getGreenChannel();
        short[][] blue = img.getBlueChannel();
        
        // int k = Integer.parseInt(javax.swing.JOptionPane.showInputDialog("How many colors do you want?"));
        int k = 2;

        if (clusters == null) {
            clusters = initClusters(k);
        }
    
        ArrayList<Point> points = new ArrayList<Point>();
        for (int i = 0; i < blue.length; i++) {
            for (int j = 0; j < blue[i].length; j++) {
                points.add(new Point(red[i][j], green[i][j], blue[i][j], i, j));
            }
        }
        
        while (!isStable(clusters)) {
            
            for (Cluster cluster : clusters) {
                cluster.clearPoints();
            }

            for (Point point : points) {
                point.assignPoint(clusters);
            }

            for (Cluster cluster : clusters) {
                cluster.calculateCenter();
            }
        }

        setNewColors(clusters, points);

        pointToImage(img, points, clusters);
         
        return img;
        
    }

    private boolean isStable(ArrayList<Cluster> clusters) {
        
        for (Cluster cluster : clusters) {
            if (!cluster.isStable()) {
                return false;
            }
        }

        return true;
    }

    private void pointToImage(DImage img, ArrayList<Point> points, ArrayList<Cluster> clusters) {
        
        short[][] red = img.getRedChannel();
        short[][] green = img.getGreenChannel();
        short[][] blue = img.getBlueChannel();
        
        for (Point point : points) {
            red[point.get2dPos()[0]][point.get2dPos()[1]] = (short) point.getPos()[0];
            green[point.get2dPos()[0]][point.get2dPos()[1]] = (short) point.getPos()[1];
            blue[point.get2dPos()[0]][point.get2dPos()[1]] = (short) point.getPos()[2];
        }
        
        img.setColorChannels(red, green, blue);
    }

    private void setNewColors(ArrayList<Cluster> clusters, ArrayList<Point> pts) {
        
        for (Cluster c : clusters) {
            ArrayList<Point> pArr = c.getPoints();
            for (Point point : pArr) {
                point.setPos(c.getPos());
            }
        }
        
    }

    /**
     * Initialize the clusters to random positions
     * @param k Number of clusters
     * @return ArrayList of the clusters at random locations
     */
    private ArrayList<Cluster> initClusters(int k) {
        ArrayList<Cluster> returnArr = new ArrayList<Cluster>();

        for (int i = 0; i < k; i++) {
            returnArr.add(new Cluster());
        }
        
        return returnArr;

    }
}