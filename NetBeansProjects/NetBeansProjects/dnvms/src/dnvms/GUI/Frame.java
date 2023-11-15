 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dnvms.GUI;

import dnvms.objects.User;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import dnvms.objects.Driver;
import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author Mathew Keegan
 */
public class Frame extends JFrame implements ActionListener, ChangeListener, FocusListener, MouseListener, KeyListener, MouseMotionListener {

    

    private User currentUser;
    private User[] users;
    private Driver[] drivers;
    private LoginPanel login;
    private DebriefPane debrief;
    private SuperPane superPane;
    private DriverPane driverPane;
    UserManagementPane userManagementPane;
    private int xPosition,yPosition;
    private int width = 400,height = 400;
    

    public Frame(User[] users,Driver[] drivers) {
        super("DNVMS - JANS Aberystwyth");
        this.users = users;
        this.drivers = drivers;
        workOutLocation();
        this.setSize(width, height);
        this.setLocation(xPosition, yPosition);
        this.setUndecorated(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        intiatePanes();
        
    }
    
    public final void workOutLocation(){
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        xPosition = (dimension.width/2)-(width/2);
        yPosition = (dimension.height/2)-(height/2);
        
    }
    
    public void showSuperPane(){
        this.clearPanes();
        this.add(superPane);
        superPane.setVisible(true);        
    }
    
    public void showLoginScreen(){
        currentUser = null;
        this.clearPanes();
        this.add(login);
        login.setVisible(true);
    }
    
    public void showDebrief(){
        this.clearPanes();
        this.add(debrief);
        debrief.setVisible(true);
    }
    
    public void showUserManagement(){
        this.clearPanes();
        this.add(userManagementPane);
        userManagementPane.setVisible(true);
        
    }
    
    public void showDriverAdmin(){
        this.clearPanes();
        this.add(driverPane);
        driverPane.setVisible(true);
        
    }
    public void setDriverAdminSource(String source){
        driverPane.setSource(source);
    }
    
    public void setDebriefSource(String source){
        debrief.setSource(source);
    }
    
    public void showInitialScreen() {
        if(currentUser.getClearance()== User.UserType.SUPER){
            login.setVisible(false);
            showSuperPane();
        }
    }
    
    private void clearPanes(){
        login.setVisible(false);
        superPane.setVisible(false);
        debrief.setVisible(false);
    }
    
    public void setUserManagementSource(String source){
        userManagementPane.setSource(source);
    }
    
    private void intiatePanes(){
        login = new LoginPanel(users,this);
        superPane = new SuperPane(this);
        debrief = new DebriefPane(this,drivers);
        
        userManagementPane = new UserManagementPane(this,users);
        driverPane = new DriverPane(this,drivers);
        
        this.add(driverPane);
        this.add(superPane);
        this.add(debrief);
        this.add(userManagementPane);
        this.add(login);
        
        this.showLoginScreen();
    }
    
    
    public User getCurrentUser(String userName,String password){
        int count = 0;
            outer:
            {
                while (users[count] != null) {
                    if (userName.equals(users[count].getUserName())) {
                        if (users[count].getPassword().equals(password)) {
                            currentUser = users[count];
                            break outer;
                        }
                    }
                    count++;
                }
                JOptionPane.showMessageDialog(null, "Please check your username and password!");
                return null;
            }
            return currentUser;
    }

    //Listeners
    @Override
    public void actionPerformed(ActionEvent e) {
    
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void focusGained(FocusEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void focusLost(FocusEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
