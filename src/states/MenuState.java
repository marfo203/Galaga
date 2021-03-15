package states;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import static constants.Constants.SCREEN_HEIGHT;
import static constants.Constants.SCREEN_WIDTH;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * This state represents the menu of the Game The main responsibility of this
 * class is to allow the user to swap state to the PlayState
 */
public class MenuState extends GameState {

	private String informationText;
	private Color fontColor;
	private Image bgImage;

	public MenuState(GameModel model) {
		super(model);
		informationText = "Press Enter To Play\nPress H for Highscore\nPress Escape to exit";
		fontColor = Color.WHITE;

		try {
			bgImage = new Image(new FileInputStream("Menuimage.jpg"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void draw(GraphicsContext g) {
		drawBg(g, bgImage);

		g.setFill(fontColor);
		g.setFont(new Font(30));
		g.fillText(informationText, SCREEN_WIDTH / 2 - 150, SCREEN_HEIGHT / 2 + 200);
	}

	@Override
	public void keyPressed(KeyEvent key, GraphicsContext gc) {
		System.out.println("Trycker på " + key.getText() + " i MenuState");

		if (key.getCode() == KeyCode.ENTER) {
			model.switchState(new ChooseShipState(model, gc));
		} else if (key.getCode() == KeyCode.ESCAPE) {
			System.exit(0);
		} else if (key.getCode() == KeyCode.H) {
			model.switchState(new HighScoreState(model));
		}
	}

	@Override
	public void update() {
	}

}
