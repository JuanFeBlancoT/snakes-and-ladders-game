package model;

import processing.core.PApplet;

public class Board {
	
	//constants
	public final static int BOXSIZE = 80;
	
	//attributes
	private int rows,cols;
	private int givenPX, givenPY, givenNum;
	
	//relations	
	private MatrixNode first;
	
	public Board() {
		rows = 0;
		cols = 0;
		givenPX = 0;
		givenPY = 240;
		givenNum = 1;
	}
	
	/**
	* createMatrix: It creates a double linked matrix with a given amount of rows and columns <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param n Number of rows that the board will have.
	* @param m Number of columns that the board will have.
	*/
	public void createMatrix(int n, int m) {
		rows = n;
		cols = m;
		first = new MatrixNode(0, 0, givenPX, givenPY, givenNum, BOXSIZE);
		createRow(0,0, givenPX, givenPY, first, first.getDown());
	}

	/**
	* createRow: It creates a new row in the double linked matrix by using recursion <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param i Row position.
	* @param j Column position.
	* @param px The X coordinates of the node that would be used for showing it.
	* @param py The Y coordinates of the node that would be used for showing it.
	* @param firstRow Is the first node in a row.
	* @param prevBelow Is the node below the firstRow node, used for linking vertically.
	*/
	private void createRow(int i, int j, int px, int py, MatrixNode firstRow, MatrixNode prevBelow) {
		createCol(i, j+1, givenPX+BOXSIZE, py, firstRow, prevBelow);
		if(i+1<rows) {

			givenNum+=cols;
			MatrixNode upNode = new MatrixNode(i+1, j, px, py-BOXSIZE, givenNum, BOXSIZE);
			upNode.setDown(firstRow);
			firstRow.setUp(upNode);
			createRow(i+1, j, px, py-BOXSIZE, upNode, firstRow);
		}
	}
	
	/**
	* createCol: It creates a new node in a column from a double linked matrix by using recursion <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param i Row position.
	* @param j Column position.
	* @param px The X coordinates of the node that would be used for showing it.
	* @param py The Y coordinates of the node that would be used for showing it.
	* @param prev Is the previous node in a row, used for linking and recursion.
	* @param below Is the node below the previous node, used for linking vertically.
	*/
	private void createCol(int i, int j, int px, int py, MatrixNode prev, MatrixNode below) {
		if(j<cols) {
		
			MatrixNode current;
			if(i%2 == 0) {
				givenNum++;
				current = new MatrixNode(i, j, px, py, givenNum, BOXSIZE);
			}else {
				givenNum--;
				current = new MatrixNode(i, j, px, py, givenNum, BOXSIZE);
			}
			prev.setNext(current);
			current.setPrev(prev);
			if(below != null) {
				current.setDown(below.getNext());
				below.getNext().setUp(current);
				if(below.getNext() != null) {
					below = below.getNext();
				}
			}
			createCol(i, j+1, px+BOXSIZE, py, current, below);
		}
	}
	
	/**
	* drawBoard: It shows the board in the UI with recursion <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param app Is the reference to the PApplet library.
	*/
	public void drawBoard(PApplet app) {
		drawRow(app, first);
	}
	
	/**
	* drawRow: It renders a row from the double linked matrix <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param app Is the reference to the PApplet library.
	* @param firstRow Is the first node from a row in the matrix.
	*/
	private void drawRow(PApplet app, MatrixNode firstRow) {
		if(firstRow != null) {
			drawCol(app, firstRow);
			drawRow(app, firstRow.getUp());
		}
	}

	/**
	* drawCol: It renders a column from the double linked matrix <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param app Is the reference to the PApplet library.
	* @param current Is the current node that is being rendered.
	*/
	private void drawCol(PApplet app, MatrixNode current) {
		 if(current != null) {
			 current.drawBox(app);
			 drawCol(app, current.getNext());
		 }
	}

	/**
	* moveTable: It changes the horizontal and vertical position of all nodes in a double linked matrix using recursion <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param globalPosX Is an integer that is added to the node horizontal position.
	* @param globalPosY Is an integer that is added to the node vertical position.
	*/
	public void moveTable(int globalPosX, int globalPosY) {
		updateRowPositions(first, globalPosX,globalPosY);
	}

	/**
	* updateRowPosition: It changes the horizontal and vertical position of all nodes in a row <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param firstInRow Is the first node in a matrix row.
	* @param globalPosX Is an integer that is added to the node horizontal position.
	* @param globalPosY Is an integer that is added to the node vertical position.
	*/
	private void updateRowPositions(MatrixNode firstInRow, int globalPosX, int globalPosY) {
		if(firstInRow != null) {
			updateColPositoins(firstInRow, globalPosX, globalPosY);
			updateRowPositions(firstInRow.getUp(), globalPosX, globalPosY);
		}
	}

	/**
	* updateColPosition: It changes the horizontal and vertical position of all nodes in a row <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param current Is the current node that is being updated.
	* @param globalPosX Is an integer that is added to the node horizontal position.
	* @param globalPosY Is an integer that is added to the node vertical position.
	*/
	private void updateColPositoins(MatrixNode current, int globalPosX, int globalPosY) {
		if(current != null) {
			current.setGlobalPX(globalPosX);
			current.setGlobalPY(globalPosY);
			updateColPositoins(current.getNext(), globalPosX, globalPosY);
		}
	}
	
	public void asignSnakesAndLatters(BoardPosLinkedList firstLinked) {
		asignToRow(first, firstLinked);
	}

	private void asignToRow(MatrixNode current, BoardPosLinkedList firstLinked) {
		if(current != null) {
			asignToCol(current, firstLinked);
			asignToRow(current.getUp(), firstLinked);
		}
	}

	private void asignToCol(MatrixNode current, BoardPosLinkedList firstLinked) {
		if(current != null) {
		
			asignToCol(current.getNext(), firstLinked);
		}
	}
	
	
	/*private void drawCol2(PApplet app, MatrixNode firstCol) {
		if(firstCol != null) {
			drawRow2(app, firstCol);
			drawCol2(app, firstCol.getNext());
		}
		
	}
	
	private void drawRow2(PApplet app, MatrixNode current) {
		if(current != null) {
			current.drawBox(app);
			drawRow2(app, current.getUp());
		}
	}*/
}
