package main;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import states.PlayState;

public abstract class Ship {
	public Ship(double posX, double posY, int size, Image ship, GraphicsContext gc) {
	}

	public Ship(int posX, int posY, int health, Image image, GraphicsContext gc, PlayState play) {
	}
	
	public Ship(int posX, int posY, Image image, GraphicsContext gc, PlayState play, int speed) {
	}
	
	

	public abstract void CollisionCheck();

	public abstract void update();

	public abstract Rectangle2D getHitbox();

}
