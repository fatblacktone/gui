/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chessserver;

import java.net.Socket;

/**
 *
 * @author Tone (anw8@aber.ac.uk)
 */
public class Client {

    private int playerID;
    private Socket thisPlayersSocket,
                   opponentPlayersSocket;

    public Client(int ID,Socket thisPlayersSocket,Socket opponentsPlayersSocket){
        this.playerID = ID;
        this.thisPlayersSocket = thisPlayersSocket;
        this.opponentPlayersSocket = opponentsPlayersSocket;
    }

    public Socket getOpponentPlayersSocket() {
        return opponentPlayersSocket;
    }

    public void setOpponentPlayersSocket(Socket opponentPlayersSocket) {
        this.opponentPlayersSocket = opponentPlayersSocket;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public Socket getThisPlayersSocket() {
        return thisPlayersSocket;
    }

    public void setThisPlayersSocket(Socket thisPlayersSocket) {
        this.thisPlayersSocket = thisPlayersSocket;
    }


}
