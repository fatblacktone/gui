/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cricket;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Fatblack
 */
public class Frame extends JFrame implements ActionListener{
    
    private JButton submit = new JButton("Submit");
    private int run = 1;
    private JTextField runScore,comment;
    private JLabel runLabel,commentLabel,scoreLabel,previousLabel;
    private JLayeredPane content;
    private JTextArea previousRuns = new JTextArea();
    
    public Frame(){
        super("Cricket");
        this.setSize(880,520);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        runLabel = new JLabel("Run "+run);
        content = new JLayeredPane();
        this.setContentPane(content);
        
        runLabel.setBounds(50,50,50,30);
        content.add(runLabel);
        runScore = new JTextField();
        runScore.setBounds(105,50, 50, 30);
        content.add(runScore);
        
        
        comment = new JTextField();
        comment.setBounds(160,50,250,30);
        content.add(comment);
        
        commentLabel = new JLabel("  Comment");
        commentLabel.setBounds(160,25,100,30);
        content.add(commentLabel);
        
        scoreLabel = new JLabel(" Score");
        scoreLabel.setBounds(105, 25, 50, 30);
        content.add(scoreLabel);
        
        previousRuns.setBounds(430,50,400,400);
        content.add(previousRuns);
        
        previousLabel = new JLabel(" Previous Runs");
        previousLabel.setBounds(430, 25, 200, 30);
        content.add(previousLabel);
        
        submit.setBounds(260, 85, 150, 30);
        content.add(submit);
        
        
        this.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
