package ui;

import model.Board;
import processing.core.PApplet;

public class Menu {
	
	public final static String NEW_GAME_BTN_TEXT = "Start new game";
	public final static String WINNERS_BTN_TEXT = "See score table";
	public final static String QUIT_BTN_TEXT = "Quit game";
	
	//attributes 
	private int screen;		//Controls which objects are going to be shown in the UI.
	private int width;		//Windows width
	private int height;		//Windows height
	
	//relations
	private Board board;
	
	public Menu(int w, int h) {
		
		screen = 1;
		width = w;
		height = h;
		
		board = new Board();
	}
	
	/**
	* drawScene: It renders the UI in the main window <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param app Is a reference to the PApplet library
	*/
	public void drawScene(PApplet app) {
		switch(screen) {
		case 1:
			//New game button
			app.textSize(35);
			app.fill(220);
			app.rect((width/2)-200, height/4, 400, 80);
			app.fill(40);
			app.text(NEW_GAME_BTN_TEXT, (width/2)-130, (height/4)+50);
			
			//see scores button
			app.fill(220);
			app.rect((width/2)-200, (height/2)-25, 400, 80);
			app.fill(40);
			app.text(WINNERS_BTN_TEXT, (width/2)-130, (height/2)+25);
			
			//quit game button
			app.fill(220);
			app.rect((width/2)-200, height-275, 400, 80);
			app.fill(40);
			app.text(QUIT_BTN_TEXT, (width/2)-90, height-225);
			break;
		case 2:
			//Start button
			app.fill(220);
			app.rect((width/2)-200, (height/2)-25, 400, 80);
			app.fill(40);
			app.text(NEW_GAME_BTN_TEXT, (width/2)-130, (height/2)+25);
			
			break;
		case 3:
			board.drawBoard(app);
			break;
		}
	}
	
	/**
	* createBoard: It creates a double linked matrix with a given amount of rows and columns from type Board <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param n Number of rows that the board will have.
	* @param m Number of columns that the board will have.
	*/
	public void createBoard(int n, int m) {
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

	public int getScreen() {
		return screen;
	}

	public void setScreen(int screen) {
		this.screen = screen;
	}
	
	
	
}
