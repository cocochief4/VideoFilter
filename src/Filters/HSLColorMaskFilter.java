package Filters;

import Interfaces.PixelFilter;
import core.DImage;

import java.util.HashMap;

public class HSLColorMaskFilter implements PixelFilter{
    @Override
    public DImage processImage(DImage img) {

        // double tgtHue = Double.parseDouble(javax.swing.JOptionPane.showInputDialog("What is the target hue?"));
        double tgtHue = 20;
        
        short[][] gray = img.getBWPixelGrid();
        
        double threshold = 20;
        
        HashMap<String, short[][]> colorChannels = new HashMap<String, short[][]>();
        colorChannels.put("red", img.getRedChannel());
        colorChannels.put("blue", img.getBlueChannel());
        colorChannels.put("green", img.getGreenChannel());
        
        for (int y = 0; y < gray.length; y++) {
            for (int x = 0; x < gray[y].length; x++) {
                double h = RGBtoH(colorChannels.get("red")[y][x], colorChannels.get("green")[y][x], colorChannels.get("blue")[y][x]);
                if (Math.abs(h - tgtHue) < threshold) {
                    gray[y][x] = 255;
                } else {
                    gray[y][x] = 0;
                }
            }
        }

        img.setPixels(gray);

        return img;
    }
    
    private double RGBtoH (int r, int g, int b) {
        double sR = r/255;
        double sG = g/255;
        double sB = b/255;
        
        int cMax = Math.max(r, Math.max(g, b));
        int cMin = Math.min(r, Math.min(g, b));
        double delta = (double) cMax / 255 - (double) cMin / 255;

        double h = 0;
        
        if (cMax == r) {
            h = (60 * (((sG-sB)/delta) % 6)) % 360;
        } else if (cMax == g) {
            h = (60 * (((sB-sR)/delta) + 2)) % 360;
        } else if (cMax == b) {
            h = (60 * (((sR-sG)/delta) + 4)) % 360;
        }
        
        return h;

    }
 
}
