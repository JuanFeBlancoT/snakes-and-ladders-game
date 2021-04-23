package ui;

import model.Board;
import processing.core.PApplet;

public class Menu {
	
	//attributes 
	private int screen;
	
	//relations
	private Board board;
	
	public Menu() {
		screen = 1;
		
		board = new Board();
		board.createMatrix(3,3);
	}
	
	/**
	* drawScene: It renders the UI in the main window <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param app Is a reference to the PApplet library
	*/
	public void drawScene(PApplet app) {
		board.drawBoard(app);	
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
	
}
