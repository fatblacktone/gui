/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.angryandroid.framework;

import java.awt.Image;
import java.awt.Polygon;

/**
 *
 * @author Fatblack
 */
public interface Interactable {
    
    public int getCenterX();
    
    public int getCenterY();
    
    public Polygon getBoundingPolygon();  
    
    public void setBoundingPolygon(Polygon boundingPolygon);
    
    public void setCenterX(int centerX);  
    

    public void setCenterY(int centerY);

    public Image getImage();

    public void setImage(Image image);
    
    public int getWidth();
    
    public int getHeight();
    
    public void update();
    
    public void setXSpeed(int xSpeed);
    
    public int getXSpeed();
    
}
