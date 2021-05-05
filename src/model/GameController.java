package model;

import java.util.Random;

import processing.core.PApplet;

public class GameController {
	
	//attributes
	private boolean gameWon;
	private int globalPosY;
	private int yW;
	
	private int diceNum;
	private String playerSymbol;
	
	//relations
	private Board board;
	private Player rootWinner;
	private Player actualWinner;
	
	/**
	* GameController: Class constructor <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	*/
	public GameController() {
		board = new Board();
	}
	
	/**
	* createBoard: It creates a new board object and calls the method that creates the matrix <br>
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
	
	/**
	* movePlayer: Calls a method that generates a random number between 1 and 6, then sends that number to another method which moves the player and finally verifies if the game was won in that turn <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param app Reference to PApplet
	*/
	public void movePlayer(PApplet app) {
		
		int die = throwDie();
		playerSymbol = board.movePlayer(die);
		diceNum = die;
		
		gameWon = board.isGameWon();
	}
	
	/**
	* setWinner: Obtains the winner Player object from the class board and assigns its nickname, then the winner is added to the winners binary tree <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param name The String containing the nickname of the winner player
	*/
	public void setWinner(String name) {
		Player winner = board.getWinner();
		winner.setNickName(name);
		actualWinner = winner;
		addPlayerToTree(winner);
	}
	
	/**
	* addPlayerToTree: Receives a Player object that is going to be added to a binary tree using recursion <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param winner Player object
	*/
	private void addPlayerToTree(Player winner) {

		if(rootWinner == null) {
			rootWinner = winner;
		}else {
			addPlayerToTree(rootWinner, winner);
		}
	}
	
	/**
	* addPlayerToTree: Receives a Player object that is going to be added to a binary tree using recursion, using the score as criteria <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param current Parent player object
	* @param newPLayer Player object to be added
	*/
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

	/**
	* throwDie: Generates a random number from 1 to 6 <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return dieNumber The amount of position the player is going to move
	*/
	
	public int throwDie() {
		Random rnd = new Random();
		int dieNumber = rnd.nextInt(6)+1;
		return dieNumber;
	}

	/**
	* isGameWon: Gets the boolean that determines if the match was won <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return gameWon Boolean
	*/
	public boolean isGameWon() {
		return gameWon;
	}

	/**
	* setGameWon: Sets the boolean that determines if the match was won <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return gameWon Boolean
	*/
	public void setGameWon(boolean gameWon) {
		this.gameWon = gameWon;
	}

	/**
	* getActualWinner: Gets the Player object that won the match <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return actualWinner Player object who won the game
	*/
	public Player getActualWinner() {
		return actualWinner;
	}

	/**
	* setActualWinner: Sets the Player object that won the match <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param actualWinner Player object who won the game
	*/
	public void setActualWinner(Player actualWinner) {
		this.actualWinner = actualWinner;
	}

	/**
	* drawWinners: Renders the winners attributes by using recursion <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param app The reference to PApplet
	*/
	public void drawWinners(PApplet app) {
		yW = 70;	
		int baseX = 50;
		int baseY = 110;
		
		if(rootWinner != null) {
			drawWinners(rootWinner, baseX, baseY, app);
		}
	}

	/**
	* drawWinners: Renders the winners information  <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param root Current root Player object on the binary tree
	* @param bx base horizontal position
	* @param by base vertical position
	* @param app Reference to PApplet
	*/
	private void drawWinners(Player root, int bx, int by, PApplet app) {
		yW+=40;
		if(root != null) {
			yW+=40;
			drawWinners(root.getLeft(), bx, by+70, app);
			app.textSize(20);
			app.text(root.toString(), bx, yW+globalPosY);
			app.rect(bx, yW+40+globalPosY, 1100, 2);
			yW+=40;
			drawWinners(root.getRight(), bx, by+100, app);
		}
	}

	/**
	* setBoard: Sets the board<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param board The new board
	*/
	public void setBoard(Board board) {
		this.board = board;
	}

	/**
	* moveWinners: Updates the vertical position of the winners to simulate a scroll <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param globalPosY The Integer number added to the base position of the players
	*/
	public void moveWinners(int globalPosY) {
		this.globalPosY = globalPosY;
	}

	/**
	* getDiceNum: Gets the number obtain from throwing the dice <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return diceNum Integer between 1 and 6
	*/
	public int getDiceNum() {
		return diceNum;
	}
	
	/**
	* getPlayerSymbol: Gets the current player who's playing symbol <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return playerSymbol String with the player symbol
	*/
	public String getPlayerSymbol() {
		return playerSymbol;
	}	
	
}
