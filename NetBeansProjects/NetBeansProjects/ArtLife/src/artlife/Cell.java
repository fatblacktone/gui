/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package artlife;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Fatblack
 */
public class Cell {
    
    private int centerX,centerY;
    private double angleOfMotion;
    private Color color;
    private ArrayList<Cell> others;
    private boolean foodInRange,enemyInRange;
    private int xpoints[],ypoints[];
    private boolean normalMotion;
    private int movementPosition = 0;
    private int foodColected;
    private int foodInBelly;
  
    
    private String chromosome;  //entire chromosome 0:00:000:000:00000000000000000000.............
    private String shape ="",       //shape of entity 
                   range,       //range of vision 0-99
                   angle,       //angle from forwards = 0-360
                   spread,      //angle of sperad of vision
                   normalMovement=""; //string
    private double hyp;
    private double velocityX;
    private double velocityY;
    private int points;

    public Cell(int centerX, int centerY) {
        this.centerX = centerX;
        this.centerY = centerY;
        
        
        Random rand = new Random();
        this.angleOfMotion = rand.nextInt(360);
        points = 10;
        shape+=points;
        int segment = 360/points;
        int add = segment;
        for(int i=0;i<points;i++){
            shape+=rand.nextInt(8)+2;
            if(i==0){
                shape+="000";
                
            }else {
               if(add<10){
                   shape+="00"+add;
               }else if(add<100){
                   shape+="0"+add;
               }else{
                   shape+=add;
               } 
               add += segment; 
            }
                        
        }
        setBoundingPolygon();
        for(int i=0;i<20;i++){
            normalMovement+=rand.nextInt(6);
            normalMovement+=rand.nextInt(5);
            normalMovement+=rand.nextInt(10);
            
        }
        System.out.println(normalMovement);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ArrayList<Cell> getOthers() {
        return others;
    }

    public void setOthers(ArrayList<Cell> others) {
        this.others = others;
    }

    public int getFoodColected() {
        return foodColected;
    }

    public void setFoodColected(int foodColected) {
        this.foodColected = foodColected;
    }
    
    public void incrementFoodColected(){
        foodColected++;
        foodInBelly++;
    }

    public int getFoodInBelly() {
        return foodInBelly;
    }

    public void setFoodInBelly(int foodInBelly) {
        this.foodInBelly = foodInBelly;
    }
    
    public void decrementFoodInBelly(){
        foodInBelly--;
    }
    
    public void update() {
        int oldx = centerX;
        int oldy = centerY;
        
        
        hyp = Integer.parseInt(""+normalMovement.charAt(movementPosition++));
        if(Integer.parseInt(""+normalMovement.charAt(movementPosition))>2){
            angleOfMotion+=Integer.parseInt(""+normalMovement.charAt(movementPosition+1));
        }
        else if(Integer.parseInt(""+normalMovement.charAt(movementPosition))<2){
            angleOfMotion-=Integer.parseInt(""+normalMovement.charAt(movementPosition+1));
        } else if(Integer.parseInt(""+normalMovement.charAt(movementPosition))==2){
            
        }
        movementPosition+=2;

        if(movementPosition==60){
            movementPosition = 0;
        }

        double radians = Math.toRadians(angleOfMotion);


        double scaleX = Math.cos(radians);
        double scaleY = Math.sin(radians);

        velocityX = hyp * scaleX;
        velocityY = hyp * scaleY;




        centerX = (int) (centerX + velocityX);
        centerY = (int) (centerY + velocityY);
        if(centerX<50){
            centerX+=800;
        }
        if(centerX>850){
            centerX-=800;
        }
        if(centerY<50){
            centerY+=500;
        }
        if(centerY>550){
            centerY-=500;
        }
        for (Cell other : others) {
            if (!this.equals(other) && this.getBoundingPolygon().intersects(other.getBoundingPolygon())) {
                centerX = oldx;
                centerY = oldy;
            }
        }
        int xp[] = xpoints;
        int yp[] = ypoints;
        setBoundingPolygon(); 
        for (Cell other : others) {
            if (!this.equals(other) && this.getBoundingPolygon().intersects(other.getBoundingPolygon())) {
                xpoints = xp;
                ypoints = yp;
            }
        }
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(color);
        
        g.fillPolygon(this.getBoundingPolygon());
       
        //System.out.println(points);
       
       // boolean intersects = false;
       //     for(int outer=0;outer<getBoundingPolygon().npoints-1;outer++){
       //             g.setColor(Color.red);
       //             g.drawLine(getBoundingPolygon().xpoints[outer], getBoundingPolygon().ypoints[outer],
       //                                   getBoundingPolygon().xpoints[outer+1], getBoundingPolygon().ypoints[outer+1]);
       //             //System.out.println("LINES");
       //         g.setColor(Color.black);
       //     }
    }
    
    public void adjustPolygonAngle(int adjustment){
        int position = 0;
        int point = Integer.parseInt(shape.charAt(position++)+""+shape.charAt(position++));
        
        
        xpoints = new int[point];
        ypoints = new int[point];
        //System.out.println(points+": :"+shape+": :"+(shape.length()-1)/points);
        
        for(int i=0;i<point;i++){
            hyp = Integer.parseInt(""+shape.charAt(position++));
            String angleString = "";
            for(int in=0;in<3;in++){
                angleString+=shape.charAt(position);
                position++;
            }
            int degrees = Integer.parseInt(angleString)+adjustment;
            double radians = Math.toRadians(degrees);
                   
        
            double scaleX = Math.cos(radians);
            double scaleY = Math.sin(radians);
        
            velocityX = hyp*scaleX;
            velocityY = hyp*scaleY;
        
        
        
        
            xpoints[i] = (int) (velocityX);
            ypoints[i] = (int) (velocityY);
        }
        
    }
    
    public void setBoundingPolygon(){
        
        int position = 0;
        int point = Integer.parseInt(shape.charAt(position++)+""+shape.charAt(position++));
        
        
        xpoints = new int[point];
        ypoints = new int[point];
        //System.out.println(points+": :"+shape+": :"+(shape.length()-1)/points);
        
        for(int i=0;i<point;i++){
            hyp = Integer.parseInt(""+shape.charAt(position++));
            String angleString = "";
            for(int in=0;in<3;in++){
                angleString+=shape.charAt(position);
                position++;
            }
            int degrees = (int) (Integer.parseInt(angleString)+angleOfMotion);
            double radians = Math.toRadians(degrees);
                   
        
            double scaleX = Math.cos(radians);
            double scaleY = Math.sin(radians);
        
            velocityX = hyp*scaleX;
            velocityY = hyp*scaleY;
        
        
        
        
            xpoints[i] = (int) (velocityX);
            ypoints[i] = (int) (velocityY);
        }
        
         
    }

    public Polygon getBoundingPolygon(){
        int xp[] = new int[points];
        int yp[] = new int[points];
        for(int i=0;i<points;i++){
            xp[i] = xpoints[i]+ centerX;
            yp[i] = ypoints[i]+ centerY;
        }
        return new Polygon(xp, yp, points);     
    }
    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    
    
    
    public boolean equals(Cell other){
        boolean equal = false;
        if(this.shape.equals(other.shape)){
            equal = true;
            
        }
        return equal;
    }
    
    public double angle(double centreX, double centreY, double pointX, double pointY) {
        return (Math.atan2(pointX - centreX, centreY - pointY));//-1.57)*0.95;// * 180.0F / Math.PI;
    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public double getAngleOfMotion() {
        return angleOfMotion;
    }

    public void setAngleOfMotion(double angleOfMotion) {
        this.angleOfMotion = angleOfMotion;
    }

    public String getChromosome() {
        return chromosome;
    }

    public void setChromosome(String chromosome) {
        this.chromosome = chromosome;
    }
    
    
}
