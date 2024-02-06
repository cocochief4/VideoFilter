package BallTracking;

import Interfaces.PixelFilter;
import core.DImage;

import java.util.ArrayList;

import BallTracking.*;

public class Main implements PixelFilter {

    @Override
    public DImage processImage(DImage img) {

        DImage original = new DImage(img);
        original.setColorChannels(img.getRedChannel().clone(), img.getGreenChannel().clone(), img.getBlueChannel().clone());

        ArrayList<PixelFilter> pipeline = new ArrayList<PixelFilter>();
        pipeline.add(new Blur());
        pipeline.add(new Threshold());
        pipeline.add (new CenterDrawer());

        for (int i = 0; i < pipeline.size(); i++) {
            pipeline.get(i).processImage(img);
        }
        
        PixelFilter overlay = new Overlay();
        
        img = overlay.overlay(original, img);

        PixelFilter overlay = new Overlay(original);

        img = overlay.processImage(img);

        return img;
    }
}
