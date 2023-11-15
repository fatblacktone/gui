/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleplatformgame.data;

/**
 *
 * @author Fatblack
 */
public class Player {
    
    private int xPosition,
                yPosition,
                health,
                speed,
                ySpeed,
                xSpeed;
    
    

    public Player() {
        xPosition = 100;
        yPosition = 250;
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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getySpeed() {
        return ySpeed;
    }

    public void setySpeed(int ySpeed) {
        if((ySpeed==10)||(ySpeed==-10))return;
        this.ySpeed = ySpeed;
    }

    public int getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(int xSpeed) {
        if((xSpeed==10)||(xSpeed==-10))return;
        this.xSpeed = xSpeed;
    }
    
    
    
    
    
}
