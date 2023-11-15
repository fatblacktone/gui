/*
 * Copyright (C) 2017 P9134107
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package MANDSRegressionPack.GUI;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;

/**
 *
 * @author Tone Walters (tone_walters@yahoo.com)
 */
public class Interface extends JFrame implements ActionListener, MouseListener, MouseMotionListener {

    private JButton exit = new JButton("EXIT");
    private JButton run = new JButton("RUN");
    private JButton paPosHeaderBrowse = new JButton("Browse");
    private JButton paPosLineBrowse = new JButton("Browse");

    private JLayeredPane contentPane = new JLayeredPane();

    private JTabbedPane content = new JTabbedPane();

    private JLayeredPane paPane = new JLayeredPane();
    private JLayeredPane inventory = new JLayeredPane();
    private JLayeredPane orderPane = new JLayeredPane();

    private JMenuBar menuBar = new JMenuBar();
    private JMenu menu = new JMenu("Menu");
    private JMenu about = new JMenu("About");
    private JMenuItem info = new JMenuItem("About", KeyEvent.VK_I);

    private JLabel paInterfaceLabel = new JLabel("Pre Advice Interface");
    private JLabel paPosHeader = new JLabel("Pre Advice Header");
    private JLabel paPosLine = new JLabel("Pre Advice Line");

    private JTextField paHeaderFile = new JTextField();
    private JTextField paLineFile = new JTextField();

    private JRadioButton paInterfacePositiveTests = new JRadioButton("Positive Tests");
    private JRadioButton paInterfaceNegativeTests = new JRadioButton("Negative Tests");

    private JFileChooser fileChooser = new JFileChooser();

    public Interface() {

        this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width,
                Toolkit.getDefaultToolkit().getScreenSize().height - 40);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(contentPane);
        contentPane.add(content);
        content.addTab("Pre Advice/UPI", paPane);
        paPane.setOpaque(true);
        paPane.setBackground(Color.LIGHT_GRAY);
        content.addTab("Inventory", inventory);
        content.addTab("Orders", orderPane);
        paPane.setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width,
                Toolkit.getDefaultToolkit().getScreenSize().height - 90);
        inventory.setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width,
                Toolkit.getDefaultToolkit().getScreenSize().height - 90);
        orderPane.setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width,
                Toolkit.getDefaultToolkit().getScreenSize().height - 90);
        content.setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width,
                Toolkit.getDefaultToolkit().getScreenSize().height - 150);
        setupLabels();
        setupButtons();
        setupRadioButtons();
        setupTextFields();
        this.setupMenus();
        this.setJMenuBar(menuBar);
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        } catch (InstantiationException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        }
        paHeaderFile.setEnabled(false);
        paLineFile.setEnabled(false);
        paPosHeaderBrowse.setEnabled(false);
        paPosLineBrowse.setEnabled(false);
        //this.pack();
        this.setVisible(true);
    }

    private void setupLabels() {
        paInterfaceLabel.setBounds(100, 50, 150, 30);
        paPosHeader.setBounds(100, 130, 150, 30);
        paPosLine.setBounds(100, 170, 150, 30);
        paPane.add(paInterfaceLabel);
        paPane.add(paPosHeader);
        paPane.add(paPosLine);
    }

    private void setupTextFields() {
        paHeaderFile.setBounds(250, 130, 150, 30);
        paHeaderFile.setToolTipText("Enter the location of the CSV PA Header file");
        paLineFile.setBounds(250, 170, 150, 30);
        paLineFile.setToolTipText("Enter the location of the CSV PA Line file");
        paPane.add(paHeaderFile);
        paPane.add(paLineFile);

    }

    private void setupButtons() {
        exit.setBounds(1240, 625, 100, 30);
        run.setBounds(1130, 625, 100, 30);
        paPosHeaderBrowse.setBounds(400, 130, 30, 30);
        paPosHeaderBrowse.setToolTipText("Browse for the PA Header csv file");
        paPosLineBrowse.setBounds(400, 170, 30, 30);
        paPosLineBrowse.setToolTipText("Browse for the PA Line csv file");

        exit.addActionListener(this);
        run.addActionListener(this);
        paPosHeaderBrowse.addActionListener(this);
        paPosLineBrowse.addActionListener(this);

        contentPane.add(run);
        contentPane.add(exit);
        paPane.add(paPosHeaderBrowse);
        paPane.add(paPosLineBrowse);
    }

    private void setupMenus() {
        menu.setMnemonic(KeyEvent.VK_M);
        menu.getAccessibleContext().setAccessibleDescription(
                "Program Options");
        menuBar.add(menu);

        about.setMnemonic(KeyEvent.VK_A);
        about.getAccessibleContext().setAccessibleDescription(
                "About ths program and help");
        menuBar.add(about);

        info.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        info.getAccessibleContext().setAccessibleDescription(
                "This doesn't really do anything");
        about.add(info);
        info.addActionListener(this);
    }

    private void setupRadioButtons() {
        paInterfacePositiveTests.setBounds(100, 90, 150, 30);
        paInterfacePositiveTests.setOpaque(true);
        paInterfacePositiveTests.setBackground(Color.LIGHT_GRAY);
        paInterfacePositiveTests.setToolTipText("This will run the tests that "
                + "are expected to succeed at interfacing PAs into the system");
        paInterfacePositiveTests.setActionCommand("disable");
        paInterfacePositiveTests.addActionListener(this);
        paInterfaceNegativeTests.setBounds(100, 320, 150, 30);
        paInterfaceNegativeTests.setOpaque(true);
        paInterfaceNegativeTests.setBackground(Color.LIGHT_GRAY);
        paInterfaceNegativeTests.setToolTipText("This will run the tests that "
                + "are expected to fail at interfacing PAs into the system");
        paPane.add(paInterfacePositiveTests);
        paPane.add(paInterfaceNegativeTests);
    }

    private void showInfoFrame() {
        JFrame frame = new JFrame("Regression Suite");
        frame.setSize(500, 550);
        frame.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 250,
                Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 250);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit) {
            System.exit(0);
        } else if (e.getSource() == info) {
            showInfoFrame();
        } else if (e.getSource() == paInterfacePositiveTests) {
            if (paInterfacePositiveTests.isSelected()) {
                System.out.println("Enabled");
                paHeaderFile.setEnabled(true);
                paLineFile.setEnabled(true);
                paPosHeaderBrowse.setEnabled(true);
                paPosLineBrowse.setEnabled(true);
            } else {
                System.out.println("Disabled");
                paHeaderFile.setEnabled(false);
                paLineFile.setEnabled(false);
                paPosHeaderBrowse.setEnabled(false);
                paPosLineBrowse.setEnabled(false);
            }
        } else if (e.getSource() == paPosHeaderBrowse) {
            int returnVal = fileChooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                paHeaderFile.setText(file.getAbsolutePath());
            } else {

            }
        } else if (e.getSource() == paPosLineBrowse) {
            int returnVal = fileChooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                paLineFile.setText(file.getAbsolutePath());
            } else {

            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

}
