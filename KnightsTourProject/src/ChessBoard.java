
public class ChessBoard {

	//Attributes
	private int MAX_Length = 8;
	private int MAX_Width = 8;
	private int[][] board;
	//Constructor
	public ChessBoard() {
		MAX_Length = 8;
		MAX_Width = 8;
		this.initBoard();
	}
	public ChessBoard(int length, int width) {
		MAX_Length = length;
		MAX_Width = width;
		this.initBoard();
	}
	//Medthods
	public void initBoard() {
		board = new int[MAX_Length+2][MAX_Width+2];
		for(int i = 0; i < MAX_Length+2; i++) {
			for(int j = 0; j < MAX_Width+2; j++) {
				if (i == 0 || i == MAX_Length+1 || j == 0 || j == MAX_Width+1) {
					board[i][j] = 69; //69 for border
				} else {
					board[i][j] = 0; //Movable square
				}
			}
		}
	}
	//End init board method
	public boolean isLegalMove(int column, int row) {
		try {
			if(board[column][row] == 0) {
				return true;
			} else {
				return false;
			}
		} catch(ArrayIndexOutOfBoundsException e) {
			//Tried to move to a square outside the board. Not a legal move.
			return false;
		}
	}
	//End isLegal method
	public void markSquare(int column, int row, int value) {
		board[column][row] = value;
	}
	//End markSquare
	public String toString() {
		String boardString = new String();
		//for each row
		for(int j = 0; j < MAX_Length+2; j++) {
			//for each column
			for(int i = 0; i < MAX_Width+2; i++) {
				if (board[i][j] == 69) {
					boardString += " B ";
				} else if (board[i][j] == 0) {
					boardString += " O ";
				} else {
					boardString += " M ";
				}
				//adding a new line at the end of every row
				if(i == MAX_Width + 1) {
					boardString += "\n";
				}
			}
		}
		return boardString;
	}
	//End toString
	public int getSquare(int i, int j) {
		// TODO Auto-generated method stub
		return board[i][j];
	}
}


