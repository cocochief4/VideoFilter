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
                returnArr[i][j] = sample((int) (i / scale), (int) (j / scale), gray, scale);
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

    private short sample (double r, double c, short[][] arr, double scale) {
        double avg = 0;

        double[][] weights = new double[(int) (1/scale + 1)][(int) (1/scale + 1)];

        for (int i = 0; i < weights.length; i++) {
            double xstartVal = r + i*scale/weights.length;
            int xpInt = (int) xstartVal;
            int xnInt = (int) (xstartVal + 1);
            double xWeight = xnInt - xstartVal;

            for (int j = 0; j < weights[i].length; j++) {
                double ystartVal = c + j*scale/weights[i].length;
                int ypInt = (int) ystartVal;
                int ynInt = (int) (ystartVal + 1);
                double yWeight = ynInt - ystartVal;

                double trueWeight = arr[xpInt][ypInt] * xWeight * yWeight;
                weights[i][j] = trueWeight;
    
            }
        }

        for (int i = 0; i < weights.length; i++) {
            for (int j = 0; j < weights[i].length; j++) {
                avg += weights[i][j];
            }
        }
        
        avg = avg / (weights.length * weights[0].length);

        return (short) avg;
    }

}