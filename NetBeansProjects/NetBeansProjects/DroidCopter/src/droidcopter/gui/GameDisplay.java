/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package droidcopter.gui;

import com.angryandroid.framework.Animation;
import com.angryandroid.framework.DisplayPanel;
import com.angryandroid.framework.GameCharacter;
import com.angryandroid.framework.InteractableLayer;
import com.angryandroid.framework.Projectile;
import droidcopter.characters.EnemyTypeOne;
import droidcopter.characters.Player;
import droidcopter.characters.PowerUp;
import droidcopter.gui.graphics.Background;
import droidcopter.ops.Loop;
import droidcopter.sound.SoundPlayer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import java.awt.geom.Ellipse2D;

/**
 *
 * @author Fatblack
 */
public class GameDisplay extends JLayeredPane implements DisplayPanel ,ActionListener, MouseMotionListener,MouseListener{

    private Background backgroundOne, backgroundTwo, midgroundOne, midgroundTwo;
    private long lastUpdateTime;
    private Image backgroundImage, midgroundImage,life,bullet;
    private Animation playerStill, playerForwards, playerBackwards,enemyAnim,explosion;
    private Player player;
    private boolean rightKeyPressed,
            leftKeyPressed,
            downKeyPressed,
            upKeyPressed,
            spacePressed,
            firing;
    private static final int GAME_INTRO = 1, GAME_RUNNING = 2, GAME_OVER = 3;
    private int gameState = 2;
    private JButton restart;
    private JLayeredPane gameOverPanel;
    private Loop loop;
    private int fps;
    private ArrayList<EnemyTypeOne> enemies;
    private int lastShotFired;
    private SoundPlayer sounds;
    private ArrayList<Projectile> enemyProjectiles;
    private int pointerX;
    private int pointerY;
    private double angle;
    private ArrayList<PowerUp> powerups;
    private Image damage,death,health,killAll,lifeUp;
    private Image shield;
    private Image shieldUp;
    

