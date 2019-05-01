//Daniel Andrejczyk ~ COMP220.A ~ Knights Tour Problem

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class KnightsTour {
	
	static ChessBoard board;
	
	final static int WIDTH = 6;
	final static int HEIGHT = 6;
	
	private Random randObj = new Random();
	private int startX, startY = randObj.nextInt(63); //Generate a random starting position
	
	private int lastMoveIndex = 0; //Index of the last move
	private int currentMoveIndex = 1; //Index of the move being tested

	private int[][] knightOffsets = {
			{ -2, 1 }, //0
			{ -1, 2 }, //1
			{ 1, 2 }, //2
			{ 2, 1 }, //3
			{ 2, -1 }, //4
			{ 1, -2 }, //5
			{ -1, -2 }, //6
			{ -2, -1 }  //7
	};
	
	public boolean tryNextMove() {
		
		boolean success = false;
		
		ArrayList<Move> possibleMoves = new ArrayList();
		ArrayList<Move> moves = new ArrayList();
		possibleMoves.add(new Move(startX, startY));
		
		//While we haven't found thee solution GRIND GRIND GRIND!!!
		while(! success) {
			//Create possible moves(Might exist anywhere on or off the board)
			for(int i = 0; i < 7; i++) {
				possibleMoves.add(new Move(possibleMoves.get(lastMoveIndex).getX()+knightOffsets[i][0],
						possibleMoves.get(lastMoveIndex).getY()+knightOffsets[0][i]));
			}
			//If it is a legal move
			if(board.isLegalMove(possibleMoves.get(currentMoveIndex).getX(), possibleMoves.get(currentMoveIndex).getY())) {
				//Mark square as visited
				board.markSquare(possibleMoves.get(currentMoveIndex).getX(), possibleMoves.get(currentMoveIndex).getY(),1);
				//Record the legal move
				
			}
			
		}
		
		return false;
	}
	
	public static void main(String args[]) {
		
		board = new ChessBoard(WIDTH, HEIGHT);
		
		
		
	}
	
}

