package main;

import java.io.FileInputStream;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
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
	private GameFrame gameFrame;

	public GameFrame(GameModel model, int width, int height) {
		// Create a new GamePanel and add's it to the frame
		panel = new GamePanel(model, width, height, gameFrame);
		this.getChildren().add(panel);
		gc = panel.getGC();

	}

	public void repaint() {
		panel.repaint(this);
	}

	public GraphicsContext getGC() {
		return gc;
	}
}
