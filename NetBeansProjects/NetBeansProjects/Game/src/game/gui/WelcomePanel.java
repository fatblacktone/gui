/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLayeredPane;

/**
 *
 * @author Fatblack
 */
public class WelcomePanel extends JLayeredPane {

    private ActionListener actionListener;
    private JButton start,
                    cancel;

    public WelcomePanel(ActionListener actionListener) {
        this.actionListener = actionListener;      
    }
    
    public void setup(){
        createButtons();
    }
    
    public void createButtons(){
        //initiate the buttons
        start = new JButton("Start");
        cancel = new JButton("Cancel");
        
        //name the buttons - so we can identify them in the actionListener
        start.setName("START");
        cancel.setName("CANCEL");
        
        //set the button size and position
        start.setBounds(200, 340, 400, 50);
        cancel.setBounds(200, 400, 400, 50);
        
        //add the action Listeners
        start.addActionListener(actionListener);
        cancel.addActionListener(actionListener);
        
        //add the buttons to the panel
        this.add(start,1);
        this.add(cancel,1);
        
        
    }
    
    public Dimension getButtonDimensions(){
        int buttonWidth = this.getWidth()/5;
        int buttonHeight = this.getHeight()/10;        
        return new Dimension(buttonWidth,buttonHeight);
    }
    
    
    
}
