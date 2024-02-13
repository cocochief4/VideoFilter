package Filters;

import Interfaces.Interactive;
import Interfaces.PixelFilter;
import core.DImage;

import java.io.IOException;
import java.util.ArrayList;

import BallTracking.*;
import Filters.*;

public class Main implements PixelFilter, Interactive {

    private static ArrayList<PixelFilter> pipeline = new ArrayList<PixelFilter>();

    public Main() {
       pipeline.add(new Blur());
       pipeline.add(new RGBDistMask());
//        pipeline.add(new CenterDrawer());
        pipeline.add(new BWEvenBetterDownsamplingFilter(0.25));
        pipeline.add(new CenterDrawerFloodFill());
        pipeline.add(new BWEvenBetterDownsamplingFilter(4));
        // pipeline.add(new Blur());
        // pipeline.add(new RGBDistMask());
        // pipeline.add(new CenterDrawer());
        // pipeline.add(new BWEvenBetterDownsamplingFilter());
        // pipeline.add(new CenterDrawerFloodFill());
//        pipeline.add(new Blur());
//        pipeline.add(new RGBDistMask());
    }

    @Override
    public DImage processImage(DImage img) throws IOException {

        DImage original = new DImage(img);
        original.setColorChannels(img.getRedChannel().clone(), img.getGreenChannel().clone(), img.getBlueChannel().clone());


        for (int i = 0; i < pipeline.size(); i++) {
            img=pipeline.get(i).processImage(img);
        }
        
        /* PixelFilter overlay = new Overlay();
        
        img = overlay.overlay(original, img) */;

        return img;
    }
    
    @Override
    public void mouseClicked(int mouseX, int mouseY, DImage img) {
        
        for (PixelFilter filter : pipeline) {
            if (filter instanceof Interactive) {
                ((Interactive)filter).mouseClicked(mouseX, mouseY, img);
            }
        }

    }

    @Override
    public void keyPressed(char key) {
        for (PixelFilter filter : pipeline) {
            if (filter instanceof Interactive) {
                ((Interactive)filter).keyPressed(key);
            }
        }
    }
}
