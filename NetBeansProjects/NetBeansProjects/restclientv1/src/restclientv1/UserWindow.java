/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package restclientv1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Mathew Keegan
 */
public class UserWindow extends JFrame implements ActionListener {

    private JLayeredPane contentPane;
    private boolean isEditable, isAddUser;
    private JTextField forename, surname, phone, gradYear, email, username;
    private JPasswordField password, passwordConfirm;
    private JCheckBox jobs;
    private JLabel[] labels;
    JButton save, cancel;
    private int id;
    RestCommands rc;
    ClientApplication ca;
    

    public UserWindow(String title, boolean isEditable, boolean isAddUser,RestCommands rc,ClientApplication ca) {
        super(title);
        this.rc = rc;
        this.ca = ca;       
        this.isEditable = isEditable;
        this.isAddUser = isAddUser;
        this.setSize(400, 570);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);

        contentPane = new JLayeredPane();
        this.add(contentPane);
        this.createLabels();
        this.createButtons();
        this.createTextFields();
    }

    private void createTextFields() {
        forename = new JTextField();
        forename.setBounds(110, 20, 250, 40);
        forename.setEditable(isEditable);
        contentPane.add(forename);

        surname = new JTextField();
        surname.setEditable(isEditable);
        surname.setBounds(110, 70, 250, 40);
        contentPane.add(surname);

        phone = new JTextField();
        phone.setEditable(isEditable);
        phone.setBounds(110, 120, 250, 40);
        contentPane.add(phone);

        gradYear = new JTextField();
        gradYear.setEditable(isEditable);
        gradYear.setBounds(110, 170, 250, 40);
        contentPane.add(gradYear);

        email = new JTextField();
        email.setEditable(isEditable);
        email.setBounds(110, 220, 250, 40);
        contentPane.add(email);

        jobs = new JCheckBox();
        jobs.setEnabled(isEditable);
        jobs.setBounds(110, 270, 250, 40);
        contentPane.add(jobs);

        username = new JTextField();
        username.setEditable(isEditable);
        username.setBounds(110, 320, 250, 40);

        password = new JPasswordField();
        password.setBounds(110, 370, 250, 40);

        passwordConfirm = new JPasswordField();
        passwordConfirm.setBounds(110, 420, 250, 40);

        if (isAddUser) {
            contentPane.add(username);
            contentPane.add(password);
            contentPane.add(passwordConfirm);
        }
    }

    private void createLabels() {
        labels = new JLabel[10];
        labels[0] = new JLabel("Forename");
        labels[0].setBounds(5, 20, 100, 40);
        labels[0].setHorizontalAlignment(JLabel.RIGHT);
        contentPane.add(labels[0]);
        labels[1] = new JLabel("Surname");
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
        labels[5] = new JLabel("Jobs ");
        labels[5].setBounds(5, 270, 100, 40);
        labels[5].setHorizontalAlignment(JLabel.RIGHT);
        contentPane.add(labels[5]);

        labels[6] = new JLabel("Username");
        labels[6].setBounds(5, 320, 100, 40);
        labels[6].setHorizontalAlignment(JLabel.RIGHT);

        labels[7] = new JLabel("Password");
        labels[7].setBounds(5, 370, 100, 40);
        labels[7].setHorizontalAlignment(JLabel.RIGHT);

        labels[8] = new JLabel("Confirm");
        labels[8].setBounds(5, 420, 100, 40);
        labels[8].setHorizontalAlignment(JLabel.RIGHT);

        if (isAddUser) {
            contentPane.add(labels[6]);
            contentPane.add(labels[7]);
            contentPane.add(labels[8]);
        }

    }

   
    

    private void createButtons() {
        cancel = new JButton("Cancel");
        cancel.setBounds(280, 480, 80, 40);
        cancel.addActionListener(this);
        contentPane.add(cancel);

        save = new JButton("Save");
        save.setBounds(195, 480, 80, 40);
        save.addActionListener(this);
        if (isEditable) {
            contentPane.add(save);
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setForename(String forename) {
        this.forename.setText(forename);
    }

    public void setSurname(String surname) {
        this.surname.setText(surname);
    }

    public void setPhone(String phone) {
        this.phone.setText(phone);
    }

    public void setGradYear(String gradYString) {
        this.gradYear.setText(gradYString);
    }

    public void setEmail(String email) {
        this.email.setText(email);
    }

    public void setJobs(Boolean jobs) {
        this.jobs.setSelected(jobs);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancel) {
            this.dispose();
        } 
        if (e.getSource() == save) {
            if(checkInput()) {
                System.out.println(this.getTitle());
                if(isAddUser) {
                    User user = new User(forename.getText(), surname.getText(), phone.getText(), gradYear.getText(), jobs.isSelected(), email.getText(), username.getText(), password.getText());
                    try {
                        rc.addUser(user);
                        ca.refreshUserList();
                        this.dispose();
                    }
                    catch(Exception ex) {
                        
                    }
                }
                else {
                    User user = new User(forename.getText(), surname.getText(), phone.getText(), gradYear.getText(), jobs.isSelected(), email.getText(), username.getText(), password.getText());
                    user.setId(id);
                    try {
                        rc.editUser(user);
                        
                        ca.refreshUserList();
                        this.dispose();
                    }
                    catch(Exception ex) {
                        
                    }
                }
            }
        }
        
    }

    public boolean checkInput() {
        boolean inputComplete = true;
        if (forename.getText().equals("")) {
            inputComplete = false;
        } else if (surname.getText().equals("")) {
            inputComplete = false;
        } else if (phone.getText().equals("")) {
            inputComplete = false;
        } else if (gradYear.getText().equals("")) {
            inputComplete = false;
        } else if (email.getText().equals("")) {
            inputComplete = false;
        } else if (isAddUser) {
            if (username.getText().equals("")) {
                inputComplete = false;
            } else if (password.getText().equals("")) {
                inputComplete = false;
            } else if (passwordConfirm.getText().equals("")) {
                inputComplete = false;
            }
        }
        return inputComplete;
    }
}
