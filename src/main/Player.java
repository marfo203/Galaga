package main;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Player extends Ship {
	private boolean dead = false;
	private double posX;
	private double posY;
	private int size;
	private Image ship;
	private Image deadShip;
	private GraphicsContext gc;
	private int health = 3;
	ArrayList<Bullet> bullets = new ArrayList<Bullet>();

	public Player(double posX, double posY, int size, Image ship, GraphicsContext gc) {
		super(posX, posY, size, ship);

		this.posX = posX;
		this.posY = posY;
		this.size = size;
		this.ship = ship;
		this.gc = gc;
		//En liten ändring
	}

	public void Shoot() {
		Bullet bullet = new Bullet(this.posX, this.posY - (size / 2), -1, gc);
		bullets.add(bullet);
		
		 
	}

	public void update() {
		if (!dead) {
			gc.drawImage(ship, posX, posY - 30, size * 4, size * 5);
			for (int i = 0; i < bullets.size(); i++) {
				bullets.get(i).update();		
			}
		} else {
			gc.drawImage(deadShip, posY, posX, size, size);
		}

	}

	public void move(KeyEvent key) {
			if (key.getCode() == KeyCode.RIGHT) {
				this.posX += 15;
			} else {
				this.posX -= 15;
			}
		}
	}
