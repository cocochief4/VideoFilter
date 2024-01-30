package Filters;

import java.nio.file.DirectoryStream.Filter;

import Interfaces.PixelFilter;
import core.DImage;

public class BWConvolution3x3 implements PixelFilter {

    @Override
    public DImage processImage(DImage img) {
        
        short[][] gray = img.getBWPixelGrid();
        short[][] returnArr = img.getBWPixelGrid();

        // int k = Integer.parseInt(javax.swing.JOptionPane.showInputDialog("How many colors do you want?"));
         
        int[][] kernel = 
        {
        {-2, -1, 0},
        {-1, 0, 1},
        {0, 1, 2},
        };
        
        int kernelWeight = 0;

        for (int i = 0; i < kernel.length; i++) {
            for (int j = 0; j < kernel[i].length; j++) {
                kernelWeight += kernel[i][j];
            }
        }
        
        
        for (int r = 0; r < gray.length - 2; r++) {
            for (int c = 0; c < gray[r].length - 2; c++) {

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

        img.setPixels(returnArr);

        return img;
    }
 
}
