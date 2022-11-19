/* Author: NgTienHungg */
package game.state;

import java.awt.Graphics2D;
import java.awt.GridLayout;
import javax.swing.JPanel;

public abstract class GameState extends JPanel {

    public static boolean IsChanging;
    protected GameStateType type;

    protected GameState(GameStateType type) {
        super(new GridLayout(1, 1));
        this.type = type;
    }

    public abstract void update();

    public abstract void render(Graphics2D g);

    public GameStateType getType() {
        return type;
    }

    // factory method
    public static GameState createState(GameStateType type) {
        GameState state = null;
        switch (type) {
            case Intro ->
                state = new GSIntro(GameStateType.Intro);
            case Menu ->
                state = new GSMenu(GameStateType.Menu);
            case Play ->
                state = new GSPlay(GameStateType.Play);
            case About ->
                state = new GSAbout(GameStateType.About);
            case Win ->
                state = new GSWin(GameStateType.Win);
        }
        return state;
    }
}
