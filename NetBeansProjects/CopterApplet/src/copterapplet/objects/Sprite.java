/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package copterapplet.objects;

import java.awt.Image;
import java.awt.Polygon;

/**
 *
 * @author Fatblack
 */
public class Sprite {
    
    private int xPosition,
                yPosition;
    
    private Image image;
    
    private int depth;

    public Sprite(int xPosition, int yPosition,int depth, Image image) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.depth = depth;
        this.image = image;
    }

    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
    
    
    
    
    
}
