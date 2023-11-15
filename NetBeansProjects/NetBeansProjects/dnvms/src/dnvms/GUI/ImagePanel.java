/*
* ImagePanel.java 0.1 30-Jan-2010
*
* Copyright (c) 2010 University of Wales, Aberystwyth.
* All rights reserved.
*
*/

package dnvms.GUI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

/**
 * ImagePanel - This extension of JPanel handles all drawing events.
 *
 * @author Antony Walters
 * @version 0.1
 */

public class ImagePanel extends JPanel {

    //instance variables
    private Image canvasImage;                //the image to  draw
    private Graphics2D graphic; 
    private Level_Of_Detail levelOfDetail;
    private Graph_Type graphType;
    private double xSpacing,ySpacing;
    private ArrayList<Integer> firstValues,secondValues;
    private boolean firstValuesUsed;
    
    
    public static enum Level_Of_Detail {
        DAYS,WEEKS,MONTHS
    } ;
    
    public static enum Graph_Type {
        DELIVERIES,COLLECTIONS,MILES,HOURS,GROSSPROFIT,NETPROFIT
    } ;
    //graphic to buffer the image to

    /**
     * Constructor that sets the size
     */
    public ImagePanel(int width,int height,Graph_Type graphType) {
        this.setSize(width,height);  
        this.graphType = graphType;
        levelOfDetail = Level_Of_Detail.DAYS;
    }

    /**
     * sets up the pane
    
     */

    public final void setUp(){        
        //get the size of the panel
        Dimension size = this.getSize();
        //create an image linked to the screen
        canvasImage = this.createImage(size.width, size.height);
        //then we turn the image into a graphic we can work with
        graphic = (Graphics2D)canvasImage.getGraphics();
        //set the graphic color to white
        graphic.setColor(Color.white);
        //fill entire panel
        graphic.fillRect(0, 0, size.width, size.height);
        //set color to black ready for drawing
        graphic.setColor(Color.black);
        //repaint the panel
        drawX();
        drawY();
        if(levelOfDetail == Level_Of_Detail.DAYS){
            drawDays();
        }else if(levelOfDetail == Level_Of_Detail.WEEKS){
            
        }else if(levelOfDetail == Level_Of_Detail.MONTHS){
            
        }
        
        
        if(graphType == Graph_Type.DELIVERIES){
            drawStops();               
        }else if(graphType == Graph_Type.COLLECTIONS){
            drawStops();
        }else if(graphType == Graph_Type.HOURS){
            drawHours();
        }else if(graphType == Graph_Type.MILES){
            drawMiles();   
        }else if(graphType == Graph_Type.GROSSPROFIT){
            drawProfit();   
        }else if(graphType == Graph_Type.NETPROFIT){
            drawProfit();   
        }
        
        repaint();
    }
    
    private void drawX(){
        graphic.setColor(Color.LIGHT_GRAY);
        graphic.drawLine(50, 410, 650, 410);
    }
    
    private void drawY(){
        graphic.setColor(Color.LIGHT_GRAY);
        graphic.drawLine(50, 40, 50, 410);
    }
    
    private void drawDays(){
        graphic.setColor(Color.DARK_GRAY);
        graphic.drawString("DAYS", 315,440);
        graphic.setColor(Color.LIGHT_GRAY);
        graphic.drawString("MONDAY", 75,423);
        graphic.drawString("TUESDAY", 175,423);
        graphic.drawString("WEDNESDAY", 265,423);
        graphic.drawString("THURSDAY", 370,423);
        graphic.drawString("FRIDAY", 480,423);
        graphic.drawString("SATURDAY", 570,423);
        for(int i =0;i<7;i++){
            graphic.drawLine((100*i)+50, 410, (100*i)+50, 420);
        }
    }
    
    private void drawWeeks(){
        graphic.setColor(Color.DARK_GRAY);
        graphic.drawString("WEEKS", 315,440); 
    }
    
    private void drawMonths(){
        graphic.setColor(Color.DARK_GRAY);
        graphic.drawString("MONTHS", 315,440); 
    }
    
    public void drawPoint(Dimension point,Color color){
        
    }
    
    public void drawPoint(int x,int y,Color color){
        graphic.setColor(color);
        graphic.drawLine(x-5, y, x+5, y);
        graphic.drawLine(x, y-5, x, y+5);
        
        
    }
    
