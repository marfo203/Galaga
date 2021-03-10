package states;

import static constants.Constants.SCREEN_HEIGHT;
import static constants.Constants.SCREEN_WIDTH;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import main.Player;

public class GameOverState extends GameState {

	private Image gameOver;
	private Color color = Color.BLACK;
	private Player player;

	public GameOverState(GameModel model, GraphicsContext gc, Player player) {
		super(model);
		
		this.player = player;
		
		try {
			gameOver = new Image(new FileInputStream("gameOver.png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void SaveScore() {
		
	}

	@Override
	public void update() {
	}

	@Override
	public void draw(GraphicsContext g) {
		drawBg(g, color);
		
		g.drawImage(gameOver, SCREEN_WIDTH / 4 + 20, SCREEN_HEIGHT / 3);
		
		g.setFill(Color.WHITE);
		g.setFont(new Font(30)); // Big letters
		// Print the information text, centered on the canvas
		g.fillText("Enter name: ", SCREEN_WIDTH / 4 - 40, SCREEN_HEIGHT / 1.5);
	}

	@Override
	public void keyPressed(KeyEvent key, GraphicsContext gc) {
	}

	@Override
	public void activate() {
	}

	@Override
	public void deactivate() {
	}

}