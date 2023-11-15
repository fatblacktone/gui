/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cwk8223;

import java.util.ArrayList;

/**
 * Interface to be implemented by classes that traverse a network. You should
 * not modify this file.
 *
 * @author David Sutton
 */
public interface Navigator {

    /**
     * List the Stations in the network in breadth first order.
     *
     * @param network The network to be traversed.
     * @param start The start station.
     * @return A list of stations created by performing a breadth first
     * traversal of the network, starting at the specified start point.
     */
    public ArrayList<String> breadthFirst(Network network, String start);

    /**
     * List the Stations in the network in depth first order.
     *
     * @param network The network to be traversed.
     * @param start The start station.
     * @return A list of stations created by performing a depth first traversal
     * of the network, starting at the specified start point
     */
    public ArrayList<String> depthFirst(Network network, String start);
}
