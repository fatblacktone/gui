/*
 * TheFrame.java 0.1 28-Jan-2010
 *
 * Copyright (c) 2010 University of Wales, Aberystwyth.
 * All rights reserved.
 *
 */
package GuiComponents;

import backend.FileManipulation;


import backend.Level;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 * TheFrame - The frame containing the GUI.
 *
 * This class will build and control the GUI
 *
 * @author Antony Walters
 * @version 0.1
 * @see (ref to related classes)
 */
public class TheFrame implements MouseMotionListener, MouseListener {

   private JFrame theFrame;
   private JLayeredPane contentPane;
   private boolean mousePressed;
   private int mousePressedAtX, mousePressedAtY;
   private ImagePanel drawingArea;
   private JLabel label;
   private JTextArea helpText;
   private JTextField nameInput;
   private FileManipulation fileReader = new FileManipulation();
   private boolean hasLoadedFromPC;
   private LevelSelectionPanel pcLevels;
   private static final int WIDTH = 640, HEIGHT = 480;
   private JLabel button3, button2, button1, button4;

   /**
    * Constructor that creates the gui
    */
   public TheFrame() {
      createFrame();
   }

   public void drawAll() {
      drawingArea.drawAll();
   }

   public void createFrame() {
      if (theFrame != null) {
         theFrame.dispose();
      }
      theFrame = new JFrame();
      theFrame.setSize(WIDTH, HEIGHT);
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      theFrame.setLocation((int) (screenSize.width / 2) - (theFrame.getWidth() / 2), (int) (screenSize.height / 2) - (theFrame.getHeight() / 2));
      theFrame.setUndecorated(true);
      theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      createContentPane();
      createAndAddButtons();
      fileReader.loadLevelsFromPC();
      
      hasLoadedFromPC = true;
      theFrame.setVisible(true);
   }

   private void createContentPane() {
      contentPane = new JLayeredPane();
      contentPane.addMouseListener(this);
      contentPane.addMouseMotionListener(this);
      label = new JLabel(new ImageIcon("images/pcBuilderBackground.jpg"));
      label.setVerticalAlignment(JLabel.TOP);
      label.setHorizontalAlignment(JLabel.CENTER);
      label.setOpaque(true);
      label.setBackground(Color.red);
      label.setForeground(Color.black);
      label.setBorder(BorderFactory.createLineBorder(Color.black));
      label.setBounds(0, 0, WIDTH, HEIGHT);
      contentPane.add(label);
      theFrame.add(contentPane);
   }

   private void createAndAddButtons() {
      ImageIcon icon = new ImageIcon("images/exit.png");
      button4 = new JLabel(icon);
      button4.setName("Exit");
      button4.addMouseListener(this);
      button4.setOpaque(true);
      button4.setBackground(Color.lightGray);
      button4.setForeground(Color.black);
      button4.setBounds(142, 340, 353, 54);
      contentPane.add(button4, 1, 0);

      icon = new ImageIcon("images/newlevel.png");
      button3 = new JLabel(icon);
      button3.setName("New Level");
      button3.addMouseListener(this);
      button3.setOpaque(true);
      button3.setBackground(Color.lightGray);
      button3.setForeground(Color.black);
      button3.setBounds(145, 100, 353, 54);
      contentPane.add(button3, 1, 0);

      icon = new ImageIcon("images/editlevel.png");
      button2 = new JLabel(icon);
      button2.setName("Edit Level");
      button2.addMouseListener(this);
      button2.setOpaque(true);
      button2.setBackground(Color.lightGray);
      button2.setForeground(Color.black);
      button2.setBounds(142, 174, 353, 54);
      contentPane.add(button2, 1, 0);

      icon = new ImageIcon("images/managelevels.png");
      button1 = new JLabel(icon);
      button1.setName("Manage Levels");
      button1.addMouseListener(this);
      button1.setOpaque(true);
      button1.setBackground(Color.lightGray);
      button1.setForeground(Color.black);
      button1.setBounds(142, 260, 353, 54);
      contentPane.add(button1, 1, 0);

   }

