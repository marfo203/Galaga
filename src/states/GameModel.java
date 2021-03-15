package states;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

/**
 * This class represents the current state of the game.
 *
 * This implementation of a state machine keeps a reference to the current state
 * (see /src/states/GameState).
 *
 * Please note: This is just one way to do it, there are several other ways and
 * none of them work for every case, so if you want to implement your own state
 * machine make sure that it can do all the operations that you need for your
 * project.
 *
 * To change state simply call the switchState(GameState nextState) function
 * passing a reference to some other gameState.
 *
 * Initial State: MenuState
 *
 */

public class GameModel {

	private GameState currentState;

	public GameModel() {
		// We start out in the MenuState.
		this.currentState = new MenuState(this);
	}

	public void switchState(GameState nextState) {
		currentState = nextState;
	}

	public void keyPressed(KeyEvent key, GraphicsContext gc) {
		currentState.keyPressed(key, gc);
	}

	public void update() {
		currentState.update();
	}

	public void draw(GraphicsContext g) {
		currentState.draw(g);
	}
}
