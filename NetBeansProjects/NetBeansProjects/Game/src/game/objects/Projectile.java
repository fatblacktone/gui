/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.objects;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Fatblack
 */
public class Projectile {

    private int x, y, speedX;
    private boolean visible;
    private Animation animation;
    private long currentTimeInMilliseconds,lastTimeInMiliseconds;
    public Projectile(int x, int y) {
        this.x = x;
        this.y = y;
        speedX = 7;
        visible = true;
        animation = new Animation();
        try {
            animation.addFrame(ImageIO.read(new File("./images/player/projectiles/machineGun.png")), y);
        } catch (IOException ex) {
            System.err.println("kjhkjhkjh");
        }
    }

    public void update() {
        x += speedX;
        if (x > 2000) {
            visible = false;
        }
        long updateBy = currentTimeInMilliseconds-lastTimeInMiliseconds;
        animation.update(updateBy, false);
    }
    
    public Image getImage(){
        return animation.getImage();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
