/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genal;

import com.angryandroid.framework.GameLoop;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

/**
 *
 * @author Fatblack
 */
public class Loop extends GameLoop {

    private Panel panel;
    private ArrayList<Ameba> amebas;
    private int updateCount;
    private ArrayList<Dimension> food;
    private boolean[][] foodPlacements;
    private Ameba elite;
    private boolean random;

    public boolean[][] getFoodPlacements() {
        return foodPlacements;
    }

    public void setFoodPlacements(boolean[][] foodPlacements) {
        this.foodPlacements = foodPlacements;
    }

    public Loop(Panel panel, ArrayList<Ameba> amebas) {
        this.panel = panel;
        this.amebas = amebas;
    }

    public Panel getPanel() {
        return panel;
    }

    public ArrayList<Ameba> getAmebas() {
        return amebas;
    }

    public void setAmebas(ArrayList<Ameba> amebas) {
        this.amebas = amebas;
    }

    public ArrayList<Dimension> getFood() {
        return food;
    }

    public void setFood(ArrayList<Dimension> food) {
        this.food = food;
    }

    public void setPanel(Panel panel) {
        this.panel = panel;
    }

    @Override
    public void updateGame() {
        updateCount++;
        int current = 0;
        
        for (int i=0;i<amebas.size();i++) {
            boolean canMove = true;
            
            Ameba a = amebas.get(i);
            a.setOthers(amebas);
            Ameba b = null;
            for (int inner=0;inner<amebas.size();inner++){
                b = amebas.get(inner);
                if(b.getBoundingPolygon().intersects(a.getBoundingPolygon())){
                    if (i != inner) {
                        //canMove = false;
                    }
                }
            }
            if (canMove) {
                a.update();
            } else {
                System.out.println(" not moved");
            }
            //System.out.append("fsdjlk");
        }
        for (Ameba a : amebas) {
            Dimension d = null;
            for (int i = 0; i < food.size(); i++) {
                d = food.get(i);
                if (a.getBoundingPolygon().contains(d.width, d.height)) {
                    int column = (d.width - 20) / 20;
                    int row = (d.height - 20) / 20;
                    foodPlacements[column][row] = false;
                    a.increamentFood();
                    food.remove(i);

                    Random rand = new Random();

                    if (!random) {
                        boolean foodPlaced = false;
                        while (!foodPlaced) {
                            row = rand.nextInt(25);
                            column = rand.nextInt(35);
                            //System.out.println(column+"  "+row+" "+foodPlacements[column][row]);
                            if (!foodPlacements[column][row]) {
                                foodPlacements[column][row] = true;
                                d = new Dimension(column * 20 + 20, row * 20 + 20);
                                food.add(d);
                                //System.out.println("Added");
                                foodPlaced = true;
                            } else {
                                // System.out.println("Already taken");
                            }


                        }
                    }else {
                        
                        d = new Dimension(rand.nextInt(680)+20, rand.nextInt(480)+20);
                        food.add(d);
                    }

                }
            }
        }
        if (updateCount > 1500) {
            updatePopulation();
            //changePopulation();
            updateCount = 0;
        }
        if (updateCount % 100 > 0) {
            // System.err.println(updateCount);
        }
        panel.setUpdate(updateCount);
    }

    @Override
    public void drawGame(float currentTimeInNanoSeconds) {
        panel.repaint();
    }
    
    public void updatePopulation(){
        
        Object old[] = amebas.toArray();

        Arrays.sort(old);
        Ameba am = (Ameba) old[0];
        panel.setBest(am.getFood());
        ArrayList<Ameba> oldPopulation = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            Ameba a = (Ameba) old[i];
            oldPopulation.add(a);
            //System.out.println(a.getChromosome() + "  " + a.getFood());
        }
        for (int i = 0; i < 200; i++) {
            Ameba a = (Ameba) oldPopulation.get(i);
            System.out.println(i+": :"+a.getChildren()+": :"+a.getChromosome() + ":  :" + a.getFood());
        }
        oldPopulation.remove(199);
        oldPopulation.remove(198);
        int sum = 0;
        for (int i = 0; i < 198; i++) {
            sum += oldPopulation.get(i).getFood();
        }
        panel.addAverage(sum / 198);
        Random rand = new Random();
        for (int in = 0; in < 2; in++) {
            Ameba firstParent = null;
            int score = rand.nextInt(sum);
            int sum2 = 0;
            for (int i = 0; i < 198; i++) {
                sum2 += oldPopulation.get(i).getFood();
                if (sum2 > score) {
                    firstParent = oldPopulation.get(i);
                    break;
                }
            }
            Ameba secondParent = null;
            score = rand.nextInt(sum);
            sum2 = 0;
            for (int i = 0; i < 198; i++) {
                sum2 += oldPopulation.get(i).getFood();
                if (sum2 > score) {
                    secondParent = oldPopulation.get(i);
                    break;
                }
            }

            firstParent.incrementChildren();
            secondParent.incrementChildren();
            Ameba child = null;
            String childChromosome = "";
            String firstParentChromosome = firstParent.getChromosome();
            String secondParentChromosome = secondParent.getChromosome();
            for (int i = 0; i < 20; i++) {
                if (i<80) {
                    childChromosome += firstParentChromosome.charAt(i);
                } else {
                    childChromosome += secondParentChromosome.charAt(i);
                }
            }
            char chars[] = childChromosome.toCharArray();
            for (int i = 0; i < 20; i++) {
                if (rand.nextInt(100) > 80) {
                    
                    chars[i] = (rand.nextInt(9)+"").charAt(0);
                }
            }
            childChromosome = new String(chars);
            for (int i = 0; i < 1; i++) {
                child = new Ameba(in*25+10, 4*50+10, childChromosome);
                boolean placeFound = false;
                for (Ameba a : amebas) {
                    if(!a.equals(child)){
                        if (!a.getBoundingPolygon().intersects(child.getBoundingPolygon())) {
                            placeFound = true;
                        }
                    }
                }
                if (placeFound) {
                        oldPopulation.add(child);
                } else {
                    i--;
                }
            }
            amebas = oldPopulation;
            panel.setAmebas(amebas);
        }
        
