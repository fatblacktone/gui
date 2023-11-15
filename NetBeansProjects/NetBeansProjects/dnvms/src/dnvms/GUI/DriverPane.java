/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dnvms.GUI;

import dnvms.fileOperators.FileWrite;
import dnvms.objects.Driver;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.Image;
import dnvms.objects.User;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.border.BevelBorder;
import java.io.File;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Mathew Keegan
 */
public class DriverPane extends JLayeredPane implements ActionListener, MouseListener {

    private JButton exit;
    private String source;
    private Frame frame;
    private JLayeredPane topPanel;
    private JScrollPane panel;
    private JButton newDriver,
            removeDriver,
            editDriver;
    private JButton create, cancel, save, selectDriverPhoto;
    private JFrame newDriverPane,editDriverPane;
    private JLabel dName, postcode, address, head, dobLabel, licenseLabel,runLabel,driverNumberLabel;
    private JTextField driverName, driverAddress1, driverAddress2, driverAddress3, driverPostcode, dob, licenseNumber,run,driverNo;
    private JLabel[] driverLabels;
    private JLabel selectedLabel;
    private int selectedIndex = 0;
    private int numberOfDrivers = 0;
    private boolean emptyFields = false;
    private File driverImageFile;
    private Driver[] drivers;
    private ImageIcon headIcon;
    private boolean driverSelected;

    public DriverPane(Frame frame,Driver[] drivers) {
        exit = new JButton("EXIT");
        exit.addActionListener(this);
        exit.setBounds(340, 620, 80, 20);
        this.frame = frame;
        this.drivers = drivers;
        this.add(exit);
        initiateNumberOfDrivers();
        this.setOpaque(true);
        this.setBackground(new Color(180, 180, 180));
        newDriver = new JButton("New Driver");
        newDriver.addActionListener(this);
        newDriver.setBounds(240, 210, 130, 25);
        this.add(newDriver);
        removeDriver = new JButton("Remove Driver");
        removeDriver.setBounds(240, 240, 130, 25);
        removeDriver.addActionListener(this);
        this.add(removeDriver);
        editDriver = new JButton("Edit Driver");
        editDriver.setBounds(240, 270, 130, 25);
        editDriver.addActionListener(this);
        this.add(editDriver);
        topPanel = new JLayeredPane();
        topPanel.setBounds(10, 30, 200, 900);
        //topPanel.setLayout(new GridLayout(100, 1));
	topPanel.setOpaque(true);
        
        
        panel = new JScrollPane();        
        panel.setOpaque(true);
        panel.setBounds(10, 30, 200, 400);
        //panel.setEnabled(true);
       // panel.setMinimumSize(new Dimension(200, 400));
       // panel.setMaximumSize(new Dimension(200,400));
        panel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);//setBounds(10, 30, 200, 300);
        //panel.setBorder(new BevelBorder(1));
        
