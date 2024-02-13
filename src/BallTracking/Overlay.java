package BallTracking;

import Interfaces.PixelFilter;
import core.DImage;

public class Overlay {
    DImage orig;

    public Overlay(){
    }
    public void setOrig(DImage orig) {
        this.orig = orig;
    }
    
    public DImage overlay (DImage img, DImage processed) {
        short[][] orig_red = img.getRedChannel();

        short[][] img_red = processed.getBWPixelGrid();

        short[][] new_red = img.getRedChannel();

        for(int r=0;r<img_red.length;r++){
            for(int c=0;c<img_red[0].length;c++){
                if(img_red[r][c]>0){
                    new_red[r][c]=img_red[r][c];
                }else{
                    new_red[r][c]=orig_red[r][c];
                }
            }
        }
        img.setColorChannels(new_red,img.getGreenChannel(),img.getBlueChannel());
        return img;
    }
}