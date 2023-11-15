/*
 * This class acts as the server used for
 */
package chessserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tone
 */
public class Server implements Runnable {

    //Instance variables
    private Socket socket;
    private int ID;
    private static boolean playerWaiting;
    private static ArrayList<Client> clients = new ArrayList<Client>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int count = 0;
        
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(4242);
            System.out.println("Listening on port "+serverSocket.getLocalPort());
        } catch (IOException e) {
            System.err.println("Could not listen on port: 4444.");
            System.exit(1);
        }
        try {
            Socket lastSocketReturned = null;
            while (true) {                
                Socket connection = serverSocket.accept();

                if(count>clients.size()){
                    count = 0;
                }
                while(true){
                    if(findClient(count)==null)break;
                    count++;
                }
                count++;
                //Create a new instance
                Runnable runnable = new Server(connection, count);
                //Print report to terminal
                System.out.print("New connection made @ "+DateUtils.getTime()+"\n"+
                                 "Assigning to player "+count+"\n");
                Thread thread = new Thread(runnable);
                thread.start();
                
                //Create a client object to add to the list
                Client client = null;
                if(playerWaiting){
                    client = new Client(count, connection,lastSocketReturned);
                }else{
                    client = new Client(count, connection,null);
                }
                playerWaiting = playerWaiting ? false : true ;
                clients.add(client);
                lastSocketReturned = connection;
            }
        } catch (Exception e) {
        }


        try {

            serverSocket.close();
        } catch (Exception ex) {
        }

    }

    public Server(Socket s, int i) {
        this.socket = s;
        this.ID = i;

        playerWaiting = playerWaiting ? false: true;
    }

    public static Client findClient(int ID){
        Client foundClient = null;
        for(Client client:clients){
            if(client.getPlayerID() == ID){
                foundClient = client;
                break;
            }
        }
        return foundClient;
    }

    public void run() {
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        } catch (IOException ex) {            
        }
        
        String inputLine;
        try {
            while ((inputLine = in.readLine()) != null) {                
                if(inputLine.equals("CLOSING")){
                    System.out.println("Closing connection to player "+ID);
                    break;
                }
            }
        } catch (Exception ex) {
        }


        try {
            clients.remove(findClient(this.ID));
            out.close();
            in.close();
            socket.close();
            System.out.println(clients.size());

        } catch (Exception ex) {
        }
    }
}

