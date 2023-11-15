/*
* LevelSelectionPanel.java 0.1 31-Jan-2010
*
* Copyright (c) 2010 University of Wales, Aberystwyth.
* All rights reserved.
*
*/

package GuiComponents;

import backend.Level;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.util.LinkedList;

/**
 * LevelSelectionPanel - This class creates a panel that contains
 * labels that represent the levels.
 *
 * @author Antony Walters
 * @version 0.1
 * @see (ref to related classes)
 */
public class LevelSelectionPanel extends JPanel implements MouseListener{

	
   //Instance variables
   private JLayeredPane layeredPane;
   private JLabel[] names;
   private int state=0;
   private TheFrame theFrame;
   private int numberOfNames;
   private int buttonMoved;
   private int mousePressedAtY;
   private int[] buttonPositions;
   private boolean shouldMoveButtonUp=true, shouldMoveButtonDown=true;
   
   //constants
   private static final int WIDTH = 220,
                            HEIGHT = 350,
                            NUMBER_OF_LEVELS = 25,
                            BUTTON_HEIGHT = 20;
   
   
   /**
    * Constructor 
    */
   public LevelSelectionPanel(){      
      layeredPane = new JLayeredPane();
      layeredPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));      
      names = new JLabel[NUMBER_OF_LEVELS];
      buttonPositions = new int[NUMBER_OF_LEVELS];
      this.add(layeredPane);
   }
   
   /**
    * This method sets the parent frame for this class
    * 
    * @param theFrame
    */
   public void setParent(TheFrame theFrame){
	   this.theFrame=theFrame;
   }

   /**
    * This method loads the labels that represent the levels
    * 
    * @param levels
    * @param names
    * @param numberOfNames
    */
   public void loadLabels(LinkedList<Level> levels){
	   this.numberOfNames=levels.size();
	  for(int count=0;count<numberOfNames;count++){
    	  this.names[count]=new JLabel("             "+levels.get(count).getNameOfLevel());
    	  this.names[count].setBounds(0, 0+(count*BUTTON_HEIGHT), WIDTH, BUTTON_HEIGHT);
    	  this.names[count].setBorder(BorderFactory.createLineBorder(Color.black));
    	  this.names[count].addMouseListener(this);
    	  buttonPositions[count]=count;
    	  layeredPane.add(this.names[count],0);          
      }      
      
   }
   
   /**
    * This method deals with mouse clicks when
    * in editing state
    */

   public void mouseClicked(MouseEvent e) {
	   if(state==0){
		   int count=0;
		   for(count=0;count<numberOfNames;count++){
			   if(e.getSource()==names[count]){
				   break;
			   }
		   }
		   theFrame.startEditor();
		   theFrame.loadLevelIntoDrawingArea(count);
		   theFrame.drawAll();
	   }	   
   }   
   
   //Un-used methods from implemented interfaces
   
   /**
    * UNUSED
    */
   
   public void mouseReleased(MouseEvent e) {

   }

   /**
    * UNUSED
    */
   
   public void mouseEntered(MouseEvent e) {

   }
   
   /**
    * UNUSED
    */

   public void mouseExited(MouseEvent e) {

   }

   public void mousePressed(MouseEvent e) {

   }



}
