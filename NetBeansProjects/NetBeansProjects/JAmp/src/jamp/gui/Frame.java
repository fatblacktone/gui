/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jamp.gui;

import jamp.media.SoundFile;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.LinkedList;
import javax.media.GainChangeEvent;
import javax.media.GainChangeListener;
import javax.media.GainControl;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Tone
 */
public class Frame extends JFrame implements ActionListener,
        MouseListener, MouseMotionListener, KeyListener, ChangeListener {

    // Instance variables

    private Dimension screensize;
    private JButton play,
            stop,
            back,
            forward,
            close,
            playList;
    private JTextArea trackTime,
            trackName;
    private LinkedList<File> tracks;
    private SoundFile player;
    private PlayList playListWindow;
    private JLayeredPane contentPane;
    private int mousePressedAtX, mousePressedAtY;
    private boolean mousePressed;
    protected boolean isPlaying;
    private int trackNumber = 0;
    private File currentTrack;
    private JSlider trackPosition,
                    gain;
    private int currentTimeInSeconds;
    private GainControl control;

    public Frame() {
        super("J Amp");
        //setIconImage(new ImageIcon(imgURL).getImage());
        
        setupComponents();
        setupThis();
        setupPlayList();
    }

    /**
     * This method sets up the main frame for the player
     * in turn it also creates the playlist window offscreen
     *
     */
    
    private void setupThis(){
        screensize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        this.setUndecorated(true);
        this.setLocation(screensize.width / 2 - 200, screensize.height / 2 - 100);        
        this.setContentPane(contentPane);
        this.setVisible(true);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    /**
     * This method calls the various methods used to build the
     * components of the player
     *
     */

    private void setupComponents(){
        contentPane = new JLayeredPane();
        contentPane.setBackground(new Color(40,40,40));
        contentPane.setOpaque(true);
        setupButtons();        
        setupDisplay();
        setupTrackPosition();
        setupVolumeControl();
    }

    /**
     * This method sets the gain control for the player
     *
     * @param control
     */

    public void addGain(GainControl control){
        this.control = control;
    }

    /**
     * This method sets up the track position scrill bar
     */

    private void setupTrackPosition(){
        trackPosition = new JSlider(0,100,0);
        trackPosition.setBounds(5,100,300,30);
        trackPosition.setBackground(new Color(40,40,40));
        trackPosition.setForeground(Color.red);

        trackPosition.addChangeListener(this);
        trackPosition.addMouseListener(this);
        trackPosition.setForeground(Color.gray);
        trackPosition.setEnabled(false);
        trackPosition.setOpaque(true);
        contentPane.add(trackPosition,0,0);
    }

    /**
     * This method sets up the volume control slider
     */

    private void setupVolumeControl(){
        gain = new JSlider(0, 100, 0);
        gain.setOrientation(JSlider.VERTICAL);
        gain.setBounds(305, 5, 40, 90);
        gain.addChangeListener(this);
        gain.setOpaque(true);
        gain.setBackground(new Color(40,40,40));
        gain.setMajorTickSpacing(10);
        gain.setPaintTicks(true);
        gain.addChangeListener(this);
        contentPane.add(gain,0,0);
    }

    /**
     * This method sets the current tracks length
     *
     * @param time
     */

    public void setTrackLength(int time){
        trackPosition.setMaximum(time);
    }

    /**
     * This method sets up the playlist window
     */

    private void setupPlayList() {
        playListWindow = new PlayList();
        playListWindow.setLocation(this.getX() + 400, this.getY());
        playListWindow.setPlayer(player);
        playListWindow.setFrame(this);

    }

    /**
     * This method sets up the track info area on the player
     */

    public void setupDisplay() {
        trackName = new JTextArea();
        trackName.setBounds(5, 5, 300, 20);
        trackName.setEditable(false);
        trackName.setBackground(Color.black);
        trackName.setForeground(Color.lightGray);
        contentPane.add(trackName, 0, 0);

        trackTime = new JTextArea();
        trackTime.setBackground(Color.black);
        trackTime.setForeground(Color.lightGray);
        trackTime.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
        trackTime.setBounds(5, 25, 300, 70);
        trackTime.setEditable(false);
        contentPane.add(trackTime, 0, 0);

    }

    /**
     * This method gets the current level of gain
     * @return
     */
    public float getVolume(){
        return (float) gain.getValue()/100;
    }

    /**
     * This method sets up the buttons used on the player
     */

    private void setupButtons() {
        close = new JButton(new ImageIcon("closeUnpressed.png"));
        close.addMouseListener(this);
        close.setBorderPainted(false);
        close.setBounds(365, 5, 30, 30);
        close.addActionListener(this);
        contentPane.add(close, 0, 0);

        play = new JButton(new ImageIcon("playUnpressed.png"));
        play.setBorderPainted(false);
        play.setBounds(5, 130, 200, 30);
        play.addMouseListener(this);
        play.addActionListener(this);
        contentPane.add(play, 0, 0);

        stop = new JButton(new ImageIcon("stopUnpressed.png"));
        stop.setBorderPainted(false);
        stop.setBounds(60, 165, 90, 30);
        stop.addMouseListener(this);
        stop.addActionListener(this);
        contentPane.add(stop, 0, 0);

        back = new JButton(new ImageIcon("backUnpressed.png"));
        back.setBorderPainted(false);
        back.setBounds(5, 165, 50, 30);
        back.addMouseListener(this);
        back.addActionListener(this);
        contentPane.add(back, 0, 0);

        forward = new JButton(new ImageIcon("forwardUnpressed.png"));
        forward.setBorderPainted(false);
        forward.setBounds(155, 165, 50, 30);
        forward.addActionListener(this);
        forward.addMouseListener(this);
        contentPane.add(forward, 0, 0);

        playList = new JButton(new ImageIcon("playlistUnpressed.png"));
        playList.setBorderPainted(false);
        playList.setBounds(210, 130, 90, 30);
        playList.addActionListener(this);
        playList.addMouseListener(this);
        contentPane.add(playList, 0, 0);
    }

    /**
     * This method plays a track if the play button is pressed
     *
     * @param track
     */

    private void play(int track) {
        if (isPlaying) {
            return;
        }
        if (tracks.size() == 0) {
            return;
        }
        if(trackNumber>tracks.size()){
            resetTrackNumber();
        }
        isPlaying = true;
        try {
            currentTrack = tracks.get(trackNumber);
            player = new SoundFile("file:" + currentTrack.getAbsolutePath(), trackNumber);
            playListWindow.setPlayer(player);
            player.setTheFrame(this);
            System.out.println("Playing:" + tracks.get(trackNumber).getName());
            
            player.start();
            
            
        } catch (ArrayIndexOutOfBoundsException ex) {
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    /**
     * This method is solely for windows systems and returns the three char
     * extension
     *
     * @param file
     * @return
     */

    public String getExtension(File file) {
        String fileString = file.getName();
        String nameAndExtension[] = fileString.split(".");
        return nameAndExtension[1];
    }

    /**
     * This method updates the track information display
     *
     * @param name
     * @param time
     * @param seconds
     */

    public void setDisplay(String name, String time, int seconds) {
        trackPosition.setValue(seconds);
        trackName.setText(name);
        trackTime.setText(time);
    }

    /**
     * This method sets the isPlaying boolean
     *
     * @param isPlaying
     */
    public void setPlaying(boolean isPlaying) {
        this.isPlaying = isPlaying;
        play(++trackNumber);
        
    }

    /**
     * This method updates the list of tracks to be used by the player
     *
     */

    public void updateTracks(){
        tracks = playListWindow.getTracks();
    }

    /**
     * This method tests if a track isPlaying
     *
     * @return
     */
    public  boolean isPlaying(){
        return isPlaying;
    }

    /**
     * This resets the current track number to 0
     */

    public void resetTrackNumber(){
        trackNumber = 0;
    }

    /**
     * This method sets the current track number
     *
     * @param newTrackNumber
     */

    public void setTrackNumber(int newTrackNumber){
        this.trackNumber = newTrackNumber;
    }

    /**
     * This method returns the current track number
     *
     * @return
     */

    public int getTrackNumber(){
        return trackNumber;
    }

    /**
     * This method returns the file name without the absolute path
     * 
     * @return
     */

    public String getCurrentTrackString(){
        return currentTrack.getName();
    }

    ///////////////////////////////////////////
    //          Action Listener              //
    ///////////////////////////////////////////
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == close) {
            System.exit(0);
        } else if (e.getSource() == play) {
            tracks = playListWindow.getTracks();
            this.play(trackNumber);

        } else if (e.getSource() == playList) {
            if (playListWindow.isVisible()) {
                playListWindow.setVisible(false);
            } else {
                playListWindow.setVisible(true);
            }
        }
        if (e.getSource() == stop) {
            if (player != null) {
                player.stopPlay();
                isPlaying = false;
            }
        } else if (e.getSource() == forward) {
            if (player != null) {
                player.stopPlay();
                isPlaying = false;
                play(++trackNumber);
            }
        } else if (e.getSource() == back) {
            if (player != null) {
                if(Integer.parseInt(player.getTimeElapsed().split(":")[1])>5){
                    player.stopPlay();
                    isPlaying = false;
                    play(trackNumber);
                    return;
                }
                player.stopPlay();
                isPlaying = false;
                if(trackNumber>0)play(--trackNumber);
                else play(trackNumber=tracks.size()-1);
            }
        }
    }

    ///////////////////////////////////////////
    //           Mouse Listener              //
    ///////////////////////////////////////////
    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        mousePressedAtX = e.getX();
        mousePressedAtY = e.getY();
        mousePressed = true;
        if(e.getSource()==play){
            play.setIcon(new ImageIcon("playPressed.png"));
        }
        else if(e.getSource()==close){
            close.setIcon(new ImageIcon("closePressed.png"));
        }
        else if(e.getSource()==stop){
            stop.setIcon(new ImageIcon("stopPressed.png"));
        }
        else if(e.getSource()==back){
            back.setIcon(new ImageIcon("backPressed.png"));
        }
        else if(e.getSource()==forward){
            forward.setIcon(new ImageIcon("forwardPressed.png"));
        }
         else if(e.getSource()==playList){
            playList.setIcon(new ImageIcon("playlistPressed.png"));
        }
         
    }

    public void mouseReleased(MouseEvent e) {
        mousePressed = false;
        if(e.getSource()==play){
            play.setIcon(new ImageIcon("playHigh.png"));
        }
        else if(e.getSource()==close){
            close.setIcon(new ImageIcon("closeHigh.png"));
        }
        else if(e.getSource()==stop){
            stop.setIcon(new ImageIcon("stopHigh.png"));
        }
        else if(e.getSource()==back){
            back.setIcon(new ImageIcon("backHigh.png"));
        }
        else if(e.getSource()==forward){
            forward.setIcon(new ImageIcon("forwardHigh.png"));
        }
         else if(e.getSource()==playList){
            playList.setIcon(new ImageIcon("playlistHigh.png"));
        }
         

    }

    public void mouseEntered(MouseEvent e) {
        if(e.getSource()==play){
            play.setIcon(new ImageIcon("playHigh.png"));
        }
        else if(e.getSource()==close){
            close.setIcon(new ImageIcon("closeHigh.png"));
        }
        else if(e.getSource()==stop){
            stop.setIcon(new ImageIcon("stopHigh.png"));
        }
        else if(e.getSource()==back){
            back.setIcon(new ImageIcon("backHigh.png"));
        }
        else if(e.getSource()==forward){
            forward.setIcon(new ImageIcon("forwardHigh.png"));
        }
         else if(e.getSource()==playList){
            playList.setIcon(new ImageIcon("playlistHigh.png"));
        }
    }

    public void mouseExited(MouseEvent e) {
        if(e.getSource()==play){
            play.setIcon(new ImageIcon("playUnpressed.png"));
        }
        else if(e.getSource()==close){
            close.setIcon(new ImageIcon("closeUnpressed.png"));
        }
        else if(e.getSource()==stop){
            stop.setIcon(new ImageIcon("stopUnpressed.png"));
        }
        else if(e.getSource()==back){
            back.setIcon(new ImageIcon("backUnpressed.png"));
        }
        else if(e.getSource()==forward){
            forward.setIcon(new ImageIcon("forwardUnpressed.png"));
        }
        else if(e.getSource()==playList){
            playList.setIcon(new ImageIcon("playlistUnpressed.png"));
        }
    }

    public void mouseDragged(MouseEvent e) {
        if (mousePressed) {
            this.setLocation(e.getXOnScreen() - mousePressedAtX, e.getYOnScreen() - mousePressedAtY);
            playListWindow.setLocation(this.getX() + 400, this.getY());
        }
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }



    public void stateChanged(ChangeEvent e) {
        
        if(e.getSource()==gain&&control!=null){
            JSlider source = (JSlider) e.getSource();
            float level = ((float)source.getValue())/100;
            control.setLevel(level);
        }
    }
}
