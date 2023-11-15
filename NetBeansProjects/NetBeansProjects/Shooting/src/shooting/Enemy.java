/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shooting;

import java.awt.Color;
import java.awt.Dimension;

/**
 *
 * @author Fatblack
 */
public class Enemy {
    
    private int x,y;
    private Dimension size;
    private Color color;

    public Enemy(int x, int y, Dimension size, Color color) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = color;
    }

    public Enemy() {
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

    public Dimension getSize() {
        return size;
    }

    public void setSize(Dimension size) {
        this.size = size;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    
    
    
    public void update(){
        
    }
    
}
