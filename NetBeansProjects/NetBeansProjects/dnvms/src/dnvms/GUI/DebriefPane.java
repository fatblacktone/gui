/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dnvms.GUI;

import dnvms.fileOperators.FileReader;
import dnvms.objects.DebriefData;
import dnvms.objects.Downtime;
import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import dnvms.objects.Driver;
import java.awt.Image;

/**
 *
 * @author Mathew Keegan
 */
public class DebriefPane extends JLayeredPane implements ActionListener, ChangeListener, FocusListener, MouseListener, KeyListener, MouseMotionListener {

    private final int LOWER_LEFT_ALIGNMENT = 25,
            LOWER_LEFT_TEXT_ALIGNMENT = 135,
            LOWER_RIGHT_ALIGNMENT = 230,
            LOWER_RIGHT_TEXT_ALIGNMENT = 340,
            LOWER_TEXTFIELD_WIDTH = 70,
            MIDDLE_LEFT_ALIGNMENT = 30,
            MIDDLE_LEFT_TEXT_ALIGNMENT = 120,
            MIDDLE_RIGHT_ALIGNMENT = 260,
            MIDDLE_RIGHT_TEXT_ALIGNMENT = 350;
    private final int MIDDLE_SECTION_TOP = 240,
            BOTTOM_SECTION_START = 410;
    private final int HEAD_X = 230,
            HEAD_Y = 50;
    private JButton login,
            go;
    private JButton dCancel,
            save;
    private JButton addDown;
    private JLabel sDelLabel,
            cDelLabel,
            rejDelLabel,
            bwDelLabel,
            cfaDelLabel,
            pcDelLabel;
    private JTextField sDel,
            cDel,
            rejDel,
            bwDel,
            cfaDel,
            pcDel;
    private JLabel sColLabel,
            uColLabel,
            ntcColLabel,
            cColLabel,
            bwColLabel,
            cfaColLabel,
            pcColLabel;
    private JTextField sCol,
            uCol,
            ntcCol,
            cCol,
            bwCol,
            cfaCol,
            pcCol;
    private JTextField userName,
            driverNumber,
            runNo,
            reg,
            mileage,
            hours,
            oCol,
            oDel;
    private JTextField downStart,
            downEnd,
            comments;
    private JLabel downTime;
    private JPasswordField password;
    private JLabel pLabel;
    private JLabel uLabel;
    private JLabel dNLabel,
            rNLabel,
            regLabel,
            milLabel,
            hourLabel,
            oCLabel,
            oDLabel,
            head;
    private Frame frame;
    private String source;
    private Driver[] drivers;
    private Driver currentDriver;
    private ImageIcon headIcon;

