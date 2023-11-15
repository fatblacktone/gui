/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.angryandroid.framework;

import java.awt.Point;
import java.awt.Polygon;

/**
 *
 * @author Fatblack
 */
public class CollisionDetection {
    
    /**
     * Determines if the two polygons supplied intersect each other, by checking if either polygon has points which are contained in the other.
     * (It doesn't detect body-only intersections, but is sufficient in most cases.)
     */
    public static boolean DoesPolygonIntersectPolygon(Polygon p1, Polygon p2)
    {
        Point p;
        for(int i = 0; i < p2.npoints;i++)
        {
            p = new Point(p2.xpoints[i],p2.ypoints[i]);
            if(p1.contains(p))
                return true;
        }
        for(int i = 0; i < p1.npoints;i++)
        {
            p = new Point(p1.xpoints[i],p1.ypoints[i]);
            if(p2.contains(p))
                return true;
        }
        return false;
    }
    
}
