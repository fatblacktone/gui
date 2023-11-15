/*
* ImagePanel.java 0.1 30-Jan-2010
*
* Copyright (c) 2010 University of Wales, Aberystwyth.
* All rights reserved.
*
*/

package GuiComponents;

import backend.Level;
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

public class ImagePanel extends JPanel implements MouseListener, MouseMotionListener{

    //instance variables
    private Image canvasImage;                //the image to  draw
    private Graphics2D graphic;               //graphic to buffer the image to
    private int status;
    private boolean leftButtonPressed,rightButtonPressed;
    private Dimension player;
    private Level level;
    private int numberOfEnemies;

    //constants
    private static int WIDTH = 250, HEIGHT = 330;
    private boolean playerPlaced;


    /**
     * Constructor that sets the size
     */
    public ImagePanel() {
        //set the size
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        level=new Level();

    }
    
    public boolean isPlayerPlaced(){
       return playerPlaced;
    }
    

    /**
     * this metho draws a rectangle 10x10px at the co-ords held in the dimension
     * with some more rectangles drawn on top to make a pattern
     * @param pos
     */

    public void drawWall(Dimension pos){
        int x=pos.width;
        int y=pos.height;
        //draw rect
        graphic.drawImage(new ImageIcon("images/wall.png").getImage(), x+5, y+5, this);
        //repaint image
        repaint(1);
    }

    /**
     * This method returns the status of the drawing area
     *
     * @return
     */
    
