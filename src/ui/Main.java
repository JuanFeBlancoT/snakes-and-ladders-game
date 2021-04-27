package ui;

import processing.core.PApplet;
import processing.event.MouseEvent;

public class Main extends PApplet{
	
	//constants
	public final static int WIDTH = 1200;
	public final static int HEIGHT = 900;
	
	//attributes
	public int globalPosX;
	public int globalPosY;
	boolean scrollType;
	
	//relations
	private Menu menu;
	
	public static void main(String[] args) {
		PApplet.main("ui.Main");
	}
	
	public void settings() {
		size(WIDTH,HEIGHT);
	}
		
	public void setup() {
		globalPosX = 0;
		globalPosY = 0;
		menu = new Menu(WIDTH, HEIGHT);
	}
	
	public void draw() {
		background(40);
		menu.drawScene(this);
	}
		
	public void mouseWheel(MouseEvent event) {
		
		if(menu.getScreen() == 3) {
			float e = event.getCount();
			if(!scrollType && globalPosX>=0) {
				globalPosX+=(int)e*15;
			}
			if(globalPosX<0) {
				globalPosX = 0;
			}
			
			if(scrollType && globalPosY>=0){
				globalPosY+=(int)e*15;
			}if(globalPosY<0) {
				globalPosY = 0;
			}
			menu.getGameController().getBoard().moveTable(globalPosX, globalPosY);
			//getBoard().moveTable(globalPosX, globalPosY);
		}
	}
	
	public void mousePressed() {
		if(mouseButton == RIGHT) {
			scrollType = !scrollType;
		}
		
		//screen 1
		if(menu.getScreen() == 1 && mouseX > (WIDTH/2)-200 && mouseX < (WIDTH/2)+200 && mouseY > HEIGHT/4 && mouseY < (HEIGHT/4)+80) {
			System.out.println("btn new game");
			menu.setScreen(2);
		}
		
		if(menu.getScreen() == 1 && mouseX > (WIDTH/2)-200 && mouseX < (WIDTH/2)+200 && mouseY > (HEIGHT/2)-25 && mouseY < (HEIGHT/2)+55) {
			System.out.println("btn winners");
		}
		
		if(menu.getScreen() == 1 && mouseX > (WIDTH/2)-200 && mouseX < (WIDTH/2)+200 && mouseY > HEIGHT-275 && mouseY < HEIGHT-195) {
			System.out.println("btn quit");
		}
		
		//screen 2
		if(menu.getScreen() == 2 && mouseX > (WIDTH/2)-200 && mouseX < (WIDTH/2)+200 && mouseY > (HEIGHT/2)-25 && mouseY < (HEIGHT/2)+55) {
			System.out.println("start");
			//menu.getGameController().getBoard().createBoardPositionLinkedList(3,3);
			menu.getGameController().createBoard(4, 4);
			menu.getGameController().getBoard().createSpecialCells(2, 2, 4, 4);
			//menu.getGameController().getBoard().createSnakePositions(0,2, 3, 3);
			//menu.getGameController().getBoard().createLaddersPoistions(0,2, 3, 3);
			menu.getGameController().getBoard().asignSnakesAndLadders();
			
			menu.setScreen(3);
		}
	}
	
}
