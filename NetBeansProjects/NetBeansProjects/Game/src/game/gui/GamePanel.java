/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.gui;

import game.Game;
import game.Tilemap;
import game.layers.BackgroundLayer;
import game.layers.Layer;
import game.objects.Enemy;
import game.objects.Player;
import game.objects.Projectile;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Polygon;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Fatblack
 */
public class GamePanel extends JPanel {

    float interpolation;
    private Game gm;
    private Image backGround,
            layer1;
    private int currentLevel = 1;
    private Layer[] layers;
    private Player player;
    private boolean rightKeyPressed,
                leftKeyPressed,
                downKeyPressed,
                upKeyPressed,
                spacePressed,
                firing;
    private int lastShotFired;
    private boolean needToCount;
    private Enemy enemy;
    int playerHits = 100,enemyHits = 100;
    private Tilemap tilemap;

    public GamePanel(Game gm) {
        this.gm = gm;
        setFocusable(true);
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new GamePanel.MyDispatcher());
        this.setOpaque(true);
        this.setBackground(Color.BLACK);
        this.setVisible(true);
        player = new Player(200, 200);
        enemy= new Enemy(900, 200,player);

        //TODO Load images
        try {
            backGround = ImageIO.read(new File("./images/level" + currentLevel + "Background.png"));
            layer1 = ImageIO.read(new File("./images/level" + currentLevel + "Layer1.png"));
        } catch (IOException ex) {
            System.err.println("./images/level" + currentLevel + "Background.png");
        }
        layers = new BackgroundLayer[4];
        //TODO position scenery
        layers[0] = new BackgroundLayer(0, 0);
        layers[1] = new BackgroundLayer(1600, 0);
        layers[2] = new BackgroundLayer(0, 0);
        layers[3] = new BackgroundLayer(1600, 0);

