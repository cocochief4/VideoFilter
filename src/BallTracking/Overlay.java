package BallTracking;

import Interfaces.PixelFilter;
import core.DImage;

public class Overlay implements PixelFilter {
    DImage orig;

    public Overlay(DImage orig){
        this.orig=orig;
    }
    @Override
    public DImage processImage(DImage img) {
        short[][] orig_red = orig.getRedChannel();
        short[][] orig_green = orig.getGreenChannel();
        short[][] orig_blue = orig.getBlueChannel();

        short[][] img_red = img.getRedChannel();

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
        img.setColorChannels(new_red,orig_green,orig_blue);
        return img;
    }
}