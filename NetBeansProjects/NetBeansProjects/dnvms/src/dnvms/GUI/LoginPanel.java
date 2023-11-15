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

/**
 *
 * @author Mathew Keegan
 */
public class LoginPanel extends JLayeredPane implements ActionListener, ChangeListener, FocusListener, MouseListener, KeyListener, MouseMotionListener {

    private JButton login;
    private JTextField userName;
    private JPasswordField password;
    
    private JLabel branding;
    private User currentUser;
    private User[] users;
    private Frame frame;

    public LoginPanel(User[] users,Frame frame) {
        this.users = users;
        this.frame = frame;
        loginScreen();
    }

    private void loginScreen() {        
        userName = new JTextField();
        userName.setBounds(150, 277, 100, 25);
        
        this.add(userName,0);
        password = new JPasswordField();
        password.setBounds(150, 307, 100, 25);
        password.addActionListener(this);
        
        branding = new JLabel(new ImageIcon("logo.png"));
        branding.setBounds(0,0,400,400);
        this.add(branding,1);
        
        this.add(password,0);
        login = new JButton(new ImageIcon("button.png"));
        login.addActionListener(this);
        login.setBounds(150, 342, 100, 25);
        this.add(login,0);
        
        userName.requestFocus();
        this.setVisible(true);
    }
    
    

    

    //Listeners
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            String pass = new String(password.getPassword());            
            currentUser = frame.getCurrentUser(userName.getText(), pass);
            if(currentUser!=null){
                frame.showInitialScreen();                
            }
            else{
                password.setText("");
                return;
            }
            password.setText("");
        } else if (e.getSource() == password) {
            login.doClick();
            password.setText("");
        }
    }
    
    @Override
    public void setVisible(boolean isVisible){
        if(isVisible){
            frame.setTitle("DNVMS - LOGIN");
            
            frame.setSize(400, 400);
        
        
        }
        super.setVisible(isVisible);
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
       
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
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
