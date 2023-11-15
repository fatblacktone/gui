/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fatblacktone.gameframework.implementation;

import android.graphics.Bitmap;

import com.fatblacktone.gameframework.Image;
import com.fatblacktone.gameframework.Graphics.ImageFormat;

public class AndroidImage implements Image {
    Bitmap bitmap;
    ImageFormat format;
    
    public AndroidImage(Bitmap bitmap, ImageFormat format) {
        this.bitmap = bitmap;
        this.format = format;
    }

    @Override
    public int getWidth() {
        return bitmap.getWidth();
    }

    @Override
    public int getHeight() {
        return bitmap.getHeight();
    }

    @Override
    public ImageFormat getFormat() {
        return format;
    }

    @Override
    public void dispose() {
        bitmap.recycle();
    }      
}