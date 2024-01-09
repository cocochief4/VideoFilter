package Filters;

import Interfaces.PixelFilter;
import core.DImage;

public class BWHorizontalBlindsFilter implements PixelFilter {
    
    @Override
    public DImage processImage(DImage img) {

        int blindWidth = Integer.parseInt(javax.swing.JOptionPane.showInputDialog("What is the width of blinds?"));
        double blindWeight = Double.parseDouble(javax.swing.JOptionPane.showInputDialog("What is the weight of blinds?"));


        short[][] gray = img.getBWPixelGrid(); 

        for (int i = 0; i < gray.length; i++) {
            for (int j = 0; j < gray[i].length; j++) {
                if ((i % (2*blindWidth)) > blindWidth) {
                    gray[i][j] = (short) (gray[i][j] * blindWeight);
                }
            }
        }
        
        img.setPixels(gray);


        return img;
    }


} 