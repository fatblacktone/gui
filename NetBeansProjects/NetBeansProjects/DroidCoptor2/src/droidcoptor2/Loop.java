/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package droidcoptor2;

import com.angryandroid.framework.GameLoop;

/**
 *
 * @author Tone
 */
public class Loop extends GameLoop{
    private final Display display;
    
    public Loop(Display display) {
        this.display = display;
        
    }

    @Override
    public void updateGame() {
        display.update();
    }
}
