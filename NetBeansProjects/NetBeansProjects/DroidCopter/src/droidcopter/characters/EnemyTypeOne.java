/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package droidcopter.characters;

import com.angryandroid.framework.Animation;
import com.angryandroid.framework.GameCharacter;
import com.angryandroid.framework.Projectile;
//import droidcopterapplet.gui.GameDisplay;
import java.awt.Image;
import java.awt.Polygon;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Fatblack
 */
public class EnemyTypeOne implements GameCharacter{
    private int xCoord;
    private int yCoord;
    private int health;
    private int xSpeed;
    private int ySpeed;
    private final droidcopter.characters.Player player;
    private int behaviour;
    private Polygon boundingPolygon;
    private boolean visible;
    private boolean dead;
    private Animation explosion;
    private long lastUpdateTime;
    private ArrayList<Projectile> projectiles;
    private int lastShotFired;
    private boolean hasPowerup;
    private double angleToPlayer;
    private double angle;
    
    public EnemyTypeOne(droidcopter.characters.Player player,ArrayList<Projectile> projectiles){
        this.player = player;
        Random rand = new Random();
        xCoord = 1010;
        yCoord = rand.nextInt(200) + 100;
        health = 1;
        xSpeed = -1  ;
        behaviour = rand.nextInt(4)+1;
        this.projectiles = projectiles;
        visible = true;
       loadExplosionImages();
       if(rand.nextInt(10)+1<2){
           hasPowerup = true;
       }
       
    }

    public boolean isHasPowerup() {
        return hasPowerup;
    }

    public void setHasPowerup(boolean hasPowerup) {
        this.hasPowerup = hasPowerup;
    }
    
    
    
    public Image getExplosion(){
        if(explosion.getCurrentFrame()==10){
            visible = false;
        }
        return explosion.getImage();
    }
    
    public void loadExplosionImages(){
        explosion = new Animation();
        int fileNumber = 1;
        File file = new File("./images/explosion/" + (fileNumber++) + ".png");
        while (file.exists()) {
            try {
                explosion.addFrame(ImageIO.read(file), 17);
            } catch (IOException ex) {
                //Logger.getLogger(GameDisplay.class.getName()).log(Level.SEVERE, null, ex);
            }
            file = new File("./images/explosion/" + (fileNumber++) + ".png");
        }
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
        if(!visible){
            
        }
    }

    public ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }

    public void setProjectiles(ArrayList<Projectile> projectiles) {
        this.projectiles = projectiles;
    }

    public void setBehaviour(int behaviour) {
        this.behaviour = behaviour;
    }

    public double getAngleToPlayer() {
        return angleToPlayer;
    }

    public void setAngleToPlayer(double angleToPlayer) {
        this.angleToPlayer = angleToPlayer;
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

    

    

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
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
        if (!dead) {
            
            switch (behaviour) {
                case 1:
                    //System.out.println("Player Match");
                    //// Player match 
                    if (player.getY() < getY()) {
                        setY(getY() - 1);
                    } else if (player.getY() > getY()) {
                        setY(getY() + 1);
                    }
                    
                    break;

                case 2:
                    //// Top runner
                    //System.out.println("Top Runner");
                    xSpeed = -4;
                    if (getY() > 30 && getX() > 500) {
                        yCoord -= 5;
                    }
                    if(getX() < 500 && getY() < 400){
                        yCoord +=5;
                    }
                    
                    break;
                case 3:
                    //// Bottom runner
                    //System.out.println("Bottom runner");
                    xSpeed = -4;
                    if(yCoord<400 && xCoord >500){
                        yCoord += 5;
                    }else if(yCoord>200&&xCoord <500){
                        yCoord -= 5;
                    }
                    
                    break;
                case 4:
                    //System.out.println("Nothing: "+ xCoord);
                   
                    if(xCoord<800){
                        xSpeed=0;
                    }
                    if (player.getY() < getY()) {
                        setY(getY() - 1);
                    } else if (player.getY() > getY()) {
                        setY(getY() + 1);
                    }
                    break;
                //// Player match and stay on screen
                    
            }
            if (lastShotFired >= 150) {
                double asDegrees = Math.toDegrees(angleToPlayer);
                if (asDegrees > 90) {
                    asDegrees += 270;
                } else {
                    asDegrees -= 90;
                }
                angleToPlayer = Math.toRadians(asDegrees);
                Projectile projectile = new Projectile(xCoord, yCoord, angleToPlayer);
                //projectiles.add(projectile);
                

                
                
                //Projectile projectile = new Projectile(xCoord + 10, yCoord + 30);
                //projectile.setSpeedX(-5);
                //projectiles.add(projectile);
                lastShotFired = 0;
            } else {
                lastShotFired++;
            }
            if (getX() < -50) {
                player.setScore(player.getScore()-5);
                setX(1100);
                Random rand = new Random();
                yCoord = rand.nextInt(200) + 100;
            }
            xCoord += xSpeed;
        }else{
            //System.out.println("updating anim"+lastUpdateTime);
            explosion.update((System.currentTimeMillis()- lastUpdateTime), false);
            if(explosion.getCurrentFrame()==10){
                visible = false;
            }
            lastUpdateTime = System.currentTimeMillis();
            //visible = false;
        }

    }

    public void setDead(boolean dead) {
        lastUpdateTime = System.currentTimeMillis();
        this.dead = dead;
    }

    public boolean isDead() {
        return dead;
    }
    
    

    @Override
    public void setBoundingPolygon(Polygon boundingPolygon) {
        this.boundingPolygon = boundingPolygon;
    }

    @Override
    public Polygon getBoundingPolygon() {
        int modX = getX();
        int modY = getY();

        int xPoints[] = {modX + 38,
            modX + 55,
            modX + 56,
            modX + 56,
            modX + 55,
            modX + 38,
            modX + 20,
            modX + 19,
            modX + 19,
            modX + 20
        };
        int yPoints[] = {modY + 20,
            modY + 25,
            modY + 32,
            modY + 47,
            modY + 54,
            modY + 59,
            modY + 54,
            modY + 47,
            modY + 32,
            modY + 25
        };
        boundingPolygon = new Polygon(xPoints, yPoints,xPoints.length);
        return boundingPolygon;
    }

    @Override
    public void stop() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setXSpeed(int xSpeed) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setYSpeed(int ySpeed) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