   public void startEditor() {
	   
      if (pcLevels != null) {
         if (pcLevels.isVisible()) {
            pcLevels.setVisible(false);
         }
      }
      drawingArea = new ImagePanel();
      drawingArea.setOpaque(true);
      drawingArea.setBackground(Color.white);
      drawingArea.setForeground(Color.black);
      drawingArea.setBorder(BorderFactory.createLineBorder(Color.black));
      drawingArea.setBounds(90, 90, 250, 340);
      contentPane.add(drawingArea, 1, 0);
      drawingArea.setUp();
      drawingArea.drawGrid();
      drawingArea.setStatus(1);

      if (button4.isVisible()) {
         button4.setVisible(false);
      }
      if (!button3.isVisible()) {
         button3.setVisible(true);
      }
      button3.setBounds(520, 434, 99, 31);
      ImageIcon icon = new ImageIcon("images/cancel.png");
      button3.setIcon(icon);
      button3.setName("Cancel");

      if (!button2.isVisible()) {
         button2.setVisible(true);
      }
      button2.setBounds(415, 434, 98, 31);
      icon = new ImageIcon("images/next.png");
      button2.setIcon(icon);
      button2.setName("Next");

      if (!button1.isVisible()) {
         button1.setVisible(true);
      }
      button1.setBounds(310, 434, 99, 31);
      icon = new ImageIcon("images/backplain.png");
      //button1.setText("Back");
      button1.setIcon(icon);
      button1.setName("Back");

      helpText = new JTextArea();
      helpText.setBounds(380, 95, 175, 320);
      helpText.setBackground(Color.LIGHT_GRAY);
      helpText.setText("\n\nTo draw wall use the left mouse\nbutton\n\n" +
              "to remove wall use right mouse\nbutton");
      contentPane.add(helpText, 1, 0);

   }

   private void manageLevels() {
      fileReader.setTheFrame(this);
      
      
      fileReader.loadLevelsFromPhone();
      
      if (drawingArea != null) {
         drawingArea.setVisible(false);
      }
      if (button4.isVisible()) {
         button4.setVisible(false);
         button4 = null;
      }
      button3.setBounds(520, 434, 99, 31);
      ImageIcon icon = new ImageIcon("images/cancel.png");
      button3.setIcon(icon);
      button3.setName("Cancel Manage");

      button2.setBounds(415, 434, 98, 31);
      button2.setName("Save");
      icon = new ImageIcon("images/save.png");
      button2.setIcon(icon);

      if (!button1.isVisible()) {
         button1.setVisible(true);
      }
      button1.setBounds(310, 434, 99, 31);
      icon = new ImageIcon("images/del.png");
      button1.setIcon(icon);
      button1.setName("Delete");

      TransferPanel tp = new TransferPanel();
      tp.setBounds(40, 70, 340, 350);
      tp.setFileReader(fileReader);
      tp.setup();
      contentPane.add(tp, 1, 0);
      helpText = new JTextArea();
      helpText.setBounds(430, 80, 175, 320);
      helpText.setBackground(Color.LIGHT_GRAY);
      helpText.setText("To re-order the levels drag\n" +
              "with the left mouse button.\n\n" +
              "The left panel is levels stored\n" +
              "on the pc the right panel are\n" +
              "levels stored on the phone.");
      contentPane.add(helpText, 1, 0);
   }

   private void editLevel() {
      if (button4 != null) {
         button4.setVisible(false);
      }
      
      fileReader.loadLevelsFromPhone();
      pcLevels = new LevelSelectionPanel();
      pcLevels.setParent(this);
      pcLevels.setBounds(40, 70, 220, 350);
      pcLevels.loadLabels(fileReader.getLevelsFromPc());
      contentPane.add(pcLevels, 1, 0);
      button3.setBounds(520, 434, 100, 30);
      ImageIcon icon = new ImageIcon("images/cancel.png");
      button3.setIcon(icon);
      button3.setName("Cancel");
      button2.setVisible(false);
      button1.setVisible(false);

   }

   public void loadLevelIntoDrawingArea(int count) {
      Level foundLevel = fileReader.getLevelFromThePc(count);

      drawingArea.setLevel(foundLevel);
   }

   private void addLevelToPcArray() {
      drawingArea.getLevel().setNameOfLevel(nameInput.getText());
      fileReader.getLevelsFromPc().add(drawingArea.getLevel());
   }

   private void promptForName() {

      drawingArea.setVisible(false);
      nameInput = new JTextField("Eneter name here");
      nameInput.setBounds(130, 220, 200, 30);
      nameInput.setBackground(Color.LIGHT_GRAY);
      contentPane.add(nameInput, 1, 0);

   }

