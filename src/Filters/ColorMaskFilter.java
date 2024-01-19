package Filters;

import Interfaces.PixelFilter;
import core.DImage;

import java.util.HashMap;

public class ColorMaskFilter implements PixelFilter{
    @Override
    public DImage processImage(DImage img) {

        int threshold = 20;

        int red = Integer.parseInt(javax.swing.JOptionPane.showInputDialog("What is red value?"));
        int green = Integer.parseInt(javax.swing.JOptionPane.showInputDialog("What is green value?"));
        int blue = Integer.parseInt(javax.swing.JOptionPane.showInputDialog("What is blue value?"));
        
        short[][] gray = img.getBWPixelGrid();
        
        HashMap<String, short[][]> channels = new HashMap<String, short[][]>();
        channels.put("red", img.getRedChannel());
        channels.put("blue", img.getBlueChannel());
        channels.put("green", img.getGreenChannel());
        
        for (int y = 0; y < channels.get("red").length; y++) {
            for (int x = 0; x < channels.get("red")[y].length; x++) {
                int dRed = channels.get("red")[y][x] - red;
                int dGreen = channels.get("green")[y][x] - green;
                int dBlue = channels.get("blue")[y][x] - blue;
                
                double distance = Math.sqrt(dRed*dRed + dGreen*dGreen + dBlue*dBlue);
                if (distance < threshold) {
                    gray [y][x] = 255;
                } else {
                    gray [y][x] = 0;
                }
            }
        }
        
        img.setPixels(gray);
        
        return img;
    }
 
}