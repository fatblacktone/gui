/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.client;

import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 * This class acts as the client for the program. It opens and closes connections
 * to the server and then uses this connection to comunicate with the server
 *
 * @author Tone (anw8@aber.ac.uk)
 */
public class Client {

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private TheBoard board;
    private String ip;
    private int port;
    private boolean isPlayersTurn;
    /**
     * This constructor creates the client. It takes a string representing the
     * ip of the server and an int to represent the port to connect to.
     * It does not connect to the server.
     *
     * @param ip
     * @param port
     */
    public Client(String ip, int port) {
        this.ip = ip;
        this.port = port;
        isPlayersTurn = false;
    }

    /**
     * This method creates a connection to the server. Returns true if
     * connection is made false if it fails. If it fails to make a connection
     * the failure is noted in the connections.LOG file found in the pakage
     *
     * @return  isConnected  true if connection made
     */
    public boolean connectToServer() {
        boolean isConnected = false;
        try {
            //Create the socket, reader and writer
            socket = new Socket(ip, port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            //If it has reached here a connection has been made
            isConnected = true;
            System.out.println(DateUtils.getTime() + " :-: Connected to server " +
                                        "@ " + ip + " on port: " + port + "");
        } catch (UnknownHostException ex) {
            System.out.println("Unknown host: " + ip + ".");
        } catch (IOException ex) {
            System.out.println("Could not get IO for: " + ip + ".");
        }
        return isConnected;
    }

    /**
     * This method sends the text given as a paremeter to the server. See
     * configuration file allowedCommands.config for all commands the server
     * recognises
     *
     * @param toSend
     */
    public void send(String toSend) {
        out.println(toSend);
    }

    /**
     * This method checks the input from the server to check for incoming
     * messages.
     *
     * @return clientCommand text command for client
     */
    public String get() {
        System.out.println("lhndfskajhdfkjhsa");
        String clientCommand = null;
        try {
            clientCommand = in.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clientCommand;
    }

    /**
     * This method gets the ip for the cient machine.
     *
     * @return string that represents the ip
     */

    public String getIP() {
        InetAddress thisIp = null;
        try {
            thisIp =
                    InetAddress.getLocalHost();
            System.out.println("IP:" + thisIp.getHostAddress());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return thisIp.toString();
    }

    /**
     * This method closes the current connection.
     *
     */
    
    public void closeConnection() {
        try {
            out.close();
            in.close();
            socket.close();
        } catch (IOException ex) {
            System.out.println("WRONG!!!");
        }
    }
}
