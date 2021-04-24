package model;

public class GameController {

	//attributes
	private int takenSpots;
	
	//relations
	private Board board;
	private BoardPosLinkedList head;
	
	public GameController() {
		
		takenSpots = 0;
		board = new Board();
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
	
	public void createBoardPositionLinkedList(int n) {
		head = new BoardPosLinkedList(1);
		createBoardPositionLinkedList(head,2,n);
	}
	
	private void createBoardPositionLinkedList(BoardPosLinkedList current, int currentNum, int endNum) {
		if(currentNum<=endNum) {
			BoardPosLinkedList nextNode = new BoardPosLinkedList(currentNum);
			current.setNext(nextNode);
			createBoardPositionLinkedList(nextNode, currentNum+1, endNum);
		}
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
