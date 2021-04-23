package ui;

import model.Board;
import processing.core.PApplet;

public class Menu {

	private Board board;
	
	public Menu() {
		
		board = new Board(3, 3);
		board.createMatrix();
	}
	
	public void drawScene(PApplet app) {
		board.drawTable(app);	
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
	
	
}
