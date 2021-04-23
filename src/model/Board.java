package model;

import processing.core.PApplet;

public class Board {

	public final static int BOXSIZE = 80;
	private MatrixNode first;
	private int rows,cols;
	private int givenPX, givenPY, givenNum;
	
	public Board(int n, int m) {
		rows = n;
		cols = m;
		givenPX = 0;
		givenPY = 240;
		givenNum = 1;
		
	}
	
	public void createMatrix() {
		first = new MatrixNode(0, 0, givenPX, givenPY, givenNum, BOXSIZE);
		//System.out.println(givenNum);
		createRow(0,0, givenPX, givenPY, first, first.getDown());
	}

	private void createRow(int i, int j, int px, int py, MatrixNode firstRow, MatrixNode prevBelow) {
		//System.out.println("PRE----nueva row: "+i+","+j+": "+givenNum);
		createCol(i, j+1, givenPX+BOXSIZE, py, firstRow, prevBelow);
		if(i+1<rows) {

			givenNum+=cols;
			MatrixNode upNode = new MatrixNode(i+1, j, px, py-BOXSIZE, givenNum, BOXSIZE);
			upNode.setDown(firstRow);
			firstRow.setUp(upNode);
			createRow(i+1, j, px, py-BOXSIZE, upNode, firstRow);
		}
	}
	
	private void createCol(int i, int j, int px, int py, MatrixNode prev, MatrixNode below) {
		//System.out.println("PRE----nueva col: "+i+","+j+": "+givenNum);
		//System.out.println(py);
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
			
			//System.out.println("POS----nueva col: "+i+","+j+": "+givenNum);
			createCol(i, j+1, px+BOXSIZE, py, current, below);
		}
	}
	
	public void drawTable(PApplet app) {
		drawRow(app, first);
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
	
	
	private void drawRow(PApplet app, MatrixNode firstRow) {
		if(firstRow != null) {
			drawCol(app, firstRow);
			drawRow(app, firstRow.getUp());
		}
	}

	private void drawCol(PApplet app, MatrixNode current) {
		 if(current != null) {
			 current.drawBox(app);
			 drawCol(app, current.getNext());
		 }
	}

	public void moveTable(int globalPosX, int globalPosY) {
		updateRowPositions(first, globalPosX,globalPosY);
	}

	private void updateRowPositions(MatrixNode firstInRow, int globalPosX, int globalPosY) {
		if(firstInRow != null) {
			updateColPositoins(firstInRow, globalPosX, globalPosY);
			updateRowPositions(firstInRow.getUp(), globalPosX, globalPosY);
		}
	}

	private void updateColPositoins(MatrixNode current, int globalPosX, int globalPosY) {
		if(current != null) {
			current.setGlobalPX(globalPosX);
			current.setGlobalPY(globalPosY);
			updateColPositoins(current.getNext(), globalPosX, globalPosY);
		}
		
	}
	
	
}
