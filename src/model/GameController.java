package model;

import java.util.Random;

import javax.swing.text.Position.Bias;

public class GameController {

	//constants
	public final static int SNAKE_ENTRANCE = 1;
	public final static int SNAKE_EXIT = 2;
	public final static int LADDER_ENTRANCE = 3;
	public final static int LADDER_EXIT = 4;
	
	//attributes
	private int takenSpots;
	
	//relations
	private Board board;
	private BoardPosLinkedList head;
	private Random rndm;
	
	public GameController() {
		
		takenSpots = 0;
		board = new Board();
		rndm = new Random();
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
	
	public void asignSnakesAndLadders() {
		
	}
	
	
	private void createBoardPositionLinkedList(BoardPosLinkedList current, int currentNum, int endNum) {
		if(currentNum<=endNum) {
			BoardPosLinkedList nextNode = new BoardPosLinkedList(currentNum);
			current.setNext(nextNode);
			createBoardPositionLinkedList(nextNode, currentNum+1, endNum);
		}
	}
	
	public void createSnakePositions(int currentNumSnakes, int numSnakes, int n, int m) {

		
		if(currentNumSnakes < numSnakes) {

			//selects a random entrance which can start from the first box in the second row to (n*m)-1
			int posEntrance = rndm.nextInt((n*m)-(m+1))+(m+1);
			BoardPosLinkedList posNodeEntrance = searchBoardPosition(posEntrance);
			
			if(!posNodeEntrance.isTaken()) {
				
				//if the node in the position of the random entrance is not taken yet, creates a random exit which can go from 1 to the number before the entrance
				int posExit = rndm.nextInt(posEntrance-(posEntrance%m))+1;
				BoardPosLinkedList posNodeExit = searchBoardPosition(posExit);
				
				if(!posNodeExit.isTaken()) {
					
					System.out.println(posEntrance);
					System.out.println(posExit);
					
					takenSpots+=2;
					posNodeEntrance.setType(SNAKE_ENTRANCE);
					posNodeEntrance.setTaken(true);
					posNodeEntrance.setPairNum(posExit);
					//
					posNodeExit.setType(SNAKE_EXIT);
					posNodeExit.setTaken(true);
					currentNumSnakes++;
					System.out.println("Done"+"\n");
				}
			}
			createSnakePositions(currentNumSnakes, numSnakes, n, m);
		}
	}
	
	public void createLaddersPoistions(int currentLadders, int numLadders, int n, int m) {
		
		if(currentLadders < numLadders) {
			
			//select a random ladder entrance that can go from 2 to (n*m)-m
			int posEntrance = rndm.nextInt((n*m)-(m+1))+2;
			BoardPosLinkedList posNodeEntrance = searchBoardPosition(posEntrance);
			
			if(!posNodeEntrance.isTaken()) {
				
				//if the node in the position of the random entrance is not taken yet, creates a random exit which can go from (m+1) to n*m
				int posExit = rndm.nextInt((n*m)-(m))+(m+1);
				BoardPosLinkedList posNodeExit = searchBoardPosition(posExit);
				
				if(!posNodeExit.isTaken()) {
					
					System.out.println(posEntrance);
					System.out.println(posExit);
					
					takenSpots+=2;
					posNodeEntrance.setType(LADDER_ENTRANCE);
					posNodeEntrance.setTaken(true);
					posNodeEntrance.setPairNum(posExit);
					//
					posNodeExit.setType(LADDER_EXIT);
					posNodeExit.setTaken(true);
					currentLadders++;
					System.out.println("Done"+"\n");
				}
			}
			createLaddersPoistions(currentLadders, numLadders, n, m);
		}
	}

	private BoardPosLinkedList searchBoardPosition(int posEntrance) {
		BoardPosLinkedList searched;
		searched = searchBoardPosition(head, posEntrance);
		
		return searched;
		
	}

	private BoardPosLinkedList searchBoardPosition(BoardPosLinkedList current, int posEntrance) {
		BoardPosLinkedList searched;
		if(current.getNum() == posEntrance) {
			searched = current;
		}else {
			searched = searchBoardPosition(current.getNext(), posEntrance);
		}
		return searched;
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
