package piece;

import GameLogic.GameBoard;
import java.awt.Toolkit;

public class Knight extends ChessPiece
{
	private String name;

	public Knight(int x, int y, int side, String name)
	{
		super(x, y, side);
		this.name = name;
		if (side == 1) {
			setImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("knight.png")));
		} else {
			setImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("bknight.png")));
		}
	}

	public boolean isValid(int x, int y)
	{
		if (GameLogic.GameMode.isInBounds(x, y)) {

		}

		return false;
	}




	public boolean GetHasMoved()
	{
		return false;
	}

	public void setHasMoved() {}

	public String getName()
	{
		return this.name;
	}
}

