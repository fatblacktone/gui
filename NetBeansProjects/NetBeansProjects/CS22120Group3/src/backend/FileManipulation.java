/*
* FileManipluation.java 0.1 31-Jan-2010
*
* Copyright (c) 2010 University of Wales, Aberystwyth.
* All rights reserved.
*
*/

package backend;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import GuiComponents.TheFrame;
import backend.Level;

/**
 * FileManipluation - This class handle all input and output to text files.
 * these store the level information.
 *
 * @author Antony Walters
 * @version 0.1
 */
public class FileManipulation {
	
   //Instance variables

   
   
   private TheFrame theFrame;
   private LinkedList<Level> levelsOnThePc,levelsOnThePhone;
   
   private final int NUMBER_OF_LEVELS = 25;

   /**
    * Constructor creates an instance of this class
    */
   
   public FileManipulation(){
      levelsOnThePc = new LinkedList<Level>();
      levelsOnThePhone = new LinkedList<Level>();            
   }
   
   public void clearLists(){
	   levelsOnThePc.clear();
	   levelsOnThePhone.clear(); 
   }
   
   /**
    * This method returns the levels stored on the PC
    * 
    * Returns an array of Level.java.
    * 
    * @return LevelsOnThePc
    */

   public LinkedList<Level> getLevelsFromPc(){
      return levelsOnThePc;
   }
   
   /**
    * This method returns the levels stored on the Phone
    * 
    * Returns an array of Level.java.
    * 
    * @return levelsOnThePhone
    */

   public LinkedList<Level> getLevelsFromPhone(){
      return levelsOnThePhone;
   }
   
   /**
    * This method sets the frame that created it
    * 
    * The parameter is the parent of the instance
    * 
    * @param theFrame
    */
   
   public void setTheFrame(TheFrame theFrame){
	   this.theFrame=theFrame;
   }
   
     
   
   /**
    * This method returns the selected level for editing purposes
    * does not remove the element
    * 
    * The parameter is the index of the level to be selected
    * 
    * @param indexOfLevel
    * @return 
    */
   
   public Level getLevelFromThePc(int indexOfLevel){
	   return levelsOnThePc.get(indexOfLevel);
   }

   /**
    * This method loads the levels from a plain text file
    * 
    * the format is name of levels then 22 rows of
    * 16 char of the following format
    * 
    * f = free
    * p = player
    * e = enemy
    * w = wall
    * 
    * followed by a blank line
    */
   public void loadLevelsFromPC(){ 
	   levelsOnThePc.clear();
	   BufferedReader reader=null;
	      try{
	         reader = new BufferedReader(new FileReader("C:\\PCLevels.txt"));
	      } catch (Exception ex) {
	         createRandomLevels();
	         return;
	      }
	      try{
	         String temp="";
	         int lineNumber=0;
	         int levelCount=0;
	         while((temp=reader.readLine())!=null){
	            if(lineNumber==0){
	               Level level= new Level();
	               level.setNameOfLevel(temp);
	               levelsOnThePc.add(level);               
	               lineNumber++;
	               continue;
	            }
	            if(temp.equals("")){
	               lineNumber=0;
	               continue;
	            }
	            for(int i=0;i<temp.length();i++){
	            	Level level = levelsOnThePc.get(levelCount);
	            	level.placeChar(temp.charAt(i), i, lineNumber-1);
	            }
	            lineNumber++;
	         }
	      }
	      catch(IOException ex){

	      }

   }

   public void addToPhoneLevels(LinkedList<Level> levels, int insertionPoint){
      for(int i = 0;i < levels.size();i++){
         levelsOnThePhone.add(insertionPoint++, levels.get(i));
      }
   }

   public void addToPcLevels(LinkedList<Level> levels, int insertionPoint){
      for(int i = 0;i < levels.size();i++){
         levelsOnThePc.add(insertionPoint++, levels.get(i));
      }
   }

