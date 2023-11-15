/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dnvms.GUI;

import javax.swing.JCheckBox;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Mathew Keegan
 */
public class CheckboxPane extends JLayeredPane implements ChangeListener{
    
    private JCheckBox succesfulDeliveries,
                      failedDeliveries,
                      succesfulCollections,
                      unsuccesfulCollections;
    
    public CheckboxPane(ImagePanel.Graph_Type graphType){
        
            succesfulDeliveries = new JCheckBox("Successful"
                                                + "Deliveries");
            failedDeliveries = new JCheckBox("Un-Successful"
                                             + "Deliveries ");
            succesfulCollections = new JCheckBox("Successful"
                                                 + " collections");
            
            succesfulDeliveries.setHorizontalTextPosition(SwingConstants.CENTER);
            succesfulDeliveries.setVerticalTextPosition(SwingConstants.TOP);
            failedDeliveries.setHorizontalTextPosition( SwingConstants.CENTER );
            failedDeliveries.setVerticalTextPosition(SwingConstants.TOP);
            succesfulCollections.setHorizontalTextPosition(SwingConstants.CENTER);
            succesfulCollections.setVerticalTextPosition(SwingConstants.TOP);
            
            
            succesfulDeliveries.setBounds(0,20,120,40);
            succesfulCollections.setBounds(0,100,120,40);
            failedDeliveries.setBounds(0,60,120,40);
            
            this.add(succesfulDeliveries);
            this.add(failedDeliveries);
            this.add(succesfulCollections);
        
        
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        
    }
    
}
