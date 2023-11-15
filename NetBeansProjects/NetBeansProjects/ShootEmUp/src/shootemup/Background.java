/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shootemup;

/**
 *
 * @author Fatblack
 */
public class Background {

    private int bgX, bgY, speedX;

    public Background(int bgX, int bgY) {
        this.bgX = bgX;
        this.bgY = bgY;
        this.speedX = 2;
    }

    public void update() {
        bgX += speedX;
        if (bgX <= -2160) {
            bgX += 4320;
        }
    }

    public int getBgX() {
        return bgX;
    }

    public void setBgX(int bgX) {
        this.bgX = bgX;
    }

    public int getBgY() {
        return bgY;
    }

    public void setBgY(int bgY) {
        this.bgY = bgY;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }
}
