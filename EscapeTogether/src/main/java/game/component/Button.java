/* Author: NgTienHungg */
package game.component;

import java.awt.Point;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import game.manager.Game;
import game.effect.IAction;
import game.state.GameState;
import static game.manager.Util.*;

public class Button {

    private Vector2 position, size, round;
    private IAction eventClick;

    private Color activeColor, inactiveColor;
    private boolean active;

    private String text;
    private Font textFont;
    private Color textColor;
    private Vector2 textPos;

    public Button(float x, float y, float w, float h) {
        this.position = new Vector2(x, y);
        this.size = new Vector2(w, h);
        this.round = new Vector2(0, 0);

        this.activeColor = Color.YELLOW;
        this.inactiveColor = Color.GRAY;
        this.active = false;

        this.text = "";
        this.textFont = new Font(Font.MONOSPACED, Font.CENTER_BASELINE, 10);
        this.textColor = Color.WHITE;
        this.textPos = new Vector2(0, 0);
    }

    public void enable() {
        this.active = true;
    }

    public void disable() {
        this.active = false;
    }

    public void setRound(float x, float y) {
        round = new Vector2(x, y);
    }

    public void setEvent(IAction action) {
        eventClick = action;
    }

    public void setActiveColor(Color enableColor) {
        this.activeColor = enableColor;
    }

    public void setInactiveColor(Color disableColor) {
        this.inactiveColor = disableColor;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTextFont(Font font) {
        this.textFont = font;
    }

    public void setTextColor(Color color) {
        this.textColor = color;
    }

    public void setTextPosition(float x, float y) {
        this.textPos = new Vector2(x, y);
    }

    public void update() {
        if (!active) {
            return;
        }
        if (!GameState.IsChanging && Game.getInstance().keyboardInput.isKeyPressedOnce(KeyEvent.VK_ENTER)) {
            eventClick.excute();
            return;
        }
        if (!GameState.IsChanging && Game.getInstance().mouseInput.isMousePressedOnce(1) == true) {
            Point mousePos = Game.getInstance().mouseInput.getPosition();
            if (inRange(mousePos.x, position.x, position.x + size.x) && inRange(mousePos.y, position.y, position.y + size.y)) {
                eventClick.excute();
            }
        }
    }

    public void render(Graphics2D g) {
        // fill background
        if (active) {
            g.setColor(activeColor);
        } else {
            g.setColor(inactiveColor);
        }
        g.fillRoundRect((int) position.x, (int) position.y, (int) size.x, (int) size.y, (int) round.x, (int) round.y);

        // draw text
        if (active) {
            g.setColor(textColor);
        } else {
            g.setColor(Color.WHITE);
        }
        g.setFont(textFont);
        g.drawString(text, textPos.x, textPos.y);
    }
}
