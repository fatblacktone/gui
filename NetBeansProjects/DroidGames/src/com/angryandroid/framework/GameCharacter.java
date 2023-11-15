/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.angryandroid.framework;

import java.awt.Polygon;

/**
 *
 * @author Fatblack
 */
public interface GameCharacter {
    
    /**
     * This method returns the x coordinate of the character
     * 
     * @return 
     */
    
    public int getX();
    
    /**
     * This method returns the y coordinate of the character
     * 
     * @return 
     */
    
    public int getY();
    
    /**
     * This method sets the x coordinates of the character
     * 
     * @param xCoord
     */
    
    public void setX(int xCoord);
    
    /**
     * This method sets the y coordinate of the character
     * 
     * @param yCoord 
     */ 
    
    
    public void setY(int yCoord);
    
    /**
     * This method moves the character up
     */
    
    public void moveUp();
    
    /**
     * This method moves the character down
     */
    
    public void moveDown();
    
    /**
     * This method moves the character left
     */
    
    public void moveLeft();
    
    /**
     * This method moves the character right
     */
    
    public void moveRight();
   
    /**
     * This method sets the horizontal speed
     */
    
    public void setXSpeed(int xSpeed);
    
    /**
     * This method sets the vertical speed
     */
    
    public void setYSpeed(int ySpeed);
    
    /**
     * This method stops all movement
     * 
     */
    
    public void stop();
    
    /**
     * This method updates the players position and state
     * 
     * @param currentNanosecond 
     */
    
    public void update();
    
    /**
     * This method set the bounding polygon for the character to allow for
     * interactions between objects
     * 
     * @param boundingPolygon 
     */
    
    public void setBoundingPolygon(Polygon boundingPolygon);
    
    /**
     * This method returns the bounding polygon
     * 
     */
    
    public Polygon getBoundingPolygon();
    
    
}
