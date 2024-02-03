package Filters;

import java.nio.file.DirectoryStream.Filter;
import java.util.Arrays;

import Interfaces.PixelFilter;
import core.DImage;

public class BWConvolutionNxN implements PixelFilter {

    @Override
    public DImage processImage(DImage img) {
        
        short[][] gray = img.getBWPixelGrid();
        short[][] returnArr = img.getBWPixelGrid();

        int n = Integer.parseInt(javax.swing.JOptionPane.showInputDialog("what is size of kernel? odd number greater than 1 please!"));
         
        int[][] boxBlurKernel = new int[n][n];  
        setBoxBlur(boxBlurKernel, n);

        int[][] sobelGx = new int[n][n];  
        setSobelGx(sobelGx, n);
        // System.out.println(Arrays.deepToString(sobelGx));

        int[][] sobelGy = new int[n][n];   
        setSobelGy(sobelGy, n);
        // System.out.println(Arrays.deepToString(sobelGy));
        
        short[][] gX = doConvolution(sobelGx, gray);
        short[][] gY = doConvolution(sobelGy, gray);

        returnArr = combineSobel(gX, gY);
        
        img.setPixels(returnArr);
        
        return img;
    }
    
    private short[][] combineSobel(short[][] gX, short[][] gY) {
        short[][] returnArr = gX;

        for (int i = 0; i < gY.length; i++) {
            for (int j = 0; j < gY[i].length; j++) {
                short val = (short) Math.sqrt(gX[i][j]*gX[i][j] + gY[i][j]*gY[i][j]);
                if (val < 127) val = 0;
                else val = 255;
                returnArr[i][j] = val;
            }
        }
        return returnArr;
    }

    private void setSobelGx(int[][] sobelGx, int n) {
        for (int c = 0; c < sobelGx[0].length; c++) {
            for (int r = sobelGx.length-1; r > -1; r--) {
                sobelGx[r][c] = -1*r + (sobelGx.length-1)/2;
            }
        }
        
        for (int r = 0; r < sobelGx[0].length; r++) {
            sobelGx[r][(sobelGx.length-1)/2] = sobelGx[r][(sobelGx.length-1)/2] * 2;
        }
    }
    
    private void setSobelGy(int[][] sobelGy, int n) {
        for (int r = 0; r < sobelGy[0].length; r++) {
            for (int c = sobelGy.length-1; c > -1; c--) {
                sobelGy[r][c] = -1*c + (sobelGy.length-1)/2;
            }
        }
        
        for (int c = 0; c < sobelGy[0].length; c++) {
            sobelGy[(sobelGy.length-1)/2][c] = sobelGy[(sobelGy.length-1)/2][c] * 2;
        }
    }

    private void setBoxBlur(int[][] boxBlurKernel, int n) {
        for (int i = 0; i < boxBlurKernel.length; i++) {
            for (int j = 0; j < boxBlurKernel[i].length; j++) {
                boxBlurKernel[i][j] = 1;
            }
        }
    }

    private static short[][] doConvolution(int[][] kernel, short[][] gray) {
        int kernelWeight = 0;
        short[][] returnArr = new short[gray.length][gray[0].length];
    
        for (int i = 0; i < kernel.length; i++) {
            for (int j = 0; j < kernel[i].length; j++) {
                kernelWeight += kernel[i][j];
            }
        }
        
        
        for (int r = 0; r < gray.length - kernel.length-1; r++) {
            for (int c = 0; c < gray[r].length - kernel.length-1; c++) {

                int output = 0;

                for (int i = 0; i < kernel.length; i++) {
                    for (int j = 0; j < kernel[i].length; j++) {
                        int kVal = kernel[i][j];
                        int pVal = gray[r + i][c + j];

                        output = output + kVal * pVal;
                    }
                }

                if (kernelWeight != 0) {
                    output = output/kernelWeight;
                }

                if (output < 0) output = 0;
                if (output > 255) output = 255;
                returnArr[r + 1][c + 1] = (short) output;
            }
        }
        
        return returnArr;
    }
 
}
