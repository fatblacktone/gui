/*
 * 
 */

package chess.client;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 * This class represents the board and displays it in an interactive frame.
 * It holds all game state information but doesnt worry about game logic and
 * legality of moves, as this is taken care of by the server.
 *
 * @author Tone (m1cha3l@lightside.biz)
 */
public class TheBoard extends JFrame implements MouseListener{

    //Array to hold game state
    private char[][] square;

    private Piece[] pieces;

    //GUI components
    private JFrame theFrame;
    private JLayeredPane pane;
    private ImagePanel board;
    private JLabel playerLabel;
    

    public final static int WIDTH = 8;
    private final Client client = new Client("192.168.1.74",4242);

    public TheBoard() {
        square = new char[WIDTH][WIDTH];
        
        this.setUndecorated(true);
        pane = new JLayeredPane();
        this.setSize(800,550);
        this.setContentPane(pane);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        createBoard();
        Thread t = new Thread(new Runnable() {
            public void run() {
                boolean connectionMade = client.connectToServer();
            }
        });
        t.start();
        try{
            Thread.sleep(5000);
        }catch(InterruptedException ex){}        
        
        
    }

    public void createBoard(){
        board = new ImagePanel();
        board.setBounds(50,50,400,400);
        pane.add(board,0,0);
        board.setUp();
        board.drawBoard();        
    }

    @Override
    public void dispose(){
        client.send("CLOSING");
        super.dispose();
        System.exit(0);
    }

    public void placePieces(Color startColor){
        pieces = new Piece[32];
        if(startColor == Color.white){

        }
        else if(startColor == Color.black){

        }
    }

    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
