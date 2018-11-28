package graphics;

import GameLogic.GameBoard;
import GameLogic.GameMode;
import NET.Server;
import java.io.IOException;
import java.net.InetAddress;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class PopUps {
	public static void newGame() throws IOException {
		if (JOptionPane.showConfirmDialog(new JFrame(),
				"If start a new game, all unsaved information will be lost.\n\nWould you like start a new game?",
				"New Game", 0) == 0) {
			GameBoard.newGame(GameMode.getMode());
		}
	}

	public static void displayIP() {
		try {
			InetAddress thisIp = InetAddress.getLocalHost();
			String message = "Your IP Address is: " + thisIp.getHostAddress();
			JOptionPane.showMessageDialog(new JFrame(), message, "IP Address", 2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void openGame() {
		String message = "This feature has not been created yet.";
		JOptionPane.showMessageDialog(new JFrame(), message, "Error:  Not a feature... yet", 0);
	}

	public static void saveGame() {
		String message = "This feature has not been created yet.";
		JOptionPane.showMessageDialog(new JFrame(), message, "Error:  Not a feature... yet", 0);
	}

	public static void GameMode(int mode) throws IOException {
		if (JOptionPane.showConfirmDialog(new JFrame(),
				"You are about to change the game mode.   This will cause the current game data to be lost.\n\nWould you like start a new game?",
				"Change Game Mode", 0) == 0) {
			if (GameBoard.server != null) {
				GameBoard.server.sendMessage("NG " + mode);
			}
			GameMode.changeMode(mode);
		}
	}

	public static void check() {
		String message = "Check!";
		JOptionPane.showMessageDialog(new JFrame(), message, "Check!", 2);
	}

	public static void KingInCheck() {
		String message = "You cannot place your king in check!";
		JOptionPane.showMessageDialog(new JFrame(), message, "Check!", 2);
	}

	public static void checkMate() {
		String message = "Checkmate!";
		JOptionPane.showMessageDialog(new JFrame(), message, "Checkmate!", 0);
	}

	public static void poison(String name) {
		String message = "The " + name + " died of poison.";
		JOptionPane.showMessageDialog(new JFrame(), message, "Piece Death", 0);
	}

	public static void errorMessage(String message) {
		JOptionPane.showMessageDialog(new JFrame(), message, "Ops...  There is a problem", 0);
	}
}
