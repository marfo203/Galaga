package main;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Bullet {
	private GraphicsContext gc;
	private int speed = 5; // Constant speed. Maybe shange depending on score
	private boolean hitTarget = false;
	private double posX;
	private double posY;
	private int direction;
	private int height = 20;
	private int width = 2;
	private Rectangle2D bulletHitbox;

	public Bullet(double posX, double posY, int direction, GraphicsContext gc) {

		this.posX = posX;
		this.posY = posY;
		this.direction = direction; // Direction is -1 if player is shooting, +1 if enemy is shooting
		this.gc = gc;
	}

	public void update() {
		if (!hitTarget) {
			this.posY += speed * direction;
			bulletHitbox = new Rectangle2D(posX, posY, width, height);
			if (this.direction == -1) {
				gc.setFill(Color.LIMEGREEN); // Här kan vi ju använda bilder på något som skott istället men najs om man												// ser tydligt "goda" och "onda" skott.
			} else {
				gc.setFill(Color.RED);
			}
			gc.fillOval(this.posX, this.posY, width, height);
		}
	}
	
	public Rectangle2D getBullethitbox() {
		return bulletHitbox;
	}

	public void setBullethitbox(Rectangle2D bullethitbox) {
		this.bulletHitbox = bullethitbox;
	}

}
