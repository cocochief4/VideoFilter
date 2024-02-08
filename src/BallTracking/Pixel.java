package BallTracking;

import java.util.ArrayList;

public class Pixel {
    int r, c;

    public Pixel(int r, int c) {
        this.r = r;
        this.c = c;
    }

    public static Pixel getCenter(ArrayList<Pixel> pixels){
        int rSum=0;
        int cSum=0;

        for(Pixel i : pixels){
            rSum+=i.r;
            cSum+=i.c;
        }

        return new Pixel(rSum/pixels.size(),cSum/pixels.size());
    }

}
