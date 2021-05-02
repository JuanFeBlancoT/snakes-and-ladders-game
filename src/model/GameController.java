package model;

import java.util.Random;

import processing.core.PApplet;

public class GameController {
	
	//attributes
	private boolean gameWon;
	private int globalPosY;
	
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
		board = new Board();
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
				newPlayer.setParent(current);
			}else {
				addPlayerToTree(current.getLeft(), newPlayer);
			}
		}else {
			if(current.getRight() == null) {
				current.setRight(newPlayer);
				newPlayer.setParent(current);
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

	public void drawWinners(PApplet app) {
	
		int baseX = 50;
		int baseY = 110;
		
		if(rootWinner != null) {
			drawWinners(rootWinner, baseX, baseY, app);
		}
	}

	private void drawWinners(Player root, int bx, int by, PApplet app) {
		if(root != null) {
			drawWinners(root.getLeft(), bx, by+70, app);
			app.textSize(20);
			app.text(root.toString(), bx, by+globalPosY);
			app.rect(bx, by+40+globalPosY, 1100, 2);
			drawWinners(root.getRight(), bx, by+100, app);
		}
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public void moveWinners(int globalPosY) {
		this.globalPosY = globalPosY;
	}

	
	
}
