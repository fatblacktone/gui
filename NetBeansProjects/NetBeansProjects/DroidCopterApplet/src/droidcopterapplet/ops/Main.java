/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package droidcopterapplet.ops;

import com.angryandroid.framework.InteractableLayer;
import com.angryandroid.framework.Projectile;
import droidcopterapplet.characters.EnemyTypeOne;
import droidcopterapplet.characters.Player;
import droidcopterapplet.gui.GameDisplay;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Fatblack
 */
public class Main extends JApplet implements ActionListener{

    private static final ArrayList<Projectile> enemyProjectiles = new ArrayList<>();
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static Dimension centerOfScreen = new Dimension(screenSize.width / 2, screenSize.height / 2);
    private JLayeredPane startGamePanel = new JLayeredPane();
    private Loop loop;
    private GameDisplay display = new GameDisplay();
    private Player player;
    private InteractableLayer[] midgrounds;
    private ArrayList<EnemyTypeOne> enemies = new ArrayList<>();
    private JButton start;

    @Override
    public void init() {
        player = new Player();
        enemies = new ArrayList<>();
        EnemyTypeOne enemy;
        for (int i = 0; i < 1; i++) {
            enemy = new EnemyTypeOne(player, enemyProjectiles);
            enemy.setX(1100 + (200 * i));
            enemies.add(enemy);
        }
        this.setVisible(true);
        setupLoop();
        showStartScreen();
    }
    
    public void runLoop(){
        loop.runGameLoop();
    }
    
    public void setupDisplay(){
        display = new GameDisplay();
        display.setMain(this);
        display.setBase(getCodeBase());
        display.setEnemyProjectiles(enemyProjectiles);
        display.loadGameLayerImages();
        display.loadPlayerImages();
        display.loadEnemyImages();
        display.loadPowerUpImages();
        midgrounds = display.loadInteractableImages();
        display.setEnemyArray(enemies);
        display.setPlayer(player);
        display.setLoop(loop);
        
    }
    
    public void setupLoop(){
       loop = new Loop(display);
        loop.setPlayer(player);
        loop.setEnemyProjectiles(enemyProjectiles);
        loop.setMidGrounds(midgrounds);
        loop.setEnemies(enemies); 
    }
    
    public void showStartScreen(){
        if(display!=null){
            display.setVisible(false);
        }
        start = new JButton(new ImageIcon(getImage(getCodeBase(),"images/startup/start_button.png")));
        start.setBounds(100, 400, 300, 75);
        start.addActionListener(this);
        JLabel label = new JLabel(new ImageIcon(getImage(getCodeBase(),"images/startPanel.gif")));
        label.setBounds(0, 0, 1000, 600);
        startGamePanel.add(label);
        startGamePanel.add(start,3);
        this.add(startGamePanel);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==start){
            startGamePanel.setVisible(false);
            setupDisplay();
            this.add(display);
            runLoop();
        }
    }
    
    

   
}
