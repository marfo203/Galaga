package states;

import main.Comet;
import main.Enemy;
import main.HeartPowerUp;
import main.Player;
import main.PowerUp;
import main.SpeedPowerUp;
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
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class represents the playstate where the actual game is played. Handles
 * the creation of objects like enemies and powerups as well as handling
 * collisions between the objects.
 * 
 * @author Berggren
 *
 */
public class PlayState extends GameState {
	private Color fontColor;

	private Player player;
	private Enemy enemy;
	private Comet comet;
	private PowerUp powerUp;

	private Image tieFighter;
	private Image cometimage;
	private Image gameOver;
	private Image heart;
	private Image fast;
	private Image bgImage;

	private GraphicsContext gc;

	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private ArrayList<Comet> comets = new ArrayList<Comet>();
	private ArrayList<PowerUp> powerUps = new ArrayList<PowerUp>();

	public PlayState(GameModel model, GraphicsContext gc, Player player) {
		super(model);
		this.gc = gc;
		this.model = model;
		this.player = player;

		fontColor = Color.WHITE;

		try {
			tieFighter = new Image(new FileInputStream("TieFighter.png"));
			gameOver = new Image(new FileInputStream("gameOver.png"));
			cometimage = new Image(new FileInputStream("Comet.png"));
			heart = new Image(new FileInputStream("heart.png"));
			fast = new Image(new FileInputStream("fast.png"));
			bgImage = new Image(new FileInputStream("stars2.gif"));
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find image-files!");
		}

		spawnEnemies();
		spawnComets();
		spawnPowerUps();

	}

	public void spawnEnemies() {
		if (enemies.size() <= 2) {
			Random rand = new Random();
			int spawnlocation = rand.nextInt(5);
			int speed = rand.nextInt(3) + 1;

			enemy = new Enemy((SCREEN_WIDTH / 2 - 325) + spawnlocation * 65, SCREEN_HEIGHT - 50 + spawnlocation * 65,
					speed, tieFighter, gc, this);
			enemies.add(enemy);
		}
	}

	public void spawnComets() {
		Random rand = new Random();
		int cometamount = rand.nextInt(8) + 1;
		int speed = rand.nextInt(5) + 1;
		int size = rand.nextInt(70) + 50;
		if (comets.size() <= 3) {
			comet = new Comet((SCREEN_WIDTH / 2) + cometamount * 65, (SCREEN_HEIGHT - 200), size, cometimage, gc, this,
					speed);
			comets.add(comet);
		}
	}

	private void spawnPowerUps() {

		Random rand = new Random();
		int cometamount = rand.nextInt(8) + 1;
		int speed = rand.nextInt(8) + 1;
		int spawnfrequency = rand.nextInt(2000) + 1;
		if (spawnfrequency == 20 || spawnfrequency == 22 && powerUps.isEmpty()) {
			powerUp = new HeartPowerUp((SCREEN_WIDTH / 2) + cometamount * 65, (SCREEN_HEIGHT - 200), 1, heart, gc,
					this);
			powerUps.add(powerUp);
		}
		if (spawnfrequency == 21 && powerUps.isEmpty()) {
			powerUp = new SpeedPowerUp((SCREEN_WIDTH / 2) + cometamount * 65, (SCREEN_HEIGHT - 200), speed + 3, fast, gc,
					this);
			powerUps.add(powerUp);
		}
	}

	@Override
	public void draw(GraphicsContext g) {
		drawBg(g, bgImage);

		g.setFill(fontColor);
		g.setFont(new Font(20));
		g.fillText("Score: " + player.getPoints() + "\nHealth: " + player.getHealth(), 20, 20);

		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).Shoot();
		}

		player.update();

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
			g.setFill(Color.WHITE);
			g.setFont(new Font(30));
			g.fillText("Your Score: " + player.getPoints() + "\nPress ENTER to continue", SCREEN_WIDTH / 4 - 40,
					SCREEN_HEIGHT / 1.5);
		}
	}

	@Override
	public void keyPressed(KeyEvent key, GraphicsContext gc) {
		System.out.println("Trycker på " + key.getCode() + " i PlayState");
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
				SaveScoreToFile();
				model.switchState(new HighScoreState(model));
			}
		}
	}

	private void SaveScoreToFile() {
		try {
			FileWriter output = (new FileWriter("HighScores.txt", true));
			output.write(player.getPoints() + "\n");
			output.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update() {

	}

	private void checkCollision() {

		for (int i = 0; i < player.getpBullets().size(); i++) {
			for (int j = 0; j < enemies.size(); j++) {

				if (player.getpBullets().get(i).getBullethitbox().intersects(enemies.get(j).getHitbox())) {
					player.getpBullets().remove(i);
					enemies.get(j).takeDamage();
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
		Comet toRemove = null;
		for (Comet comet : comets) {
			for (int i = 0; i < player.getpBullets().size(); i++) {
				if (player.getpBullets().get(i).getBullethitbox().intersects(comet.getHitbox())) {
					System.out.println(comets.size());
					player.getpBullets().remove(i);
					player.Points(comet);
					toRemove = comet;
				}
			}
		}
		if (toRemove != null) {
			comets.remove(toRemove);
			toRemove = null;
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
