package model;

import processing.core.PApplet;

public class MatrixNode {
	
	private MatrixNode up;
	private MatrixNode down;
	private MatrixNode prev;
	private MatrixNode next;
	private MatrixNode exitPair;
	private Player firstPlayer;
	
	private int rC, gC,bC;
	private int row, col;	
	private int posX, posY, boxNumber, boxSize, globalPX, globalPY;
	private char identifier;
	private int numericIdentifier;
	private int playersInCell;
	
	/**
	* matrixNode: Class constructor <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param r Integer row location
	* @param c Integer column location
	* @param px Integer horizontal position
	* @param py Integer vertical position
	* @param num Integer cell order
	* @param bxs Integer cell size
	*/
	public MatrixNode(int r, int c, int px, int py, int num, int bxs) {
		
		row = r;
		col = c;
		posX = px;
		posY = py;
		boxNumber = num;
		boxSize = bxs;
		globalPX = 0;
		globalPY = 0;
		numericIdentifier = 0;
		identifier = ' ';
		playersInCell = 0;
		firstPlayer = null;
		
		if(num%2 == 0) {
			rC = 136;
			gC = 250;
			bC = 148;
		}else {
			rC = 98;
			gC = 219;
			bC = 106;
		}
		
	}

	/**
	* drawBox: Renders a cell with its player, if it has <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param app Reference to the PApplet library
	*/
	public void drawBox(PApplet app) {
		app.textSize(20);
		app.fill(rC,gC,bC);
		app.square(posX+globalPX, posY+globalPY, boxSize);
		app.fill(20);
		if(boxNumber<10) {
			app.text(boxNumber, posX+35+globalPX, posY+45+globalPY);
		}else {
			app.text(boxNumber, posX+28+globalPX, posY+45+globalPY);
		}
		app.textSize(15);
		if(numericIdentifier != 0) {
			app.text(numericIdentifier, posX+35+globalPX, posY+65+globalPY);
		}
		if(identifier != ' ') {
			app.text(identifier, posX+35+globalPX, posY+65+globalPY);
		}
		drawPlayersInCell(app);
	}
	
	/**
	* drawPlayer: Starts a recursive call to draw all players within the cell <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param app Reference to the PApplet library
	*/
	public void drawPlayersInCell(PApplet app) {
		if(firstPlayer != null) {
			drawPlayersInCell(firstPlayer, app);
		}
	}
	
	/**
	* drawPlayersInCell: draws every player in the current cell <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param currentPlayer The current player on the linked list of players
	* @param app Reference to the PApplet library
	*/
	private void drawPlayersInCell(Player currentPlayer, PApplet app) {
		if(currentPlayer != null) {
			currentPlayer.drawPlayer(app);
			drawPlayersInCell(currentPlayer.getNext(), app);
		}
	}
	
	/**
	* getPlayersInCell: Gets the amount of players in the cell <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return playersInCell Integer number with the amount of players in a cell
	*/
	public int getPlayersInCell() {
		return playersInCell;
	}

	/**
	* setPlayersInCell: Sets the amount of players in the cell <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param playersInCell Integer number with the new amount of players in a cell
	*/
	public void setPlayersInCell(int playersInCell) {
		this.playersInCell = playersInCell;
	}
	
	/**
	* getFirstPlayer: Gets the Player associated to the head of the linked list <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return firstPlayer Player object associated to the head of the linked list
	*/
	public Player getFirstPlayer() {
		return firstPlayer;
	}

	/**
	* getFirstPlayer: Gets the Player associated to the head of the linked list <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param firstPlayer Player object associated to the head of the linked list
	*/
	public void setFirstPlayer(Player firstPlayer) {
		this.firstPlayer = firstPlayer;
	}

	/**
	* getNumericIdentifier: Gets the numeric identifier in case the node is a ladder <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return numericIdentifier Integer number that indicates which ladder is paired with another one
	*/
	public int getNumericIdentifier() {
		return numericIdentifier;
	}

	/**
	* setNumericIdentifier: Sets the numeric identifier in case the node is a ladder <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return numericIdentifier Integer number that indicates which ladder is paired with another one
	*/
	public void setNumericIdentifier(int numericIdentifier) {
		this.numericIdentifier = numericIdentifier;
	}

