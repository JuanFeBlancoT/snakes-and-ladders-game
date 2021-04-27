package model;

import processing.core.PApplet;
import java.util.Random;

public class Board {
	
	//constants
	public final static int BOXSIZE = 80;
	//constants
	public final static int SNAKE_ENTRANCE = 1;
	public final static int SNAKE_EXIT = 2;
	public final static int LADDER_ENTRANCE = 3;
	public final static int LADDER_EXIT = 4;
	
	//attributes
	private int rows,cols;
	private int givenPX, givenPY, givenNum;
	//
	private int availableLadderEntrances;
	private int availabeLadderExits;
	private int availabaleSnakesEntrances;
	private int availableSnakesExits;
	private int attempts;
	
	private char snakesAlphabeticEnum;
	private int laddersEnumeration;
	
	//relations	
	private MatrixNode first;
	private BoardPosLinkedList head;
	private Random rndm;
	
	public Board() {
		rows = 0;
		cols = 0;
		givenPX = 0;
		givenPY = 240;
		givenNum = 1;
		attempts = 0;
		snakesAlphabeticEnum = 'A';
		laddersEnumeration = 1;
		
		rndm = new Random();
	}
	
	/**
	* createMatrix: It creates a double linked matrix with a given amount of rows and columns <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param n Number of rows that the board will have.
	* @param m Number of columns that the board will have.
	*/
	public void createMatrix(int n, int m) {
		createBoardPositionLinkedList(n, m);
		rows = n;
		cols = m;
		first = new MatrixNode(0, 0, givenPX, givenPY, givenNum, BOXSIZE);
		createRow(0,0, givenPX, givenPY, first, first.getDown());
	}

	/**
	* createRow: It creates a new row in the double linked matrix by using recursion <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param i Row position.
	* @param j Column position.
	* @param px The X coordinates of the node that would be used for showing it.
	* @param py The Y coordinates of the node that would be used for showing it.
	* @param firstRow Is the first node in a row.
	* @param prevBelow Is the node below the firstRow node, used for linking vertically.
	*/
	private void createRow(int i, int j, int px, int py, MatrixNode firstRow, MatrixNode prevBelow) {
		createCol(i, j+1, givenPX+BOXSIZE, py, firstRow, prevBelow);
		if(i+1<rows) {

			givenNum+=cols;
			MatrixNode upNode = new MatrixNode(i+1, j, px, py-BOXSIZE, givenNum, BOXSIZE);
			upNode.setDown(firstRow);
			firstRow.setUp(upNode);
			createRow(i+1, j, px, py-BOXSIZE, upNode, firstRow);
		}
	}
	