    public void drawHours(){
        drawVerticalString("HOURS", 10, 220);
        for (int i = 0; i < 21; i++) {
            graphic.drawLine(45, 410 - (i * 18), 50, 410 - (i * 18));
            graphic.drawString(i + "", 30, 415 - (i * 18));
        }
    }
    
    public void drawProfit(){
        drawVerticalString("PROFIT", 10, 220);
        for (int i = 0; i < 16; i++) {
            graphic.drawLine(45, 410 - (i * 20), 50, 410 - (i * 20));
            if(i==0)graphic.drawString((i*10) + "", 35, 415 - (i * 20));
            else if(i<5)graphic.drawString((i*20) + "", 30, 415 - (i * 20));
            else graphic.drawString((i*20) + "", 25, 415 - (i * 20));
        }
    }
    
    public void drawMiles(){        
        drawVerticalString("MILES", 10, 220);
        for (int i = 0; i < 16; i++) {
            graphic.drawLine(45, 410 - (i * 20), 50, 410 - (i * 20));
            if(i==0)graphic.drawString((i*20) + "", 35, 415 - (i * 20));
            else if(i<5)graphic.drawString((i*20) + "", 30, 415 - (i * 20));
            else graphic.drawString((i*20) + "", 25, 415 - (i * 20));
        }
        clear(new Dimension(50, 40));
        clear(new Dimension(50, 55));
        clear(new Dimension(50, 70));
        clear(new Dimension(50, 85));
        clear(new Dimension(50, 100));       
    }
    
    public void drawStops(){
        drawVerticalString("STOPS", 10, 220);
        for (int i = 0; i < 16; i++) {
            graphic.drawLine(45, 410 - (i * 20), 50, 410 - (i * 20));
            if(i==0)graphic.drawString((i*10) + "", 35, 415 - (i * 20));
            else if(i<10)graphic.drawString((i*10) + "", 30, 415 - (i * 20));
            else graphic.drawString((i*10) + "", 25, 415 - (i * 20));
        }
        clear(new Dimension(50, 40));
        clear(new Dimension(50, 55));
        clear(new Dimension(50, 70));
        clear(new Dimension(50, 85));
        clear(new Dimension(50, 100));
    }
    
    public void drawLineGraph(ArrayList<Integer> values,Color color){
        if(!firstValuesUsed){
            this.firstValues = new ArrayList<>();
            
        }
        else this.secondValues = new ArrayList<>();
        setXYSpacing();
        graphic.setColor(color);
        boolean firstPass = true;
        int currentX = 50+(int) (xSpacing/2);
        int currentY = 0;
        for(Integer integer:values){
            if(!firstValuesUsed) this.firstValues.add(integer);
            else this.secondValues.add(integer);
            if(firstPass){
                currentY = (int) (integer.intValue() * ySpacing);
                
                drawPoint((int) (currentX),
                        410 - (int) (integer.intValue() * ySpacing), color);
                firstPass = false;
                continue;
            }
            graphic.drawLine(currentX, 410 - currentY, (int) (currentX + xSpacing),
                    410 - (int) (integer.intValue() * ySpacing));
            drawPoint((int) (currentX + xSpacing),
                    410 - (int) (integer.intValue() * ySpacing), color);
            currentX = (int) (currentX + xSpacing);
            if(integer.intValue()!=0)currentY = (int) (integer.intValue() * ySpacing);
            else currentY = integer.intValue();
        }
        firstValuesUsed = true;
    }

    public ArrayList<Integer> getFirstValues() {
        return firstValues;
    }

    public void setFirstValues(ArrayList<Integer> firstValues) {
        this.firstValues = firstValues;
    }

    public boolean isFirstValuesUsed() {
        return firstValuesUsed;
    }

    public void setFirstValuesUsed(boolean firstValuesUsed) {
        this.firstValuesUsed = firstValuesUsed;
    }

    public ArrayList<Integer> getSecondValues() {
        return secondValues;
    }

    public void setSecondValues(ArrayList<Integer> secondValues) {
        this.secondValues = secondValues;
    }

    
    
    public void drawDeliveriesKey(){
        graphic.setColor(Color.DARK_GRAY);
        graphic.drawString("KEY", 470, 85);
        graphic.drawLine(470, 87, 492, 87);
        graphic.setColor(Color.green);
        graphic.drawString("Successful Deliveries", 470, 110);
        graphic.setColor(Color.red);
        graphic.drawString("Un-Successful Deliveries", 470, 125);
        
    }
    
