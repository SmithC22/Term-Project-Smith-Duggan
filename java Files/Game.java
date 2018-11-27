import java.util.Scanner;

public class Game {
	Player player1;
	Player player2;
	Scanner in = new Scanner(System.in);
	
	public Game(Player p1, Player p2) {
		System.out.print("Enter player one's name: ");
		player1 = p1;
		System.out.print("Enter player two's name: ");
		player2 = p2;
	}
	
	public void placeShips(Player p) {
		/*System.out.println(p.getName()+" please enter your ship coordinates.");
		System.out.print("Enter first x coordinaate: ");
		int x1 = in.nextInt();
		System.out.print("Enter first y coordinaate: ");
		int y1 = in.nextInt();
		System.out.print("Enter second x coordinaate: ");
		int x2 = in.nextInt();
		System.out.print("Enter second y coordinaate: ");
		int y2 = in.nextInt();
		player1.placeSubmarinePlayer(x1, y1, x2, y2, b);*/
	}
	
	public void runGame() {
		
	}
	
	public static void main(String[] args){
		
	}
}
