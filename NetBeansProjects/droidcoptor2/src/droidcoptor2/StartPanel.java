/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package droidcoptor2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLayeredPane;

/**
 *
 * @author Tone
 */
public class StartPanel extends JLayeredPane{
    
    private JButton start,cancel;
    
    public StartPanel(ActionListener listener){
        start = new JButton("START");
        cancel = new JButton("CANCEL");
        
        start.addActionListener(listener);
        cancel.addActionListener(listener);
        
        start.setBounds(350,300,300,50);
        cancel.setBounds(350,360,300,50);
        
        this.add(start);
        this.add(cancel);
    }

    
    
}
