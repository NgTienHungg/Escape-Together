/* Author: NgTienHungg */
package game.manager;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import game.input.KeyboardInput;
import game.input.MouseInput;
import game.state.GameState;
import game.state.GameStateMachine;
import game.state.GameStateType;
import game.resource.ImageManager;
import game.effect.SceneTransition;

public class Game extends Canvas {

    public static final int FPS = 60;
    public static final float DELTA_TIME = 1f / FPS;

    public Window window;
    public ImageManager imageManager;
    public SceneTransition sceneTransition;
    public KeyboardInput keyboardInput;
    public MouseInput mouseInput;

    private static Game instance;

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    private Game() {
        window = new Window(this);
        imageManager = new ImageManager();
        sceneTransition = new SceneTransition();

        keyboardInput = new KeyboardInput();
        addKeyListener(keyboardInput);

        mouseInput = new MouseInput();
        addMouseListener(mouseInput);
        addMouseMotionListener(mouseInput);
        addMouseWheelListener(mouseInput);

        Saver.load();
        GameStateMachine.getInstance().changeState(GameStateType.Intro);
    }

    private void update() {
        if (GameStateMachine.getInstance().needToChangeState()) {
            GameStateMachine.getInstance().pushScreen();
        }
        GameStateMachine.getInstance().getCurrentState().update();

        keyboardInput.update();
        mouseInput.update();
        sceneTransition.update();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics2D g = (Graphics2D) bs.getDrawGraphics();

        // render
        window.clear(g);
        GameStateMachine.getInstance().getCurrentState().render(g);
        sceneTransition.render(g);

        g.dispose();
        bs.show();
    }

    public void run() {
        double drawInterval = 1e9 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        // calculate FPS
        long timer = 0;
        int drawCount = 0;

        while (true) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                render();
                delta--;
                drawCount++;
            }

            // show FPS
            if (timer >= 1e9) {
                System.out.println("FPS: " + drawCount);
                timer = 0;
                drawCount = 0;
            }
        }
    }

    public void add(GameState state) {
        window.add(state);
    }
}
