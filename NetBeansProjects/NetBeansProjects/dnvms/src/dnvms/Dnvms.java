/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dnvms;

import dnvms.GUI.Frame;
import dnvms.GUI.ImagePanel;
import dnvms.GUI.ReviewFrame;
import dnvms.calander.GUI.CalenderPane;
import dnvms.fileOperators.FileReader;
import dnvms.fileOperators.FileWrite;
import dnvms.objects.Driver;
import dnvms.objects.User;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JFrame;

/**
 *
 * @author Mathew Keegan
 */
public class Dnvms {
    
    private static User[] users;
    private static Driver[] drivers;
    
    private static void loadUsers(){
        FileReader reader = new FileReader();
        users = reader.readUsers();
    }
    
    private static void loadDrivers(){
        FileReader reader = new FileReader();
        drivers = reader.readDrivers();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //JFrame frame = new JFrame();
        //frame.setSize(640, 720);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //CalenderPane cPane = new CalenderPane();
        //frame.add(cPane);
        //frame.setVisible(true);
        
        FileWrite a = new FileWrite();
        loadUsers();      
        loadDrivers();
        
        Frame theframe= new Frame(users,drivers);
        ArrayList<Integer> dncTest = new ArrayList<>();
        dncTest.add(new Integer(50));
        dncTest.add(new Integer(43));
        dncTest.add(new Integer(54));
        dncTest.add(new Integer(62));
        dncTest.add(new Integer(50));
        dncTest.add(new Integer(47));
        
        
        
        
        
        ReviewFrame rf = new ReviewFrame(null);
         rf.drawLineGraph(dncTest, Color.green, ImagePanel.Graph_Type.DELIVERIES);
         
         
         dncTest = new ArrayList<>();
         dncTest.add(new Integer(1));
         dncTest.add(new Integer(0));
         dncTest.add(new Integer(1));
         dncTest.add(new Integer(2));
         dncTest.add(new Integer(0));
         dncTest.add(new Integer(2));
         rf.drawLineGraph(dncTest, Color.red, ImagePanel.Graph_Type.DELIVERIES);
         
         dncTest = new ArrayList<>();
         dncTest.add(new Integer(10));
         dncTest.add(new Integer(7));
         dncTest.add(new Integer(12));
         dncTest.add(new Integer(4));
         dncTest.add(new Integer(6));
         dncTest.add(new Integer(10));
         rf.drawLineGraph(dncTest, Color.green, ImagePanel.Graph_Type.COLLECTIONS);
         
         dncTest = new ArrayList<>();
         dncTest.add(new Integer(3));
         dncTest.add(new Integer(0));
         dncTest.add(new Integer(1));
         dncTest.add(new Integer(4));
         dncTest.add(new Integer(0));
         dncTest.add(new Integer(1));
         rf.drawLineGraph(dncTest, Color.red, ImagePanel.Graph_Type.COLLECTIONS);
         
         dncTest = new ArrayList<>();
         dncTest.add(new Integer(180));
         dncTest.add(new Integer(185));
         dncTest.add(new Integer(190));
         dncTest.add(new Integer(193));
         dncTest.add(new Integer(243));
         dncTest.add(new Integer(187));
         rf.drawLineGraph(dncTest, Color.red, ImagePanel.Graph_Type.MILES);
         
         dncTest = new ArrayList<>();
         dncTest.add(new Integer(12));
         dncTest.add(new Integer(10));
         dncTest.add(new Integer(11));
         dncTest.add(new Integer(12));
         dncTest.add(new Integer(7));
         dncTest.add(new Integer(12));
         rf.drawLineGraph(dncTest, Color.red, ImagePanel.Graph_Type.HOURS);
    }
}
