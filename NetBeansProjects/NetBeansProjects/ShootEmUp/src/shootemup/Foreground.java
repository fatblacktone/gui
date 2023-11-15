/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shootemup;

/**
 *
 * @author Fatblack
 */
public class Foreground {
    
    private int fgX,fgY,speedX;

    public Foreground(int fgX, int fgY) {
        this.fgX = fgX;
        this.fgY = fgY;
        this.speedX = 3;
    }
    
    public void update() {
        fgX += speedX;
        if (fgX <= -2160) {
            fgX += 4320;
        }
    }

    public int getFgX() {
        return fgX;
    }

    public void setFgX(int fgX) {
        this.fgX = fgX;
    }

    public int getFgY() {
        return fgY;
    }

    public void setFgY(int fgY) {
        this.fgY = fgY;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }
    
    
    
    
    
}
