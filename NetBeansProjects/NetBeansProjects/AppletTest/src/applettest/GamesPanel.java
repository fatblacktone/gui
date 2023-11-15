/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package applettest;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

/**
 *
 * @author Fatblack
 */
public class GamesPanel extends JLayeredPane implements ActionListener {

    private JButton start,
            cancel;
    private Image image,
            backgroundImage,
            midGroundImage,
            playerImage;
    private URL base;
    private ImageIcon icon;
    private JLabel label;
    private boolean firstRun;
    private Background bg1, bg2,fg1,fg2;
    private Player player;

    public GamesPanel(Image image) {
        this.image = image;
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new MyDispatcher());
        this.setSize(800, 600);

        this.setVisible(true);
        this.player = new Player(200, 100);
        createButtons();
        bg1 = new Background(0,0);
        bg2 = new Background(2160, 0);
        fg1 = new Background(0,0);
        fg2 = new Background(2160, 0);
        fg1.setXAndYSpeed(new Dimension(-2,0));
        fg2.setXAndYSpeed(new Dimension(-2,0));
        

    }

    public void createButtons() {
        firstRun = true;
        //initiate the buttons
        start = new JButton("Start");


        //name the buttons - so we can identify them in the actionListener
        start.setName("START");


        //set the button size and position
        start.setBounds(200, 340, 400, 50);


        //add the action Listeners
        start.addActionListener(this);


        //add the buttons to the panel
        this.add(start, 1);
        this.setOpaque(true);


    }

    public void update(){
        bg1.update();
        bg2.update();
        fg1.update();
        fg2.update();
        player.update();
    }
    /**
     *
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        if (firstRun) {
            g.drawImage(image, 0, 0, this);
        } else {
            g.drawImage(backgroundImage, bg1.getPosition().width,bg1.getPosition().height, this);
            g.drawImage(backgroundImage, bg2.getPosition().width,bg2.getPosition().height, this);
            g.drawImage(midGroundImage, fg1.getPosition().width,fg1.getPosition().height, this);
            g.drawImage(midGroundImage, fg2.getPosition().width,fg2.getPosition().height, this);
            g.drawImage(playerImage, player.getCenterX()-15,player.getCenterY()-15, this);
        }

        super.paintComponent(g);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == start) {
            firstRun = false;
            start.setVisible(false);
            repaint();
        }

    }

    public void setBackgroundImage(Image theImage) {
        backgroundImage = theImage;
    }
    
    public void setMidgroundImage(Image image){
        this.midGroundImage = image;
    }
    
    public void setPlayerImage(Image image){
        playerImage = image;
    }

    private class MyDispatcher implements KeyEventDispatcher {

        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {



            if (e.getID() == KeyEvent.KEY_PRESSED) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_1:

                        break;
                    case KeyEvent.VK_2:

                        break;
                    case KeyEvent.VK_ESCAPE:

                    case KeyEvent.VK_P:

                        break;
                    case KeyEvent.VK_UP:

                        break;

                    case KeyEvent.VK_DOWN:

                        break;

                    case KeyEvent.VK_LEFT:

                        break;

                    case KeyEvent.VK_RIGHT:

                        break;

                    case KeyEvent.VK_SPACE:





                        break;

                    case KeyEvent.VK_W:

                        break;

                    case KeyEvent.VK_S:

                        break;

                    case KeyEvent.VK_A:

                        break;

                    case KeyEvent.VK_D:

                        break;

                }
            } else if (e.getID() == KeyEvent.KEY_RELEASED) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:


                        break;

                    case KeyEvent.VK_DOWN:

                        break;

                    case KeyEvent.VK_LEFT:

                        break;

                    case KeyEvent.VK_RIGHT:

                        break;

                    case KeyEvent.VK_W:

                        break;

                    case KeyEvent.VK_S:


                        break;

                    case KeyEvent.VK_A:

                        break;

                    case KeyEvent.VK_D:


                        break;


                    case KeyEvent.VK_SPACE:

                        break;

                }
            } else if (e.getID() == KeyEvent.KEY_TYPED) {
                //System.out.println("3test3");
            }
            return false;
        }
    }
}