	/**
	* getIdentifier: Gets the new char identifier for the snakes <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return identifier Char that indicates which snake is paired with another one
	*/
	public char getIdentifier() {
		return identifier;
	}

	/**
	* setIdentifier: Gets the new char identifier for the snakes <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return identifier Char that indicates which snake is paired with another one
	*/
	public void setIdentifier(char identifier) {
		this.identifier = identifier;
	}

	/**
	* getExitPair: Gets the exit pair of the cell whether it is a snake or ladder <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return exitPair MatrixNode that references the pair cell
	*/
	public MatrixNode getExitPair() {
		return exitPair;
	}

	/**
	* setExitPair: Gets the exit pair of the cell whether it is a snake or ladder <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param exitPair MatrixNode that references the pair cell
	*/
	public void setExitPair(MatrixNode exitPair) {
		this.exitPair = exitPair;
	}

	/**
	* setR: Sets the red attribute for the cell color <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param r Integer between 0 and 255 that determines the amount of red that the cell is going to have
	*/
	public void setR(int r) {
		this.rC = r;
	}

	/**
	* setG: Sets the green attribute for the cell color <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param g Integer between 0 and 255 that determines the amount of green that the cell is going to have
	*/
	public void setG(int g) {
		this.gC = g;
	}

	/**
	* setB: Sets the red attribute for the cell color <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param b Integer between 0 and 255 that determines the amount of blue that the cell is going to have
	*/
	public void setB(int b) {
		this.bC = b;
	}

	/**
	* setGlobalPX: Sets the global horizontal position for using the scroll <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param globalPX Integer that determines the horizontal position that will be added to the default position of all objects within the screen
	*/
	public void setGlobalPX(int globalPX) {
		this.globalPX = globalPX;
	}

	/**
	* setGlobalPY: Sets the global vertical position for using the scroll <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param globalPX Integer that determines the vertical position that will be added to the default position of all objects within the screen
	*/
	public void setGlobalPY(int globalPY) {
		this.globalPY = globalPY;
	}
	
	/**
	* getUp: Gets the Matrix node above the current one<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return up MatrixNode above the current node within the matrix
	*/
	public MatrixNode getUp() {
		return up;
	}

	/**
	* setUp: Sets the Matrix node above the current one<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param up New MatrixNode above the current node within the matrix
	*/
	public void setUp(MatrixNode up) {
		this.up = up;
	}

	/**
	* setDown: Sets the Matrix node below the current one<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param down MatrixNode below the current node within the matrix
	*/
	public void setDown(MatrixNode down) {
		this.down = down;
	}
	
	/**
	* getDown: Gets the Matrix node below the current one<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return down MatrixNode below the current node within the matrix
	*/
	public MatrixNode getDown() {
		return down;
	}

	/**
	* getPrev: Gets the previous matrix node to the current one<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return prev Previous matrix node to the current node within the matrix
	*/
	public MatrixNode getPrev() {
		return prev;
	}

	/**
	* setPrev: Sets the previous matrix node to the current one<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param prev New previous matrix node to the current node within the matrix
	*/
	public void setPrev(MatrixNode prev) {
		this.prev = prev;
	}

	/**
	* getNext: Gets the next matrix node to the current one<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return next Next matrix node to the current node within the matrix
	*/
	public MatrixNode getNext() {
		return next;
	}

	/**
	* setNext: Sets the next matrix node to the current one<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param next New next matrix node to the current node within the matrix
	*/
	public void setNext(MatrixNode next) {
		this.next = next;
	}

	/**
	* getRow: Gets the row of the matrix node to the current one<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return row The row where the matrix is located
	*/
	public int getRow() {
		return row;
	}

	/**
	* getCol: Gets the column of the matrix node to the current one<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return col The column where the matrix is located
	*/
	public int getCol() {
		return col;
	}

	/**
	* getBoxNumber: Gets the number of the cell in the board game <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return boxNumber Integer cell number
	*/
	public int getBoxNumber() {
		return boxNumber;
	}

	/**
	* setBoxNumber: Sets the number of the cell in the board game <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return boxNumber The new cell number
	*/
	public void setBoxNumber(int boxNumber) {
		this.boxNumber = boxNumber;
	}
	
}
