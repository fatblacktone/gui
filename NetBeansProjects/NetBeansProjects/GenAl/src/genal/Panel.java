/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Fatblack
 */
public class Panel extends JLayeredPane implements MouseMotionListener,ChangeListener{
    
    
    private ArrayList<Ameba> amebas;
    private ArrayList<Dimension> food;
    private int generation,best,update;
    private ArrayList<Integer> bestGraph = new ArrayList<>(),averageGraph = new ArrayList<>();
   
    
    public Panel(){
        
    }

    public ArrayList<Ameba> getAmebas() {
        return amebas;
    }

    public void setAmebas(ArrayList<Ameba> amebas) {
        this.amebas = amebas;
    }

    public ArrayList<Dimension> getFood() {
        return food;
    }

    public void setFood(ArrayList<Dimension> food) {
        this.food = food;
    }
    
    
    public void addAverage(int avg){
        averageGraph.add(avg);
    }
    
    
    @Override
    public void paintComponent(Graphics g){
        Ameba a = null;
        if (amebas != null) {
            for (int i = 0; i < amebas.size(); i++) {
                a = amebas.get(i);
                if(a.getFood()<256){
                    g.setColor(new Color(255-a.getFood(),0,a.getFood()));
                }else if(a.getFood()>255&&a.getFood()<512){
                    //System.out.println(a.getFood());
                    try{
                        g.setColor(new Color(0,a.getFood()-255,(255)-(a.getFood()-256)));
                    }catch(IllegalArgumentException ex){
                        System.out.println(a.getFood()+": "+ex.toString());
                    }
                }else if(a.getFood()>511){
                    try{
                        g.setColor(new Color(0,255,a.getFood()-512));
                    }catch(IllegalArgumentException ex){
                        System.out.println(a.getFood()+": "+ex.toString());
                    }
                }
                //g.drawLine(a.getX() - 6, a.getY(), a.getX() + 6, a.getY());
                //g.drawLine(a.getX(), a.getY() + 6, a.getX(), a.getY() - 6);
                g.fillPolygon(a.getBoundingPolygon());
                g.setColor(Color.black);
                
            }
        }
        if(food!=null){
            Dimension d = null;
            Random ran = new Random();
            for(int i=0;i<food.size();i++){
                //g.setColor(new Color(ran.nextInt(255),ran.nextInt(255),ran.nextInt(255)));
                g.setColor(Color.yellow);
                try{
                    d=food.get(i);
                }catch(IndexOutOfBoundsException ex){
                    
                }
                int xpoints[] = {d.width-5,d.width,d.width+5,d.width};
                int ypoints[] = {d.height,d.height-5,d.height,d.height+5};
                g.fillPolygon(xpoints,ypoints,4);
                //g.setColor(new Color(ran.nextInt(255),ran.nextInt(255),ran.nextInt(255)));
                g.setColor(Color.green);
                int xpoint[] = {d.width-4,d.width,d.width+4,d.width};
                int ypoint[] = {d.height,d.height-4,d.height,d.height+4};
                g.drawPolygon(xpoint,ypoint,4);
                //g.setColor(new Color(ran.nextInt(255),ran.nextInt(255),ran.nextInt(255)));
                int xpoin[] = {d.width-3,d.width,d.width+3,d.width};
                int ypoin[] = {d.height,d.height-3,d.height,d.height+3};
                g.drawPolygon(xpoin,ypoin,4);
            }
        }
        g.setColor(Color.red);
        
        int count =0;
        for(Integer d:bestGraph){
            g.drawRect(710+(count++),500, 1, -d);
        }
        g.setColor(Color.green);
        count = 0;
        for(Integer d:averageGraph){
            g.drawRect(710+count++,500, 1, -d);
        }
        g.setFont(new Font(Font.MONOSPACED, 25, 25));
        g.drawString("Generation: "+generation, 710,40);
        g.drawString("Best: "+best, 710,70);
        g.drawString("update: "+update, 710,100);
        
    }   

    public void setGeneration(int generation) {
        this.generation = generation;
        
    }

    public void setBest(int best) {
        this.best = best;
        bestGraph.add(best);
    }

    public int getGeneration() {
        return generation;
    }

    public void setUpdate(int update) {
        this.update = update;
    }
    
    

    
    @Override
    public void mouseDragged(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void stateChanged(ChangeEvent ce) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
