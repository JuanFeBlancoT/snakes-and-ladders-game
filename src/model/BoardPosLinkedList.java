package model;

public class BoardPosLinkedList {
	
	private int type;		//Determines whether this box is an empty box or an entrance/exit from a snake or ladder 
	private int num;
	private boolean taken;
	private int pairNum;
	
	private BoardPosLinkedList next;
	
	public BoardPosLinkedList(int n) {
		num = n;
		taken = false;
		next = null;
		type = 0;
		pairNum = 0;
	}
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public BoardPosLinkedList getNext() {
		return next;
	}

	public void setNext(BoardPosLinkedList next) {
		this.next = next;
	}

	public boolean isTaken() {
		return taken;
	}

	public void setTaken(boolean taken) {
		this.taken = taken;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getPairNum() {
		return pairNum;
	}

	public void setPairNum(int pairNum) {
		this.pairNum = pairNum;
	}
	
	
}
