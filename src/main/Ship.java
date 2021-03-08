package main;

import java.util.ArrayList;

import javafx.scene.image.Image;


public abstract class Ship {
	public Ship(double posX, double posY, int size, ArrayList<Image> img) {

	}

	public abstract void CollisionCheck();
	public abstract void update();

}
