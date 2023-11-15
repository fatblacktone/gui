/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author Fatblack
 */
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Tilemap {

    private int rows,columns;
    private Image image;
    private char[][] tilemap;
    private int xPosition=-6;
    
	

	public Tilemap(int rows,int columns) {
		tilemap = new char[columns][rows];

		System.out.println("New Tilemap created.");
		Random r = new Random(); 
		this.rows = rows;
                this.columns = columns;
		loadImages();
                createTilemap(this);
	}
        
        private void loadImages(){
        try {
            image = ImageIO.read(new File("./images/tiles/standard.png"));
        } catch (IOException ex) {
            Logger.getLogger(Tilemap.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        }
        
        public void createTilemap(Tilemap tilemap){
            for (int i = 0; i < columns; i++) {
			for (int j = 0; j < rows; j++) {
				if(i==0||i==11){
                                    tilemap.set(i,j,'3');
                                  
                                }else if(i<3&&i>0){
                                    Random ran = new Random();
                                    switch(ran.nextInt(2)){
                                        case 0:
                                            if(tilemap.get(i-1, j)=='3'){
                                                tilemap.set(i,j,'3');
                                            }
                                            break;
                                        case 1:
                                            tilemap.set(i,j,' ');
                                            break;
                                    }
                                }
                                //tilemap[i][j] = r.nextInt(5);
				
			}
			
		}
        
        }

	public void printTiles(Tilemap tilemap,Graphics g) {
		for (int i = 0; i < columns; i++) {
			for (int j = 0; j < rows; j++) {
				
                                  
                                if(tilemap.get(i, j)=='3'){ 
                                    g.drawImage(image,(j*40)-xPosition , i*40, null);
                                }
                                
                                //tilemap[i][j] = r.nextInt(5);
				
			}
			
		}
	}

    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition += xPosition;
    }

        
    private void set(int i, int j, char c) {
        tilemap[i][j] = c;
    }

    private char get(int i, int j) {
        return tilemap[i][j];
    }
	
}