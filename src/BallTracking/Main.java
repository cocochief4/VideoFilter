package BallTracking;

import Interfaces.PixelFilter;
import core.DImage;

import java.util.ArrayList;

import BallTracking.Blur.*;
import BallTracking.CenterDrawer.*;
import BallTracking.Threshold.*;

public class Main implements PixelFilter {
     
    @Override
    public DImage processImage(DImage img) {

        DImage original = new DImage(img);
        original.setColorChannels(img.getRedChannel(), img.getGreenChannel(), img.getBlueChannel());

        ArrayList<PixelFilter> pipeline = new ArrayList<PixelFilter>();
        pipeline.add(new Blur());
        pipeline.add(new Threshold());
        pipeline.add (new CenterDrawer());

        for (int i = 0; i < pipeline.size(); i++) {
            pipeline.get(i).processImage(img);
        }

        return img;
    }
}
 
