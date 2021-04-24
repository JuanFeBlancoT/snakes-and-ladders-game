package model;

public class BoardPosLinkedList {

	private int num;
	private boolean taken;
	
	private BoardPosLinkedList next;
	
	public BoardPosLinkedList(int n) {
		num = n;
		taken = false;
		next = null;
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
	
	
}