        for(Ameba a:amebas){
            a.setFood(0);
        }
        
    }

    public void changePopulation() {
        ArrayList<Ameba> newPopulation = new ArrayList<>();
        Object old[] = amebas.toArray();

        Arrays.sort(old);
        Ameba am = (Ameba) old[0];
        panel.setBest(am.getFood());
        ArrayList<Ameba> oldPopulation = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            Ameba a = (Ameba) old[i];
            oldPopulation.add(a);
            //System.out.println(a.getChromosome() + "  " + a.getFood());
        }
        //System.out.println();
        //System.out.println();
        System.out.println(oldPopulation.get(0).getFood());
        int sum = 0;
        for (int i = 0; i < 200; i++) {
            sum += oldPopulation.get(i).getFood();
        }
        panel.addAverage(sum /200);
        System.out.println(sum /200);
        if(elite==null){
            elite = new Ameba(oldPopulation.get(0).getX(), oldPopulation.get(0).getY(), oldPopulation.get(0).getChromosome());
        }
        if(oldPopulation.get(0).getFood()>elite.getFood()){
            elite = new Ameba(oldPopulation.get(0).getX(), oldPopulation.get(0).getY(), oldPopulation.get(0).getChromosome());
        }
        //Ameba newA = new Ameba(oldPopulation.get(0).getX(), oldPopulation.get(0).getY(), oldPopulation.get(0).getChromosome());
        //newPopulation.add(newA);
        Random rand = new Random();

        for (int in = 0; in < 20; in++) {
            for(int inner=0;inner<10;inner++){
                Ameba firstParent = null;
            int score = rand.nextInt(sum);
            int sum2 = 0;
            for (int i = 0; i < 200; i++) {
                sum2 += oldPopulation.get(i).getFood();
                if (sum2 > score) {
                    firstParent = oldPopulation.get(i);
                    break;
                }
            }

            Ameba secondParent = null;
            score = rand.nextInt(sum);
            sum2 = 0;
            for (int i = 0; i < 200; i++) {
                sum2 += oldPopulation.get(i).getFood();
                if (sum2 > score) {
                    secondParent = oldPopulation.get(i);
                    break;
                }
            }

            firstParent.incrementChildren();
            secondParent.incrementChildren();
            Ameba child = null;
            String childChromosome = "";
            String firstParentChromosome = firstParent.getChromosome();
            String secondParentChromosome = secondParent.getChromosome();

            for (int i = 0; i < 20; i++) {
                if (i<80) {
                    childChromosome += firstParentChromosome.charAt(i);
                } else {
                    childChromosome += secondParentChromosome.charAt(i);
                }
            }
            char chars[] = childChromosome.toCharArray();
            for (int i = 0; i < 20; i++) {
                if (rand.nextInt(100) > 80) {
                    
                    chars[i] = (rand.nextInt(9)+"").charAt(0);
                }
            }
            childChromosome = new String(chars);
            
            for (int i = 0; i < 1; i++) {
                child = new Ameba(in*35+10, inner*50+10, childChromosome);
                boolean placeFound = false;
                for (Ameba a : amebas) {
                    if(!a.equals(child)){
                        if (!a.getBoundingPolygon().intersects(child.getBoundingPolygon())) {
                            placeFound = true;
                        }
                    }
                }
                if (i == 0) {
                    placeFound = true;
                }
                if (placeFound) {
                    if(inner!=0){
                        amebas.add(child);
                    }else{
                        amebas.add(new Ameba(elite.getX(), elite.getY(), elite.getChromosome()));
                    }
                    
                } else {
                    i--;
                }
            }

            
            newPopulation.add(child);
            }
        }
        
        amebas = newPopulation;
        panel.setAmebas(amebas);
        panel.setGeneration(panel.getGeneration() + 1);

        for (int i = 0; i < 200; i++) {
            Ameba a = (Ameba) old[i];
            System.out.println(i+": :"+a.getChildren()+": :"+a.getChromosome() + ":  :" + a.getFood());
        }
        if (random) {
            for (int i = 0; i < 800; i++) {


                Dimension d = new Dimension(rand.nextInt(680) + 20, rand.nextInt(480) + 20);
                food.add(d);


            }
        }
    }
}
