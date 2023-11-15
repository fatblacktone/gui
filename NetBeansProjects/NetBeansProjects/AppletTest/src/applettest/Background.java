/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package applettest;

import java.awt.Dimension;

/**
 *
 * @author Fatblack
 */
public class Background{

    private int bgX, bgY, speedX,speedY;
    private int normalSpeed;

    public Background(int bgX, int bgY) {
        this.bgX = bgX;
        this.bgY = bgY;
        this.speedX = -1;
        this.normalSpeed = -1;
        this.speedY = 0;
    }

    
    public void update() {
        bgX += speedX;
        if (bgX <= -2160) {
            bgX += 4320;
        }
    }

    public int getNormalSpeed() {
        return normalSpeed;
    }

    public void setNormalSpeed(int normalSpeed) {
        this.normalSpeed = normalSpeed;
    }
    
    
    

    public Dimension getPosition() {
        return new Dimension(bgX,bgY);
    }

    public Dimension getXAndYSpeed() {
        return new Dimension(speedX,speedY);
    }

    public void setPosition(Dimension position) {
        bgX = position.width;
        bgY = position.height;
    }

    public void setXAndYSpeed(Dimension xAndYSpeed) {
        speedX = xAndYSpeed.width;
        speedY = xAndYSpeed.height;
    }
    
    
}
