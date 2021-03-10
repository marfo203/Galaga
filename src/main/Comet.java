package main;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import states.PlayState;

public class Comet extends Ship {

	private int speed = 2; // Constant speed. Maybe shange depending on score
	private boolean dead;
	private int height = 50;
	private int width = 50;
	private int posX;
	private int posY;
	Rectangle2D cometHitbox;
	private GraphicsContext gc;
	private Image image;
	private PlayState play;

	public Comet(int posX, int posY, int size, Image image, GraphicsContext gc, PlayState play) {
		super(posX, posY, size, image, gc);

		this.posX = posX;
		this.posY = posY;
		this.image = image;
		this.speed = speed;
		this.dead = dead;
		this.gc = gc;
		this.play = play;

	}

	public void update() {
		if (!dead) {
			gc.drawImage(image, posX - 300, posY - 650, height, width);
			cometHitbox = new Rectangle2D(posX - 300, posY - 650, height, width);
			posY += speed;
		}
		for (int i = 0; i < play.getComets().size(); i++) {
			if (play.getComets().get(i).posY >= 1500) {
				play.getComets().remove(i);
			}
		}

	}

	@Override
	public void CollisionCheck() {
	}

	public Rectangle2D getHitbox() {
		return cometHitbox;
	}

}
