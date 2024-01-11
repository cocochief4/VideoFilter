package Filters;

import Interfaces.PixelFilter;
import core.DImage;

public class BWEvenBetterDownsamplingFilter implements PixelFilter {
    
    @Override
    public DImage processImage(DImage img) {
        
        double scale = Double.parseDouble(javax.swing.JOptionPane.showInputDialog("How much do you want to zoom?"));

        short[][] gray = img.getBWPixelGrid(); 
        short[][] returnArr = new short[(int) (gray.length*scale)][(int) (gray.length*scale)];
        
        for (int i = 0; i < returnArr.length; i = i + 2) {
            for (int j = 0; j < returnArr[i].length; j = j + 2) {
                
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


    }

}