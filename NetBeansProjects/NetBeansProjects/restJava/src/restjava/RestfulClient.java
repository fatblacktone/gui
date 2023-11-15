package restjava;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

/**
 *
 * @author Mathew Keegan
 */
public class RestfulClient implements ActionListener {

    private JFrame frame;
    private JLayeredPane users;
    private JPanel broadcasts;
    private JPanel resultsPanel;
    private JTextField searchInput;
    private JComboBox searchChoice;
    private JButton searchButton,add;
    private JLabel surname,
            forname,
            email,
            gradYear;
    private User[] user;
    private JLayeredPane loginPane;
    private JTextField loginField;
    private JPasswordField passField;
    private JButton loginButton;
    private String loginName;
    private String passwordString;

    public RestfulClient() {
        frame = new JFrame("CS Desktop Application");
        frame.setSize(600, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
       
        loginPane = new JLayeredPane();
        frame.add(loginPane);
        loginPane.setOpaque(true);

        loginPane.setBackground(new Color(175,31,0));
        loginField = new JTextField();
        passField = new JPasswordField();
        loginButton = new JButton("LOGIN");

        loginField.setBounds(250, 150, 100, 30);
        passField.setBounds(250, 190, 100, 30);
        loginButton.setBounds(250, 230, 100, 30);

        loginField.addActionListener(this);
        passField.addActionListener(this);
        loginButton.addActionListener(this);


        loginPane.add(loginField);
        loginPane.add(passField);
        loginPane.add(loginButton);
        
        Icon icon = new ImageIcon("logo.png");
        JLabel label = new JLabel(icon);
        label.setBounds(150, 100, 300, 300);
        label.setOpaque(true);
        label.setBackground(new Color(180,31,0));
        label.setBorder(new BevelBorder(1));
        
        loginPane.add(label);

        frame.setVisible(true);
    }

    private void setupTabbedPane() {
        JTabbedPane tab = new JTabbedPane();
        
        tab.setOpaque(true);
        
        frame.add(tab, BorderLayout.CENTER);
        try {
            user = RestJava.getUsers(loginName, passwordString);
        } catch (Exception ex) {
            System.out.println("Fuckity cunt");
        }
        // Generate the tab pages
        createUsersTab();
        createBroadcastsTab();
        
        //createRandomUsers(100);
        tab.add("Users", users);
        tab.add("Broadcasts", broadcasts);
    }

    public void createRandomUsers(int numberOfUsers) {
        for (int i = 0; i < numberOfUsers; i++) {
            user[i] = new User(null, null, null, null, null, null, null);
            String fornames[] = new String[10];
            fornames[0] = "Dave";
            fornames[1] = "Pete";
            fornames[2] = "John";
            fornames[3] = "Andrew";
            fornames[4] = "Mathew";
            fornames[5] = "Adrian";
            fornames[6] = "Patrick";
            fornames[7] = "Douglas";
            fornames[8] = "Tony";
            fornames[9] = "Edward";

            Random random = new Random();
            int selection = random.nextInt(10);
            user[i].setForname(fornames[selection]);

            String surnames[] = new String[10];
            surnames[0] = "Davies";
            surnames[1] = "Smith";
            surnames[2] = "Jones";
            surnames[3] = "Williams";
            surnames[4] = "Evans";
            surnames[5] = "Johnson";
            surnames[6] = "Daniels";
            surnames[7] = "Thomas";
            surnames[8] = "Harris";
            surnames[9] = "Griffiths";

            selection = random.nextInt(10);
            user[i].setSurname(surnames[selection]);

            String phone = "0";
            for (int in = 0; in < 10; in++) {
                phone += "" + random.nextInt(10);
                if (in == 3) {
                    phone += " ";
                }
            }

            user[i].setGradYear("" + (1950 + random.nextInt(62)));

            user[i].setEmail("" + user[i].getForname() + "_" + user[i].getSurname() + random.nextInt(1000) + "@madeup.com");
            user[i].setPhone(phone);

            user[i].setLogin(user[i].getForname().charAt(0) + "" + user[i].getForname().charAt(1) + "" + user[i].getSurname().charAt(0));

            System.out.println(user[i].toString());
        }
    }

    public void createUsersTab() {
        String[] searchStrings = {"Firstname", "Surname", "Grad. Year", "Phone", "Email"};
        users = new JLayeredPane();
        users.setOpaque(true);
        users.setBackground(new Color(30,30,30));
        searchInput = new JTextField();
        searchInput.setBounds(85, 15, 150, 30);
        users.add(searchInput);
        searchChoice = new JComboBox(searchStrings);
        searchChoice.setBounds(240, 15, 150, 30);
        users.add(searchChoice);
        searchButton = new JButton("Search");
        searchButton.setBounds(395, 15, 100, 30);
        searchButton.addActionListener(this);
        users.add(searchButton);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 80, 560, 530);
        Icon icon = new ImageIcon("add.png");
        add = new JButton(icon);
        add.setBounds(530, 15, 30, 30);
        add.addActionListener(this);
        users.add(add);

        resultsPanel = new JPanel(new GridLayout(100, 1));

        scrollPane.getViewport().add(resultsPanel);
        users.add(scrollPane);

        this.createLabels();

        int count = 0;
        while (count < user.length) {

            Entry entry = new Entry(user[count].getForname(), user[count].getSurname(), user[count].getEmail(), user[count].getGradYear(), user[count]);
            entry.setRC(this);
            resultsPanel.add(entry);
            frame.repaint();

            count++;
        }

    }
    
