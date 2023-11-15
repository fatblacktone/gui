/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genal;

import java.util.ArrayList;



/**
 *
 * @author Fatblack
 */
public class Ameba implements Comparable<Ameba> {
    
    
    private int x,y;
    private String chromosome;
    private int food;
    private int currentGene = 0;
    private int speed = 3;
    private ArrayList<Ameba> others;
    private int children;
    

    public Ameba(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public ArrayList<Ameba> getOthers() {
        return others;
    }

    public void setOthers(ArrayList<Ameba> others) {
        this.others = others;
    }
    
    public void incrementChildren(){
        children++;
    }

    public int getChildren() {
        return children;
    }

    
    
    public Ameba(int x, int y, String chromosome) {
        this.x = x;
        this.y = y;
        this.chromosome = chromosome;
    }
    
    public Polygon getBoundingPolygon(){
        int xpoints[] = {x-5,x,x+5,x};
        int ypoints[] = {y,y-5,y,y+5};
        return new Polygon(xpoints, ypoints, 4);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getChromosome() {
        return chromosome;
    }

    public void setChromosome(String chromosome) {
        this.chromosome = chromosome;
    }
    
    
    
    public void update(){
        int oldx = x;
        int oldy = y;
        switch(Integer.parseInt(chromosome.charAt(currentGene++)+"")){
            case 0:
                break;
            case 1:
                y-=speed;
                break;
            case 2:
                x+=speed;
                y-=speed;
                break;
            case 3:
                y+=speed;
                break;
            case 4:
                x+=speed;
                y+=speed;
                break;
                case 5:
                y-=speed;
                
                break;
            case 6:
                x-=speed;
                y+=speed;
                break;
            case 7:
                x-=speed;
                break;
            case 8:
                x-=speed;
                y-=speed;
                break;
        }
        if(x<20){
            x=700;
        }
        if(x>700){
            x=20;
        }
        if(y<20){
            y=500;
        }
        if(y>500){
            y=20;
        }
        
        for(Ameba a:others){
            if(!a.equals(this)){
                if(a.getBoundingPolygon().intersects(this.getBoundingPolygon())){
                    x=oldx;
                    y=oldy;
                }
            }
        }
        if(currentGene>=20){
            currentGene = 0;
        }
    }
    
    public void increamentFood(){
        food++;
        
    }

    public int getFood() {
        return food;
    }
    
    public boolean equals(Ameba other){
        boolean b = false;
        if(this.getX()==other.getX()&&this.getY()==other.getY()&&this.getFood()==other.getFood()){
            b=true;
        }
        return b;
    }
    

    @Override
    public int compareTo(Ameba other) {
        int i;
        if(this.getFood()<other.getFood()){
            i=1;
        }else if(this.getFood()>other.getFood()){
            i=-1;
        }else{
            i=0;
        }
        return i;
    }

    void setFood(int i) {
        food = i;
    }
    
    
}
