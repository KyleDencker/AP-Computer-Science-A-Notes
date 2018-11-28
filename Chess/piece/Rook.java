package piece;

import GameLogic.GameBoard;
import GameLogic.GameMode;
import java.awt.Toolkit;
import java.io.IOException;

public class Rook extends ChessPiece {
	private String name;
	private boolean hasMoved;

	public Rook(int x, int y, int side, String name) throws IOException {
		super(x, y, side);
		this.name = name;
		this.hasMoved = false;
		if (side == 1) {
			setImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("rook.png")));
		} else {
			setImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("brook.png")));
		}
	}

	public boolean isValid(int x, int y) {
		if (GameMode.isInBounds(x, y)) {
		
		}
		return false;
	}

	public boolean GetHasMoved() {
		return this.hasMoved;
	}

	public void setHasMoved() {
		this.hasMoved = true;
	}

	public String getName() {
		return this.name;
	}
}
