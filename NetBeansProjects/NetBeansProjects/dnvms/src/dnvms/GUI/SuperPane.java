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
public class SuperPane extends JLayeredPane implements ActionListener{
    
    private Frame frame;
    private JButton exit,
                    userAccounts,
                    driverRecords,
                    review,
                    dataInput;
    
    
    
    public SuperPane(Frame frame){       
        exit = new JButton("EXIT");
        exit.addActionListener(this);
        exit.setBounds(340, 620,80, 20);       
        this.frame = frame;
        this.add(exit);
        this.setVisible(true);
        this.setup();
    }
    
    private void setup(){
        createButtons();
        positionButtons();
        addListeners();
        addToFrame();       
    }
    
    public void createButtons(){
        userAccounts = new JButton("USERS");
        driverRecords = new JButton("Driver\n Records");
        review = new JButton("Driver\n Review");
        dataInput = new JButton("Debrief");
    }
    
    public void positionButtons(){
        userAccounts.setBounds(50,100,100,100);
        driverRecords.setBounds(150,100,100,100);
        review.setBounds(50,200,100,100);
        dataInput.setBounds(150,200,100,100);
    }
    
    public void addListeners(){
        userAccounts.addActionListener(this);
        driverRecords.addActionListener(this);
        review.addActionListener(this);
        dataInput.addActionListener(this);
        
    }
    
    public void addToFrame(){
        this.add(userAccounts);       
        this.add(driverRecords);
        this.add(review);
        this.add(dataInput);
    }
    
    @Override
    public void setVisible(boolean isVisible){
        if(isVisible){
            frame.setTitle("SUPERUSER");
        }
        super.setVisible(isVisible);
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == exit){
            frame.showLoginScreen();
            this.setVisible(false);
        }else if(e.getSource()==dataInput){
            this.setVisible(false);
            frame.setDebriefSource("super");
            frame.showDebrief();
        }else if(e.getSource()==userAccounts){
            this.setVisible(false);
            frame.setUserManagementSource("super");
            frame.showUserManagement();
        }else if(e.getSource()==driverRecords){
            this.setVisible(false);
            frame.setDriverAdminSource("super");
            frame.showDriverAdmin();
        }
    }
    
}
