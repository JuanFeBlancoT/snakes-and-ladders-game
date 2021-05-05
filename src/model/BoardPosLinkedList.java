package model;

public class BoardPosLinkedList {
	
	private int type;		//Determines whether this box is an empty box or an entrance/exit from a snake or ladder 
	private int num;
	private boolean taken;
	private int pairNum;
	
	private BoardPosLinkedList next;
	
	/**
	* BoardPosLinkedList: Class constructor <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param n Integer number with the position in the board
	*/
	public BoardPosLinkedList(int n) {
		num = n;
		taken = false;
		next = null;
		type = 0;
		pairNum = 0;
	}
	
	/**
	* getNum: Gets the number of the node <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return num Integer number
	*/
	public int getNum() {
		return num;
	}

	/**
	* setNum: Sets the number of the node <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param num New Integer number
	*/
	public void setNum(int num) {
		this.num = num;
	}

	/**
	* getNext: Gets the next node in the linked list <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return next Next BoardPosLinkedList in list
	*/
	public BoardPosLinkedList getNext() {
		return next;
	}

	/**
	* setNext: Sets the next node int the linked list <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param next New next BoardPosLinkedList node
	*/
	public void setNext(BoardPosLinkedList next) {
		this.next = next;
	}

	/**
	* isTaken: Gets the boolean value that determines if the position is already taken <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return taken Boolean
	*/
	public boolean isTaken() {
		return taken;
	}

	/**
	* setTaken: Sets the boolean value that determines if the position is already taken <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return taken Boolean
	*/
	 public void setTaken(boolean taken) {
		this.taken = taken;
	}
	 
	/**
	* getType: Gets the node type, depending on if it is an exit or entrance from a snake or ladder <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return type Integer number between 1 and 4
	*/
	public int getType() {
		return type;
	}

	/**
	* getType: Sets the node type, depending on if it is an exit or entrance from a snake or ladder <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param type Integer number between 1 and 4
	*/
	public void setType(int type) {
		this.type = type;
	}

	/**
	* getPairNum: Gets the pair node number of the current node <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return pairNum Integer number of the pair node
	*/
	public int getPairNum() {
		return pairNum;
	}

	/**
	* setPairNum: Sets the pair node number of the current node <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param pairNum Integer number of the pair node
	*/
	public void setPairNum(int pairNum) {
		this.pairNum = pairNum;
	}
	
	
}
