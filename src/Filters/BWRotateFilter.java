package Filters;

import Interfaces.PixelFilter;
import core.DImage;

public class BWRotateFilter implements PixelFilter {
    
    @Override
    public DImage processImage(DImage img) {

        double radians = Integer.parseInt(javax.swing.JOptionPane.showInputDialog("How many degrees to rotate?"));
        radians = radians * Math.PI/180;

        short[][] gray = img.getBWPixelGrid();

        int centerR = gray.length/2;
        int centerC = gray[0].length/2;
        
        double diagonalDistance = Math.sqrt(centerR*centerR + centerC*centerC);
        double diagonalAngle = Math.atan2(centerR, centerC);
        diagonalAngle += radians;
        double diagonalAngle2 = Math.atan2(-centerR, centerC);
        diagonalAngle2 += radians;

        int newR = Math.cos(diagonalAngle) > Math.cos(diagonalAngle2) ? (int) (2*(diagonalDistance * Math.cos(diagonalAngle))) : (int) (2*(diagonalDistance * Math.cos(diagonalAngle2)));
        int newC = Math.sin(diagonalAngle) > Math.sin(diagonalAngle2) ? (int) (2*(diagonalDistance * Math.sin(diagonalAngle))) : (int) (2*(diagonalDistance * Math.sin(diagonalAngle2)));

        short[][] returnArr = new short[2*gray.length][2*gray[0].length];
        
        double newCenterX = returnArr[0].length/2;
        double newCenterY = returnArr.length/2;

        for (int i = 0; i < gray.length; i++) {
            for (int j = 0; j < gray[i].length; j++) {
                double d = Math.sqrt((centerR-i)*(centerR-i) + (j-centerC)*(j-centerC));
                double theta = -1 * Math.atan2((centerR-i),(j-centerC));

                theta += radians;

                int newX = (int) (d * Math.cos(theta) + newCenterX);
                int newY = (int) (d * Math.sin(theta) + newCenterY);

                returnArr[newY][newX] = gray[i][j];
            }
        }
        
        img.setPixels(returnArr);
        return img;
    }

}