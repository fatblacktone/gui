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
public class BackgroundLayer implements Layer{

    private int bgX, bgY, speedX,speedY;
    private int normalSpeed;

    public BackgroundLayer(int bgX, int bgY) {
        this.bgX = bgX;
        this.bgY = bgY;
        this.speedX = -1;
        this.normalSpeed = -1;
        this.speedY = 0;
    }

    @Override
    public void update() {
        bgX += speedX;
        if (bgX <= -1600) {
            bgX += 3200;
        }
    }

    @Override
    public int getNormalSpeed() {
        return normalSpeed;
    }

    @Override
    public void setNormalSpeed(int normalSpeed) {
        this.normalSpeed = normalSpeed;
    }
    
    
    

    @Override
    public Dimension getPosition() {
        return new Dimension(bgX,bgY);
    }

    @Override
    public Dimension getXAndYSpeed() {
        return new Dimension(speedX,speedY);
    }

    @Override
    public void setPosition(Dimension position) {
        bgX = position.width;
        bgY = position.height;
    }

    @Override
    public void setXAndYSpeed(Dimension xAndYSpeed) {
        speedX = xAndYSpeed.width;
        speedY = xAndYSpeed.height;
    }
    
    
}
