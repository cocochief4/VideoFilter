package Filters;

import Interfaces.PixelFilter;
import core.DImage;

public class BWBetterDownsamplingFilter implements PixelFilter {
    
    @Override
    public DImage processImage(DImage img) {
        
        short[][] gray = img.getBWPixelGrid(); 
        short[][] returnArr = new short[gray.length/2][gray[0].length/2];
        
        for (int i = 0; i < gray.length-1; i = i + 2) {
            for (int j = 0; j < gray[i].length-1; j = j + 2) {
                returnArr[i/2][j/2] = dSample2x2(i, j, gray);
            }
        }

        img.setPixels(returnArr);
        
        return img;
    }

    private short dSample2x2(int i, int j, short[][] arr) {
       
        short avg = (short) (arr[i][j] + arr[i+1][j] + arr[i][j+1] + arr[i+1][j+1]);
        avg = (short) (avg/4);

        return avg;
    }

}
