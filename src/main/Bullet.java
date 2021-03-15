package main;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * This class creates both enemy and player bullets and sets
 * the position according to player or enemy positions.
 * @author Berggren
 *
 */

public class Bullet {
	private boolean hitTarget = false;
	
	private double posX;
	private double posY;
	
	private int speed = 5; // Constant speed. Maybe change depending on score
	private int direction;
	private int height = 20;
	private int width = 2;
	
	private Rectangle2D bulletHitbox;
	private GraphicsContext gc;

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
				gc.setFill(Color.LIMEGREEN);
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
