/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package droidcoptor2;

import com.angryandroid.framework.GameCharacter;
import java.awt.Polygon;

/**
 *
 * @author Tone
 */
public class APCEnemy extends BasicCharacter{

    public APCEnemy(int x,int xSpeed){
        this.setX(x);
        this.setXSpeed(xSpeed);
        this.setY(528);
    } 
    
    public void update(){
        super.update();
        //System.out.println(getX());
    }
}
