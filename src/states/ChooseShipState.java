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
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import main.GameFrame;
import main.Player;

public class ChooseShipState extends GameState {

	private Color bgColor = Color.BLACK;
	private Color fontColor = Color.WHITE;
	private Image xwing;
	private Image mfalcon;
	private Player player;
	private String informationText = "Press M for Millenium Falcon\nPress X for X-Wing";
	private double height = 175;
	private double width = 135;
	private int angle = 0;
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
		angle += 1;

	}

	private void rotate(GraphicsContext gc, double angle, double px, double py) {
		Rotate r = new Rotate(angle, px, py);
		gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
	}

	@Override
	public void draw(GraphicsContext g, GameFrame gameFrame) {
		drawBg(g, bgColor, gameFrame);

		g.setFill(fontColor);
		g.setFont(new Font(30));
		g.fillText(informationText, SCREEN_WIDTH / 2 - 180, SCREEN_HEIGHT / 2 - 50);
		drawRotatedImage(g, mfalcon, angle, SCREEN_WIDTH / 2 - 220, SCREEN_HEIGHT / 2 + 100);
		drawRotatedImage(g, xwing, angle, SCREEN_WIDTH / 2 + 80, SCREEN_HEIGHT / 2 + 100);
	}

	private void drawRotatedImage(GraphicsContext g, Image image, double angle, double topLeftX, double topLeftY) {
		g.save(); // saves the current state on stack, including the current transform
		rotate(g, angle, topLeftX + width / 2, topLeftY + height / 2);
		g.drawImage(image, topLeftX, topLeftY, width, height);
		g.restore(); // back to original state (before rotation)
	}

	@Override
	public void keyPressed(KeyEvent key, GraphicsContext gc) {
		System.out.println("Trycker på " + key.getText() + " i MenuState");

		if (key.getCode() == KeyCode.X) {
			player = new Player(SCREEN_WIDTH / 2, SCREEN_HEIGHT - 50, 30, xwing, gc, explosion, model);
			PlayState playstate = new PlayState(model, gc, player);
			model.switchState(playstate);
		} else if (key.getCode() == KeyCode.M) {
			model.switchState(new PlayState(model, gc, player));
			player = new Player(SCREEN_WIDTH / 2, SCREEN_HEIGHT - 50, 15, mfalcon, gc, explosion, model);
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
