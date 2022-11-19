/* Author: NgTienHungg */
package game.state;

import java.util.ArrayList;
import game.manager.Game;

public class GameStateMachine {

    private static GameStateMachine instance;
    private ArrayList<GameState> states;
    private GameState nextState;

    private GameStateMachine() {
        states = new ArrayList<>();
    }

    public static GameStateMachine getInstance() {
        if (instance == null) {
            instance = new GameStateMachine();
        }
        return instance;
    }

    public GameState getCurrentState() {
        if (states.isEmpty()) {
            System.out.println("Current state is null");
            return null;
        }
        return states.get(states.size() - 1);
    }

    public GameState getNextState() {
        return nextState;
    }

    public void changeState(GameStateType type) {
        GameState screen = GameState.createState(type);
        changeState(screen);
    }

    public void changeState(GameState state) {
        nextState = state;
    }

    public void pushScreen() {
        states.add(nextState);
        Game.getInstance().add(GameStateMachine.getInstance().getCurrentState());
        nextState = null;
    }

    public void popState() {
        if (!states.isEmpty()) {
            states.remove(states.size() - 1);
        }
        Game.getInstance().add(GameStateMachine.getInstance().getCurrentState());
    }

    public boolean needToChangeState() {
        return nextState != null;
    }
}
