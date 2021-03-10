package states;

import static constants.Constants.SCREEN_HEIGHT;
import static constants.Constants.SCREEN_WIDTH;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import main.Player;

public class ChooseShipState extends GameState {

	private Color bgColor = Color.BLACK;
	private Image xwing;
	private Image mfalcon;
	private Player player;
	private Image explosion;

	public ChooseShipState(GameModel model, GraphicsContext gc) {
		super(model);
				
		try {
			mfalcon = new Image(new FileInputStream("ship.png"));
			xwing = new Image(new FileInputStream("X-wing.png"));
			explosion = new Image(new FileInputStream("explosion.gif"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(GraphicsContext g) {
		drawBg(g, bgColor);
		
	}

	@Override
	public void keyPressed(KeyEvent key, GraphicsContext gc) {
		System.out.println("Trycker på " + key.getText() + " i MenuState");

		if (key.getCode() == KeyCode.X) {
			player = new Player(SCREEN_WIDTH / 2, SCREEN_HEIGHT - 50, 30, xwing, gc, explosion);
			PlayState playstate = new PlayState(model, gc, player);
			model.switchState(playstate);
		} else if (key.getCode() == KeyCode.M) {
			model.switchState(new PlayState(model, gc, player));
			player = new Player(SCREEN_WIDTH / 2, SCREEN_HEIGHT - 50, 15, mfalcon, gc, explosion);
			PlayState playstate = new PlayState(model, gc, player);
			model.switchState(playstate);
		} else if (key.getCode() == KeyCode.ESCAPE) {
			model.switchState(new MenuState(model));
	}
	}

	@Override
	public void activate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deactivate() {
		// TODO Auto-generated method stub
		
	}

}
