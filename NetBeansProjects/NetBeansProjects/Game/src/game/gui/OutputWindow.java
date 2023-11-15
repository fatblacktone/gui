/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.gui;

import javax.swing.JTextArea;
import javax.swing.JWindow;

/**
 *
 * @author Fatblack
 */
public class OutputWindow extends JWindow{
    
    private int width,height;
    private JTextArea output = new JTextArea();

    public OutputWindow(int width, int height) {
        this.width = width;
        this.height = height;
        this.setSize(width,height);
        this .setLocation(-300, 700);
        this.add(output);
        this.setVisible(true);
    }
    
    
    public void setOutput(String outputString){
        output.setText(outputString);
    }
    
    
    
    
}
