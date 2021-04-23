package model;

import processing.core.PApplet;

public class MatrixNode {
	
	private MatrixNode up;
	private MatrixNode down;
	private MatrixNode prev;
	private MatrixNode next;
	
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
	}
	
	

	public void drawBox(PApplet app) {
		
		app.fill(255);
		app.square(posX+globalPX, posY+globalPY, boxSize);
		app.fill(20);
		app.text(boxNumber, posX+40+globalPX, posY+40+globalPY);
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
