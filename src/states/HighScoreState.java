package states;

import static constants.Constants.SCREEN_HEIGHT;
import static constants.Constants.SCREEN_WIDTH;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import main.Enemy;
import main.GameFrame;

public class HighScoreState extends GameState {

	private Color bgColor;
	private Color fontColor;
	private String informationText;
	private String text;

	private ArrayList<Integer> highscores = new ArrayList<Integer>();

	public HighScoreState(GameModel model) {
		super(model);
		bgColor = Color.BLACK;
		fontColor = Color.WHITE;
		text = "Current High Scores:";
		informationText = "Press Escape to go\nback to the main menu";

		GetHighScoresFromFile();

	}

	public void GetHighScoresFromFile() {

		try {
			Scanner input = new Scanner(new FileInputStream("HighScores.txt"));
			highscores = new ArrayList<Integer>();
			while (input.hasNextInt()) {
				highscores.add(input.nextInt());
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Collections.sort(highscores);
		Collections.reverse(highscores);
	}

	@Override
	public void update() {
	}

	@Override
	public void draw(GraphicsContext g) {
		drawBg(g, bgColor);
		g.setFill(fontColor);
		g.setFont(new Font(30));
		g.fillText(text, SCREEN_WIDTH / 2 - 150, SCREEN_HEIGHT / 2 - 300);
		for (int i = 0; i < highscores.size(); i++) {
			g.fillText(highscores.get(i).toString(), SCREEN_WIDTH / 2 - 150, SCREEN_HEIGHT / 2 - 265 + i * 35);
			if (i >= 12) {
				break;
			}
		}
		g.fillText(informationText, SCREEN_WIDTH / 2 - 150, SCREEN_HEIGHT / 2 + 200);
	}

	@Override
	public void keyPressed(KeyEvent key, GraphicsContext gc) {
		System.out.println("Trycker på " + key.getText() + " i HighScoreState");

		if (key.getCode() == KeyCode.ESCAPE)
			model.switchState(new MenuState(model));
	}

	@Override
	public void activate() {
	}

	@Override
	public void deactivate() {
	}

}
