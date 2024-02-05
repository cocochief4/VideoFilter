package BallTracking;

import Interfaces.PixelFilter;
import core.DImage;

import Filters.KMeansFilter;

public class KMeans implements PixelFilter {
     
    @Override
    public DImage processImage(DImage img) {
        KMeansFilter kMeans = new KMeansFilter();
        kMeans.processImage(img);

        return img;
    }
}