    public DebriefPane(Frame frame, Driver[] drivers) {
        this.setVisible(true);
        this.frame = frame;
        
        this.drivers = drivers;



        dCancel = new JButton("Cancel");
        dCancel.setBounds(340, 620, 80, 20);
        dCancel.addActionListener(this);
        dCancel.setBackground(Color.GRAY);
        this.add(dCancel);

        save = new JButton("Save");
        save.setBounds(255, 620, 80, 20);
        save.addActionListener(this);
        save.setBackground(Color.GRAY);
        this.add(save);


        dNLabel = new JLabel("Driver No.");
        rNLabel = new JLabel("Run No.");
        regLabel = new JLabel("Vehicle Reg.");
        milLabel = new JLabel("Mileage");
        hourLabel = new JLabel("Hours");
        oCLabel = new JLabel("Colections");
        oDLabel = new JLabel("Deliveries");

        //rNLabel,
        //regLabel,
        //milLabel,
        //hourLabel,
        //oCLabel,
        //oDLabel;
        dNLabel.setBounds(MIDDLE_LEFT_ALIGNMENT, 70, 100, 20);
        rNLabel.setBounds(MIDDLE_LEFT_ALIGNMENT, MIDDLE_SECTION_TOP, 100, 20);
        regLabel.setBounds(MIDDLE_RIGHT_ALIGNMENT, MIDDLE_SECTION_TOP, 100, 20);
        milLabel.setBounds(MIDDLE_LEFT_ALIGNMENT, MIDDLE_SECTION_TOP + 30, 100, 20);
        hourLabel.setBounds(MIDDLE_RIGHT_ALIGNMENT, MIDDLE_SECTION_TOP + 30, 100, 20);
        oCLabel.setBounds(MIDDLE_LEFT_ALIGNMENT, MIDDLE_SECTION_TOP + 60, 100, 20);
        oDLabel.setBounds(MIDDLE_RIGHT_ALIGNMENT, MIDDLE_SECTION_TOP + 60, 100, 20);

        driverNumber = new JTextField("");
        runNo = new JTextField("");
        runNo.addKeyListener(this);
        reg = new JTextField("");
        mileage = new JTextField("");
        hours = new JTextField("");
        oCol = new JTextField("");
        oDel = new JTextField("");

        driverNumber.setBackground(Color.LIGHT_GRAY);
        runNo.setBackground(Color.LIGHT_GRAY);
        reg.setBackground(Color.LIGHT_GRAY);
        mileage.setBackground(Color.LIGHT_GRAY);
        hours.setBackground(Color.LIGHT_GRAY);
        oCol.setBackground(Color.LIGHT_GRAY);
        oDel.setBackground(Color.LIGHT_GRAY);

        driverNumber.setBounds(MIDDLE_LEFT_TEXT_ALIGNMENT, 70, 50, 20);
        runNo.setBounds(MIDDLE_LEFT_TEXT_ALIGNMENT, MIDDLE_SECTION_TOP, 50, 20);
        reg.setBounds(MIDDLE_RIGHT_TEXT_ALIGNMENT, MIDDLE_SECTION_TOP, 50, 20);
        mileage.setBounds(MIDDLE_LEFT_TEXT_ALIGNMENT, MIDDLE_SECTION_TOP + 30, 50, 20);
        hours.setBounds(MIDDLE_RIGHT_TEXT_ALIGNMENT, MIDDLE_SECTION_TOP + 30, 50, 20);
        oCol.setBounds(MIDDLE_LEFT_TEXT_ALIGNMENT, MIDDLE_SECTION_TOP + 60, 50, 20);
        oDel.setBounds(MIDDLE_RIGHT_TEXT_ALIGNMENT, MIDDLE_SECTION_TOP + 60, 50, 20);

        this.add(driverNumber);
        this.add(runNo);
        this.add(reg);
        this.add(mileage);
        this.add(hours);
        this.add(oCol);
        this.add(oDel);

        this.add(dNLabel);
        this.add(rNLabel);
        this.add(regLabel);
        this.add(milLabel);
        this.add(hourLabel);
        this.add(oCLabel);
        this.add(oDLabel);

        driverNumber.requestFocus();
        driverNumber.addActionListener(this);

        headIcon = new ImageIcon("head.jpg");
        head = new JLabel(new ImageIcon(getScaledPhoto(headIcon, 150, 150)));
        head.setBounds(HEAD_X, HEAD_Y, 150, 150);
        this.add(head);

        downStart = new JTextField("Start");
        downEnd = new JTextField("End");
        comments = new JTextField("Reason and comments");

        downStart.setBounds(20, 370, 100, 25);
        downEnd.setBounds(120, 370, 100, 25);
        comments.setBounds(220, 370, 180, 25);

        downStart.setBackground(Color.LIGHT_GRAY);
        downEnd.setBackground(Color.LIGHT_GRAY);
        comments.setBackground(Color.LIGHT_GRAY);

        this.add(downStart);
        this.add(downEnd);
        this.add(comments);

        downTime = new JLabel("DownTime:");
        downTime.setBounds(20, 345, 100, 25);
        this.add(downTime);

        addDown = new JButton("X");
        addDown.setBounds(400, 370, 30, 25);
        addDown.setBackground(Color.GRAY);
        this.add(addDown);

        sDelLabel = new JLabel("Delivered");
        cDelLabel = new JLabel("Carded");
        rejDelLabel = new JLabel("Rejected");
        bwDelLabel = new JLabel("Bad Weather");
        cfaDelLabel = new JLabel("Can't Find Address");
        pcDelLabel = new JLabel("Police/Civil Action");

        sDelLabel.setBounds(LOWER_LEFT_ALIGNMENT, BOTTOM_SECTION_START, 100, 20);
        cDelLabel.setBounds(LOWER_LEFT_ALIGNMENT, BOTTOM_SECTION_START + 25, 100, 20);
        rejDelLabel.setBounds(LOWER_LEFT_ALIGNMENT, BOTTOM_SECTION_START + 50, 100, 20);
        bwDelLabel.setBounds(LOWER_LEFT_ALIGNMENT, BOTTOM_SECTION_START + 75, 100, 20);
        cfaDelLabel.setBounds(LOWER_LEFT_ALIGNMENT, BOTTOM_SECTION_START + 100, 100, 20);
        pcDelLabel.setBounds(LOWER_LEFT_ALIGNMENT, BOTTOM_SECTION_START + 125, 100, 20);

        this.add(sDelLabel);
        this.add(cDelLabel);
        this.add(rejDelLabel);
        this.add(bwDelLabel);
        this.add(cfaDelLabel);
        this.add(pcDelLabel);

        sDel = new JTextField("");
        cDel = new JTextField("");
        rejDel = new JTextField("");
        bwDel = new JTextField("");
        cfaDel = new JTextField("");
        pcDel = new JTextField("");

        sDel.setBounds(LOWER_LEFT_TEXT_ALIGNMENT, BOTTOM_SECTION_START, LOWER_TEXTFIELD_WIDTH, 20);
        cDel.setBounds(LOWER_LEFT_TEXT_ALIGNMENT, BOTTOM_SECTION_START + 25, LOWER_TEXTFIELD_WIDTH, 20);
        rejDel.setBounds(LOWER_LEFT_TEXT_ALIGNMENT, BOTTOM_SECTION_START + 50, LOWER_TEXTFIELD_WIDTH, 20);
        bwDel.setBounds(LOWER_LEFT_TEXT_ALIGNMENT, BOTTOM_SECTION_START + 75, LOWER_TEXTFIELD_WIDTH, 20);
        cfaDel.setBounds(LOWER_LEFT_TEXT_ALIGNMENT, BOTTOM_SECTION_START + 100, LOWER_TEXTFIELD_WIDTH, 20);
        pcDel.setBounds(LOWER_LEFT_TEXT_ALIGNMENT, BOTTOM_SECTION_START + 125, LOWER_TEXTFIELD_WIDTH, 20);

        this.add(sDel);
        this.add(cDel);
        this.add(rejDel);
        this.add(bwDel);
        this.add(cfaDel);
        this.add(pcDel);

        sDel.setBackground(Color.LIGHT_GRAY);
        cDel.setBackground(Color.LIGHT_GRAY);
        rejDel.setBackground(Color.LIGHT_GRAY);
        bwDel.setBackground(Color.LIGHT_GRAY);
        cfaDel.setBackground(Color.LIGHT_GRAY);
        pcDel.setBackground(Color.LIGHT_GRAY);

        sColLabel = new JLabel("Scheduled");
        uColLabel = new JLabel("Unscheduled");
        ntcColLabel = new JLabel("Nil to Collect");
        cColLabel = new JLabel("Carded");
        bwColLabel = new JLabel("Bad Weather");
        cfaColLabel = new JLabel("Can' Find Address");
        pcColLabel = new JLabel("Police/Civil Action");

        sColLabel.setBounds(LOWER_RIGHT_ALIGNMENT, BOTTOM_SECTION_START, 100, 20);
        uColLabel.setBounds(LOWER_RIGHT_ALIGNMENT, BOTTOM_SECTION_START + 25, 100, 20);
        ntcColLabel.setBounds(LOWER_RIGHT_ALIGNMENT, BOTTOM_SECTION_START + 50, 100, 20);
        cColLabel.setBounds(LOWER_RIGHT_ALIGNMENT, BOTTOM_SECTION_START + 75, 100, 20);
        bwColLabel.setBounds(LOWER_RIGHT_ALIGNMENT, BOTTOM_SECTION_START + 100, 100, 20);
        cfaColLabel.setBounds(LOWER_RIGHT_ALIGNMENT, BOTTOM_SECTION_START + 125, 100, 20);
        pcColLabel.setBounds(LOWER_RIGHT_ALIGNMENT, BOTTOM_SECTION_START + 150, 100, 20);

        this.add(sColLabel);
        this.add(uColLabel);
        this.add(ntcColLabel);
        this.add(cColLabel);
        this.add(bwColLabel);
        this.add(cfaColLabel);
        this.add(pcColLabel);

        sCol = new JTextField("");
        uCol = new JTextField("");
        ntcCol = new JTextField("");
        cCol = new JTextField("");
        bwCol = new JTextField("");
        cfaCol = new JTextField("");
        pcCol = new JTextField("");


        sCol.setBounds(LOWER_RIGHT_TEXT_ALIGNMENT, BOTTOM_SECTION_START, LOWER_TEXTFIELD_WIDTH, 20);
        uCol.setBounds(LOWER_RIGHT_TEXT_ALIGNMENT, BOTTOM_SECTION_START + 25, LOWER_TEXTFIELD_WIDTH, 20);
        ntcCol.setBounds(LOWER_RIGHT_TEXT_ALIGNMENT, BOTTOM_SECTION_START + 50, LOWER_TEXTFIELD_WIDTH, 20);
        cCol.setBounds(LOWER_RIGHT_TEXT_ALIGNMENT, BOTTOM_SECTION_START + 75, LOWER_TEXTFIELD_WIDTH, 20);
        bwCol.setBounds(LOWER_RIGHT_TEXT_ALIGNMENT, BOTTOM_SECTION_START + 100, LOWER_TEXTFIELD_WIDTH, 20);
        cfaCol.setBounds(LOWER_RIGHT_TEXT_ALIGNMENT, BOTTOM_SECTION_START + 125, LOWER_TEXTFIELD_WIDTH, 20);
        pcCol.setBounds(LOWER_RIGHT_TEXT_ALIGNMENT, BOTTOM_SECTION_START + 150, LOWER_TEXTFIELD_WIDTH, 20);

        this.add(sCol);
        this.add(uCol);
        this.add(ntcCol);
        this.add(cCol);
        this.add(bwCol);
        this.add(cfaCol);
        this.add(pcCol);

        sCol.setBackground(Color.LIGHT_GRAY);
        uCol.setBackground(Color.LIGHT_GRAY);
        ntcCol.setBackground(Color.LIGHT_GRAY);
        cCol.setBackground(Color.LIGHT_GRAY);
        bwCol.setBackground(Color.LIGHT_GRAY);
        cfaCol.setBackground(Color.LIGHT_GRAY);
        pcCol.setBackground(Color.LIGHT_GRAY);

    }

