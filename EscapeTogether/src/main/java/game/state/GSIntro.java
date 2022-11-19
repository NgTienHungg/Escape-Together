/* Author: NgTienHungg */
package game.state;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.AlphaComposite;
import game.manager.Window;
import static game.resource.ImageManager.*;
import static game.manager.Game.DELTA_TIME;

public class GSIntro extends GameState {

    private BufferedImage logoPRO, logoIT;
    private boolean fading = false;
    private int alpha = 60;

    private float waitDuration = 0.8f;
    private float timer = 0f;

    public GSIntro(GameStateType type) {
        super(type);
        logoPRO = SPR_logo_pro;
        logoIT = SPR_logo_it;
    }

    @Override
    public void update() {
        if (!fading) {
            alpha = Math.min(alpha + 3, 230);
            if (alpha == 230) {
                timer += DELTA_TIME;
                if (timer >= waitDuration) {
                    fading = true;
                }
            }
        } else {
            alpha = Math.max(alpha - 2, 0);
            if (alpha == 0) {
                fading = false;
                GameStateMachine.getInstance().changeState(GameStateType.Menu);
            }
        }
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Window.WIDTH, Window.HEIGHT);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) alpha / 255));
        g.drawImage(logoPRO, 160, 175, 400, 400, null);
        g.drawImage(logoIT, 650, 190, 370, 370, null);
    }
}
