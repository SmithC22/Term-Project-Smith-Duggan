import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Server1 {
	public static void main(String args[]) throws IOException {
		
		Scanner input = new Scanner(System.in);
		Scanner guess = new Scanner(System.in);
		boolean streamOpen = true;
	
		// Register service on port 6789
		ServerSocket serverSocket1 = new ServerSocket(6789);
		ServerSocket serverSocket2 = new ServerSocket(6790);
		// Wait and accept a connection
		System.out.println("Waiting for clients...");
		
		Socket socket1 = serverSocket1.accept(); 
		System.out.println("One client found. Waiting for second client...");
		Socket socket2 = serverSocket2.accept(); 
	
		System.out.println("Clients found. Running game...");
		// Get a communication stream associated with the socket
		OutputStream outStream1 = socket1.getOutputStream();
		DataOutputStream dataOutStream1 = new DataOutputStream(outStream1);
		InputStream inputStream1 = socket1.getInputStream();
		DataInputStream dataInputStream1 = new DataInputStream(inputStream1);
		
		OutputStream outStream2 = socket2.getOutputStream();
		DataOutputStream dataOutStream2 = new DataOutputStream(outStream2);
		InputStream inputStream2 = socket2.getInputStream();
		DataInputStream dataInputStream2 = new DataInputStream(inputStream2);
	

			dataOutStream1.writeUTF("Enter your name: ");
			dataOutStream2.writeUTF("Enter your name: ");
			
			String client1 = new String (dataInputStream1.readUTF());
			String client2 = new String (dataInputStream2.readUTF());
			dataOutStream1.writeUTF("Connected with " +client2);	
			dataOutStream2.writeUTF("Connected with " +client1);	
			
			Player client1P = new Player(client1);
			Player client2P = new Player(client2);
			
		// loop that places client ships on client board	
		dataOutStream2.writeUTF("Waiting for "+client1+" to place ships...\n");	
		for (int size = 5; size > 1; size--) {
			
		String ship;
		
		dataOutStream1.writeUTF(client1P.playerBoard.toString() + "\nEnter coordinates to place ship (Length "+size+")\nStart Row: ");
		ship = dataInputStream1.readUTF();
		int x1 = Integer.parseInt(ship)-1;
		
		dataOutStream1.writeUTF("\nStart Column: ");
		ship = dataInputStream1.readUTF();
		int y1 = Integer.parseInt(ship)-1;
		
		dataOutStream1.writeUTF("\nEnd Row: ");
		ship = dataInputStream1.readUTF();
		int x2 = Integer.parseInt(ship)-1;
		
		dataOutStream1.writeUTF("\nEnd Column: ");
		ship = dataInputStream1.readUTF();
		int y2 = Integer.parseInt(ship)-1;
		
		while (!client1P.playerBoard.checkShip(x1, y1, x2, y2, size) || !client1P.playerBoard.checkShipSpace(x1, y1, x2, y2)) {
			dataOutStream1.writeUTF("\nIncorrect coordinates. Try again (Length "+size+") \nStart Row: ");
			ship = dataInputStream1.readUTF();
			x1 = Integer.parseInt(ship)-1;
			
			dataOutStream1.writeUTF("\nStart Column: ");
			ship = dataInputStream1.readUTF();
			y1 = Integer.parseInt(ship)-1;
			
			dataOutStream1.writeUTF("\nEnd Row: ");
			ship = dataInputStream1.readUTF();
			x2 = Integer.parseInt(ship)-1;
			
			dataOutStream1.writeUTF("\nEnd Column: ");
			ship = dataInputStream1.readUTF();
			y2 = Integer.parseInt(ship)-1;
		}
		client1P.playerBoard.placeShip(x1, y1, x2, y2);
		
	}
		
		String ship;
		// loop that places server ships on server board	
		dataOutStream1.writeUTF("Waiting for "+client2+" to place ships...");
		
		for (int size = 5; size > 1; size--) {
		
		dataOutStream2.writeUTF(client2P.playerBoard.toString() + "\nEnter coordinates to place ship (Length "+size+")\nStart Row: ");
		ship = dataInputStream2.readUTF();
		int x1 = Integer.parseInt(ship)-1;
		
		dataOutStream2.writeUTF("\nStart Column: ");
		ship = dataInputStream2.readUTF();
		int y1 = Integer.parseInt(ship)-1;
		
		dataOutStream2.writeUTF("\nEnd Row: ");
		ship = dataInputStream2.readUTF();
		int x2 = Integer.parseInt(ship)-1;
		
		dataOutStream2.writeUTF("\nEnd Column: ");
		ship = dataInputStream2.readUTF();
		int y2 = Integer.parseInt(ship)-1;
		
		while (!client2P.playerBoard.checkShip(x1, y1, x2, y2, size) || !client2P.playerBoard.checkShipSpace(x1, y1, x2, y2)) {
			dataOutStream2.writeUTF("\nIncorrect coordinates. Try again (Length "+size+") \nStart Row: ");
			ship = dataInputStream2.readUTF();
			x1 = Integer.parseInt(ship)-1;
			
			dataOutStream2.writeUTF("\nStart Column: ");
			ship = dataInputStream2.readUTF();
			y1 = Integer.parseInt(ship)-1;
			
			dataOutStream2.writeUTF("\nEnd Row: ");
			ship = dataInputStream2.readUTF();
			x2 = Integer.parseInt(ship)-1;
			
			dataOutStream2.writeUTF("\nEnd Column: ");
			ship = dataInputStream2.readUTF();
			y2 = Integer.parseInt(ship)-1;
		}
		client2P.playerBoard.placeShip(x1, y1, x2, y2);
		
	}
		int turn = 1;
		
		while(!client2P.lost() && !client1P.lost()) {
			
			
			String coordinates;
			int row;
			int column;
			
			dataOutStream2.writeUTF(client1+" is choosing a coordinate to hit...");

			dataOutStream1.writeUTF("Turn "+turn+ "\n" +client2+"'s Board:\n"+client2P.returnHitBoard()+"\nYour Board:\n"+client1P.returnPlayerBoard()+"\nGuess your coordinates ('Row', 'Column'): ");
			coordinates = dataInputStream1.readUTF();
			String x = coordinates.split(",")[0];
			String y = coordinates.split(",")[1];
			row = Integer.parseInt(x);
			column = Integer.parseInt(y);
		
			if (client2P.Hit(row-1, column-1)) {
				dataOutStream1.writeUTF("Hit!\n"+client2+" is choosing a coordinate to hit...");
				dataOutStream2.writeUTF("Ship was hit at (" +row+","+column+")!\n");
			} else {
				dataOutStream1.writeUTF("Miss!\n"+client2+" is choosing a coordinate to hit...");
				dataOutStream2.writeUTF("Shot missed!\n");
			}
			
			if (!client2P.lost()) {
				String coordinatesS;
				int rowS;
				int columnS;
		
			dataOutStream2.writeUTF("Turn "+turn+ "\n" +client1+"'s\n Board:\n"+client1P.returnHitBoard()+"\nYour Board:\n"+client2P.returnPlayerBoard()+"\nGuess your coodinates('Row', Column'): ");
			coordinatesS = dataInputStream2.readUTF();
			String xS = coordinatesS.split(",")[0];
			String yS = coordinatesS.split(",")[1];
			rowS = Integer.parseInt(xS);
			columnS = Integer.parseInt(yS);
		
			if (client1P.Hit(rowS-1, columnS-1)) {
				dataOutStream2.writeUTF("Hit!\n");
				dataOutStream1.writeUTF("Ship was hit at (" +rowS+","+columnS+")!\n");
			} else {
				dataOutStream2.writeUTF("Miss!\n");
				dataOutStream1.writeUTF("Shot missed!\n");
			}
		}
			turn++;
		}
		
		if (client1P.lost()) {
			dataOutStream2.writeUTF(client2+ " wins! Thanks for playing, exiting");
			dataOutStream1.writeUTF(client2+ " wins! Thanks for playing, exiting");
		} else {
			dataOutStream2.writeUTF(client1+ " wins! Thanks for playing, exiting");
			dataOutStream1.writeUTF(client1+ " wins! Thanks for playing, exiting");
		}
		
		System.out.println("Game is over. Exiting.");
		dataOutStream1.close();
		socket1.close();
		serverSocket1.close();
		dataOutStream2.close();
		socket2.close();
		serverSocket2.close();
	}
}