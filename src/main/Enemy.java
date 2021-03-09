package main;

import java.util.ArrayList;
import java.util.Random;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import states.PlayState;

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
	private PlayState play;
	private int shipIndex;
	private int i;

	public Enemy(double posX, double posY, int size, ArrayList<Image> images, GraphicsContext gc, PlayState play) {
		super(posX, posY, size, images);
		this.posX = posX;
		this.posY = posY;
		this.images = images;
		this.gc = gc;
		this.ship = images.get(2);
		this.play = play;

	}

	public void Shoot() {
		Random rand = new Random(); 
	      int upperbound = 200;
	       
	      int enemyShoot = rand.nextInt(upperbound); 
	      if (enemyShoot % 200 == 0) {
		Bullet bullet = new Bullet(this.posX-270 , this.posY-650, 1, gc);
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
		} else if (dead) {

			gc.drawImage(ship, posX - 300, posY - 650, width, height);

		}
	}

	private void remove() {

	}

	public void enemyMovement() {
		if (posX <= 860) {
			posX += 1;
			if (posX == 860) {
				this.posY += height;
				this.posX = 250;
			}
		}

		// posX += speed;

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

	public void takeDamage(int j) {
		this.ship = images.get(1);
		this.dead = true;
		this.shipIndex = j;
	}

	public ArrayList<Bullet> getEBullets() {
		// TODO Auto-generated method stub
		return bullets;
	}

}
