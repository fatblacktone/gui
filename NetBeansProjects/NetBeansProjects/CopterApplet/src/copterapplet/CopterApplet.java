/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package copterapplet;

import copterapplet.gui.GameDisplayPanel;
import copterapplet.objects.Sprite;
import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.JApplet;
import javax.swing.JButton;

/**
 *
 * @author Fatblack
 */
public class CopterApplet extends JApplet implements ActionListener{

    private Image playerImage,
                  enemyImage,
                  background,
                  midground,
                  foreground,
                  welcome;
    
    private GameDisplayPanel displayPanel;
    
    private JButton start;
    
    private ArrayList<Sprite> sprites;
    
    
    private boolean loadImages(){
        URL base = null;
        try {
            base = getCodeBase();
        } catch (Exception e) {
            
        }
        
        background = getImage(base,"images/clouds.png");
        midground = getImage(base,"images/buildings.png");        
        playerImage = getImage(base,"images/ship2.png");
        foreground = getImage(base,"images/foreground.png");
        welcome = getImage(base,"images/test.png");
        boolean result = true;
        if(welcome==null||background==null||midground==null||playerImage==null||foreground==null){
            result = false;
        }
        return result;
    }
    
    
     @Override
    public void init() {
         if (loadImages()) {
             start = new JButton("START");
             start.setBounds(200, 340, 400, 50);            
             start.addActionListener(this);

            try {
                javax.swing.SwingUtilities.invokeAndWait(new Runnable() {
                    @Override
                    public void run() {
                        Sprite welcomeSprite = new Sprite(0,0,0,welcome);
                        ArrayList<Sprite> tempArray = new ArrayList<>();
                        tempArray.add(welcomeSprite);
                        displayPanel = new GameDisplayPanel(tempArray);
                        displayPanel.add(start);
                        setContentPane(displayPanel);
                        Game game = new Game(displayPanel,populateArray());
                        //create the GUI
                    }
                });
            } catch (InterruptedException | InvocationTargetException e) {
                System.err.println("createGUI didn't successfully complete");
            }
            //show welcome screen
        } else {
            //show contact webmaster to report a fault
        }



    }
     
    public ArrayList<Sprite> populateArray(){
        sprites = new ArrayList<>();
        sprites.add(new Sprite(0,0,2,background));
        sprites.add(new Sprite(2160,0,2,background));
        sprites.add(new Sprite(0,0,3,midground));
        sprites.add(new Sprite(2160,0,3,midground));
        sprites.add(new Sprite(0,0,4,foreground));
        sprites.add(new Sprite(2160,0,4,foreground));
        
        
        return sprites;
    }
    
    public void stepBackground(int i){
        int count = 0;
        for(Sprite s:sprites){
            s.setxPosition(s.getxPosition()-(s.getDepth()*i));            
            if(count==5){
                return;
            }
            count++;
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==start){
            start.setVisible(false);
            displayPanel.setSprites(sprites);
            displayPanel.repaint();
            
            
        }
    }
}
