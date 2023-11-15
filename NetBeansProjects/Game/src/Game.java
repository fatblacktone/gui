import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Game {

    static int x = 0, y = 0;
    static Image background1, background2, background3;
    static final int CHAR_WIDTH = 10, CHAR_HEIGHT = 10;

    public static void main(String[] args) throws IOException {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel panel = new JPanel() {

            @Override
            public void paintComponent(Graphics g) {

                g.drawImage(background1, x, 0, this);
                g.drawImage(background2, x/2, 0, this);
                g.drawImage(background3, x/3, 0, this);

                g.fillRect(x, y, CHAR_WIDTH, CHAR_HEIGHT);
            }
        };

        frame.add(panel);

        // Load the images
        background1 = ImageIO.read(new java.io.File("image1.png")).getScaledInstance(-1, frame.getHeight(), Image.SCALE_SMOOTH);
        background2 = ImageIO.read(new java.io.File("image2.png")).getScaledInstance(-1, frame.getHeight(), Image.SCALE_SMOOTH);
        background3 = ImageIO.read(new java.io.File("image3.png")).getScaledInstance(-1, frame.getHeight(), Image.SCALE_SMOOTH);

        frame.addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {

                int key = e.getKeyCode();
                if(key == KeyEvent.VK_LEFT) x -= 5;
                else if(key == KeyEvent.VK_RIGHT) x += 5;
                else if(key == KeyEvent.VK_UP) y -= 5;
                else if(key == KeyEvent.VK_DOWN) y += 5;

                //Prevent character from leaving the screen
                x = Math.min(Math.max(x, 0), frame.getWidth() - CHAR_WIDTH);
                y = Math.min(Math.max(y, 0), frame.getHeight() - CHAR_HEIGHT);

                panel.repaint();
            }
        });

        frame.setFocusable(true);
        frame.pack();
        frame.setVisible(true);
    }
}