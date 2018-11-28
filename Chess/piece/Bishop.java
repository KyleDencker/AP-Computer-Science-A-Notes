package piece;

import GameLogic.GameBoard;
import GameLogic.GameMode;
import java.awt.Toolkit;
import java.io.IOException;

public class Bishop extends ChessPiece {
	private String name;

	public Bishop(int x, int y, int side, String name) throws IOException {
		super(x, y, side);
		this.name = name;
		if (side == 1) {
			setImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("bishop.png")));
		} else {
			setImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("bbishop.png")));
		}
	}

	public boolean isValid(int x, int y) {
		if (GameMode.isInBounds(x, y)) {
			
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
}
