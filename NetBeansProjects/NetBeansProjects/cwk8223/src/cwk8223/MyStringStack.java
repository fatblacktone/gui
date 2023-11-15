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
public class MyStringStack implements StringStack{
    
    private ArrayList<String> theStack = new ArrayList<>();

    
    /**
     * This method add a String to the top of the stack
     * 
     * @param toAdd 
     */
    @Override
    public void push(String toAdd) {
        theStack.add(toAdd);
    }

    /**
     * This method removes and returns the top String from the stack.
     * 
     * @return 
     */
    @Override
    public String pop() {
        return theStack.remove(theStack.size()-1);
    }
    
    /**
     * This method returns the string at the top of the stack but does not remove
     * it.
     * 
     * @return 
     */

    @Override
    public String peek() {
        return theStack.get(theStack.size()-1);
    }
    
    /**
     * This method Returns the size of the stack.
     * 
     * @return 
     */

    @Override
    public int getSize() {
        return theStack.size();
    }
    
}
