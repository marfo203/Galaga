package states;

import static constants.Constants.SCREEN_HEIGHT;
import static constants.Constants.SCREEN_WIDTH;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import main.GameFrame;

public class HighScoreState extends GameState {

	private Color bgColor;
	private Color fontColor;
	private String informationText;

	public HighScoreState(GameModel model) {
		super(model);
		bgColor = Color.BLACK;
		fontColor = Color.WHITE;
		informationText = "Current High Scores:\nPress Escape to go\nback to the main menu";
		
	}

	@Override
	public void update() {
	}

	@Override
	public void draw(GraphicsContext g, GameFrame gameFrame) {
		drawBg(g, bgColor, gameFrame);
		g.setFill(fontColor);
		g.setFont(new Font(30)); 
		g.fillText(informationText, SCREEN_WIDTH / 2-150, SCREEN_HEIGHT / 2);
	}

	@Override
	public void keyPressed(KeyEvent key, GraphicsContext gc) {
		System.out.println("Trycker på " + key.getText() + " i HighScoreState");
		
		if (key.getCode() == KeyCode.ESCAPE)
			model.switchState(new MenuState(model));
	}

	@Override
	public void activate() {
	}

	@Override
	public void deactivate() {
	}

}
