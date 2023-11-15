/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package droidcopter.gui.graphics;

import com.angryandroid.framework.InteractableLayer;
import java.awt.Image;
import java.awt.Polygon;




/**
 *
 * @author Fatblack
 */
public class Background implements InteractableLayer{
    
    private float xCoord, yCoord, width , speedX ,speedY;
    private Image image;
    private final int PIXELS_PER_SECOND = 60;
    private final int UPDATE_LENGTH = PIXELS_PER_SECOND/1000000000;
    private float lastUpdateTime;
    private Polygon boundingPolygon;

    

    

    /**
     *
     * @param xCoord
     * @param yCoord
     * @param width
     * @param image
     */
    public Background(int xCoord, int yCoord,int width,Image image) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.width = width;
        this.speedX = -1;        
        this.speedY = 0;
        this.image = image;
        
        

        
    }
    
    

    
    /**
     *
     */
    public void update() {
            
        xCoord +=  speedX;        

        if (xCoord <= -width) {
            xCoord += width * 2;
        }
        
   
    }
    
    /**
     * Get the value of image
     *
     * @return the value of image
     */
    public Image getImage() {
        return image;
    }

    /**
     * Set the value of image
     *
     * @param image new value of image
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     *
     * @return
     */
    public int getxCoord() {
        return (int) xCoord;
    }

    /**
     *
     * @param xCoord
     */
    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    /**
     *
     * @return
     */
    public int getyCoord() {
        return (int) yCoord;
    }

    /**
     *
     * @param yCoord
     */
    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    /**
     *
     * @return
     */
    public int getWidth() {
        return (int) width;
    }

    /**
     *
     * @param width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     *
     * @return
     */
    public int getSpeedX() {
        return (int) speedX;
    }

    /**
     *
     * @param speedX
     */
    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    /**
     *
     * @return
     */
    public int getSpeedY() {
        return (int) speedY;
    }

    /**
     *
     * @param speedY
     */
    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }
    
    
    public void setBoundingPolygon(Polygon boundingPolygon){
        this.boundingPolygon = boundingPolygon;
    }
    
    public Polygon getBoundingPolygon(){
        
        int modifier = getxCoord();
        
        
        
        int xPoints[] = {15+modifier,
                         15+modifier,
                         538+modifier,
                         538+modifier,
                         1095+modifier,
                         1095+modifier,
                         1515+modifier,
                         1515+modifier,
                         1962+modifier,
                         1962+modifier,
                         15+modifier
        };
        
        int yPoints[] = {600,
                         515,
                         515,
                         460,
                         460,
                         415,
                         415,
                         453,
                         453,
                         600,
                         600
            
        };
        
        boundingPolygon = new Polygon(xPoints, yPoints, 11);
        return boundingPolygon;
    }
}
