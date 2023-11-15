/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shootemupapplet;

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
public class ShootemupApplet extends JApplet implements ActionListener{

    private Image playerImage,
                  enemyImage,
                  background,
                  midground,
                  foreground,
                  welcome;   
    
    private JButton start;
    
    
    
    
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
     
    
    
    

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==start){
            start.setVisible(false);      
        }
    }
}
