/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jamp.media;

import jamp.gui.Frame;
import java.io.File;
import java.net.URL;

import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.EndOfMediaEvent;
import javax.media.GainChangeEvent;
import javax.media.GainChangeListener;
import javax.media.GainControl;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.media.Time;

/**
 *
 * @author Tone (anw8@aber.ac.uk)
 */
public class SoundFile extends Thread implements GainChangeListener{

    private URL url;
    private MediaLocator mediaLocator;
    private Player playMP3;
    private Time trackLength;
    private Frame theFrame;
    private boolean shouldDisplay;
    private Thread update;

    public SoundFile(String mp3, int in) {
        try {
            this.url = new URL(mp3);
        } catch (java.net.MalformedURLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void setTheFrame(Frame theFrame){
        this.theFrame = theFrame;
    }

    public GainControl getGainControl(){
        return playMP3.getGainControl();
    }

    @Override
    public void run() {
        shouldDisplay = true;
        try {
            mediaLocator = new MediaLocator(url);
            playMP3 = Manager.createPlayer(mediaLocator);

        } catch (java.io.IOException e) {
            System.out.println(e.getMessage());
        } catch (javax.media.NoPlayerException e) {
            System.out.println(e.getMessage());
        }

        playMP3.addControllerListener(new ControllerListener() {

            public void controllerUpdate(ControllerEvent e) {
                if (e instanceof EndOfMediaEvent) {
                    playMP3.stop();
                    playMP3.close();
                    theFrame.setPlaying(false);
                    shouldDisplay = false;
                }
            }
        });
        playMP3.realize();
        try{
            Thread.sleep(500);
        }catch(InterruptedException ex){}
        //GainControl g=playMP3.getGainControl();
        //g.setLevel(theFrame.getVolume());
        //theFrame.addGain(g);
        playMP3.start();
        try{
                            Thread.sleep(50);
        }catch(InterruptedException ex){}
        theFrame.setTrackLength((int) playMP3.getDuration().getSeconds());
        update = new Thread(new Runnable() {

                public void run() {
                    while(shouldDisplay){
                        theFrame.setDisplay(new File(url.getFile()).getName()+" ("+getTrackLength()+")",
                        getTimeElapsed(),(int) playMP3.getMediaTime().getSeconds());
                        try{
                            Thread.sleep(500);
                        }catch(InterruptedException ex){}
                    }
                }

            });
        update.start();
        
    }

    public int getPlayerState() {
        int state = playMP3.getState();
        return state;
    }

    public void stopToAdjust(){
        //playMP3.stop();
        update.suspend();
    }

    public void setTrackTime(int trackTime){
        Time t = new Time(trackTime);
        playMP3.setMediaTime(t);

    }

    public void play(){
        
        playMP3.start();
        update.resume();
    }

    public void setTrackLength() {
        trackLength = playMP3.getStopTime();//getDuration();
    }

    public String getTimeElapsed(){
        int time = (int) playMP3.getMediaTime().getSeconds();
        int m = time/60, s = time%60;
        
        String seconds = (s<10)?("0"+s):""+s;

        return m + ":" + seconds;
    }

    public String getTrackLength() {
        int time = (int) playMP3.getDuration().getSeconds();
        int m = time/60, s = time%60;
        String seconds = (s<10)?("0"+s):""+s;

        return m + ":" + seconds;
    }

    public void stopPlay() {
        shouldDisplay=false;
        playMP3.stop();
        playMP3.close();
    }

    public void gainChange(GainChangeEvent gce) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
