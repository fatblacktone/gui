/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dnvms.GUI;

import dnvms.objects.Driver;
import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Mathew Keegan
 */
public class ReviewFrame extends JFrame implements ActionListener,MouseListener,ChangeListener{
    
    private Driver driver;
    private ImagePanel mileageGraph,
                       hourGraph,
                       deliveriesGraph,
                       collectionsGraph,
                       grossProfitGraph,
                       netProfitGraph;
    
    private JTabbedPane tabbedPane;
    private JLayeredPane manePane;
    private JButton exit;
    private JLabel driverPhoto;
    private CheckboxPane checkboxPane;
    private JMenuBar menuBar;
    private JMenu tools,
                  file,
                  edit;
    private JMenu settings;
    private JMenuItem setDelPPP;
    
    private int delPPP = 200;
    
   
    
    
    public ReviewFrame(Driver driver){
        this.driver = driver;
        menuBar = new JMenuBar();
        tools = new JMenu("TOOLS");
        file = new JMenu("FILE");
        edit = new JMenu("EDIT");
        settings = new JMenu("SETTINGS");
        
        setDelPPP = new JMenuItem("Set Delivery PPP");
        setDelPPP.addActionListener(this);
        
        settings.add(setDelPPP);
        tools.add(settings);
        
        
        
        
        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(tools);
        this.setJMenuBar(menuBar);
        this.mileageGraph = new ImagePanel(680, 450,ImagePanel.Graph_Type.MILES);
        this.hourGraph = new ImagePanel(680, 450,ImagePanel.Graph_Type.HOURS);
        this.deliveriesGraph = new ImagePanel(680, 450,ImagePanel.Graph_Type.DELIVERIES);
        this.collectionsGraph = new ImagePanel(680, 450,ImagePanel.Graph_Type.DELIVERIES);
        this.grossProfitGraph = new ImagePanel(680, 450,ImagePanel.Graph_Type.GROSSPROFIT);
        this.netProfitGraph = new ImagePanel(680, 450,ImagePanel.Graph_Type.NETPROFIT);
        
        this.tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        this.tabbedPane.setBounds(150,30,680,475);
        tabbedPane.addChangeListener(this);
        this.manePane = new JLayeredPane();
        this.setSize(860, 600);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.add(manePane);
        
        manePane.add(tabbedPane);
        tabbedPane.addTab("Deliveries", deliveriesGraph);
        tabbedPane.addTab("Collections", collectionsGraph);
        tabbedPane.addTab("Milage", mileageGraph);
        tabbedPane.addTab("Hours", hourGraph);
        tabbedPane.addTab("Gross Profit", grossProfitGraph);
        tabbedPane.addTab("Net Profit", netProfitGraph);
        
        this.setVisible(true);
        mileageGraph.setUp();
        hourGraph.setUp();
        deliveriesGraph.setUp();
        collectionsGraph.setUp();
        grossProfitGraph.setUp();
        netProfitGraph.setUp();
        deliveriesGraph.drawDeliveriesKey();
        collectionsGraph.drawCollectionsKey();
        
        
        
        
        exit = new JButton("EXIT");
        exit.addActionListener(this);
        exit.setBounds(720,520,100,20);
        manePane.add(exit);
    }
    
    public void drawLineGraph(ArrayList<Integer> values,Color color, ImagePanel.Graph_Type type){
        if(type == ImagePanel.Graph_Type.DELIVERIES){
            deliveriesGraph.drawLineGraph(values, color);             
        }else if(type == ImagePanel.Graph_Type.HOURS){
            hourGraph.drawLineGraph(values, color);
        }else if(type == ImagePanel.Graph_Type.MILES){
            mileageGraph.drawLineGraph(values, color);
        }else if(type == ImagePanel.Graph_Type.COLLECTIONS){
            collectionsGraph.drawLineGraph(values, color);
        }else if(type == ImagePanel.Graph_Type.GROSSPROFIT){
            grossProfitGraph.drawLineGraph(values, color);
        }else if(type == ImagePanel.Graph_Type.NETPROFIT){
            netProfitGraph.drawLineGraph(values, color);
        }
        
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==exit){
            this.dispose();
        } else if (e.getSource() == setDelPPP) {
            int temp = delPPP;
            for (;;) {
                try {
                    delPPP = Integer.parseInt(JOptionPane.showInputDialog(this, 
                            "Please enter the \n price per parcel in pence:"));
                    ArrayList<Integer> tempValues = new ArrayList<>();
                    for(Integer tempInteger :deliveriesGraph.getFirstValues()){
                        int newValue = tempInteger * delPPP;
                        tempValues.add(newValue/100);
                    }
                    drawLineGraph(tempValues, Color.yellow, ImagePanel.Graph_Type.GROSSPROFIT);
                    break;
                } catch (NumberFormatException ex) {
                    if(delPPP == temp)break;
                }
            }
        }
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
    public void stateChanged(ChangeEvent e) {
        JTabbedPane sourceTabbedPane = (JTabbedPane) e.getSource();
        int index = sourceTabbedPane.getSelectedIndex();
        System.out.println("Tab changed to: " + sourceTabbedPane.getTitleAt(index));
        if(index == 0){
            checkboxPane = new CheckboxPane(ImagePanel.Graph_Type.DELIVERIES);
            checkboxPane.setBounds(20,100,140,250);
            manePane.add(checkboxPane);
        }else{
            if(checkboxPane!=null)checkboxPane.setVisible(false);
        }
    }
    
}
