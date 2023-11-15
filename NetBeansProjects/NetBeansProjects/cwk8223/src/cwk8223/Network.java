/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cwk8223;

/**
 * Interface to be implemented by a class representing a network of
 * interconnected stations.
 * You should not modify this file.
 * @author David Sutton
 */
public interface Network {

    /**
     * Add a station to the network.
     * @param station The station to be added.
     */
    public void addStation(String station);

    /**
     * Add a direct connection from one station to another.
     * @pre both fromStation and toStation have already been added by the method
     * addStation.
     * @param fromStation The station from which the connection begins. 
     * @param toStation The station at which the connection ends.
     */
    public void addConnection(String fromStation, String toStation);

    /**
     * Get a list of all stations directly connected to a given station.
     * @pre fromStation has been added to the network by the method addStation.
     * @param fromStation
     * @return A list of all the stations to which there is a direct connection
     * from fromStation. 
     */
    public String[] getConnections(String fromStation);

    /**
     * Search for a station in the network.
     * @param station Station to be searched for,
     * @return true if the Station exists in the network, false otherwise.
     */
    public boolean hasStation(String station);

    /**
     * Get all stations in the network.
     * @return An array containing all the stations in the network, with no
     * duplicates.
     */
    public String[] getStations();
}
