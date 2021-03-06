package main;

import javafx.scene.image.Image;

public class Enemy extends Ship {

	private int speed = 5; // Constant speed. Maybe shange depending on score
	private boolean dead = false;
	private double posX = 0;
	private double posY = 0;
	private int size;
	private Image enemy;

	public Enemy(double posX, double posY, int size, Image enemy) {
		super(posX, posY, size, enemy);
		this.posX = posX;
		this.posY = posY;
		this.size = size;
		this.enemy = enemy;

	}

	public void update() {
		if (!dead) {
			posX += speed;
			if (posX == 600)
				posY += size;
			
		}

	}

	public Bullet Shoot() {
		return new Bullet(this.posX, this.posY+(size/2), 1);
	}

}
