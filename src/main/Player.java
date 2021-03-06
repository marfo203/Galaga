package main;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Player extends Ship {
	private boolean dead = false;
	private double posX;
	private double posY;
	private int size;
	private Image ship;
	private Image deadShip;
	private GraphicsContext gc;
	private int health = 3;

	public Player(double posX, double posY, int size, Image ship, GraphicsContext gc) {
		super(posX, posY, size, ship);

		this.posX = posX;
		this.posY = posY;
		this.size = size;
		this.ship = ship;
		this.gc = gc;
	}

	public Bullet Shoot() {
		return new Bullet(this.posX, this.posY-(size/2), -1);
	}

//	public void TakeDamage() {
//		if (ShotHit || CometHit) {
//		--health;
//	}
//	}

	public void update() {
		if (!dead) {
			gc.drawImage(ship, posX, posY, size, size * 1.5);
		} else {
			gc.drawImage(deadShip, posY, posX, size, size);
		}

	}
}
