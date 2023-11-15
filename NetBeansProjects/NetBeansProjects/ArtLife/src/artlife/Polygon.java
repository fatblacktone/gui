/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package artlife;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;



/**
 *
 * @author Fatblack
 */
public class Polygon extends java.awt.Polygon{
    
    private Line2D lineOne,lineTwo;
    
    
    public Polygon(int xpoints[],int ypoints[],int points){
        super(xpoints,ypoints,points);
    }
    
    
    public boolean intersects(Polygon other){ 
        boolean intersects = false;
        for(int i=0;i<other.npoints;i++){
            if(this.contains(other.xpoints[i], other.ypoints[i])){
                intersects = true;
                //System.out.println(""+i);
                
            }
        }
            for(int outer=0;outer<this.npoints-1;outer++){
                for(int inner=0;inner<other.npoints-1;inner++){
                    int x1 = xpoints[outer],y1=ypoints[outer],
                        x2 = xpoints[outer+1],y2=ypoints[outer+1];
                    
                    int x3 = other.xpoints[inner],y3=other.ypoints[inner],
                        x4 = other.xpoints[inner+1],y4 = other.ypoints[inner+1];
                    
                    //System.out.println(outer+" Line 1: "+x1+"."+y1+"  to  "+x2+"."+y2);
                    //System.out.println(outer+" Line 2: "+x3+"."+y3+"  to  "+x4+"."+y4);
                    //System.out.println("----------------------------------------------");
                    if(Line2D.linesIntersect(x1,y1,x2,y2,x3,y3,x4,y4)){
                        intersects = true;
                        //System.out.println("LINES");
                    }
                    
                }
            }
            //System.out.println();
            //System.out.println(intersects);
        return intersects;
    }
}
