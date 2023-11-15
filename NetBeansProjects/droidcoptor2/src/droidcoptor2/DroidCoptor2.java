/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package droidcoptor2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author Tone
 */
public class DroidCoptor2 implements ActionListener{
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Droid Copter");
                frame.setSize(1000, 600);
                frame.setUndecorated(true);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                Display display = new Display();
                
               
                Player player = new Player();
                display.setPlayer(player);
                display.loadGameLayerImages();
                display.loadEnemyImages();
                display.loadPlayerImages();
                frame.setContentPane(display);
                Loop loop = new Loop(display);
                loop.runGameLoop();
                frame.setVisible(true);
            };
            
        });
        // TODO code application logic here
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
