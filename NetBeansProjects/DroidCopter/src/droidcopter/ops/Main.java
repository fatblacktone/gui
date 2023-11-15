/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package droidcopter.ops;

import com.angryandroid.framework.InteractableLayer;
import com.angryandroid.framework.Projectile;
import droidcopter.characters.EnemyTypeOne;
import droidcopter.characters.Player;
import droidcopter.gui.GameDisplay;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author Fatblack
 */
public class Main {
    
    private static final ArrayList<Projectile> enemyProjectiles = new ArrayList<>();

    public static void main(String[] args) {


        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Droid Copter");
                frame.setSize(1000, 600);
                frame.setUndecorated(true);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                GameDisplay display = new GameDisplay();
                display.setEnemyProjectiles(enemyProjectiles);
                frame.add(display);
                display.loadGameLayerImages();
                display.loadPlayerImages();
                display.loadEnemyImages();
                display.loadPowerUpImages();
                InteractableLayer[] midgrounds = display.loadInteractableImages();
                Player player = new Player();



                ArrayList<EnemyTypeOne> enemies = new ArrayList<>();
                EnemyTypeOne enemy;
                for(int i = 0;i<1;i++){
                    enemy = new EnemyTypeOne(player,enemyProjectiles);
                    enemy.setX(1100+(200*i));
                    enemies.add(enemy);                
                }





                display.setEnemyArray(enemies);
                display.setPlayer(player);
                Loop loop = new Loop(display);
                loop.setPlayer(player);
                loop.setEnemyProjectiles(enemyProjectiles);
                loop.setMidGrounds(midgrounds);
                loop.setEnemies(enemies);
                display.setLoop(loop);
                frame.setVisible(true);

                loop.runGameLoop();
            }
        });
    }
}
