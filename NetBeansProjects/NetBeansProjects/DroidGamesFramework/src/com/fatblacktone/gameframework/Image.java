/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fatblacktone.gameframework;

import com.fatblacktone.gameframework.Graphics.ImageFormat;

/**
 *
 * @author Tone
 */
public interface Image {
    public int getWidth();
    public int getHeight();
    public ImageFormat getFormat();
    public void dispose();
}
