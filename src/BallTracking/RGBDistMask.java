package BallTracking;

import java.util.Arrays;

import Interfaces.Interactive;
import Interfaces.PixelFilter;
import core.DImage;

public class RGBDistMask implements PixelFilter, Interactive {
    
    private int[] tgtrgb = {0, 0, 0};
    private double threshold;
    
    public RGBDistMask() {
        threshold = 5;
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, DImage img) {
        tgtrgb[0] = img.getRedChannel()[mouseY][mouseX];
        tgtrgb[1] = img.getGreenChannel()[mouseY][mouseX];
        tgtrgb[2] = img.getBlueChannel()[mouseY][mouseX];
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
        
        int[][] rArr = new int[img.getHeight()][img.getWidth()];
        
        for (int i = 0; i < img.getHeight(); i++) {
            for (int j = 0; j < img.getWidth(); j++) {
                int dR = img.getRedChannel()[i][j]-tgtrgb[0];
                int dG = img.getGreenChannel()[i][j]-tgtrgb[1];
                int dB = img.getBlueChannel()[i][j]-tgtrgb[2];
                
                double dist = Math.sqrt(dR*dR + dG*dG + dB*dB);
                
                if (dist < threshold) {
                    rArr[i][j] = 255;
                }
                else {
                    rArr[i][j] = 0;
                }
            }
        }
        
        img.setPixels(rArr);

        return img;
    }

}
