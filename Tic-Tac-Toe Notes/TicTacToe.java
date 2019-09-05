import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class TicTacToe extends JComponent implements MouseListener {

	Color[][] board;
	int turn = 0;
	public TicTacToe() {
		// setting all the variables for our game
		board = new Color[3][3]; // y, x;
		addMouseListener(this);

		board[0][0] = Color.white;
		board[0][1] = Color.white;
		board[0][2] = Color.white;
		board[1][0] = Color.white;
		board[1][1] = Color.white;
		board[1][2] = Color.white;
		board[2][0] = Color.white;
		board[2][1] = Color.white;
		board[2][2] = Color.white;

	}

	public void paintComponent(Graphics g) {
		g.fillRect(100, 0, 1, 300);
		g.fillRect(200, 0, 1, 300);
		g.fillRect(0, 100, 300, 1);
		g.fillRect(0, 200, 300, 1);

		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				g.setColor(board[row][col]);
				g.fillRect(row*100+1, col*100+1, 99, 99);
			}
		}

	}

	public static void main(String[] args) {

		JFrame frame = new JFrame("Tic-Tac-Toe - Title");
		frame.setVisible(true);
		frame.setSize(300, 320);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new TicTacToe());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println(e.getX()/100 + ", " + e.getY()/100);
		if (board[e.getX()/100][e.getY()/100].equals(Color.white)) {
			if (turn%2 == 0) {
				board[e.getX()/100][e.getY()/100] = Color.magenta;
			} else {
				board[e.getX()/100][e.getY()/100] = Color.green;
			}
			turn++;
			
			// check to see if someone wins, and if they win then make the entire board that color

			
			repaint();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
