/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dontgetsquished.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

/**
 *
 * @author Fatblack
 */
public class GameFrame extends JFrame implements KeyListener{
    
    
    public GameFrame(){
        super("Don't Get Squished!!");
        this. setSize(800,800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        
    }
    
    
    ///////////////////KEY LISTENERS///////////////////////////////

    @Override
    public void keyTyped(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