	/**
	* createCol: It creates a new node in a column from a double linked matrix by using recursion <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param i Row position.
	* @param j Column position.
	* @param px The X coordinates of the node that would be used for showing it.
	* @param py The Y coordinates of the node that would be used for showing it.
	* @param prev Is the previous node in a row, used for linking and recursion.
	* @param below Is the node below the previous node, used for linking vertically.
	*/
	private void createCol(int i, int j, int px, int py, MatrixNode prev, MatrixNode below) {
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
			createCol(i, j+1, px+BOXSIZE, py, current, below);
		}
	}
	
	/**
	* drawBoard: It shows the board in the UI with recursion <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param app Is the reference to the PApplet library.
	*/
	public void drawBoard(PApplet app) {
		drawRow(app, first);
	}
	
	/**
	* drawRow: It renders a row from the double linked matrix <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param app Is the reference to the PApplet library.
	* @param firstRow Is the first node from a row in the matrix.
	*/
	private void drawRow(PApplet app, MatrixNode firstRow) {
		if(firstRow != null) {
			drawCol(app, firstRow);
			drawRow(app, firstRow.getUp());
		}
	}

	/**
	* drawCol: It renders a column from the double linked matrix <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param app Is the reference to the PApplet library.
	* @param current Is the current node that is being rendered.
	*/
	private void drawCol(PApplet app, MatrixNode current) {
		 if(current != null) {
			 current.drawBox(app);
			 drawCol(app, current.getNext());
		 }
	}

	/**
	* moveTable: It changes the horizontal and vertical position of all nodes in a double linked matrix using recursion <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param globalPosX Is an integer that is added to the node horizontal position.
	* @param globalPosY Is an integer that is added to the node vertical position.
	*/
	public void moveTable(int globalPosX, int globalPosY) {
		updateRowPositions(first, globalPosX,globalPosY);
	}

	/**
	* updateRowPosition: It changes the horizontal and vertical position of all nodes in a row <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param firstInRow Is the first node in a matrix row.
	* @param globalPosX Is an integer that is added to the node horizontal position.
	* @param globalPosY Is an integer that is added to the node vertical position.
	*/
	private void updateRowPositions(MatrixNode firstInRow, int globalPosX, int globalPosY) {
		if(firstInRow != null) {
			updateColPositoins(firstInRow, globalPosX, globalPosY);
			updateRowPositions(firstInRow.getUp(), globalPosX, globalPosY);
		}
	}

	/**
	* updateColPosition: It changes the horizontal and vertical position of all nodes in a row <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param current Is the current node that is being updated.
	* @param globalPosX Is an integer that is added to the node horizontal position.
	* @param globalPosY Is an integer that is added to the node vertical position.
	*/
	private void updateColPositoins(MatrixNode current, int globalPosX, int globalPosY) {
		if(current != null) {
			current.setGlobalPX(globalPosX);
			current.setGlobalPY(globalPosY);
			updateColPositoins(current.getNext(), globalPosX, globalPosY);
		}
	}

	public void asignSnakesAndLadders() {
		asignToRow(first, head);
	}

	private void asignToRow(MatrixNode current, BoardPosLinkedList firstLinked) {
		if(current != null) {
			asignToCol(current, firstLinked);
			asignToRow(current.getUp(), firstLinked);
		}
	}

	private void asignToCol(MatrixNode current, BoardPosLinkedList firstLinked) {
		if(current != null) {
			int numberBox = current.getBoxNumber();
			BoardPosLinkedList currentPosLinked = searchBoardPosition(numberBox);
			
			if(currentPosLinked.getType() == SNAKE_ENTRANCE) {
				System.out.println("SNAKE");
				current.setIdentifier(snakesAlphabeticEnum);
				current.setR(245);
				current.setG(102);
				current.setB(88);
				MatrixNode pair = searchMatrixNode(currentPosLinked.getPairNum());
				pair.setIdentifier(snakesAlphabeticEnum);
				pair.setR(245);
				pair.setG(157);
				pair.setB(105);	
				snakesAlphabeticEnum++;
			}else if(currentPosLinked.getType() == SNAKE_EXIT) {
				
			}else if(currentPosLinked.getType() == LADDER_ENTRANCE) {
				System.out.println("LADDER");
				current.setNumericIdentifier(laddersEnumeration);
				current.setR(99);
				current.setG(115);
				current.setB(250);
				MatrixNode pair = searchMatrixNode(currentPosLinked.getPairNum());
				pair.setNumericIdentifier(laddersEnumeration);
				pair.setR(127);
				pair.setG(208);
				pair.setB(250);	
				laddersEnumeration++;
			}else if(currentPosLinked.getType() == LADDER_EXIT) {
				
			}else {
				
			}
			
			
			asignToCol(current.getNext(), firstLinked);
		}
	}
	
	public MatrixNode searchMatrixNode(int numberBox) {
		MatrixNode searched;
		searched = searchRow(first, numberBox);
		return searched;
	}
	
	private MatrixNode searchRow(MatrixNode current, int numberBox) {
		MatrixNode searched = null;
		if(current != null) {
			searched = searchCol(current, numberBox);
			if(searched == null) {
				searched = searchRow(current.getUp(), numberBox);
			}
		}
		return searched;
	}

	private MatrixNode searchCol(MatrixNode current, int numberBox) {
		MatrixNode searched = null;
		if(current != null) {
			 if(current.getBoxNumber() == numberBox) {
				 searched = current;
			 }
			 if(searched == null) {
				 searched = searchCol(current.getNext(), numberBox);
			 }
		}
		return searched;
	}

	public void createBoardPositionLinkedList(int n, int m) {
		availabaleSnakesEntrances = (n*m)-(m+1);
		availableSnakesExits = availabaleSnakesEntrances+1;
		availableLadderEntrances = (n*m)-(m+1);
		availabeLadderExits = availableLadderEntrances+1;
		
		int num = n*m;
		head = new BoardPosLinkedList(1);
		System.out.println(1);
		createBoardPositionLinkedList(head,2,num);
	
	}
	
	
	private void createBoardPositionLinkedList(BoardPosLinkedList current, int currentNum, int endNum) {
		if(currentNum<=endNum) {
			System.out.println(currentNum);
			BoardPosLinkedList nextNode = new BoardPosLinkedList(currentNum);
			current.setNext(nextNode);
			createBoardPositionLinkedList(nextNode, currentNum+1, endNum);
		}
	}
	
	public void createSpecialCells(int requiredSnakes, int requiredLadders, int n, int m) {
		attempts = 0;
		createBoardPositionLinkedList(n,m);
		//System.out.println("Entrada Serp: "+availabaleSnakesEntrances+". Salida serp: "+availableSnakesExits+". Entrada esca: "+availableLadderEntrances+". Salida esca: "+availabeLadderExits);
		createSnakePositions(0, requiredSnakes, n, m, requiredLadders);
		createLaddersPoistions(0, requiredLadders, n, m, requiredSnakes);
		
	}
	
	public void createSnakePositions(int currentNumSnakes, int numSnakes, int n, int m, int numLadders) {

		
		if(currentNumSnakes < numSnakes && attempts < 100 ) {

			//selects a random entrance which can start from the first box in the second row to (n*m)-1
			int posEntrance = rndm.nextInt((n*m)-(m+1))+(m+1);			
			BoardPosLinkedList posNodeEntrance = searchBoardPosition(posEntrance);
			
			if(!posNodeEntrance.isTaken()) {
				
				//if the node in the position of the random entrance is not taken yet, creates a random exit which can go from 1 to the number before the entrance
				int posExit;
				if(n == 2 && m == 2) {
					posExit = 1;
				}else {
					if(posEntrance%m == 0) {
						posExit = rndm.nextInt(posEntrance-(m+1))+1;
					}else {
						posExit = rndm.nextInt(posEntrance-(posEntrance%m))+1;
					}
				}

				BoardPosLinkedList posNodeExit = searchBoardPosition(posExit);
				
				if(!posNodeExit.isTaken()) {
					attempts++;
					System.out.println("Snakes attempts:"+attempts);
					
					System.out.println(posEntrance);
					System.out.println(posExit);
					
					posNodeEntrance.setType(SNAKE_ENTRANCE);
					posNodeEntrance.setTaken(true);
					posNodeEntrance.setPairNum(posExit);
					//
					posNodeExit.setType(SNAKE_EXIT);
					posNodeExit.setTaken(true);
					currentNumSnakes++;
					System.out.println("Done snake"+"\n");
				}
			}
			if(attempts<100) {
				createSnakePositions(currentNumSnakes, numSnakes, n, m, numLadders);
			}else {
				attempts = 0;
				createSpecialCells(numSnakes, numLadders, n, m);
			}
			
		}
		
	}
	
	public void createLaddersPoistions(int currentLadders, int numLadders, int n, int m, int numSnakes) {
		
		if(currentLadders < numLadders  && attempts < 100) {
			
			//select a random ladder entrance that can go from 2 to (n*m)-m
			int posEntrance;
			if(n == 2 && m==2) {
				posEntrance = 2;
			}else {
				posEntrance = rndm.nextInt((n*m)-(m+2))+2;
				attempts++;
			}
		
			BoardPosLinkedList posNodeEntrance = searchBoardPosition(posEntrance);
			
			if(!posNodeEntrance.isTaken()) {
				//if the node in the position of the random entrance is not taken yet, creates a random exit which can go from the entrance to n*m
				int posExit;
				if(n == 2 && m==2) {
					posExit= n*m;;
				}else {
					
					int reducedEntrance = reduceEntranceNum(posEntrance, m);
					System.out.println("REDUCED E: "+reducedEntrance);
					int minExitPos = (m-reducedEntrance)+1;
					int posExitMin = minExitPos + posEntrance;
					System.out.println("mini: "+minExitPos+".....posEn: "+posEntrance+"...posExitMin: "+posExitMin);
					
					posExit = rndm.nextInt((n*m)-posExitMin)+posExitMin;
					attempts++;
				}
				
				BoardPosLinkedList posNodeExit = searchBoardPosition(posExit);
				
				if(!posNodeExit.isTaken()) {
					
					System.out.println("Ladder attempts:"+attempts);
					System.out.println(posEntrance);
					System.out.println(posExit);
					
					posNodeEntrance.setType(LADDER_ENTRANCE);
					posNodeEntrance.setTaken(true);
					posNodeEntrance.setPairNum(posExit);
					//
					posNodeExit.setType(LADDER_EXIT);
					posNodeExit.setTaken(true);
					currentLadders++;
					System.out.println("Done Ladder "+currentLadders+"\n");
				}
			}
			if(attempts<100) {
				createLaddersPoistions(currentLadders, numLadders, n, m, numSnakes);
			}else {
				attempts = 0;
				createSpecialCells(numSnakes, numLadders, n, m);
			}
		}
	}

	private int reduceEntranceNum(int entranceNum, int m) {
		if(entranceNum<=m) {
			return entranceNum;
		}else {
			return reduceEntranceNum(entranceNum-m, m);
		}

	}

	private BoardPosLinkedList searchBoardPosition(int posEntrance) {
		return searchBoardPosition(head, posEntrance);
	}

	private BoardPosLinkedList searchBoardPosition(BoardPosLinkedList current, int posEntrance) {
		
		if(current.getNum() == posEntrance) {
			return current;
		}else {
			return searchBoardPosition(current.getNext(), posEntrance);
		}
		
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
}
