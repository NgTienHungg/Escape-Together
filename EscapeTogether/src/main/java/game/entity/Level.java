/* Author: NgTienHungg */
package game.entity;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import game.manager.Game;
import game.state.GSPlay;
import game.state.GameStateMachine;
import game.state.GameStateType;

public class Level extends GameObject {

    public static final float X = 275, Y = 50;
    public static final float WIDTH = 650, HEIGHT = 650;
    public static final int ROW = 13, COL = 13;

    // reset in each level
    public static int NumOfHero;
    public static boolean LockMove;
    public static boolean FinishMove;
    public static boolean OpenKeyDoor;
    public static boolean StandOnSwitch;
    public static boolean OpenSwitchDoor;

    private TileMap tileMap;
    private Chessboard chessboard;
    private boolean isGameOver = false;

    public Level(int index) {
        super(Level.X, Level.Y, Level.WIDTH, Level.HEIGHT);
        Level.NumOfHero = 0;
        Level.LockMove = false;
        Level.FinishMove = false;
        Level.OpenKeyDoor = false;
        Level.StandOnSwitch = false;
        Level.OpenSwitchDoor = false;

        System.out.println("Level: " + index);
        Game.getInstance().sceneTransition.openScene();
        loadAndInitLevel(index);
    }

    private void loadAndInitLevel(int index) {
        try {
            String fileName = "data/level" + index + ".txt";
            Scanner scanner = new Scanner(new File(fileName));

            // init map
            int[][] dataMap = new int[ROW][COL];
            for (int i = 0; i < ROW; i++) {
                for (int j = 0; j < COL; j++) {
                    dataMap[i][j] = scanner.nextInt();
                }
            }
            tileMap = new TileMap(dataMap);

            // init board
            int[][] dataBoard = new int[ROW][COL];
            for (int i = 0; i < ROW; i++) {
                for (int j = 0; j < COL; j++) {
                    dataBoard[i][j] = scanner.nextInt();
                }
            }
            chessboard = new Chessboard(dataBoard);

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update() {

        // check keyboard input
        if (!Level.LockMove) {
            if (Game.getInstance().keyboardInput.isKeyPressedOnce(KeyEvent.VK_LEFT)) {
                chessboard.handleMoving(0, -1);
            } else if (Game.getInstance().keyboardInput.isKeyPressedOnce(KeyEvent.VK_RIGHT)) {
                chessboard.handleMoving(0, 1);
            } else if (Game.getInstance().keyboardInput.isKeyPressedOnce(KeyEvent.VK_UP)) {
                chessboard.handleMoving(-1, 0);
            } else if (Game.getInstance().keyboardInput.isKeyPressedOnce(KeyEvent.VK_DOWN)) {
                chessboard.handleMoving(1, 0);
            }

            if (Game.getInstance().keyboardInput.isKeyPressedOnce(KeyEvent.VK_SPACE)) {
                chessboard.handleConnecting();
            }
        }

        // check win
        if (NumOfHero == 0 && !isGameOver) {
            System.out.println("Level: You win!");
            isGameOver = true;
            Game.getInstance().sceneTransition.closeScene(() -> {
                if (GSPlay.CurrentLevel == 20) {
                    GameStateMachine.getInstance().changeState(GameStateType.Win);
                } else {
                    ((GSPlay) (GameStateMachine.getInstance().getCurrentState())).levelUp();
                }
            });
        }

        tileMap.update();
        chessboard.update();
    }

    @Override
    public void render(Graphics2D g) {
        tileMap.render(g);
        chessboard.render(g);
    }
}
