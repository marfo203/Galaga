package states;

import main.Comet;
import main.Enemy;
import main.GameFrame;
import main.Player;
import main.PowerUp;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import static constants.Constants.SCREEN_HEIGHT;
import static constants.Constants.SCREEN_WIDTH;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

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
	private Color bgColor;
	private Color fontColor;

	private Player player;
	private Enemy enemy;
	private Comet comet;
	private PowerUp powerUp;

	private Image tieFighter;
	private Image cometimage;
	private Image gameOver;
	private Image heart;

	private GraphicsContext gc;

	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private ArrayList<Comet> comets = new ArrayList<Comet>();

	private ArrayList<PowerUp> powerUps = new ArrayList<PowerUp>();
	private String name;
	private String c;

	public PlayState(GameModel model, GraphicsContext gc, Player player) {
		super(model);
		this.gc = gc;
		this.model = model;
		this.player = player;
		bgColor = Color.BLACK;
		fontColor = Color.WHITE;

		try {
			tieFighter = new Image(new FileInputStream("TieFighter.png"));
			gameOver = new Image(new FileInputStream("gameOver.png"));
			cometimage = new Image(new FileInputStream("Comet.png"));
			heart = new Image(new FileInputStream("heart.png"));
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find image-files!");
		}
		
		spawnEnemies();
		spawnComets();
		spawnPowerUps();

	}

	private void spawnPowerUps() {
		Random rand = new Random();
		int upperbound = 8;
		int lowerbound = 1;
		int cometamount = rand.nextInt(upperbound) + lowerbound;
		int speed = rand.nextInt(5) + 1;

		powerUp = new PowerUp((SCREEN_WIDTH / 2) + cometamount * 65, (SCREEN_HEIGHT - 50), speed, 1, heart, gc, this,
				speed);
		comets.add(powerUp);
		powerUps.add(powerUp);

	}

	public void StartGame() {
	}

	public void spawnEnemies() {
		Random rand = new Random();
		int upperbound = 20;
		int enemyamount = rand.nextInt(upperbound);

		if (enemies.size() <= 2) {
			Random rand1 = new Random();
			int upperbound1 = 5;
			int spawnlocation = rand1.nextInt(upperbound1);

			enemy = new Enemy((SCREEN_WIDTH / 2 - 325) + spawnlocation * 65, SCREEN_HEIGHT - 50 + spawnlocation * 65,
					spawnlocation, tieFighter, gc, this);
			enemies.add(enemy);
		}
	}

	public void spawnComets() {
		Random rand = new Random();
		int cometamount = rand.nextInt(8) + 1;
		int speed = rand.nextInt(5) + 1;
		int size = rand.nextInt(5) + 1;
		if (comets.size() <= 3) {

			comet = new Comet((SCREEN_WIDTH / 2) + cometamount * 65, (SCREEN_HEIGHT - 200), speed, cometimage, gc, this,

					speed);
			comets.add(comet);

		}
	}

	private void spawnPowerUps() {

		Random rand = new Random();
		int cometamount = rand.nextInt(8) + 1;
		int speed = rand.nextInt(5) + 1;
		int size = rand.nextInt(1000) + 1;
		if (size == 20 && powerUps.isEmpty()) {

			powerUp = new PowerUp((SCREEN_WIDTH / 2) + cometamount * 65, (SCREEN_HEIGHT - 200), speed, 1, heart, gc,
					this, speed);
			powerUps.add(powerUp);
		}

	}

	/**
	 * Draws information text to the screen.
	 */
	@Override
	public void draw(GraphicsContext g, GameFrame gameFrame) {
		drawBg(g, bgColor, gameFrame);

		g.setFill(fontColor);
		g.setFont(new Font(20)); // Big letters
		g.fillText("Score: " + player.getPoints() + "\nHealth: " + player.getHealth(), 20, 20);

		// Can also use:
		// g.setStroke(fontColor);
		// g.strokeText(informationText, SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2);

		// This could be a call to all our objects that we want to draw.
		// Using the tester simply to illustrate how it could work.
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).Shoot();
		}

		player.update();
		System.out.println("Calling player update");
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).update();
		}
		for (int i = 0; i < comets.size(); i++) {
			comets.get(i).update();
		}
		for (int i = 0; i < powerUps.size(); i++) {
			powerUps.get(i).update();
		}
		checkCollision();
		spawnEnemies();
		spawnComets();
		spawnPowerUps();
		if (player.getDead()) {
			g.drawImage(gameOver, SCREEN_WIDTH / 4 + 20, SCREEN_HEIGHT / 3);
//			model.switchState(new GameOverState(model, g));
			g.setFill(Color.WHITE);
			g.setFont(new Font(30)); // Big letters
			// Print the information text, centered on the canvas
			g.fillText("Your Score: " + player.getPoints() + "\nPress ENTER to continue", SCREEN_WIDTH / 4 - 40,
					SCREEN_HEIGHT / 1.5);
		}
	}

	@Override
	public void keyPressed(KeyEvent key, GraphicsContext gc) {
		System.out.println("Trycker på " + key.getCode() + " i PlayState");
		System.out.println("hej");
		if (!player.getDead()) {
			if (key.getCode() == KeyCode.ESCAPE) {
				model.switchState(new MenuState(model));
			}
			if ((key.getCode() == KeyCode.LEFT) || (key.getCode() == KeyCode.RIGHT)) {
				player.move(key);
			}
			if (key.getCode() == KeyCode.SPACE) {
				player.Shoot();
			}
		} else {
			if (key.getCode() == KeyCode.ENTER) {
				model.switchState(new GameOverState(model, gc, player));
			}
		}
	}

	@Override
	public void update() {
		// Here one would probably instead move the player and any
		// enemies / moving obstacles currently active.

	}

	private void checkCollision() {

		for (int i = 0; i < player.getpBullets().size(); i++) {
			for (int j = 0; j < enemies.size(); j++) {

				if (player.getpBullets().get(i).getBullethitbox().intersects(enemies.get(j).getHitbox())) {
					player.getpBullets().remove(i);
					enemies.get(j).takeDamage(j);
					player.Points(enemies.get(j));
					enemies.remove(j);
				}
			}
		}
		for (int j = 0; j < enemies.size(); j++) {
			for (int i = 0; i < enemies.get(j).getEBullets().size(); i++) {
				if (enemies.get(j).getEBullets().get(i).getBullethitbox().intersects(player.getHitbox())) {
					enemies.get(j).getEBullets().remove(i);
					player.CollisionCheck();
				}
			}
		}
		for (int j = 0; j < comets.size(); j++) {
			for (int i = 0; i < player.getpBullets().size(); i++) {
				if (player.getpBullets().get(i).getBullethitbox().intersects(comets.get(j).getHitbox())) {
					comets.remove(j);

					player.getpBullets().remove(i);
					player.Points(comets.get(j));

				}
			}
		}

		for (int i = 0; i < comets.size(); i++) {
			if (player.getHitbox().intersects(comets.get(i).getHitbox())) {
				player.CollisionCheck();
				comets.remove(i);
			}
		}

		for (int i = 0; i < powerUps.size(); i++) {
			if (player.getHitbox().intersects(powerUps.get(i).getHitbox())) {
				player.addHealth(powerUps.get(i).getHealth());
				player.addSpeed(powerUps.get(i).getSpeed());
				powerUps.remove(i);

			}
		}
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

	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	public ArrayList<Comet> getComets() {
		return comets;
	}

	public ArrayList<PowerUp> getPowerUps() {
		return powerUps;
	}
}
