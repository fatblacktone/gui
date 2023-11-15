/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dnvms.objects;

import java.util.ArrayList;

/**
 *
 * @author Mathew Keegan
 */
public class Downtime {
    
    private ArrayList<String> start;
    private ArrayList<String> end;
    private ArrayList<String> rNc;

    public Downtime(ArrayList<String> start, ArrayList<String> end, ArrayList<String> rNc) {
        this.start = start;
        this.end = end;
        this.rNc = rNc;
    }

    public ArrayList<String> getEnd() {
        return end;
    }

    public void setEnd(ArrayList<String> end) {
        this.end = end;
    }

    public ArrayList<String> getrNc() {
        return rNc;
    }

    public void setrNc(ArrayList<String> rNc) {
        this.rNc = rNc;
    }

    public ArrayList<String> getStart() {
        return start;
    }

    public void setStart(ArrayList<String> start) {
        this.start = start;
    }

    @Override
    public String toString() {
        return "Downtime{" + "start=" + start + ", end=" + end + ", rNc=" + rNc + '}';
    }

    
    
    
    
}
