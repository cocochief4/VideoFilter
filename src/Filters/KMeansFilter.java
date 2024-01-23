package Filters;

import java.util.ArrayList;

import KMeansClustering.*;

import Interfaces.PixelFilter;
import core.DImage;

public class KMeansFilter implements PixelFilter{
    @Override
    public DImage processImage(DImage img) {
        
        short[][] red = img.getRedChannel();
        short[][] green = img.getGreenChannel();
        short[][] blue = img.getBlueChannel();
        
        int k = Integer.parseInt(javax.swing.JOptionPane.showInputDialog("How many colors do you want?"));

        ArrayList<Cluster> clusters = initClusters(k);
        
        ArrayList<Point> points = new ArrayList<Point>();
        for (int i = 0; i < blue.length; i++) {
            for (int j = 0; j < blue[i].length; j++) {
                points.add(new Point(red[i][j], green[i][j], blue[i][j]));
            }
        }
        
        for (Point point : points) {
            point.assignPoint(clusters);
        }

        points = setNewColors(clusters, points);

        pointToImage(img, points, clusters);
        
        return img;
        
    }

    private void pointToImage(DImage img, ArrayList<Point> points, ArrayList<Cluster> clusters) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pointToImage'");
    }

    private ArrayList<Point> setNewColors(ArrayList<Cluster> clusters, ArrayList<Point> pts) {
        
        
        
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