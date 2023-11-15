/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package applettest;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Image;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import javax.swing.JApplet;

/**
 *
 * @author Fatblack
 */
public class AppletTest extends JApplet {
    
    private static URL base;
    private GamesPanel gamePanel;
    private Image main,background,midground,helicopter;
    private Background bg1,bg2;
    private Game game;
    
    
    public AppletTest(){
        
    }

    /**
     *
     */
    @Override
    public void init() {
        setSize(800, 480);
        setBackground(Color.BLACK);
        loadImages();
        main = getImage(base, "images/test.png");
        gamePanel = new GamesPanel(main);
        gamePanel.setBackgroundImage(background);
        gamePanel.setMidgroundImage(midground);
        gamePanel.setPlayerImage(helicopter);
        game = new Game(gamePanel);
        //Execute a job on the event-dispatching thread:
        //creating this applet's GUI.
        try {
            javax.swing.SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    createGUI();
                }
            });
        } catch (InterruptedException | InvocationTargetException e) {
            System.err.println("createGUI didn't successfully complete");
        }
        
        
    }

    private void createGUI() {
        try {
            base = getCodeBase();
        } catch (Exception e) {
            
        }

        // Image Setups
        
        setContentPane(gamePanel);
    }
    
    private void loadImages(){
        try {
            base = getCodeBase();
        } catch (Exception e) {
            
        }
        background = getImage(base,"images/clouds.png");
        midground = getImage(base,"images/buildings.png");        
        helicopter = getImage(base,"images/ship2.png");
        
        
    }

    
}
