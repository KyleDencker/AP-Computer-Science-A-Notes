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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server implements Runnable {
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private Thread t;
	private boolean disconnecting = false;
	public static boolean connected = false;

	public Server() {
		GameBoard.isOnline = true;
		this.t = new Thread(this);

		this.t.start();
	}

	public void disconnect() {
		connected = false;
		GameBoard.isOnline = false;

		try {
			if (this.clientSocket != null) {
				sendMessage("CloseGame");
				this.disconnecting = true;
				this.clientSocket.close();
				this.clientSocket = null;
			}
			this.serverSocket.close();
		} catch (IOException localIOException) {
		}
	}

	public void run() {
		IPAddress ip = new IPAddress();

		this.serverSocket = null;
		try {
			this.serverSocket = new ServerSocket(5239);
		} catch (IOException e) {
			PopUps.errorMessage(
					"Could not setup the server. \n\nPlease check to make sure no other process is using 5239.");
			return;
		}

		this.clientSocket = null;
		try {
			this.clientSocket = this.serverSocket.accept();
		} catch (IOException e) {
			PopUps.errorMessage("The server has been shut off.");
			return;
		}
		try {
			GameBoard.newGame(GameMode.getMode());
		} catch (IOException e) {
			PopUps.errorMessage("Could not create a new game");
			return;
		}
		connected = true;
		GameBoard.isOnline = true;

		try {
			PrintWriter out = new PrintWriter(this.clientSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));

			sendMessage(Chess.verison);
			String inputLine = in.readLine();
			if (!inputLine.equals(Chess.verison)) {
				PopUps.errorMessage("The versions do not match.");
				connected = false;
				GameBoard.isOnline = false;
				this.clientSocket.close();
				this.serverSocket.close();
				return;
			}
			String outputLine = ""+GameMode.getMode();
			sendMessage(outputLine);
		} catch (IOException e1) {
			PopUps.errorMessage("Unknown error");
			return;
		}

		do {
			try {
				String outputLine;
				String inputLine;
				PrintWriter out = new PrintWriter(this.clientSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
				while ((inputLine = in.readLine()) != null) {
					if (inputLine.equals("CloseGame")) {
						connected = false;
						GameBoard.isOnline = false;
						this.clientSocket.close();
						this.serverSocket.close();
						PopUps.errorMessage("The black has disconnected from you. \nYou win!");
						return;
					}

					if (GameBoard.turn == 2) {
						Scanner scan = new Scanner(inputLine);

						Chess.board.chessMove(scan.nextInt(), scan.nextInt());

						if (inputLine.equals("Checkmate")) {
							connected = false;
							GameBoard.isOnline = false;
							this.clientSocket.close();
							this.serverSocket.close();
							return;
						}

					}

				}

			} catch (IOException e) {
				connected = false;
				GameBoard.isOnline = false;
				if (!this.disconnecting) {
					PopUps.errorMessage("Client Disconnected from the game.");
				} else
					PopUps.errorMessage("You have disconnected from the game.");
				return;
			}
		} while (connected);

		try {
			this.clientSocket.close();
			this.serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendMessage(String message) throws IOException {
		PrintWriter out = new PrintWriter(this.clientSocket.getOutputStream(), true);
		out.println(message);
		System.out.println("Server: " + message);
	}
}
