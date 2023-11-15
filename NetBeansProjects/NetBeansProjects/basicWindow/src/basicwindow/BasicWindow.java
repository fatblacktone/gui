/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package basicwindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JTextArea;

/**
 *
 * @author Fatblack
 */
public class BasicWindow extends JFrame implements ActionListener{
    
    private JLayeredPane mainPane;
    
    
    public BasicWindow(){
        super("basic window");
        this.setSize(800, 800);  //Remember this is the size of the frame not of the content pane
        
        //create and add the content pane
        mainPane = new JLayeredPane();
        this.add(mainPane);
        
        //the line below could also be used. Not sure what the diff is if any
        //this.setContentPane(mainPane);
        
        JTextArea textArea = new JTextArea();
        //here is where you give the element size and position. first set of co-ords is pos
        //then elements width and hight
        textArea.setBounds(20,50, 400, 400);
        mainPane.add(textArea);
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BasicWindow bw = new BasicWindow();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
