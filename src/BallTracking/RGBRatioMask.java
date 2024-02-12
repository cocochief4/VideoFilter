package BallTracking;

import java.util.Arrays;

import Interfaces.Interactive;
import Interfaces.PixelFilter;
import core.DImage;

public class RGBRatioMask implements PixelFilter, Interactive {
    
    private short[] tgtrgb = {0, 0, 0};
    private double threshold;
    
    public RGBRatioMask() {
        threshold = 0;
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
                double dRrG = Double.MAX_VALUE;
                double dGrB = Double.MAX_VALUE;
                if (tgtrgb[1] != 0 && img.getGreenChannel()[i][j] != 0) {
                    dRrG = ((double) tgtrgb[0]/tgtrgb[1]) - ((double) img.getRedChannel()[i][j]/img.getGreenChannel()[i][j]);
                }
                if (tgtrgb[2] != 0 && img.getBlueChannel()[i][j] != 0) {
                    dGrB = ((double) tgtrgb[1]/tgtrgb[2]) - ((double) img.getGreenChannel()[i][j]/img.getBlueChannel()[i][j]);
                }
                
                double ratioDist = Math.sqrt(dRrG*dRrG + dGrB*dGrB);
                
                int dR = img.getRedChannel()[i][j]-tgtrgb[0];
                int dG = img.getGreenChannel()[i][j]-tgtrgb[1];
                int dB = img.getBlueChannel()[i][j]-tgtrgb[2];
                
                double colorDist = Math.sqrt(dR*dR + dG*dG + dB*dB);

                double dist = Math.sqrt(colorDist*colorDist + ratioDist*ratioDist);
                

                
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
