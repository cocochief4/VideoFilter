package BallTracking;

import Interfaces.PixelFilter;
import core.DImage;

public class Blur implements PixelFilter {

    public void applyKernel(short grid[][], double kernel[][]){
        short[][] newGrid = grid;

        double weightSum = 0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                weightSum+=kernel[i][j];
            }
        }

        for(int r = 1;r<(grid.length-1);r++){
            for(int c=1;c<(grid[0].length-1);c++){

                double outputVal = 0;

                for(int dr=-1;dr<=1;dr++){
                    for(int dc=-1;dc<=1;dc++){
                        double kernelVal = kernel[dr+1][dc+1];
                        double pixelVal = grid[r+dr][c+dc];
                        outputVal += (pixelVal*kernelVal);
                    }
                }

                newGrid[r][c]=(short) (outputVal/weightSum);

            }
        }

        grid = newGrid;
    }

    @Override
    public DImage processImage(DImage img) {
        short[][] grid = img.getBWPixelGrid();

        double kernel[][]={{1,1,1},{1,1,1},{1,1,1}};
        applyKernel(grid,kernel);

        img.setPixels(grid);
        return img;
    }
}