        //topPanel.add(panel);
        
        
        headIcon = new ImageIcon("head.jpg");
        head = new JLabel(headIcon);
        head.setBounds(230, 45, 150, 150);
        this.add(head);
        this.add(panel);
        this.createDriverLabels();
    }

    private void initiateNumberOfDrivers(){
        while(drivers[numberOfDrivers]!=null){
            numberOfDrivers++;
        }
    }

    private void createDriverLabels() {
        
        panel.setVisible(false);
        panel.removeAll();
        driverLabels = new JLabel[100];
        for(int i=0;i<numberOfDrivers;i++){
            driverLabels[i] = new JLabel(drivers[i].getName());
            driverLabels[i].setHorizontalAlignment(JTextField.CENTER);
            driverLabels[i].setOpaque(true);
            driverLabels[i].setBorder(new BevelBorder(0));
            driverLabels[i].setBackground(new Color(180,180,180));
            driverLabels[i].setBounds(0,i*25,200,25);
            driverLabels[i].addMouseListener(this);
            panel.add(driverLabels[i]);
            driverLabels[i].setVisible(true);
        }
        panel.setVisible(true);
    }

    @Override
    public void setVisible(boolean isVisible) {
        if (isVisible) {
            frame.setTitle("Driver Admin");
        }
        super.setVisible(isVisible);
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSource() {
        return source;
    }

    public void flagEmptyField(JTextField toFlag) {
        toFlag.setOpaque(true);
        toFlag.setBackground(new Color(220,180,180));
    }

    public void unflagEmptyField(JTextField toUnflag){
        toUnflag.setOpaque(true);
        toUnflag.setBackground(Color.red);
    }

    public Image getScaledPhoto(ImageIcon photo, int x, int y) {
        Image image = photo.getImage();
        return image.getScaledInstance(x, y, Image.SCALE_FAST);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit) {
            if (source.equals("super")) {
                this.setVisible(false);
                source = "";
                frame.showInitialScreen();
            }
        } else if (e.getSource() == newDriver) {
            newDriverPane = new JFrame("New Driver");
            newDriverPane.setSize(300, 450);
            newDriverPane.setLocation(130, 200);
            newDriverPane.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            newDriverPane.setVisible(true);
            JLayeredPane pane = new JLayeredPane();
            newDriverPane.add(pane);
            cancel = new JButton("Cancel");
            cancel.setBounds(185, 375, 90, 25);
            cancel.addActionListener(this);
            create = new JButton("Create");
            create.setBounds(90, 375, 90, 25);
            create.addActionListener(this);
            pane.add(create);
            pane.add(cancel);

            ImageIcon imageIcon = new ImageIcon("head.jpg");
            Image image = imageIcon.getImage();
            Image newImage = image.getScaledInstance(50, 50, Image.SCALE_FAST);

            selectDriverPhoto = new JButton(new ImageIcon(newImage));
            selectDriverPhoto.setBounds(125, 280, 50, 50);
            selectDriverPhoto.addActionListener(this);
            pane.add(selectDriverPhoto);

            dName = new JLabel("Driver Name");
            address = new JLabel("Address");
            dName.setBounds(20, 25, 90, 25);
            address.setBounds(20, 50, 90, 25);
            pane.add(dName);
            pane.add(address);

            driverName = new JTextField();
            driverName.setBounds(120, 25, 120, 25);
            pane.add(driverName);
            driverAddress1 = new JTextField();
            driverAddress1.setBounds(120, 50, 120, 25);
            pane.add(driverAddress1);
            driverAddress2 = new JTextField();
            driverAddress2.setBounds(120, 75, 120, 25);
            pane.add(driverAddress2);
            driverAddress3 = new JTextField();
            driverAddress3.setBounds(120, 100, 120, 25);
            pane.add(driverAddress3);

            driverPostcode = new JTextField();
            driverPostcode.setBounds(120, 125, 120, 25);
            pane.add(driverPostcode);


            dobLabel = new JLabel("D.O.B");
            dobLabel.setBounds(20, 155, 90, 25);
            pane.add(dobLabel);
            dob = new JTextField();
            dob.setBounds(120, 155, 120, 25);
            pane.add(dob);

            licenseLabel = new JLabel("License No.");
            licenseLabel.setBounds(20, 185, 90, 25);
            pane.add(licenseLabel);
            licenseNumber = new JTextField();
            licenseNumber.setBounds(120, 185, 120, 25);
            pane.add(licenseNumber);
            
            runLabel = new JLabel("Run No.");
            runLabel.setBounds(20, 215, 90, 25);
            pane.add(runLabel);
            run = new JTextField();
            run.setBounds(120, 215, 120, 25);
            pane.add(run);
            
            driverNumberLabel = new JLabel("Driver No.");
            driverNumberLabel.setBounds(20, 245, 90, 25);
            pane.add(driverNumberLabel);
            driverNo = new JTextField();
            driverNo.setBounds(120, 245, 120, 25);
            pane.add(driverNo);
            
            
            
            






        }else if (e.getSource() == editDriver) {
            if(!driverSelected)return;
            
            editDriverPane = new JFrame("Edit Driver");
            editDriverPane.setSize(300, 450);
            editDriverPane.setLocation(130, 200);
            editDriverPane.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            editDriverPane.setVisible(true);
            JLayeredPane pane = new JLayeredPane();
            editDriverPane.add(pane);
            cancel = new JButton("Cancel");
            cancel.setBounds(185, 375, 90, 25);
            cancel.addActionListener(this);
            save = new JButton("Save");
            save.setBounds(90, 375, 90, 25);
            save.addActionListener(this);
            pane.add(save);
            pane.add(cancel);

            ImageIcon imageIcon = new ImageIcon(drivers[selectedIndex].getDriverPhoto().getAbsolutePath());
            Image image = imageIcon.getImage();
            Image newImage = image.getScaledInstance(50, 50, Image.SCALE_FAST);

            selectDriverPhoto = new JButton(new ImageIcon(newImage));
            selectDriverPhoto.setBounds(125, 280, 50, 50);
            selectDriverPhoto.addActionListener(this);
            pane.add(selectDriverPhoto);

            dName = new JLabel("Driver Name");
            address = new JLabel("Address");
            dName.setBounds(20, 25, 90, 25);
            address.setBounds(20, 50, 90, 25);
            pane.add(dName);
            pane.add(address);

            
            driverName = new JTextField(drivers[selectedIndex].getName());
            
            driverName.setBounds(120, 25, 120, 25);
            pane.add(driverName);
            String[] add = drivers[selectedIndex].getAddress().split(",");
            driverAddress1 = new JTextField(add[0]);
            driverAddress1.setBounds(120, 50, 120, 25);
            pane.add(driverAddress1);
            driverAddress2 = new JTextField(add[1]);
            driverAddress2.setBounds(120, 75, 120, 25);
            pane.add(driverAddress2);
            driverAddress3 = new JTextField(add[2]);
            driverAddress3.setBounds(120, 100, 120, 25);
            pane.add(driverAddress3);

            driverPostcode = new JTextField(add[3]);
            driverPostcode.setBounds(120, 125, 120, 25);
            pane.add(driverPostcode);


            dobLabel = new JLabel("D.O.B");
            dobLabel.setBounds(20, 155, 90, 25);
            pane.add(dobLabel);
            dob = new JTextField(drivers[selectedIndex].getDob());
            dob.setBounds(120, 155, 120, 25);
            pane.add(dob);

            licenseLabel = new JLabel("License No.");
            licenseLabel.setBounds(20, 185, 90, 25);
            pane.add(licenseLabel);
            licenseNumber = new JTextField(drivers[selectedIndex].getLicenseNumber());
            licenseNumber.setBounds(120, 185, 120, 25);
            pane.add(licenseNumber);
            
            runLabel = new JLabel("Run No.");
            runLabel.setBounds(20, 215, 90, 25);
            pane.add(runLabel);
            run = new JTextField(drivers[selectedIndex].getRun());
            run.setBounds(120, 215, 120, 25);
            pane.add(run);
            
            driverNumberLabel = new JLabel("Driver No.");
            driverNumberLabel.setBounds(20, 245, 90, 25);
            pane.add(driverNumberLabel);
            driverNo = new JTextField(drivers[selectedIndex].getDriverNumber());
            driverNo.setBounds(120, 245, 120, 25);
            pane.add(driverNo);

        } else if (e.getSource() == cancel) {
            if(newDriverPane != null)newDriverPane.dispose();
            if(editDriverPane != null)editDriverPane.dispose();
        } else if (e.getSource() == create) {
            String[] driverDetails = new String[10];
            driverDetails[0] = driverName.getText();
            driverDetails[1] = driverAddress1.getText();
            driverDetails[2] = driverAddress2.getText();
            driverDetails[3] = driverAddress3.getText();
            driverDetails[4] = driverPostcode.getText();
            driverDetails[5] = dob.getText();
            driverDetails[6] = licenseNumber.getText();
            driverDetails[7] = run.getText();
            driverDetails[8] = driverNo.getText();
            for (int i = 0; i < 7; i++) {
                if (driverDetails[i].equals("")) {
                    emptyFields = true;
                    switch (i) {
                        case 0:
                            flagEmptyField(driverName);
                            break;
                        case 1:
                            flagEmptyField(driverAddress1);
                            break;
                        case 2:
                            flagEmptyField(driverAddress2);
                            break;
                        case 3:
                            flagEmptyField(driverAddress3);
                            break;
                        case 4:
                            flagEmptyField(driverPostcode);
                            break;
                        case 5:
                            flagEmptyField(dob);
                            break;
                        case 6:
                            flagEmptyField(licenseNumber);
                            break;
                        case 7:
                            flagEmptyField(run);
                            break;
                        case 8:
                            flagEmptyField(driverNo);
                            break;

                    }

                }

            }
            if(emptyFields){
                JOptionPane.showMessageDialog(this, "Please Complete all fields", "", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(driverImageFile == null){
                JOptionPane.showMessageDialog(this, "Please select Driver Photo", "", JOptionPane.ERROR_MESSAGE);
                selectDriverPhoto.doClick();
                return;
            }
            drivers[numberOfDrivers++] = new Driver(driverDetails[0],driverDetails[1]+","+driverDetails[2]+","+driverDetails[3]+","+driverDetails[4],driverDetails[5],driverDetails[6],driverDetails[7],driverDetails[8],driverImageFile.getAbsolutePath());
            FileWrite writer = new FileWrite();
            writer.createDriverFile(drivers);
            createDriverLabels();
            newDriverPane.dispose();

        } else if (e.getSource() == removeDriver) {
            if(selectedLabel == null)return;
            for(int i=selectedIndex;i<numberOfDrivers;i++){
                drivers[i] = drivers[i+1];
            }
            numberOfDrivers--;
            FileWrite writer = new FileWrite();
            writer.createDriverFile(drivers);
            selectedIndex = 0;
            selectedLabel = null;
            head.setIcon(headIcon);
            createDriverLabels();
            driverSelected = false;
            
        } else if (e.getSource() == save) {
            String[] driverDetails = new String[10];
            driverDetails[0] = driverName.getText();
            driverDetails[1] = driverAddress1.getText();
            driverDetails[2] = driverAddress2.getText();
            driverDetails[3] = driverAddress3.getText();
            driverDetails[4] = driverPostcode.getText();
            driverDetails[5] = dob.getText();
            driverDetails[6] = licenseNumber.getText();
            driverDetails[7] = run.getText();
            driverDetails[8] = driverNo.getText();
            
            for (int i = 0; i < 9; i++) {
                if (driverDetails[i].equals("")) {
                    emptyFields = true;
                    switch (i) {
                        case 0:
                            flagEmptyField(driverName);
                            break;
                        case 1:
                            flagEmptyField(driverAddress1);
                            break;
                        case 2:
                            flagEmptyField(driverAddress2);
                            break;
                        case 3:
                            flagEmptyField(driverAddress3);
                            break;
                        case 4:
                            flagEmptyField(driverPostcode);
                            break;
                        case 5:
                            flagEmptyField(dob);
                            break;
                        case 6:
                            flagEmptyField(licenseNumber);
                            break;
                        case 7:
                            flagEmptyField(run);
                            break;
                        case 8:
                            flagEmptyField(driverNo);
                            break;

                    }

                }else emptyFields = false;

            }
            if(emptyFields){
                JOptionPane.showMessageDialog(this, "Please Complete all fields", "", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(driverImageFile == null){
                driverImageFile = new File(drivers[selectedIndex].getDriverPhoto().getAbsolutePath());               
            }
            drivers[selectedIndex] = new Driver(driverDetails[0],driverDetails[1]+","+driverDetails[2]+","+driverDetails[3]+","+driverDetails[4],driverDetails[5],driverDetails[6],driverDetails[7],driverDetails[8],driverImageFile.getAbsolutePath());
            FileWrite writer = new FileWrite();
            writer.createDriverFile(drivers);
            createDriverLabels();
            editDriverPane.dispose();
            head.setIcon(headIcon);
            driverSelected = false;
            selectedLabel = null;
        } else if (e.getSource() == selectDriverPhoto) {
            FileFilter filter = new FileNameExtensionFilter("Image file", "jpg", "jpeg","gif","tif","bmp");
            JFileChooser fc = new JFileChooser();
            fc.setFileFilter(filter);
            int returnVal = fc.showOpenDialog(this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                driverImageFile = fc.getSelectedFile();
                ImageIcon imageIcon = new ImageIcon(driverImageFile.getAbsolutePath());
                selectDriverPhoto.setIcon(new ImageIcon(this.getScaledPhoto(imageIcon, 50, 50)));
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (int i = 0; i < 100; i++) {
            if (e.getSource() == driverLabels[i]) {
                
                if (selectedLabel == driverLabels[i]) {
                    selectedLabel = null;
                    selectedIndex = 0;
                    driverLabels[i].setBackground(new Color(180, 180, 180));
                    head.setIcon(headIcon);
                    driverSelected=false;
                    break;
                }
                if(selectedLabel!=null){
                    selectedLabel.setBackground(new Color(180,180,180));
                }
                selectedLabel = driverLabels[i];
                selectedIndex = i;
                driverLabels[i].setBackground(new Color(220, 220, 220));
                head.setIcon(new ImageIcon(this.getScaledPhoto(new ImageIcon(drivers[i].getDriverPhoto().getPath()), 150, 150)));
                driverSelected=true;
                break;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        for (int i = 0; i < 100; i++) {
            if (e.getSource() == driverLabels[i]) {
                if (driverLabels[i] == selectedLabel) {
                    break;
                }
                driverLabels[i].setBackground(new Color(200, 200, 200));
                if(!driverSelected)head.setIcon(new ImageIcon(this.getScaledPhoto(new ImageIcon(drivers[i].getDriverPhoto().getPath()), 150, 150)));
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        for (int i = 0; i < 100; i++) {
            if (e.getSource() == driverLabels[i]) {
                if (driverLabels[i] == selectedLabel) {
                    break;
                }
                driverLabels[i].setBackground(new Color(180, 180, 180));
                if(!driverSelected)head.setIcon(headIcon);
            }
        }
    }
}
