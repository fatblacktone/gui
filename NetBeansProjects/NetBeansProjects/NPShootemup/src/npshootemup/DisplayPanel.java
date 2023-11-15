/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package npshootemup;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

/**
 *
 * @author Fatblack
 */
public class DisplayPanel extends JLayeredPane{
    
    private JFrame frame = new JFrame("Shoot Em Up");
    
    private int xPosition;
    private final double MOVE_SPEED = 1000000000/200;
    private double lastRenderedTime;
    private double lastMoved;
    private Image background;

    public DisplayPanel() {
        frame.setSize(840, 530);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(this);
        frame.setVisible(true);
        try {
            background = ImageIO.read(new File("./images/Background.png"));
        } catch (IOException ex) {
            Logger.getLogger(DisplayPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public void paintComponent(Graphics g){
        
        g.drawImage(background, xPosition, 0, this);
    }
    
    public void update(double time){
        xPosition -= 3;
        if(xPosition>790){
            xPosition = 0;
        }
    }

    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }
    
    
    
}
