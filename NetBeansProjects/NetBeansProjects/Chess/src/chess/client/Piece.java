/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chess.client;

import java.awt.Color;
import javax.swing.ImageIcon;

/**
 *
 * @author Tone (anw8@aber.ac.uk)
 */
public class Piece {

    private PieceType type;
    private ImageIcon icon;
    private Color color;

    public Piece(PieceType type,Color color){
        this.type = type;
        this.color = color;

        StringBuffer fileString = null;

        if(color == Color.white){
            fileString.append("white_");
        }
        else if(color == Color.black){
            fileString.append("black_");
        }
        switch(type){
            case KING:
                fileString.append("king.gif");
                break;
        }
    }
}
