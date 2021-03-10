package states;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class ChooseShipState extends GameState {

	private Color bgColor = Color.BLACK;
	private Image xwing;
	private Image mfalcon;

	public ChooseShipState(GameModel model, GraphicsContext gc) {
		super(model);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(GraphicsContext g) {
		drawBg(g, bgColor);
		
	}

	@Override
	public void keyPressed(KeyEvent key, GraphicsContext gc) {
		System.out.println("Trycker på " + key.getText() + " i MenuState");

		if (key.getCode() == KeyCode.X) {
			model.switchState(new PlayState(model, gc));
			PlayState.SetChoosenShip(xwing);
		} else if (key.getCode() == KeyCode.M) {
			model.switchState(new PlayState(model, gc));
			PlayState.SetChoosenShip(mfalcon);
		} else if (key.getCode() == KeyCode.ESCAPE) {
			model.switchState(new MenuState(model));
	}
	}

	@Override
	public void activate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deactivate() {
		// TODO Auto-generated method stub
		
	}

}
