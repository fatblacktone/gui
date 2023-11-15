/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.angryandroid.framework;

import android.content.SharedPreferences;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author Fatblack
 */
public interface FileIO {
    public InputStream readFile(String file) throws IOException;

    public OutputStream writeFile(String file) throws IOException;
   
    public InputStream readAsset(String file) throws IOException;
   
    public SharedPreferences getSharedPref();
    
}
