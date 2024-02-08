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
                if (bw[i][j] > 0) {
                    r += i;
                    rCount ++;
                    
                    c += j;
                    cCount ++;

                }
            }
        }
        
        if (rCount != 0 && cCount != 0) {
            r /= rCount;
            c /= cCount;
        }

        for (int i = 0; i < bw.length; i++) {
            for (int j = 0; j < bw[i].length; j++) {
                if (Math.abs(i-r) < 10 && Math.abs(j-c) < 10 && bw[i][j] > 0) {
                    bw[i][j] = 0;
                } 
            }
        }
        
        img.setPixels(bw);
        return img;
    }
}
 