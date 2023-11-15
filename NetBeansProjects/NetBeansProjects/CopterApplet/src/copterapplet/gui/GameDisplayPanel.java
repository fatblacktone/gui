/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package copterapplet.gui;

import copterapplet.objects.Sprite;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.JLayeredPane;

/**
 *
 * @author Fatblack
 */
public class GameDisplayPanel extends JLayeredPane{
    
    private ArrayList<Sprite> sprites;

    public GameDisplayPanel(ArrayList<Sprite> sprites) {
        this.sprites = sprites;
        
    }

    public void setSprites(ArrayList<Sprite> sprites) {
        this.sprites = sprites;
    }
    
    /**
     *
     * @param g
     */
    @Override
    public void paintComponent(Graphics g){
        
        for(Sprite s:sprites){
            g.drawImage(s.getImage(), s.getxPosition(), s.getyPosition(), this);
        }
        
    }

   
    
    
}
