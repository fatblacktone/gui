
package simpleshootemup;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import javax.swing.JFrame;

public class SimpleShootEmUp extends JFrame{
    
    private String title;
    
    
    
    
    public SimpleShootEmUp(String toPrint){
        super(toPrint);
        
        
    }
    
    @Override
    public void paint(Graphics g){
        
    }
    
    private void runGameLoop(){
        
    }
    
    public void run(){
        Thread animationThread = new Thread(new Runnable() {

            @Override
            public void run() {
                runGameLoop();
            }
        });
        animationThread.start();
    }
    
    

    public static void main(String[] args) {               
        SimpleShootEmUp simpleShootEmUp = new SimpleShootEmUp("Animation");
        
    }
}
