/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chess;

import chess.client.Client;
import chess.client.TheBoard;


/**
 *
 * @author Tone
 */
public class Main {

    private static void sop(String in){
        System.out.println(in);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {         
        Client client = new Client("192.168.1.74",4242);
        client.connectToServer();
        while(true){
            try{
                Thread.sleep(1000);
            }catch(Exception ex){}
            sop(client.get());
        }
    }

}
