package Filters;

import Interfaces.PixelFilter;
import core.DImage;

public class BWPolychromeFilter implements PixelFilter {
    
    @Override
    public DImage processImage(DImage img) {

        int intervals = Integer.parseInt(javax.swing.JOptionPane.showInputDialog("How many intervals do you want?"));

        short[][] returnArr = img.getBWPixelGrid();

        for (int i = 0; i < returnArr.length; i++) {
            for (int j = 0; j < returnArr[i].length; j++) {
                int val = returnArr[i][j];

                for (int k = 0; k < intervals; k++) {
                    if (val >= k * (255.0/intervals) && val <= (k + 1) * (255.0/intervals)) {
                        val = 255/(2*intervals) * (2*k + 1);
                    }
                }
                returnArr[i][j] = (short) val;
            }
        }

        img.setPixels(returnArr);
        
        return img;
    }
}