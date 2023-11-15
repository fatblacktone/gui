/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package droidcopterapplet.characters;

import com.angryandroid.framework.GameCharacter;
import java.awt.Image;
import java.awt.Polygon;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Fatblack
 */
public class PowerUp implements GameCharacter{
    
    private int x,y;
    private Image image;
    private int type;
    public final static int DAMAGE_INCREASE = 1,
                            DEATH = 2,
                            HEALTH = 3,
                            KILL_ALL = 4,
                            EXTRA_LIFE = 5,
                            SHIELD = 6;
    private Polygon boundingPolygon;

    public PowerUp(int x, int y) {
        this.x = x;
        this.y = y;
        Random rand = new Random();
        type = rand.nextInt(6)+1;
        //loadImage();
    }
    
    
    
    

    public Image getImage() {
        return image;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    
    

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void moveUp() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void moveDown() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void moveLeft() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void moveRight() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void stop() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update() {
        y+=1;
    }

    @Override
    public void setBoundingPolygon(Polygon boundingPolygon) {
        this.boundingPolygon =  boundingPolygon;
    }

    @Override
    public Polygon getBoundingPolygon() {
        
        
        int xPoints[] = {
            getX(),
            getX()+50,
            getX()+50,
            getX()
        };
        
        int yPoints[] = {
            getY(),
            getY(),
            getY()+50,
            getY()+50
            
        };
        
        return boundingPolygon = new Polygon(xPoints, yPoints, xPoints.length);
        
        
    }
    
    
    
}
