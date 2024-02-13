package BallTracking;

import java.util.ArrayList;
import java.util.Arrays;

import Interfaces.Interactive;
import Interfaces.PixelFilter;
import core.DImage;

public class RGBDistMask implements PixelFilter, Interactive {
    
    private ArrayList<short[]> tgtrgb = new ArrayList<short[]>();
    private double threshold;
    
    public RGBDistMask() {
        threshold = 5;
        tgtrgb.add(new short[3]);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, DImage img) {
        short[] newtgt = new short[3];
        newtgt[0] = img.getRedChannel()[mouseY][mouseX];
        newtgt[1] = img.getGreenChannel()[mouseY][mouseX];
        newtgt[2] = img.getBlueChannel()[mouseY][mouseX];
        tgtrgb.add(newtgt);
    }

    @Override
    public void keyPressed(char key) {
        if (key == '=') {
            threshold = threshold + 2;
        }
        else if (key == '-') {
            threshold = threshold - 2;
        }
    }

    @Override
    public DImage processImage(DImage img) {
        
        short[][] rArr = new short[img.getHeight()][img.getWidth()];
        
        for (int i = 0; i < img.getHeight(); i++) {
            for (int j = 0; j < img.getWidth(); j++) {
                for (short[] s : tgtrgb) {
                    int dR = img.getRedChannel()[i][j]-s[0];
                    int dG = img.getGreenChannel()[i][j]-s[1];
                    int dB = img.getBlueChannel()[i][j]-s[2];
                
                    double dist = Math.sqrt(dR*dR + dG*dG + dB*dB);
                
                    if (dist < threshold) {
                        rArr[i][j] = 255;
                    }
                    else {
                    }
                }
                
            }
        }
        
        img.setPixels(rArr);

        return img;
    }

}
