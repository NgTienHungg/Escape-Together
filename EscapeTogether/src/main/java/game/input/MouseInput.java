/* Author: NgTienHungg */
package game.input;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import javax.swing.event.MouseInputAdapter;

class ButtonState {

    public boolean pressed = false;     // is being pressed ?
    public boolean pressedOnce = false;	// has just been pressed ?
    public boolean released = false;	// has just been released ?
    public boolean clicked = false;	// has been clicked ?
    public MouseEvent data;
}

public class MouseInput extends MouseInputAdapter {

    private final int buttonCount = 3;

    private ButtonState[] buttons;

    private MouseWheelEvent wheel = null;

    private Point currentMousePosition = new Point();

    public MouseInput() {
        buttons = new ButtonState[buttonCount];
        for (int i = 0; i < buttonCount; i++) {
            buttons[i] = new ButtonState();
        }
    }

    public void update() {
        for (int i = 0; i < buttonCount; i++) {
            buttons[i].pressedOnce = false;
            buttons[i].released = false;
            buttons[i].clicked = false;
        }
        wheel = null;
    }

    public Point getCurrentPos() {
        return currentMousePosition;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int button = e.getButton() - 1;	// offset by -1
        if (button >= 0 && button < buttonCount) {
            buttons[button].clicked = true;
            buttons[button].data = e;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int button = e.getButton() - 1;	// offset by -1
        if (button >= 0 && button < buttonCount) {
            if (!buttons[button].pressed) {
                buttons[button].pressedOnce = true;
            }

            buttons[button].pressed = true;
            buttons[button].data = e;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int button = e.getButton() - 1;	// offset by -1
        if (button >= 0 && button < buttonCount) {
            buttons[button].pressed = false;
            buttons[button].released = true;
            buttons[button].data = e;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        currentMousePosition = e.getPoint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseMoved(e);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        wheel = e;
    }

    // button codes: 1 - left, 2 - middle (wheel button), 3 - right
    public ButtonState getButton(int button) {
        button -= 1;	// offset by -1
        if (button >= 0 && button < buttonCount) {
            return buttons[button];
        } else {
            return null;
        }
    }

    public boolean isMousePressedOnce(int button) {
        ButtonState btnState = getButton(button);
        if (btnState != null) {
            return btnState.clicked;
        }
        return false;
    }

    public MouseWheelEvent getWheel() {
        return wheel;
    }

    public Point getPosition() {
        return currentMousePosition;
    }
}
