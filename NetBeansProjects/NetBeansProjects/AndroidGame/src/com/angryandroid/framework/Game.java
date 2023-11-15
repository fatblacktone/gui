/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.angryandroid.framework;

/**
 *
 * @author Fatblack
 */
public interface Game {
    
    /**
     *
     * @return
     */
    public Audio getAudio();

    /**
     *
     * @return
     */
    public Input getInput();

    public FileIO getFileIO();

    public Graphics getGraphics();

    public void setScreen(Screen screen);

    public Screen getCurrentScreen();

    public Screen getInitScreen();
    
}
