package cwk8223;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Implementation of NetworkReader interface. You need to complete this file.
 *
 * @author David Sutton
 */
public class MyNetworkReader implements NetworkReader {
    
    private MyNetwork network = new MyNetwork();

    @Override
    public Network read(InputStream stream) {
        Scanner scan = new Scanner(stream);
        StringBuilder input = new StringBuilder("");
        while(scan.hasNext()){
            input.append(scan.next());
            input.append(" ");
        }
        
        String[] connections = input.toString().split("Connection:");
        
        int numberOfConnections = connections.length;
        
        for(int i=1;i<numberOfConnections;i++){
            String[] stations = connections[i].split(" ");
            network.addStation(stations[1]);
            network.addStation(stations[2]);
            network.addConnection(stations[1],stations[2]);
        }
    
        return network;
    }
}
