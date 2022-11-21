/* Author: NgTienHungg */
package game.component;

import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.event.KeyEvent;
import game.manager.Game;
import game.resource.AudioManager;
import static game.resource.ImageManager.*;

public class SoundButton {

    private BufferedImage img;
    private Point pos, size;
    private boolean active;

    public SoundButton(int x, int y, int w, int h) {
        pos = new Point(x, y);
        size = new Point(w, h);
        renew();
    }

    public void renew() {
        if (AudioManager.AllowSound) {
            img = SPR_sound_on;
        } else {
            img = SPR_sound_off;
        }
    }

    public void enable() {
        AudioManager.AllowSound = true;
        img = colorImage(img, Color.YELLOW);
        active = true;
    }

    public void disable() {
        AudioManager.AllowSound = true;
        img = colorImage(img, Color.WHITE);
        active = false;
    }

    public void update() {
        if (!active) {
            return;
        }
        if (Game.getInstance().keyboardInput.isKeyPressedOnce(KeyEvent.VK_ENTER)) {
            if (AudioManager.AllowSound) {
                img = colorImage(SPR_sound_off, Color.YELLOW);
                AudioManager.getInstance().disable();
            } else {
                img = colorImage(SPR_sound_on, Color.YELLOW);
                AudioManager.getInstance().enable();
            }
        }
    }

    public void render(Graphics2D g) {
        g.drawImage(img, pos.x, pos.y, size.x, size.y, null);
    }
}
