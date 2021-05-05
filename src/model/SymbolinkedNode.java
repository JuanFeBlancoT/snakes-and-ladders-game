package model;

public class SymbolinkedNode {
	
	private String symbol;
	private int positionInList;
	private boolean taken;
	
	private SymbolinkedNode next;
	
	/**
	* SymbolinkedNode: Constructor of the class SymbolinkedNode<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param pos Integer number that represents the position in the linked list
	* @param s The String symbol
	*/
	public SymbolinkedNode(int pos, String s) {
		symbol = s;
		positionInList = pos;
	}

	/**
	* getNext: Gets the next Node in the linked node<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return next The next Node
	*/
	public SymbolinkedNode getNext() {
		return next;
	}

	/**
	* setNext: Sets the next Node in the linked node<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param next New integer number that represents the position in the linked list
	*/
	public void setNext(SymbolinkedNode next) {
		this.next = next;
	}
	
	/**
	* isTaken: gets the boolean that determines if the node was already taken<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return taken Boolean
	*/
	public boolean isTaken() {
		return taken;
	}

	/**
	* setTaken: Sets the taken boolean<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param taken New boolean
	*/
	public void setTaken(boolean taken) {
		this.taken = taken;
	}

	/**
	* getSymbol: gets the node symbol<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return symbol String
	*/
	public String getSymbol() {
		return symbol;
	}

	/**
	* getPositionInList: gets the position in the linked list<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return positionInList String
	*/
	public int getPositionInList() {
		return positionInList;
	}

}