    public int getStatus(){
    	return status;
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

    public void mouseDragged(MouseEvent e) {
    	if(e.getX()<0||e.getY()<0||e.getX()/15>15||e.getY()/15>21)return;
    	System.out.println("X = "+e.getX()/15+" Y = "+e.getY()/15);
        if(status==1&&leftButtonPressed&&level.getChar((e.getX())/15,e.getY()/15)=='f'){
        	
            drawWall(new Dimension((e.getX()-(e.getX()%15)),(e.getY()-(e.getY()%15))));
            level.placeChar('w', e.getX()/15,e.getY()/15);
            if(e.getY()/15<21){
            if(level.getChar(e.getX()/15,(e.getY()/15)+1)=='p'){
        		drawPlayer(player);
        	}
            else if(level.getChar(e.getX()/15,e.getY()/15+1)=='e'){
        		drawEnemy(new Dimension(e.getX(),e.getY()));
        	}
            }
            drawGrid();
        }
        else if(rightButtonPressed){
            clear(new Dimension(e.getX()-(e.getX()%10),e.getY()-(e.getY()%10)));
            drawGrid();
            if(level.getChar(e.getX()/15,e.getY()/15)=='w'){
            	clear(new Dimension(e.getX()-(e.getX()%10),e.getY()-(e.getY()%10)));
            	level.placeChar('f',e.getX()/15,e.getY()/15);
            }
            else{
                int x=e.getX()-(e.getX()%15);
                int y=e.getY()-(e.getY()%15);
                clear(new Dimension(x,y));
                clear(new Dimension(x,y-15));
                level.placeChar('f',e.getX()/15,e.getY()/15);
                //clear(new Dimension(x,y));
                if(level.getChar(e.getX()/15,e.getY()/15)=='p'){
                   playerPlaced=false;
                }
                if(level.getChar(e.getX()/15,e.getY()/15)=='e'){
                	numberOfEnemies--;
            	}
                if(level.getChar(e.getX()/15,e.getY()/15-1)=='w'){
            		drawWall(new Dimension(x,y-15));
            	}
                if(level.getChar(e.getX()/15,e.getY()/15-1)=='p'){
            		drawPlayer(new Dimension(x,y-15));
            	}
                if(level.getChar(e.getX()/15,e.getY()/15-1)=='e'){
            		drawEnemy(new Dimension(x,y-15));
            	}
                if(level.getChar(e.getX()/15,e.getY()/15+1)=='e'){
            		drawEnemy(new Dimension(x,y+15));
            	}

            }
        }
    }


    /**
     * This method deals with mouse button presses
     *
     * @param e
     */

    public void mousePressed(MouseEvent e) {
       if(e.getX()/15<0||e.getY()/15<0||e.getX()/15>15||e.getY()/15>21)return;
        System.out.println("X = "+e.getX()/15+" Y = "+e.getY()/15);
        if(status==1&&e.getButton()==1&&level.getChar(e.getX()/15,e.getY()/15)=='f'){
        	
        	drawWall(new Dimension((e.getX()-(e.getX()%15)),(e.getY()-(e.getY()%15))));
        	if(e.getY()/15<21)
                if(level.getChar(e.getX()/15,(int) e.getY()/15+1)=='p'){
        		drawPlayer(player);
        	}
            level.placeChar('w',e.getX()/15,e.getY()/15);
            leftButtonPressed=true;
        }
        else if(e.getButton()==3){
            if(level.getChar(e.getX()/15,e.getY()/15)=='w'){
            	clear(new Dimension(e.getX()-(e.getX()%10),e.getY()-(e.getY()%10)));
            	level.placeChar('f',e.getX()/15,e.getY()/15);
            }
            else{
                int x=e.getX()-(e.getX()%15);
                int y=e.getY()-(e.getY()%15);
                clear(new Dimension(x,y));
                clear(new Dimension(x,y-15));
                if(level.getChar(e.getX()/15,e.getY()/15)=='p'){
                    playerPlaced=false;
                 }
                level.placeChar('f',e.getX()/15,e.getY()/15);
                //clear(new Dimension(x,y));
                if(level.getChar(e.getX()/15,e.getY()/15)=='e'){
                	numberOfEnemies--;
            	}
                if(level.getChar(e.getX()/15,e.getY()/15-1)=='w'){
            		drawWall(new Dimension(x,y-15));
            	}
                if(level.getChar(e.getX()/15,e.getY()/15-1)=='p'){
            		drawPlayer(new Dimension(x,y-15));
                        playerPlaced=true;
            	}
                if(level.getChar(e.getX()/15,e.getY()/15-1)=='e'){
            		drawEnemy(new Dimension(x,y-15));
            	}
                if(level.getChar(e.getX()/15,e.getY()/15+1)=='e'){
            		drawEnemy(new Dimension(x,y+15));
            	}
                
            }
            rightButtonPressed=true;
        }
        else if(status==2&&e.getButton()==1&&level.getChar(e.getX()/15,e.getY()/15)=='f'){
        	level.placeChar('p',e.getX()/15,e.getY()/15);
        	drawPlayer(new Dimension(e.getX()-(e.getX()%15),e.getY()-(e.getY()%15)));
                playerPlaced = true ;

        }
        if(status==3&&e.getButton()==1&&level.getChar(e.getX()/15,e.getY()/15)=='f'){
            drawEnemy(new Dimension(e.getX()-(e.getX()%15),e.getY()-(e.getY()%15)));
            level.placeChar('e',e.getX()/15,e.getY()/15);
        }

        drawGrid();
    }

    /**
     * This method is to test that the minimum requirements
     * for the level have been reached.
     *
     * This is 3 enemies and a placed player
     *
     * @return
     */
    
    public boolean hasDrawn(){
    	boolean enoughEnemies=false;
    	boolean hasDrawnPlayer=false;
    	if(numberOfEnemies>3) enoughEnemies = true;
    	if(player!=null)hasDrawnPlayer = true;
    	return (enoughEnemies&&hasDrawnPlayer);
    }

    /**
     * This method draws all objects on board
     */
    
    public void drawAll(){
    	for(int outer=0;outer<16;outer++){
    		for(int inner=0;inner<22;inner++){
    			switch(level.getChar(outer,inner)){
    				case 'f':
    					break;
    				case 'w':
    					drawWall(new Dimension(outer*15,inner*15));
    					break;
    				case 'p':
    					player = new Dimension(outer*15,inner*15);
    					drawPlayer(new Dimension(outer*15,inner*15));
    					break;
    				case 'e':
    					drawEnemy(new Dimension(outer*15,inner*15));
    					break;
    			}
    		}
    	}
    }

    /**
     * This method returns the level associated with
     * the drawing area
     *
     * @return
     */
    public Level getLevel(){
    	return level;
    }

    /**
     * This method sets the level associated with
     * the drawing area
     *
     * The paremter is the level to draw on
     *
     * @param level
     */
    
    public void setLevel(Level level){
    	this.level=level;
    }

    /**
     * This method draws an player at the given coordinates
     *
     * the parameter is a dimension to hold the x and y
     * position of the player to be drawn
     *
     * @param pos
     */

    public void drawPlayer(Dimension pos){
        if(player!=null){
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
        
    }

    /**
     * This method draws an enemy at the given coordinates
     *
     * the parameter is a dimension to hold the x and y
     * position of the enemy to be drawn
     *
     * @param pos
     */

    public void drawEnemy(Dimension pos){
    	if(numberOfEnemies>10)return;
        int x=pos.width;
        int y=pos.height;
        graphic.drawImage(new ImageIcon("images/enemy.png").getImage(), x-(x%15)+5, y-(y%15)+5, this);
        numberOfEnemies++;
        repaint(1);

    }

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
    public void setStatus(int status){
        this.status=status;
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
     * This method deals with mouse button releases.
     *
     * @param e
     */

    public void mouseReleased(MouseEvent e) {
        if(e.getButton()==1){
            leftButtonPressed=false;
        }
        if(e.getButton()==3){
            rightButtonPressed=false;
        }
    }

    //unused methods implemented from interfaces

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {

    }

    public void mouseClicked(MouseEvent e) {

    }




}
