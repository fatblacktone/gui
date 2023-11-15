/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.gui;

import javax.swing.JFrame;
import javax.swing.JWindow;

/**
 *
 * @author Fatblack
 */
public class GameFrame extends JFrame{

    private int width,height;

    public GameFrame(int width, int height) {
        this.width = width;
        this.height = height;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        this.setSize(width, height);
        //this.setLocation(50,50);
        this.setUndecorated(true);
        this.setVisible(true);
        
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
     
    
}
