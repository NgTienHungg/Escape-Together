/* Author: NgTienHungg */
package game.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import game.manager.Game;
import static game.resource.ImageManager.*;

public class GSWin extends GameState {

    private Font _font_ = new Font("Calibri", Font.CENTER_BASELINE, 35);
    private Color _color_ = new Color(153, 255, 51);

    public GSWin(GameStateType type) {
        super(type);
        Game.getInstance().sceneTransition.openScene();
    }

    @Override
    public void update() {
        if (!GameState.IsChanging && Game.getInstance().keyboardInput.isKeyPressedOnce(KeyEvent.VK_ESCAPE)) {
            Game.getInstance().sceneTransition.closeScene(() -> {
                GSPlay.CurrentLevel = 1;
                GameStateMachine.getInstance().popState();
                GameStateMachine.getInstance().popState();
            });
        }
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(SPR_background[2], 0, 0, 1200, 750, null);
        g.drawImage(SPR_title_congra, 275, 200, 650, 100, null);

        g.setFont(_font_);
        g.setColor(_color_);
        g.drawString("ĐÃ VƯỢT QUA HẾT TẤT CẢ CÁC MÀN RỒI À", 290, 350);
        g.drawString("CHÚC MỪNG NHÉ, BẠN CŨNG ĐỈNH ĐẤY", 300, 410);
        g.drawString("CẢM ƠN VÌ ĐÃ TRẢI NGHIỆM SẢN PHẨM", 300, 470);
        g.drawString("NHẤN ESC ĐỂ TRỞ LẠI MENU", 350, 530);
    }
}
