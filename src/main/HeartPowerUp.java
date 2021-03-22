package main;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import states.PlayState;

public class HeartPowerUp extends PowerUp {

	private int health;

	public HeartPowerUp(int posX, int posY, int health, Image image, GraphicsContext gc, PlayState play) {
		super(posX, posY, health, image, gc, play);
		this.health = health;
	}

	public int getHealth() {
		return this.health;
	}

	@Override
	public int getSpeed() {
		return 0;
	}
}
