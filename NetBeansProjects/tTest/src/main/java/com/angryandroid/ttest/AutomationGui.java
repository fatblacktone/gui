/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.angryandroid.ttest;

/**
 *
 * @author tone_
 */
import javax.swing.*;
import java.awt.*;

public class AutomationGui {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Automated Testing App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Main Panel
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Project Tree on the left
        JTree projectTree = new JTree();
        JScrollPane projectTreeScroll = new JScrollPane(projectTree);
        mainPanel.add(projectTreeScroll, BorderLayout.WEST);

        // Actions on the right
        String[] actions = {"Type", "Move Mouse", "Hover", "Click", "Recognize Object"};
        JList<String> actionList = new JList<>(actions);
        JScrollPane actionScroll = new JScrollPane(actionList);
        mainPanel.add(actionScroll, BorderLayout.EAST);

        // Project Window in the middle
        JTextArea projectWindow = new JTextArea();
        JScrollPane projectWindowScroll = new JScrollPane(projectWindow);
        mainPanel.add(projectWindowScroll, BorderLayout.CENTER);

        frame.add(mainPanel);
        frame.setVisible(true);
    }
}

