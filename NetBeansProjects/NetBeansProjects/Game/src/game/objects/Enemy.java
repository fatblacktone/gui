/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.objects;

import java.awt.Image;
import java.awt.Polygon;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Fatblack
 */
public class Enemy {
    private Image image;
    private int centerX,centerY;
    private int xSpeed,ySpeed;
    private Animation animation,movingForwardsAnimation,movingBackwardsAnimation;
    private long currentTimeInMilliseconds,lastTimeInMiliseconds;
    private ArrayList<Projectile> projectiles,missiles;
    private int weaponSelect = 1;
    private Player player;
    private int numberOfUpdates;
    private Polygon boundingPolygon;

    public Enemy(int centerX, int centerY,Player player) {
        this.player = player;
        this.centerX = centerX;
        this.centerY = centerY;
        projectiles = new ArrayList<>();
        loadImages();
        
    }
    
    private void loadImages(){
       
       missiles = new ArrayList<>();
       
       movingForwardsAnimation = new Animation();
       movingBackwardsAnimation = new Animation();
       animation = new Animation();
        try {
            image = ImageIO.read(new File("./images/enemy/ship.png"));
        } catch (IOException ex) {
            
        }
       
       
       
        currentTimeInMilliseconds = System.currentTimeMillis();
       
        
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

    public Polygon getBoundingPolygon() {
        return boundingPolygon;
    }
    
    private void updatePolygon(){
        int xPoints[] = {getCenterX()-50,
         getCenterX()-30,
         getCenterX()-15,
         getCenterX()-9,
         getCenterX(),
         getCenterX()+9,
         getCenterX()+15,
         getCenterX()+30,
         getCenterX()+50,
         getCenterX()+30,
         getCenterX(),
         getCenterX()-30};
         
         int yPoints[] = {getCenterY()+5,
         getCenterY()-3,
         getCenterY()-8,
         getCenterY()-15,
         getCenterY()-17,
         getCenterY()-15,
         getCenterY()-8,
         getCenterY()-3,
         getCenterY()+5,
         getCenterY()+17,
         getCenterY()+25,
         getCenterY()+17};
         
         boundingPolygon = new Polygon(xPoints, yPoints, 12);
    }
    
    
    
    public Image getImage(){
        
        return image;
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
        updatePolygon();
        if(numberOfUpdates<30){
            numberOfUpdates++;
        }else{
            this.shoot();
            numberOfUpdates=0;
        }
        long updateBy = currentTimeInMilliseconds-lastTimeInMiliseconds;
        animation.update(updateBy, false);
        movingForwardsAnimation.update(updateBy, false);
        lastTimeInMiliseconds = currentTimeInMilliseconds;
        currentTimeInMilliseconds = System.currentTimeMillis();
        //if (xSpeed < 0) {
          //  centerX += xSpeed;
        //}
        setxSpeed(-6);
        if(centerX>700&&centerX+xSpeed>700){
            centerX += xSpeed;
        }
        
        if(centerY>=player.getCenterY()&&centerY-player.getCenterY()>10){
            ySpeed=-2;
        }else if(centerY<=player.getCenterY()&&player.getCenterY()-centerY>10){
            ySpeed=2;
        }
        
        if(centerY<400&&centerY>50&&centerY+ySpeed<400&&centerY+ySpeed>50){
            centerY+=ySpeed;
        }
        
        
    }

    public int getWeaponSelect() {
        return weaponSelect;
    }

    
    public void setWeaponSelect(int weaponSelect) {
        this.weaponSelect = weaponSelect;
    }
    
    

    public void shoot() {
        switch (weaponSelect) {
            case 1:
                Projectile p = new Projectile(centerX - 20, centerY + 32);
                p.setSpeedX(-10);
                projectiles.add(p);
                
                break;
                /*case 2:
                 * System.out.println("Fire Missile");
                 * Missile m = new Missile(centerX, centerY);
                 * //m.setPlayer(this);
                 * missiles.add(m);
                 * break;*/
        }
    }

    public ArrayList getProjectiles() {
        return projectiles;
    }
    
    public ArrayList getMissiles(){
        return missiles;
    }

  
    
    
    
}
