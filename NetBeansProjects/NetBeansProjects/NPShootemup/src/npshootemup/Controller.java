/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package npshootemup;

/**
 *
 * @author Fatblack
 */
public class Controller {
    
    private double lastUpdateInMiliseconds,
                   lastRenderInMiliseconds;
    
    
    
    private final double GAME_HERTZ = 30.0;
    private final double TIME_BETWEEN_UPDATES = 1000000000 / GAME_HERTZ;
    private final int MAX_UPDATES_BEFORE_RENDER = 60;
    private final double TARGET_FPS = 60;
    final double TARGET_TIME_BETWEEN_RENDERS = 1000000000 / TARGET_FPS;
      
    
    private int fps;
    private int frameCount;
    
    private boolean running = true,
                    paused;
    
    private DisplayPanel display = new DisplayPanel();
    
    
       
    private void runGameLoop(){ 
        
      lastUpdateInMiliseconds = System.nanoTime();
      lastRenderInMiliseconds = System.nanoTime();      
      
      int lastSecondTime = (int) (lastUpdateInMiliseconds / 1000000000);
      
      while (running)
      {
         double now = System.nanoTime();
            int updateCount = 0;
            if (!paused) {
                //Do as many game updates as we need to, potentially playing catchup.
                while (now - lastUpdateInMiliseconds > TIME_BETWEEN_UPDATES && updateCount < MAX_UPDATES_BEFORE_RENDER) {
                    updateGame(now-lastUpdateInMiliseconds);
                    lastUpdateInMiliseconds += TIME_BETWEEN_UPDATES;
                    updateCount++;
                }
                //If for some reason an update takes forever, we don't want to do an insane number of catchups.
                //If you were doing some sort of game that needed to keep EXACT time, you would get rid of this.
                if (now - lastUpdateInMiliseconds > TIME_BETWEEN_UPDATES) {
                    lastUpdateInMiliseconds = now - TIME_BETWEEN_UPDATES;
                }

                //Render. To do so, we need to calculate interpolation for a smooth render.
                float interpolation = Math.min(1.0f, (float) ((now - lastUpdateInMiliseconds) / TIME_BETWEEN_UPDATES));
                drawGame(interpolation);
                lastRenderInMiliseconds = now;

                //Update the frames we got.
                int thisSecond = (int) (lastUpdateInMiliseconds / 1000000000);
                if (thisSecond > lastSecondTime) {
                    //System.out.println("NEW SECOND " + thisSecond + " " + frameCount);
                    fps = frameCount;
                    
                    frameCount = 0;
                    lastSecondTime = thisSecond;
                }

                //Yield until it has been at least the target time between renders. This saves the CPU from hogging.
                while (now - lastRenderInMiliseconds < TARGET_TIME_BETWEEN_RENDERS && now - lastUpdateInMiliseconds < TIME_BETWEEN_UPDATES) {
                    Thread.yield();

                    //This stops the app from consuming all your CPU. It makes this slightly less accurate, but is worth it.
                    //You can remove this line and it will still work (better), your CPU just climbs on certain OSes.
                    //FYI on some OS's this can cause pretty bad stuttering. Scroll down and have a look at different peoples' solutions to this.
                    try {
                        Thread.sleep(1);
                    } catch (Exception e) {
                    }

                    now = System.nanoTime();
                }
            }
      }
    }

    

    private void updateGame(double time) {
        display.update(time);
    }
    
    private void drawGame(double interpolation) {
        display.repaint();
        frameCount++;
    }
    
    /**
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final Controller c = new Controller();
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                c.runGameLoop();
            }
        });
        t.start();
    }

    
                 
         
}