   public void mousePressed(MouseEvent e) {
      if (e.getSource() == button1 && button1.getName().equals("Back")) {
         ImageIcon icon = new ImageIcon("images/backPressed.png");
         button1.setBounds(310, 434, 96, 34);
         button1.setIcon(icon);
      }
      if (e.getSource() == button1 && button1.getName().equals("Delete")) {
         ImageIcon icon = new ImageIcon("images/delpressed.png");
         button1.setBounds(310, 434, 99, 31);
         button1.setIcon(icon);
      } else if (e.getSource() == button3 && button3.getName().equals("New Level")) {
         ImageIcon icon = new ImageIcon("images/newlevelpressed.png");
         button3.setBounds(145, 100, 353, 53);
         button3.setIcon(icon);
      } else if (e.getSource() == button2 && button2.getName().equals("Edit Level")) {
         ImageIcon icon = new ImageIcon("images/editlevelpressed.png");
         button2.setBounds(145, 180, 353, 54);
         button2.setIcon(icon);
      } else if (e.getSource() == button1 && button1.getName().equals("Manage Levels")) {
         ImageIcon icon = new ImageIcon("images/managelevelspressed.png");
         button1.setBounds(142, 260, 353, 54);
         button1.setIcon(icon);

      } else if (e.getSource() == button4 && button4.getName().equals("Exit")) {
         ImageIcon icon = new ImageIcon("images/exitpressed.png");
         button4.setBounds(142, 340, 353, 54);
         button4.setIcon(icon);
      } else if (e.getSource() == button2 && button2.getName().equals("Next")) {
         ImageIcon icon = new ImageIcon("images/nextpressed.png");
         button2.setBounds(415, 434, 98, 31);
         button2.setIcon(icon);
      } else if (e.getSource() == button3 && button3.getName().equals("Cancel")) {
         ImageIcon icon = new ImageIcon("images/cancelpressed.png");
         button3.setBounds(520, 434, 99, 31);
         button3.setIcon(icon);
      } else if (e.getSource() == button3 && button3.getName().equals("Cancel Manage")) {
         ImageIcon icon = new ImageIcon("images/cancelpressed.png");
         button3.setBounds(520, 434, 99, 31);
         button3.setIcon(icon);
      } else if (e.getSource() == button2 && button2.getName().equals("Save")) {
         ImageIcon icon = new ImageIcon("images/savepressed.png");
         button2.setBounds(415, 434, 98, 31);
         button2.setIcon(icon);
      }
      mousePressed = true;
      mousePressedAtX = e.getX();
      mousePressedAtY = e.getY();
   }

   public void mouseReleased(MouseEvent e) {

      if (e.getSource() == button1 && button1.getName().equals("Back")) {
         ImageIcon icon = new ImageIcon("images/backHighlight.png");
         button1.setIcon(icon);
      }
      if (e.getSource() == button3 && button3.getName().equals("New Level")) {
         ImageIcon icon = new ImageIcon("images/newlevelhighlight.png");
         button3.setIcon(icon);
      }
      if (e.getSource() == button1 && button1.getName().equals("Delete")) {
         ImageIcon icon = new ImageIcon("images/delhighlight.png");
         button1.setBounds(310, 434, 99, 31);
         button1.setIcon(icon);
      } else if (e.getSource() == button2 && button2.getName().equals("Edit Level")) {
         ImageIcon icon = new ImageIcon("images/editlevelhighlight.png");
         button2.setBounds(142, 174, 353, 54);
         button2.setIcon(icon);
      } else if (e.getSource() == button1 && button1.getName().equals("Manage Levels")) {
         ImageIcon icon = new ImageIcon("images/managelevelshighlighted.png");
         button1.setBounds(142, 260, 353, 54);
         button1.setIcon(icon);
      } else if (e.getSource() == button4 && button4.getName().equals("Exit")) {
         ImageIcon icon = new ImageIcon("images/exithighlight.png");
         button4.setBounds(142, 340, 353, 54);
         button4.setIcon(icon);
      } else if (e.getSource() == button2 && button2.getName().equals("Next")) {
         ImageIcon icon = new ImageIcon("images/nexthighlight.png");
         button2.setBounds(415, 434, 98, 31);
         button2.setIcon(icon);
      } else if (e.getSource() == button3 && button3.getName().equals("Cancel")) {
         ImageIcon icon = new ImageIcon("images/cancelhighlight.png");
         button3.setBounds(520, 434, 99, 31);
         button3.setIcon(icon);
      } else if (e.getSource() == button3 && button3.getName().equals("Cancel Manage")) {
         ImageIcon icon = new ImageIcon("images/cancelhighlight.png");
         button3.setBounds(520, 434, 99, 31);
         button3.setIcon(icon);
      } else if (e.getSource() == button2 && button2.getName().equals("Save")) {
         ImageIcon icon = new ImageIcon("images/savehighlight.png");
         button2.setBounds(415, 434, 98, 31);
         button2.setIcon(icon);
      }



      mousePressed = false;
   }

