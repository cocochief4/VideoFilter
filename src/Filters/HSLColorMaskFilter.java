package Filters;

import Interfaces.Interactive;
import Interfaces.PixelFilter;
import core.DImage;

import java.util.HashMap;

public class HSLColorMaskFilter implements PixelFilter, Interactive {

    private static double[] tgtHsl = {0, 0, 0};

    public HSLColorMaskFilter(double[] hsl) {
        tgtHsl = hsl;
    }

    public HSLColorMaskFilter() {

    }

    @Override
    public DImage processImage(DImage img) {

        // double tgtHue = Double.parseDouble(javax.swing.JOptionPane.showInputDialog("What is the target hue?"));
        
        short[][] gray = img.getBWPixelGrid();
        
        double threshold = 50;
        
        HashMap<String, short[][]> colorChannels = new HashMap<String, short[][]>();
        colorChannels.put("red", img.getRedChannel());
        colorChannels.put("blue", img.getBlueChannel());
        colorChannels.put("green", img.getGreenChannel());
        
        for (int y = 0; y < gray.length; y++) {
            for (int x = 0; x < gray[y].length; x++) {
                double[] hsl = RGBtoHSL(colorChannels.get("red")[y][x], colorChannels.get("green")[y][x], colorChannels.get("blue")[y][x]);
                if (Math.sqrt(Math.abs(hsl[0] - tgtHsl[0])*Math.abs(hsl[0] - tgtHsl[0]) + Math.abs(hsl[1] - tgtHsl[1])*Math.abs(hsl[1] - tgtHsl[1]) + Math.abs(hsl[2] - tgtHsl[2])*Math.abs(hsl[2] - tgtHsl[2])) <= threshold) {
                    gray[y][x] = 255;
                } else {
                    gray[y][x] = 0;
                }
            }
        }

        img.setPixels(gray);

        return img;
    }
    
    public double[] RGBtoHSL (int r, int g, int b) {
        double sR = r/255;
        double sG = g/255;
        double sB = b/255;
        
        int cMax = Math.max(r, Math.max(g, b));
        int cMin = Math.min(r, Math.min(g, b));
        double delta = (double) cMax / 255 - (double) cMin / 255;

        double h = 0;
        if (delta == 0) h = 0;
        else if (cMax == r) {
            h = (60 * (((sG-sB)/delta) % 6)) % 360;
        } else if (cMax == g) {
            h = (60 * (((sB-sR)/delta) + 2)) % 360;
        } else if (cMax == b) {
            h = (60 * (((sR-sG)/delta) + 4)) % 360;
        }

        double l = (cMax + cMin)/2;
        
        double s = 0;

        if (delta == 0) s = 0;
        else {
            s = delta/(1-Math.abs(2*l - 1));
        }
        
        double[] returnArr = {h, s, l};
        return returnArr;

    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, DImage img) {
        
        tgtHsl = RGBtoHSL(img.getRedChannel()[mouseY][mouseX], img.getGreenChannel()[mouseY][mouseX], img.getBlueChannel()[mouseY][mouseX]);

    }

    @Override
    public void keyPressed(char key) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'keyPressed'");
    }
 
}
