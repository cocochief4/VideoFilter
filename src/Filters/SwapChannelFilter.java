package Filters;

import Interfaces.PixelFilter;
import core.DImage;

import java.util.HashMap;

public class SwapChannelFilter implements PixelFilter{
    @Override
    public DImage processImage(DImage img) {

        String channel1 = javax.swing.JOptionPane.showInputDialog("First color channel to swap");
        String channel2 = javax.swing.JOptionPane.showInputDialog("Second color channel to swap");
        
        HashMap<String, short[][]> colorChannels = new HashMap<String, short[][]>();
        colorChannels.put("red", img.getRedChannel());
        colorChannels.put("blue", img.getBlueChannel());
        colorChannels.put("green", img.getGreenChannel());
        
        short[][] buf = colorChannels.get(channel1);

        colorChannels.put(channel1, colorChannels.get(channel2));
        colorChannels.put(channel2, buf);
       
        img.setColorChannels(colorChannels.get("red"), colorChannels.get("green"), colorChannels.get("blue"));

        return img;
    }
 
}
