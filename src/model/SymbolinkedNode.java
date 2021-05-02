package model;

public class SymbolinkedNode {
	
	private String symbol;
	private int positionInList;
	private boolean taken;
	
	private SymbolinkedNode next;
	
	public SymbolinkedNode(int pos, String s) {
		symbol = s;
		positionInList = pos;
	}

	public SymbolinkedNode getNext() {
		return next;
	}

	public void setNext(SymbolinkedNode next) {
		this.next = next;
	}
	
	public boolean isTaken() {
		return taken;
	}

	public void setTaken(boolean taken) {
		this.taken = taken;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public int getPositionInList() {
		return positionInList;
	}

	public void setPositionInList(int positionInList) {
		this.positionInList = positionInList;
	}

	public String toString() {
		return "S: "+symbol+". Pos: "+positionInList;
	}
}