    public final Image getScaledPhoto(ImageIcon photo, int x, int y) {
        Image image = photo.getImage();
        return image.getScaledInstance(x, y, Image.SCALE_FAST);
    }
    
    @Override
    public void setVisible(boolean visible){
        super.setVisible(visible);
        
        if(visible&&frame!=null){
            frame.setSize(450, 650);
        }
        
    }

    private boolean setCurrentDriver(String driverNumber) {
        int count = 0;
        while (drivers[count] != null) {
            if (drivers[count].getDriverNumber().equals(driverNumber)) {
                currentDriver = drivers[count];
                return true;
            }
            count++;
        }
        return false;
    }

    //Listeners
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == dCancel) {
            this.setVisible(false);
            currentDriver = null;
            head.setIcon(headIcon);
            driverNumber.setText("");
            if (source.equals("super")) {
                frame.showSuperPane();
            }
        } else if (e.getSource() == driverNumber) {
            drivers = new FileReader().readDrivers(); 
            System.out.println("it got here");
            this.setCurrentDriver(driverNumber.getText());
            System.out.println("it got here to");
            if (currentDriver == null) {
                JOptionPane.showMessageDialog(this, "No matching records found!!", "", JOptionPane.ERROR_MESSAGE);
                driverNumber.setText("");
                return;
            }
            System.out.println("it got here aswell");
            head.setIcon(new ImageIcon(this.getScaledPhoto(new ImageIcon(currentDriver.getDriverPhoto().getPath()), 150, 150)));
            runNo.requestFocus();
        } else if (e.getSource() == save) {
            if(allFieldsComplete()){
                String driverNum = driverNumber.getText(),
                       runNumber = runNo.getText(),
                       regPlate = reg.getText(),
                       miles = mileage.getText(),
                       hoursAtWork = hours.getText(),
                       outboundColections = oCol.getText(),
                       outBoundDeliveries = oDel.getText(),
                       sucessfulDeliveries = sDel.getText(),
                       cardedDeliveries = cDel.getText(),
                       rejectedDeliveries = rejDel.getText(),
                       badWeatherDeliveries = bwDel.getText(),
                       cantFindAddressDeliveries = cfaDel.getText(),
                       policeCivilDeliveries = pcDel.getText(),
                       scheduledCollections = sCol.getText(),
                       unscheduledCollections = uCol.getText(),
                       nilToCollectCollections = ntcCol.getText(),
                       cardedCollections = cCol.getText(),
                       badWeatherCollections = bwCol.getText(),
                       cantFainAddressCollections = cfaCol.getText(),
                       policeCivilCollections = pcCol.getText();
                       
                       DebriefData debriefData = currentDriver.getDebriefData(); 
                       if(debriefData == null)debriefData = new DebriefData();
                if(this.setCurrentDriver(driverNum)){
                     debriefData.createNewDebrief(runNumber, regPlate, Integer.parseInt(miles),
                             Integer.parseInt(hoursAtWork),
                             Integer.parseInt(outboundColections),
                             Integer.parseInt(outBoundDeliveries),
                             new Downtime(null,null,null),
                             Integer.parseInt(sucessfulDeliveries),
                             Integer.parseInt(cardedDeliveries),
                             Integer.parseInt(rejectedDeliveries),
                             Integer.parseInt(badWeatherDeliveries),
                             Integer.parseInt(cantFindAddressDeliveries),
                             Integer.parseInt(policeCivilDeliveries),
                             Integer.parseInt(scheduledCollections),
                             Integer.parseInt(unscheduledCollections),
                             Integer.parseInt(nilToCollectCollections),
                             Integer.parseInt(cardedCollections),
                             Integer.parseInt(badWeatherCollections),
                             Integer.parseInt(cantFainAddressCollections),
                             Integer.parseInt(policeCivilCollections));
                }
                
                        
                        
                        
                //TODO deal with downtime
            }
        }




    }

    private boolean fieldComplete(JTextField toCheck) {
        return toCheck.getText().equals("");
    }

    public void flagEmptyField(JTextField toFlag) {
        toFlag.setOpaque(true);
        toFlag.setBackground(new Color(220, 180, 180));
    }

    public void unflagEmptyField(JTextField toUnflag) {
        toUnflag.setOpaque(true);
        toUnflag.setBackground(Color.red);
    }

    private boolean allFieldsComplete() {

        boolean returnValue = true;
        
        if (!fieldComplete(driverNumber)) {
            flagEmptyField(driverNumber);
            returnValue = false;
        } else if (!fieldComplete(runNo)) {
            flagEmptyField(runNo);
            returnValue = false;
        } else if (!fieldComplete(reg)) {
            flagEmptyField(reg);
            returnValue = false;
        } else if (!fieldComplete(mileage)) {
            flagEmptyField(mileage);
            returnValue = false;
        } else if (!fieldComplete(driverNumber)) {
            flagEmptyField(driverNumber);
            returnValue = false;
        } else if (!fieldComplete(hours)) {
            flagEmptyField(hours);
            returnValue = false;
        } else if (!fieldComplete(oCol)) {
            flagEmptyField(oCol);
            returnValue = false;
        } else if (!fieldComplete(oDel)) {
            flagEmptyField(oDel);
            returnValue = false;
        } else if (!fieldComplete(sDel)) {
            flagEmptyField(sDel);
            returnValue = false;
        } else if (!fieldComplete(cDel)) {
            flagEmptyField(cDel);
            returnValue = false;
        } else if (!fieldComplete(rejDel)) {
            flagEmptyField(rejDel);
            returnValue = false;
        } else if (!fieldComplete(bwDel)) {
            flagEmptyField(bwDel);
            returnValue = false;
        } else if (!fieldComplete(cfaDel)) {
            flagEmptyField(cfaDel);
            returnValue = false;
        } else if (!fieldComplete(pcDel)) {
            flagEmptyField(pcDel);
            returnValue = false;
        } else if (!fieldComplete(sCol)) {
            flagEmptyField(sCol);
            returnValue = false;
        } else if (!fieldComplete(uCol)) {
            flagEmptyField(uCol);
            returnValue = false;
        } else if (!fieldComplete(ntcCol)) {
            flagEmptyField(ntcCol);
            returnValue = false;
        } else if (!fieldComplete(cCol)) {
            flagEmptyField(cCol);
            returnValue = false;
        } else if (!fieldComplete(bwCol)) {
            flagEmptyField(bwCol);
            returnValue = false;
        } else if (!fieldComplete(cfaCol)) {
            flagEmptyField(cfaCol);
            returnValue = false;
        } else if (!fieldComplete(pcCol)) {
            flagEmptyField(pcCol);
            returnValue = false;
        }
        if(returnValue == false)JOptionPane.showMessageDialog(this, "Please complete all fields", "", JOptionPane.ERROR_MESSAGE);
        return returnValue;
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
        if (e.getSource() == runNo) {
            if (currentDriver == null) {
                JOptionPane.showMessageDialog(this, "No driver selected!", "", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
