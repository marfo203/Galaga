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

    public GamePanel(final GameModel model, int width, int height) {
        this.model = model;
        gc = this.getGraphicsContext2D();
        this.setWidth(width);
        this.setHeight(height);
    }

    public void repaint() {
    	model.draw(gc);
    }

	public GraphicsContext getGC() {
		return gc;
	}
}
