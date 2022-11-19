/* Author: NgTienHungg */
package game.effect;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.AlphaComposite;
import game.manager.Window;
import static game.resource.ImageManager.*;
import game.state.GameState;

public class SceneTransition {

    private final int maxAlpha = 255, minAlpha = 0;
    private IAction endAction;

    private BufferedImage image;
    private boolean enable, fading;
    private int alpha, deltaAlpha = 10;

    public SceneTransition() {
        image = SPR_black;
        enable = false;
    }

    public boolean isEnable() {
        return enable;
    }

    public void openScene() {
        // black -> transparent
        enable = true;
        alpha = maxAlpha;
        fading = true;
        
        GameState.IsChanging = false;
    }

    public void closeScene(IAction action) {
        // transparent -> black
        enable = true;
        alpha = minAlpha;
        fading = false;

        // action when complete close
        endAction = action;
        
        GameState.IsChanging = true;
    }

    public void update() {
        if (enable) {
            if (fading) {
                alpha = Math.max(alpha - deltaAlpha, minAlpha);
                if (alpha == minAlpha) {
                    enable = false;
                }
            } else {
                alpha = Math.min(alpha + deltaAlpha, maxAlpha);
                if (alpha == maxAlpha) {
                    endAction.excute();
                    openScene();
                }
            }
            // dùng ImageManager.alphaImage() bị drop FPS
            // image = alphaImage(image, alpha);
        }
    }

    public void render(Graphics2D g) {
        if (enable) {
            // dùng cái này để vẽ alpha image
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) alpha / 255));
            g.drawImage(image, 0, 0, Window.WIDTH, Window.HEIGHT, null);
        }
    }
}
