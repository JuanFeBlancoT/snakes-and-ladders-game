package ui;

import model.GameController;
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
	private GameController gameController;
	
	public Menu(int w, int h) {
		
		screen = 1;
		width = w;
		height = h;
		
		gameController = new GameController();
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
			
			gameController.getBoard().drawBoard(app);
			app.fill(20);
			app.noStroke();
			app.rect(0, 780, 1200, 120);
			//die button
			app.fill(220);
			app.rect(1100, 805, 70, 70);
			app.fill(40);
			app.text("Throw \n dice", 1115, 835);
			app.stroke(40);
			
			break;
		case 4:
			app.textSize(50);
			app.fill(255);
			app.text("Congratulations", 400, 400);
			app.textSize(20);
			app.text(gameController.getActualWinner().toString(), 20, 500);
			
			app.fill(220);
			app.rect(400, 780, 400, 80);
			app.fill(40);
			app.text("Back to menu", (width/2)-60, 825);
			break;
		case 5:
			gameController.drawWinners(app);
			app.textSize(30);
			app.fill(0);
			app.rect(0, 0, 1200, 80);
			app.fill(255);
			app.text("Winners", 550, 50);
			
			break;
		}
	}

	public int getScreen() {
		return screen;
	}

	public void setScreen(int screen) {
		this.screen = screen;
	}

	public GameController getGameController() {
		return gameController;
	}
	
	
}