    public void refreshList(){
        try {
            user = RestJava.getUsers(loginName, passwordString);
        } catch (Exception ex) {
            System.out.println("Fuckity cunt");
        }
        resultsPanel.removeAll();
        int count = 0;
        while (count < user.length) {

            Entry entry = new Entry(user[count].getForname(), user[count].getSurname(), user[count].getEmail(), user[count].getGradYear(), user[count]);
            entry.setRC(this);
            resultsPanel.add(entry);
            frame.repaint();

            count++;
        }
    }

    public void createBroadcastsTab() {
        broadcasts = new JPanel();
        broadcasts.setLayout(null);
        JLabel label1 = new JLabel("Username:");
        label1.setBounds(10, 15, 150, 20);
        broadcasts.add(label1);

        JTextField field = new JTextField();
        field.setBounds(10, 35, 150, 20);
        broadcasts.add(field);

        JLabel label2 = new JLabel("Password:");
        label2.setBounds(10, 60, 150, 20);
        broadcasts.add(label2);

        JPasswordField fieldPass = new JPasswordField();
        fieldPass.setBounds(10, 80, 150, 20);
        broadcasts.add(fieldPass);
    }

    public static void main(String args[]) {
        RestfulClient rc = new RestfulClient();

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == searchButton) {
            resultsPanel.removeAll();
            frame.repaint();
            if (searchChoice.getSelectedItem().equals("Firstname")) {
                int count = 0;
                while (count < user.length) {
                    if (user[count].getForname().equals(searchInput.getText())) {
                        Entry entry = new Entry(user[count].getForname(), user[count].getSurname(), user[count].getEmail(), user[count].getGradYear(), user[count]);
                        entry.setRC(this);
                        resultsPanel.add(entry);
                        frame.repaint();
                    }
                    count++;
                }
            }
        } else if (ae.getSource() == loginField) {
            login();
        } else if (ae.getSource() == passField) {
            login();
        } else if (ae.getSource() == loginButton) {
            login();
        }else if(ae.getSource() == add){
            EditUserWindow editUserWindow = new EditUserWindow("Create user", true);
            editUserWindow.setParent(this);
        }
    }

    private void login() {
        if (loginField.getText().equals("") || passField.getText().equals("")) {
            System.out.println("no dtata");
            return;
        }
        loginName = loginField.getText();
        passwordString = passField.getText();
        loginPane.setVisible(false);
        setupTabbedPane();
    }

    private void createLabels() {
        surname = new JLabel("SURNAME");
        surname.setBounds(40, 50, 100, 30);
        surname.setVerticalAlignment(JLabel.BOTTOM);
        users.add(surname);

        forname = new JLabel("FIRST NAME");
        forname.setBounds(140, 50, 100, 30);
        forname.setVerticalAlignment(JLabel.BOTTOM);
        users.add(forname);

        email = new JLabel("EMAIL");
        email.setBounds(260, 50, 100, 30);
        email.setVerticalAlignment(JLabel.BOTTOM);
        users.add(email);

        gradYear = new JLabel("GRAD YEAR");
        gradYear.setBounds(350, 50, 100, 30);
        gradYear.setVerticalAlignment(JLabel.BOTTOM);
        users.add(gradYear);
    }

    /**
     * Private inner class to deal with elements retrieved from the search can
     * be put in separate file.
     */
    private class Entry extends JLayeredPane implements ActionListener {

        private JTextField surname,
                forname,
                email,
                gradYear;
        private JButton edit,
                view,
                delete;
        private User user;
        
        private RestfulClient rc;

        public Entry(String surname, String forname, String email, String gradYear, User user) {

            System.out.println("here");
            this.user = user;

            this.setPreferredSize(new Dimension(400, 40));
            this.surname = new JTextField(surname);
            this.surname.setBounds(5, 5, 100, 30);
            this.add(this.surname);
            this.forname = new JTextField(forname);
            this.forname.setBounds(110, 5, 100, 30);
            this.add(this.forname);
            this.email = new JTextField(email);
            this.email.setBounds(215, 5, 100, 30);
            this.add(this.email);
            this.gradYear = new JTextField(gradYear);
            this.gradYear.setBounds(320, 5, 100, 30);
            this.add(this.gradYear);

            Icon icon = new ImageIcon("view.jpg");
            view = new JButton(icon);
            view.setBounds(425, 5, 30, 30);
            view.addActionListener(this);
            this.add(view);
            view.setOpaque(false);
            icon = new ImageIcon("edit.png");
            edit = new JButton(icon);
            edit.setBounds(460, 5, 30, 30);
            edit.addActionListener(this);
            this.add(edit);
            icon = new ImageIcon("delete.png");
            delete = new JButton(icon);
            delete.setBounds(495, 5, 30, 30);
            delete.addActionListener(this);
            
            this.add(delete);
            this.setVisible(true);
        }

        public Entry() {
            this.setPreferredSize(new Dimension(400, 40));
            surname = new JTextField();
            surname.setBounds(5, 5, 100, 30);
            add(surname);
            forname = new JTextField();
            forname.setBounds(110, 5, 100, 30);
            add(forname);
            email = new JTextField();
            email.setBounds(215, 5, 100, 30);
            add(email);
            gradYear = new JTextField();
            gradYear.setBounds(320, 5, 100, 30);
            add(gradYear);

            Icon icon = new ImageIcon("view.jpg");
            view = new JButton(icon);
            view.setBounds(425, 5, 30, 30);
            view.addActionListener(this);
            this.add(view);
            view.setOpaque(false);
            icon = new ImageIcon("edit.png");
            edit = new JButton(icon);
            edit.setBounds(460, 5, 30, 30);
            edit.addActionListener(this);
            this.add(edit);
            icon = new ImageIcon("delete.png");
            delete = new JButton(icon);
            delete.setBounds(495, 5, 30, 30);
            delete.addActionListener(this);
            this.add(delete);
            this.setVisible(true);
        }
        
        public void setRC(RestfulClient rc){
            this.rc = rc;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == view) {
                EditUserWindow editUserWindow = new EditUserWindow("View user", false);
                editUserWindow.setForname(user.getForname());
                editUserWindow.setSurname(user.getSurname());
                editUserWindow.setPhone(user.getPhone());
                editUserWindow.setGradYear(user.getGradYear());
                editUserWindow.setEmail(user.getEmail());
                editUserWindow.setLogin(user.getLogin());
                editUserWindow.setId(user.getId());
            } else if (e.getSource() == edit) {
                EditUserWindow editUserWindow = new EditUserWindow("Edit user", true);
                editUserWindow.setForname(user.getForname());
                editUserWindow.setSurname(user.getSurname());
                editUserWindow.setPhone(user.getPhone());
                editUserWindow.setGradYear(user.getGradYear());
                editUserWindow.setEmail(user.getEmail());
                editUserWindow.setLogin(user.getLogin());
                editUserWindow.setId(user.getId());
                editUserWindow.setParent(rc);
            } else if (e.getSource() == delete) {
                try{
                    DeleteExample.Delete(user);
                }catch(Exception ex){
                    
                }
                rc.refreshList();
            }
        }
    }
}
