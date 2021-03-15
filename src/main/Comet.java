package main;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;
import states.PlayState;

/**
 * Draws rotating comets, creates the hitbox and handles the comets path.
 * 
 * @author Berggren
 *
 */

public class Comet extends Ship {

	private boolean dead;
	private int posX;
	private int posY;
	private Rectangle2D cometHitbox;
	private GraphicsContext gc;
	private Image image;
	private PlayState play;
	private int size;
	private double angle = 1;
	private int speed;

	public Comet(int posX, int posY, int size, Image image, GraphicsContext gc, PlayState play, int speed) {
		super(posX, posY, size, image, gc);

		this.posX = posX;
		this.posY = posY;
		this.image = image;
		this.speed = speed;
		this.gc = gc;
		this.play = play;
		this.size = size;

	}

	private void rotate(GraphicsContext gc, double angle, double px, double py) {
		Rotate r = new Rotate(angle, px, py);
		gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
	}

	public void update() {
		if (!dead) {
			drawRotatedImage(gc, image, angle, posX - 300, posY - 650);
			posY += speed;
			angle += 1;
		}
		for (int i = 0; i < play.getComets().size(); i++) {
			if (play.getComets().get(i).posY >= 1500) {
				play.getComets().remove(i);
			}
		}
	}

	private void drawRotatedImage(GraphicsContext gc, Image image, double angle, double topLeftX, double topLeftY) {
		gc.save(); // saves the current state on stack, including the current transform
		rotate(gc, angle, topLeftX + size / 2, topLeftY + size / 2);
		gc.drawImage(image, topLeftX, topLeftY, size, size);
		cometHitbox = new Rectangle2D(topLeftX, topLeftY, size, size);
		gc.restore(); // back to original state (before rotation)
	}

	@Override
	public void CollisionCheck() {
	}

	public Rectangle2D getHitbox() {
		return cometHitbox;
	}

	public int getHealth() {
		return 0;
	}

}
