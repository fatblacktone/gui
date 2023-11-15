/*
* ImagePanel.java 0.1 30-Jan-2010
*
* Copyright (c) 2010 University of Wales, Aberystwyth.
* All rights reserved.
*
*/

package chess.client;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * ImagePanel - This extension of JPanel handles all drawing events.
 *
 * @author Antony Walters
 * @version 0.1
 */

public class ImagePanel extends JPanel {

    //instance variables
    private Image canvasImage;                //the image to  draw
    private Graphics2D graphic;               //graphic to buffer the image to


    //constants


    /**
     * Constructor that sets the size
     */
    public ImagePanel() {
        //set the size
        this.setSize(400,400);
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
        repaint();
    }

    public void drawBoard(){
        for(int outer=0;outer<4;outer++){
            for(int inner=0;inner<4;inner++){
                graphic.fillRect(outer * 100, inner *100, 50, 50);
                graphic.fillRect(outer * 100 + 50, inner *100 + 50, 50, 50);
            }
        }

    }

}
