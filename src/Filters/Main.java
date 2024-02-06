package Filters;

import Interfaces.Interactive;
import Interfaces.PixelFilter;
import core.DImage;

import java.util.ArrayList;

import BallTracking.*;
import Filters.*;

public class Main implements PixelFilter, Interactive {

    @Override
    public DImage processImage(DImage img) {

        DImage original = new DImage(img);
        original.setColorChannels(img.getRedChannel().clone(), img.getGreenChannel().clone(), img.getBlueChannel().clone());

        ArrayList<PixelFilter> pipeline = new ArrayList<PixelFilter>();
        pipeline.add(new Blur());
        pipeline.add(new HSLColorMaskFilter());
        // pipeline.add (new CenterDrawer());

        for (int i = 0; i < pipeline.size(); i++) {
            pipeline.get(i).processImage(img);
        }
        
        /* PixelFilter overlay = new Overlay();
        
        img = overlay.overlay(original, img) */;

        return img;
    }
    
    @Override
    public void mouseClicked(int mouseX, int mouseY, DImage img) {
        
        tgtHue = RGBtoH(img.getRedChannel()[mouseY][mouseX], img.getGreenChannel()[mouseY][mouseX], img.getBlueChannel()[mouseY][mouseX]);

    }

    @Override
    public void keyPressed(char key) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'keyPressed'");
    }
}
