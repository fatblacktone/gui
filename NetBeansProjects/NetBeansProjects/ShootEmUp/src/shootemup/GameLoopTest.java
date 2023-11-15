package shootemup;


import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.*;
import shootemup.famework.Animation;
import java.util.Timer;

public class GameLoopTest extends JFrame implements ActionListener, KeyListener {

    private GamePanel gamePanel = new GamePanel(this);
    private JButton startButton = new JButton("Start");
    private JButton quitButton = new JButton("Quit");
    private JButton pauseButton = new JButton("Pause");
    private boolean running = false;
    private boolean paused = false;
    private int fps = 60;
    private int frameCount = 0;
    
    
    private static Background bg1, bg2,cg1,cg2;
        private static Foreground fg1, fg2;

    public GameLoopTest() {

        super("Fixed Timestep Game Loop Test");
        addKeyListener(this);
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(1, 2));
        //p.add(startButton);
        //p.add(pauseButton);
        //p.add(quitButton);
        cp.add(gamePanel, BorderLayout.CENTER);
        cp.add(p, BorderLayout.SOUTH);
        setSize(800, 530);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        startButton.addActionListener(this);
        quitButton.addActionListener(this);
        pauseButton.addActionListener(this);
        runGameLoop();
        running = true;

    }

    public static void main(String[] args) {
        GameLoopTest glt = new GameLoopTest();
        glt.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        Object s = e.getSource();
        if (s == startButton) {
            running = !running;
            if (running) {
                startButton.setText("Stop");
                runGameLoop();
            } else {
                startButton.setText("Start");
            }
        } else if (s == pauseButton) {
            paused = !paused;
            if (paused) {
                pauseButton.setText("Unpause");
            } else {
                pauseButton.setText("Pause");
            }
        } else if (s == quitButton) {
            System.exit(0);
        }
    }

    //Starts a new thread and runs the game loop in it.
    public void runGameLoop() {
        Thread loop = new Thread() {
            public void run() {
                gameLoop();
            }
        };
        loop.start();
    }

    //Only run this in another Thread!
    private void gameLoop() {
        //This value would probably be stored elsewhere.
        final double GAME_HERTZ = 30.0;
        //Calculate how many ns each frame should take for our target game hertz.
        final double TIME_BETWEEN_UPDATES = 1000000000 / GAME_HERTZ;
        //At the very most we will update the game this many times before a new render.
        //If you're worried about visual hitches more than perfect timing, set this to 1.
        final int MAX_UPDATES_BEFORE_RENDER = 5;
        //We will need the last update time.
        double lastUpdateTime = System.nanoTime();
        //Store the last time we rendered.
        double lastRenderTime = System.nanoTime();

        //If we are able to get as high as this FPS, don't render again.
        final double TARGET_FPS = 60;
        final double TARGET_TIME_BETWEEN_RENDERS = 1000000000 / TARGET_FPS;

        //Simple way of finding FPS.
        int lastSecondTime = (int) (lastUpdateTime / 1000000000);

        while (running) {
            double now = System.nanoTime();
            int updateCount = 0;

            if (!paused) {
                //Do as many game updates as we need to, potentially playing catchup.
                while (now - lastUpdateTime > TIME_BETWEEN_UPDATES && updateCount < MAX_UPDATES_BEFORE_RENDER) {
                    updateGame();
                    lastUpdateTime += TIME_BETWEEN_UPDATES;
                    updateCount++;
                }

                //If for some reason an update takes forever, we don't want to do an insane number of catchups.
                //If you were doing some sort of game that needed to keep EXACT time, you would get rid of this.
                if (now - lastUpdateTime > TIME_BETWEEN_UPDATES) {
                    lastUpdateTime = now - TIME_BETWEEN_UPDATES;
                }

                //Render. To do so, we need to calculate interpolation for a smooth render.
                float interpolation = Math.min(1.0f, (float) ((now - lastUpdateTime) / TIME_BETWEEN_UPDATES));
                drawGame(interpolation);
                lastRenderTime = now;

                //Update the frames we got.
                int thisSecond = (int) (lastUpdateTime / 1000000000);
                if (thisSecond > lastSecondTime) {
                    //System.out.println("NEW SECOND " + thisSecond + " " + frameCount);
                    fps = frameCount;
                    frameCount = 0;
                    lastSecondTime = thisSecond;
                }

                //Yield until it has been at least the target time between renders. This saves the CPU from hogging.
                while (now - lastRenderTime < TARGET_TIME_BETWEEN_RENDERS && now - lastUpdateTime < TIME_BETWEEN_UPDATES) {
                    Thread.yield();

                    //This stops the app from consuming all your CPU. It makes this slightly less accurate, but is worth it.
                    //You can remove this line and it will still work (better), your CPU just climbs on certain OSes.
                    //FYI on some OS's this can cause pretty bad stuttering. Scroll down and have a look at different peoples' solutions to this.
                    try {
                        Thread.sleep(1);
                    } catch (Exception e) {
                    }

                    now = System.nanoTime();
                }
            }
        }
    }

    private void updateGame() {
        gamePanel.update();
    }

    private void drawGame(float interpolation) {
        gamePanel.setInterpolation(interpolation);
        gamePanel.repaint();
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        System.out.println("hdsakjhk");
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }
    
    public void setBg1(Background bg1){
        
    }

   

    public void setBg2(Background bg2) {
        this.bg2 = bg2;
    }

   

    public void setCg1(Background cg1) {
        this.cg1 = cg1;
    }

    

    public void setCg2(Background cg2) {
        this.cg2 = cg2;
    }

   

    public void setFg1(Foreground fg1) {
        this.fg1 = fg1;
    }

    

    public void setFg2(Foreground fg2) {
        this.fg2 = fg2;
    }
    
    public static Background getBg1() {
        return bg1;
    }

    public static Background getBg2() {
        return bg2;
    }

    public static Foreground getFg1() {
        return fg1;
    }

    public static Foreground getFg2() {
        return fg2;
    }
    
    public static Background getCg1() {
        return cg1;
    }

    public static Background getCg2() {
        return cg2;
    }

    private class GamePanel extends JPanel implements KeyListener {

        float interpolation;
        int lastDrawX, lastDrawY;
        private boolean rightKeyPressed,
                leftKeyPressed,
                downKeyPressed,
                upKeyPressed,
                spacePressed,
                firing;
        private int lastShotFired = 0;
        private Image enemyNormal,image, character, char1, char2, char3,char4 , enemy, foreground, character_normal, character_forwards, character_backwards, buildings, clouds;
        private Graphics second;
        private Ship ship;
        private URL base;
        private Background bg1, bg2,cg1,cg2;
        private Foreground fg1, fg2;
        private boolean inWindow = true;
        private int mouseX, mouseY;
        private Plane plane;
        private Animation anim,expanim;
        private SoundPlayer player = new SoundPlayer();
        private boolean enemyVisible;
        private int enFrame = 0;
        private ArrayList<Plane> enemies = new ArrayList<>();
        private int enemyCreationCounter = 50,currentUpdate = 0,score = 0,lives = 3;
        private Image exp1,exp2,exp3,exp4,exp5,exp6,exp7,exp8,exp9,exp10,exp11;
        private GameLoopTest gm;

        public GamePanel(GameLoopTest gm) {
            this.gm = gm;
            setFocusable(true);
            KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
            manager.addKeyEventDispatcher(new MyDispatcher());
            //parent.addKeyListener(this);            
            try {
                character_normal = ImageIO.read(new File("./images/ship2.png"));
                char1 = ImageIO.read(new File("./images/ship1.png"));
                char2 = ImageIO.read(new File("./images/ship2.png"));
                char3 = ImageIO.read(new File("./images/ship3.png"));
                char4 = ImageIO.read(new File("./images/ship4.png"));

                character_backwards = ImageIO.read(new File("./images/ship_backwards.png"));
                character_forwards = ImageIO.read(new File("./images/ship_forwards.png"));
                buildings = ImageIO.read(new File("./images/buildings.png"));                
                clouds = ImageIO.read(new File("./images/clouds.png"));
                foreground = ImageIO.read(new File("./images/foreground.png"));
                enemyNormal = ImageIO.read(new File("./images/plane.png"));
                
                exp1 = ImageIO.read(new File("./images/planeExplosion1.png"));
                exp2 = ImageIO.read(new File("./images/planeExplosion2.png"));
                exp3 = ImageIO.read(new File("./images/planeExplosion3.png"));
                exp4 = ImageIO.read(new File("./images/planeExplosion4.png"));
                exp5 = ImageIO.read(new File("./images/planeExplosion5.png"));
                exp6 = ImageIO.read(new File("./images/planeExplosion6.png"));
                exp7 = ImageIO.read(new File("./images/planeExplosion7.png"));
                exp8 = ImageIO.read(new File("./images/planeExplosion8.png"));
                exp9 = ImageIO.read(new File("./images/planeExplosion9.png"));
                exp10 = ImageIO.read(new File("./images/planeExplosion10.png"));
                exp11 = ImageIO.read(new File("./images/planeExplosion11.png"));
                
                image = ImageIO.read(new File("./images/backdrop.png"));
                
            } catch (Exception e) {
                setBackground(Color.yellow);
            }
            bg1 = new Background(0, 0);
            bg2 = new Background(2160, 0);
            cg1 = new Background(0,0);
            cg2 = new Background(2160, 0);
            fg1 = new Foreground(0, 0);
            fg2 = new Foreground(2160, 0);
            
            plane = new Plane(2160, 200,bg1);
            ship = new Ship(bg1, bg2,cg1,cg2, fg1, fg2);
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    player.playChopper();
                }
            });
            t.start();
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    player.playMusic();
                }
            });
            t1.start();
            anim = new Animation();
            
            anim.addFrame(char1, 20);
            anim.addFrame(char2, 20);
            anim.addFrame(char3, 20);
            anim.addFrame(char4, 20);
            enemyVisible = true;
            character = character_normal;
            enemy = enemyNormal;
            expanim = new Animation();
            expanim.addFrame(exp1, 30);
            expanim.addFrame(exp2, 30);
            expanim.addFrame(exp3, 30);
            expanim.addFrame(exp4, 30);
            expanim.addFrame(exp5, 30);
            expanim.addFrame(exp6, 30);
            expanim.addFrame(exp7, 30);
            expanim.addFrame(exp8, 30);
            expanim.addFrame(exp9, 30);
            expanim.addFrame(exp10, 30);
            expanim.addFrame(exp11, 30);
            
        }

        public void setInterpolation(float interp) {
            interpolation = interp;
        }
        
        
        public void createEnemy(){
            Random rand = new Random();            
            Plane p = new Plane(850,rand.nextInt(400)+30,bg1);
            p.setImage(enemy);
            Animation temp = new Animation();
            p.setAnim(temp);
            temp.addFrame(exp1, 30);
            temp.addFrame(exp2, 30);
            temp.addFrame(exp3, 30);
            temp.addFrame(exp4, 30);
            temp.addFrame(exp5, 30);
            temp.addFrame(exp6, 30);
            temp.addFrame(exp7, 30);
            temp.addFrame(exp8, 30);
            temp.addFrame(exp9, 30);
            temp.addFrame(exp10, 30);
            temp.addFrame(exp11, 30);
            
            enemies.add(p);
        }
        
        

        public void update() {
            
            ship.update();
            plane.update();
            if (ship.getxSpeed() > 0) {
                character = character_forwards;
            } else if (ship.getxSpeed() < 0) {
                character = character_backwards;
            } else {
                character = anim.getImage();
            }
            if(!plane.isVisible()){
                enemy = expanim.getImage();
            }else{
                expanim.resetAnim();
                enemy = enemyNormal;
            }
            for(Plane p:enemies){
                if (!p.isVisible()) {
                    p.setImage(p.getAnimImage());
                    p.updateAnim();
                } else {
                    p.setImage(enemy);
                }
            }
            ArrayList projectiles = ship.getProjectiles();
            for (int i = 0; i < projectiles.size(); i++) {
                Projectile p = (Projectile) projectiles.get(i);
                if (p.isVisible() == true) {
                    p.update();
                } else {
                    projectiles.remove(i);
                }
            }
            Plane toRemove = null;
            for (Plane p : enemies) {
                p.update();
                if (p.getCentreX() < 0) {
                    toRemove = p;
                }
                ArrayList enemyProjectiles = p.getProjectiles();
                for (int i = 0; i < enemyProjectiles.size(); i++) {
                    Projectile proj = (Projectile) enemyProjectiles.get(i);
                    if (proj.isVisible() == true) {
                        proj.update();
                        if(closeToPlayer(proj.getX(), proj.getY())){
                            enemyProjectiles.remove(i);                        
                        }
                        
                    } else {
                        
                        
                    }
                }
            }
            enemies.remove(toRemove);
            bg1.update();
            bg2.update();
            fg1.update();
            fg2.update();
            cg1.update();
            cg2.update();
            //System.out.println("Current Update:"+currentUpdate);
            if(currentUpdate>enemyCreationCounter){
                Random rand = new Random();
                if(rand.nextInt(10)>5){
                    createEnemy();
                    currentUpdate=0;
                }
                
            }
            currentUpdate++;

            if (spacePressed) {
                
                if (lastShotFired == 0) {
                    ship.fire();
                    lastShotFired++;
                } else {
                    lastShotFired++;
                    if (lastShotFired >= 4) {
                        lastShotFired = 0;
                    }
                }
            }
            for (int i = 0; i < projectiles.size(); i++) {
                Projectile p = (Projectile) projectiles.get(i);
                if (p.isVisible() == true) {
                    if(closeTo(p.getX(), p.getY())){
                        p.setVisible(false);
                    }
                    //p.update();
                } else {
                    //projectiles.remove(i);
                }
            }
            
            
            animate();
            repaint();
            //Timer timer = new Timer();
            //timer.schedule(new LoopyStuff(), 0, 1000 / 60);
            //timer.start();
        }
        
        
        
        public boolean closeTo(int x, int y) {
            boolean isClose = false;
            for(Plane p:enemies){
                if ((x - p.getCentreX() < 10) && (x - p.getCentreX() > -10)) {
                    //System.out.println("x axis found");
                    if ((y - p.getCentreY() < 25) && (y - p.getCentreY() > 5)) {
                        //System.out.println("y axis found"+plane.getCentreX());
                        if(p.isVisible()){
                            score += 10;
                        }
                        p.setVisible(false);
                        Thread t = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                player.playBoom();
                                
                            }
                        });
                        t.start();
                        isClose = true;
                    }
                }
            }
            return isClose;
        }
        
        public boolean closeToPlayer(int x, int y) {
            boolean isClose = false;
            
                if ((x - ship.getCentreX() < 10) && (x - ship.getCentreX() > -10)) {
                    //System.out.println("x axis found");
                    if ((y - ship.getCentreY() < 25) && (y - ship.getCentreY() > 5)) {
                        if(lives>0){
                            lives--;
                        }else{
                            gm.paused = true;
                            JOptionPane.showMessageDialog(this, "Thank you for playing. Score: "+score);
                            
                        }
                        isClose = true;
                    }
                }
            
            return isClose;
        }

        @Override
        public void paintComponent(Graphics g) {
            
            g.drawImage(image, 0, 0, this);
            g.drawImage(clouds, cg1.getBgX(), cg1.getBgY(), this);
            g.drawImage(clouds, cg2.getBgX(), cg2.getBgY(), this);
            g.drawImage(buildings, bg1.getBgX(), bg1.getBgY(), this);
            g.drawImage(buildings, bg2.getBgX(), bg2.getBgY(), this);
            
            g.drawImage(character, ship.getCentreX() - 15, ship.getCentreY() - 15, this);
            g.drawImage(foreground, fg1.getFgX(), fg1.getFgY(), this);
            g.drawImage(foreground, fg2.getFgX(), fg2.getFgY(), this);
            ArrayList projectiles = ship.getProjectiles();
            for (int i = 0; i < projectiles.size(); i++) {
                Projectile p = (Projectile) projectiles.get(i);
                g.setColor(Color.YELLOW);
                g.fillRect(p.getX(), p.getY(), 5, 2);
                g.setColor(Color.BLACK);
                g.fillRect(p.getX()+5, p.getY(), 2, 2);
            }
            for (Plane p : enemies) {
                g.drawImage(p.getImage(), p.getCentreX() - 15, p.getCentreY() - 15, this);
                ArrayList enemyProjectiles;
                if ((enemyProjectiles = p.getProjectiles()) != null) {
                    for (int i = 0; i < enemyProjectiles.size(); i++) {
                        Projectile ep = (Projectile) enemyProjectiles.get(i);
                        g.setColor(Color.BLACK);
                        g.fillRect(ep.getX(), ep.getY(), 5, 2);
                        g.setColor(Color.RED);
                        g.fillRect(ep.getX() + 5, ep.getY(), 2, 2);
                        
                    }
                }

            }
            
            g.drawImage(enemy, plane.getCentreX() - 15, plane.getCentreY() - 15, this);
            
            g.setColor(Color.BLACK);
            g.drawString("FPS: " + fps, 5, 10);
            
           
            g.setFont(new Font(Font.SANS_SERIF, Font.BOLD,20));
            g.setColor(Color.BLACK);
            g.drawString("Score: "+score, 210, 20);
             g.drawString("Lives: "+lives, 350, 20);
            
            frameCount++;
        }

        @Override
        public void keyTyped(KeyEvent ke) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void keyPressed(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

        public int getFPS(){
            return fps;
        }
        
        public void animate() {
            if(!plane.isVisible()){
                
                expanim.update(20,true);
            }
            //expanim.update(10);
            anim.update(10,false);
        }

        private class MyDispatcher implements KeyEventDispatcher {

            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getID() == KeyEvent.KEY_PRESSED) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_UP:
                            upKeyPressed = true;
                            ship.moveUp();
                            break;

                        case KeyEvent.VK_DOWN:
                            downKeyPressed = true;
                            ship.moveDown();
                            break;

                        case KeyEvent.VK_LEFT:
                            leftKeyPressed = true;
                            ship.moveLeft();
                            break;

                        case KeyEvent.VK_RIGHT:
                            rightKeyPressed = true;
                            ship.moveRight();
                            break;

                        case KeyEvent.VK_SPACE:

                            Thread t = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    player.playGun();
                                }
                            });
                            if (!spacePressed) {
                                t.start();
                            }
                            spacePressed = true;


                            break;

                        case KeyEvent.VK_W:
                            ship.moveUp();
                            upKeyPressed = true;
                            break;

                        case KeyEvent.VK_S:
                            downKeyPressed = true;
                            ship.moveDown();
                            break;

                        case KeyEvent.VK_A:
                            leftKeyPressed = true;
                            ship.moveLeft();
                            break;

                        case KeyEvent.VK_D:
                            rightKeyPressed = true;
                            ship.moveRight();
                            break;

                    }
                } else if (e.getID() == KeyEvent.KEY_RELEASED) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_UP:
                            upKeyPressed = false;
                            if (leftKeyPressed || rightKeyPressed) {
                                ship.setySpeed(0);
                                return false;
                            }
                            ship.stop();
                            break;

                        case KeyEvent.VK_DOWN:
                            downKeyPressed = false;
                            if (leftKeyPressed || rightKeyPressed) {
                                ship.setySpeed(0);
                                return false;
                            }
                            ship.stop();
                            break;

                        case KeyEvent.VK_LEFT:
                            leftKeyPressed = false;
                            if (rightKeyPressed) {
                                ship.moveRight();
                                return false;
                            }
                            ship.stop();
                            break;

                        case KeyEvent.VK_RIGHT:
                            rightKeyPressed = false;
                            if (leftKeyPressed) {
                                ship.moveLeft();
                                return false;
                            }
                            ship.stop();
                            break;

                        case KeyEvent.VK_W:
                            upKeyPressed = false;
                            if (leftKeyPressed || rightKeyPressed) {
                                ship.setySpeed(0);
                                return false;
                            }
                            ship.stop();
                            break;

                        case KeyEvent.VK_S:
                            downKeyPressed = false;
                            if (leftKeyPressed || rightKeyPressed) {
                                ship.setySpeed(0);
                                return false;
                            }
                            ship.stop();
                            break;

                        case KeyEvent.VK_A:
                            leftKeyPressed = false;
                            if (rightKeyPressed) {
                                ship.moveRight();
                                return false;
                            }
                            ship.stop();
                            break;

                        case KeyEvent.VK_D:
                            rightKeyPressed = false;
                            if (leftKeyPressed) {
                                ship.moveLeft();
                                return false;
                            }
                            ship.stop();
                            break;


                        case KeyEvent.VK_SPACE:
                            spacePressed = false;
                            player.stopGun();
                            lastShotFired = 0;
                            break;

                    }
                } else if (e.getID() == KeyEvent.KEY_TYPED) {
                    //System.out.println("3test3");
                }
                return false;
            }
        }
    }
}
