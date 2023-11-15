/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package droidcopterapplet.ops;

import com.angryandroid.framework.CollisionDetection;
import com.angryandroid.framework.GameLoop;
import com.angryandroid.framework.InteractableLayer;
import com.angryandroid.framework.Projectile;
import droidcopterapplet.characters.EnemyTypeOne;
import droidcopterapplet.characters.Player;
import droidcopterapplet.characters.PowerUp;
import droidcopterapplet.gui.GameDisplay;
import droidcopterapplet.gui.graphics.Background;
import droidcopterapplet.sound.SoundPlayer;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JLabel;

/**
 *
 * @author Fatblack
 */
public class Loop extends GameLoop {

    private GameDisplay display;
    private Player player;
    private float lastRenderTime = System.nanoTime();
    private InteractableLayer[] midgrounds;
    private int hitcount = 0;
    private SoundPlayer sounds = new SoundPlayer();
    private Thread t;
    private boolean firstRun = true;
    private static final int GAME_INTRO = 1, GAME_RUNNING = 2, GAME_OVER = 3;
    private int gameState = 2;
    private Main main;
    private ArrayList<EnemyTypeOne> enemies;
    private ArrayList<Projectile> enemyProjectiles;
    private ArrayList<PowerUp> powerups;

    public Loop(GameDisplay display) {
        this.display = display;
        display.setSoundPlayer(sounds);
        powerups = display.getPowerups();
    }

    public ArrayList<Projectile> getEnemyProjectiles() {
        return enemyProjectiles;
    }

    public void setEnemyProjectiles(ArrayList<Projectile> enemyProjectiles) {
        this.enemyProjectiles = enemyProjectiles;
    }

