package main;

import java.util.ArrayList;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Comet extends Ship {

	private int speed = 2; // Constant speed. Maybe shange depending on score
	private boolean dead;
	private int height = 50;
	private int width = 50;
	private int posX;
	private int posY;
	Rectangle2D cometHitbox;
	private ArrayList<Image> images;
	private GraphicsContext gc;
	private Image image;

	public Comet(int posX, int posY, int size, Image image, GraphicsContext gc) {
		super(posX, posY, size, image);

		this.posX = posX;
		this.posY = posY;
		this.image = image;
		this.speed = speed;
		this.dead = dead;
		this.gc = gc;

	}

	public void update() {
		if (!dead) {			
			gc.drawImage(image, posX - 300, posY - 650, height, width);
			cometHitbox = new Rectangle2D(posX - 300, posY - 650, height, width);
			posY += speed;		
		}
	}

	@Override
	public void CollisionCheck() {
	}

}
