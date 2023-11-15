/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fatblacktone.samplegame;

import com.fatblacktone.gameframework.Game;
import com.fatblacktone.gameframework.Graphics;
import com.fatblacktone.gameframework.Screen;
import com.fatblacktone.gameframework.Graphics.ImageFormat;


public class LoadingScreen extends Screen {
    public LoadingScreen(Game game) {
        super(game);
    }


    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        Assets.menu = g.newImage("menu.png", ImageFormat.RGB565);
        Assets.click = game.getAudio().createSound("bomb.wav");


        
        game.setScreen(new MainMenuScreen(game));


    }


    @Override
    public void paint(float deltaTime) {


    }


    @Override
    public void pause() {


    }


    @Override
    public void resume() {


    }


    @Override
    public void dispose() {


    }


    @Override
    public void backButton() {


    }
}