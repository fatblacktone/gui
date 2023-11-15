/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package droidcopterapplet.sound;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;



/**
 *
 * @author Fatblack
 */
public class SoundPlayer implements Runnable{
    
    private Clip gunClip;
    private Clip chopper;
    private Clip boomClip;
    private File boomFile = new File("./sounds/boom.wav");
    private File gunFile = new File("./sounds/gun.wav");
    
    
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
            chopper.loop(200);
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
            
            
            AudioInputStream sound = AudioSystem.getAudioInputStream(gunFile);

		DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
		gunClip = (Clip) AudioSystem.getLine(info);
		gunClip.open(sound);
                gunClip.loop(50);
                    sound.close();

		gunClip.addLineListener(new LineListener(){
                    @Override
			public void update(LineEvent event){
				if(event.getType() == LineEvent.Type.STOP){
					event.getLine().close();
				}
			}

               
		});

		gunClip.start();
        } catch (Exception e) {
            //whatevers
        }
    }
    
    public void playBoom() {
        try {
            
           // AudioInputStream stream;
           // AudioFormat format;
           // DataLine.Info info;
            

          //  stream = AudioSystem.getAudioInputStream(boomFile);
          //  format = stream.getFormat();
          //  info = new DataLine.Info(Clip.class, format);
          //  boomClip = (Clip) AudioSystem.getLine(info);
          //  boomClip.open(stream);
          //  boomClip.start();  
            
            
            AudioInputStream sound = AudioSystem.getAudioInputStream(boomFile);

		DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
		Clip clip = (Clip) AudioSystem.getLine(info);
		clip.open(sound);
                    sound.close();

		clip.addLineListener(new LineListener(){
                    @Override
			public void update(LineEvent event){
				if(event.getType() == LineEvent.Type.STOP){
					event.getLine().close();
				}
			}

               
		});

		clip.start();
           
        } catch (Exception e) {
            //whatevers
        }
    }
    
    public void stopGun(){
        if(gunClip!=null){
            gunClip.close();
            gunClip = null;
        }
    }

    @Override
    public void run() {
        playChopper();
        
    }
    
}