   /**
    * This method create a random number of levels
    *
    */

   private void createRandomLevels(){

      for(int i=0;i<15;i++){
         Level level =new Level(5);
         level.setNameOfLevel("Level "+i);
         levelsOnThePc.add(level);

      }

   }
   
   /**
    * This method saves the PC levels in plain text
    * 
    * the format is name of levels then 22 rows of
    * 16 char of the following format
    * 
    * f = free
    * p = player
    * e = enemy
    * w = wall
    * 
    * followed by a blank line
    */
   
   public void savePcLevelsToTextFile(){

      File myFile = new File ("M:\\PCLevels.txt");
      
      PrintWriter myStream=null;
      try {
         myStream = new PrintWriter(new BufferedOutputStream(new FileOutputStream(myFile)));

    
        
        for(int numberOfLevels = 0; numberOfLevels < levelsOnThePc.size(); numberOfLevels++){
           if(numberOfLevels>0)myStream.write("\n");
            try {
               myStream.write(levelsOnThePc.get(numberOfLevels).getNameOfLevel()+"\n");
            } catch (Exception ex) {
               System.out.println("Error");
            }
            for(int x=0;x<22;x++){
               StringBuffer temp = new StringBuffer(35);
               for(int y=0;y<16;y++){
            	  
                  temp.append(levelsOnThePc.get(numberOfLevels).getChar(y, x));
               }
               myStream.write(temp.toString()+"\n");
            }
         
         }
         
        myStream.flush();       
        myStream.close();
        } catch (Exception ex) {
         System.out.println("Error");
      }


       
   }
   
   /**
    * This method is called when cancelling loading 
    * return user to main menu
    */
   
   private void returnToMenu(){
	   theFrame.createFrame();
   }

   /**
    * This method loads the levels from the Phone
    * if default location is empty, it prompts the user 
    * for the files location
    * 
    * * the format is name of levels then 22 rows of
    * 16 char of the following format
    * 
    * f = free
    * p = player
    * e = enemy
    * w = wall
    * 
    * followed by a blank line
    * 
    */

