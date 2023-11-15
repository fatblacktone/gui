/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.game.components;

import java.awt.Image;

/**
 *
 * @author Fatblack
 */
public class Layer {
    
    //The dimensions of the layer
    private int width,height;
    //This is the layers depth. 0 is the bottom layer.
    private int depth;
    //The speed of the background in the given axis
    private int xSpeed,ySpeed;
    //current position of layer
    private int xPosition,yPosition;
    
    private Image image;

    public Layer(Image image,int width, int height, int depth) {
        this.image = image;
        this.width = width;
        this.height = height;
        this.depth = depth;
    }
    
    public void update(){
        xPosition += xSpeed;
        yPosition += ySpeed;
        
        if(xPosition<(-width)){
            xPosition = xPosition * 2;
        }else if(xPosition>width){
            xPosition = 0;
        }
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
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
    
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(int xSpeed) {
        this.xSpeed = xSpeed*depth;
    }

    public int getySpeed() {
        return ySpeed;
    }

    public void setySpeed(int ySpeed) {
        this.ySpeed = ySpeed*depth;
    }
    
    
    
    
    
}
