//Daniel Andrejczyk ~ COMP220.A ~ Knights Tour Problem

public class Move {
	
	private int x;
	private int y;
	
	public Move() {
		x = 1;
		y = 1;
	} //Default Constructor
	
	public Move(int x, int y) {
		this.x = x;
		this.y = y;
	} //Constructor

	/**
	 * @return	x	Gets the x value of the move.
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param	x	Sets the x value of the move.
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return	y	Gets the y value of the move.
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y		Sets the y value of the move.
	 */
	public void setY(int y) {
		this.y = y;
	}

}
