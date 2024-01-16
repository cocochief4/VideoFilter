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
                
                val = val/(255/intervals);
                val *= (255/intervals);
                if (val == 255) {
                    val = 255-(255/intervals);
                }

                val += (255/intervals/2);

                returnArr[i][j] = (short) val;
            }
        }

        img.setPixels(returnArr);
        
        return img;
    }
}