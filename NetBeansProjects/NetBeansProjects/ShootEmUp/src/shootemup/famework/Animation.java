/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shootemup.famework;

import java.awt.Image;
import java.util.ArrayList;

/**
 *
 * @author Fatblack
 */
public class Animation {

    private ArrayList frames;
    private int currentFrame;
    private long animTime;
    private long totalDuration;

    public Animation() {
        frames = new ArrayList();
        totalDuration = 0;


        synchronized (this) {
            animTime = 0;
            currentFrame = 0;
        }
    }

    public synchronized void addFrame(Image image, long duration) {
        totalDuration += duration;
        frames.add(new AnimFrame(image, totalDuration));
    }

    public synchronized void update(long elapsedTime,boolean runOnce) {
        if (frames.size() > 1) {
            animTime += elapsedTime;
            if (animTime >= totalDuration) {
                if(runOnce){
                    return;
                }
                animTime = animTime % totalDuration;
                currentFrame = 0;

            }

            while (animTime > getFrame(currentFrame).endTime) {
                currentFrame++;

            }
        }
    }

    public synchronized Image getImage() {
        if (frames.size() == 0) {
            return null;
        } else {
            return getFrame(currentFrame).image;
        }
    }

    private AnimFrame getFrame(int i) {
        return (AnimFrame) frames.get(i);
    }

    private class AnimFrame {

        Image image;
        long endTime;

        public AnimFrame(Image image, long endTime) {
            this.image = image;
            this.endTime = endTime;
        }
    }
    
    public void resetAnim(){
        animTime = 0;
        currentFrame = 0;
    }
    
    public int getCurrentFrame(){
        return currentFrame;
    }
}
