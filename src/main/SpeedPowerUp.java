package main;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import states.PlayState;

public class SpeedPowerUp extends PowerUp {

	private int speed;

	public SpeedPowerUp(int posX, int posY, int speed, Image image, GraphicsContext gc, PlayState play) {
		super(posX, posY, speed, image, gc, play);
		this.speed = speed;
	}
	
	public int getSpeed() {
		return this.speed;
	}

	@Override
	public int getHealth() {
		return 0;
	}



}
