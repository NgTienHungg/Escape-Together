/* Author: NgTienHungg */
package game.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import game.entity.Level;
import game.manager.Game;
import game.manager.Saver;
import game.resource.AudioManager;

public class GSPlay extends GameState {

    public static boolean IsPlaying;
    public static int CurrentLevel;

    private Level level;

    // new liên tục trong render() dẫn tới drop FPS, cache lại để tối ưu hơn
    private Font _font_ = new Font(Font.MONOSPACED, Font.CENTER_BASELINE, 16);
    private Color _color_ = new Color(41, 163, 163);

    public GSPlay(GameStateType type) {
        super(type);
        level = new Level(CurrentLevel);
    }

    public void levelUp() {
        System.out.println("GSPlay: Level up");
        level = new Level(++CurrentLevel);
        Saver.save();
    }

    public void restart() {
        System.out.println("GSPlay: Restart");
        level = new Level(CurrentLevel);
    }

    @Override
    public void update() {
        if (!GameState.IsChanging) {
            if (Game.getInstance().keyboardInput.isKeyPressedOnce(KeyEvent.VK_R)) {
                Game.getInstance().sceneTransition.closeScene(() -> {
                    ((GSPlay) (GameStateMachine.getInstance().getCurrentState())).restart();
                });
            } else if (Game.getInstance().keyboardInput.isKeyPressedOnce(KeyEvent.VK_ESCAPE)) {
                Game.getInstance().sceneTransition.closeScene(() -> {
                    GameStateMachine.getInstance().popState();
                    ((GSMenu) GameStateMachine.getInstance().getCurrentState()).renew();
                });
            } else if (Game.getInstance().keyboardInput.isKeyPressedOnce(KeyEvent.VK_M)) {
                if (AudioManager.AllowSound) {
                    AudioManager.getInstance().disable();
                } else {
                    AudioManager.getInstance().enable();
                }
            }
        }
        level.update();
    }

    @Override
    public void render(Graphics2D g) {
        level.render(g);

        g.setFont(_font_);
        g.setColor(_color_);

        g.drawString("PRESS ESC TO BACK HOME", 20, 30);
        g.drawString("PRESS R TO RESTART", 20, 50);
        g.drawString("PRESS M TO MUTE", 20, 70);

        g.drawString("LEVEL " + CurrentLevel, 1100, 30);
        g.drawString("HERO " + Level.NumOfHero, 1100, 50);
    }
}
