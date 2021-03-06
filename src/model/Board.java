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
	
	public final static String SYMBOL1 ="*";
	public final static String SYMBOL2 ="!";
	public final static String SYMBOL3 ="?";
	public final static String SYMBOL4 ="X";
	public final static String SYMBOL5 ="%";
	public final static String SYMBOL6 ="$";
	public final static String SYMBOL7 ="#";
	public final static String SYMBOL8 ="+";
	public final static String SYMBOL9 ="&";
	
	//attributes
	private int rows,cols;
	private int givenPX, givenPY, givenNum;
	private int attempts;
	
	private char snakesAlphabeticEnum;
	private int laddersEnumeration;
	private int snakesInGame;
	private int laddersInGame;
	private int numberPlayers;
	private int playerTurn;
	
	private boolean gameWon;
	
	//relations	
	private MatrixNode first;
	private BoardPosLinkedList head;
	private Random rndm;
	private Player winner;
	private SymbolinkedNode firstSymbol;
	
	/**
	* Board: Class constructor <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	*/
	public Board() {
		rows = 0;
		cols = 0;
		givenPX = 0;
		givenPY = 0;
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
		givenPY = (n-1)*80;
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
			updatePlayerPosition(current, globalPosX, globalPosY);
			updateColPositoins(current.getNext(), globalPosX, globalPosY);
		}
	}
	
	/**
	* updatePlayerPosition: It changes the horizontal and vertical position of all players in the board <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param current Is the current node that is being updated.
	* @param gpx Is an integer that is added to the player horizontal position.
	* @param gpy Is an integer that is added to the player vertical position.
	*/
	private void updatePlayerPosition(MatrixNode current, int gpx, int gpy) {
		if(current.getFirstPlayer() != null) {
			updatePlayerPosition(current.getFirstPlayer(), gpx, gpy);
		}
	}

	/**
	* updatePlayerPosition: It changes the horizontal and vertical position of all players in the board <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param currentPlayer Is the current player that is being updated.
	* @param gpx Is an integer that is added to the player horizontal position.
	* @param gpy Is an integer that is added to the player vertical position.
	*/
	private void updatePlayerPosition(Player currentPlayer, int gpx, int gpy) {
		if(currentPlayer != null) {
			currentPlayer.setGlobalPosX(gpx);
			currentPlayer.setGlobalPosY(gpy);
			updatePlayerPosition(currentPlayer.getNext(), gpx, gpy);
		}
	}

	/**
	* createSymbolslist: It creates the linked list of symbols <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	*/
	public void createSymbolslist() {
		int positionInList = 0;
		if(firstSymbol == null) {
			firstSymbol = new SymbolinkedNode(positionInList, SYMBOL1);
			
		}
		createSymbolslist(firstSymbol, positionInList+1 , 9);
	}
	
	/**
	* createSymbolslist: It creates the linked list of symbols <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param current Is the current symbol node in the linked list.
	* @param createf Is an integer that determines how many nodes has been created
	* @param positionInLinkedList Is an integer that is the position of the crated node within the list
	*/
	private void createSymbolslist(SymbolinkedNode current, int created, int positionInList) {
		SymbolinkedNode newSymbol = null;
		if(created < positionInList) {
			
			switch (created) {
			case 1:
				newSymbol = new SymbolinkedNode(created, SYMBOL2);
				break;
			case 2:
				newSymbol = new SymbolinkedNode(created, SYMBOL3);
				break;
			case 3:
				newSymbol = new SymbolinkedNode(created, SYMBOL4);
				break;
			case 4:
				newSymbol = new SymbolinkedNode(created, SYMBOL5);
				break;
			case 5:
				newSymbol = new SymbolinkedNode(created, SYMBOL6);
				break;
			case 6:
				newSymbol = new SymbolinkedNode(created, SYMBOL7);
				break;
			case 7:
				newSymbol = new SymbolinkedNode(created, SYMBOL8);
				break;
			case 8:
				newSymbol = new SymbolinkedNode(created, SYMBOL9);
				break;
			}
			current.setNext(newSymbol);
		}
		if(newSymbol != null) {
			createSymbolslist(current.getNext(), created+1, positionInList);
		}
	}

	/**
	* asignSnakesAndLadders: it creates the snakes and ladders of the board <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	*/
	public void asignSnakesAndLadders() {
		asignToRow(first, head);
	}

	/**
	* asignSnakesasignToRowAndLadders: it Assigns to each node its corresponding cell type and pair <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param current Is the current MatrixNode in the matrix.
	* @param firstLinked Is the current node in the BoardPositionLinkedList that would be assigned to the matrix node
	*/
	private void asignToRow(MatrixNode current, BoardPosLinkedList firstLinked) {
		if(current != null) {
			asignToCol(current, firstLinked);
			asignToRow(current.getUp(), firstLinked);
		}
	}

	/**
	* asignToCol: it Assigns its cells type and pair to a node recursively on a column <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param current Is the current MatrixNode in the matrix.
	* @param firstLinked Is the current node in the BoardPositionLinkedList that would be assigned to the matrix node
	*/
	private void asignToCol(MatrixNode current, BoardPosLinkedList firstLinked) {
		if(current != null) {
			int numberBox = current.getBoxNumber();
			BoardPosLinkedList currentPosLinked = searchBoardPosition(numberBox);
			
			if(currentPosLinked.getType() == SNAKE_ENTRANCE) {
			
				current.setIdentifier(snakesAlphabeticEnum);
				current.setR(245);
				current.setG(102);
				current.setB(88);
				MatrixNode pair = searchMatrixNode(currentPosLinked.getPairNum());
				current.setExitPair(pair);
				pair.setIdentifier(snakesAlphabeticEnum);
				pair.setR(245);
				pair.setG(157);
				pair.setB(105);	
				snakesAlphabeticEnum++;
			}else if(currentPosLinked.getType() == LADDER_ENTRANCE) {
				
				current.setNumericIdentifier(laddersEnumeration);
				current.setR(99);
				current.setG(115);
				current.setB(250);
				MatrixNode pair = searchMatrixNode(currentPosLinked.getPairNum());
				current.setExitPair(pair);
				pair.setNumericIdentifier(laddersEnumeration);
				pair.setR(127);
				pair.setG(208);
				pair.setB(250);	
				laddersEnumeration++;
			}			
			
			asignToCol(current.getNext(), firstLinked);
		}
	}
	
	/**
	* searchMatrixNode: Searches a MatrixNode by its number <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param numberBox Integer, the number of the searched cell
	* @return searched Is the searched MatrixNode
	*/
	public MatrixNode searchMatrixNode(int numberBox) {
		MatrixNode searched;
		searched = searchRow(first, numberBox);
		return searched;
	}
	
	/**
	* searchRow: Searches a MatrixNode by its number recursively by row<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param current Current MatrixNode
	* @param numberBox Integer that is the number of the node searched
	* @return searched Is the searched MatrixNode
	*/
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

	/**
	* searchCol: Searches a MatrixNode by its number recursively by column<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param current Current MatrixNode
	* @param numberBox Integer that is the number of the node searched
	* @return searched Is the searched MatrixNode
	*/
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

	/**
	* createBoardPositionLinkedList: Creates the board position linked list<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param n Integer, number of rows in board
	* @param m Integer, number of columns in board
	*/
	public void createBoardPositionLinkedList(int n, int m) {		
		int num = n*m;
		head = new BoardPosLinkedList(1);
		createBoardPositionLinkedList(head,2,num);
	}
	
	/**
	* createBoardPositionLinkedList: Creates the board position linked list<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param current BoardPosLinkedList, current node in list
	* @param currentNum Integer, nodes created
	* @param endNum Integer, nodes to create
	*/
	private void createBoardPositionLinkedList(BoardPosLinkedList current, int currentNum, int endNum) {
		if(currentNum<=endNum) {
			
			BoardPosLinkedList nextNode = new BoardPosLinkedList(currentNum);
			current.setNext(nextNode);
			createBoardPositionLinkedList(nextNode, currentNum+1, endNum);
		}
	}
	
	/**
	* createSpecialCells: Creates sankes and ladders positions<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param requiredSnakes Integer, snakes to create
	* @param requiredLadders Integer, ladders to create
	* @param n Integer, rows in board
	* @param m Integer, columns in board
	*/
	public void createSpecialCells(int requiredSnakes, int requiredLadders, int n, int m) {
		snakesInGame = requiredSnakes;
		laddersInGame = requiredLadders;
		attempts = 0;
		createBoardPositionLinkedList(n,m);
		createSnakePositions(0, requiredSnakes, n, m, requiredLadders);
		attempts = 0;
		createLaddersPoistions(0, requiredLadders, n, m, requiredSnakes);
		
	}
	
	/**
	* createSnakePositions: Creates the snakes positions, in and out cells <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param currentNumSnakes Integer, amount of snakes created so far
	* @param numSnakes Integer, amount of snakes that must be created
	* @param n Integer, number of rows in the board
	* @param m Integer, number of columns in the board
	* @param numLadders Integer, amount of ladders that must be created
	*/
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
					
					posNodeEntrance.setType(SNAKE_ENTRANCE);
					posNodeEntrance.setTaken(true);
					posNodeEntrance.setPairNum(posExit);
					//
					posNodeExit.setType(SNAKE_EXIT);
					posNodeExit.setTaken(true);
					currentNumSnakes++;
					
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
	
	/**
	* createLaddersPoistions: Creates the ladders positions, in and out cells <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param currentLadders Integer, amount of ladders created so far
	* @param numLadders Integer, amount of ladders that must be created
	* @param n Integer, number of rows in the board
	* @param m Integer, number of columns in the board
	* @param numSnakes Integer, amount of snakes that must be created
	*/
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
					
					int minExitPos = (m-reducedEntrance)+1;
					int posExitMin = minExitPos + posEntrance;
					
					posExit = rndm.nextInt((n*m)-posExitMin)+posExitMin;
					attempts++;
				}
				
				BoardPosLinkedList posNodeExit = searchBoardPosition(posExit);
				
				if(!posNodeExit.isTaken()) {
					
					posNodeEntrance.setType(LADDER_ENTRANCE);
					posNodeEntrance.setTaken(true);
					posNodeEntrance.setPairNum(posExit);
					//
					posNodeExit.setType(LADDER_EXIT);
					posNodeExit.setTaken(true);
					currentLadders++;
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

	/**
	* reduceEntranceNum: It takes a number and reduce it to a number between 0 and the amount of columns in the board <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param entranceNum Integer, the number that will be compared and reduced
	* @param m Integer, amount of columns in the board
	*/
	private int reduceEntranceNum(int entranceNum, int m) {
		if(entranceNum<=m) {
			return entranceNum;
		}else {
			return reduceEntranceNum(entranceNum-m, m);
		}

	}

	/**
	* searchBoardPosition: Search a BoardPositionNode<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param posEntrance Integer, the number to search the node
	* @return The searched BoardPosLinkedList object
	*/
	private BoardPosLinkedList searchBoardPosition(int posEntrance) {
		return searchBoardPosition(head, posEntrance);
	}

	/**
	* searchBoardPosition: Search a BoardPositionNode<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param current BoardPosLinkedList in list
	* @param posEntrance Integer, the number to search the node
	* @return The searched BoardPosLinkedList object
	*/
	private BoardPosLinkedList searchBoardPosition(BoardPosLinkedList current, int posEntrance) {
		
		if(current.getNum() == posEntrance) {
			return current;
		}else {
			return searchBoardPosition(current.getNext(), posEntrance);
		}
		
	}
	
	/**
	* createInitialPlayers: creates initial players <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param p Amount of players to create
	*/
	public void createInitialPlayers(int p) {
		numberPlayers = p;
		int currentP = 0;
		int px = 10;
		int py = ((rows-1)*80)+10;  
		
		//symbol selection		
		String symbolToAsign = asignSymbol();
		//	
		
		if(currentP < p) {
			Player player = new Player(first.getRow(), first.getCol(), px, py, symbolToAsign, currentP+1, 0, rows, cols, numberPlayers, snakesInGame, laddersInGame);
			first.setFirstPlayer(player);
			first.setPlayersInCell(first.getPlayersInCell()+1);
			createInitialPlayers(player, currentP+1, p, px+20,py);
		}
		playerTurn = rndm.nextInt(numberPlayers-1)+1;
	}
	
	/**
	* asignSymbol: Assigns symbols to players randomly <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return String, the player Symbol
	*/
	private String asignSymbol() {
		int pos = rndm.nextInt(9);		
		SymbolinkedNode symbolX = searchSymbol(pos);
		
		if(!symbolX.isTaken()) {
			symbolX.setTaken(true);
			return symbolX.getSymbol();
			
		}else {
			return asignSymbol();
		}
		
	}

	/**
	* searchSymbol: Search a symbol from the symbol linked list by position <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param pos Integer, the symbol position to search
	* @return searched SymbolinkedNode, the symbol searched 
	*/
	private SymbolinkedNode searchSymbol(int pos) {

		SymbolinkedNode searched = null;
		if(firstSymbol != null) {
			searched = searchSymbol(pos, firstSymbol);
		}
		return searched;
	}

	/**
	* searchSymbol: Search a symbol from the symbol linked list by position <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param pos Integer, the symbol position to search
	* @param current SymbolinkedNode, the current symbol in list
	* @return current SymbolinkedNode, the symbol searched 
	*/
	private SymbolinkedNode searchSymbol(int pos, SymbolinkedNode current) {
		if(current != null && current.getPositionInList() == pos) {
			return current;
		}
		if(current.getNext() != null) {
			return searchSymbol(pos, current.getNext());
		}
		return current;
	}

	/**
	* createInitialPlayers: create the initial player of the game <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param current Player, current player
	* @param currentP Integer, amount of players to create
	* @param p Integer, created players
	* @param px Integer, player initial horizontal position
	* @param py Integer, player initial vertical position
	*/
	private void createInitialPlayers(Player current, int currentP, int p, int px, int py) {
		
		if(current != null && currentP < p) {
			if(px >= 80) {
				px = 10;
				py+=22;
			}
			
			String symbolToAsign = asignSymbol();
			
			Player player = new Player(first.getRow(), first.getCol(), px, py, symbolToAsign, currentP+1, 0, rows, cols, numberPlayers, snakesInGame, laddersInGame);
			current.setNext(player);
			player.setPrev(current);
			first.setPlayersInCell(first.getPlayersInCell()+1);
			createInitialPlayers(current.getNext(), currentP+1, p, px+20, py);
		}
	}
	
	/**
	* movePlayer: Takes a player and moves it <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param cellsToMove Amount of positions to move the player
	* @return players symbol
	*/
	public String movePlayer(int cellsToMove) {
		Player player = null;
		if(first != null) {
			player = searchPlayerInRow(first, cellsToMove);
		}
		
		int finalPos = player.getCurrentPos()+cellsToMove;
		player.setMovs(player.getMovs()+1);
		
		if(finalPos >= rows*cols) {
			finalPos = rows*cols;
			player.setScore(rows*cols*player.getMovs());
			winner = player;
			gameWon = true;
		}
		
		MatrixNode actualPlayerNode = searchMatrixNode(player.getCurrentPos());		
		MatrixNode playerDestination = searchMatrixNode(finalPos);
		if(playerDestination.getExitPair() != null) {
			playerDestination = searchMatrixNode(playerDestination.getExitPair().getBoxNumber());
		}
		
		//eliminate player from actual node
		if(actualPlayerNode.getPlayersInCell() > 1) {
			
			if(player.getPrev() != null) {
				player.getPrev().setNext(player.getNext());
			}else {
				actualPlayerNode.setFirstPlayer(player.getNext());
			}
			if(player.getNext() != null) {
				player.getNext().setPrev(player.getPrev());
			}else {
				player.getPrev().setNext(null);
			}
			
		}else if (actualPlayerNode.getPlayersInCell() == 1){
			actualPlayerNode.setFirstPlayer(null);
		}
		if(actualPlayerNode.getPlayersInCell()>0) {
			actualPlayerNode.setPlayersInCell(actualPlayerNode.getPlayersInCell()-1);
		}
		
		// end eliminate player from actual node	
		int rowP = playerDestination.getRow();
		int colP = playerDestination.getCol();
		int pxp =(player.getPosX()-(player.getCol()*80))+(playerDestination.getCol()*80);
		int pyp = player.getPosY()-((playerDestination.getRow()-player.getRow())*80);
		int tp = player.getTurn();
		String sp = player.getSymbol();
		int movp = player.getMovs();
		
		Player movedPlayer = new Player(rowP, colP, pxp, pyp, sp, tp, movp, rows, cols, numberPlayers, snakesInGame, laddersInGame);

		player = null;
		
		//add player to destination node
		if(playerDestination.getFirstPlayer() == null) {

			addPlayerToNode(playerDestination, movedPlayer);
		}else {
			addPlayerToNode(playerDestination, movedPlayer);
		}
	
		
		if(playerTurn < numberPlayers) {
			playerTurn++;
		}else {
			playerTurn = 1;
		}
		
		return movedPlayer.getSymbol();
	}

	/**
	* addPlayerToNode: Add a player to a matrix node <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param currentNode Current matrix node
	* @param currentPlayer Player to add
	*/
	private void addPlayerToNode(MatrixNode currentNode, Player currentPlayer) {
		if(currentNode.getFirstPlayer() == null) {
			currentNode.setFirstPlayer(currentPlayer);
			currentNode.setPlayersInCell(currentNode.getPlayersInCell()+1);
			currentPlayer.setCurrentPos(currentNode.getBoxNumber());
			currentPlayer.setCol(currentNode.getCol());
			currentPlayer.setRow(currentNode.getRow());
		}else {
			addPlayerToNode(currentNode.getFirstPlayer(), currentPlayer, currentNode.getBoxNumber(), currentNode.getRow(), currentNode.getCol());
			currentNode.setPlayersInCell(currentNode.getPlayersInCell()+1);
		}
	}

	/**
	* addPlayerToNode: Add a player to a matrix node <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param currentPlayer Current player in list
	* @param addPlayer Player to add
	* @param boxNum Number of the node
	* @param row row of the node
	* @param col column of the node
	*/
	private void addPlayerToNode(Player currentPlayer, Player addPlayer, int boxNum, int row, int col) {
		if(currentPlayer.getNext() == null) {
			currentPlayer.setNext(addPlayer);
			addPlayer.setPrev(currentPlayer);
			addPlayer.setCurrentPos(boxNum);
			addPlayer.setCol(col);
			addPlayer.setRow(row);
		}else {
			addPlayerToNode(currentPlayer.getNext(), addPlayer, boxNum, row, col);
		}
	}

	/**
	* searchPlayerInCol: Search a player <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param current Current matrix node
	* @param cellsToMove Amount of positions to move to find the player
	* @return searched Player searched
	*/
	private Player searchPlayerInCol(MatrixNode current, int cellsToMove) {
		Player searched = null;
		if(current != null) {
			if(current.getFirstPlayer() != null) {
				
				searched = searchPlayerInLinkedPlayer(current.getFirstPlayer());
			}
			if(searched == null) {
				return searchPlayerInCol(current.getNext(), cellsToMove);
			}
		}
		return searched;
	}

	/**
	* searchPlayerInRow: Search a player <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param current Current matrix node
	* @param cellsToMove Amount of positions to move to find the player
	* @return searched Player searched
	*/
	private Player searchPlayerInRow(MatrixNode current, int cellsToMove) {
		Player searched = null;
		if(current != null) {
			searched = searchPlayerInCol(current, cellsToMove);
			if(searched == null) {
				searched = searchPlayerInRow(current.getUp(), cellsToMove);
			}
		}
		return searched;
	}
	
	/**
	* searchPlayerInLinkedPlayer: Search a player <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param currentPlayer Current player
	* @return searched Player searched
	*/
	private Player searchPlayerInLinkedPlayer(Player currentPlayer) {
		Player searched = null;
		if(currentPlayer != null) {
			if(currentPlayer.getTurn() == playerTurn) {
				searched = currentPlayer;
			}
			if(searched == null) {
				return searchPlayerInLinkedPlayer(currentPlayer.getNext());
			}	
		}
		return searched;
	}

	/**
	* getFirst: Gets the first node in the board <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return first The first node
	*/
	public MatrixNode getFirst() {
		return first;
	}

	/**
	* setFirst: Sets the first node of the board <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param first The new first node
	*/
	public void setFirst(MatrixNode first) {
		this.first = first;
	}

	/**
	* isGameWon: Gets the boolean that determines if the match was won <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return gameWon Boolean
	*/
	public boolean isGameWon() {
		return gameWon;
	}

	/**
	* setGameWon: Sets the boolean that determines if the match was won <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param gameWon Boolean
	*/
	public void setGameWon(boolean gameWon) {
		this.gameWon = gameWon;
	}

	/**
	* getWinner: Gets the winner player object of the board <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return winner The winner player object
	*/
	public Player getWinner() {
		return winner;
	}
	
	
}
