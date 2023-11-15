/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package droidcoptor2;

import com.angryandroid.framework.*;
import java.awt.Graphics;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLayeredPane;

/**
 *
 * @author Tone
 */
public class Display extends JLayeredPane implements DisplayPanel, MouseListener, MouseMotionListener, ActionListener {

    private boolean upKeyPressed, downKeyPressed, leftKeyPressed, rightKeyPressed, spacePressed;
    private BufferedImage backgroundImage;
    private Background backgroundOne;
    private Background backgroundTwo;
    private Background foregroundOne;
    private Background foregroundTwo;
    private BufferedImage playerImage;
    private GameCharacter player;
    private BufferedImage foregroundImage;
    private Animation playerStill;
    private long lastUpdateTime;
    private boolean gameRunning;
    private boolean starting;
    private JButton start, cancel;
    private BufferedImage backMidgroundImage;
    private Background midBackgroundOne;
    private float startTimeInMillis;
    private BufferedImage apcImage;
    private APCEnemy apc;

    public Display() {
        startTimeInMillis = System.currentTimeMillis();
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new MyDispatcher());
        start = new JButton("START");
        cancel = new JButton("CANCEL");

        start.addActionListener(this);
        cancel.addActionListener(this);
        gameRunning = false;
        starting = true;

        start.setBounds(350, 480, 300, 50);
        cancel.setBounds(350, 540, 300, 50);
        apc = new APCEnemy(1250, -2);
        this.add(start);
        this.add(cancel);
    }
    //Inherited methods from the DisplayPanel Interface

    @Override
    public void loadGameLayerImages() {
        try {
            backgroundImage = ImageIO.read(new File("./images/bg.jpg"));
            foregroundImage = ImageIO.read(new File("./images/bg2.png"));
            backMidgroundImage = ImageIO.read(new File("./images/bg3.png"));
            backgroundOne = new Background(0, 0, 2000, backgroundImage);
            backgroundTwo = new Background(2000, 0, 2000, backgroundImage);
            midBackgroundOne = new Background(0, 0, 3000, backMidgroundImage);
            foregroundOne = new Background(0, 0, 3000, foregroundImage);
            foregroundTwo = new Background(3000, 0, 3000, foregroundImage);
            midBackgroundOne.setSpeedX(-0.5f);
            foregroundOne.setSpeedX(-4);
            foregroundTwo.setSpeedX(-4);
        } catch (IOException ex) {
            Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("hjkdshkh");
        }

    }

    @Override
    public void loadPlayerImages() {
        try {
            playerImage = ImageIO.read(new File("./images/player/still/1.png"));
        } catch (IOException ex) {
            System.out.println("fshfkjhd");
        }
        playerStill = new Animation();
        int fileNumber = 1;

        File file = new File("./images/player/still/" + (fileNumber++) + ".png");
        while (file.exists()) {
            try {
                playerStill.addFrame(ImageIO.read(file), 60);
            } catch (IOException ex) {
                Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
            }
            file = new File("./images/player/still/" + (fileNumber++) + ".png");
        }
        //playerForwards = new Animation();
    }

    @Override
    public void loadEnemyImages() {
        try {
            apcImage = ImageIO.read(new File("./images/enemies/apc.png"));
        } catch (IOException ex) {
            System.out.println("fshfkjhd");
        }
    }

    @Override
    public InteractableLayer[] loadInteractableImages() {
        return null;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPlayer(GameCharacter player) {
        this.player = player;
    }

    @Override
    public GameCharacter getPlayer() {
        return null;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setEnemies(ArrayList<GameCharacter> enemies) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gameRunning) {
            g.drawImage(backgroundImage, backgroundOne.getxCoord(), 0, this);
            g.drawImage(backgroundImage, backgroundTwo.getxCoord(), 0, this);
            g.drawImage(backMidgroundImage, midBackgroundOne.getxCoord(), 0, this);
            g.drawImage(apcImage,apc.getX(),apc.getY(),this);
            g.drawImage(foregroundImage, foregroundOne.getxCoord(), 0, this);
            g.drawImage(foregroundImage, foregroundTwo.getxCoord(), 0, this);

            g.drawImage(playerStill.getImage(), player.getX(), player.getY(), this);
        } else if (starting) {
            BufferedImage startImage = null;
            try {
                startImage = ImageIO.read(new File("./images/startup.png"));
            } catch (IOException ex) {
                System.out.println("fshfkjhd");
            }
            g.drawImage(startImage, 0, 0, this);
        }
        if(backgroundOne.getxCoord()<=-100){
            //System.exit(0);
        }
    }

    @Override
    public void update() {
        if (gameRunning) {
            playerStill.update(System.currentTimeMillis() - lastUpdateTime, false);
            backgroundOne.update();
            backgroundTwo.update();
            midBackgroundOne.update();
            foregroundOne.update();
            foregroundTwo.update();
            apc.update();
            player.update();
            lastUpdateTime = System.currentTimeMillis();
            if (rightKeyPressed && player.getX() > 380) {
                backgroundOne.update();
                backgroundTwo.update();
                midBackgroundOne.update();
                foregroundOne.update();
                foregroundTwo.update();
            }

            repaint();
        } else if (starting) {
        }
    }

    /// Inherited methods from the Mouse Listener Interface
    @Override
    public void mouseClicked(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /// Inherited methods from mouse motion Listener
    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) {
            gameRunning = true;
            start.setVisible(false);
            cancel.setVisible(false);
        } else if (e.getSource() == cancel) {
            System.exit(0);
        }
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
                        System.exit(0);
                    case KeyEvent.VK_P:
                        break;
                    case KeyEvent.VK_UP:
                        upKeyPressed = true;
                        player.moveUp();
                        break;

                    case KeyEvent.VK_DOWN:
                        downKeyPressed = true;
                        player.moveDown();
                        break;

                    case KeyEvent.VK_LEFT:
                        leftKeyPressed = true;
                        player.moveLeft();
                        break;

                    case KeyEvent.VK_RIGHT:
                        rightKeyPressed = true;
                        player.moveRight();
                        break;

                    case KeyEvent.VK_SPACE:

                        spacePressed = true;


                        break;

                    case KeyEvent.VK_W:
                        upKeyPressed = true;
                        break;

                    case KeyEvent.VK_S:
                        downKeyPressed = true;
                        break;

                    case KeyEvent.VK_A:
                        leftKeyPressed = true;
                        break;

                    case KeyEvent.VK_D:
                        rightKeyPressed = true;
                        break;

                }
            } else if (e.getID() == KeyEvent.KEY_RELEASED) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        upKeyPressed = false;
                        if (leftKeyPressed || rightKeyPressed) {
                            player.setYSpeed(0);
                            return false;
                        }
                        player.stop();
                        break;

                    case KeyEvent.VK_DOWN:
                        downKeyPressed = false;
                        if (leftKeyPressed || rightKeyPressed) {
                            player.setYSpeed(0);
                            return false;
                        }
                        player.stop();
                        break;

                    case KeyEvent.VK_LEFT:
                        leftKeyPressed = false;

                        if (rightKeyPressed) {
                            player.moveRight();
                            return false;
                        } else if (upKeyPressed || downKeyPressed) {
                            player.setXSpeed(0);
                            return false;
                        }
                        player.stop();
                        break;

                    case KeyEvent.VK_RIGHT:
                        rightKeyPressed = false;

                        if (leftKeyPressed) {
                            player.moveLeft();
                            return false;
                        } else if (upKeyPressed || downKeyPressed) {
                            player.setXSpeed(0);
                            return false;
                        }
                        player.stop();
                        break;

                    case KeyEvent.VK_W:
                        upKeyPressed = false;
                        if (leftKeyPressed || rightKeyPressed) {
                            return false;
                        }
                        break;

                    case KeyEvent.VK_S:
                        downKeyPressed = false;
                        if (leftKeyPressed || rightKeyPressed) {

                            return false;
                        }

                        break;

                    case KeyEvent.VK_A:
                        leftKeyPressed = false;

                        if (rightKeyPressed) {
                            return false;
                        } else if (upKeyPressed || downKeyPressed) {
                            return false;
                        }
                        break;

                    case KeyEvent.VK_D:

                        rightKeyPressed = false;
                        if (leftKeyPressed) {
                            return false;
                        } else if (upKeyPressed || downKeyPressed) {
                            return false;
                        }

                        break;


                    case KeyEvent.VK_SPACE:
                        spacePressed = false;

                        break;

                }
            } else if (e.getID() == KeyEvent.KEY_TYPED) {
                //System.out.println("3test3");
            }
            return false;
        }
    }
}
