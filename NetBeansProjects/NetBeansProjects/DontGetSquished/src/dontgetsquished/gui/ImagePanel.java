/*
* ImagePanel.java 0.1 30-Jan-2010
*
* Copyright (c) 2010 University of Wales, Aberystwyth.
* All rights reserved.
*
*/

package dontgetsquished.gui;



import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * ImagePanel - This extension of JPanel handles all drawing events.
 *
 * @author Antony Walters
 * @version 0.1
 */

public class ImagePanel extends JPanel implements MouseListener, MouseMotionListener{

    //instance variables
    private Image canvasImage;                //the image to  draw
    private Graphics2D graphic;               //graphic to buffer the image to
    
    //constants
    private static int WIDTH = 250, HEIGHT = 330;
    


    /**
     * Constructor that sets the size
     */
    public ImagePanel() {
       

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
        drawGrid();
        //repaint image
        //if posy == e/p the clear above then if posy-1 == wall redraw 
        
        repaint(1);
    }

    /**
     * this method clears the panel to white
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

    /**
     * This method deals with mouse drag event
     *
     * @param e
     */

    


    /**
     * This method deals with mouse button presses
     *
     * @param e
     */

    

    /**
     * This method is to test that the minimum requirements
     * for the level have been reached.
     *
     * This is 3 enemies and a placed player
     *
     * @return
     */
    
   

    /**
     * This method draws a bevel around the edge of the 
     * drawing area
     */

    private void drawBevel(){
       int[] leftBottomX = new int[7];
       int[] leftBottomY = new int[7];

       leftBottomX[0] = 0;
       leftBottomX[1] = 0;
       leftBottomX[2] = this.getWidth();
       leftBottomX[3] = this.getWidth() - 5;
       leftBottomX[4] = 5;
       leftBottomX[5] = 5;

       leftBottomY[0] = 0;
       leftBottomY[1] = this.getHeight();
       leftBottomY[2] = this.getHeight();
       leftBottomY[3] = this.getHeight()-5;
       leftBottomY[4] = this.getHeight()-5;
       leftBottomY[5] = 5;

       graphic.setColor(Color.DARK_GRAY);
       graphic.fillPolygon(leftBottomX, leftBottomY, 6);

       int[] rightTopX = new int[7];
       int[] rightTopY = new int[7];

       rightTopX[0] = 0;
       rightTopX[1] = this.getWidth();
       rightTopX[2] = this.getWidth();
       rightTopX[3] = this.getWidth() - 5;
       rightTopX[4] = this.getWidth() - 5;
       rightTopX[5] = 5;

       rightTopY[0] = 0;
       rightTopY[1] = 0;
       rightTopY[2] = this.getHeight();
       rightTopY[3] = this.getHeight()-5;
       rightTopY[4] = 5;
       rightTopY[5] = 5;

       graphic.setColor(Color.BLACK);
       graphic.fillPolygon(rightTopX, rightTopY, 6);
    }

    /**
     * sets up the pane
     *
     * i have to admit that this bit of code i did get from elsewhere. i think i
     * origanaly saw it in an example from michael kolling but cant be sure now
     * as i have copy/paseted it a hundred times
     */

    public void setUp(){
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
        drawBevel();
        repaint();
    }

    /**
     * This method sets the status of the drawing
     * 0 = draw walls
     * 1 = draw player
     * 2 = draw enemy
     * 3 = set name
     * @param status
     */
   
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
     * This method deals with mouse button releases.
     *
     * @param e
     */

   

    //unused methods implemented from interfaces

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {

    }

    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet.");
    }




}
