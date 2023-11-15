/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package droidcopter.sound;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;



/**
 *
 * @author Fatblack
 */
public class SoundPlayer implements Runnable{
    
    private static Clip gunClip;
    private static Clip chopper;
    
    
    
    public void playChopper() {
        

        
        
        try {
            File yourFile = new File("./sounds/chopper.wav");
            AudioInputStream stream;
            AudioFormat format;
            DataLine.Info info;
            stream = AudioSystem.getAudioInputStream(yourFile);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            chopper = (Clip) AudioSystem.getLine(info);
            chopper.open(stream);
            chopper.start();
            chopper.loop(20);
        } catch (Exception e) {
            //whatevers
        }
    }
    
    public void stopChopper(){
        if(chopper!=null){
            chopper.close();
        }
    }
    
   
    
    public void playGun() {
        try {
            File yourFile = new File("./sounds/gun.wav");
            AudioInputStream stream;
            AudioFormat format;
            DataLine.Info info;
            

            stream = AudioSystem.getAudioInputStream(yourFile);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            gunClip = (Clip) AudioSystem.getLine(info);
            gunClip.open(stream);
            gunClip.start();
            gunClip.loop(20);
        } catch (Exception e) {
            //whatevers
        }
    }
    
    public void playBoom() {
        try {
            File yourFile = new File("./sounds/boom.wav");
            AudioInputStream stream;
            AudioFormat format;
            DataLine.Info info;
            Clip clip;

            stream = AudioSystem.getAudioInputStream(yourFile);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
            clip.start();            
        } catch (Exception e) {
            //whatevers
        }
    }
    
    public void stopGun(){
        if(gunClip!=null){
            gunClip.close();
        }
    }

    @Override
    public void run() {
        playChopper();
        
    }
    
}
