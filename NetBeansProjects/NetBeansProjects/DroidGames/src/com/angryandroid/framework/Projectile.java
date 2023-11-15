/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.angryandroid.framework;

/**
 *
 * @author Fatblack
 */
public class Projectile {

    private int x, y, speedX,speedY;
    private boolean visible;
    private double angle;
    private int originX,originY;
    private int hyp = 20;
    private long lastUpdate,thisUpdate;
    private int gravity = 2;
    double velocityX;
    double velocityY;

    public Projectile(){
        
    }
    
    public Projectile(int x, int y) {
        this.x = this.originX = x;
        this.y = this.originY = y;        
        speedX = 10;
        visible = true;
        lastUpdate = System.nanoTime();
    }
    
    public Projectile(int x, int y,double angle) {
        this.x = this.originX = x;
        this.y = this.originY = y;  
        this.angle = angle;
        speedX = 10;
        visible = true;
        
    }

    public void update() {
        //System.out.println("Xpos: "+x+" SpeedX: "+speedX);
        x += speedX;
        if (x > 1000||x<0) {
            visible = false;
        }
        y += speedY;
        if(y>600||y<0){
            visible = false;
        }
    }
    
    
    public void update(int i){        
        hyp+=1;        
        
        double scaleX = Math.cos(angle);
        double scaleY = Math.sin(angle);
        
        velocityX = hyp*scaleX;
        velocityY = hyp*scaleY;
        thisUpdate = System.nanoTime();
        long elapsedTime = (long) ((thisUpdate - lastUpdate)*0.000001);
        
        
        
        x = (int) (x+velocityX);
        y = (int) ((y)+(velocityY));
        //originY+=2;
        //x =(int) (hyp * Math.cos(angle))+originX;
        //y =(int) (hyp * Math.sin(angle))+originY;
        
        if (x > 1000||x<0) {
            visible = false;
        }
        //y += speedY;
        if(y>800||y<0){
            visible = false;
        }
        
        
        //System.out.println("px: "+getX());
        //System.out.println("py: "+getY());
        //System.out.println("shot angle: "+getAngle());
        //originX-=2;
        lastUpdate = thisUpdate;
    }

    public double getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }
    
    

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }
    
    
    

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
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
