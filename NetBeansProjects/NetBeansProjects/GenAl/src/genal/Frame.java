/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genal;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author Fatblack
 */
public class Frame extends JFrame implements ActionListener{
    
    public Frame(){
        Dimension screensDimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(950, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this .setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
    }
    
}
