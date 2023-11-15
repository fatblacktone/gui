/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jamp.gui;

import javax.media.GainControl;
import javax.swing.JSlider;
import javax.swing.JWindow;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Tone (anw8@aber.ac.uk)
 */
public class GainTest extends JWindow implements ChangeListener{

    private JSlider slider;
    private GainControl gain;

    public GainTest(){
        this.setSize(200, 50);
        slider = new JSlider(0, 100, 50);
        
        this.add(slider);
        slider.addChangeListener(this);
        this.setVisible(true);

    }

    public void addGain(GainControl gain){
        this.gain = gain;

    }

    public void stateChanged(ChangeEvent e) {
        if(e.getSource()==slider){
            JSlider source = (JSlider) e.getSource();
            float level = ((float)source.getValue())/100;
            gain.setLevel(level);
        }
    }

}
