/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fatblacktone.gameframework.implementation;

import java.util.List;

import android.view.View.OnTouchListener;

import com.fatblacktone.gameframework.Input.TouchEvent;

public interface TouchHandler extends OnTouchListener {
    public boolean isTouchDown(int pointer);
    
    public int getTouchX(int pointer);
    
    public int getTouchY(int pointer);
    
    public List<TouchEvent> getTouchEvents();
}
 
