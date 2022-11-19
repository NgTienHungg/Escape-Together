/* Author: NgTienHungg */
package game.parallaxBG;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import static game.manager.Game.DELTA_TIME;

public class Layer {

    private BufferedImage img1, img2;
    private float x1, x2;
    private float speed;

    public Layer(BufferedImage image, float speed) {
        this.img1 = image;
        this.img2 = image;
        this.speed = speed;
        x1 = 0f;
        x2 = 1360f;
    }

    public void update() {
        x1 -= speed * DELTA_TIME;
        x2 -= speed * DELTA_TIME;

        if (x1 < x2 && x2 <= 0) {
            x1 = x2 + 1360;
        } else if (x2 < x1 && x1 <= 0) {
            x2 = x1 + 1360;
        }
    }

    public void render(Graphics2D g) {
        // gây tốn dữ liệu quá -> giảm FPS
        g.drawImage(img1, (int) x1, 0, 1360, 765, null);
        g.drawImage(img2, (int) x2, 0, 1360, 765, null);
    }
}
