package piece;

import GameLogic.GameBoard;
import GameLogic.GameMode;
import java.awt.Toolkit;
import java.io.IOException;


public class Queen
extends ChessPiece
{
	private boolean hasMoved;
	private String name;

	public Queen(int x, int y, int side, String name)
			throws IOException
	{
		super(x, y, side);
		this.name = name;
		this.hasMoved = false;
		if (side == 1) {
			setImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("queen.png")));
		} else
			setImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("bqueen.png")));
	}

	public boolean isValid(int x, int y) {
		if (GameMode.isInBounds(x, y))
		{

		}
		return false;
	}

	public boolean GetHasMoved() {
		return false;
	}


	public void setHasMoved() {}

	public String getName()
	{
		return this.name;
	}
}


