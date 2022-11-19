/* Author: NgTienHungg */
package game.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import game.component.Vector2;
import game.entity.Tag;
import game.entity.Character;
import game.manager.Game;
import java.awt.event.KeyEvent;
import static game.resource.ImageManager.*;

public class GSAbout extends GameState {

    private Font fontName = new Font("Calibri", Font.PLAIN, 20);
    private Font fontTitle = new Font("Calibri", Font.CENTER_BASELINE, 40);
    private Font fontText = new Font("Calibri", Font.CENTER_BASELINE, 25);
    private Color nameColor = new Color(255, 204, 0);
    private Color textColor = new Color(0, 153, 153);

    private Character blue, red, green;

    private boolean stateChanging = false;

    public GSAbout(GameStateType type) {
        super(type);
        initEntities();
    }

    private void initEntities() {
        blue = new Character(0, 0, Tag.BlueCharacter);
        red = new Character(0, 0, Tag.RedCharacter);
        green = new Character(0, 0, Tag.GreenCharacter);

        blue.transform.position = new Vector2(570f, 400f);
        red.transform.position = new Vector2(570f, 460f);
        green.transform.position = new Vector2(570f, 520f);
    }

    @Override
    public void update() {
        if (!GameState.IsChanging) {
            if (Game.getInstance().keyboardInput.isKeyPressedOnce(KeyEvent.VK_ESCAPE)) {
                Game.getInstance().sceneTransition.closeScene(() -> {
                    GameStateMachine.getInstance().popState();
                    ((GSMenu) GameStateMachine.getInstance().getCurrentState()).renew();
                });
            }
        }

        red.update();
        blue.update();
        green.update();
    }

    @Override
    public void render(Graphics2D g) {
//        g.drawImage(SPR_background[1], 0, 0, 1200, 750, null);

        g.drawImage(SPR_avatar[0], 70, 70, 100, 100, null);
        g.drawImage(SPR_avatar[1], 70, 200, 100, 100, null);
        g.drawImage(SPR_avatar[2], 70, 330, 100, 100, null);
        g.drawImage(SPR_avatar[3], 70, 450, 100, 100, null);
        g.drawImage(SPR_avatar[4], 70, 570, 100, 100, null);

        g.setFont(fontName);
        g.setColor(nameColor);
        g.drawString("B20DCCN297", 190, 110);
        g.drawString("Nguyễn Tiến Hùng", 190, 140);
        g.drawString("B20DCCN569", 190, 240);
        g.drawString("Nguyễn Như Quỳnh", 190, 270);
        g.drawString("B20DCCN330", 190, 370);
        g.drawString("Trương Quang Huy", 190, 400);
        g.drawString("B20DCCN510", 190, 490);
        g.drawString("Lê Quang Phúc", 190, 520);
        g.drawString("B20DCCN258", 190, 610);
        g.drawString("Phạm Trung Hiếu", 190, 640);

        blue.render(g);
        red.render(g);
        green.render(g);

        g.setColor(textColor);
        g.setFont(fontTitle);
        g.drawString("ESCAPE TOGETHER", 650, 200);
        g.setFont(fontText);
        g.drawString("BTL OOP - Class 03 - Group 13", 655, 250);

        g.drawString("--------------------------------------------------", 630, 300);

        g.setFont(fontTitle);
        g.drawString("TUTORIAL", 730, 370);
        g.setFont(fontText);
        g.drawString("Use UP and DOWN arrows to control", 630, 435);
        g.drawString("Use LEFT and RIGHT arrows to control", 630, 495);
        g.drawString("Use SPACE to connect surrounding objects", 630, 555);

        g.setColor(Color.RED);
        g.setFont(new Font("Calibri", Font.CENTER_BASELINE, 18));
        g.drawString("ESC TO BACK", 1080, 30);
    }
}
