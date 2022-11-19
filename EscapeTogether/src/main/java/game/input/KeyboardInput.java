/* Author: NgTienHungg */
package game.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class KeyState {

    boolean wasPressed = false;
    boolean isPressed = false;
}

public class KeyboardInput implements KeyListener {

    private final int keyCount = 240;	// 0 .. 240 keycodes range

    private KeyState[] keys;

    public KeyboardInput() {
        keys = new KeyState[keyCount];
        for (int i = 0; i < keyCount; i++) {
            keys[i] = new KeyState();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // don't need that
    }

    @Override
    public synchronized void keyPressed(KeyEvent e) {
        int keycode = e.getKeyCode();
        if (keycode >= 0 && keycode < keyCount) {
            keys[keycode].isPressed = true;
        }
    }

    @Override
    public synchronized void keyReleased(KeyEvent e) {
        int keycode = e.getKeyCode();
        if (keycode >= 0 && keycode < keyCount) {
            keys[keycode].isPressed = false;
            keys[keycode].wasPressed = true;
        }
    }

    public synchronized void update() {
        for (int i = 0; i < keyCount; i++) {
            keys[i].wasPressed = keys[i].isPressed;
        }
    }

    // get key state
    public synchronized boolean isKeyPressed(int keycode) {
        if (keycode >= 0 && keycode < keyCount) {
            return keys[keycode].isPressed;
        } else {
            return false;
        }
    }

    // has key just been pressed?
    public synchronized boolean isKeyPressedOnce(int keycode) {
        if (keycode >= 0 && keycode < keyCount) {
            return (keys[keycode].isPressed && !keys[keycode].wasPressed);
        } else {
            return false;
        }
    }

    // has key just been released?
    public synchronized boolean isKeyReleased(int keycode) {
        if (keycode >= 0 && keycode < keyCount) {
            return (!keys[keycode].isPressed && keys[keycode].wasPressed);
        } else {
            return false;
        }
    }
}