   public void mouseEntered(MouseEvent e) {
      if (e.getSource() == button1 && button1.getName().equals("Back")) {
         ImageIcon icon = new ImageIcon("images/backHighlight.png");
         button1.setIcon(icon);
      }
      if (e.getSource() == button3 && button3.getName().equals("New Level")) {
         ImageIcon icon = new ImageIcon("images/newlevelhighlight.png");
         button3.setIcon(icon);
      } else if (e.getSource() == button2 && button2.getName().equals("Edit Level")) {
         ImageIcon icon = new ImageIcon("images/editlevelhighlight.png");
         button2.setBounds(142, 174, 353, 54);
         button2.setIcon(icon);
      } else if (e.getSource() == button1 && button1.getName().equals("Manage Levels")) {
         ImageIcon icon = new ImageIcon("images/managelevelshighlighted.png");
         button1.setBounds(142, 260, 353, 54);
         button1.setIcon(icon);
      }
      if (e.getSource() == button1 && button1.getName().equals("Delete")) {
         ImageIcon icon = new ImageIcon("images/delhighlight.png");
         button1.setBounds(310, 434, 99, 31);
         button1.setIcon(icon);
      } else if (e.getSource() == button4 && button4.getName().equals("Exit")) {
         ImageIcon icon = new ImageIcon("images/exithighlight.png");
         button4.setBounds(142, 340, 353, 54);
         button4.setIcon(icon);
      } else if (e.getSource() == button2 && button2.getName().equals("Next")) {
         ImageIcon icon = new ImageIcon("images/nexthighlight.png");
         button2.setBounds(415, 434, 98, 31);
         button2.setIcon(icon);
      } else if (e.getSource() == button3 && button3.getName().equals("Cancel")) {
         ImageIcon icon = new ImageIcon("images/cancelhighlight.png");
         button3.setBounds(520, 434, 99, 31);
         button3.setIcon(icon);
      } else if (e.getSource() == button3 && button3.getName().equals("Cancel Manage")) {
         ImageIcon icon = new ImageIcon("images/cancelhighlight.png");
         button3.setBounds(520, 434, 99, 31);
         button3.setIcon(icon);
      } else if (e.getSource() == button2 && button2.getName().equals("Save")) {
         ImageIcon icon = new ImageIcon("images/savehighlight.png");
         button2.setBounds(415, 434, 98, 31);
         button2.setIcon(icon);
      }
   }

   public void mouseExited(MouseEvent e) {
      if (e.getSource() == button1 && button1.getName().equals("Back")) {
         ImageIcon icon = new ImageIcon("images/backplain.png");
         button1.setIcon(icon);
      } else if (e.getSource() == button3 && button3.getName().equals("New Level")) {
         ImageIcon icon = new ImageIcon("images/newlevel.png");
         button3.setIcon(icon);
      }
      if (e.getSource() == button1 && button1.getName().equals("Delete")) {
         ImageIcon icon = new ImageIcon("images/del.png");
         button1.setBounds(310, 434, 99, 31);
         button1.setIcon(icon);
      } else if (e.getSource() == button2 && button2.getName().equals("Edit Level")) {
         ImageIcon icon = new ImageIcon("images/editlevel.png");
         button2.setBounds(142, 174, 353, 54);
         button2.setIcon(icon);
      } else if (e.getSource() == button1 && button1.getName().equals("Manage Levels")) {
         ImageIcon icon = new ImageIcon("images/managelevels.png");
         button1.setBounds(142, 260, 353, 54);
         button1.setIcon(icon);
      } else if (e.getSource() == button2 && button2.getName().equals("Save")) {
         ImageIcon icon = new ImageIcon("images/save.png");
         button2.setBounds(415, 434, 98, 31);
         button2.setIcon(icon);
      } else if (e.getSource() == button4 && button4.getName().equals("Exit")) {
         ImageIcon icon = new ImageIcon("images/exit.png");
         button4.setBounds(142, 340, 353, 54);
         button4.setIcon(icon);
      } else if (e.getSource() == button2 && button2.getName().equals("Next")) {
         ImageIcon icon = new ImageIcon("images/next.png");
         button2.setBounds(415, 434, 98, 31);
         button2.setIcon(icon);
      } else if (e.getSource() == button3 && button3.getName().equals("Cancel")) {
         ImageIcon icon = new ImageIcon("images/cancel.png");
         button3.setBounds(520, 434, 99, 31);
         button3.setIcon(icon);
      } else if (e.getSource() == button3 && button3.getName().equals("Cancel Manage")) {
         ImageIcon icon = new ImageIcon("images/cancel.png");
         button3.setBounds(520, 434, 99, 31);
         button3.setIcon(icon);
      }
   }

