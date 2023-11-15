package cwk8223;

import java.io.InputStream;

/**
 * Interface to be implemented by a class that can read an nputStream and
 * create a Network object.
 * You should not modify this file.
 * @author David Sutton
 */
public interface NetworkReader {
    /**
     * Create a Network object by reading and InputStream
     * @param stream The stream to be read.
     * @return A Network object corresponding to that InputStream
     */
    public Network read(InputStream stream);
}