   public int loadLevelsFromPhone(){
	   levelsOnThePhone.clear();
	   BufferedReader reader=null;
	   
	   File file=new File("M:\\PhoneLevels.txt");
	   if(!file.exists()){
		   JOptionPane.showMessageDialog(null, "Please select the text file containing the levels stored on the phone");
		   JFileChooser fileChooser = new JFileChooser();
		   fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		   fileChooser.setCurrentDirectory(new File("M:"));
		   int result = fileChooser.showOpenDialog(null);
	
		   if ( result == JFileChooser.CANCEL_OPTION ){
			   returnToMenu();
			   return -1;
		   }
		   else{
		      file =  fileChooser.getSelectedFile();
		   }      
	   }
	   
	   try{
	         reader = new BufferedReader(new FileReader(file));
	      } catch (Exception ex) {
	    	  System.out.println("Error");
	      }
	   try{
	      String temp="";
	      int lineNumber=0;
	      int levelCount=0;
	      Level level = null;
	      while((temp=reader.readLine())!=null){
                 if(lineNumber==0){
                    level=new Level();
                    level.setNameOfLevel("Level "+levelCount);	            
                    lineNumber++;
	            continue;
	         }
	         if(lineNumber==22){
	            lineNumber=0;
	            levelsOnThePhone.add(level);
	            levelCount++;
	            continue;
	         }
	         for(int i=0;i<temp.length();i++){               
	            level.placeChar(temp.charAt(i), i,lineNumber-1);
	         }
	         lineNumber++;
	      }
	   }
	   catch(IOException ex){
System.out.println("Error");
	   }
      try {
         reader.close();
      } catch (IOException ex) {
         Logger.getLogger(FileManipulation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      }
      return 0;
   }
   
   /**
    * This method saves the Phone levels
    *
    * the format is name of levels then 22 rows of
    * 16 char of the following format
    * 
    * f = free
    * p = player
    * e = enemy
    * w = wall
    * 
    * followed by a blank line
    */
   
   public void savePhoneLevelsToTextFile(){

      File myFile = new File ("M:\\PhoneLevels.txt");
      if(myFile==null){
    	  JFileChooser fileChooser = new JFileChooser();
          fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
          fileChooser.setCurrentDirectory(new File("C:"));
          int result = fileChooser.showOpenDialog(null);

           if ( result == JFileChooser.CANCEL_OPTION )return;
           else{
              File dir =  fileChooser.getSelectedFile();
              myFile=new File(dir.getAbsolutePath()+"PhoneLevels.txt");
           }      
           
    	  
      }
      PrintWriter myStream=null;
      try {
         myStream = new PrintWriter(new BufferedOutputStream(new FileOutputStream(myFile)));

        System.out.println("Writing to the file now....");
        
        for(int numberOfLevels = 0; numberOfLevels < levelsOnThePhone.size(); numberOfLevels++){
            for(int x=0;x<22;x++){
               StringBuffer temp = new StringBuffer(35);
               for(int y=0;y<16;y++){
                  temp.append(levelsOnThePhone.get(numberOfLevels).getChar(y, x));
               }
               myStream.write(temp.toString()+"\n");
            }         
         }
         
        myStream.flush();       // make sure that all is written
        myStream.close();
        } catch (Exception ex) {
         System.out.println("Error 2 "+ex.toString());
      }


       
   }
   
   /**
    * This method finds the index of a PC level by name
    *
    * The parameter is the name of the level the find
    *
    * @param name
    * @return
    */

   public int findPcLevelIndex(String name){
	   int i;
	   for(i=0;i<levelsOnThePc.size();i++){
		  if(levelsOnThePc.get(i).getNameOfLevel().equals(name))return i;
	   }
	   return -1;
   }

   /**
    * This method finds the index of a Phone level by name
    *
    * The parameter is the name of the level the find
    *
    * @param name
    * @return
    */

   public int findPhoneLevelIndex(String name){
	   int i;
	   for(i=0;i<levelsOnThePhone.size();i++){
		  if(levelsOnThePhone.get(i).getNameOfLevel().equals(name))return i;
	   }
           System.out.println("Level not found");
	   return -1;
   }

   /**
    * This method removes the level selected from the pc
    *
    * The parameter is the levels index
    *
    * @param level
    */
   
   public void removePcLevel(int level){
	   levelsOnThePc.remove(level);
   }

   /**
    * This method removes the level selected from the phone
    *
    * The parameter is the levels index
    *
    * @param level
    */
   public void removePhoneLevel(int level){
      levelsOnThePhone.remove(level);
   }

   /**
    * This method adds the given level to the levels
    * dtored on the PC
    *
    * the parameter is the level to add
    *
    * @param theLevelToAdd
    */

   public void addLevelToArray(Level theLevelToAdd){
      levelsOnThePc.add(theLevelToAdd);
   }
   
   /**
    * This method returns and removes the leve from the phone
    * selected by the given index.
    *
    * The parameter is the levels index
    *
    * @param level
    */

   public Level getLevelFromPhone(int level){
      Level temp = levelsOnThePhone.get(level);
      if(temp == null)System.out.println("ERROR");
      levelsOnThePhone.remove(level);
      return temp;
   }

   /**
    * This method returns and removes the leve from the PC
    * selected by the given index.
    *
    * The parameter is the levels index
    *
    * @param level
    */

   public Level getLevelFromPc(int level){
      Level temp = levelsOnThePc.get(level);
      levelsOnThePc.remove(level);
      return temp;
   }

   /**
    * This method
    */

   public void setLevelsOnPc(LinkedList<Level> levels){
      levelsOnThePc = levels;
   }

   public void setLevelsOnPhone(LinkedList<Level> levels){
      levelsOnThePhone = levels;
   }
}
