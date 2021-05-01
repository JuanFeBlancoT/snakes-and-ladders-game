package model;

import java.util.Random;

public class GameController {
	
	//attributes
	
	
	//relations
	private Board board;
	
	
	public GameController() {
		board = new Board();
	}
	
	/**
	* createBoard: It creates a double linked matrix with a given amount of rows and columns from type Board <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param n Number of rows that the board will have.
	* @param m Number of columns that the board will have.
	*/
	public void createBoard(int n, int m) {
		board.createMatrix(n,m);
	}
	
	/**
	* getBoard: It returns the object of type Board <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return board An object from type Board
	*/
	public Board getBoard() {
		return board;
	}
	
	public void movePlayer() {
		int die = throwDie();
		board.movePlayer(die);
	}
	
	public int throwDie() {
		Random rnd = new Random();
		int dieNumber = rnd.nextInt(6)+1;
		return dieNumber;
	}
}
