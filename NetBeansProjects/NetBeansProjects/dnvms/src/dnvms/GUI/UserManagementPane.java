/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dnvms.GUI;

import dnvms.fileOperators.FileWrite;
import dnvms.objects.User;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.border.BevelBorder;

/**
 *
 * @author Mathew Keegan
 */
public class UserManagementPane extends JLayeredPane implements ActionListener,MouseListener{
    
    private JLayeredPane parent;
    private JButton exit;
    private String source;
    private Frame frame;
    private JPanel panel;
    private JButton newUser,
                    removeUser,
                    editUser;
    
    private JButton create,cancel,save;
    
    private JFrame newUserFrame;
    
    private JLabel uName,clearence;
    private JComboBox privilageLevel;
    private JTextField userName;
    private User[] users;
    private int numberOfUsers = 1;
    private JLabel[] userLabels;
    private JLabel selectedLabel;
    int selectedIndex = 0;
    
    
    
    public UserManagementPane(Frame frame, User[] users){       
        exit = new JButton("EXIT");
        this.users = users;
        initiateNumberOfUsers();
        exit.addActionListener(this);
        exit.setBounds(340, 620,80, 20);       
        this.frame = frame;
        this.add(exit);
        this.setOpaque(true);
        this.setBackground(new Color(180,180,180));
        
        newUser = new JButton("New User");
        newUser.addActionListener(this);
        newUser.setBounds(240,100,130,25);
        this.add(newUser);
        
        removeUser = new JButton("Remove User");
        removeUser.setBounds(240,130,130,25);
        removeUser.addActionListener(this);
        this.add(removeUser);
        
        editUser = new JButton("Edit User");
        editUser.setBounds(240,160,130,25);
        editUser.addActionListener(this);
        this.add(editUser);
         //scrollPane = new JScrollPane();
        ///scrollPane.setBounds(10,30,200,300);
        panel = new JPanel();
        panel.setLayout(new GridLayout(10, 1));
        panel.setBackground(new Color(180,180,180));
        panel.setOpaque(true);
        
        ///scrollPane.add(panel);
        
        panel.setBounds(10,30,200,300);
        panel.setBorder(new BevelBorder(1));
        userLabels = new JLabel[20];
        this.add(panel);
        
        //this.add(scrollPane);
        this.createUserLabels();
    }
    
    private void initiateNumberOfUsers(){
        
        while(users[numberOfUsers]!=null){
            numberOfUsers++;
        }
    }
    
    private void createUserLabels(){
        panel.setVisible(false);
        panel.removeAll();
        userLabels = new JLabel[20];
        for(int i=1;i<numberOfUsers;i++){
            userLabels[i] = new JLabel(users[i].getUserName()+"  "+users[i].getClearenceString());
            userLabels[i].setHorizontalAlignment(JTextField.CENTER);
            userLabels[i].setOpaque(true);
            userLabels[i].setBorder(new BevelBorder(0));
            userLabels[i].setBackground(new Color(180,180,180));
            userLabels[i].addMouseListener(this);
            panel.add(userLabels[i]);
            userLabels[i].setVisible(true);
        }
        panel.setVisible(true);
       
    }
    
    @Override
    public void setVisible(boolean isVisible){
        
        if(isVisible){
            frame.setTitle("User Management");
        }
        super.setVisible(isVisible);
    }
    
    public void setSource(String source){
        this.source = source;
    }
    
