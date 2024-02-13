package BallTracking;

import java.util.ArrayList;
import java.util.Arrays;

import Interfaces.Interactive;
import Interfaces.PixelFilter;
import core.DImage;

public class RGBRatioMask implements PixelFilter, Interactive {
    
    private ArrayList<short[]> tgtrgb = new ArrayList<short[]>();
    private double threshold;
    
    public RGBRatioMask() {
        threshold = 0;
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
            threshold = threshold + 0.25;
        }
        else if (key == '-') {
            threshold = threshold - 0.25;
        }
    }

    @Override
    public DImage processImage(DImage img) {
        
        int[][] rArr = new int[img.getHeight()][img.getWidth()];
        
        for (int i = 0; i < img.getHeight(); i++) {
            for (int j = 0; j < img.getWidth(); j++) {
                for (short[] s : tgtrgb) {
                    double dRrG = Double.MAX_VALUE;
                    double dGrB = Double.MAX_VALUE;
                    if (s[1] != 0 && img.getGreenChannel()[i][j] != 0) {
                        dRrG = ((double) s[0]/s[1]) - ((double) img.getRedChannel()[i][j]/img.getGreenChannel()[i][j]);
                    }
                    if (s[2] != 0 && img.getBlueChannel()[i][j] != 0) {
                        dGrB = ((double) s[1]/s[2]) - ((double) img.getGreenChannel()[i][j]/img.getBlueChannel()[i][j]);
                    }
                    
                    double ratioDist = Math.sqrt(dRrG*dRrG + dGrB*dGrB);
                    
                    int dR = img.getRedChannel()[i][j]-s[0];
                    int dG = img.getGreenChannel()[i][j]-s[1];
                    int dB = img.getBlueChannel()[i][j]-s[2];
                    
                    double colorDist = Math.sqrt(dR*dR + dG*dG + dB*dB);

                    double dist = Math.sqrt(colorDist*colorDist + ratioDist*ratioDist);
                    
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
