package simpleshootemup;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class Animation {
  
    private ArrayList<Image> images = new ArrayList<>();
    private File currentImageFile,
                 imageDirectory;
    private int numberOfImages,
                currentImage;
    private double frameLength = 1000000000/20;
    private double lastUpdate;
    
    public Animation(File imageDirectory){
        this.imageDirectory = imageDirectory;        
    }
    
    public boolean loadImages(){
        boolean imagesFound = false;
        numberOfImages = 1;
        String filePrefix = imageDirectory.getPath().split("\\\\")[1];
        currentImageFile = new File(imageDirectory.getPath()+"\\\\"+filePrefix+""+numberOfImages+".png");
        while(currentImageFile.exists()){
            try {
                Image image = ImageIO.read(currentImageFile);
                images.add(image);
            } catch (IOException ex) {
                Logger.getLogger(Animation.class.getName()).log(Level.SEVERE, null, ex);
            }
            numberOfImages++;
            currentImageFile = new File(imageDirectory.getPath()+"\\\\"+filePrefix+""+numberOfImages+".png");
            imagesFound = true;
        }
        lastUpdate = System.nanoTime();
        return imagesFound;
    }
  
    public Image getCurrentImage(double currentTimeInMiliseconds) {
        Image image = null;


        if (currentTimeInMiliseconds - lastUpdate > frameLength) {
            image = images.remove(0);
            images.add(image);
            lastUpdate = currentTimeInMiliseconds;
        }
        return image;
    }
    
    
}
