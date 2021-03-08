package states;

import testing.Tester;
import main.Player;

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
 * This state represents the Playing State of the Game The main responsibility
 * of this class is to; - create Game Objects - update Game Objects - draw Game
 * Objects Game Objects are for instance; players, enemies, npc's, etc...
 *
 * The PlayState can also be thought off as a blue print where data is loaded
 * into some container from a file or some other type of data storage.
 *
 * It can also be created by some class responsible for object creation and then
 * passed to the PlayState as a parameter. This means all the PlayState has to
 * do is receive a list of objects, store them in some container and then for
 * every object in that container update and render that object.
 *
 * This way you can let the user define different Levels based on what
 * parameters are passed into the PlayState.
 */
public class PlayState extends GameState {
	/*
	 * The following three variables are just used to show that a change of state
	 * can be made. The same variables also exist in the MenuState, can you think of
	 * a way to make this more general and not duplicate variables?
	 */
	private String informationText;
	private Color bgColor;
	private Color fontColor;

	/* Class only used for testing */
	private Tester tester;
	private Player player;
	private Image ship;

	public PlayState(GameModel model, GraphicsContext gc) {
		super(model);
		informationText = "Press Escape To Return To The Menu";
		bgColor = Color.BLACK;
		fontColor = Color.BLUE;
		
		try {
			ship = new Image(new FileInputStream("ship.png"));
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find image-files!");
		}

		player = new Player(SCREEN_WIDTH/2, SCREEN_HEIGHT-50, 10, ship, gc);
	}

	/**
	 * Draws information text to the screen.
	 */
	@Override
	public void draw(GraphicsContext g) {
		drawBg(g, bgColor);

		g.setFill(fontColor);
		g.setFont(new Font(30)); // Big letters
		g.fillText(informationText, SCREEN_WIDTH / 3 - 150, SCREEN_HEIGHT / 3);
		// Can also use:
		// g.setStroke(fontColor);
		// g.strokeText(informationText, SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2);

		// This could be a call to all our objects that we want to draw.
		// Using the tester simply to illustrate how it could work.
		player.update();
	}

	@Override
	public void keyPressed(KeyEvent key, GraphicsContext gc) {
		System.out.println("Trycker på " + key.getCode() + " i PlayState");

		if (key.getCode() == KeyCode.ESCAPE)
			model.switchState(new MenuState(model));
		if ((key.getCode() == KeyCode.LEFT) || (key.getCode() == KeyCode.RIGHT))
			player.move(key);
		if(key.getCode() == KeyCode.SPACE) {
			player.Shoot();
		}
		
	}

	@Override
	public void update() {
		// Here one would probably instead move the player and any
		// enemies / moving obstacles currently active.
//		player.update();
	}

	/**
	 * We currently don't have anything to activate in the PlayState so we leave
	 * this method empty in this case.
	 */
	@Override
	public void activate() {
		
	}

	/**
	 * We currently don't have anything to deactivate in the PlayState so we leave
	 * this method empty in this case.
	 */
	@Override
	public void deactivate() {
		
	}

}
