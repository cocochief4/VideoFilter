package BallTracking;

import Interfaces.PixelFilter;
import KMeansClustering.Point;
import core.DImage;

import Filters.KMeansFilter;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class CenterDrawerFloodFill implements PixelFilter {

    boolean vis[][];
    public void floodFill(int r, int c, ArrayList<Pixel> curVisited, short[][] grid){
        vis[r][c]=true;

        if((r+1)<grid.length && grid[r+1][c]==255 && !vis[r+1][c]){
            vis[r+1][c]=true;
            curVisited.add(new Pixel(r+1,c));
            floodFill(r+1,c,curVisited,grid);
        }

        if((r-1)>0 && grid[r-1][c]==255 && !vis[r-1][c]){
            vis[r-1][c]=true;
            curVisited.add(new Pixel(r-1,c));
            floodFill(r-1,c,curVisited,grid);
        }

        if((c+1)<grid.length && grid[r][c+1]==255 && !vis[r][c+1]){
            vis[r][c+1]=true;
            curVisited.add(new Pixel(r,c+1));
            floodFill(r,c+1,curVisited,grid);
        }

        if((c-1)<grid.length && grid[r][c-1]==255 && !vis[r][c-1]){
            vis[r][c-1]=true;
            curVisited.add(new Pixel(r,c-1));
            floodFill(r,c-1,curVisited,grid);
        }
    }


    @Override
    public DImage processImage(DImage img) {
        short[][] grid = img.getBWPixelGrid();
        vis = new boolean[grid.length][grid[0].length];

        for(int r=0;r<grid.length;r++){
            for(int c=0;c<grid.length;c++){
                if(grid[r][c]==255){
                    ArrayList<Pixel> curVis = new ArrayList<>();
                    floodFill(r,c,curVis,grid);
                    Pixel center = Pixel.getCenter(curVis);
                    System.out.println(center.r + center.c);
                }
            }
        }

        return img;


    }


}
