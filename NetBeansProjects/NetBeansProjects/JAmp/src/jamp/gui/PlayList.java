/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jamp.gui;

import jamp.media.SoundFile;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JWindow;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Tone (anw8@aber.ac.uk)
 */
public class PlayList extends JWindow implements ActionListener, MouseMotionListener, MouseListener, ListSelectionListener {

    private JButton add,
            addDir,
            addFile,
            remove,
            sort,
            randomize,
            order;
    private JPanel addPanel,
            sortPanel;
    private JScrollPane scrollPane;
    private DList playList;
    private JLayeredPane contentPane;
    private DefaultListModel listModel;
    private LinkedList<File> tracks;
    private SoundFile player;
    private int toBeMoved;
    private int movedTo;
    private boolean pressedOnPlayList;
    private Frame theFrame;

    public PlayList() {
        this.setSize(150, 500);
        //this.setUndecorated(true);
        contentPane = new JLayeredPane();
        contentPane.setOpaque(true);
        contentPane.setBackground(Color.black);
        this.setContentPane(contentPane);
        listModel = new DefaultListModel();
        playList = new DList(listModel);
        playList.setOpaque(true);
        playList.setBackground(Color.lightGray);
        playList.addListSelectionListener(this);
        scrollPane = new JScrollPane(playList);
        scrollPane.setBounds(5, 5, 140, 450);
        contentPane.add(scrollPane, 0, 0);
        setupButtons();
        tracks = new LinkedList<File>();
    }

    public void setFrame(Frame theFrame) {
        this.theFrame = theFrame;
    }

    private void setupButtons() {
        add = new JButton(new ImageIcon("addUnpressed.png"));
        add.setBorderPainted(false);
        add.setBounds(5, 465, 45, 30);
        add.addMouseListener(this);
        add.addActionListener(this);
        contentPane.add(add, 0, 0);

        remove = new JButton(new ImageIcon("removeUnpressed.png"));
        remove.setBorderPainted(false);
        remove.setBounds(55, 465, 40, 30);
        remove.addActionListener(this);
        remove.addMouseListener(this);
        contentPane.add(remove, 0, 0);

        sort = new JButton(new ImageIcon("sortUnpressed.png"));
        sort.setBorderPainted(false);
        sort.setBounds(100, 465, 45, 30);
        sort.addMouseListener(this);
        sort.addActionListener(this);
        contentPane.add(sort, 0, 0);
    }

