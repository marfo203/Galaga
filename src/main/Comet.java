package main;

import java.util.ArrayList;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Comet extends Ship {

	private int speed = 5; // Constant speed. Maybe shange depending on score
	private boolean dead = false;
	private int height = 50;
	private int width = 50;
	private int posX;
	private int posY;
	private int size;
	Rectangle2D cometHitbox;
	private ArrayList<Image> comet;
	private GraphicsContext gc;

	public Comet(int posX, int posY, int size, ArrayList<Image> comet, GraphicsContext gc) {
		super(posX, posY, size, comet);

		this.posX = posX;
		this.posY = posY;
		this.size = size;
		this.comet = comet;
		this.speed = speed;
		this.dead = dead;
		this.gc = gc;

	}

	public void update() {
		if (!dead) {
			
			gc.drawImage(comet.get(4), posX, posY, size, size);
			cometHitbox = new Rectangle2D(posX, posY, size, size);
			posX += speed;
			
		}
	}

	@Override
	public void CollisionCheck() {
	}

}
