package main;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public abstract class Ship {
	public Ship(double posX, double posY, int size, Image ship, GraphicsContext gc) {

	}

	public abstract void CollisionCheck();
	public abstract void update();

}