   public void mouseDragged(MouseEvent e) {
      if (mousePressed) {
         theFrame.setLocation(e.getXOnScreen() - mousePressedAtX, e.getYOnScreen() - mousePressedAtY);
      }
   }

   public void mouseMoved(MouseEvent e) {
   }

   public void mouseClicked(MouseEvent e) {
      if (e.getSource() == contentPane && e.getButton() == 3) {
         fileReader.savePcLevelsToTextFile();
         System.exit(0);
      } else if (e.getSource() == button4) {
         fileReader.savePcLevelsToTextFile();
         System.exit(0);
      } else if (e.getSource() == button3 && button3.getName().equals("New Level")) {
         startEditor();
      } else if (e.getSource() == button3 && button3.getName().equals("Cancel")) {
         createFrame();
      } else if (e.getSource() == button3 && button3.getName().equals("Cancel Manage")) {
         createFrame();
      } else if (e.getSource() == button1 && button1.getName().equals("Manage Levels")) {
         manageLevels();
      } else if (e.getSource() == button2 && button2.getName().equals("Edit Level")) {
         editLevel();
      } else if (e.getSource() == button2 && button2.getName().equals("Save")) {
         fileReader.savePcLevelsToTextFile();
         fileReader.savePhoneLevelsToTextFile();
         createFrame();
      }
      if (e.getSource() == button1 && button1.getName().equals("Delete")) {
         ImageIcon icon = new ImageIcon("images/del.png");
         button1.setBounds(310, 434, 99, 31);
         button1.setIcon(icon);
      } else if (e.getSource() == button1 && button1.getName().equals("Back")) {
         switch (drawingArea.getStatus()) {
            case 1:
               theFrame.setVisible(false);
               createFrame();
               break;
            case 2:
               drawingArea.setStatus(1);
               helpText.setText("\n\nTo draw wall use the left mouse\nbutton\n\n" +
                       "to remove wall use right mouse\nbutton");
               break;
            case 3:
               drawingArea.setStatus(2);
               helpText.setText("\n\nTo draw Player use the left mouse\nbutton\n\n" +
                       "to remove player use right mouse\nbutton");
               break;
            case 4:
               drawingArea.setStatus(3);
               helpText.setText("\n\nTo draw enemy use the left mouse\nbutton\n\n" +
                       "to remove enemy use right mouse\nbutton");
               drawingArea.setVisible(true);
               nameInput.setVisible(false);
               break;
         }
      } else if (e.getSource() == button2 && button2.getName().equals("Next")) {
         switch (drawingArea.getStatus()) {
            case 1:
               drawingArea.setStatus(2);
               helpText.setText("\n\nTo draw Player use the left mouse\nbutton\n\n" +
                       "to remove player use right mouse\nbutton");
               break;
            case 2:
               drawingArea.setStatus(3);
               helpText.setText("\n\nTo draw enemy use the left mouse\nbutton\n\n" +
                       "to remove enemy use right mouse\nbutton");

               break;
            case 3:
               if (drawingArea.getLevel().getNumberOfEnemies() < 3) {
                  JOptionPane.showMessageDialog(null, "You need to add more enemies. min = 3");
                  return;
               }
               if (!drawingArea.getLevel().isPlayerPlaced()) {
                  JOptionPane.showMessageDialog(null, "You need to add a player.");
                  return;
               }
               drawingArea.setStatus(4);
               helpText.setText("\n\nplease enter a name for your\nlevel into the text box");
               promptForName();
               break;
            case 4:
               //if(drawingArea.hasDrawn()){
               addLevelToPcArray();
               helpText.setVisible(false);
               nameInput.setVisible(false);
               manageLevels();
               //}
               //prompt for more input!!
               break;
         }
      }

   }
}
