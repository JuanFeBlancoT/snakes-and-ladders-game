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
	
	public void drawPlayersInCell(PApplet app) {
		if(firstPlayer != null) {
			drawPlayersInCell(firstPlayer, app);
		}
	}
	
	private void drawPlayersInCell(Player currentPlayer, PApplet app) {
		if(currentPlayer != null) {
			currentPlayer.drawPlayer(app);
			drawPlayersInCell(currentPlayer.getNext(), app);
		}
	}
	
	public void updatePlayersPositions() {
		if(firstPlayer != null) {
			
		}
	}

	
	public int getPlayersInCell() {
		return playersInCell;
	}

	public void setPlayersInCell(int playersInCell) {
		this.playersInCell = playersInCell;
	}

	//
	public Player getFirstPlayer() {
		return firstPlayer;
	}

	public void setFirstPlayer(Player firstPlayer) {
		this.firstPlayer = firstPlayer;
	}

	public int getNumericIdentifier() {
		return numericIdentifier;
	}

	public void setNumericIdentifier(int numericIdentifier) {
		this.numericIdentifier = numericIdentifier;
	}

	public char getIdentifier() {
		return identifier;
	}

	public void setIdentifier(char identifier) {
		this.identifier = identifier;
	}

	public MatrixNode getExitPair() {
		return exitPair;
	}

	public void setExitPair(MatrixNode exitPair) {
		this.exitPair = exitPair;
	}

	public void setR(int r) {
		this.rC = r;
	}

	public void setG(int g) {
		this.gC = g;
	}

	public void setB(int b) {
		this.bC = b;
	}

	public int getGlobalPX() {
		return globalPX;
	}

	public void setGlobalPX(int globalPX) {
		this.globalPX = globalPX;
	}

	public int getGlobalPY() {
		return globalPY;
	}

	public void setGlobalPY(int globalPY) {
		this.globalPY = globalPY;
	}
	
	public MatrixNode getUp() {
		return up;
	}

	public void setUp(MatrixNode up) {
		this.up = up;
	}

	public MatrixNode getDown() {
		return down;
	}

	public void setDown(MatrixNode down) {
		this.down = down;
	}

	public MatrixNode getPrev() {
		return prev;
	}

	public void setPrev(MatrixNode prev) {
		this.prev = prev;
	}

	public MatrixNode getNext() {
		return next;
	}

	public void setNext(MatrixNode next) {
		this.next = next;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getBoxNumber() {
		return boxNumber;
	}

	public void setBoxNumber(int boxNumber) {
		this.boxNumber = boxNumber;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	public String toString() {
		return "["+row+","+col+"] :"+boxNumber;
	}
	
}
