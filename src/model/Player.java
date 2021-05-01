package model;

import javax.naming.InitialContext;

import processing.core.PApplet;

public class Player {
	
	//atributtes
	private int globalPosX, globalPosY;
	private int posX, posY;				//X and Y coordinates of the player
	private int row, col;				//row and col in which the player is located within the matrix
	private int score;					//score of the player: Calculated by multiplying the amount of movements it took the player to win times the sixe of the board (n*m)
	private int movs;					//Amount of moves the player does in a match
	private int size;
	private String symbol;				//symbol that identifies the player during the match
	private String nickName;			//Nickname of the player, required for the winners table (menu option 2)
	private int turn, currentPos;
	
	private Player next;
	private Player prev;
	
	public Player(int row, int col, int px, int py, String s, int t, int mov) {
		currentPos = 1;
		score = 0;
		movs = mov;
		posX = 0;
		size = 20;
		symbol = s;
		turn = t;
		
		posX = px;
		posY = py;
		globalPosX = 0;
		globalPosY = 0;
	}
	
	public void drawPlayer(PApplet app) {
		app.noStroke();
		app.fill(255);
		app.circle(posX+globalPosX, posY+globalPosY, size);
		app.textSize(14);
		app.fill(0);
		app.text(turn, (posX+globalPosX)-5, posY+globalPosY+5);
		app.stroke(80);
	}
	
	
	public int getMovs() {
		return movs;
	}

	public void setMovs(int movs) {
		this.movs = movs;
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

	public int getCurrentPos() {
		return currentPos;
	}

	public void setCurrentPos(int currentPos) {
		this.currentPos = currentPos;
	}

	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public int getGlobalPosX() {
		return globalPosX;
	}

	public void setGlobalPosX(int globalPosX) {
		this.globalPosX = globalPosX;
	}

	public int getGlobalPosY() {
		return globalPosY;
	}

	public void setGlobalPosY(int globalPosY) {
		this.globalPosY = globalPosY;
	}

	public Player getNext() {
		return next;
	}

	public void setNext(Player next) {
		this.next = next;
	}

	public Player getPrev() {
		return prev;
	}

	public void setPrev(Player prev) {
		this.prev = prev;
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

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	
}
