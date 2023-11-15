/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.layers;

import java.awt.Dimension;

/**
 *
 * @author Fatblack
 */
public interface Layer {
    
    /**
     *
     */
    public String baseclass = "Layer";
    
    /**
     *
     */
    public int CenterX = 0;
    
    /**
     *
     */
    public int CenterY = 0;
    
    /**
     * This method is used to update the position of this layer.
     * an example of a left to right scrolling layer is:
     * 
     * x += speedX;
     *  if (x <= -2160) {
     *      x += 4320;
     *  }
     */
    
    
    public void update();
    
    public Dimension getPosition();
    
    public Dimension getXAndYSpeed();
    
    public void setPosition(Dimension position);
    
    public void setXAndYSpeed(Dimension xAndYSpeed);
    
    public int getNormalSpeed();
    
    public void setNormalSpeed(int speed);
    
    

    
    
}
