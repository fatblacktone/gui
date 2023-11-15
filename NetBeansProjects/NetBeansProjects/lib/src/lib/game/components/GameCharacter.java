/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.game.components;

import lib.game.graphics.Animation;

/**
 *
 * @author Fatblack
 */
public class GameCharacter {
    
    private Animation still,
                      movingRight,
                      movingLeft,
                      movingDown,
                      movingUp;
    
    private int centerX,centerY;
    
    private int health;
    
    private Weapon weapon;
    
    private void moveUp(){
        
    }

    public Animation getStill() {
        return still;
    }

    public void setStill(Animation still) {
        this.still = still;
    }

    public Animation getMovingRight() {
        return movingRight;
    }

    public void setMovingRight(Animation movingRight) {
        this.movingRight = movingRight;
    }

    public Animation getMovingLeft() {
        return movingLeft;
    }

    public void setMovingLeft(Animation movingLeft) {
        this.movingLeft = movingLeft;
    }

    public Animation getMovingDown() {
        return movingDown;
    }

    public void setMovingDown(Animation movingDown) {
        this.movingDown = movingDown;
    }

    public Animation getMovingUp() {
        return movingUp;
    }

    public void setMovingUp(Animation movingUp) {
        this.movingUp = movingUp;
    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
    
    
    
}
