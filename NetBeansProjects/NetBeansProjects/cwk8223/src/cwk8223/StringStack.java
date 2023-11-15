package cwk8223;

/**
 * Interface to be implemented by a class that stores a stack of Strings.
 * Do not modify this file.
 * @author David Sutton
 */
public interface StringStack {
    /**
     * Pushes a String onto the stack
     * @param s String to be pushed on to the stack
     */
    public void push(String s);
    /**
     * Removes the String at the top of this stack and returns 
     * that String.
     * 
     * @return The String at the top of this stack
     */
    public String pop();
    
    /**
     * Looks at the String at the top of this stack without 
     * removing it from the stack.
     * 
     * @return The String at the top of the Stack.
     * 
     */
    public String peek();
    
    /**
     * Get the size of this stack.
     * @return The number of Strings on the stack.
     */
    public int getSize();
}
