package backend;

/*
* ImagePanel.java 0.1 30-Jan-2010
*
* Copyright (c) 2010 University of Wales, Aberystwyth.
* All rights reserved.
*
*/

import theExceptions.OutOfRangeException;

/**
 * Level - This class holds all the level information
 *
 * @author Antony Walters
 * @version 0.1
 */

public class Level {

   //Instance variables
   private char[][] theLevel;
   private int numberOfEnemiesOnBoard;
   private boolean playerPlaced;
   private String nameOfLevel;

   public char[][] getTheLevel() {
      return theLevel;
   }

   public void setTheLevel(char[][] theLevel) {
      this.theLevel = theLevel;
   }

   public int getNumberOfEnemies(){
      return numberOfEnemiesOnBoard;
   }

   public boolean isPlayerPlaced(){
      return playerPlaced;
   }

   public int getNumberOfEnemiesOnBoard() {
      return numberOfEnemiesOnBoard;
   }

   public void setNumberOfEnemiesOnBoard(int numberOfEnemiesOnBoard) {
      this.numberOfEnemiesOnBoard = numberOfEnemiesOnBoard;
   }

   public String getNameOfLevel() {
      return nameOfLevel;
   }

   public void setNameOfLevel(String nameOfLevel) {
      this.nameOfLevel = nameOfLevel;
   }

   /**
    * Constructor to create a blank level
    * with no enemies, walls and the
    * player is not placed
    */
   public Level() {
      theLevel = new char[16][22];
      for (int x = 0; x < 16; x++) {
         for (int y = 0; y < 22; y++) {
            theLevel[x][y] = 'f';
         }
      }
      numberOfEnemiesOnBoard = 0;
      playerPlaced = false;
   }

   /**
    * Constructor that creates a random level
    *
    * The parameter is the number of enemies
    * you would like placed on the board
    *
    * @param numberOfEnemiesToPlace
    */
   public Level(int numberOfEnemiesToPlace) {
      theLevel = new char[16][22];
      for (int x = 0; x < 16; x++) {
         for (int y = 0; y < 22; y++) {
            theLevel[x][y] = 'f';
         }
      }
      try {
         createRandomWalls(3);
         placeRandomEnemies(numberOfEnemiesToPlace);
      } catch (OutOfRangeException ex) {
      }

      placePlayer();
   }

   /**
    * This method place a given char at given
    * coordinates
    *
    * The parameters are the char to place and
    * the x and y coordinates of where the char
    * is to be placed
    *
    * @param theChar
    * @param x
    * @param y
    */
   public void placeChar(char theChar, int x, int y) {
      if (theChar == 'e') {
         numberOfEnemiesOnBoard++;
      } else if (theChar == 'p') {
         playerPlaced = true;
      }
      theLevel[x][y] = theChar;
   }

   /**
    * This method returns a char selected by
    * given coordinates
    *
    * The parameters are the x and y coordinates
    * of the selected char
    */
   public char getChar(int x, int y) {
      return theLevel[x][y];
   }

   /**
    * This method places and enemy on the board
    * at the given coordinates
    *
    * The parameters are the x and y position to
    * place the enemy at.
    *
    * @param x
    * @param y
    */
   public void placeEnemy(int x, int y) {
      if (theLevel[x][y] == 'f') {
         theLevel[x][y] = 'e';
      }
      numberOfEnemiesOnBoard++;
   }

   /**
    * This method create random walls on the map
    *
    * The parameters the density of wall placement
    * it has a range of 1 - 5
    *
    * It will throw an OutOfRangeException if out of
    * given range
    *
    * @throws OutOfRangeException
    * @param densityOfWalls
    */
   public void createRandomWalls(int densityOfWalls) throws OutOfRangeException {
      if (densityOfWalls < 3 || densityOfWalls > 10) {
         throw new OutOfRangeException();
      }
      for (int numberOfWalls = 0; numberOfWalls < 5 * densityOfWalls; numberOfWalls++) {
         int x = ((int) (Math.random() * 16));
         int y = ((int) (Math.random() * 22));
         int direction = (1 + (int) (Math.random() * 4));
         theLevel[x][y] = 'w';
         for (int length = 0; length < 4; length++) {
            switch (direction) {
               case 1:
                  if (y > 0) {
                     theLevel[x][y--] = 'w';
                  }
                  break;
               case 2:
                  if (x < 16) {
                     theLevel[x++][y] = 'w';
                  }
                  break;
               case 3:
                  if (y < 22) {
                     theLevel[x][y++] = 'w';
                  }
                  break;
               case 4:
                  if (x > 0) {
                     theLevel[x--][y] = 'w';
                  }
                  break;
            }
         }

      }
   }

   /**
    * This method randomly places a player on the board
    */
   public void placePlayer() {
      while (true) {
         int x = ((int) (Math.random() * 16));
         int y = ((int) (Math.random() * 22));
         if (theLevel[x][y] != 'e' && theLevel[x][y] != 'w') {
            theLevel[x][y] = 'p';
            break;
         }
      }
      playerPlaced = true;
   }

   /**
    * This method places a wall at the given
    * Coordinates
    *
    * The parameters are the x and y coordinates
    * of the piece of wall to be placed
    *
    * @param x
    * @param y
    */
   public void placeWall(int x, int y) {
      if (theLevel[x][y] == 'f') {
         theLevel[x][y] = 'w';
      }
   }

   /**
    * This method places a player at the given
    * coordinates
    *
    * The parameters are the x and y coordinates
    * of the piece of wall to be placed
    *
    * @param x
    * @param y
    */
   public void placePlayer(int x, int y) {
      if (theLevel[x][y] == 'f') {
         theLevel[x][y] = 'p';
         playerPlaced = true;
      }
   }

   /**
    * This method clears the selected coordinates
    *
    * The parameters are the x and y coordinates
    * of the location to be cleared
    *
    * @param x
    * @param y
    */
   public void clear(int x, int y) {
      if (theLevel[x][y] == 'e') {
         numberOfEnemiesOnBoard--;
      } else if (theLevel[x][y] == 'p') {
         playerPlaced = false;
      }
      theLevel[x][y] = 'f';
   }

   /**
    * This method sets the playerPlaced variable
    *
    * the parameter is if the player has been placed yet
    *
    * @param hasPlayerBeenPlaced
    */
   public void setPlayerPlaced(boolean isPlayerPlaced) {
      playerPlaced = isPlayerPlaced;
   }

   /**
    * This method populates the board with
    * randomly placed enemies
    *
    * The parameter is the number of enemies
    * you would like to place on the board
    * within a range of 3 - 10
    *
    * Throws an OutOfRangeException if this
    * range is exceeded
    *
    * @param numberOfEnemiesToPlace
    * @throws OutOfRangeException
    */
   public void placeRandomEnemies(int numberOfEnemiesToPlace) throws OutOfRangeException {
      if (numberOfEnemiesToPlace < 3 || numberOfEnemiesToPlace > 10) {
         throw new OutOfRangeException();
      }
      for (; numberOfEnemiesToPlace > 0; numberOfEnemiesToPlace--) {
         while (true) {
            int x = ((int) (Math.random() * 16));
            int y = ((int) (Math.random() * 22));
            if (theLevel[x][y] != 'p' && theLevel[x][y] != 'w' && theLevel[x][y] != 'e') {
               theLevel[x][y] = 'e';
               break;
            }
         }
      }
   }
}

