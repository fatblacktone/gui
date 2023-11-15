/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shooting;

import com.angryandroid.framework.GameLoop;

/**
 *
 * @author Fatblack
 */
public class Loop extends GameLoop{
    
    private Display display;
    

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }
    
    
    
    @Override
    public void updateGame(){
        
    }
    
    @Override
    public void drawGame(float currentTimeInNanoSeconds){
        display.repaint();
    }
}
