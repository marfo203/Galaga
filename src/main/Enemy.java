package main;

import java.util.ArrayList;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Enemy extends Ship {

	private int speed = 3; // Constant speed. Maybe shange depending on score
	private boolean dead = false;
	private double posX = 0;
	private double posY = 0;
	private int height = 50;
	private int width = 65;
	private ArrayList<Image> images;
	private GraphicsContext gc;
	
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	public Rectangle2D enemyHitbox;
	private Image ship;

	public Enemy(double posX, double posY, int size, ArrayList<Image> images, GraphicsContext gc) {
		super(posX, posY, size, images);
		this.posX = posX;
		this.posY = posY;
		this.images = images;
		this.gc = gc;
		this.ship = images.get(2);
		

	}

	public void update() {
		
			
			gc.drawImage(ship, posX - 300, posY - 650, width, height);
			enemyHitbox = new Rectangle2D(posX-300, posY-650, height, width);
			if (!dead) {
				enemyMovement();
		}
	}
	public void enemyMovement() {

		posX += 1;

		posX += speed;

	}

//	public Bullet Shoot(GraphicsContext gc) {
//		return new Bullet(this.posX, this.posY+(height/2), 1, gc);
//	}

	@Override
	public void CollisionCheck() {		
	}

	public Rectangle2D getHitbox() {
		
		return enemyHitbox;
	}

	public void takeDamage() {
    this.ship = images.get(1);	
    this.dead = true;
     
	}

}