        layers[2].setXAndYSpeed(new Dimension(-2, 0));
        layers[3].setXAndYSpeed(new Dimension(-2, 0));
        layers[2].setNormalSpeed(-3);        
        layers[3].setNormalSpeed(-3);
        repaint();
        //TODO create animations


    }

    public void setInterpolation(float interp) {
        interpolation = interp;
    }

    public void update() {
        player.update();
        
        if(player.getCenterX()>445&&player.getxSpeed()>0){
            layers[0].setXAndYSpeed(new Dimension(-player.getxSpeed()+layers[0].getNormalSpeed(),
                                                  0));
            layers[1].setXAndYSpeed(new Dimension(-player.getxSpeed()+layers[1].getNormalSpeed(),
                                                  0));
            layers[2].setXAndYSpeed(new Dimension(-player.getxSpeed()+layers[2].getNormalSpeed(),
                                                  0));
            layers[3].setXAndYSpeed(new Dimension(-player.getxSpeed()+layers[3].getNormalSpeed(),
                                                  0));
            tilemap.setxPosition(9);
        
        }else{
            layers[0].setXAndYSpeed(new Dimension(layers[0].getNormalSpeed(),
                                                  0));
            layers[1].setXAndYSpeed(new Dimension(layers[1].getNormalSpeed(),
                                                  0));
            layers[2].setXAndYSpeed(new Dimension(layers[2].getNormalSpeed(),
                                                  0));
            layers[3].setXAndYSpeed(new Dimension(layers[3].getNormalSpeed(),
                                                  0));
            tilemap.setxPosition(6);
        
        }
        
        layers[0].update();
        layers[1].update();
        layers[2].update();
        layers[3].update();
        ArrayList projectiles = player.getProjectiles();
        for (int i = 0; i < projectiles.size(); i++) {
                Projectile p = (Projectile) projectiles.get(i);
                if (p.isVisible() == true) {
                    p.update();
                    if(enemy.getBoundingPolygon().contains(p.getX(),p.getY())){
                        enemyHits--;
                        System.out.println("Enemy hit; "+enemyHits);
                        p.setVisible(false);
                    }
                } else {
                    projectiles.remove(i);
                }
            }
        ArrayList missiles = player.getMissiles();
            for (int i = 0; i < missiles.size(); i++) {
                Projectile p = (Projectile) missiles.get(i);
                if (p.isVisible() == true) {
                    p.update();
                } else {
                     missiles.remove(i);
                }
            }
            
            ArrayList enemyProj = enemy.getProjectiles();
            for (int i = 0; i < enemyProj.size(); i++) {
                Projectile p = (Projectile) enemyProj.get(i);
                if (p.isVisible() == true) {
                    p.update();
                    if(player.getBoundingPolygon().contains(p.getX(),p.getY())){
                        playerHits--;
                        System.out.println("Player hit; "+playerHits);
                        p.setVisible(false);
                    }
                } else {
                     enemyProj.remove(p);
                }
            }
            
            
           
        if (spacePressed) {
                
                if (lastShotFired == 0) {
                    player.shoot();
                    needToCount = true;
                    lastShotFired++;
                } else {
                    lastShotFired++;
                    if(player.getWeaponSelect()==1){
                        if (lastShotFired >= 4) {
                            lastShotFired = 0;
                            needToCount = false;
                        }
                    }else if(player.getWeaponSelect()==2){
                        if (lastShotFired >= 30) {
                            lastShotFired = 0;
                            needToCount = false;
                        }
                    }
                }
                
            }else if(needToCount){
            lastShotFired++;
        }
        enemy.update();
        
        

    }
    
    private void increaseLayerSpeed(int speed){
        
    }
    
    

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(backGround, layers[0].getPosition().width, layers[0].getPosition().height, this);
        g.drawImage(backGround, layers[1].getPosition().width, layers[1].getPosition().height, this);
        g.drawImage(layer1, layers[2].getPosition().width, layers[2].getPosition().height, this);
        g.drawImage(layer1, layers[3].getPosition().width, layers[3].getPosition().height, this);
        g.drawImage(player.getImage(), player.getCenterX() - 50, player.getCenterY() - 50, this);
        ArrayList projectiles = player.getProjectiles();
            for (int i = 0; i < projectiles.size(); i++) {
                Projectile p = (Projectile) projectiles.get(i);
                g.drawImage(p.getImage(), p.getX()-5, p.getY()-2, this);
            }
            ArrayList missiles = player.getMissiles();
            for (int i = 0; i < missiles.size(); i++) {
                Projectile p = (Projectile) missiles.get(i);
                g.drawImage(p.getImage(), p.getX()-5, p.getY()-2, this);
            }
            ArrayList enemyProjectiles = enemy.getProjectiles();
            for (int i = 0; i < enemyProjectiles.size(); i++) {
                Projectile p = (Projectile) enemyProjectiles.get(i);
                g.drawImage(p.getImage(), p.getX()-5, p.getY()-2, this);
            }
            g.drawImage(enemy.getImage(), enemy.getCenterX()-50, enemy.getCenterY()-50, this);
            
            g.setColor(Color.red);
            g.fillRect(10, 10, playerHits*2, 10);
            g.fillRect(enemy.getCenterX()-(enemyHits/4),enemy.getCenterY()-30,  enemyHits/2, 10);
         //tilemap.printTiles(tilemap, g);
         
         
         
    }    
    
    public String splitString(String in){
        String input;
        input = in.replaceAll("\"", "");        
        
        String parts[] = input.split(",");
        for(int i=0;i<parts.length;i++){
            System.out.println(parts[i]);
        }
        
        return input;
        
    }

    public void animate() {
    }

    public void setTilemap(Tilemap tilemap) {
        this.tilemap= tilemap;
    }

    private class MyDispatcher implements KeyEventDispatcher {

        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {



            if (e.getID() == KeyEvent.KEY_PRESSED) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_1:
                        player.setWeaponSelect(1);
                        lastShotFired =0;
                        needToCount = false;
                        break;
                        case KeyEvent.VK_2:
                        player.setWeaponSelect(2);
                        lastShotFired =0;
                        needToCount = false;
                        break;                    
                    case KeyEvent.VK_ESCAPE:
                        System.exit(0);
                    case KeyEvent.VK_P:
                        gm.pause();
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

                          //player.shoot();                        
                        spacePressed = true;


                        break;

                    case KeyEvent.VK_W:
                        player.moveUp();
                        upKeyPressed = true;
                        break;

                    case KeyEvent.VK_S:
                        downKeyPressed = true;
                        player.moveDown();
                        break;

                    case KeyEvent.VK_A:
                        leftKeyPressed = true;
                        player.moveLeft();
                        break;

                    case KeyEvent.VK_D:
                        rightKeyPressed = true;
                        player.moveRight();
                        break;

                }
            } else if (e.getID() == KeyEvent.KEY_RELEASED) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        upKeyPressed = false;
                        if (leftKeyPressed || rightKeyPressed) {
                            player.setySpeed(0);
                            return false;
                        }
                        player.stop();
                        break;

                    case KeyEvent.VK_DOWN:
                        downKeyPressed = false;
                        if (leftKeyPressed || rightKeyPressed) {
                            player.setySpeed(0);
                            return false;
                        }
                        player.stop();
                        break;

                    case KeyEvent.VK_LEFT:
                        leftKeyPressed = false;
                        
                        if (rightKeyPressed) {
                            player.moveRight();
                            return false;
                        }  else if (upKeyPressed || downKeyPressed) {
                            player.setxSpeed(0);
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
                            player.setxSpeed(0);
                            return false;
                        }
                        player.stop();
                        break;

                    case KeyEvent.VK_W:
                        upKeyPressed = false;
                        if (leftKeyPressed || rightKeyPressed) {
                            player.setySpeed(0);
                            return false;
                        }
                        player.stop();
                        break;

                    case KeyEvent.VK_S:
                        downKeyPressed = false;
                        if (leftKeyPressed || rightKeyPressed) {
                            player.setySpeed(0);
                            
                            return false;
                        }
                        player.stop();
                        
                        break;

                    case KeyEvent.VK_A:
                        leftKeyPressed = false;
                        
                        if (rightKeyPressed) {
                            player.moveRight();
                            return false;
                        } else if (upKeyPressed || downKeyPressed) {
                            player.setxSpeed(0);
                            return false;
                        }
                        player.stop();
                        break;

                    case KeyEvent.VK_D:
                        
                        rightKeyPressed = false;
                        if (leftKeyPressed) {
                            player.moveLeft();
                            return false;
                        } else if (upKeyPressed || downKeyPressed) {
                            player.setxSpeed(0);
                            return false;
                        }
                        player.stop();
                        break;


                    case KeyEvent.VK_SPACE:
                        spacePressed = false;
                        //player.stopGun();
                          //lastShotFired = 0;
                        break;

                }
            } else if (e.getID() == KeyEvent.KEY_TYPED) {
                //System.out.println("3test3");
            }
            return false;
        }
    }
}