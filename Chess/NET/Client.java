package NET;

import GameLogic.Chess;
import GameLogic.GameBoard;
import GameLogic.GameMode;
import graphics.PopUps;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Client implements Runnable {
	private Socket kkSocket = null;
	private Thread t;
	private boolean disconnecting = false;

	public static boolean connected = false;

	public Client() {
		try {
			String ip = JOptionPane.showInputDialog(null, "Enter the IP address you would like to connect to: ",
					"Connect to User", 1);
			if (ip != null) {
				this.kkSocket = new Socket(ip, 5239);
				BufferedReader in = new BufferedReader(new InputStreamReader(this.kkSocket.getInputStream()));
				Scanner scan = new Scanner(in.readLine());
				sendMessage(Chess.verison);

				if (!scan.nextLine().equals(Chess.verison)) {
					PopUps.errorMessage("The server you are trying to connect to does not have the same version");
					connected = false;
					GameBoard.isOnline = false;
					this.kkSocket.close();
					return;
				}
				scan = new Scanner(in.readLine());
				GameMode.changeMode(scan.nextInt());
				GameBoard.newGame(GameMode.getMode());
				connected = true;
				GameBoard.isOnline = true;
				this.t = new Thread(this);
				this.t.start();
			}
		} catch (UnknownHostException e) {
			PopUps.errorMessage("Invalid IP/Computer name.");
		} catch (IOException e) {
			PopUps.errorMessage("Could not find the server.");
		}
	}

	public void disconnect() {
		this.disconnecting = true;
		connected = false;
		GameBoard.isOnline = false;
		try {
			sendMessage("CloseGame");
			this.kkSocket.close();
		} catch (IOException localIOException) {
		}
	}

	public void run() {
		try {
			PrintWriter out = null;
			BufferedReader in = null;

			out = new PrintWriter(this.kkSocket.getOutputStream(), true);

			in = new BufferedReader(new InputStreamReader(this.kkSocket.getInputStream()));

			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				boolean action = true;
				if (inputLine.substring(0, 2).equals("NG")) {
					Scanner scan = new Scanner(inputLine.substring(2));
					GameMode.changeMode(scan.nextInt());
					GameBoard.newGame(GameMode.getMode());
					action = false;
				}
				System.out.println("Test: " + inputLine.substring(0, 1));

				if ((inputLine.equals("CloseGame")) && (action)) {
					connected = false;
					GameBoard.isOnline = false;
					this.kkSocket.close();
					PopUps.errorMessage("The white has disconnected from you. \nYou win!");
					return;
				}
				if ((GameBoard.turn == 1) && (action)) {
					System.out.println("Server: " + inputLine);
					Scanner scan = new Scanner(inputLine);
					if (inputLine.equals("Checkmate")) {
						connected = false;
						GameBoard.isOnline = false;
						this.kkSocket.close();
						return;
					}

					Chess.board.chessMove(scan.nextInt(), scan.nextInt());

				}

			}

		} catch (IOException e) {

			connected = false;
			GameBoard.isOnline = false;
			if (!this.disconnecting) {
				PopUps.errorMessage("Server disconnected from the game.");
			} else {
				PopUps.errorMessage("You have disconnected from the game.");
			}

			try {
				this.kkSocket.close();
			} catch (IOException localIOException1) {
			}

			return;
		}
	}

	public void sendMessage(String message) throws IOException {
		PrintWriter out = new PrintWriter(this.kkSocket.getOutputStream(), true);
		out.println(message);
		System.out.println("Client: " + message);
	}
}
