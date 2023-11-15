/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package droidcopterapplet.characters;

import com.angryandroid.framework.GameCharacter;
import com.angryandroid.framework.Projectile;
import java.awt.Polygon;
import java.util.ArrayList;

/**
 *
 * @author Fatblack
 */
public class Player implements GameCharacter{
    
    private int xCoord,yCoord;
    private int xSpeed,ySpeed;
    private int lives,health;
    private ArrayList<Projectile> projectiles;
    private int score;
    private int damage;
    private int updatesImmuneFor;
    private boolean immune;
    private int shieldStrength;
    
    public Player(){
        projectiles = new ArrayList<>();
        xCoord = 200;
        yCoord = 200;
        health = 100;
        lives = 3;
        damage = 25;
        shieldStrength = 0;
        updatesImmuneFor = 100;
    }

    public boolean isImmune() {
        return immune;
    }

    public void setImmune(boolean immune) {
        this.immune = immune;
    }

    public int getShieldStrength() {
        return shieldStrength;
    }

    public void setShieldStrength(int shieldStrength) {
        this.shieldStrength = shieldStrength;
    }
    
    
    
    

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getUpdatesImmuneFor() {
        return updatesImmuneFor;
    }

    public void setUpdatesImmuneFor(int updatesImmuneFor) {
        this.updatesImmuneFor = updatesImmuneFor;
    }
    
    
    
    
    
    ///////////////////////////////////////////////////////////////////////////
    /////////////////////     Inherited methods   /////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    
    @Override
    public int getX() {
        return xCoord;
    }

    @Override
    public int getY() {
        return yCoord;
    }

    @Override
    public void setX(int xCoord) {
        this.xCoord = xCoord;
    }

    @Override
    public void setY(int yCoord) {
        this.yCoord = yCoord;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if(shieldStrength>0){
            shieldStrength += (health-this.getHealth());
            return;
        }else if(shieldStrength<0){
            shieldStrength = 0;
        }
        this.health = health;
    }
    
    
    
    public int getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

    public int getySpeed() {
        return ySpeed;
    }

    public void setySpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }

    public ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }

    public void setProjectiles(ArrayList<Projectile> projectiles) {
        this.projectiles = projectiles;
    }
    
    
    
    public void fire(double angle){
        double asDegrees = Math.toDegrees(angle);
        if(asDegrees>90){
            asDegrees += 270;
        }else{
            asDegrees -= 90;
        }
        angle = Math.toRadians(asDegrees);
        Projectile projectile = new Projectile(xCoord+75, yCoord+45,angle);
        projectiles.add(projectile);
        projectile.setSpeedX(30);
    }

    @Override
    public void moveUp() {
        ySpeed = -3;
    }

    @Override
    public void moveDown() {
        ySpeed = 3;
    }

    @Override
    public void moveLeft() {
        xSpeed = -3;
    }

    @Override
    public void moveRight() {
        xSpeed = 3;
    }

    @Override
    public void update() {
        if(shieldStrength>0){            
            immune = true;
        }else{
            immune = false;
        }
        if(updatesImmuneFor>0){
            health = 100; 
            shieldStrength = 5;
        }
        updatesImmuneFor--;
      
        if((xCoord+xSpeed)>30&&(xCoord+xSpeed)<900){
            xCoord+=xSpeed;
        }
        if((yCoord+ySpeed)>20){
            yCoord+=ySpeed;
        }
    }
    
    @Override
    public void stop(){
        xSpeed = 0;
        ySpeed = 0;
    }

    @Override
    public void setBoundingPolygon(Polygon boundingPolygon) {
        
    }

    @Override
    public Polygon getBoundingPolygon() {
        Polygon boundingPolygon = null;
        int modX = getX();
        int modY = getY();
        if(xSpeed==0){
            
            
            int xPoints[] = {modX+25,
                             modX+100,
                             modX+100,
                             modX+62,
                             modX+75,
                             modX+77,
                             modX+76,
                             modX+72,
                             modX+77,
                             modX+45,
                             modX+45,
                             modX+35,
                             modX+15,
                             modX+10,
                             modX+5,
                             modX+5
            }; 
            
            int yPoints[] = {
                    modY+20,
                    modY+25,
                    modY+30,
                    modY+27,
                    modY+35,
                    modY+40,
                    modY+47,
                    modY+52,
                    modY+62,
                    modY+60,
                    modY+52,
                    modY+37,
                    modY+35,
                    modY+45,
                    modY+45,
                    modY+25
            };
            boundingPolygon = new Polygon(xPoints, yPoints, xPoints.length);
        }else if(xSpeed>0){
            int xPoints[] = {modX+30,
                             modX+100,
                             modX+100,
                             modX+65,
                             modX+70,
                             modX+77,
                             modX+73,
                             modX+68,
                             modX+65,
                             modX+40,
                             modX+30,
                             modX+20,
                             modX+10,
                             modX,
                             modX+12
                             
            }; 
            
            int yPoints[] = {modY+13,
                             modY+43,
                             modY+45,
                             modY+33,
                             modY+40,
                             modY+43,
                             modY+55,
                             modY+60,
                             modY+70,
                             modY+60,
                             modY+30,
                             modY+25,
                             modY+35,
                             modY+32,
                             modY+10
            };
            boundingPolygon = new Polygon(xPoints, yPoints, xPoints.length);
            
        }else if(xSpeed<0){
            int xPoints[] = {modX+20,
                             modX+100,
                             modX+100,
                             modX+65,
                             modX+80,
                             modX+80,
                             modX+70,
                             modX+80,
                             modX+80,
                             modX+50,
                             modX+25,
                             modX+15,
                             modX+15,
                             modX+5,
                             modX+3
                             
                             
                             
            }; 
            
            int yPoints[] = {modY+30,
                             modY+13,
                             modY+16,
                             modY+25,
                             modY+35,
                             modY+40,
                             modY+52,
                             modY+50,
                             modY+55,
                             modY+62,
                             modY+42,
                             modY+45,
                             modY+55,
                             modY+55,
                             modY+35
                             
                    
                             
            };
            boundingPolygon = new Polygon(xPoints, yPoints, xPoints.length);
        }
        
        return boundingPolygon;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    
    
}
