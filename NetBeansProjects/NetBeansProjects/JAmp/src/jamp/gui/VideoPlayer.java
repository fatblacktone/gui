/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jamp.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.CannotRealizeException;
import javax.media.Control;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author Tone (anw8@aber.ac.uk)
 */
public class VideoPlayer extends JFrame{

    /**
     * The frame title.
     */
    private static final String FRAME_TITLE = "developerWorks JMF Tutorial " +
        "Media Player";

    /**
     * The panel title of the main control panel.
     */
    private static final String CONTROL_PANEL_TITLE = "Control Panel";

    // location and size variables for the frame.
    private static final int LOC_X = 100;
    private static final int LOC_Y = 100;
    private static final int HEIGHT = 500;
    private static final int WIDTH = 500;

    /**
     * The current player.
     */
    private Player player = null;

    /**
     * The tabbed pane for displaying controls.
     */
    private JTabbedPane tabPane = null;

    /**
     * Create an instance of the media frame.  No data will be displayed in the
     * frame until a player is set.
     */
    public VideoPlayer(String file) {
        super(FRAME_TITLE);
        setLocation(LOC_X, LOC_Y);
        setSize(WIDTH, HEIGHT);

        tabPane = new JTabbedPane();
        getContentPane().add(tabPane);
        try {
            this.setMediaLocator(new MediaLocator(new File(file).getAbsolutePath()));
        } catch (IOException ex) {
            Logger.getLogger(VideoPlayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoPlayerException ex) {
            Logger.getLogger(VideoPlayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CannotRealizeException ex) {
            Logger.getLogger(VideoPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Creates the main panel.  This panel will contain the following if they
     * exist:
     * - The visual component: where any visual data is displayed, i.e. a
     * movie uses this control to display the video.
     * - The gain component:   where the gain/volume may be changed.  This
     * is often * contained in the control panel component (below.)
     * - The control panel component: time and some extra info regarding
     * the media.
     */
    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();

        mainPanel.setLayout(gbl);

        boolean visualComponentExists = false;

        // if the visual component exists, add it to the newly created panel.
        if (player.getVisualComponent() != null) {
            visualComponentExists = true;
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1;
            gbc.weighty = 1;
            gbc.fill = GridBagConstraints.BOTH;
            mainPanel.add(player.getVisualComponent(), gbc);
        }

        // if the gain control component exists, add it to the new panel.
        if ((player.getGainControl() != null) &&
            (player.getGainControl().getControlComponent() != null)) {
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.weightx = 0;
            gbc.weighty = 1;
            gbc.gridheight = 2;
            gbc.fill = GridBagConstraints.VERTICAL;
            mainPanel.add(player.getGainControl().getControlComponent(), gbc);
        }

        // Add the control panel component if it exists (it should exists in
        // all cases.)
        if (player.getControlPanelComponent() != null) {
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.weightx = 1;
            gbc.gridheight = 1;

            if (visualComponentExists) {
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.weighty = 0;
            } else {
                gbc.fill = GridBagConstraints.BOTH;
                gbc.weighty = 1;
            }

            mainPanel.add(player.getControlPanelComponent(), gbc);
        }

        return mainPanel;
    }

    /**
     * Sets the media locator.  Setting this to a new value effectively
     * discards any Player which may have already existed.
     * @param locator the new MediaLocator object.
     * @throws IOException indicates an IO error in opening the media.
     * @throws NoPlayerException indicates no player was found for the
     * media type.
     * @throws CannotRealizeException indicates an error in realizing the
     * media file or stream.
     */
    public void setMediaLocator(MediaLocator locator) throws IOException,
        NoPlayerException, CannotRealizeException {

        // create a new player with the new locator.  This will effectively
        // stop and discard any current player.
        setPlayer(Manager.createRealizedPlayer(locator));
    }

    /**
     * Sets the player reference.  Setting this to a new value will discard
     * any Player which already exists.  It will also refresh the contents
     * of the pane with the components for the new player.  A null value will
     * stop the discard the current player and clear the contents of the
     * frame.
     */
    public void setPlayer(Player newPlayer) {
        // close the current player
        closeCurrentPlayer();

        player = newPlayer;

        // refresh the tabbed pane.
        tabPane.removeAll();

        if (player == null) return;

        // add the new main panel
        tabPane.add(CONTROL_PANEL_TITLE, createMainPanel());

        // add any other controls which may exist in the player.  These
        // controls should already contain a name which is used in the
        // tabbed pane.
        Control[] controls = player.getControls();
        for (int i = 0; i < controls.length; i++) {
            if (controls[i].getControlComponent() != null) {
                tabPane.add(controls[i].getControlComponent());
            }
        }
    }

    /**
     * Stops and closes the current player if one exists.
     */
    private void closeCurrentPlayer() {
        if (player != null) {
            player.stop();
            player.close();
        }
    }
}
