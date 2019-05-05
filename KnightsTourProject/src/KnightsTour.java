//Daniel Andrejczyk and Noah Callinan ~ COMP220.A ~ Knights Tour Problem

//David Valentine ~ COMP220 Spring 2019	~Knight's Tour
//Implementation of Wirth's Recursive Backtracking Algorithm
//	for the Knight's Tour.  We are currently set up for a
//	6x6 chessboard, with the starting square at [2][5].
//Warning- this take about 4 minutes on my faculty machine. 

public class KnightsTour {
	static final int BOARD_SIZE = 6;				//size of our chessboard
	static final int MAX_SQUARES = 
			BOARD_SIZE * BOARD_SIZE; 		//the final move number


	public static void main(String[] args) {
		ChessBoard theBoard = new ChessBoard(BOARD_SIZE,BOARD_SIZE);	//our new board
		int row, col;		//first move on the board
		long startTime, stopTime;	//stopwatch values (running time)

		//1.0 Initialize
		System.out.print("The Knight's Tour on ");
		System.out.println(BOARD_SIZE + " x " + BOARD_SIZE + " square board");
		
		row = 2;	col = 5;	//our starting square
		theBoard.markSquare(row,col,1);	//move move #1
		
		//2.0 try Wirth's recursive backtracking solution
		startTime = System.currentTimeMillis(); //get start time
		
		if(tryTour(theBoard, 1, row, col)) {
			System.out.println("\nA solution is:");
			System.out.println(theBoard.toString());
		}
		else
			System.out.println("No solution found");
	
		stopTime = System.currentTimeMillis();	//click stopwatch
		
		//3.0 Finish up
		System.out.printf("\nElapsed running time (sec): %5.1f\n" 
				, (stopTime-startTime)*1e-3);
		System.out.println("\n\n\t<< Normal Termination >>");
	}
	
	
	//Wirth's Backtracking Algorithm
	//Chessboard b is the current board
	//The knight just put "moveNumber" into b[row][col]
	// and we are to solve the puzzle from here.
	static boolean tryTour(ChessBoard b, int moveNumber, int row, int col) {
		int curMove = 0;		//Knight's choice of move [1,8] in this call
		int curR, curC;			//location of Knight's choice
		boolean allDone = false;	//flag to show success
		int[][] moveAry = null;		//moves for our Knight

		//This takes a LONG time ~ give the human some eye candy
		//Debugging Write: we are one square away from answer
		if (moveNumber >= MAX_SQUARES - 1) {
			System.out.println("Square Number: "+ moveNumber +
				" @ [" + row + ", "+ col + "]");
			System.out.println(b.toString() + "\n\n");
		}
		
		//Initialize selection of moves (candidates)
		moveAry = loadMoveAry(b, row, col);	//up to 8 moves come back
		
		if (moveAry.length > 0)	//that is, we HAVE moves to explore
		do {
			//get curRow & curCol from moveAry (select next candidate)
			curR = moveAry[curMove][0];	curC=moveAry[curMove][1];
			
			//try that move
			if (b.isLegalMove(curR, curC)) {	//we can move there
				moveNumber ++;	//incr move counter
				b.markSquare(curR, curC, moveNumber);	//make (record) move
				if (moveNumber < MAX_SQUARES) {	//if not yet done, recurse
					allDone = tryTour(b, moveNumber, curR, curC);
					
					if (!allDone) {	//if that was dead end, try next move
						b.markSquare(curR, curC, 0);	//erase prev move
						moveNumber--;				//reset move counter
					}//!allDone
					
				}//board not yet full
				else 
					allDone=true;	//full board = success!
			}//knight has an empty square for move
			curMove++;	//go to next Knight move
			
		//continue until allDone (success) or no-more-moves to try
		} while ( (!allDone) && (curMove<moveAry.length)); 
		
		return allDone;	//send our result back to caller
	}
	
	//Knight has up to 8 moves from any [r,c] square on board
	//curMove is how far down the list of 8 we're testing
	//theMove has row in [0] and col in [1]
	static int[] getNextMove(int curMove, int r, int c) {
		int[] theMove = new int[2];
		
		switch (curMove) {	//walk through the 8 choices, in order
		//theMove[0] gets nextRow; theMove[1] gets nextCol
		case 0:	theMove[0] = r-2;	theMove[1] = c+1;
			break;
		case 1:	theMove[0] = r-1;	theMove[1] = c+2;
			break;
		case 2:	theMove[0] = r+1;	theMove[1] = c+2;
			break;
		case 3:	theMove[0] = r+2;	theMove[1] = c+1;
			break;
		case 4:	theMove[0] = r+2;	theMove[1] = c-1;
			break;
		case 5:	theMove[0] = r+1;	theMove[1] = c-2;
			break;
		case 6:	theMove[0] = r-1;	theMove[1] = c-2;
			break;
		case 7:	theMove[0] = r-2;	theMove[1] = c-1;
			break;
		default:
			throw new IllegalArgumentException(
					"Move must be in [0,7]");
		}//switch
		return theMove;		//send back to caller
	}
	
	
	//find all legal moves for Knight from b[row][col]
	static int[][] loadMoveAry(ChessBoard b, int row, int col) {
		int [][] ary = new int[8][2];	//possibly have 8 moves
		int [] aMove = null;			//move returned from getNextMove
		int curMove = 0;				//range 1..8
		int numLegalMoves = 0;			//count of legal moves [0,8]
		
		//walk through all 8 move possiblities
		for (curMove=0; curMove<8; curMove++) { //just get the moves possible
			aMove = getNextMove(curMove, row, col);
			
			if (b.isLegalMove(aMove[0], aMove[1])) {//isa legal move
					ary[numLegalMoves][0] = aMove[0]; //so add to our list
					ary[numLegalMoves][1] = aMove[1];
					numLegalMoves++;
			}
		}//for 8 move dir's

		//we have up to 8 legal moves in ary
		//create a perfectly sized array to hold them (ie length = #moves)
		int[][] retVal = new int[numLegalMoves][2];
		for (curMove=0; curMove<numLegalMoves; curMove++) {
			retVal[curMove][0] = ary[curMove][0];
			retVal[curMove][1] = ary[curMove][1];
		}
		return retVal;
	}//loadMoveAry
}


/* NOAH'S CODE
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
		//from our current square, we will make an array of
		// possible moves
		//
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
			//2.1)  Then we will make an array of possible moves
			// for our current square
			possMoves(currMove);
			//2.2)  We will then try the next possible move we are one
			if(possIndex < 7) {
				possX = possibleMoves.get(possIndex).getX();
				possY = possibleMoves.get(possIndex).getY();
			} else {
				//if we the possible array index is over 7, we
				// already know the move isn't legal
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
				//2.5)  Did we find the Knights tour?
				if(moveIndex == 36) {
					//if we have been to all 36 square
					success = true;
					System.out.println(board.toString());
					//...if we didn't find the knight's tour, then on to
					// the next move
				} else {
					//If we are close to a perfect Knight's tour, then
					// we want to see that
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
				//2.7)  If the possible move we tried is not a
				// legal move
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
		//Generate a random starting position
		Move start = new Move(startX, startY);
		//printing the start point too
		System.out.println("Starting at: (" + startX + 
				", " + startY + ")");
		//1.3)  Other variables
		boolean success;  //A boolean that is true if we sucessfully
		// make a knights tour
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
*/

