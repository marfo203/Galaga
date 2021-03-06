package main;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Bullet{
	private GraphicsContext gc;
	private int speed = 5; // Constant speed. Maybe shange depending on score
	private boolean hitTarget = false;
	private double posX;
	private double posY;
	private int size;
	private Image bullet;
	private int direction;


	public Bullet(double posX, double posY, int direction) {
		
		
		this.posX = posX;
		this.posY = posY;
		this.size = size;
		this.bullet = bullet;
		this.speed = speed;
		this.hitTarget = hitTarget;
		this.direction = direction; //Direction is -1 if player is shooting, +1 if enemy is shooting
	}
	
	
	
	public void update() {
		if(!hitTarget) {
			this.posY = speed*direction;
		}
	}
	
	public void CreateBullet() {
		if(this.direction == -1) {
		gc.setFill(Color.LIMEGREEN); //Här kan vi ju använda bilder på något som skott istället men najs om man ser tydligt "goda" och "onda" skott.
		} else {
			gc.setFill(Color.RED);
		gc.fillOval(this.posX, this.posY, 10, 10);
		}
		
	}
	
	

}
