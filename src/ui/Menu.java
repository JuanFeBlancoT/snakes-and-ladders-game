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
	
	private boolean selectedNickNameInput;
	private boolean selecRows;
	private boolean selectCols;
	private boolean selectSnakes;
	private boolean selectLadders;
	private boolean selectPlayers;
	
	private String genericString;
	private String nickNameText;
	private String rows;
	private String cols;
	private String snakes;
	private String ladders;
	private String players;

	//relations
	private GameController gameController;
	
	public Menu(int w, int h) {
	
		genericString = "";
		nickNameText = "";
		rows = "";
		cols = "";
		snakes = "";
		ladders = "";
		players = "";
		
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
	* @throws InterruptedException Not sure
	*/
	public void drawScene(PApplet app) throws InterruptedException {
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
			app.textSize(30);
			app.text(NEW_GAME_BTN_TEXT, (width/2)-115, (height/2)+25);
			//Rows in board
			if(!selecRows) {
				app.fill(220);
			}else {
				app.fill(204,244,228);
			}
			app.rect(150, 200, 100, 100);
			app.textSize(15);
			app.text("rows in\nboard", 150, 170);
			app.fill(67,107,86);
			app.text(rows, 160, 255);
			//Columns in board
			if(!selectCols) {
				app.fill(220);
			}else {
				app.fill(204,244,228);
			}
			app.rect(350, 200, 100, 100);
			app.textSize(15);
			app.text("Columns in\nboard", 350, 170);
			app.fill(67,107,86);
			app.text(cols, 360, 255);
			//Snakes in board
			if(!selectSnakes) {
				app.fill(220);
			}else {
				app.fill(204,244,228);
			}
			app.rect(550, 200, 100, 100);
			app.textSize(15);
			app.text("Snakes in\nboard", 550, 170);
			app.fill(67,107,86);
			app.text(snakes, 560, 255);
			//Ladders in board
			if(!selectLadders) {
				app.fill(220);
			}else {
				app.fill(204,244,228);
			}
			app.rect(750, 200, 100, 100);
			app.textSize(15);
			app.text("Ladders in\nboard", 750, 170);
			app.fill(67,107,86);
			app.text(ladders, 760, 255);
			//Players in board
			if(!selectPlayers) {
				app.fill(220);
			}else {
				app.fill(204,244,228);
			}
			app.rect(950, 200, 100, 100);
			app.textSize(15);
			app.text("Players in\nboard", 950, 170);
			app.fill(67,107,86);
			app.text(players, 960, 255);
			//
			app.fill(245);
			app.text("The number of columns and rows must be greater than 1 each", 370, 600);
			app.text("The number of snakes and columns must be lower than the half of rows*columns", 310, 620);
			app.text("The number of players must be from 2 to 9", 440, 640);
			
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
			//simul button
			app.fill(220);
			app.rect(950, 805, 100, 70);
			app.fill(40);
			app.text("Simulation", 960, 845);
			app.stroke(40);
			//back to menu button
			app.fill(220);
			app.rect(50, 805, 200, 70);
			app.fill(40);
			app.text("Back to menu", 100, 845);
			app.stroke(40);
			
			app.fill(255);
			app.text(gameController.getPlayerSymbol()+ " moves "+ gameController.getDiceNum()+ " positions", 750, 850);
			break;
		case 4:
			app.textSize(50);
			app.fill(255);
			app.text("Congratulations "+ gameController.getBoard().getWinner().getSymbol(), 400, 200);
			app.textSize(20);
			if(gameController.getBoard().getWinner().getMovs() == 1) {
				app.text("It took you "+ gameController.getBoard().getWinner().getMovs() + " turn to win", 490, 250);
			}else {
				app.text("It took you "+ gameController.getBoard().getWinner().getMovs() + " turns to win", 490, 250);
			}
			app.fill(220);
			app.rect(400, 580, 400, 80);
			app.fill(40);
			app.text("Back to menu", (width/2)-60, 625);
			//textfield
			app.textSize(20);		
			if(!selectedNickNameInput) {
				app.fill(220);
			}else {
				app.fill(204,244,228);
			}
			app.rect(300, 380, 600, 60);
			app.text("Enter the winners nickname", 470, 340);
			app.fill(67,107,86);
			app.text(nickNameText, 310, 420);
			break;
		case 5:
			gameController.drawWinners(app);
			app.textSize(30);
			app.fill(0);
			app.rect(0, 0, 1200, 80);
			app.fill(255);
			app.text("Winners", 550, 50);
			
			app.fill(0);
			app.rect(0, 780, 1200, 120);
			//back to menu button
			app.fill(220);
			app.rect(50, 805, 200, 70);
			app.fill(40);
			app.textSize(15);
			app.text("Back to menu", 100, 845);
			app.stroke(40);
			app.fill(255);
			
			break;
		case 6:
			gameController.getBoard().drawBoard(app);
			app.fill(20);
			app.noStroke();
			app.rect(0, 780, 1200, 120);
			//die button
			app.fill(220);
			app.rect(1100, 805, 70, 70);
			app.fill(40);
			app.text("Throw \n dice", 1115, 835);
			//simul button
			app.fill(220);
			app.rect(950, 805, 100, 70);
			app.fill(40);
			app.text("Simulation", 960, 845);
			app.stroke(40);
			
			app.fill(255);
			app.text(gameController.getPlayerSymbol()+ " moves "+ gameController.getDiceNum()+ " positions", 750, 850);
			//
			gameController.movePlayer(app);
			Thread.sleep(2000);
			Boolean gameWon =gameController.getBoard().isGameWon();
			if(gameWon) {
				screen = 4;
			}
			break;
		}
	}
	
	public void setWinner() {
		gameController.setWinner(nickNameText);
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

	public boolean isSelectedNickNameInput() {
		return selectedNickNameInput;
	}

	public void setSelectedNickNameInput(boolean selectedNickNameInput) {
		this.selectedNickNameInput = selectedNickNameInput;
	}

	public String getNickNameText() {
		return nickNameText;
	}

	public void setNickNameText(String nickNameText) {
		this.nickNameText = nickNameText;
	}
	
	public String getGenericString() {
		return genericString;
	}

	public void setGenericString(String genericString) {
		this.genericString = genericString;
	}

	public boolean isSelecRows() {
		return selecRows;
	}

	public void setSelecRows(boolean selecRows) {
		this.selecRows = selecRows;
	}

	public boolean isSelectCols() {
		return selectCols;
	}

	public void setSelectCols(boolean selectCols) {
		this.selectCols = selectCols;
	}

	public boolean isSelectSnakes() {
		return selectSnakes;
	}

	public void setSelectSnakes(boolean selectSnakes) {
		this.selectSnakes = selectSnakes;
	}

	public boolean isSelectLadders() {
		return selectLadders;
	}

	public void setSelectLadders(boolean selectLadders) {
		this.selectLadders = selectLadders;
	}

	public boolean isSelectPlayers() {
		return selectPlayers;
	}

	public void setSelectPlayers(boolean selectPlayers) {
		this.selectPlayers = selectPlayers;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getCols() {
		return cols;
	}

	public void setCols(String cols) {
		this.cols = cols;
	}

	public String getSnakes() {
		return snakes;
	}

	public void setSnakes(String snakes) {
		this.snakes = snakes;
	}

	public String getLadders() {
		return ladders;
	}

	public void setLadders(String ladders) {
		this.ladders = ladders;
	}

	public String getPlayers() {
		return players;
	}

	public void setPlayers(String players) {
		this.players = players;
	}

	public void startMatch(int n, int m, int s, int l, int p) {
		gameController.createBoard(n, m);
		gameController.getBoard().createSymbolslist();
		gameController.getBoard().createSpecialCells(s, l, n, m);
		gameController.getBoard().createInitialPlayers(p);
		gameController.getBoard().asignSnakesAndLadders();
	}
	
}
