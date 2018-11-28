package GameLogic;

import graphics.menu;
import java.awt.Point;
import java.io.IOException;
import javax.swing.JFrame;

public class Chess {
	public static boolean debug = false;
	public static Chess game;
	public static GameBoard board;
	public static String verison = "0.5.3.0";

	private JFrame window;

	public Chess() throws IOException {
		this.window = new JFrame("Chess");

		this.window.setDefaultCloseOperation(3);
		this.window.setJMenuBar(menu.MainMenuBar());
		board = new GameBoard();
		this.window.add(board);
		this.window.setVisible(true);
		this.window.setSize(405, 450);
		this.window.setResizable(false);
		GameMode.setDefaultGame();
	}

	public void close() {
		this.window.dispose();
		System.exit(1);
	}

	public void refresh() {
		this.window.repaint();
	}

	public void changeSize(int mode) {
		switch (mode) {
		case 1:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
			this.window.setBounds(this.window.getLocation().x, this.window.getLocation().y, 405, 450);
			break;
		case 2:
			this.window.setBounds(this.window.getLocation().x, this.window.getLocation().y, 505, 550);
			break;
		case 3:
			this.window.setBounds(this.window.getLocation().x, this.window.getLocation().y, 405, 750);
		}

	}

	public static void main(String[] args) throws IOException {
		game = new Chess();
	}
}
