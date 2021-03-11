package main;

import states.GameModel;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 * The GamePanel wraps a Canvas
 *
 * The main responsibilities of the GamePanel are:
 *      - Supplying the game with a GraphicsContext object for painting different states
 *      - Governing the size of the "game-surface".
 */
public class GamePanel extends Canvas {

	private GameModel model;
	private GraphicsContext gc;
	private GameFrame gameFrame;

    public GamePanel(final GameModel model, int width, int height, GameFrame gameFrame) {
        this.model = model;
        gc = this.getGraphicsContext2D();
        this.setWidth(width);
        this.setHeight(height);
        this.gameFrame = gameFrame;
    }

    public void repaint() {
    	model.draw(gc, gameFrame);
    }

	public GraphicsContext getGC() {
		return gc;
	}
}
