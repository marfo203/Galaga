package main;

import javafx.scene.image.Image;


public abstract class Ship {
	public Ship(double posX, double posY, int size, Image ship) {

	}

	public abstract void CollisionCheck();
	public abstract void update();

}
