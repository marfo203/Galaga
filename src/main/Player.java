package main;

import java.util.ArrayList;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Player extends Ship {
	private boolean dead;
	
	private double posX;
	private double posY;
	
	private int height = 45;
	private int width = 55;
	private int points = 0;
	private int shipspeed = 20;
	private int health;
	
	private GraphicsContext gc;
	
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	
	private Rectangle2D shipHitbox = new Rectangle2D(posX, posY, height, width);
	
	private Image ship;
	private Image explosion;

	public Player(double posX, double posY, int speed, Image ship, GraphicsContext gc, Image explosion, int health) {
		super(posX, posY, speed, ship, gc);

		this.posX = posX;
		this.posY = posY;
		this.gc = gc;
		this.ship = ship;
		this.shipspeed = speed;
		this.explosion = explosion;
		this.health = health;
		
	}

	public void Shoot() {
		Bullet bullet = new Bullet(this.posX + 17, this.posY - (height / 2), -1, gc);
		bullets.add(bullet);
	}

	public void update() {
		if (!dead) {

			gc.drawImage(ship, posX, posY - 30, height, width);
			shipHitbox = new Rectangle2D(posX, posY - 30, height, width);
			for (int i = 0; i < bullets.size(); i++) {
				bullets.get(i).update();
			}
		} else if (dead) {
			gc.drawImage(explosion, posX, posY - 30, height, width);
			for (int i = 0; i < bullets.size(); i++) {
				bullets.get(i).update();
			}
		}
	}

	public void move(KeyEvent key) {
		if (!dead) {
			if (key.getCode() == KeyCode.RIGHT) {
				this.posX += shipspeed;
			} else {
				this.posX -= shipspeed;
			}
			checkBoundaries();
		}
	}

	private void checkBoundaries() {
		if (posX < 0) {
			posX = 0;
		}
		if (posX > 550) {
			posX = 550;
		}
	}

	public void CollisionCheck() {
		if (!dead) {
			health -= 1;
			if (health == 0) {
				dead = true;
			}
		}
	}

	public Rectangle2D getHitbox() {
		return shipHitbox;
	}

	public ArrayList<Bullet> getpBullets() {
		return bullets;
	}

	public void Points(Ship ship) {
		if (ship instanceof Enemy) {
			addPoints(50);
		} else if (ship instanceof Comet) {
			addPoints(10);
		}
	}

	private void addPoints(int i) {
		this.points += i;
	}

	public int getPoints() {
		return this.points;
	}

	public int getHealth() {
		return this.health;
	}

	public boolean getDead() {
		return this.dead;
	}

	public void addHealth(int i) {
		this.health += i;
	}

	public void addSpeed(int speed) {
		if (this.shipspeed < 100) {
			this.shipspeed += speed;
		}
	}	
}
