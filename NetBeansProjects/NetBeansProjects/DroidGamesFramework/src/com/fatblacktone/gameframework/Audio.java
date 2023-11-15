/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fatblacktone.gameframework;

/**
 *
 * @author Tone
 */
public interface Audio {
    public Music createMusic(String file);

    public Sound createSound(String file);
}
