/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cwk8223;

import java.util.ArrayList;

/**
 *
 * @author Fatblack
 */
public class MyStringQueue implements StringQueue{
    
    private ArrayList<String> theQueue = new ArrayList<>();

    /**
     * This method adds a String to the end of the queue
     * 
     * @param toAdd 
     */
    
    @Override
    public void add(String toAdd) {
        theQueue.add(toAdd);
    }
    
    /**
     * This method removes and returns the head of the queue.
     * 
     * @return 
     */

    @Override
    public String remove() {
        return theQueue.remove(0);
    }
    
    /**
     * This method returns the element at the head of the queue but does
     * not remove it.
     * 
     * @return 
     */

    @Override
    public String peek() {
        return theQueue.get(0);
    }
    
    /**
     * This method returns the size of the queue.
     * @return 
     */

    @Override
    public int getSize() {
        return theQueue.size();
    }
    
}
