package Filters;

import Interfaces.PixelFilter;
import core.DImage;

public class BWLightenFilter implements PixelFilter {
    
    @Override
    public DImage processImage(DImage img) {

        int percentage = Integer.parseInt(javax.swing.JOptionPane.showInputDialog("What percent (0-100) do you want to lighten by?"));

        short[][] gray = img.getBWPixelGrid();
       
        for (int i = 0; i < gray.length; i++) {
            for (int j = 0; j < gray[i].length; j++) {
                gray[i][j] = (short) (gray[i][j] + (255 - gray[i][j])*(percentage/100.0)); 
            }
        }
        
        img.setPixels(gray);


        return img;
    }

    
}
