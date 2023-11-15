/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shooting;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;

/**
 *
 * @author Fatblack
 */
public class Shooting {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("SHOOTING");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Display display = new Display();
        frame.setContentPane(display);
        frame.setSize(960, 640);
        frame.setVisible(true);
        Loop loop = new Loop();
        loop.setDisplay(display);
        
        Random rand = new Random();
        
        ArrayList<Enemy> enemies = new ArrayList<>();
        
        
        display.setEnemies(enemies);
        
        
        
        boolean test = false;
        
        String out = test ? "false": "true";
        
        System.out.println(out);
        
        //loop.runGameLoop();
        
        
        
    }
}