    public File getFile() {
        File file;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new FileNameExtensionFilter("MP3 files", "mp3"));

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.CANCEL_OPTION) {
            return null;
        } else {
            return fileChooser.getSelectedFile();
        }
    }

    public void setPlayer(SoundFile player) {
        this.player = player;
    }

    public File getDir() {
        File file;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Directory", "mp3"));
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.CANCEL_OPTION) {
            return null;
        } else {
            return fileChooser.getSelectedFile();
        }
    }

    public LinkedList<File> getTracks() {
        return tracks;
    }

    public void addDir(File dir) {
        if (dir == null) {
            return;
        }
        String[] files = dir.list();
        for (int i = 0; i < files.length; i++) {
            String ext = "";
            int string = files[i].length();
            while (files[i].charAt(string - 1) != '.') {
                ext += files[i].charAt(string - 1);
                string--;
            }
            ext = ext.toLowerCase();
            if (!(ext.equals("3pm") || ext.equals("vaw"))) {
                continue;
            }
            addTrack(new File(dir.getAbsolutePath() + "\\" + files[i]));
        }
    }

    public void addTrack(File file) {
        if (file == null) {
            return;
        }
        listModel.addElement(file.getName());
        tracks.add(file);
    }

    private File getFile(String fileName) {
        File temp = null;
        for (File file : tracks) {
            if (file.getName().equals(fileName)) {
                temp = file;
                break;
            }
        }
        return temp;
    }

    private void updateFrame(){
        if(theFrame.isPlaying){
            theFrame.setTrackNumber(findTrackNumber(theFrame.getCurrentTrackString()));
            theFrame.updateTracks();
        }
    }

    private int findTrackNumber(String fileName){
        int i=0;
        for(;i<tracks.size()-1;i++){
            if(tracks.get(i).getName().equals(fileName))break;
        }
        return i;
    }

    public void sortTracks() {
        listModel.removeAllElements();

        LinkedList<String> temp = new LinkedList<String>();
        for (File file : tracks) {
            temp.add(file.getName());
        }
        Collections.sort(temp);
        LinkedList<File> tempFiles = new LinkedList<File>();
        for (int i = 0; i < temp.size() - 1; i++) {
            tempFiles.add(getFile(temp.get(i)));
        }
        tracks = tempFiles;
        for (String s : temp) {
            listModel.addElement(s);
        }
        updateFrame();

    }

    private void randomizeTracks() {
        listModel.removeAllElements();

        LinkedList<String> temp = new LinkedList<String>();
        for (File file : tracks) {
            temp.add(file.getName());
        }
        Collections.shuffle(temp);
        LinkedList<File> tempFiles = new LinkedList<File>();
        for (int i = 0; i < temp.size() - 1; i++) {
            tempFiles.add(getFile(temp.get(i)));
        }
        tracks = tempFiles;
        for (String s : temp) {
            listModel.addElement(s);
        }
        updateFrame();
    }

    private void reOrderTracks() {
        Object[] trackStrings = listModel.toArray();

        LinkedList<File> tempFiles = new LinkedList<File>();
        for (int i = 0; i < trackStrings.length - 1; i++) {
            tempFiles.add(getFile((String) trackStrings[i]));
        }
        tracks = tempFiles;
        updateFrame();
    }

    ///////////////////////////////////////////
    //          Action Listener              //
    ///////////////////////////////////////////
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            addPanel = new JPanel(new GridLayout(2, 1));
            addPanel.setBounds(5, 435, 45, 60);

            addDir = new JButton(new ImageIcon("dirUnpressed.png"));
            addDir.setBorderPainted(false);
            addDir.addActionListener(this);
            addDir.addMouseListener(this);
            addPanel.add(addDir);

            addFile = new JButton(new ImageIcon("fileUnpressed.png"));
            addFile.setBorderPainted(false);
            addFile.addActionListener(this);
            addFile.addMouseListener(this);
            addPanel.add(addFile);

            contentPane.add(addPanel, 0, 1);
        } else if (e.getSource() == addFile) {
            addTrack(getFile());
            addPanel.setVisible(false);
        } else if (e.getSource() == addDir) {
            addDir(getDir());
            addPanel.setVisible(false);
        } else if (e.getSource() == remove) {
            listModel.removeAllElements();
            tracks.clear();

        } else if (e.getSource() == sort) {
            sortPanel = new JPanel(new GridLayout(2, 1));
            sortPanel.setBounds(100, 435, 45, 60);

            randomize = new JButton(new ImageIcon("randomUnpressed.png"));
            randomize.setBorderPainted(false);
            randomize.addActionListener(this);
            randomize.addMouseListener(this);

            order = new JButton(new ImageIcon("sortUnpressed.png"));
            order.setBorderPainted(false);
            order.addActionListener(this);
            order.addMouseListener(this);

            sortPanel.add(order);
            sortPanel.add(randomize);
            sort.setVisible(false);
            contentPane.add(sortPanel, 0, 1);
        } else if (e.getSource() == order) {
            sortTracks();
            sort.setVisible(true);
            sortPanel.setVisible(false);
        } else if (e.getSource() == randomize) {
            randomizeTracks();
            sort.setVisible(true);
            sortPanel.setVisible(false);
        }
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        if (e.getSource() == add) {
            add.setIcon(new ImageIcon("addPressed.png"));
        } else if (e.getSource() == addFile) {
            addFile.setIcon(new ImageIcon("filePressed.png"));
        } else if (e.getSource() == addDir) {
            addDir.setIcon(new ImageIcon("dirPressed.png"));
        } else if (e.getSource() == remove) {
            remove.setIcon(new ImageIcon("removePressed.png"));
        } else if (e.getSource() == sort) {
            sort.setIcon(new ImageIcon("sortPressed.png"));
        } else if (e.getSource() == randomize) {
            randomize.setIcon(new ImageIcon("randomPressed.png"));
        } else if (e.getSource() == order) {
            order.setIcon(new ImageIcon("sortPressed.png"));
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == add) {
            add.setIcon(new ImageIcon("addHigh.png"));
        } else if (e.getSource() == addFile) {
            addFile.setIcon(new ImageIcon("fileHigh.png"));
        } else if (e.getSource() == addDir) {
            addDir.setIcon(new ImageIcon("dirHigh.png"));
        } else if (e.getSource() == remove) {
            remove.setIcon(new ImageIcon("removeHigh.png"));
        } else if (e.getSource() == sort) {
            sort.setIcon(new ImageIcon("sortHigh.png"));
        } else if (e.getSource() == randomize) {
            randomize.setIcon(new ImageIcon("randomHigh.png"));
        } else if (e.getSource() == order) {
            order.setIcon(new ImageIcon("sortHigh.png"));
        }
    }

    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == add) {
            add.setIcon(new ImageIcon("addHigh.png"));
        } else if (e.getSource() == addFile) {
            addFile.setIcon(new ImageIcon("fileHigh.png"));
        } else if (e.getSource() == addDir) {
            addDir.setIcon(new ImageIcon("dirHigh.png"));
        } else if (e.getSource() == remove) {
            remove.setIcon(new ImageIcon("removeHigh.png"));
        } else if (e.getSource() == sort) {
            sort.setIcon(new ImageIcon("sortHigh.png"));
        } else if (e.getSource() == randomize) {
            randomize.setIcon(new ImageIcon("randomHigh.png"));
        } else if (e.getSource() == order) {
            order.setIcon(new ImageIcon("sortHigh.png"));
        }
    }

    public void mouseExited(MouseEvent e) {
        if (e.getSource() == add) {
            add.setIcon(new ImageIcon("addUnpressed.png"));
        } else if (e.getSource() == addFile) {
            addFile.setIcon(new ImageIcon("fileUnpressed.png"));
        } else if (e.getSource() == addDir) {
            addDir.setIcon(new ImageIcon("dirUnpressed.png"));
        } else if (e.getSource() == remove) {
            remove.setIcon(new ImageIcon("removeUnpressed.png"));
        } else if (e.getSource() == sort) {
            sort.setIcon(new ImageIcon("sortUnpressed.png"));
        } else if (e.getSource() == randomize) {
            randomize.setIcon(new ImageIcon("randomUnpressed.png"));
        } else if (e.getSource() == order) {
            order.setIcon(new ImageIcon("sortUnpressed.png"));
        }
    }

    public void valueChanged(ListSelectionEvent e) {
        reOrderTracks();
    }
}
