/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.gui;


import game.Game;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLayeredPane;

/**
 *
 * @author Fatblack
 */
public class GameActionListener implements ActionListener{
    
    private Game game;

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton temp = (JButton) e.getSource();
        JLayeredPane pane = (JLayeredPane) temp.getParent();
        switch (temp.getName()) {
            case "CANCEL":
                System.exit(0);
                break;
            case "START":
                pane.setVisible(false);
                game.initiateGame();
                //game.runGameLoop();
                break;
        }
    }
    
   public void callMeAnyTime()
   {
      try
      {
         throw new Exception("Who called me?");
      }
      catch( Exception e )
      {
         for(int i = 0;i< e.getStackTrace().length;i++){
          System.out.println( i+":  I was called by " + 
                             e.getStackTrace()[i].getClassName() + 
                             "." +
                             e.getStackTrace()[i].getMethodName() + 
                             "()!" );
            }
      }
         
      
   }
    
}
