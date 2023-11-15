/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package artlife;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
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
    
    private ArrayList<Cell> cells;
    private ArrayList<Cell> cells2;
    private ArrayList<Food> food;
    private ArrayList<Food2> food2;
    
    public Panel(){
        cells = new ArrayList<>();
    }

    public ArrayList<Cell> getCells() {
        return cells;
    }

    public void setCells(ArrayList<Cell> cells) {
        this.cells = cells;
    }

   public void addCell(Cell cell){
       cells.add(cell);
   }

    public ArrayList<Food> getFood() {
        return food;
    }

    public void setFood(ArrayList<Food> food) {
        this.food = food;
    }

    public ArrayList<Food2> getFood2() {
        return food2;
    }

    public void setFood2(ArrayList<Food2> food2) {
        this.food2 = food2;
    }

    public ArrayList<Cell> getCells2() {
        return cells2;
    }

    public void setCells2(ArrayList<Cell> cells2) {
        this.cells2 = cells2;
    }
    
    
    
    @Override
    public void paintComponent(Graphics g){
        int count = 0;
        if(cells2!=null){
            for(Cell c:cells2){
               
            c.draw(g);
            g.setFont(new Font(Font.MONOSPACED,20,20));
            //g.drawString(++count+"", c.getCenterX(), c.getCenterY());
        }
        
        }
        for(Cell c:cells){
            c.draw(g);
            g.setFont(new Font(Font.MONOSPACED,20,20));
            //g.drawString(++count+"", c.getCenterX(), c.getCenterY());
        }
        
        if(food!=null){
            for(Food f:food){
                g.setColor(Color.red);
                g.fillRect(f.getX()-1, f.getY()-1, 2, 2);
            }
        }
        if(food2!=null){
            for(Food2 f:food2){
                g.setColor(Color.blue);
                g.fillRect(f.getX()-1, f.getY()-1, 2, 2);
            }
        }
        //g.setColor(Color.red);
        //g.drawLine(10, 10, 100, 100);
        //g.setColor(Color.green);
        //g.drawLine(50, 10, 50, 200);
        //g.setColor(Color.black);
        //boolean doesCross = Line2D.linesIntersect
        //        (10, 10, 100, 100, 50, 10, 50, 200);
        //g.drawString(doesCross+"", 100, 100);
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
