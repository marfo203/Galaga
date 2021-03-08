package main;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Enemy extends Ship {

	private int speed = 5; // Constant speed. Maybe shange depending on score
	private boolean dead = false;
	private double posX = 0;
	private double posY = 0;
	private int height = 50;
	private int width = 65;
	private ArrayList<Image> images;
	private GraphicsContext gc;

	public Enemy(double posX, double posY, int size, ArrayList<Image> enemy, GraphicsContext gc) {
		super(posX, posY, size, enemy);
		this.posX = posX;
		this.posY = posY;
		this.images = enemy;
		this.gc = gc;

	}

	public void update() {
		if (!dead) {
			gc.drawImage((Image) images.get(2), posX - 300, posY - 650, width, height);
			enemyMovement();
		}
	}
	public void enemyMovement() {
		posX += 3;
	}

//	public Bullet Shoot(GraphicsContext gc) {
//		return new Bullet(this.posX, this.posY+(height/2), 1, gc);
//	}

	@Override
	public void CollisionCheck() {		
	}

}