    @Override
    public void updateGame() {
        
        display.setGameState(gameState);
        if (gameState == GAME_RUNNING) {
            for (EnemyTypeOne e : enemies) {
                e.update();
                e.setAngleToPlayer(angleInDegrees(e.getX(), e.getY(), player.getX(), player.getY()));
                //System.out.println(Math.toDegrees(e.getAngleToPlayer()));

            }
            if (firstRun) {
                playSound();
                firstRun = false;
            }
            if (CollisionDetection.DoesPolygonIntersectPolygon(player.getBoundingPolygon(), midgrounds[0].getBoundingPolygon())
                    || CollisionDetection.DoesPolygonIntersectPolygon(player.getBoundingPolygon(), midgrounds[1].getBoundingPolygon())) {
                player.setHealth(player.getHealth() - 10);

            }
            ArrayList<Projectile> projectiles;
            for (EnemyTypeOne e : enemies) {
                if (!e.isDead()) {
                    if (CollisionDetection.DoesPolygonIntersectPolygon(player.getBoundingPolygon(), e.getBoundingPolygon())) {
                        player.setHealth(player.getHealth() - 10);
                        e.setHealth(e.getHealth() - 10);

                    }
                    projectiles = player.getProjectiles();
                    for (Projectile p : projectiles) {
                        if (e.getBoundingPolygon().contains(p.getX(), p.getY())) {
                            e.setHealth(e.getHealth() - player.getDamage());

                            p.setVisible(false);
                        }
                    }
                    if (e.getHealth() <= 0) {
                        if (!e.isDead()) {
                            player.setScore(player.getScore() + 10);
                        }
                        Thread t = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                sounds.playBoom();
                                System.gc();
                            }
                        });
                        
                        t.start();
                        e.setDead(true);
                    }
                }




            }
            projectiles = player.getProjectiles();
            for (int i = 0; i < projectiles.size(); i++) {
                Projectile p = (Projectile) projectiles.get(i);
                if (p.isVisible() == true) {
                    p.update(15);
                } else {
                    projectiles.remove(i);
                }
            }
            for (int i = 0; i < enemyProjectiles.size(); i++) {
                Projectile p = enemyProjectiles.get(i);
                if (player.getBoundingPolygon().contains(p.getX(), p.getY())) {
                    player.setHealth(player.getHealth() - 5);
                    p.setVisible(false);
                    enemyProjectiles.remove(p);
                } else if (p.isVisible()) {
                    p.update(2);
                } else if (!p.isVisible()) {
                    enemyProjectiles.remove(p);
                }
            }

            for (int i = 0; i < enemies.size(); i++) {
                EnemyTypeOne enemy = (EnemyTypeOne) enemies.get(i);
                if (!enemy.isVisible()) {
                    if (enemy.isHasPowerup()) {
                        PowerUp p = new PowerUp(enemy.getX(), enemy.getY());
                        powerups.add(p);
                    }
                    enemies.remove(enemy);
                }
            }

            for (int i = 0; i < powerups.size(); i++) {
                PowerUp p = powerups.get(i);
                if (CollisionDetection.DoesPolygonIntersectPolygon(p.getBoundingPolygon(), player.getBoundingPolygon())) {
                    switch (p.getType()) {
                        case PowerUp.DAMAGE_INCREASE:
                            player.setDamage(player.getDamage() + 5);
                            break;
                        case PowerUp.DEATH:
                            player.setHealth(0);
                            break;
                        case PowerUp.HEALTH:
                            player.setHealth(player.getHealth() + 50);
                            break;
                        case PowerUp.KILL_ALL:
                            for (EnemyTypeOne e : enemies) {
                                if (!e.isDead()) {
                                    Thread t = new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            sounds.playBoom();
                                        }
                                    });

                                    t.start();

                                }
                                e.setDead(true);

                            }
                            enemyProjectiles.clear();
                            break;
                        case PowerUp.EXTRA_LIFE:
                            player.setLives(player.getLives() + 1);
                            break;
                        case PowerUp.SHIELD:
                            player.setShieldStrength(100);
                            break;
                    }
                    powerups.remove(p);
                }
            }
            if (player.getHealth() <= 0) {
                player.setUpdatesImmuneFor(100);
                player.setLives(player.getLives() - 1);
                player.setHealth(100);
                player.setX(200);
                player.setY(200);
                if (player.getLives() <= 0) {
                    super.pause();
                    gameState = 3;
                    display.setGameState(gameState);
                    display.showGameOver();
                    stopSound();
                }
            }

            if (enemies.size() < 5) {
                EnemyTypeOne enemy;
                Random rand = new Random();
                switch (rand.nextInt(5) + 1) {
                    case 1:
                        enemy = new EnemyTypeOne(player, enemyProjectiles);
                        enemies.add(enemy);
                        break;
                    case 2:
                        enemy = new EnemyTypeOne(player, enemyProjectiles);
                        enemies.add(enemy);
                        enemy = new EnemyTypeOne(player, enemyProjectiles);
                        enemies.add(enemy);
                        enemy.setX(1100);
                        break;
                    case 3:
                        enemy = new EnemyTypeOne(player, enemyProjectiles);
                        enemies.add(enemy);
                        enemy = new EnemyTypeOne(player, enemyProjectiles);
                        enemies.add(enemy);
                        enemy.setX(1050);
                        enemy = new EnemyTypeOne(player, enemyProjectiles);
                        enemies.add(enemy);
                        enemy.setX(1100);
                        break;
                    case 4:
                        for (int i = 0; i < 5; i++) {
                            enemy = new EnemyTypeOne(player, enemyProjectiles);
                            enemy.setBehaviour(2);
                            enemy.setX(1100 + (i * 50));
                            enemies.add(enemy);

                        }
                        break;
                    case 5:
                        for (int i = 0; i < 5; i++) {
                            enemy = new EnemyTypeOne(player, enemyProjectiles);
                            enemy.setBehaviour(3);
                            enemy.setX(1100 + (i * 50));
                            enemies.add(enemy);

                        }

                }
            }

            for (int i = 0; i < powerups.size(); i++) {
                PowerUp p = powerups.get(i);
                p.update();
            }
            if(!display.isSpacePressed()){
                sounds.stopGun();
            }
            player.update();

        } else if (gameState == GAME_OVER) {
        }

    }
    
    public static double angleInDegrees(double centreX, double centreY, double pointX, double pointY) {
        return (Math.atan2(pointX - centreX, centreY - pointY));//-1.57)*0.95;// * 180.0F / Math.PI;
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    @Override
    public void drawGame(float currentTimeInNanoSeconds) {
        //System.err.println("dsfsj");
        if (gameState == GAME_RUNNING) {
            if (!firstRun) {
                display.update();
                display.setFPS(getFPS());
                super.drawGame(currentTimeInNanoSeconds);
                display.repaint(1);
                lastRenderTime = System.nanoTime();
            }
        }
    }
    
    @Override
    public void pause(){
        super.pause();
        if(isPaused()){
            sounds.stopChopper();
        }
        if(!isPaused()){
            sounds.playChopper();
        }
        
    }

    public void setEnemies(ArrayList<EnemyTypeOne> enemies) {
        this.enemies = enemies;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setMidGrounds(InteractableLayer[] midgrounds) {
        this.midgrounds = midgrounds;
    }

    @Override
    public void playSound() {
        t = new Thread(sounds);
        t.start();
    }

    @Override
    public void stopSound() {
        sounds.stopChopper();
    }

    @Override
    public void pauseSound() {
    }
}
