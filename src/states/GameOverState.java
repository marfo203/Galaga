package states;

import static constants.Constants.SCREEN_HEIGHT;
import static constants.Constants.SCREEN_WIDTH;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import main.GameFrame;
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
		
		SaveScore();
	}

	public void SaveScore() {
		
	}

	@Override
	public void update() {
	}

	@Override
	public void draw(GraphicsContext g, GameFrame gameFrame) {
		drawBg(g, color, gameFrame);
		
		g.drawImage(gameOver, SCREEN_WIDTH / 4 + 20, SCREEN_HEIGHT / 3);
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
