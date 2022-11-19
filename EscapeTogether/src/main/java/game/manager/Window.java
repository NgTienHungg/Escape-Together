/* Author: NgTienHungg */
package game.manager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import javax.swing.JFrame;

public class Window extends JFrame {

    public static final int WIDTH = 1200;
    public static final int HEIGHT = 750;
    public static final String TITLE = "Escape Together";
    public static final Color CLEAR = new Color(0, 20, 20);

    public Window(Game game) {
        super(TITLE);

        this.add(game);
        getContentPane().setPreferredSize(new Dimension(WIDTH, HEIGHT)); // chỗ này khá quan trọng -> vì nếu không, window sẽ nhỏ hơn kích thước mình set
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public void clear(Graphics2D g) {
        g.setColor(CLEAR);
        g.fillRect(0, 0, WIDTH, HEIGHT);
    }
}
