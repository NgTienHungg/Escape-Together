/* Author: NgTienHungg */
package game.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import game.resource.AudioManager;
import static game.entity.Chessboard.dirRow;
import static game.entity.Chessboard.dirCol;
import static game.entity.TileMap.DataMap;
import static game.resource.ImageManager.*;

public class Key extends Chessman {

    private BufferedImage cursor;

    public Key(int row, int col, Tag tag) {
        super(row, col, tag);
        cursor = SPR_cursor;
    }

    @Override
    protected void stop() {
        super.stop();

        // kiểm tra xem Key có đứng trước lock_door hay không
        for (int i = 0; i < 4; i++) {
            int r = row + dirRow[i];
            int c = col + dirCol[i];
            if (DataMap[r][c] == 4) {
                tag = Tag.Default;
                image = null;
                animation = null;
                connecting = false;

                // remove lock_door from tilemap
                Level.OpenKeyDoor = true;
                AudioManager.getInstance().getAudio("OpenDoor").play();
                return;
            }
        }
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
