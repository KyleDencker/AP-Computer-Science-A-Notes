package GameLogic;

import NET.Client;
import NET.Server;
import graphics.PopUps;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Random;
import javax.swing.JComponent;
import piece.Bishop;
import piece.ChessPiece;
import piece.King;
import piece.Knight;
import piece.Lady;
import piece.Pawn;
import piece.Queen;
import piece.Rook;
import piece.Serpent;



public class GameBoard
extends JComponent
implements MouseListener
{
	   private static int x = 40;
	   private static int y = 40;
	   private static int selected = -1;
	private static int lastX;
	   private static int lastY; private static int lastDelete = -1;
	private static ChessPiece[] pieces;
	   private static boolean gameStarted = false;
	   public static int turn = 0;
	private static final long serialVersionUID = -2127938543571628440L;
	   public static boolean isOnline = false;
	public static Server server;
	public static Client client;

	public GameBoard() throws IOException
	{
		     repaint();
		     addMouseListener(this);
	}


	protected void paintComponent(Graphics g)
	{
		     for (int i = 0; i < GameMode.getX(); i++) {
			       for (int j = 0; j < GameMode.getY(); j++) {
				         if (i % 2 == j % 2) {
					           g.setColor(Color.GRAY);
				} else {
					           g.setColor(Color.red);
				}

				         g.fillRect(i * 50, j * 50, 50, 50);
			}
		}
		     if (gameStarted) {
			       for (int i = 0; i < pieces.length; i++) {
				         if (pieces[i] != null) {
					           g.drawImage(pieces[i].getImage(), pieces[i].getX() * 50, pieces[i].getY() * 50, this);

					           if (pieces[i].getHealth() != 0) {
						             g.setColor(Color.MAGENTA);
						             for (int j = 0; j < 5; j++) {
							               g.drawRect(pieces[i].getX() * 50 + j, pieces[i].getY() * 50 + j, 50 - 2 * j, 50 - 2 * j);
						}
					}
				}


				         if (i == selected) {
					           g.setColor(Color.blue);
					           for (int j = 0; j < 5; j++) {
						             g.drawRect(pieces[i].getX() * 50 + j, pieces[i].getY() * 50 + j, 50 - 2 * j, 50 - 2 * j);
					}
				}
			}


			       if (selected != -1) {
				         g.setColor(Color.yellow);
				         for (int i = 0; i < GameMode.getX(); i++) {
					           for (int j = 0; j < GameMode.getY(); j++) {
						             if (pieces[selected].isValid(i, j)) {
							               for (int k = 0; k < 5; k++) {
								                 g.drawRect(i * 50 + k, j * 50 + k, 50 - 2 * k, 50 - 2 * k);
							}
						}
					}
				}
			}
		}
	}




	public static int isLocated(int x, int y)
	{
		if (!GameMode.isInBounds(x, y)) return -1;
		for (int i = 0; i < pieces.length; i++)
			if ((pieces[i] != null) && 
					(pieces[i].getX() == x) && (pieces[i].getY() == y))
				return i;
		return -1;
	}

	public static boolean isThreat(ChessPiece king) {
		     for (int selected = 0; selected < pieces.length; selected++) {
			       if ((pieces[selected] != null) && (king.getSide() != pieces[selected].getSide()) && 
					         (pieces[selected].isValid(king.getX(), king.getY()))) {
				         return true;
			}
		}


		     return false;
	}

	public static ChessPiece getPiece(int x, int y) {
		     for (int i = 0; i < pieces.length; i++)
			       if ((pieces[i] != null) && 
					         (pieces[i].getX() == x) && (pieces[i].getY() == y))
				         return pieces[i];
		     return null;
	}

	public static void DeletePiece(int index) {
		     pieces[index] = null;
	}

	public void mouseClicked(MouseEvent e)
	{
		     repaint();
	}



	public void mouseEntered(MouseEvent e) {}


	public void mouseExited(MouseEvent e) {}


	public void mousePressed(MouseEvent e) {}


	public void mouseReleased(MouseEvent e)
	{
		try
		{
			       if ((!isOnline) || ((server != null) && (turn == 1)) || ((Client.connected) && (turn == 2))) {
				         chessMove(e.getX(), e.getY());
			}
		} catch (IOException e1) {
			       e1.printStackTrace();
		}
	}


	public void chessMove(int MX, int MY)
			throws IOException
	{
		     if (gameStarted) {
			       if (selected == -1) {
				         if ((isLocated(MX / 50, MY / 50) != -1) && 
						           (getPiece(MX / 50, MY / 50).getSide() == turn)) {
					           selected = isLocated(MX / 50, MY / 50);
					           if (server != null) server.sendMessage(MX + " " + MY);
					           if (Client.connected) client.sendMessage(MX + " " + MY);
				}
			}
			else {
				         if (pieces[selected].isValid(MX / 50, MY / 50))
				{

					           int offBoardX = -1;int offBoardY = -1;
					           int place = isLocated(MX / 50, MY / 50);
					           if (place != -1)
					{
						             offBoardX = pieces[place].getX();
						             offBoardY = pieces[place].getY();

						             pieces[place].setCoord(-1, 64636);
					}

					           lastX = pieces[selected].getX();
					           lastY = pieces[selected].getY();

					           pieces[selected].setCoord(MX / 50, MY / 50);
					           pieces[selected].setHasMoved();
					           if (pieces[selected].getName().equals("Black Pawn")) {
						             if (pieces[selected].getY() == GameMode.getY() - 1) {
							try {
								                 pieces[selected] = new Queen(pieces[selected].getX(), pieces[selected].getY(), 2, "Black Queen");
							} catch (IOException e1) {
								                 e1.printStackTrace();
							}
						}
					           } else if ((pieces[selected].getName().equals("White Pawn")) && 
							             (pieces[selected].getY() == 0)) {
						try {
							               pieces[selected] = new Queen(pieces[selected].getX(), pieces[selected].getY(), 1, "White Queen");
						} catch (IOException e1) {
							               e1.printStackTrace();
						}
					}


					           boolean nextTurn = true;

					           if (isThreat(pieces[(turn % 2)])) {
						             pieces[selected].setCoord(lastX, lastY);


						             if (place != -1) {
							               pieces[place].setCoord(offBoardX, offBoardY);
						}

						             selected = -1;
						             if (turn == 1) turn = 2; else
							               turn = 1;
						             PopUps.KingInCheck();
						             nextTurn = false;
					}
					           else if (isThreat(pieces[((turn + 1) % 2)])) { 
						int side;
						             if (turn == 1) side = 2; else {
							               side = 1;
						}
						             if (!isCheckMate(side)) {
							               repaint();
							               PopUps.checkMate();
							try {
								                 Thread.sleep(2000L);
							} catch (InterruptedException e1) {
								                 e1.printStackTrace();
							}
							               turn = 0;
							               gameStarted = false;
						} else {
							               PopUps.check();
						}
					}

					           if (nextTurn) {
						             if (pieces[selected].isPoison()) {
							               for (int pX = pieces[selected].getX() - 1; pX < pieces[selected].getX() + 2; pX++) {
								                 for (int pY = pieces[selected].getY() - 1; pY < pieces[selected].getY() + 2; pY++) {
									                   int loc = isLocated(pX, pY);
									                   if ((loc != -1) && 
											                     (pieces[loc].getSide() != pieces[selected].getSide()) && (pieces[loc].getHealth() == 0)) {
										                     pieces[loc].addHealth();
									}
								}
							}
						}



						             if (pieces[selected].isHeal()) {
							               for (int pX = pieces[selected].getX() - 1; pX < pieces[selected].getX() + 2; pX++) {
								                 for (int pY = pieces[selected].getY() - 1; pY < pieces[selected].getY() + 2; pY++) {
									                   int loc = isLocated(pX, pY);
									                   if ((loc != -1) && 
											                     (pieces[loc].getSide() == pieces[selected].getSide())) {
										                     pieces[loc].heal();
									}
								}
							}
						}


						             for (int ploc = 0; ploc < pieces.length; ploc++) {
							               if (pieces[ploc] != null) {
								                 if (pieces[ploc].getHealth() != 0) {
									                   pieces[ploc].addHealth();
								}

								                 if (pieces[ploc].getHealth() == 10) {
									                   PopUps.poison(pieces[ploc].getName());

									                   if ((ploc == 0) || (ploc == 1))
									{
										                     repaint();
										                     PopUps.checkMate();
										try {
											                       Thread.sleep(2000L);
										} catch (InterruptedException e1) {
											                       e1.printStackTrace();
										}
										                     if (server != null) server.sendMessage("Checkmate");
										                     if (Client.connected) client.sendMessage("Checkmate");
										                     turn = 0;
										                     gameStarted = false;
									}


									                   DeletePiece(ploc);
								}
							}
						}
					}
					           if (server != null) server.sendMessage(MX + " " + MY);
					           if (Client.connected) client.sendMessage(MX + " " + MY);
					           selected = -1;
					           if (turn == 1) { turn = 2;
					           } else if (turn == 2) turn = 1;
				} else {
					           if (server != null) server.sendMessage(MX + " " + MY);
					           if (Client.connected) client.sendMessage(MX + " " + MY);
					           selected = -1;
				}
				         lastDelete = -1;
			}


			       if (isLocated(-1, 64636) != -1) {
				         DeletePiece(isLocated(-1, 64636));
			}
			       repaint();
		}
	}



	private boolean isCheckMate(int side)
	{
		     for (int i = 0; i < pieces.length; i++) {
			       if ((pieces[i] != null) && 
					         (pieces[i].getSide() == side)) {
				         int tempX = pieces[i].getX();
				         int tempY = pieces[i].getY();
				         for (int x = 0; x < GameMode.getX(); x++) {
					           for (int y = 0; y < GameMode.getY(); y++) {
						             if (pieces[i].isValid(x, y)) {
							               int offBoardX = -1;int offBoardY = -1;
							               int place = isLocated(x, y);
							               if (place != -1) {
								                 offBoardX = pieces[place].getX();
								                 offBoardY = pieces[place].getY();
								                 pieces[place].setCoord(-1, 64636);
							}
							               pieces[i].setCoord(x, y);
							               if (!isThreat(pieces[(side % 2)])) {
								                 pieces[i].setCoord(tempX, tempY);
								                 if (place != -1) {
									                   pieces[place].setCoord(offBoardX, offBoardY);
								}
								                 return true;
							}
							               pieces[i].setCoord(tempX, tempY);
							               if (place != -1) {
								                 pieces[place].setCoord(offBoardX, offBoardY);
							}
						}
					}
				}
			}
		}


		     return false;
	}




	public static void newGame(int mode)
			throws IOException
	{
		     turn = 1;
		     switch (mode) {
		case 1: 
			       pieces = new ChessPiece[32];
			       pieces[0] = new King(4, 0, 2, "Black King");
			       pieces[1] = new King(4, 7, 1, "White King");
			       pieces[2] = new Knight(1, 0, 2, "Black Knight");
			       pieces[3] = new Knight(6, 0, 2, "Black Knight");
			       pieces[4] = new Rook(0, 0, 2, "Black Rook");
			       pieces[5] = new Rook(7, 0, 2, "Black Rook");
			       pieces[6] = new Knight(1, 7, 1, "white Knight");
			       pieces[7] = new Knight(6, 7, 1, "White Knight");
			       pieces[8] = new Rook(0, 7, 1, "White Rook");
			       pieces[9] = new Rook(7, 7, 1, "White Rook");
			       pieces[10] = new Bishop(2, 0, 2, "Black Bishop");
			       pieces[11] = new Bishop(5, 0, 2, "Black Bishop");
			       pieces[12] = new Bishop(2, 7, 1, "White Bishop");
			       pieces[13] = new Bishop(5, 7, 1, "White Bishop");
			       pieces[14] = new Pawn(0, 6, 1, "White Pawn");
			       pieces[15] = new Pawn(1, 6, 1, "White Pawn");
			       pieces[16] = new Pawn(2, 6, 1, "White Pawn");
			       pieces[17] = new Pawn(3, 6, 1, "White Pawn");
			       pieces[18] = new Pawn(4, 6, 1, "White Pawn");
			       pieces[19] = new Pawn(5, 6, 1, "White Pawn");
			       pieces[20] = new Pawn(6, 6, 1, "White Pawn");
			       pieces[21] = new Pawn(7, 6, 1, "White Pawn");
			       pieces[23] = new Pawn(0, 1, 2, "Black Pawn");
			       pieces[24] = new Pawn(1, 1, 2, "Black Pawn");
			       pieces[25] = new Pawn(2, 1, 2, "Black Pawn");
			       pieces[26] = new Pawn(3, 1, 2, "Black Pawn");
			       pieces[27] = new Pawn(4, 1, 2, "Black Pawn");
			       pieces[28] = new Pawn(5, 1, 2, "Black Pawn");
			       pieces[29] = new Pawn(6, 1, 2, "Black Pawn");
			       pieces[30] = new Pawn(7, 1, 2, "Black Pawn");
			       pieces[31] = new Queen(3, 0, 2, "Black Queen");
			       pieces[22] = new Queen(3, 7, 1, "White Queen");
			       break;

		case 2: 
			       pieces = new ChessPiece[40];
			       pieces[0] = new King(5, 0, 2, "Black King");
			       pieces[1] = new King(5, 9, 1, "White King");
			       pieces[2] = new Knight(2, 0, 2, "Black Knight");
			       pieces[3] = new Knight(7, 0, 2, "Black Knight");
			       pieces[4] = new Rook(1, 0, 2, "Black Rook");
			       pieces[5] = new Rook(8, 0, 2, "Black Rook");
			       pieces[6] = new Knight(2, 9, 1, "white Knight");
			       pieces[7] = new Knight(7, 9, 1, "White Knight");
			       pieces[8] = new Rook(1, 9, 1, "White Rook");
			       pieces[9] = new Rook(8, 9, 1, "White Rook");
			       pieces[10] = new Bishop(3, 0, 2, "Black Bishop");
			       pieces[11] = new Bishop(6, 0, 2, "Black Bishop");
			       pieces[12] = new Bishop(3, 9, 1, "White Bishop");
			       pieces[13] = new Bishop(6, 9, 1, "White Bishop");
			       pieces[14] = new Pawn(1, 8, 1, "White Pawn");
			       pieces[15] = new Pawn(2, 8, 1, "White Pawn");
			       pieces[16] = new Pawn(3, 8, 1, "White Pawn");
			       pieces[17] = new Pawn(4, 8, 1, "White Pawn");
			       pieces[18] = new Pawn(5, 8, 1, "White Pawn");
			       pieces[19] = new Pawn(6, 8, 1, "White Pawn");
			       pieces[20] = new Pawn(7, 8, 1, "White Pawn");
			       pieces[21] = new Pawn(8, 8, 1, "White Pawn");
			       pieces[23] = new Pawn(1, 1, 2, "Black Pawn");
			       pieces[24] = new Pawn(2, 1, 2, "Black Pawn");
			       pieces[25] = new Pawn(3, 1, 2, "Black Pawn");
			       pieces[26] = new Pawn(4, 1, 2, "Black Pawn");
			       pieces[27] = new Pawn(5, 1, 2, "Black Pawn");
			       pieces[28] = new Pawn(6, 1, 2, "Black Pawn");
			       pieces[29] = new Pawn(7, 1, 2, "Black Pawn");
			       pieces[30] = new Pawn(8, 1, 2, "Black Pawn");
			       pieces[31] = new Queen(4, 0, 2, "Black Queen");
			       pieces[22] = new Queen(4, 9, 1, "White Queen");
			       pieces[32] = new Pawn(0, 8, 1, "White Pawn");
			       pieces[33] = new Pawn(9, 8, 1, "White Pawn");
			       pieces[34] = new Pawn(0, 1, 2, "Black Pawn");
			       pieces[35] = new Pawn(9, 1, 2, "Black Pawn");
			       pieces[36] = new Serpent(0, 0, 2, "Black Serpent");
			       pieces[37] = new Lady(9, 0, 2, "Black Serpent");
			       pieces[38] = new Serpent(0, 9, 1, "White Serpent");
			       pieces[39] = new Lady(9, 9, 1, "White Serpent");
			       break;
		case 3: 
			       pieces = new ChessPiece[56];
			       pieces[0] = new King(4, 0, 2, "Black King");
			       pieces[1] = new King(4, 13, 1, "White King");
			       pieces[2] = new Knight(1, 0, 2, "Black Knight");
			       pieces[3] = new Knight(6, 0, 2, "Black Knight");
			       pieces[4] = new Rook(0, 0, 2, "Black Rook");
			       pieces[5] = new Rook(7, 0, 2, "Black Rook");
			       pieces[6] = new Knight(1, 13, 1, "white Knight");
			       pieces[7] = new Knight(6, 13, 1, "White Knight");
			       pieces[8] = new Rook(0, 13, 1, "White Rook");
			       pieces[9] = new Rook(7, 13, 1, "White Rook");
			       pieces[10] = new Bishop(2, 0, 2, "Black Bishop");
			       pieces[11] = new Bishop(5, 0, 2, "Black Bishop");
			       pieces[12] = new Bishop(2, 13, 1, "White Bishop");
			       pieces[13] = new Bishop(5, 13, 1, "White Bishop");
			       pieces[14] = new Pawn(0, 9, 1, "White Pawn");
			       pieces[15] = new Pawn(1, 10, 1, "White Pawn");
			       pieces[16] = new Pawn(2, 11, 1, "White Pawn");
			       pieces[17] = new Pawn(3, 12, 1, "White Pawn");
			       pieces[18] = new Pawn(4, 12, 1, "White Pawn");
			       pieces[19] = new Pawn(5, 11, 1, "White Pawn");
			       pieces[20] = new Pawn(6, 10, 1, "White Pawn");
			       pieces[21] = new Pawn(7, 9, 1, "White Pawn");
			       pieces[23] = new Pawn(0, 4, 2, "Black Pawn");
			       pieces[24] = new Pawn(1, 3, 2, "Black Pawn");
			       pieces[25] = new Pawn(2, 2, 2, "Black Pawn");
			       pieces[26] = new Pawn(3, 1, 2, "Black Pawn");
			       pieces[27] = new Pawn(4, 1, 2, "Black Pawn");
			       pieces[28] = new Pawn(5, 2, 2, "Black Pawn");
			       pieces[29] = new Pawn(6, 3, 2, "Black Pawn");
			       pieces[30] = new Pawn(7, 4, 2, "Black Pawn");
			       pieces[31] = new Queen(3, 0, 2, "Black Queen");
			       pieces[22] = new Queen(3, 13, 1, "White Queen");
			       pieces[32] = new Bishop(2, 1, 2, "Black Bishop");
			       pieces[33] = new Bishop(5, 1, 2, "Black Bishop");
			       pieces[34] = new Bishop(2, 12, 1, "White Bishop");
			       pieces[35] = new Bishop(5, 12, 1, "White Bishop");
			       pieces[36] = new Knight(1, 1, 2, "Black Knight");
			       pieces[37] = new Knight(6, 1, 2, "Black Knight");
			       pieces[38] = new Rook(0, 1, 2, "Black Rook");
			       pieces[39] = new Rook(7, 1, 2, "Black Rook");
			       pieces[40] = new Knight(1, 12, 1, "white Knight");
			       pieces[41] = new Knight(6, 12, 1, "White Knight");
			       pieces[42] = new Rook(0, 12, 1, "White Rook");
			       pieces[43] = new Rook(7, 12, 1, "White Rook");
			       pieces[44] = new Knight(1, 2, 2, "Black Knight");
			       pieces[45] = new Knight(6, 2, 2, "Black Knight");
			       pieces[46] = new Rook(0, 2, 2, "Black Rook");
			       pieces[47] = new Rook(7, 2, 2, "Black Rook");
			       pieces[48] = new Knight(1, 11, 1, "white Knight");
			       pieces[49] = new Knight(6, 11, 1, "White Knight");
			       pieces[50] = new Rook(0, 11, 1, "White Rook");
			       pieces[51] = new Rook(7, 11, 1, "White Rook");
			       pieces[52] = new Rook(0, 10, 1, "White Rook");
			       pieces[53] = new Rook(7, 10, 1, "White Rook");
			       pieces[54] = new Rook(0, 3, 2, "Black Rook");
			       pieces[55] = new Rook(7, 3, 2, "Black Rook");
			       break;
		case 4: 
			       int[] placement = new int[8];
			       Random rand = new Random();
			       for (int i = 0; i < 8; i++) {
				         placement[i] = i;
			}

			       for (int i = 0; i < 10000; i++) {
				         int pos1 = Math.abs(rand.nextInt(8)) % 8;
				         int pos2 = Math.abs(rand.nextInt(8)) % 8;
				         int temp = placement[pos1];
				         placement[pos1] = placement[pos2];
				         placement[pos2] = temp;
			}
			       pieces = new ChessPiece[32];
			       pieces[0] = new King(placement[0], 0, 2, "Black King");
			       pieces[1] = new King(placement[0], 7, 1, "White King");
			       pieces[2] = new Knight(placement[1], 0, 2, "Black Knight");
			       pieces[3] = new Knight(placement[2], 0, 2, "Black Knight");
			       pieces[4] = new Rook(placement[3], 0, 2, "Black Rook");
			       pieces[5] = new Rook(placement[4], 0, 2, "Black Rook");
			       pieces[6] = new Knight(placement[1], 7, 1, "White Knight");
			       pieces[7] = new Knight(placement[2], 7, 1, "White Knight");
			       pieces[8] = new Rook(placement[3], 7, 1, "White Rook");
			       pieces[9] = new Rook(placement[4], 7, 1, "White Rook");
			       pieces[10] = new Bishop(placement[5], 0, 2, "Black Bishop");
			       pieces[11] = new Bishop(placement[6], 0, 2, "Black Bishop");
			       pieces[12] = new Bishop(placement[5], 7, 1, "White Bishop");
			       pieces[13] = new Bishop(placement[6], 7, 1, "White Bishop");
			       pieces[14] = new Pawn(0, 6, 1, "White Pawn");
			       pieces[15] = new Pawn(1, 6, 1, "White Pawn");
			       pieces[16] = new Pawn(2, 6, 1, "White Pawn");
			       pieces[17] = new Pawn(3, 6, 1, "White Pawn");
			       pieces[18] = new Pawn(4, 6, 1, "White Pawn");
			       pieces[19] = new Pawn(5, 6, 1, "White Pawn");
			       pieces[20] = new Pawn(6, 6, 1, "White Pawn");
			       pieces[21] = new Pawn(7, 6, 1, "White Pawn");
			       pieces[23] = new Pawn(0, 1, 2, "Black Pawn");
			       pieces[24] = new Pawn(1, 1, 2, "Black Pawn");
			       pieces[25] = new Pawn(2, 1, 2, "Black Pawn");
			       pieces[26] = new Pawn(3, 1, 2, "Black Pawn");
			       pieces[27] = new Pawn(4, 1, 2, "Black Pawn");
			       pieces[28] = new Pawn(5, 1, 2, "Black Pawn");
			       pieces[29] = new Pawn(6, 1, 2, "Black Pawn");
			       pieces[30] = new Pawn(7, 1, 2, "Black Pawn");
			       pieces[31] = new Queen(placement[7], 0, 2, "Black Queen");
			       pieces[22] = new Queen(placement[7], 7, 1, "White Queen");
			       break;

		case 5: 
			       pieces = new ChessPiece[32];
			       pieces[0] = new King(4, 0, 2, "Black King");
			       pieces[1] = new King(4, 7, 1, "White King");
			       pieces[2] = new Knight(5, 0, 2, "Black Knight");
			       pieces[3] = new Knight(6, 0, 2, "Black Knight");
			       pieces[4] = new Rook(0, 0, 2, "Black Rook");
			       pieces[5] = new Rook(7, 0, 2, "Black Rook");
			       pieces[6] = new Knight(5, 7, 1, "white Knight");
			       pieces[7] = new Knight(6, 7, 1, "White Knight");
			       pieces[8] = new Rook(0, 7, 1, "White Rook");
			       pieces[9] = new Rook(7, 7, 1, "White Rook");
			       pieces[10] = new Bishop(1, 0, 2, "Black Bishop");
			       pieces[11] = new Bishop(2, 0, 2, "Black Bishop");
			       pieces[12] = new Bishop(1, 7, 1, "White Bishop");
			       pieces[13] = new Bishop(2, 7, 1, "White Bishop");
			       pieces[14] = new Pawn(0, 6, 1, "White Pawn");
			       pieces[15] = new Pawn(1, 6, 1, "White Pawn");
			       pieces[16] = new Pawn(2, 6, 1, "White Pawn");
			       pieces[17] = new Pawn(3, 6, 1, "White Pawn");
			       pieces[18] = new Pawn(4, 6, 1, "White Pawn");
			       pieces[19] = new Pawn(5, 6, 1, "White Pawn");
			       pieces[20] = new Pawn(6, 6, 1, "White Pawn");
			       pieces[21] = new Pawn(7, 6, 1, "White Pawn");
			       pieces[23] = new Pawn(0, 1, 2, "Black Pawn");
			       pieces[24] = new Pawn(1, 1, 2, "Black Pawn");
			       pieces[25] = new Pawn(2, 1, 2, "Black Pawn");
			       pieces[26] = new Pawn(3, 1, 2, "Black Pawn");
			       pieces[27] = new Pawn(4, 1, 2, "Black Pawn");
			       pieces[28] = new Pawn(5, 1, 2, "Black Pawn");
			       pieces[29] = new Pawn(6, 1, 2, "Black Pawn");
			       pieces[30] = new Pawn(7, 1, 2, "Black Pawn");
			       pieces[31] = new Queen(3, 0, 2, "Black Queen");
			       pieces[22] = new Queen(3, 7, 1, "White Queen");
			       break;
		case 6: 
			       pieces = new ChessPiece[32];
			       pieces[1] = new King(4, 0, 1, "White King");
			       pieces[0] = new King(4, 7, 2, "Black King");
			       pieces[2] = new Knight(1, 0, 1, "White Knight");
			       pieces[3] = new Knight(6, 0, 1, "White Knight");
			       pieces[4] = new Rook(0, 0, 1, "White Rook");
			       pieces[5] = new Rook(7, 0, 1, "White Rook");
			       pieces[6] = new Knight(1, 7, 2, "Black Knight");
			       pieces[7] = new Knight(6, 7, 2, "Black Knight");
			       pieces[8] = new Rook(0, 7, 2, "Black Rook");
			       pieces[9] = new Rook(7, 7, 2, "Black Rook");
			       pieces[10] = new Bishop(2, 0, 1, "White Bishop");
			       pieces[11] = new Bishop(5, 0, 1, "White Bishop");
			       pieces[12] = new Bishop(2, 7, 2, "Black Bishop");
			       pieces[13] = new Bishop(5, 7, 2, "Black Bishop");
			       pieces[14] = new Pawn(0, 6, 2, "Black Pawn");
			       pieces[15] = new Pawn(1, 6, 2, "Black Pawn");
			       pieces[16] = new Pawn(2, 6, 2, "Black Pawn");
			       pieces[17] = new Pawn(3, 6, 2, "Black Pawn");
			       pieces[18] = new Pawn(4, 6, 2, "Black Pawn");
			       pieces[19] = new Pawn(5, 6, 2, "Black Pawn");
			       pieces[20] = new Pawn(6, 6, 2, "Black Pawn");
			       pieces[21] = new Pawn(7, 6, 2, "Black Pawn");
			       pieces[23] = new Pawn(0, 1, 1, "White Pawn");
			       pieces[24] = new Pawn(1, 1, 1, "White Pawn");
			       pieces[25] = new Pawn(2, 1, 1, "White Pawn");
			       pieces[26] = new Pawn(3, 1, 1, "White Pawn");
			       pieces[27] = new Pawn(4, 1, 1, "White Pawn");
			       pieces[28] = new Pawn(5, 1, 1, "White Pawn");
			       pieces[29] = new Pawn(6, 1, 1, "White Pawn");
			       pieces[30] = new Pawn(7, 1, 1, "White Pawn");
			       pieces[31] = new Queen(3, 0, 1, "White Queen");
			       pieces[22] = new Queen(3, 7, 2, "Black Queen");
		}


		     gameStarted = true;
		     Chess.game.refresh();
	}
}


