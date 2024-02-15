package BallTracking;

import Interfaces.PixelFilter;
import KMeansClustering.Point;
import core.DImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import Filters.KMeansFilter;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class CenterDrawerFloodFill implements PixelFilter {

    boolean vis[][];

    public void floodFill(int r, int c, ArrayList<Pixel> curVisited, short[][] grid){
        vis[r][c]=true;
        curVisited.add(new Pixel(r,c));
        if((r+1)<grid.length && grid[r+1][c]>0 && !vis[r+1][c]){
            vis[r+1][c]=true;
            floodFill(r+1,c,curVisited,grid);
        }

        if((r-1)>0 && grid[r-1][c]>0 && !vis[r-1][c]){
            vis[r-1][c]=true;
            floodFill(r-1,c,curVisited,grid);
        }

        if((c+1)<grid.length && grid[r][c+1]>0 && !vis[r][c+1]){
            vis[r][c+1]=true;
            floodFill(r,c+1,curVisited,grid);
        }

        if((c-1)>0 && grid[r][c-1]>0 && !vis[r][c-1]){
            vis[r][c-1]=true;
            floodFill(r,c-1,curVisited,grid);
        }
    }

    public static void logCenter(Pixel center){
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

        String message=timeStamp+','+center.r+','+center.c+'\n';

        try {
            writeDataToFile("CentersLog.txt",message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public DImage processImage(DImage img){
        short[][] grid = img.getBWPixelGrid();
        vis = new boolean[grid.length][grid[0].length];

        for(int r=0;r<grid.length;r++){
            for(int c=0;c<grid[0].length;c++){
                vis[r][c]=false;
            }
        }

        short[][] newGrid = img.getBWPixelGrid();

        for(int r=0;r<grid.length;r++){
            for(int c=0;c<grid[0].length;c++){
                if(grid[r][c]>0 && !vis[r][c]){
                    // System.out.println(r+" "+c);
                    ArrayList<Pixel> curVis = new ArrayList<>();
                    floodFill(r,c,curVis,grid);
                    if(curVis.size()>=50) {
                        Pixel center = Pixel.getCenter(curVis);
                        logCenter(center);

                        // System.out.println(center.r + " " + center.c + " " + curVis.size());

                        for (int i = 0; i < grid.length; i++) {
                            for (int j = 0; j < grid[i].length; j++) {
                                if (Math.abs(i-center.r) < 2 && Math.abs(j-center.c) < 2) {
                                    newGrid[i][j] = 0;
                                } else if(newGrid[i][j]!=254){
                                    // newGrid[i][j] = 0;
                                }
                            }
                        }

                    }
                }
            }
        }
        img.setPixels(newGrid);
        return img;
    }


    public static String readFile(String filePath) {
        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {

            String line = br.readLine();
            while ( line != null) {
                sb.append(line).append(System.getProperty("line.separator"));
                line = br.readLine();
            }

        } catch (Exception errorObj) {
            System.err.println("Couldn't read file: " + filePath);
            errorObj.printStackTrace();
        }

        return sb.toString();
    }

    public static void writeDataToFile(String filePath, String data) throws IOException {
        try (FileWriter f = new FileWriter(filePath);
             BufferedWriter b = new BufferedWriter(f);
             PrintWriter writer = new PrintWriter(b);) {

            System.out.println(data);
            writer.println(data);
            
            writer.close();


        } catch (IOException error) {
            System.err.println("There was a problem writing to the file: " + filePath);
            error.printStackTrace();
        }
    }


}