    public void drawCollectionsKey(){
        graphic.setColor(Color.DARK_GRAY);
        graphic.drawString("KEY", 470, 85);
        graphic.drawLine(470, 87, 492, 87);
        graphic.setColor(Color.green);
        graphic.drawString("Successful Collections", 470, 110);
        graphic.setColor(Color.red);
        graphic.drawString("Un-Successful Collections", 470, 125);
        
    }
    
    
    
    private void setXYSpacing(){       
        if(levelOfDetail == Level_Of_Detail.DAYS){
            xSpacing = 100;
        }else if(levelOfDetail == Level_Of_Detail.WEEKS){
            xSpacing = 19;
        }else if(levelOfDetail == Level_Of_Detail.MONTHS){
            xSpacing = 50;
        }
        
        if(graphType == Graph_Type.DELIVERIES){
            ySpacing = 2;               
        }else if(graphType == Graph_Type.COLLECTIONS){
            ySpacing = 2;
        }else if(graphType == Graph_Type.HOURS){
            ySpacing = 18;
        }else if(graphType == Graph_Type.MILES){
            ySpacing = 0.5;   
        }else if(graphType == Graph_Type.GROSSPROFIT){
            ySpacing = 1;   
        }else if(graphType == Graph_Type.NETPROFIT){
            ySpacing = 1;   
        }     
    }
    
    public void drawVerticalString(String toDraw,int x,int y){
        Color oldColor = graphic.getColor();
        graphic.setColor(Color.DARK_GRAY);
        // Create a rotation transformation for the font.
        AffineTransform fontAT = new AffineTransform();
        // get the current font
        Font theFont = graphic.getFont();
        // Derive a new font using a rotatation transform
        fontAT.rotate(1.57);
        Font theDerivedFont = theFont.deriveFont(fontAT);
        // set the derived font in the Graphics2D context
        graphic.setFont(theDerivedFont);
        // Render a string using the derived font
        graphic.drawString(toDraw, x, y);
        // put the original font back
        graphic.setFont(theFont);
        graphic.setColor(oldColor);
    }
    
    /**
     * This method draws a grid on the screen to aid in
     * drawing of the level
     */

    public void drawGrid(){
        graphic.setColor(Color.LIGHT_GRAY);
        //graphic.setColor(new Color(240,240,240));
        for(int i=0;i<32;i++){
            graphic.drawLine(5, 5 + i*15, 244, 5 + i*15);
        }
        for(int i=0;i<16;i++){
            graphic.drawLine(5+i*15, 5, 5+i*15, 334);
        }
        graphic.setColor(Color.black);
    }
    
    
    /**
     * This method clears a given square
     *
     * the parameter is the dimension that represents
     * the x and y position of the square to clear
     *
     * @param pos
     */

    public void clear(Dimension pos){
        int x=pos.width;
        int y=pos.height;
        graphic.setColor(Color.white);
        
        //draw rect
        graphic.fillRect(x-(x%15)+5,y-(y%15)+5, 15, 15);
        graphic.setColor(Color.black);
        //drawGrid();
        //repaint image
        //if posy == e/p the clear above then if posy-1 == wall redraw 
        
        repaint(1);
    }

    /**
     * this method clears the entire panel to white
     */

    public void clear(){
        //set color to white
        graphic.setColor(Color.WHITE);
        //draw a rect the size of the panel
        graphic.fillRect(0,0,this.getWidth(),this.getHeight());
        //set color to black ready to draw
        graphic.setColor(Color.BLACK);
        //repaint the panel
        repaint();
    }


    /**
     * this method paints the component
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        //super.paintComponent(g);
        g.drawImage(canvasImage,0,0,null);

    }

   
    public void drawPlayer(Dimension pos){
        /*if(player!=null){
            clear(player);
            clear(new Dimension(player.width,player.height+5));
            level.placeChar('f',pos.width/15,pos.height/15);
            
            if(level.getChar(player.width/15,player.height/15-1)=='w'){
              drawWall(new Dimension(player.width,player.height-15));
            }
        }

        int x=pos.width;
        int y=pos.height;
        level.placeChar('p',x/15,y/15);
        graphic.setColor(Color.red);
        graphic.drawImage(new ImageIcon("images/player.png").getImage(), x+5, y+5, this);
        repaint(1);
        graphic.setColor(Color.black);
        player=pos;
        */
    }

    public Graph_Type getGraphType() {
        return graphType;
    }

    public void setGraphType(Graph_Type graphType) {
        this.graphType = graphType;
    }

    

}
