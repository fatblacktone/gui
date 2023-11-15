/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package artlife;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;

/**
 *
 * @author Fatblack
 */
public class ArtLife {

    private static boolean multiCell;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("");
        frame.setSize(1000, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Panel panel = new Panel();
        frame.setContentPane(panel);
        frame.setVisible(true);
        ArrayList<Food2> food2 = new ArrayList<>();
        ArrayList<Cell> cells2 = new ArrayList<>();

        Random rand = new Random();
        Cell cell = new Cell(50 + rand.nextInt(800), 50 + rand.nextInt(500));
        ArrayList<Cell> cells = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            if (i == 0) {
                cells.add(cell);
                cell.setColor(Color.green);
            } else {
                int count = 1;
                boolean addCell = true;
                for (Cell c : cells) {
                    if (cell.getBoundingPolygon().intersects(c.getBoundingPolygon())) {
                        addCell = false;
                    }
                }
                if (addCell) {

                    cells.add(cell);
                }
            }
            cell = new Cell(50 + rand.nextInt(800), 50 + rand.nextInt(500));
        }
        System.out.print("\n");
        panel.setCells(cells);
        for (Cell c : cells) {
            c.setOthers(cells);
        }
        ArrayList<Food> food = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Food foodItem = new Food(rand.nextInt(800) + 50, rand.nextInt(500) + 50);
            food.add(foodItem);
        }
        panel.setFood(food);
        panel.setFood2(food2);

        if (true) {
            cell = new Cell(50 + rand.nextInt(800), 50 + rand.nextInt(500));
            
            for (int i = 0; i < 50; i++) {
                if (i == 0) {
                    cells2.add(cell);
                    cell.setColor(Color.yellow);
                } else {
                    int count = 1;
                    boolean addCell = true;
                    for (Cell c : cells2) {
                        if (cell.getBoundingPolygon().intersects(c.getBoundingPolygon())) {
                            addCell = false;
                        }
                    }
                    if (addCell) {

                        cells2.add(cell);
                    }
                }
                cell = new Cell(50 + rand.nextInt(800), 50 + rand.nextInt(500));
            }
            System.out.print("\n");
            panel.setCells2(cells2);
            for (Cell c : cells2) {
                c.setOthers(cells2);
            }
        }
        while (true) {
            for (Cell c : cells) {
                c.update();
                for (int i = 0; i < food.size(); i++) {
                    Food f = food.get(i);
                    if (c.getBoundingPolygon().contains(f.getX(), f.getY())) {
                        food.remove(f);
                        c.incrementFoodColected();
                        //Food foodItem = new Food(rand.nextInt(800) + 50, rand.nextInt(500) + 50);
                        //food.add(foodItem);
                    }
                }
                if (c.getFoodInBelly() > 0) {
                    Food2 f2 = new Food2(c.getCenterX(), c.getCenterY());
                    food2.add(f2);
                    c.decrementFoodInBelly();
                }
            }
            for (Cell c : cells2) {
                c.update();
                for (int i = 0; i < food2.size(); i++) {
                    Food2 f = food2.get(i);
                    if (c.getBoundingPolygon().contains(f.getX(), f.getY())) {
                        food2.remove(f);
                        c.incrementFoodColected();
                        //Food2 foodItem = new Food2(rand.nextInt(800) + 50, rand.nextInt(500) + 50);
                        //food2.add(foodItem);
                    }
                }
                if (c.getFoodInBelly() > 0) {
                    Food f = new Food(c.getCenterX(), c.getCenterY());
                    food.add(f);
                    c.decrementFoodInBelly();
                }
                
            }
            panel.repaint();
            try {
                Thread.sleep(5);
            } catch (InterruptedException ex) {
            }
        }


    }
}
