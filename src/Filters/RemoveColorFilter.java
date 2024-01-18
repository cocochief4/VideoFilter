package Filters;

import Interfaces.PixelFilter;
import core.DImage;

public class RemoveColorFilter implements PixelFilter{
    @Override
    public DImage processImage(DImage img) {

        String channel = javax.swing.JOptionPane.showInputDialog("Which color filter to remove?");
        
        short[][] red = img.getRedChannel();
        short[][] blue = img.getBlueChannel();
        short[][] green = img.getGreenChannel();
        
        for (int y = 0; y < red.length; y++) {
            for (int x = 0; x < red[y].length; x++) {
                switch (channel) {
                    case "red":
                        red[y][x] = 0;
                        break;
                    case "blue":
                        blue[y][x] = 0;
                        break;
                    case "green":
                        green[y][x] = 0;
                        break;
                }
            }
        }
        
        img.setColorChannels(red, green, blue);

        return img;
    }
 
}
