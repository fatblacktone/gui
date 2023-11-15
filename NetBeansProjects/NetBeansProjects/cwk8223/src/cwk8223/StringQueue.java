package cwk8223;

/**
 * Interface to be implemented by a class that stores a queue of Strings.
 * Do not modify this file.
 *
 * @author David Sutton
 */
public interface StringQueue {

    /**
     * Adds a String to the back of this queue.
     *
     * @param s The String to be added
     */
    public void add(String s);

    /**
     * Removes a String from the front of this queue.
     *
     * @return The String at the front of the queue.
     */
    public String remove();

    /**
     * Looks at the String at the front of the queue, but does not remove it.
     *
     * @return The String at the front of the queue.
     */
    public String peek();

    /**
     * Get the size of this queue.
     *
     * @return The number of Strings on the queue.
     */
    public int getSize();
}
