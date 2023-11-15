/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fatblacktone.gameframework;

/**
 *
 * @author Tone
 */
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.SharedPreferences;

public interface FileIO {
    public InputStream readFile(String file) throws IOException;

    public OutputStream writeFile(String file) throws IOException;
    
    public InputStream readAsset(String file) throws IOException;
    
    public SharedPreferences getSharedPref();
}
