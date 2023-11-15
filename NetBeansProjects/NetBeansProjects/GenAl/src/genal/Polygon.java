/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genal;

/**
 *
 * @author Fatblack
 */
public class Polygon extends java.awt.Polygon{
    
    public Polygon(int[] x,int[] y,int z){
        super(x,y,z);
    }
    
    public boolean intersects(Polygon polygon){
        int numberOfPoints = 4;
        boolean intersects = false;
        for(int i=0;i<numberOfPoints;i++){
            if(this.contains(polygon.xpoints[i], polygon.ypoints[i])){
                intersects = true;
                //System.out.println(""+i);
                break;
            }
        }
        return intersects;
    }
}
