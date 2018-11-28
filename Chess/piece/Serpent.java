package piece;

import GameLogic.GameBoard;
import java.awt.Toolkit;

public class Serpent extends ChessPiece {
	private String name;

	public Serpent(int x, int y, int side, String name) {
		super(x, y, side);
		this.name = name;
		if (side == 1) {
			setImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("serp.png")));
		} else {
			setImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("bserp.png")));
		}
	}

	public boolean isValid(int x, int y) {
		if (GameLogic.GameMode.isInBounds(x, y)) {
			
		}

		return false;
	}

	public boolean GetHasMoved() {
		return false;
	}

	public void setHasMoved() {
	}

	public String getName() {
		return this.name;
	}

	public boolean isPoison() {
		return true;
	}
}
