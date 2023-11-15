/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package restclientv1;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author Mathew Keegan
 */
public class Login implements ActionListener {

    private JFrame frame;
    private JLayeredPane loginPane;
    private JTextField usernameField;
    private JPasswordField passField;
    private JButton loginButton;
    private JLabel usernameText, passwordText, accessDeniedText;
    private String username, password;

    public Login() {
        frame = new JFrame("CSA Client");
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        loginPane = new JLayeredPane();
        frame.add(loginPane);
        loginPane.setOpaque(true);

        loginPane.setBackground(new Color(173, 9, 9));
        usernameText = new JLabel("Username:");
        usernameField = new JTextField();
        passwordText = new JLabel("Password:");
        passField = new JPasswordField();
        passField.addActionListener(this);
        loginButton = new JButton("LOGIN");

        usernameText.setForeground(Color.white);
        usernameText.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        passwordText.setForeground(Color.white);
        passwordText.setFont(new Font("Sans Serif", Font.PLAIN, 16));

        Icon icon = new ImageIcon("csa-logo.png");
        JLabel logo = new JLabel(icon);

        usernameText.setBounds(185, 88, 100, 30);
        passwordText.setBounds(185, 130, 100, 30);
        logo.setBounds(15, 200, 150, 57);
        usernameField.setBounds(270, 90, 200, 30);
        passField.setBounds(270, 130, 200, 30);
        loginButton.setBounds(270, 170, 100, 30);

        usernameField.addActionListener(this);
        passField.addActionListener(this);
        loginButton.addActionListener(this);

        loginPane.add(logo);
        loginPane.add(usernameText);
        loginPane.add(passwordText);
        loginPane.add(usernameField);
        loginPane.add(passField);
        loginPane.add(loginButton);
                
        //TEMPORARY OVERRIDE OF LOGIN
        frame.setVisible(true);        
        //frame.setVisible(false);                
        //ClientApplication ca = new ClientApplication(username, password);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == loginButton) {
            try {
                checkCredentials();
            } catch (IOException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void checkCredentials() throws IOException {
        username = usernameField.getText();
        password = passField.getText();
        int statusCode;

        if (username.equals("") || password.equals("")) {
            System.out.println("Login field incomplete");
        } else {
            DefaultHttpClient httpclient = new DefaultHttpClient();
            try {
                httpclient.getCredentialsProvider().setCredentials(
                        new AuthScope("localhost", 3000),
                        new UsernamePasswordCredentials(username, password));

                HttpGet httpget = new HttpGet("http://localhost:3000/users");
                httpget.addHeader("accept", "application/json");

                HttpResponse response = httpclient.execute(httpget);
                HttpEntity entity = response.getEntity();
                statusCode = response.getStatusLine().getStatusCode();
                EntityUtils.consume(entity);

            } finally {
                httpclient.getConnectionManager().shutdown();
            }

            /* 
             * Any status code between 200 and 205 is accepted due to recommendations
             * made by several articles that other codes may be returned, and all 
             * would provide a successful result.
             */
            if (statusCode >= 200 && statusCode <= 205) {
                frame.setVisible(false);                
                ClientApplication ca = new ClientApplication(username, password);                               
            } else {
                accessDeniedText = new JLabel("Access Denied");
                accessDeniedText.setForeground(Color.white);
                accessDeniedText.setFont(new Font("Sans Serif", Font.PLAIN, 16));
                accessDeniedText.setBounds(270, 230, 150, 30);
                loginPane.add(accessDeniedText);
            }
        }
    }
}
