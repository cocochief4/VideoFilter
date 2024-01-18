package Filters;

import Interfaces.PixelFilter;
import core.DImage;

import java.util.HashMap;

public class ColorNoiseFilter implements PixelFilter{
    @Override
    public DImage processImage(DImage img) {

        double chance = Double.parseDouble(javax.swing.JOptionPane.showInputDialog("Probability of noise (0-1)?"));
        
        HashMap<String, short[][]> channels = new HashMap<String, short[][]>();
        channels.put("red", img.getRedChannel());
        channels.put("blue", img.getBlueChannel());
        channels.put("green", img.getGreenChannel());
        
        for (int y = 0; y < channels.get("red").length; y++) {
            for (int x = 0; x < channels.get("red")[y].length; x++) {
                if (Math.random() < chance) {
                    channels.get("red")[y][x] = (short) (Math.random() * 256);
                    channels.get("blue")[y][x] = (short) (Math.random() * 256);
                    channels.get("green")[y][x] = (short) (Math.random() * 256);
                }
            }
        }
        
        img.setColorChannels(channels.get("red"), channels.get("green"), channels.get("blue"));

        return img;
    }
 
}