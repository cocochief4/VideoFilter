package BallTracking;

import Interfaces.PixelFilter;
import core.DImage;

import Filters.KMeansFilter;

public class CenterDrawer implements PixelFilter {
     
    @Override
    public DImage processImage(DImage img) {
        short[][] bw = img.getBWPixelGrid();

        int r = 0;
        int rCount = 0;
        int c = 0;
        int cCount = 0;

        for (int i = 0; i < bw.length; i++) {
            for (int j = 0; j < bw[i].length; j++) {
                if (bw[i][j] == 255) {
                    r += i;
                    rCount ++;
                }
            }
        }
    }
}
 