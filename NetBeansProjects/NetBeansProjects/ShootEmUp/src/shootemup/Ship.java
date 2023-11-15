/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shootemup;


import java.util.ArrayList;

/**
 *
 * @author Fatblack
 */
public class Ship {
    
    private int centreX = 100,
            centreY = 240;
    private int xSpeed,
            ySpeed;
    private Background bg1;// = ShootEmUp.getBg1();
    private Background bg2;// = ShootEmUp.getBg2();
    private Background cg1;// = ShootEmUp.getBg1();
    private Background cg2;// = ShootEmUp.getBg2();
    private Foreground fg1;// = ShootEmUp.getFg1();
    private Foreground fg2 ;//= ShootEmUp.getFg2();
    private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
    private int lastProjectileX;
    private static final int MOVESPEED = 5;
    
    public Ship(Background bg1,Background bg2,Background cg1,Background cg2,Foreground fg1,Foreground fg2){
        this.bg1 = bg1;
        this.bg2 = bg2;
        this.fg1 = fg1;
        this.fg2 = fg2;
        this.cg1 = cg1;
        this.cg2 = cg2;
    }
    
    public Ship(){
        
        bg1 = GameLoopTest.getBg1();
        bg2 = GameLoopTest.getBg2();
        fg1 = GameLoopTest.getFg1();
        fg2 = GameLoopTest.getFg2();
        cg1 = GameLoopTest.getCg1();
        cg2 = GameLoopTest.getCg2();
        
    }

   

    public void update() {

        if (xSpeed < 0) {
            centreX += xSpeed;
        }
        if (xSpeed == 0 || xSpeed < 0) {
            bg1.setSpeedX(-2);
            bg2.setSpeedX(-2);
            fg1.setSpeedX(-4);
            fg2.setSpeedX(-4);
            cg1.setSpeedX(-1);
            cg2.setSpeedX(-1);
        }
        if (centreX <= 300 && xSpeed < 0) {
            centreX += xSpeed;
        }
        if (xSpeed > 0 && centreX > 300) {
            bg1.setSpeedX(-MOVESPEED - 2);
            bg2.setSpeedX(-MOVESPEED - 2);
            fg1.setSpeedX(-MOVESPEED - 4);
            fg2.setSpeedX(-MOVESPEED - 4);
            cg1.setSpeedX(-MOVESPEED - 1);
            cg2.setSpeedX(-MOVESPEED - 1);
            //System.out.print("\nScroll");
        } else {
            centreX += xSpeed;
        }

        if (centreX + xSpeed <= 60) {
            centreX = 61;
        }
        //System.out.println(" "+(centreY+ySpeed));
        if (centreY + ySpeed > 30 && centreY + ySpeed < 430) {
            centreY += ySpeed;

        }
    }

    public void fire() {
        
        Projectile p = new Projectile(centreX + 20, centreY+10);
        projectiles.add(p);



    }

    public ArrayList getProjectiles() {
        return projectiles;
    }

    public void moveRight() {
        xSpeed = 6;
    }

    public void moveLeft() {
        xSpeed = -3;
    }

    public void stop() {
        xSpeed = 0;
        ySpeed = 0;
    }

    public void moveUp() {
        ySpeed = -6;
    }

    public void moveDown() {
        ySpeed = 6;
    }

    public int getCentreX() {
        return centreX;
    }

    public void setCentreX(int centreX) {
        this.centreX = centreX;
    }

    public int getCentreY() {
        return centreY;
    }

    public void setCentreY(int centreY) {
        this.centreY = centreY;
    }

    public int getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

    public int getySpeed() {
        return ySpeed;
    }

    public void setySpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }

    private void sop(String string) {
        System.out.println(string);
    }
}