    public GameDisplay() {
        enemyProjectiles = new ArrayList<>();
        powerups = new ArrayList<>();
        lastUpdateTime = System.currentTimeMillis();
        setFocusable(true);
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new MyDispatcher());
        enemies = new ArrayList<>();
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
    }

    public ArrayList<PowerUp> getPowerups() {
        return powerups;
    }

    public void setPowerups(ArrayList<PowerUp> powerups) {
        this.powerups = powerups;
    }
    
    

    public ArrayList<Projectile> getEnemyProjectiles() {
        return enemyProjectiles;
    }

    public void setEnemyProjectiles(ArrayList<Projectile> enemyProjectiles) {
        this.enemyProjectiles = enemyProjectiles;
    }
    
    public void loadPowerUpImages(){
        try {
            damage =  ImageIO.read(new File("./images/powerups/damage.png"));
            death =  ImageIO.read(new File("./images/powerups/death.png"));
            health =  ImageIO.read(new File("./images/powerups/health.png"));
            killAll =  ImageIO.read(new File("./images/powerups/kill_all.png"));
            lifeUp =  ImageIO.read(new File("./images/powerups/life.png"));            
            shieldUp = ImageIO.read(new File("./images/powerups/shield.png"));
        }
        catch (IOException ex) {
            Logger.getLogger(GameDisplay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    ///////////////////////////////////////////////////////////////////////////
    /////////////////////     Inherited methods   /////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    @Override
    public void loadGameLayerImages() {
        try {
            backgroundImage = ImageIO.read(new File("./images/Background.png"));
            backgroundOne = new Background(0, 0, 2000, backgroundImage);
            backgroundTwo = new Background(2000, 0, 2000, backgroundImage);
        } catch (IOException ex) {
            Logger.getLogger(GameDisplay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void loadPlayerImages() {
        try {
            life = ImageIO.read(new File("./images/player/still/life.png"));
            bullet = ImageIO.read(new File("./images/player/projectiles/bullet.png"));
            shield = shield = ImageIO.read(new File("./images/player/still/shield.png"));
        } catch (IOException ex) {
            Logger.getLogger(GameDisplay.class.getName()).log(Level.SEVERE, null, ex);
        }
        playerStill = new Animation();
        int fileNumber = 1;
        File file = new File("./images/player/still/" + (fileNumber++) + ".png");
        while (file.exists()) {
            try {
                playerStill.addFrame(ImageIO.read(file), 60);
            } catch (IOException ex) {
                Logger.getLogger(GameDisplay.class.getName()).log(Level.SEVERE, null, ex);
            }
            file = new File("./images/player/still/" + (fileNumber++) + ".png");
        }
        playerForwards = new Animation();
        fileNumber = 1;
        file = new File("./images/player/forwards/" + (fileNumber++) + ".png");
        while (file.exists()) {
            try {
                playerForwards.addFrame(ImageIO.read(file), 60);
            } catch (IOException ex) {
                Logger.getLogger(GameDisplay.class.getName()).log(Level.SEVERE, null, ex);
            }
            file = new File("./images/player/forwards/" + (fileNumber++) + ".png");
        }
        playerBackwards = new Animation();
        fileNumber = 1;
        file = new File("./images/player/backwards/" + (fileNumber++) + ".png");
        while (file.exists()) {
            try {
                playerBackwards.addFrame(ImageIO.read(file), 60);
            } catch (IOException ex) {
                Logger.getLogger(GameDisplay.class.getName()).log(Level.SEVERE, null, ex);
            }
            file = new File("./images/player/backwards/" + (fileNumber++) + ".png");
        }
    }

    @Override
    public void loadEnemyImages() {
        enemyAnim = new Animation();
        int fileNumber = 1;
        File file = new File("./images/enemies/enemyTypeOne/" + (fileNumber++) + ".png");
        while (file.exists()) {
            try {
                enemyAnim.addFrame(ImageIO.read(file), 60);
            } catch (IOException ex) {
                Logger.getLogger(GameDisplay.class.getName()).log(Level.SEVERE, null, ex);
            }
            file = new File("./images/enemies/enemyTypeOne/" + (fileNumber++) + ".png");
        }
    }

    @Override
    public InteractableLayer[] loadInteractableImages() {
        try {
            midgroundImage = ImageIO.read(new File("./images/buildings.png"));
            midgroundOne = new Background(0, 0, 2000, midgroundImage);
            midgroundTwo = new Background(2000, 0, 2000, midgroundImage);
            midgroundOne.setSpeedX(-2);
            midgroundTwo.setSpeedX(-2);
        } catch (IOException ex) {
            Logger.getLogger(GameDisplay.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new InteractableLayer[]{midgroundOne, midgroundTwo};
    }
    
    public void setSoundPlayer(SoundPlayer sounds){
        this.sounds = sounds;
    }
    

    @Override
    public void setPlayer(GameCharacter player) {
        this.player = (Player) player;
    }

    @Override
    public void setEnemies(ArrayList<GameCharacter> enemies) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public void showGameOver(){
        gameOverPanel = new JLayeredPane();
        JLabel label = new JLabel(new ImageIcon("images/gameOver.png"));
        label.setBounds(0, 0,1000, 600);
        gameOverPanel.add(label,0);
        gameOverPanel.setOpaque(true);
        gameOverPanel.setBackground(Color.yellow);
        gameOverPanel.setBounds(0, 0, 1000, 600);
        this.add(gameOverPanel);
        restart = new JButton("Restart");
        restart.setBounds(400, 500, 200, 75);
        restart.addActionListener(this);
        gameOverPanel.add(restart,1);
        enemies.clear();
        enemyProjectiles.clear();
        player.getProjectiles().clear();
        powerups.clear();
        player.setScore(0);
    }

    @Override
    public void paintComponent(Graphics g) {
        
            super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (gameState == GAME_RUNNING) {
            g.drawImage(backgroundImage, (int) (backgroundOne.getxCoord()), 0, this);
            g.drawImage(backgroundImage, backgroundTwo.getxCoord(), 0, this);
            g.drawImage(midgroundImage, (int) (midgroundOne.getxCoord()), 0, this);
            g.drawImage(midgroundImage, midgroundTwo.getxCoord(), 0, this);
            // g.fillRect( player.getCurrentXCoord(), player.getCurrentYCoord(), 100, 100);

            g.setColor(Color.RED);

            if (leftKeyPressed) {
                g.drawImage(playerBackwards.getImage(), player.getX(),
                        player.getY(), this);
                //g.drawPolygon(player.getBoundingPolygon());


            } else if (rightKeyPressed) {
                g.drawImage(playerForwards.getImage(), player.getX(),
                        player.getY(), this);
                //g.drawPolygon(player.getBoundingPolygon());
            } else {
                g.drawImage(playerStill.getImage(), player.getX(),
                        player.getY(), this);
                // g.drawPolygon(player.getBoundingPolygon());
            }
            if (player.getShieldStrength() > 0) {
                g.drawImage(shield, player.getX(), player.getY(), this);
            }
            //g.drawLine(player. getX()+75, player.getY()+45, pointerX, pointerY);
            g.fillRect(20, 20, player.getHealth() * 2, 20);
            
            //g.fillPolygon(player.getBoundingPolygon());
            for(int i=0;i<enemies.size();i++){
               // g.fillPolygon(enemies.get(i).getBoundingPolygon());
            }
            
            int temp = 400 - player.getDamage() * 4;

            g.fillRect(20, 100+temp, 20, player.getDamage() * 4);
            g.setColor(Color.black);
            g.drawRect(20, 100, 20, 400);

            g.setColor(Color.blue);
            g.fillRect(20, 20, player.getShieldStrength() * 2, 20);

            for (int i = 0; i < player.getLives(); i++) {
                g.drawImage(life, 30 + (i * 30), 42, this);
            }
            if (enemyProjectiles != null) {
                for (int in = 0; in < enemyProjectiles.size(); in++) {
                    Projectile p = enemyProjectiles.get(in);
                    g.fillRect(p.getX(), p.getY(), 5, 5);
                }
            }
            for (int i = 0; i < powerups.size(); i++) {
                PowerUp p = powerups.get(i);
                Image image = null;
                switch (p.getType()) {
                    case PowerUp.DAMAGE_INCREASE:
                        image = damage;
                        break;
                    case PowerUp.DEATH:
                        image = death;
                        break;
                    case PowerUp.HEALTH:
                        image = health;
                        break;
                    case PowerUp.KILL_ALL:
                        image = killAll;
                        break;
                    case PowerUp.EXTRA_LIFE:
                        image = lifeUp;
                        break;
                    case PowerUp.SHIELD:
                        image = shieldUp;
                        break;
                }
                g.drawImage(image, p.getX(), p.getY(), this);
                //g.fillPolygon(p.getBoundingPolygon());
            }
            for (int i = 0; i < enemies.size(); i++) {
                EnemyTypeOne e = enemies.get(i);
                if (!e.isDead()) {
                    g.drawImage(enemyAnim.getImage(), e.getX(), e.getY(), this);
                    ArrayList<Projectile> projectiles = e.getProjectiles();

                } else {
                    g.drawImage(e.getExplosion(), e.getX() - 40, e.getY() - 30, this);
                }
                //g.fillRect(e.getCurrentXCoord()+15, e.getCurrentYCoord()+80, e.getHealth()/2, 5);
                //g.fillPolygon(e.getBoundingPolygon());
            }
            ArrayList<Projectile> projectiles = player.getProjectiles();
            for (int i = 0; i < projectiles.size(); i++) {
                Projectile p = (Projectile) projectiles.get(i);
                g2.rotate(p.getAngle(), p.getX(), p.getY());
                g2.drawImage(bullet, p.getX(), p.getY(), this);//Rect(p.getX(), p.getY(), 10, 3);
                g2.rotate(-p.getAngle(), p.getX(), p.getY());

            }
            //g.drawPolygon(midgroundOne.getBoundingPolygon());
            //g.drawPolygon(midgroundTwo.getBoundingPolygon());
            g.setColor(Color.BLACK);

            // g.drawString("Mouse at: " + pointerX +" : "+pointerY+" ", 868, 20);
            // g.drawString("FPS: " + fps, 930, 40);
            // g.drawString("Angle: "+ angle,800,60);
            // g.drawString("in Degrees: "+ Math.toDegrees(angle),800,80);

           
        }else if(gameState==GAME_OVER){
            
        }
        g.drawString("SCORE: "+player.getScore(), 450, 20);
        

    }
    
    public void setGameState(int gameState){
        this.gameState = gameState;
    }

    @Override
    public void update() {
        playerStill.update(System.currentTimeMillis() - lastUpdateTime, false);
        playerForwards.update(System.currentTimeMillis() - lastUpdateTime, false);
        playerBackwards.update(System.currentTimeMillis() - lastUpdateTime, false);
        backgroundOne.update();
        backgroundTwo.update();
        midgroundOne.update();
        midgroundTwo.update();
        
        if (spacePressed) {
                if (lastShotFired == 0) {
                    player.fire(angle);
                    lastShotFired++;
                } else {
                    lastShotFired++;
                    if (lastShotFired >= 5) {
                        lastShotFired = 0;
                    }
                }
            }
        enemyAnim.update(System.currentTimeMillis()-lastUpdateTime, false);
        
        lastUpdateTime = System.currentTimeMillis();
    }
    
    

    /**
     *
     */
    public synchronized void drawOffScreen() {
    }
    
    public void setEnemyArray(ArrayList<EnemyTypeOne> enemies){
        this.enemies = enemies;
    }

    public void setFPS(int fps) {
        this.fps = fps;
    }

    @Override
    public GameCharacter getPlayer() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==restart){
            backgroundOne.setxCoord(0);
            backgroundTwo.setxCoord(2000);
            midgroundOne.setxCoord(0);
            midgroundTwo.setxCoord(2000);
            player.setHealth(100);
            player.setLives(3);
            player.setX(200);
            player.setY(200);
            loop.pause();
            loop.playSound();
            gameState = 2;
            loop.setGameState(gameState);
            gameOverPanel.setVisible(false);
        }
    }
    
    public void setLoop(Loop loop){
        this.loop = loop;
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        pointerX = player.getX()+175;//me.getX();
        pointerY = player.getY();
        angle = angleInDegrees(player.getX()+75, player.getY()+45, player.getX()+175, player.getY()+45);
        
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        pointerX = player.getX()+175;
        pointerY = player.getY();
        angle = angleInDegrees(player.getX()+75, player.getY()+45, player.getX()+175, player.getY()+45);
        
        

    }

    @Override
    public void mouseClicked(MouseEvent me) {         
        
    }
    
    public static double angleInDegrees(double centreX, double centreY, double pointX, double pointY) {
        return (Math.atan2(pointX - centreX, centreY - pointY));//-1.57)*0.95;// * 180.0F / Math.PI;
    }

    @Override
    public void mousePressed(MouseEvent me) {
        spacePressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        spacePressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
        
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
                        loop.pause();
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
                        Thread t = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    sounds.playGun();
                                }
                            });
                            if (!spacePressed) {
                                t.start();
                            }
                                       
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
                        } else if (upKeyPressed || downKeyPressed) {
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
                        sounds.stopGun();
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
