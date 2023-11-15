/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package applettest;

import java.awt.Image;
import java.awt.Polygon;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author Fatblack
 */
public class Player {
    
    
    private int centerX,centerY;
    private int xSpeed,ySpeed;
   
    private long currentTimeInMilliseconds,lastTimeInMiliseconds;
    //private ArrayList<Projectile> projectiles,missiles;
    private int weaponSelect = 1;
    private Polygon boundingPolygon;

    public Player(int centerX, int centerY) {
        this.centerX = centerX;
        this.centerY = centerY;
       
        
        
    }
    
  
           
           
   

    public Polygon getBoundingPolygon() {
        return boundingPolygon;
    }
    
    private void updatePolygon(){
        int xPoints[] = {getCenterX()-35,
                         getCenterX()+45,
                         getCenterX()+50,
                         getCenterX()+45,
                         getCenterX()-5,
                         getCenterX()-23,
                         getCenterX()-13,
                         getCenterX()+8,
                         getCenterX()+8,
                         getCenterX()-25,
                         getCenterX()-25,
                         getCenterX()-38};
         
         int yPoints[] = {getCenterY()-10,
         getCenterY()-10,
         getCenterY()-5,
         getCenterY()+5,
         getCenterY()+5,
         getCenterY()+20,
         getCenterY()+30,
         getCenterY()+30,
         getCenterY()+38,
         getCenterY()+38,
         getCenterY()+30,
         getCenterY()+21};
         
         boundingPolygon = new Polygon(xPoints, yPoints, 12);
        
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

    public void stop() {
        xSpeed = 0;
        ySpeed = 0;
    }

    public void moveRight() {
        xSpeed = 6;
    }

    public void moveUp() {
        ySpeed = -6;
    }

    public void moveDown() {
        ySpeed = 6;
    }

    public void moveLeft() {
        xSpeed = -6;
    }

    public void update() {
        long updateBy = currentTimeInMilliseconds-lastTimeInMiliseconds;
        
        lastTimeInMiliseconds = currentTimeInMilliseconds;
        currentTimeInMilliseconds = System.currentTimeMillis();
        //if (xSpeed < 0) {
          //  centerX += xSpeed;
        //}
        updatePolygon();
        if(centerX<=450&&centerX+xSpeed<450&&centerX+xSpeed>55){
            centerX += xSpeed;            
        }else{
            
        }
        if(centerX<=30){
            centerX = 30;
        }
        if(centerY<450&&centerY+ySpeed<450&&centerY+ySpeed>50){
            centerY += ySpeed;
        }else{
            
        
        }
        
        
    }

    public int getWeaponSelect() {
        return weaponSelect;
    }

    
    public void setWeaponSelect(int weaponSelect) {
        this.weaponSelect = weaponSelect;
    }   

    
  
    
    
    
    
}
