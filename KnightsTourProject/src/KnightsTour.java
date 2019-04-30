//Daniel Andrejczyk ~ COMP220.A ~ Knights Tour Problem

import java.util.Random;

public class KnightsTour {
	
	final static int WIDTH = 6;
	final static int HEIGHT = 6;
	
	private Random randObj = new Random();
	private int startX, startY = randObj.nextInt(63); //Generate a random starting position
	
	
	
	public static void main(String args[]) {
		
		ChessBoard board = new ChessBoard(WIDTH, HEIGHT);
		
	}
	
}

