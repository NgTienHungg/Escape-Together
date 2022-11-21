/* Author: NgTienHungg */
package game.component;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import game.entity.GameObject;

public class SpriteRenderer {

    private GameObject gameObject;
    public BufferedImage sprite;

    public SpriteRenderer(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    public void render(Graphics2D g) {
        g.drawImage(sprite, (int) gameObject.transform.position.x, (int) gameObject.transform.position.y,
                (int) gameObject.transform.size.x, (int) gameObject.transform.size.y, null);
    }
}
