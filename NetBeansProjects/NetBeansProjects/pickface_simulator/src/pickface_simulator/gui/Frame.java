/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pickface_simulator.gui;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author Tone
 */
public class Frame extends JFrame implements ActionListener {

    private JLayeredPane contentPane;
    private JButton exit, start;
    private JLabel dataLabel;
    public Frame() {
        contentPane = new JLayeredPane();
        this.setContentPane(contentPane);
        this.setSize(1000, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        start = new JButton("Start");
        start.addActionListener(this);
        start.setBounds(350, 505, 300, 40);
        contentPane.add(start);
        exit = new JButton("Exit");
        exit.addActionListener(this);
        exit.setBounds(350, 550, 300, 40);
        contentPane.add(exit);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("images/unipart_icon.gif"));

        this.createMenu();

        this.setVisible(true);
    }

    private void createWelcomeScreen() {
        JLayeredPane welcomePane = new JLayeredPane();
        ImageIcon branding = new ImageIcon("images/banding.gif");
    }

    private void createMenu() {
        JMenuBar menuBar;
        JMenu menu, submenu;
        JMenuItem menuItem;
        JRadioButtonMenuItem rbMenuItem;
        JCheckBoxMenuItem cbMenuItem;

//Create the menu bar.
        menuBar = new JMenuBar();

//Build the first menu.
        menu = new JMenu("A Menu");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription(
                "The only menu in this program that has menu items");
        menuBar.add(menu);

//a group of JMenuItems
        menuItem = new JMenuItem("A text-only menu item",
                KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "This doesn't really do anything");
        menu.add(menuItem);

        menuItem = new JMenuItem("Both text and icon",
                new ImageIcon("images/middle.jpg"));
        menuItem.setMnemonic(KeyEvent.VK_B);
        menu.add(menuItem);

        menuItem = new JMenuItem(new ImageIcon("images/middle.jpg"));
        menuItem.setMnemonic(KeyEvent.VK_D);
        menu.add(menuItem);

//a group of radio button menu items
        menu.addSeparator();
        ButtonGroup group = new ButtonGroup();
        rbMenuItem = new JRadioButtonMenuItem("A radio button menu item");
        rbMenuItem.setSelected(true);
        rbMenuItem.setMnemonic(KeyEvent.VK_R);
        group.add(rbMenuItem);
        menu.add(rbMenuItem);

        rbMenuItem = new JRadioButtonMenuItem("Another one");
        rbMenuItem.setMnemonic(KeyEvent.VK_O);
        group.add(rbMenuItem);
        menu.add(rbMenuItem);

//a group of check box menu items
        menu.addSeparator();
        cbMenuItem = new JCheckBoxMenuItem("A check box menu item");
        cbMenuItem.setMnemonic(KeyEvent.VK_C);
        menu.add(cbMenuItem);

        cbMenuItem = new JCheckBoxMenuItem("Another one");
        cbMenuItem.setMnemonic(KeyEvent.VK_H);
        menu.add(cbMenuItem);

//a submenu
        menu.addSeparator();
        submenu = new JMenu("A submenu");
        submenu.setMnemonic(KeyEvent.VK_S);

        menuItem = new JMenuItem("An item in the submenu");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_2, ActionEvent.ALT_MASK));
        submenu.add(menuItem);

        menuItem = new JMenuItem("Another item");
        submenu.add(menuItem);
        menu.add(submenu);

//Build second menu in the menu bar.
        menu = new JMenu("Another Menu");
        menu.setMnemonic(KeyEvent.VK_N);
        menu.getAccessibleContext().setAccessibleDescription(
                "This menu does nothing");
        menuBar.add(menu);

        this.setJMenuBar(menuBar);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit) {
            System.exit(0);
        } else if (e.getSource() == start) {
            this.showOptionsScreen();
        }
    }

    private void showOptionsScreen() {
        start.setVisible(false);
        exit.setVisible(false);
        exit.setBounds(500, 550, 400, 40);
        exit.setVisible(true);

        final JFileChooser fileDialog = new JFileChooser();
        JButton showFileDialogButton = new JButton("Select data set");
        showFileDialogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = fileDialog.showOpenDialog(contentPane);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    java.io.File file = fileDialog.getSelectedFile();
                    dataLabel.setText("       File Selected :"
                            + file.getName());
                } else {
                    //statusLabel.setText("Open command cancelled by user.");
                }
            }
        });
        showFileDialogButton.setBounds(500,100,400,40);
        contentPane.add(showFileDialogButton);
        dataLabel = new JLabel();
        dataLabel.setBorder(new EtchedBorder(Color.lightGray, Color.black));
        dataLabel.setBounds(95,100,400,40);
        contentPane.add(dataLabel);
    }
}
