package ui;

import processing.core.PApplet;
import processing.event.MouseEvent;

public class Main extends PApplet{
	
	public int globalPosX;
	public int globalPosY;
	boolean scrollType;
	
	private Menu menu;
	
	public static void main(String[] args) {
		PApplet.main("ui.Main");
	}
	
	public void settings() {
		size(1000,1000);
	}
		
	public void setup() {
		globalPosX = 0;
		globalPosY = 0;
		menu = new Menu();
	}
	
	public void draw() {
		background(40);
		fill(200);	
		menu.drawScene(this);
	}
		
	public void mouseWheel(MouseEvent event) {
		
		float e = event.getCount();
		if(!scrollType && globalPosX>=0) {
			globalPosX+=(int)e*3;
		}
		if(globalPosX<0) {
			globalPosX = 0;
		}
		
		if(scrollType && globalPosY>=0){
			globalPosY+=(int)e*3;
		}if(globalPosY<0) {
			globalPosY = 0;
		}
		menu.getBoard().moveTable(globalPosX, globalPosY);
	}
	
	public void mousePressed() {
		if(mouseButton == RIGHT) {
			scrollType = !scrollType;
		}
	}
	
}
