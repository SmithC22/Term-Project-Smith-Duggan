import java.util.Scanner;

public class Player {
	String name;
	Board playerBoard;
	Board hitBoard;
	int score;
	Scanner in = new Scanner(System.in);
	
	public Player(String n){
		name = n;
		score = 14;
		playerBoard = new Board();
		playerBoard.fillEmpty();
		hitBoard = new Board();
		hitBoard.fillEmpty();
	}
	
	public boolean checkHit(int r, int c) {
		if ((1<=r && r<=8)&&(1<=c && 8<=c)) {
			if(hitBoard.battleShipBoard[r-1][c-1].equals("~"))  {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	public boolean isHitPlayer(int r, int c) {
		return playerBoard.isHit(r, c);
	}
	
	public void placeShip(int x1, int y1, int x2, int y2, int size) {
		playerBoard.placeShip(x1, y1, x2, y2);
	}
		
	
	public String getName() {
		return name;
	}
	
	public boolean Hit(int r, int c) {
		if(playerBoard.hit(r, c)) {
			score--;
			hitBoard.hitEmptyBoard(r, c);
			return true;
		}
		else {
			hitBoard.missEmptyBoard(r, c);
			return false;
		}
	}
	
	public boolean lost() {
		if(score == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public String returnPlayerBoard() {
		return playerBoard.toString();
	}
	
	public String returnHitBoard() {
		return hitBoard.toString();
	}

	public boolean checkShip(int x1, int y1, int x2, int y2, int size) {	
		return playerBoard.checkShip(x1, y1, x2, y2, size);
	}
	
}
