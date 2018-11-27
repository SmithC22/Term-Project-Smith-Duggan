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
	
	public boolean isHitPlayer(int r, int c) {
		return playerBoard.isHit(r, c);
	}
	
	public boolean placeDestroyerPlayer(int x1, int y1, int x2, int y2) {
		return Board.placeDestroyer(x1, y1, x2, y2, playerBoard);
	}
	
	public boolean placeSubmarinePlayer(int x1, int y1, int x2, int y2) {
		return Board.placeSubmarine(x1, y1, x2, y2, playerBoard);
	}
	
	public boolean placeCruiserPlayer(int x1, int y1, int x2, int y2) {
		return Board.placeCruiser(x1, y1, x2, y2, playerBoard);
	}
	
	public boolean placeBattleshipPlayer(int x1, int y1, int x2, int y2) {
		return Board.placeBattleship(x1, y1, x2, y2, playerBoard);
	}
	
	public boolean placeCarrierPlayer(int x1, int y1, int x2, int y2) {
		return Board.placeCarrier(x1, y1, x2, y2, playerBoard);
	}
	
	public String getName() {
		return name;
	}
	
	public void Hit(int r, int c) {
		score--;
		playerBoard.hit(r, c);
		hitBoard.hitEmptyBoard(r, c);
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
	
}
