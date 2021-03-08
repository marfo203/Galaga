package main;

import java.util.ArrayList;

import javafx.scene.image.Image;

public class Comet extends Ship {
	
	private int speed = 5; // Constant speed. Maybe shange depending on score
	private boolean dead = false;

	private int posX;
	private int posY;
	private int size;
	private ArrayList<Image> comet;

	public Comet(int posX, int posY, int size, ArrayList<Image> comet) {
		super(posX, posY, size, comet);
		
		this.posX = posX;
		this.posY = posY;
		this.size = size;
		this.comet = comet;
		this.speed = speed;
		this.dead = dead;
		
	}
	
	public void update(){
		if (!dead) {
			posX += speed;
			if (posX == 600) {
				posX = 0;
				posY += size;
			}
		}
	}

	@Override
	public void CollisionCheck() {
	}

	

}
