/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package actions;

import java.io.File;
import java.io.IOException;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;
import org.sikuli.script.ScreenImage;

/**
 *
 * @author tone_
 */
public class Action {

    private Screen screen = new Screen();
    private int screenCaps = 0;
    
    /**
     * 
     */
    public Action(){
        super();
    }
    
    /**
     * 
     * @param toType 
     */
    public void type(String toType){
        screen.type(toType);
    }
    
    /**
     * 
     */
    public void hover(String filePath){
        try{
            screen.hover(filePath);
        } catch (FindFailed ex) {            
            screenCap("FindFailed" + screenCaps++);
        }
    }
    
    public void screenCap(String filename) {
        ScreenImage img = screen.capture();
        String tempFilename = img.getFile();
        File temp = new File(tempFilename);        
    }
}
