package Filters;

import Interfaces.PixelFilter;
import core.DImage;

public class BWEvenBetterDownsamplingFilter implements PixelFilter {
    
    @Override
    public DImage processImage(DImage img) {
        
        double scale = Double.parseDouble(javax.swing.JOptionPane.showInputDialog("How much do you want to zoom?"));

        short[][] gray = img.getBWPixelGrid(); 
        short[][] returnArr = new short[(int) (gray.length*scale)][(int) (gray.length*scale)];
        
        for (int i = 0; i < returnArr.length; i++) {
            for (int j = 0; j < returnArr[i].length; j++) {
                returnArr[i][j] = sample(i, j, returnArr, scale);
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

    private short sample (int r, int c, short[][] arr, double scale) {
        double scaleDiff = scale - (int) (scale);
        int intScale = (int) scale;
        double recipScale = 1/scale;
        int avg = 0;

        for (int i = r; i < r + intScale; i++) {
            for (int j = c; j < c + intScale; j++) {
                avg += arr[i][j];
            }
        }

        return (short) avg;
    }

}