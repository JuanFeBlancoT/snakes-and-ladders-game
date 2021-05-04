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
		try {
			menu.drawScene(this);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(menu.getGameController().getBoard() != null) {
			menu.getGameController().getBoard().moveTable(globalPosX, globalPosY);
		}
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
		}
		
		if(menu.getScreen() == 5) {
			
			float e = event.getCount();
			globalPosY+=(int)e*15;
			menu.getGameController().moveWinners(globalPosY);
		}
	}
	
	public void mousePressed() {
		if(mouseButton == RIGHT) {
			scrollType = !scrollType;
		}
		
		//screen 1
		if(menu.getScreen() == 1 && mouseX > (WIDTH/2)-200 && mouseX < (WIDTH/2)+200 && mouseY > HEIGHT/4 && mouseY < (HEIGHT/4)+80) {
			menu.setScreen(2);
		}
		
		if(menu.getScreen() == 1 && mouseX > (WIDTH/2)-200 && mouseX < (WIDTH/2)+200 && mouseY > (HEIGHT/2)-25 && mouseY < (HEIGHT/2)+55) {
			menu.setScreen(5);
		}
		
		if(menu.getScreen() == 1 && mouseX > (WIDTH/2)-200 && mouseX < (WIDTH/2)+200 && mouseY > HEIGHT-275 && mouseY < HEIGHT-195) {
			System.out.println("btn quit");
		}
		
		//screen 2
		if(menu.getScreen() == 2 && mouseX > (WIDTH/2)-200 && mouseX < (WIDTH/2)+200 && mouseY > (HEIGHT/2)-25 && mouseY < (HEIGHT/2)+55) {
			if(!menu.getRows().isEmpty() && !menu.getCols().isEmpty() && !menu.getSnakes().isEmpty() && !menu.getLadders().isEmpty() && !menu.getPlayers().isEmpty() ) {
				try {
					
					int n = Integer.parseInt(menu.getRows());
					int m = Integer.parseInt(menu.getCols());
					int s = Integer.parseInt(menu.getSnakes());
					int l = Integer.parseInt(menu.getLadders());
					int p = Integer.parseInt(menu.getPlayers());
					
					if(n > 1 && m > 1 && ((2*s)+(2*l)) <= (n*m)/2 && p > 1 && p < 10) {
						menu.startMatch(n,m,s,l,p);
						menu.setScreen(3);
					}
				} catch (NumberFormatException e) {
					//not numbers
				}
			}else {
				//empty field
			}
		}
		
		if(menu.getScreen() == 3 && mouseX > 1100 && mouseX < 1170 && mouseY > 805 && mouseY < 875) {
			
			menu.getGameController().movePlayer(this);
			
			if(menu.getGameController().isGameWon()) {
				menu.setScreen(4);
			}
		}
		
		if(menu.getScreen() == 3 && mouseX > 50 && mouseX < 250 && mouseY > 805 && mouseY < 875) {
			restart();
		}
		
		if(menu.getScreen() == 4 && mouseX > 400 && mouseX < 800 && mouseY > 580 && mouseY < 660) {
			if(!menu.getNickNameText().isEmpty()) {
				menu.setWinner();
				restart();
			}else {
				//alert, nickname empty
			}
		}
		
		if(menu.getScreen() == 3 && mouseX > 950 && mouseX < 1050 && mouseY > 805 && mouseY < 875) {
			menu.getGameController().setCanPress(false);
			menu.setScreen(6);
		}
		
		if(menu.getScreen() == 5 && mouseX > 50 && mouseX < 250 && mouseY > 805 && mouseY < 875) {
			menu.setScreen(1);
		}
		
		//input selection
		
		if(menu.getScreen() == 4 &&  mouseX > 300 && mouseX < 900 && mouseY > 380 && mouseY < 440) {
			menu.setSelectedNickNameInput(true);
		}else {
			menu.setSelectedNickNameInput(false);
		}
		
		if(menu.getScreen() == 2 && mouseX > 150 && mouseX < 250 && mouseY > 200 && mouseY < 300) {
			menu.setSelecRows(true);
			menu.setGenericString("");
		}else {
			menu.setSelecRows(false);
		}
		if(menu.getScreen() == 2 && mouseX > 350 && mouseX < 450 && mouseY > 200 && mouseY < 300) {
			menu.setSelectCols(true);
			menu.setGenericString("");
		}else {
			menu.setSelectCols(false);
		}
		if(menu.getScreen() == 2 && mouseX > 550 && mouseX < 650 && mouseY > 200 && mouseY < 300) {
			menu.setSelectSnakes(true);
			menu.setGenericString("");
		}else {
			menu.setSelectSnakes(false);
		}
		if(menu.getScreen() == 2 && mouseX > 750 && mouseX < 850 && mouseY > 200 && mouseY < 300) {
			menu.setSelectLadders(true);
			menu.setGenericString("");
		}else {
			menu.setSelectLadders(false);
		}
		if(menu.getScreen() == 2 && mouseX > 950 && mouseX < 1050 && mouseY > 200 && mouseY < 300) {
			menu.setSelectPlayers(true);
			menu.setGenericString("");
		}else {
			menu.setSelectPlayers(false);
		}
		
		
	}
	
	public void restart() {
		menu.setScreen(1);
		menu.getGameController().setBoard(null);
		menu.getGameController().setActualWinner(null);
		globalPosX = 0;
		globalPosY = 0;
	}
	
	public void keyPressed() {
		
		if(menu.getGenericString().length() < 50) {
			switch (key) {
			case 'a': 
				menu.setGenericString(menu.getGenericString()+"a");
				break;
			case 'A':
				menu.setGenericString(menu.getGenericString()+"A");
				break;
			case 'b':
				menu.setGenericString(menu.getGenericString()+"b");
				break;
			case 'B':
				menu.setGenericString(menu.getGenericString()+"B");
				break;
			case 'c':
				menu.setGenericString(menu.getGenericString()+"c");
				break;
			case 'C':
				menu.setGenericString(menu.getGenericString()+"C");
				break;
			case 'd':
				menu.setGenericString(menu.getGenericString()+"d");
				break;
			case 'D':
				menu.setGenericString(menu.getGenericString()+"D");
				break;
			case 'e':
				menu.setGenericString(menu.getGenericString()+"e");
				break;
			case 'E':
				menu.setGenericString(menu.getGenericString()+"E");
				break;
			case 'f':
				menu.setGenericString(menu.getGenericString()+"f");
				break;
			case 'F':
				menu.setGenericString(menu.getGenericString()+"F");
				break;
			case 'g':
				menu.setGenericString(menu.getGenericString()+"g");
				break;
			case 'G':
				menu.setGenericString(menu.getGenericString()+"G");
				break;
			case 'h':
				menu.setGenericString(menu.getGenericString()+"h");
				break;
			case 'H':
				menu.setGenericString(menu.getGenericString()+"H");
				break;
			case 'i':
				menu.setGenericString(menu.getGenericString()+"i");
				break;
			case 'I':
				menu.setGenericString(menu.getGenericString()+"I");
				break;
			case 'j':
				menu.setGenericString(menu.getGenericString()+"j");
				break;
			case 'J':
				menu.setGenericString(menu.getGenericString()+"J");
				break;
			case 'k':
				menu.setGenericString(menu.getGenericString()+"k");
				break;
			case 'K':
				menu.setGenericString(menu.getGenericString()+"K");
				break;
			case 'l':
				menu.setGenericString(menu.getGenericString()+"l");
				break;
			case 'L':
				menu.setGenericString(menu.getGenericString()+"L");
				break;
			case 'm':
				menu.setGenericString(menu.getGenericString()+"m");
				break;
			case 'M':
				menu.setGenericString(menu.getGenericString()+"M");
				break;
			case 'n':
				menu.setGenericString(menu.getGenericString()+"n");
				break;
			case 'N':
				menu.setGenericString(menu.getGenericString()+"N");
				break;
			case 'o':
				menu.setGenericString(menu.getGenericString()+"o");
				break;
			case 'O':
				menu.setGenericString(menu.getGenericString()+"O");
				break;
			case 'p':
				menu.setGenericString(menu.getGenericString()+"p");
				break;
			case 'P':
				menu.setGenericString(menu.getGenericString()+"P");
				break;
			case 'q':
				menu.setGenericString(menu.getGenericString()+"q");
				break;
			case 'Q':
				menu.setGenericString(menu.getGenericString()+"Q");
				break;
			case 'r':
				menu.setGenericString(menu.getGenericString()+"r");
				break;
			case 'R':
				menu.setGenericString(menu.getGenericString()+"R");
				break;
			case 's':
				menu.setGenericString(menu.getGenericString()+"s");
				break;
			case 'S':
				menu.setGenericString(menu.getGenericString()+"S");
				break;
			case 't':
				menu.setGenericString(menu.getGenericString()+"t");
				break;
			case 'T':
				menu.setGenericString(menu.getGenericString()+"T");
				break;
			case 'u':
				menu.setGenericString(menu.getGenericString()+"u");
				break;
			case 'U':
				menu.setGenericString(menu.getGenericString()+"U");
				break;
			case 'v':
				menu.setGenericString(menu.getGenericString()+"v");
				break;
			case 'V':
				menu.setGenericString(menu.getGenericString()+"V");
				break;
			case 'w':
				menu.setGenericString(menu.getGenericString()+"w");
				break;
			case 'W':
				menu.setGenericString(menu.getGenericString()+"W");
				break;
			case 'x':
				menu.setGenericString(menu.getGenericString()+"x");
				break;
			case 'X':
				menu.setGenericString(menu.getGenericString()+"X");
				break;
			case 'y':
				menu.setGenericString(menu.getGenericString()+"y");
				break;
			case 'Y':
				menu.setGenericString(menu.getGenericString()+"Y");
				break;
			case 'z':
				menu.setGenericString(menu.getGenericString()+"z");
				break;
			case 'Z':
				menu.setGenericString(menu.getGenericString()+"Z");
				break;
			case '-':
				menu.setGenericString(menu.getGenericString()+"-");
				break;
			case '_':
				menu.setGenericString(menu.getGenericString()+"_");
				break;
			case 48:
				menu.setGenericString(menu.getGenericString()+0);
				break;
			case 49:
				menu.setGenericString(menu.getGenericString()+1);
				break;
			case 50:
				menu.setGenericString(menu.getGenericString()+2);
				break;
			case 51:
				menu.setGenericString(menu.getGenericString()+3);
				break;
			case 52:
				menu.setGenericString(menu.getGenericString()+4);
				break;
			case 53:
				menu.setGenericString(menu.getGenericString()+5);
				break;
			case 54:
				menu.setGenericString(menu.getGenericString()+6);
				break;
			case 55:
				menu.setGenericString(menu.getGenericString()+7);
				break;
			case 56:
				menu.setGenericString(menu.getGenericString()+8);
				break;
			case 57:
				menu.setGenericString(menu.getGenericString()+9);
				break;
			case 8:
				if(menu.getGenericString().length() > 0) {
					int x = menu.getGenericString().length()-1;
					String newText = (String) menu.getGenericString().subSequence(0, x);
					
					menu.setGenericString(newText);
				}
				break;
			}
			
			if(menu.isSelectedNickNameInput()) {
				menu.setNickNameText(menu.getGenericString());
			}
			if(menu.isSelecRows()) {
				menu.setRows(menu.getGenericString());
			}
			if(menu.isSelectCols()) {
				menu.setCols(menu.getGenericString());
			}			
			if(menu.isSelectSnakes()) {
				menu.setSnakes(menu.getGenericString());
			}
			if(menu.isSelectLadders()) {
				menu.setLadders(menu.getGenericString());
			}
			if(menu.isSelectPlayers()) {
				menu.setPlayers(menu.getGenericString());
			}
			
		}else {
			int x = menu.getGenericString().length()-1;
			String newText = (String) menu.getGenericString().subSequence(0, x);
			
			menu.setGenericString(newText);
		}
		
	}//end keyPressed
	
}
