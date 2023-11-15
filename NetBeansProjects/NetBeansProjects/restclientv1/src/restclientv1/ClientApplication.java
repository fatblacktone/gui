package restclientv1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

/**
 *
 * @author Mathew Keegan
 */
public class ClientApplication implements ActionListener {

    RestCommands rc;
    private JFrame frame;
    private JLayeredPane userTab, broadcastTab;
    private JPanel userResultsPanel, broadcastResultsPanel;
    private String username, password;
    JButton logoutButton, refreshButton, addUserButton, searchButton;
    JButton addBroadcastButton, refreshBroadcastButton;
    JLabel surname, forename, gradYear, email;
    JLabel dateSent, feeds;
    JComboBox searchChoice;
    JTextField searchInput;
    ArrayList<User> usersArray;

    public ClientApplication(String username, String password) throws IOException {
        this.username = username;
        this.password = password;
        rc = new RestCommands(username, password);

        frame = new JFrame("CSA Client");
        frame.setSize(600, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JTabbedPane tab = new JTabbedPane();

        generateUsersTab();
        generateBroadcastsTab();

        frame.add(tab, BorderLayout.CENTER);
        tab.add("Users", userTab);
        tab.add("Broadcasts", broadcastTab);

        frame.setVisible(true);

        System.out.println("Client Username: " + username + " Password: " + password);
    }

    public void generateUsersTab() throws IOException {
        String[] searchStrings = {"- Reset -", "Firstname", "Surname", "Grad. Year", "Phone", "Email"};
        userTab = new JLayeredPane();
        userTab.setOpaque(true);
        userTab.setBackground(new Color(230, 230, 230));

        Icon icon = new ImageIcon("img/logout.png");
        logoutButton = new JButton(icon);
        logoutButton.setBounds(550, 7, 32, 32);
        logoutButton.addActionListener(this);
        userTab.add(logoutButton);

        icon = new ImageIcon("img/refresh.png");
        refreshButton = new JButton(icon);
        refreshButton.setBounds(512, 7, 32, 32);
        refreshButton.addActionListener(this);
        userTab.add(refreshButton);

        icon = new ImageIcon("img/new-user.png");
        addUserButton = new JButton(icon);
        addUserButton.setBounds(473, 7, 32, 32);
        addUserButton.addActionListener(this);
        userTab.add(addUserButton);

        searchInput = new JTextField();
        searchInput.setBounds(10, 7, 175, 30);
        userTab.add(searchInput);

        searchChoice = new JComboBox(searchStrings);
        searchChoice.setBounds(190, 7, 120, 30);
        userTab.add(searchChoice);

        searchButton = new JButton("Search");
        searchButton.setBounds(315, 7, 100, 30);
        searchButton.addActionListener(this);
        userTab.add(searchButton);

        forename = new JLabel("First Name");
        forename.setBounds(37, 50, 100, 30);
        forename.setVerticalAlignment(JLabel.BOTTOM);
        userTab.add(forename);

        surname = new JLabel("Surname");
        surname.setBounds(144, 50, 100, 30);
        surname.setVerticalAlignment(JLabel.BOTTOM);
        userTab.add(surname);

        email = new JLabel("Email");
        email.setBounds(300, 50, 100, 30);
        email.setVerticalAlignment(JLabel.BOTTOM);
        userTab.add(email);

        gradYear = new JLabel("Grad. Year");
        gradYear.setBounds(400, 50, 100, 30);
        gradYear.setVerticalAlignment(JLabel.BOTTOM);
        userTab.add(gradYear);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 90, 573, 635);

        userResultsPanel = new JPanel(new GridLayout(100, 1));
        scrollPane.getViewport().add(userResultsPanel);

        userTab.add(scrollPane);

        usersArray = new ArrayList<User>();
        usersArray = rc.getAllUsers();

        for (int i = 0; i < usersArray.size(); i++) {
            UserTableRow entry = new UserTableRow(usersArray.get(i), rc, this);
            userResultsPanel.add(entry);
            frame.repaint();
        }
    }

    public void generateBroadcastsTab() {
        broadcastTab = new JLayeredPane();
        broadcastTab.setOpaque(true);
        broadcastTab.setBackground(new Color(230, 230, 230));
        
        Icon icon = new ImageIcon("img/logout.png");
        logoutButton = new JButton(icon);
        logoutButton.setBounds(550, 7, 32, 32);
        logoutButton.addActionListener(this);
        broadcastTab.add(logoutButton);

        icon = new ImageIcon("img/refresh.png");
        refreshBroadcastButton = new JButton(icon);
        refreshBroadcastButton.setBounds(512, 7, 32, 32);
        refreshBroadcastButton.addActionListener(this);
        broadcastTab.add(refreshBroadcastButton);

        icon = new ImageIcon("img/new-user.png");
        addBroadcastButton = new JButton(icon);
        addBroadcastButton.setBounds(473, 7, 32, 32);
        addBroadcastButton.addActionListener(this);
        broadcastTab.add(addBroadcastButton);
        
        dateSent = new JLabel("Date Sent");
        dateSent.setBounds(37, 50, 100, 30);
        dateSent.setVerticalAlignment(JLabel.BOTTOM);
        broadcastTab.add(dateSent);

        feeds = new JLabel("Feeds");
        feeds.setBounds(144, 50, 100, 30);
        feeds.setVerticalAlignment(JLabel.BOTTOM);
        broadcastTab.add(feeds);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 90, 573, 635);

        broadcastResultsPanel = new JPanel(new GridLayout(100, 1));
        scrollPane.getViewport().add(broadcastResultsPanel);

        broadcastTab.add(scrollPane);


        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == logoutButton) {
            username = null; //Necessary?
            password = null; //Necessary?
            usersArray.clear();
            frame.setVisible(false);
            Login l = new Login();
        }
        if (e.getSource() == refreshButton) {
            try {
                refreshUserList();
            } catch (IOException ex) {
                Logger.getLogger(ClientApplication.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (e.getSource() == addUserButton) {
            UserWindow UserWindow = new UserWindow("Create user", true, true, rc, this);
            //UserWindow.setParent(this);        
        }
        
        if (e.getSource() == refreshBroadcastButton) {
            try {
                System.out.println("Refresh broadcasts");
                rc.getAllBroadcasts();
            } catch (IOException ex) {
                Logger.getLogger(ClientApplication.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    void refreshUserList() throws IOException {
        usersArray.clear();
        usersArray = rc.getAllUsers();
        userResultsPanel.removeAll();
        for (int i = 0; i < usersArray.size(); i++) {
            UserTableRow entry = new UserTableRow(usersArray.get(i), rc, this);
            userResultsPanel.add(entry);
            frame.repaint();
        }
    }
}
