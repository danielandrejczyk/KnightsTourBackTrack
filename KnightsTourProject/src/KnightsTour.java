//Daniel Andrejczyk and Noah Callinan ~ COMP220.A ~ Knights Tour Problem

import java.util.ArrayList;
import java.util.Random;

public class KnightsTour {
	//Attributes
	//Chess board dimensions
	final static int WIDTH = 6;
	final static int HEIGHT = 6;
	//The chess board
	public static ChessBoard board;
	//A random number generator
	private static Random randObj = new Random();
	//Arrays of moves
	private static ArrayList<Move> possibleMoves = new ArrayList<Move>();
	private static Move[] moves = new Move[37];
	private static int[] possMoveIndex = new int[36];
	//The index of the arrays we are looking at
	private static int possIndex = 0; //Index of possible moves array
	private static int moveIndex = 0; //Index of the move move array
	//The x and y cordinates of the next possible move
	private static int possX;
	private static int possY;

	private static int[][] knightOffsets = {
			{ -2, 1 }, //0
			{ -1, 2 }, //1
			{ 1, 2 }, //2
			{ 2, 1 }, //3
			{ 2, -1 }, //4
			{ 1, -2 }, //5
			{ -1, -2 }, //6
			{ -2, -1 }  //7
	};
	//Methods
	public static void possMoves(Move currMove) {
		/*from our current square, we will make an array of
		 * possible moves*/
		//first lets remove any possible moves still in this array
		possibleMoves.clear();
		for(int i = 0; i < 7; i++) {
			//For all the offets...
			//...make a "possible" move
			possibleMoves.add( 
					new Move(currMove.getY() + knightOffsets[i][0],
							currMove.getX() + knightOffsets[i][1]));
		}
	}
	public static boolean tryNextMove(Move currMove) {

		//1.0)  Init
		//1.1)  Setting success to false
		boolean success = false;
		//We are not done
		//2.0)  Processing
		//2.1)  Add the current move to the move array
		moves[moveIndex] = currMove;
		while(! success) {
			//while we are not done...
			/*2.1)  Then we will make an array of possible moves
			 * for our current square*/
			possMoves(currMove);
			//2.2)  We will then try the next possible move we are one
			if(possIndex < 7) {
				possX = possibleMoves.get(possIndex).getX();
				possY = possibleMoves.get(possIndex).getY();
			} else {
				/*if we the possible array index is over 7, we
				 * already know the move isn't legal*/
				possX = 69;
				possY = 69;
			}
			if(board.isLegalMove( possX, possY)) {
				//...if that moves works
				//2.3)...move there...
				moveIndex++;
				board.markSquare(possX, possY, moveIndex);
				moves[moveIndex] = possibleMoves.get(possIndex);
				//2.4)...and record the possible move index you picked
				possMoveIndex[moveIndex - 1] = possIndex;
				possIndex = 0;
				//And get ready for the next move
				/*2.5)  Did we find the Knights tour?*/
				if(moveIndex == 36) {
					//if we have been to all 36 square
					success = true;
					System.out.println(board.toString());
					/*...if we didn't find the knight's tour, then on to
					 * the next move*/
				} else {
					/*If we are close to a perfect Knight's tour, then
					 * we want to see that*/
					if(moveIndex == 35) {
						System.out.println(board.toString());
					}
					//On to the next move
					success = tryNextMove(moves[moveIndex]);
				}
				//2.6)  If we ever hit a dead end...
				if(! success) {
					//...move back a move...
					board.markSquare(moves[moveIndex].getX(), 
							moves[moveIndex].getY(), 0);
					moveIndex--;
					//...and try the next possible move
					possIndex = possMoveIndex[moveIndex] + 1;
				}
			} else {
				/*2.7)  If the possible move we tried is not a
				 * legal move*/
				//...try the next possible move...
				possIndex++;
				//..Unless we are out of possible moves
				if(possIndex > 7) {
					return false;
					//we hit a dead end here
				}
			}
			//If we are done we should return that we have success
			if(success) {
				return success;
			}
		}
		//end while loop
		return success;
	}
	public static void main(String args[]) {
		//1.0)  Inits
		//1.1)  Make a board
		board = new ChessBoard(WIDTH, HEIGHT);
		//1.2)  Choose a starting square
		int startX = 3;
		int startY = 3;
		/*Generate a random starting position*/
		Move start = new Move(startX, startY);
		//printing the start point too
		System.out.println("Starting at: (" + startX + 
				", " + startY + ")");
		//1.3)  Other variables
		boolean success;  /*A boolean that is true if we sucessfully
		 * make a knights tour*/
		//2.0)  Processing
		//2.1)  Trying all the moves
		success = tryNextMove(start);
		//3.0)  Output
		//3.1)  Is a Perfect Knight's tour possible?
		if(success) {
			System.out.println("\n\nWe found a perfect tour");
		} else {
			System.out.println("\n\nA perfect tour is impossible");
		}
	}
}

