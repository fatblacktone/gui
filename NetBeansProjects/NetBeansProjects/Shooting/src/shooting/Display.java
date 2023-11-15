/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shooting;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JLayeredPane;

/**
 *
 * @author Fatblack
 */
public class Display extends JLayeredPane implements MouseMotionListener, MouseListener{
   
    private int currentPointerX,currentPointerY;
    private boolean rightPressed,leftPressed;
    private ArrayList<Enemy> enemies;
    private Image trees;
    
    public Display(){
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        try{
            trees = ImageIO.read(new File("shooting.png"));
        }catch(IOException ex){
            System.err.println("Not loaded");
        }
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }
    
    
    
    @Override
    public void paintComponent(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0,0, 1000, 650);
        
        if(enemies!=null){
            for(Enemy e:enemies){
                Color oldColor = g.getColor();
                g.setColor(e.getColor());
                g.drawRect(e.getX(),e.getY(),(int) e.getSize().getWidth(),(int)e.getSize().getHeight());
                g.setColor(oldColor);
            }
        }
        g.drawImage(trees, 0, 0, this);
        
        g.setColor(Color.red);
        g.drawLine(70, 10, 70, 500);
        g.drawLine(113, 10, 113, 500);
        g.drawLine(175, 10, 175, 500);
        g.drawLine(225, 10, 225, 500);
        g.drawLine(325, 10, 325, 500);
        g.drawString("POINTER: xy  "+currentPointerX+" "+currentPointerY, 50, 50);
        g.drawString("LEFT BUTTON PRESSED: "+leftPressed, 50, 75);
        g.drawString("RIGHT BUTTON PRESSED: "+rightPressed, 50, 100);
    }
    
    
    
    @Override
    public void mouseDragged(MouseEvent me) {
        
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        currentPointerX = me.getX();
        currentPointerY = me.getY();
    }

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
        if(me.getButton()==MouseEvent.BUTTON1){
            leftPressed = true;
        }else if(me.getButton()==MouseEvent.BUTTON3){
            rightPressed = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        if(me.getButton()==MouseEvent.BUTTON1){
            leftPressed = false;
        }else if(me.getButton()==MouseEvent.BUTTON3){
            rightPressed = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent me) {
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
    
}
