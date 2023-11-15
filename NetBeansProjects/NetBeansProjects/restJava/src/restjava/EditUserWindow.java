/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package restjava;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Fatblack
 */
public class EditUserWindow extends JFrame implements ActionListener{
    
    private JTextField forname,
                      surname,
                      phone,
                      gradYear,
                      email,
                      login;
    private JPasswordField password,
                           passwordConfirm;
    
    private int id;
    
    private JLabel[] labels;
    private JLayeredPane contentPane;
    private JButton cancel,
                    save;
    
    private boolean isEditable;
    
    private RestfulClient rc;
    
    public EditUserWindow(String title,boolean editable){
        super(title);
        this.isEditable = editable;
        this.setSize(400,600);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        contentPane = new JLayeredPane();
        this.add(contentPane);
        this.createLabels();
        this.createButtons();
        this.createTextFields();
    }
    
    private void createTextFields(){
        forname = new JTextField();
        forname.setBounds(110,20,250,40);
        forname.setEditable(isEditable);
        contentPane.add(forname);
        
        surname = new JTextField();
        surname.setEditable(isEditable);
        surname.setBounds(110,70,250,40);
        contentPane.add(surname);
        
        phone = new JTextField();
        phone.setEditable(isEditable);
        phone.setBounds(110,120,250,40);
        contentPane.add(phone);
        
        gradYear = new JTextField();
        gradYear.setEditable(isEditable);
        gradYear.setBounds(110,170,250,40);
        contentPane.add(gradYear);
        
        email = new JTextField();
        email.setEditable(isEditable);
        email.setBounds(110,220,250,40);
        contentPane.add(email);
        
        login = new JTextField();
        login.setEditable(isEditable);
        login.setBounds(110,270,250,40);
        contentPane.add(login);
        
        password = new JPasswordField();
        password.setBounds(110,320,250,40);
        if(isEditable){
            contentPane.add(password);
        }
        
        passwordConfirm = new JPasswordField();
        passwordConfirm.setBounds(110,370,250,40);
        if(isEditable){
            contentPane.add(passwordConfirm);
        }
    }
    
    private void createButtons(){
        cancel = new JButton("Cancel");
        cancel.setBounds(280, 480, 80, 40);
        cancel.addActionListener(this);
        contentPane.add(cancel);
        
        save = new JButton("Save");
        save.setBounds(195,480,80,40);
        save.addActionListener(this);
        if(isEditable){
            contentPane.add(save);
        }
    }
    
    private void createLabels(){
        labels = new JLabel[50];
        labels[0] = new JLabel("First Name");
        labels[0].setBounds(5, 20, 100, 40);
        labels[0].setHorizontalAlignment(JLabel.RIGHT);
        contentPane.add(labels[0]);
        labels[1] = new JLabel("Second Name");
        labels[1].setBounds(5, 70, 100, 40);
        labels[1].setHorizontalAlignment(JLabel.RIGHT);
        contentPane.add(labels[1]);
        labels[2] = new JLabel("Phone");
        labels[2].setBounds(5, 120, 100, 40);
        labels[2].setHorizontalAlignment(JLabel.RIGHT);
        contentPane.add(labels[2]);
        labels[3] = new JLabel("Grad. Year");
        labels[3].setBounds(5, 170, 100, 40);
        labels[3].setHorizontalAlignment(JLabel.RIGHT);
        contentPane.add(labels[3]);
        labels[4] = new JLabel("Email");
        labels[4].setBounds(5, 220, 100, 40);
        labels[4].setHorizontalAlignment(JLabel.RIGHT);
        contentPane.add(labels[4]);
        labels[5] = new JLabel("Login");
        labels[5].setBounds(5, 270, 100, 40);
        labels[5].setHorizontalAlignment(JLabel.RIGHT);
        contentPane.add(labels[5]);
        labels[6] = new JLabel("Password");
        labels[6].setBounds(5, 320, 100, 40);
        labels[6].setHorizontalAlignment(JLabel.RIGHT);
        if(isEditable){
            contentPane.add(labels[6]);
        }
        
        labels[7] = new JLabel("Confirm Password");
        labels[7].setBounds(5, 370, 200, 40);
        labels[7].setHorizontalAlignment(JLabel.RIGHT);
        if(isEditable){
            contentPane.add(labels[7]);
        }
        
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public void setForname(String forname){
        this.forname.setText(forname);
    }
    
    public void setSurname(String surname){
        this.surname.setText(surname);
    }
    
    public void setPhone(String phone){
        this.phone.setText(phone);
    }
    
    public void setGradYear(String gradYString){
        this.gradYear.setText(gradYString);
    }
    
    public void setEmail(String email){
        this.email.setText(email);
    }
    
    public void setLogin(String login){
        this.login.setText(login);
    }
    
    public boolean checkInput(){
        boolean inputComplete = true;
        if(forname.getText().equals("")){
            inputComplete = false;
        }else if(surname.getText().equals("")){
            inputComplete = false;
        }else if(phone.getText().equals("")){
            inputComplete = false;
        }else if(gradYear.getText().equals("")){
            inputComplete = false;
        }else if(email.getText().equals("")){
            inputComplete = false;
        }else if(login.getText().equals("")){
            //inputComplete = false;
        }else if(password.getText().equals("")){
            //inputComplete = false;
        }else if(passwordConfirm.getText().equals("")){
            //inputComplete = false;
        }
        
        return inputComplete;
    }
    
    public void setParent(RestfulClient rc){
        this.rc = rc;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==cancel){
            this.dispose();
        }else if(e.getSource()==save){
            if(checkInput()){
                System.out.println(this.getTitle());
                if(this.getTitle().contains("Create")){
                    User user = new User(forname.getText(), surname.getText(), phone.getText(), gradYear.getText(), email.getText(), "", "");
                    try{
                        Add.addUser(user);
                    }catch(Exception ex){
                        
                    }
                    rc.refreshList();
                    this.dispose();
                    return;
                }
                User user = new User(forname.getText(), surname.getText(), phone.getText(), gradYear.getText(), email.getText(), "", "");
                user.setId(id);
                try{
                    Edit.edit(user);
                }catch(Exception ex){
                    System.out.println("oh no");
                }
                rc.refreshList();
                this.dispose();
                
            }
        }
        
    }
    
}
