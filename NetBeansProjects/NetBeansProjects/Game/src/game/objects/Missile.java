/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.objects;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Fatblack
 */
public class Missile extends Projectile{

    private int updateCount;
    private Player player;
    private Animation animation;
    private long currentTimeInMilliseconds,lastTimeInMiliseconds;
    
    public Missile(int x, int y) {
        super(x, y);
        animation = new Animation();
        Image image= null;
        try {
            int count = 1;
          image = ImageIO.read(new File("./images/player/projectiles/missile.png"));
               animation.addFrame(image, 120);
               count++;
               System.err.println(count+"   here  ");
           
           
        } catch (IOException ex) {
            
        }
        currentTimeInMilliseconds = System.currentTimeMillis();
        
    }
    
    public void setPlayer(Player player){
        this.player = player;
    }
    
    @Override
    public void update(){
        if((updateCount++)<10){
            this.setX(player.getCenterX());
            this.setY(this.getY()+2);
            
            return;
        }
        
        super.update();
        
    }
    
    @Override
    public Image getImage(){
        return animation.getImage();
    }
    
}
