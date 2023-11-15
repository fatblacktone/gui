/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fatblacktone.samplegame;

import com.fatblacktone.gameframework.Screen;
import com.fatblacktone.gameframework.implementation.AndroidGame;

public class SampleGame extends AndroidGame {
    @Override
    public Screen getInitScreen() {
        return new LoadingScreen(this); 
    }
    
}
