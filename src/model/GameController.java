package model;

import java.util.Random;

public class GameController {
	
	//attributes
	private boolean gameWon;
	
	//relations
	private Board board;
	private Player rootWinner;
	private Player actualWinner;
	
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
		gameWon = board.isGameWon();
		if(gameWon) {
			Player winner = board.getWinner();
			addPlayerToTree(winner);
			actualWinner = winner;
		}
	}
	
	private void addPlayerToTree(Player winner) {
		if(rootWinner == null) {
			rootWinner = winner;
		}else {
			addPlayerToTree(rootWinner, winner);
		}
	}
	
	private void addPlayerToTree(Player current, Player newPlayer) {
		if(newPlayer.getScore() >= current.getScore()) {
			if(current.getLeft() == null) {
				current.setLeft(newPlayer);
			}else {
				addPlayerToTree(current.getLeft(), newPlayer);
			}
		}else {
			if(current.getRight() == null) {
				current.setRight(newPlayer);
			}else {
				addPlayerToTree(current.getRight(), newPlayer);
			}
		}
	}

	public int throwDie() {
		Random rnd = new Random();
		int dieNumber = rnd.nextInt(6)+1;
		return dieNumber;
	}

	public boolean isGameWon() {
		return gameWon;
	}

	public void setGameWon(boolean gameWon) {
		this.gameWon = gameWon;
	}

	public Player getActualWinner() {
		return actualWinner;
	}

	public void setActualWinner(Player actualWinner) {
		this.actualWinner = actualWinner;
	}
	
	
}
