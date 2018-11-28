package piece;

import java.awt.Image;

public abstract class ChessPiece {
	private int x;
	private int y;
	private int side;
	private Image image;
	private int health;

	public ChessPiece(int x, int y, int side) {
		this.x = x;
		this.y = y;
		this.side = side;
		this.health = 0;
	}

	public void setCoord(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public Image getImage() {
		return this.image;
	}

	public int getSide() {
		return this.side;
	}

	public boolean isPoison() {
		return false;
	}

	public boolean isHeal() {
		return false;
	}

	public int getHealth() {
		return this.health;
	}

	public void addHealth() {
		this.health += 1;
	}

	public void heal() {
		this.health = 0;
	}

	public abstract boolean isValid(int paramInt1, int paramInt2);

	public abstract boolean GetHasMoved();

	public abstract void setHasMoved();

	public abstract String getName();
}
