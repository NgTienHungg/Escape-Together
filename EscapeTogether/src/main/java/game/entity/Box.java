/* Author: NgTienHungg */
package game.entity;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import static game.resource.ImageManager.*;

public class Box extends Chessman {

    private BufferedImage cursor;

    public Box(int row, int col, Tag tag) {
        super(row, col, tag);
        cursor = SPR_cursor;
    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);

        if (connecting) {
            sprRender.sprite = cursor;
            sprRender.render(g);
        }
    }
}
