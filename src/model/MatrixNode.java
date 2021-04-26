package model;

import processing.core.PApplet;

public class MatrixNode {
	
	private MatrixNode up;
	private MatrixNode down;
	private MatrixNode prev;
	private MatrixNode next;
	private MatrixNode exitPair;
	
	private int rC, gC,bC;
	private int row, col;	
	private int posX, posY, boxNumber, boxSize, globalPX, globalPY;
	
	public MatrixNode(int r, int c, int px, int py, int num, int bxs) {
		
		row = r;
		col = c;
		posX = px;
		posY = py;
		boxNumber = num;
		boxSize = bxs;
		globalPX = 0;
		globalPY = 0;
		
		rC = 255;
		gC = 255;
		bC = 255;
	}
	
	

	public void drawBox(PApplet app) {
		app.textSize(20);
		app.fill(rC,gC,bC);
		app.square(posX+globalPX, posY+globalPY, boxSize);
		app.fill(20);
		if(boxNumber<10) {
			app.text(boxNumber, posX+35+globalPX, posY+50+globalPY);
		}else {
			app.text(boxNumber, posX+28+globalPX, posY+50+globalPY);
		}
		
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
