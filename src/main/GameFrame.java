package main;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;
import states.GameModel;

/**
 * This class Wraps a HBox: A HBox is a second level JavaFX container used
 * organize contents in the window (Stage/Scene).
 *
 * The GameFrame, in this case, has the job of creating the game panel, and
 * adding it to itself in order for it to show.
 *
 */
public class GameFrame extends HBox {
	private GamePanel panel;
	private GraphicsContext gc;

	public GameFrame(GameModel model, int width, int height) {
		// Create a new GamePanel and add's it to the frame
		panel = new GamePanel(model, width, height);
		this.getChildren().add(panel);
		gc = panel.getGC();
	}

	public void repaint() {
		panel.repaint();
	}

	public GraphicsContext getGC() {
		return gc;
	}
}
