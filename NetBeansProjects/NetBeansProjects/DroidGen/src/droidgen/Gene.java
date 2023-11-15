/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package droidgen;

/**
 *
 * @author Fatblack
 */
public class Gene {
    
    private int length;
    private String geneAsBinaryString;

    public Gene(int length, String geneAsBinaryString) {
        this.length = length;
        this.geneAsBinaryString = geneAsBinaryString;
    }

    /**
     *
     * @return
     */
    public int getLength() {
        return length;
    }

    /**
     *
     * @param length
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     *
     * @return
     */
    public String getGeneAsBinaryString() {
        return geneAsBinaryString;
    }

    /**
     *
     * @param geneAsBinaryString
     */
    public void setGeneAsBinaryString(String geneAsBinaryString) {
        this.geneAsBinaryString = geneAsBinaryString;
    }
    
    
    
    
    
}
