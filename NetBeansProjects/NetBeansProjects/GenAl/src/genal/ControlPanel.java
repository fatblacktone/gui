/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genal;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;

/**
 *
 * @author Fatblack
 */
public class ControlPanel extends JPanel{
    
     private JSlider population,crossover,mutation;
    private JButton start,pause,stop,reset;
    
    public ControlPanel(){
        this.setBounds(715, 150, 210, 450);
        start = new JButton("Start");
        start.setBounds(715,515,75,30);
        this.add(start);
        pause = new JButton("Pause");
        pause.setBounds(795, 515, 75, 30);
        this.add(pause);
        stop = new JButton("Stop");
        stop.setBounds(875, 515, 75, 30);
        this.add(stop);
        reset = new JButton("Reset");
        reset.setBounds(955, 515, 75, 30);
        this.add(reset);
        population = new JSlider(50, 250);
        population.setBounds(720, 150, 200, 75);
        population.setBorder(BorderFactory.createTitledBorder("population"));
        population.setMajorTickSpacing(100);
        population.setMinorTickSpacing(10);
        population.setPaintTicks(true);
        population.setPaintLabels(true);
        this.add(population);
        crossover = new JSlider(0,100);
        crossover.setBounds(720, 230, 200, 75);
        crossover.setBorder(BorderFactory.createTitledBorder("Crossover"));
        crossover.setMinorTickSpacing(10);
        crossover.setMajorTickSpacing(20);
        crossover.setPaintLabels(true);
        crossover.setPaintTicks(true);
        this.add(crossover);
        mutation = new JSlider(0,100);
        mutation.setBounds(720, 310, 200, 75);
        mutation.setBorder(BorderFactory.createTitledBorder("Mutation"));
        mutation.setMinorTickSpacing(10);
        mutation.setMajorTickSpacing(20);
        mutation.setPaintLabels(true);
        mutation.setPaintTicks(true);
        this.add(mutation);
    }
    
}