    public String getSource(){
        return source;
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == exit){
            if(source.equals("super")){
                this.setVisible(false);
                source = "";
                frame.showInitialScreen();
            }
        }else if(e.getSource()==newUser){
            newUserFrame = new JFrame("New User");
            newUserFrame.setSize(250, 250);
            newUserFrame.setLocation(130, 200);
            newUserFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            newUserFrame.setVisible(true);
            
            JLayeredPane pane = new JLayeredPane();
            newUserFrame.add(pane);
            
            cancel = new JButton("Cancel");
            cancel.setBounds(135,175,90,25);
            cancel.addActionListener(this);
            
            create = new JButton("Create");
            create.setBounds(40,175,90,25);
            create.addActionListener(this);
            
            pane.add(create);
            pane.add(cancel);
            
            userName = new JTextField();
            uName = new JLabel("User Name");
            userName.addActionListener(this);
            clearence = new JLabel("Clearance");
            String[] cLevel = {"USER","MANAGER","ADMIN"};
            privilageLevel = new JComboBox(cLevel);
            
            
            uName.setBounds(20,75,90,25);
            clearence.setBounds(20,105,90,25);
            privilageLevel.setBounds(115,105,90,25);
            userName.setBounds(115,75,90,25);
            
            pane.add(uName);
            pane.add(clearence);
            pane.add(privilageLevel);
            pane.add(userName);
            
        }else if(e.getSource()==cancel){
            newUserFrame.dispose();
        }else if(e.getSource()== userName){
            if(save==null)create.doClick();
        } else if(e.getSource() == create){
            String un = userName.getText();
            if(un.equals(""))return;
            String clear =(String) privilageLevel.getSelectedItem();
            int clearLevel= 0;
            switch(clear){
                case "USER":
                    clearLevel=3;
                    break;
                case "MANAGER":
                    clearLevel=4;
                    break;
                case "ADMIN":
                    clearLevel=2;
                    break;
            }
            
            users[numberOfUsers++] = new User(un, "password",clearLevel);
            FileWrite writer = new FileWrite();
            writer.createUserFile(users);
            createUserLabels();
            newUserFrame.dispose();
        }else if(e.getSource() == removeUser){
            if(selectedLabel == null)return;
            for(int i=selectedIndex;i<numberOfUsers;i++){
                users[i] = users[i+1];
            }
            numberOfUsers--;
            FileWrite writer = new FileWrite();
            writer.createUserFile(users);
            createUserLabels();
            selectedIndex = 0;
            selectedLabel = null;
        }else if(e.getSource()==editUser){
            if(selectedLabel == null)return;
            newUserFrame = new JFrame("Edit User");
            newUserFrame.setSize(250, 250);
            newUserFrame.setLocation(130, 200);
            newUserFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            newUserFrame.setVisible(true);
            
            JLayeredPane pane = new JLayeredPane();
            newUserFrame.add(pane);
            
            cancel = new JButton("Cancel");
            cancel.setBounds(135,175,90,25);
            cancel.addActionListener(this);
            
            save = new JButton("Create");
            save.setBounds(40,175,90,25);
            save.addActionListener(this);
            
            pane.add(save);
            pane.add(cancel);
            
            uName = new JLabel("User Name");
            
            clearence = new JLabel("Clearance");
            String[] cLevel = {"USER","MANAGER","ADMIN"};
            privilageLevel = new JComboBox(cLevel);
            userName = new JTextField(users[selectedIndex].getUserName());
            userName.addActionListener(this);
            int cl = 0;
            switch(users[selectedIndex].getClearance()){
                case USER:
                    cl = 0;
                    break;
                case MANAGER:
                    cl = 1;
                    break;
                case ADMIN:
                    cl = 2;
                    break;
            }
            privilageLevel.setSelectedIndex(cl);
            uName.setBounds(20,75,90,25);
            clearence.setBounds(20,105,90,25);
            privilageLevel.setBounds(115,105,90,25);
            userName.setBounds(115,75,90,25);
            
            
            
            pane.add(uName);
            pane.add(clearence);
            pane.add(privilageLevel);
            pane.add(userName);
            
            
        }else if (e.getSource()==save){
            
            
            String un = userName.getText();
            if(un.equals(""))return;
            String clear =(String) privilageLevel.getSelectedItem();
            int l= 0;
            switch(clear){
                case "USER":
                    l=3;
                    break;
                case "MANAGER":
                    l=4;
                    break;
                case "ADMIN":
                    l=2;
                    break;
            }
            
            users[selectedIndex] = new User(un, "password",l );
            FileWrite writer = new FileWrite();
            writer.createUserFile(users);
            createUserLabels();
            newUserFrame.dispose();
        }
        
        
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for(int i=0;i<20;i++){
            if(e.getSource()==userLabels[i]){
                if(selectedLabel == userLabels[i]){
                    selectedLabel = null;
                    selectedIndex = 0;
                    userLabels[i].setBackground(new Color(180,180,180));
                    break;
                }
                selectedLabel = userLabels[i];
                selectedIndex = i;
                userLabels[i].setBackground(new Color(220,220,220));
                break;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        for(int i=0;i<20;i++){
            if(e.getSource()==userLabels[i]){
                if(userLabels[i]==selectedLabel)break;
                userLabels[i].setBackground(new Color(200,200,200));
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        for(int i=0;i<20;i++){
            if(e.getSource()==userLabels[i]){
                if(userLabels[i]==selectedLabel)break;
                userLabels[i].setBackground(new Color(180,180,180));
            }
        }
    }
    
}
