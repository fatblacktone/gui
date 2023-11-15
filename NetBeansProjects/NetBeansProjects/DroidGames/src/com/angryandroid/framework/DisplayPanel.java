/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.angryandroid.framework;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author Fatblack
 */
public interface DisplayPanel {
    
    /**
     * This method is used when initialising the game to load the layers images
     * to be used as foreground/background and any layers between
     */
    
    public void loadGameLayerImages();
    
    
    
    /**
     * This method loads the images for the player. 
     */
    
    public void loadPlayerImages();
    
    /**
     * This method loads the images used for the enemy
     */
    
    public void loadEnemyImages();
    
    /**
     * This method loads all the interactable objects for the game
     */
    
    public InteractableLayer[] loadInteractableImages();
    
    /**
     * This method sets the player 
     * 
     * @param player
     */
    
    public void setPlayer(GameCharacter player);
    
    /**
     * This method returns the player
     * 
     */
    
    public GameCharacter getPlayer();
    
    /**
     * This method set the enemies array
     * 
     * @param enemies 
     */
    
    public void setEnemies(ArrayList<GameCharacter> enemies);  
    
    /**
     * This method paints all of the game components
     */
    
    public void paintComponent(Graphics g);
    
    /**
     * This method updates the display before rendering 
     */
    
    public void update();
    
    
    
}
