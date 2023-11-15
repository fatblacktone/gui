/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shootemup;


import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;
import shootemup.famework.Animation;

/**
 *
 * @author Fatblack
 */
public class Plane {

    private int centreX, centreY, speedX, speedY, health, power;
    private Background bg = GameLoopTest.getBg1();
    private boolean visible;
    private int behaviour, behaviourCount = 0, lastBehaviour, lastShotFired;
    private Animation anim = new Animation();
    private Image image;
    private ArrayList<Projectile> projectiles = new ArrayList<>();

    public Plane(int centreX, int centreY) {
        this.centreX = centreX;
        this.centreY = centreY;
        speedX = 0;
        speedY = 0;
        visible = true;
    }

    public Plane(int centreX, int centreY, Background bg) {
        this.centreX = centreX;
        this.centreY = centreY;
        this.bg = bg;
        speedX = 0;
        speedY = 0;
        visible = true;

        //behaviour = randomBehaviour();
    }

    public Animation getAnim() {
        return anim;
    }

    public void setAnim(Animation anim) {
        this.anim = anim;
    }

    public void updateAnim() {
        anim.update(10, true);
    }

    public Image getAnimImage() {
        return anim.getImage();
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    private int randomBehaviour() {
        Random rand = new Random();
        return rand.nextInt(10);
    }

    public void update() {
        centreX += speedX;
        speedX = bg.getSpeedX() - 2;
        if (centreX <= -60) {
        }
        if (centreY < 40) {
        } else if (centreX < 800) {
            if (centreY < 400) {
                if (behaviourCount >= 10) {
                    lastBehaviour = behaviour(randomBehaviour());
                    centreY += lastBehaviour;
                    behaviourCount = 0;//behaviour(behaviour);
                } else {
                    centreY += lastBehaviour;
                    behaviourCount++;
                }

            }

        }
        if (lastShotFired == 0) {
            this.fire();
            lastShotFired++;
        } else {
            lastShotFired++;
            if (lastShotFired >= 20) {
                lastShotFired = 0;
            }
        }
    }

    public int behaviour(int behaviour) {
        int returnvalue = 0;
        switch (behaviour) {
            case 0:
                returnvalue = 0;
                break;
            case 1:
                returnvalue = -2;
                break;
            case 2:
                returnvalue = +2;
                break;
            case 3:
                returnvalue = -4;
                break;
            case 4:
                returnvalue = +4;
                break;
            case 5:
                returnvalue = -6;
                break;
            case 6:
                returnvalue = +6;
                break;
            case 7:
                returnvalue = -8;
                break;
            case 8:
                returnvalue = +8;
                break;
            case 9:
                returnvalue = -10;
                break;
            case 10:
                returnvalue = +10;
                break;
        }

        return returnvalue;
    }

    public void fire() {
        if(!isVisible()){
            return;
        }
        Projectile p = new Projectile(centreX , centreY +15 );
        p.setSpeedX(speedX*2);
        projectiles.add(p);



    }

    public ArrayList getProjectiles() {
        return projectiles;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void die() {
    }

    public void attack() {
    }

    public int getCentreX() {
        return centreX;
    }

    public void setCentreX(int centreX) {
        this.centreX = centreX;
    }

    public int getCentreY() {
        return centreY;
    }

    public void setCentreY(int centreY) {
        this.centreY = centreY;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }
}
