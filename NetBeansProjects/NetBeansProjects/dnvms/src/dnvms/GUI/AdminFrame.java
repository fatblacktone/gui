/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dnvms.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 *
 * @author Mathew Keegan
 */
public class AdminFrame extends JLayeredPane implements ActionListener{
    
    private JLayeredPane parent;
    private JButton exit,
                    userAccounts,
                    driverRecords;
    
    
    
    public AdminFrame(JLayeredPane calling){       
        exit = new JButton("EXIT");
        exit.addActionListener(this);
        exit.setBounds(340, 620,80, 20);       
        this.parent = calling;
        this.add(exit);
        this.setVisible(true);
        this.setup();
    }
    
    private void setup(){
        userAccounts = new JButton("USERS");
        userAccounts.setBounds(50,100,100,100);
        this.add(userAccounts);
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == exit){
            parent.setVisible(true);
            this.setVisible(false);
        }
    }
    
}
