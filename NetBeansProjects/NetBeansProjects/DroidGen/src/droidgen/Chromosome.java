/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package droidgen;

import java.util.ArrayList;

/**
 *
 * @author Fatblack
 */
public class Chromosome {
    
    private ArrayList<Gene> genes;
    private int numberOfGenes;
    private String chromosomeString;
    private String chromosomeStringGeneSeperated;

    public Chromosome() {
    }

    public ArrayList<Gene> getGenes() {
        return genes;
    }

    public void setGenes(ArrayList<Gene> genes) {
        this.genes = genes;
    }

    public int getNumberOfGenes() {
        return numberOfGenes;
    }

    public void setNumberOfGenes(int numberOfGenes) {
        this.numberOfGenes = numberOfGenes;
    }

    public String getChromosomeString() {
        return chromosomeString;
    }

    public void setChromosomeString(String chromosomeString) {
        this.chromosomeString = chromosomeString;
    }

    public String getChromosomeStringGeneSeperated() {
        return chromosomeStringGeneSeperated;
    }

    public void setChromosomeStringGeneSeperated(String chromosomeStringGeneSeperated) {
        this.chromosomeStringGeneSeperated = chromosomeStringGeneSeperated;
    }
    
    
    
}
