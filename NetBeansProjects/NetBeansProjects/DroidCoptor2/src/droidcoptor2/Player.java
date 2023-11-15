/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package droidcoptor2;

import com.angryandroid.framework.GameCharacter;
import java.awt.Polygon;

/**
 *
 * @author Tone
 */
public class Player implements GameCharacter{
    
    private int x,y,xSpeed,ySpeed;
    private final int MOVESPEED = 4;

    public Player(){
        y=250;
        x= 50;
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
    public void setX(int xCoord) {
        x = xCoord;
    }

    @Override
    public void setY(int yCoord) {
        y = yCoord;
    }

    @Override
    public void moveUp() {
        ySpeed = -MOVESPEED;
    }

    @Override
    public void moveDown() {
        ySpeed = MOVESPEED;
    }

    @Override
    public void moveLeft() {
        xSpeed = -MOVESPEED;
    }

    @Override
    public void moveRight() {
        xSpeed = MOVESPEED;
    }

    @Override
    public void stop() {
        xSpeed = 0;
        ySpeed = 0;
    }

    @Override
    public void update() {
        if(x+xSpeed<400&&x+xSpeed>5){
            x += xSpeed;
        }
        if(y+ySpeed<550&&y+ySpeed>5){
            y += ySpeed;
        }
    }

    @Override
    public void setBoundingPolygon(Polygon boundingPolygon) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Polygon getBoundingPolygon() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setXSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

    @Override
    public void setYSpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }
    
}
