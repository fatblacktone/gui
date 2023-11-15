/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cwk8223;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Fatblack
 */
public class MyNetwork implements Network{
    private ArrayList<String> stations = new ArrayList<>();
    private HashMap<String,ArrayList<String>> connections = new HashMap<>();

    
    /**
     * This method adds a station to the network.
     * 
     * @param station 
     */
    
    @Override
    public void addStation(String station) {
        if(!stations.contains(station)){
            stations.add(station);
        }
    }
    
    /**
     * This method adds a connection between two stations.
     * 
     * @param fromStation
     * @param toStation 
     */

    @Override
    public void addConnection(String fromStation, String toStation) {
        if(connections.containsKey(fromStation)){
            connections.get(fromStation).add(toStation);
        }else{
            ArrayList<String> newConnections = new ArrayList<>();
            newConnections.add(toStation);
            connections.put(fromStation, newConnections);
        }
    }
    
    /**
     * This method returns an array that contains all the stations that are 
     * directly connected to the station passed in.
     * 
     * @param fromStation
     * @return 
     */

    @Override
    public String[] getConnections(String fromStation) {
        ArrayList<String> list = connections.get(fromStation);
        String[] output = new String[list.size()];
        int count = 0;
        for(String s:list){
            output[count++]=s;
        }
        
        return output;
    }
    
    /**
     * This method returns true if the station passed in is contained in this
     * network.
     * 
     * @param station
     * @return 
     */

    @Override
    public boolean hasStation(String station) {
        return stations.contains(station);
    }
    
    /**
     * This method returns an array containing all the stations in this network.
     * @return 
     */

    @Override
    public String[] getStations() {
        String[] output = new String[stations.size()];
        int count=0;
        for(String s:stations){
            output[count++] = s;
        }
        return output;
    }
    
    
    
}
