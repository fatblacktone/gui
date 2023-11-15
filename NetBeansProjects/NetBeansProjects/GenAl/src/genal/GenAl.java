/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genal;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Fatblack
 */
public class GenAl {
    private static boolean random;

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Frame frame = new Frame();
        Panel panel = new Panel();
        ArrayList<Dimension> food = new ArrayList<>();
        frame.setContentPane(panel);
        boolean[][] foodPlacements = new boolean[35][25];
        for(int outer=0;outer<35;outer++){
            for(int inner=0;inner<25;inner++){
                foodPlacements[outer][inner] = false;
            }
        }
        
        Random rand = new Random();
        String chromosome = "";
        ArrayList<Ameba> amebas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            for (int inner = 0; inner < 10; inner++) {
                chromosome = "";
                for (int in = 0; in < 20; in++) {
                    chromosome += rand.nextInt(9);
                }
                Ameba ameba = new Ameba(i * 35 + 10, inner * 50 + 10, chromosome);

                amebas.add(ameba);

                System.out.println(i + ":  :" + chromosome);
            }
        }
        //random = true;
        for (int i = 0; i < 200; i++) {
            if (!random) {
                int row = rand.nextInt(25);
                int column = rand.nextInt(35);

                if (!foodPlacements[column][row]) {
                    foodPlacements[column][row] = true;
                    Dimension d = new Dimension(column * 20 + 20, row * 20 + 20);
                    food.add(d);
                } else {
                    i--;
                }
            }else if(random){
                Dimension d = new Dimension(rand.nextInt(680)+20, rand.nextInt(480)+20);
                    food.add(d);
            }

        }

        panel.setAmebas(amebas);
        panel.setFood(food);
        
        Loop loop = new Loop(panel, amebas);
        ControlPanel cp = new ControlPanel();
        //panel.add(cp,3);
        loop.setFoodPlacements(foodPlacements);
        loop.setFood(food);
        loop.runGameLoop();
    }
}
