
package main;

	import javafx.geometry.Rectangle2D;
	import javafx.scene.canvas.GraphicsContext;
	import javafx.scene.image.Image;
	import states.PlayState;

	public class PowerUp extends Comet {

		private int speed = 2; // Constant speed. Maybe shange depending on score
		private boolean dead;
		private int height = 50;
		private int width = 50;
		private int posX;
		private int posY;
		Rectangle2D cometHitbox;
		private GraphicsContext gc;
		private Image image;
		private PlayState play;
		private int size;
		private Rectangle2D powerHitbox;
		private int health;

		public PowerUp(int posX, int posY, int size, int health, Image image, GraphicsContext gc, PlayState play, int speed) {
			super(posX, posY, size, image, gc, play, speed);

			this.posX = posX;
			this.posY = posY;
			this.image = image;
			this.speed = speed;
			this.dead = dead;
			this.gc = gc;
			this.play = play;
			this.size = size;
			this.health = health;

		}

		public void update() {
			if (!dead) {
				gc.drawImage(image, posX - 300, posY - 650, height, width);
				powerHitbox = new Rectangle2D(posX - 300, posY - 650, height, width);
				posY += speed;
			}
			for (int i = 0; i < play.getPowerUps().size(); i++) {
				if (play.getPowerUps().get(i).posY >= 1500) {
					play.getPowerUps().remove(i);
				}
			}

		}

		@Override
		public void CollisionCheck() {
		}
@Override
		public Rectangle2D getHitbox() {
			return cometHitbox;
		}
		@Override
		public int getHealth() {
			return this.health;
		}

	}


