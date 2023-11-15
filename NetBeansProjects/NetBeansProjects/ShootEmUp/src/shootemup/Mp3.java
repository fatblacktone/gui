package shootemup;

import javax.media.*;
import java.io.*;
import java.net.URL;

class Mp3 
{

    private URL url;
    private MediaLocator mediaLocator;
    private Player playMP3;

    public Mp3(String mp3) {
        play();
    }
    private Player player;
    private static final String PLAY = "PLAY";
    private static final String STOP = "STOP";

    public void play() {
        try{
            File f = new File("./sounds/chopper.mp3");
            MediaLocator ml = new MediaLocator(f.toURL());
            Player p = Manager.createPlayer(ml);
            p.start();
        }catch(Exception ex){
            
        }
    }
}