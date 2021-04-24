package model;

public class Player {
	
	//atributtes
	private int posX, posY;		//X and Y coordinates of the player
	private int row, col;		//row and col in which the player is located within the matrix
	private int score;			//score of the player: Calculated by multiplying the amount of movements it took the player to win times the sixe of the board (n*m)
	private int movs;			//Amount of moves the player does in a match
	private String symbol;		//symbol that identifies the player during the match
	private String nickName;	//Nickname of the player, required for the winners table (menu option 2)
	
}
