/* Author: NgTienHungg */
package game.input;

import java.awt.Point;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import static game.resource.ImageManager.*;

// hiện icon con trỏ chuột đi theo chuột trên màn hình, nhưng k đẹp lắm
public class Mouse {

    private BufferedImage icon;
    private Point pos;
    private MouseInput mouseInput;

    public Mouse(MouseInput input) {
        this.mouseInput = input;
        icon = SPR_mouse;
    }

    public void update() {
        pos = mouseInput.getCurrentPos();
    }

    public void render(Graphics2D g) {
        g.drawImage(icon, pos.x, pos.y, 30, 30, null);
    }
}
