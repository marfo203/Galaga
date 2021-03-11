package main;

import java.util.ArrayList;
import java.util.Random;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import states.PlayState;

public class Enemy extends Ship {

	private int speed = 1; // Constant speed. Maybe change depending on score
	private boolean dead;
	private double posX = 0;
	private double posY = 0;
	private int height = 50;
	private int width = 65;
	private GraphicsContext gc;

	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	public Rectangle2D enemyHitbox;
	private int difficulty = 100; // Lower number, more difficult
	private Image ship;

	public Enemy(double posX, double posY, int size, Image ship, GraphicsContext gc, PlayState play) {
		super(posX, posY, size, ship, gc);
		this.posX = posX;
		this.posY = posY;
		this.ship = ship;
		this.gc = gc;

	}

	public void Shoot() {
		Random rand = new Random();
		int upperbound = difficulty;
		int enemyShoot = rand.nextInt(upperbound);
		if (enemyShoot % difficulty == 0) {
			Bullet bullet = new Bullet(this.posX - 270, this.posY - 640, 1, gc);
			bullets.add(bullet);
		}
	}

	public void update() {
		if (!dead) {
			gc.drawImage(ship, posX - 300, posY - 650, width, height);
			enemyHitbox = new Rectangle2D(posX - 300, posY - 650, height, width);
			for (int j = 0; j < bullets.size(); j++) {
				bullets.get(j).update();
			}
			enemyMovement();
		}
	}

	public void enemyMovement() {
		if (posX <= 860) {
			posX += speed;
			if (posX >= 860) {
				this.posY += height;
				this.posX = 250;
			}
		}
	}

	@Override
	public void CollisionCheck() {
	}

	public Rectangle2D getHitbox() {
		return enemyHitbox;
	}

	public void takeDamage() {
		this.dead = true;
	}

	public ArrayList<Bullet> getEBullets() {
		return bullets;
	}

}
