/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleplatformgame;

import simpleplatformgame.data.Player;
import simpleplatformgame.gui.GameFrame;

/**
 *
 * @author Fatblack
 */
public class SimplePLatformGame implements Runnable {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SimplePLatformGame spg = new SimplePLatformGame();
        Thread t = new Thread(spg);


        t.start();
    }

    @Override
    public void run() {
        Player player = new Player();
        GameFrame gameFrame = new GameFrame(player);
        while (true) {
            //try {
                gameFrame.repaint();
                //Thread.sleep(17);
            //} catch (InterruptedException e) {
              //  e.printStackTrace();
            //}
        }


    }
}
