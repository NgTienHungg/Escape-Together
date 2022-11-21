/* Author: NgTienHungg */
package game.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import game.entity.Tag;
import game.entity.Character;
import game.manager.Game;
import game.component.Button;
import game.component.SoundButton;
import game.component.Vector2;
import game.resource.AudioManager;
import static game.resource.ImageManager.*;

public class GSMenu extends GameState {

    private Button continueButton;
    private Button newGameButton;
    private Button aboutButton;
    private SoundButton soundButton;

    private Character green1, green2;
    private int currentButton;

    // setting for buttons
    private Font textFont = new Font("Calibri", Font.CENTER_BASELINE, 35);

    public GSMenu(GameStateType type) {
        super(type);
        initButtons();
        green1 = new Character(0, 0, Tag.GreenCharacter);
        green2 = new Character(0, 0, Tag.GreenCharacter);
        renew();

        AudioManager.getInstance().themeMusic.play();
        Game.getInstance().sceneTransition.openScene();
    }

    private void initButtons() {
        continueButton = new Button(450, 330, 300, 60);
        continueButton.setRound(50, 50);
        continueButton.setActiveColor(new Color(0, 204, 102));
        continueButton.setText("CONTINUE");
        continueButton.setTextFont(textFont);
        continueButton.setTextPosition(520, 370);
        continueButton.setEvent(() -> {
            Game.getInstance().sceneTransition.closeScene(() -> {
                GameStateMachine.getInstance().changeState(GameStateType.Play);
            });
        });

        newGameButton = new Button(450, 420, 300, 60);
        newGameButton.setRound(50, 50);
        newGameButton.setActiveColor(new Color(255, 0, 102));
        newGameButton.setText("NEW GAME");
        newGameButton.setTextFont(textFont);
        newGameButton.setTextPosition(520, 460);
        newGameButton.setEvent(() -> {
            GSPlay.CurrentLevel = 1;
            Game.getInstance().sceneTransition.closeScene(() -> {
                GameStateMachine.getInstance().changeState(GameStateType.Play);
            });
        });

        aboutButton = new Button(450, 510, 300, 60);
        aboutButton.setRound(50, 50);
        aboutButton.setActiveColor(new Color(51, 153, 255));
        aboutButton.setText("ABOUT");
        aboutButton.setTextFont(textFont);
        aboutButton.setTextPosition(550, 550);
        aboutButton.setEvent(() -> {
            Game.getInstance().sceneTransition.closeScene(() -> {
                GameStateMachine.getInstance().changeState(GameStateType.About);
            });
        });

        soundButton = new SoundButton(580, 620, 40, 40);
    }

    private void updateActiveButton() {
        switch (currentButton) {
            case 1 -> {
                continueButton.enable();
                newGameButton.disable();
                aboutButton.disable();
                soundButton.disable();

                green1.transform.position = new Vector2(385, 335);
                green2.transform.position = new Vector2(765, 335);
            }
            case 2 -> {
                continueButton.disable();
                newGameButton.enable();
                aboutButton.disable();
                soundButton.disable();

                green1.transform.position = new Vector2(385, 425);
                green2.transform.position = new Vector2(765, 425);
            }
            case 3 -> {
                continueButton.disable();
                newGameButton.disable();
                aboutButton.enable();
                soundButton.disable();

                green1.transform.position = new Vector2(385, 515);
                green2.transform.position = new Vector2(765, 515);
            }
            case 4 -> {
                continueButton.disable();
                newGameButton.disable();
                aboutButton.disable();
                soundButton.enable();

                green1.transform.position = new Vector2(505, 615); // 575
                green2.transform.position = new Vector2(645, 615);
            }
        }
    }

    public void renew() {
        currentButton = 1;
        updateActiveButton();
        soundButton.renew();
    }

    @Override
    public void update() {
        if (Game.getInstance().keyboardInput.isKeyPressedOnce(KeyEvent.VK_UP) && currentButton > 1) {
            currentButton--;
            updateActiveButton();
        } else if (Game.getInstance().keyboardInput.isKeyPressedOnce(KeyEvent.VK_DOWN) && currentButton < 4) {
            currentButton++;
            updateActiveButton();
        }

        continueButton.update();
        newGameButton.update();
        aboutButton.update();
        soundButton.update();

        green1.update();
        green2.update();
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(SPR_background[0], 0, 0, 1200, 750, null);

        g.drawImage(SPR_title_escape, 430, 60, 350, 100, null);
        g.drawImage(SPR_title_together, 360, 160, 480, 100, null);

        continueButton.render(g);
        newGameButton.render(g);
        aboutButton.render(g);
        soundButton.render(g);

        green1.render(g);
        green2.render(g);
    }
}
