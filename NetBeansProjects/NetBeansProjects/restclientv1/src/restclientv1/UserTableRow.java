/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package restclientv1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;

/**
 *
 * @author mathewkeegan
 */
public class UserTableRow extends JLayeredPane implements ActionListener {

    private JTextField forename, surname, gradYear, email;
    private JButton edit, view, delete;
    private User user;
    private RestCommands rc;
    private ClientApplication ca;
    
    public UserTableRow(User user,RestCommands rc,ClientApplication ca) {
        this.user = user;
        this.rc = rc;
        this.ca = ca;
        this.setPreferredSize(new Dimension(400,40));        
        
        this.forename = new JTextField(user.getForename());
        this.forename.setBounds(5, 5, 100, 32);
        this.forename.setEditable(false);
        this.forename.setBackground(Color.white);
        this.add(this.forename);

        this.surname = new JTextField(user.getSurname());
        this.surname.setBounds(110, 5, 100, 32);
        this.surname.setEditable(false);
        this.surname.setBackground(Color.white);
        this.add(this.surname);
 
        this.email = new JTextField(user.getEmail());
        this.email.setBounds(215, 5, 175, 32);
        this.email.setEditable(false);
        this.email.setBackground(Color.white);
        this.add(this.email);
        
        this.gradYear = new JTextField(user.getGradYear());
        this.gradYear.setBounds(395, 5, 45, 32);
        this.gradYear.setEditable(false);
        this.gradYear.setBackground(Color.white);
        this.add(this.gradYear);                
                
        Icon icon = new ImageIcon("img/view-user.png");
        view = new JButton(icon);
        view.setBounds(445, 5, 32, 32);
        view.addActionListener(this);
        this.add(view);
        
        //view.setOpaque(false);
        icon = new ImageIcon("img/edit-user.png");
        edit = new JButton(icon);
        edit.setBounds(480, 5, 32, 32);
        edit.addActionListener(this);
        this.add(edit);
        
        icon = new ImageIcon("img/delete-user.png");
        delete = new JButton(icon);
        delete.setBounds(515, 5, 32, 32);
        delete.addActionListener(this);
        this.add(delete);
        
        this.setVisible(true);        
        
    }    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view) {
                UserWindow UserWindow = new UserWindow("View user", false, false, rc, ca);
                UserWindow.setForename(user.getForename());
                UserWindow.setSurname(user.getSurname());
                UserWindow.setPhone(user.getPhone());
                UserWindow.setGradYear(user.getGradYear());
                UserWindow.setJobs(user.getJobs());
                UserWindow.setEmail(user.getEmail());                
                UserWindow.setId(user.getId());
                
            } else if (e.getSource() == edit) {
                UserWindow UserWindow = new UserWindow("Edit user", true, false, rc, ca);
                UserWindow.setForename(user.getForename());
                UserWindow.setSurname(user.getSurname());
                UserWindow.setPhone(user.getPhone());
                UserWindow.setGradYear(user.getGradYear());
                UserWindow.setJobs(user.getJobs());
                UserWindow.setEmail(user.getEmail());                
                UserWindow.setId(user.getId());                
            } else if (e.getSource() == delete) {
                try{
                   rc.deleteUser(user);
                   ca.refreshUserList();                   
                }catch(Exception ex){
                    
                }                
            }  
    }
    
}
