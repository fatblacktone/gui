/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scrollingshootertestbed;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JApplet;
import javax.swing.JLabel;

/**
 *
 * @author Tone
 */
public class ScrollingShooterTestBed extends JApplet {

    @Override
    public void init() {
        //Execute a job on the event-dispatching thread:
        //creating this applet's GUI.
        try {
            javax.swing.SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    createGUI();
                }
            });
        } catch (Exception e) {
            System.err.println("createGUI didn't successfully complete");
        }
    }

    private void createGUI() {
        JLabel label = new JLabel(
                "You are successfully running a Swing applet!");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
        getContentPane().add(label, BorderLayout.CENTER);
    }

    
}