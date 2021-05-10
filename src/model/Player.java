package model;

import processing.core.PApplet;

public class Player {
	
	//atributtes
	private int globalPosX, globalPosY;
	private int posX, posY;				//X and Y coordinates of the player
	private int row, col;				//row and col in which the player is located within the matrix
	private int score;					//score of the player: Calculated by multiplying the amount of movements it took the player to win times the sixe of the board (n*m)
	private int movs;					//Amount of moves the player does in a match
	private int size;					//diameter of the player ball
	private String symbol;				//symbol that identifies the player during the match
	private String nickName;			//Nickname of the player, required for the winners table (menu option 2)
	private int turn, currentPos;		//Current position on the board and its corresponding turn
	
	//board information
	private int gameRows;
	private int gameCols;
	private int gameSnakes;
	private int gameLadders;
	private int playersInMatch;
	
	//linked list
	private Player next;
	private Player prev;
	//binary tree
	private Player left;
	private Player right;
	private Player parent;
	
	/**
	* Player: Constructor of PLayer class <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param row Row position in which the player is going to be created within the matrix.
	* @param col Column position in which the player is going to be created within the matrix.
	* @param px Position on the X axis of the player. Used for painting it.
	* @param py Position on the Y axis of the player. Used for painting it.
	* @param s Player symbol used for identify it
	* @param t Assigned turn for playing
	* @param mov Amount of turns that the player has used
	* @param gr Number of rows that the board will have.
	* @param gc Number of columns that the board will have.
	* @param pm Number of players that the board will have.
	* @param snks Number of snakes that the board will have.
	* @param lads Number of ladders that the board will have.
	*/
	public Player(int row, int col, int px, int py, String s, int t, int mov, int gr, int gc, int pm, int snks, int lads) {
		gameRows = gr;
		gameCols = gc;
		playersInMatch = pm;
		gameSnakes = snks;
		gameLadders = lads;		
		
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
	
	/**
	* drawPlayer: Renders the visual representation of a player <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param app Reference to the PApplet library
	*/
	public void drawPlayer(PApplet app) {
		app.noStroke();
		app.fill(255);
		app.circle(posX+globalPosX, posY+globalPosY, size);
		app.textSize(14);
		app.fill(0);
		app.text(symbol, (posX+globalPosX)-5, posY+globalPosY+5);
		app.stroke(80);
	}
	
	/**
	* getParent: gets a parent Player object <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return parent Player object
	*/
	public Player getParent() {
		return parent;
	}
	
	/**
	* drawPlayer: Renders the visual representation of a player <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param parent Player object parent of this node
	*/
	public void setParent(Player parent) {
		this.parent = parent;
	}

	/**
	* getScore: gets the integer score of a player <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return score Integer that represents the score of the player
	*/
	public int getScore() {
		return score;
	}

	/**
	* setScore: Sets the score of a player <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param score Integer that represents the score of the player
	*/
	public void setScore(int score) {
		this.score = score;
	}

	/**
	* getNickname: gets the nickname of the player <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return nickName String with the nickname of the player
	*/
	public String getNickName() {
		return nickName;
	}

	/**
	* setNickName: Sets the nickname of the player <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param nickName String that represents the player nickname
	*/
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/**
	* getLeft: gets the Player object associated to the left branch of a binary tree<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return left Left Player object
	*/
	public Player getLeft() {
		return left;
	}

	/**
	* setLeft: Sets the left Player <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param left The left Player
	*/
	public void setLeft(Player left) {
		this.left = left;
	}

	/**
	* getRight: gets the Player object associated to the left branch of a binary tree<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return right Right Player object
	*/
	public Player getRight() {
		return right;
	}

	/**
	* setRight: Sets the right Player <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param right The right Player
	*/
	public void setRight(Player right) {
		this.right = right;
	}

	/**
	* getMovs: gets the amount of turns played by the player <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return movs Integer number for the amount of turns played
	*/
	public int getMovs() {
		return movs;
	}

	/**
	* setMovs: Sets the amount of turns played by a Player <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param movs Turns played by a player
	*/
	public void setMovs(int movs) {
		this.movs = movs;
	}

	/**
	* getPosX: gets the horizontal position of the player <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return posX Horizontal position of the player
	*/
	public int getPosX() {
		return posX;
	}

	/**
	* setPosX: Sets the horizontal position of the player <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param posX new Horizontal position
	*/
	public void setPosX(int posX) {
		this.posX = posX;
	}

	/**
	* getPosY: gets the vertical position of the player <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return posY Vertical position of the player
	*/
	public int getPosY() {
		return posY;
	}

	/**
	* setPosY: Sets the horizontal position of the player <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param posY new vertical position
	*/
	public void setPosY(int posY) {
		this.posY = posY;
	}

	/**
	* getCurrentPos: Get the current position of the player <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return currentPos Current position of the player
	*/
	public int getCurrentPos() {
		return currentPos;
	}

	/**
	* setCurrentPos: Sets the current position of the player <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param currentPos Current position of the player
	*/
	public void setCurrentPos(int currentPos) {
		this.currentPos = currentPos;
	}

	/**
	* getTurn: Get the turn number of the player <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return turn Turn of the player
	*/
	public int getTurn() {
		return turn;
	}

	/**
	* setGlobalPosX: Sets the global horizontal position <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param globalPosX New global horizontal position
	*/
	public void setGlobalPosX(int globalPosX) {
		this.globalPosX = globalPosX;
	}

	/**
	* setGlobalPosY: Sets the global vertical position <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param globalPosY New global vertical position
	*/
	public void setGlobalPosY(int globalPosY) {
		this.globalPosY = globalPosY;
	}

	/**
	* getNext: Get the next Player object in the linked list<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return next Next player
	*/
	public Player getNext() {
		return next;
	}

	/**
	* setNext: Sets the next player object in the linked list<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param next Next player
	*/
	public void setNext(Player next) {
		this.next = next;
	}

	/**
	* getPrev: Get the previous Player object in the linked list<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return next Next player
	*/
	public Player getPrev() {
		return prev;
	}

	/**
	* setPrev: Sets the previous player object in the linked list<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param prev Next player
	*/
	public void setPrev(Player prev) {
		this.prev = prev;
	}

	/**
	* getRow: Gets the row position<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return row The row
	*/
	public int getRow() {
		return row;
	}

	/**
	* setRow: Sets the row of the player<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param row New row
	*/
	public void setRow(int row) {
		this.row = row;
	}

	/**
	* getCol: Gets the column position<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return row The column
	*/
	public int getCol() {
		return col;
	}

	/**
	* setCol: Sets the column of the player<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param col new Column of the player
	*/
	public void setCol(int col) {
		this.col = col;
	}

	/**
	* getSymbol: Gets the player symbol<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return symbol The player symbol
	*/
	public String getSymbol() {
		return symbol;
	}

	/**
	* toString: Gets the string with all the important information of a player<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return symbol The player symbol
	*/
	public String toString() {
		return "Symbol: "+symbol+". Nickname: "+nickName+". Score: "+score+".\nRows in board: "+gameRows+". Columns in board: "+gameCols+". Snakes in game: "+gameSnakes+". Ladders in game: "+gameLadders+". Players in game: "+playersInMatch;
	}
}
