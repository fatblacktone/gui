/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleplatformgame.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import simpleplatformgame.data.Player;

/**
 *
 * @author Fatblack
 */
public class GameFrame extends JFrame implements KeyListener {

    private boolean rightKeyPressed,
            leftKeyPressed,
            downKeyPressed,
            upKeyPressed,
            spacePressed;
    private int playerSpritePosition;
    private Image image, character;
    private Image sprite[];
    private Graphics second;
    private Player player;

    public GameFrame(Player player) {
        this.setSize(800, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.addKeyListener(this);
        this.setBackground(Color.white);
        this.setVisible(true);
        sprite = new Image[10];
        try {
            sprite[0] = ImageIO.read(new File("test.jpg"));
            sprite[1] = ImageIO.read(new File("test2.jpg"));
        } catch (IOException ex) {
            System.out.println("jdsflkfjslkdj");
        }
        this.player = player;
        playerSpritePosition = 1;
        loadSprite();
    }

    private void loadSprite() {
        if (playerSpritePosition == 1) {

            character = sprite[0];

            playerSpritePosition = 2;
        } else if (playerSpritePosition == 2) {
            character = sprite[1];
            playerSpritePosition = 1;
        }
    }

    @Override
    public void update(Graphics g) {
        if (image == null) {
            image = createImage(this.getWidth(), this.getHeight());
            second = image.getGraphics();
        }

        second.setColor(getBackground());
        second.fillRect(0, 0, getWidth(), getHeight());
        second.setColor(getForeground());
        paint(second);

        g.drawImage(image, 0, 0, this);

    }

    @Override
    public void paint(Graphics g) {
        //loadSprite();
        try {
            g.setColor(Color.white);
            //g.fillRect(player.getxPosition()-20, player.getyPosition()-20, player.getxPosition()+20,player.getyPosition()+20);
            g.drawImage(character, player.getxPosition(), player.getyPosition(), this);
        } catch (NullPointerException ex) {
        }

    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                
                player.setyPosition(player.getyPosition()+player.getySpeed());
                        
                player.setySpeed(player.getySpeed()-1);
                upKeyPressed = true;
                break;

            case KeyEvent.VK_DOWN:
                
                player.setyPosition(player.getyPosition()+player.getySpeed());
                player.setySpeed(player.getySpeed()+1);
                downKeyPressed = true;
                break;

            case KeyEvent.VK_LEFT:
                leftKeyPressed = true;
                break;

            case KeyEvent.VK_RIGHT:
                rightKeyPressed = true;
                break;

            case KeyEvent.VK_SPACE:
                spacePressed = true;
                System.out.println("Jump");

                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                System.out.println("Stop moving up");
                player.setySpeed(0);
                upKeyPressed = false;
                break;

            case KeyEvent.VK_DOWN:
                System.out.println("Stop moving down");
                player.setySpeed(0);
                downKeyPressed = false;
                break;

            case KeyEvent.VK_LEFT:
                player.setxSpeed(0);
                leftKeyPressed = false;
                break;

            case KeyEvent.VK_RIGHT:
                player.setxSpeed(0);
                rightKeyPressed = false;
                break;

            case KeyEvent.VK_SPACE:
                spacePressed = false;
                System.out.println("Stop jumping");
                break;

        }

    }
}
