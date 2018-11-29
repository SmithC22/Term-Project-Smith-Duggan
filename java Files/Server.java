import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Server {
	public static void main(String args[]) throws IOException {
		
		Scanner input = new Scanner(System.in);
		boolean streamOpen = true;
	
		// Register service on port 6789
		ServerSocket serverSocket = new ServerSocket(6789);
		// Wait and accept a connection
		
		System.out.println("Enter name: ");
		String serverName = input.nextLine();
		Socket socket = serverSocket.accept(); 
	
		// Get a communication stream associated with the socket
		OutputStream outStream = socket.getOutputStream();
		DataOutputStream dataOutStream = new DataOutputStream(outStream);
		InputStream inputStream = socket.getInputStream();
		DataInputStream dataInputStream = new DataInputStream(inputStream);
	

			dataOutStream.writeUTF("Connected with "+serverName+ ". Enter your name: ");
			
			String clientResponse = new String (dataInputStream.readUTF());
			System.out.println(clientResponse + " has connected.");	
			
			Player serverP = new Player(serverName);
			Player clientP = new Player(clientResponse);
			
		// loop that places client ships on client board	
		System.out.println("Waiting for "+clientResponse+" to place ships...");	
		for (int size = 5; size > 1; size--) {
			
		String ship;
		
		dataOutStream.writeUTF(clientP.playerBoard.toString() + "\nEnter coordinates to place ship (Length "+size+")\nStart Row: ");
		ship = dataInputStream.readUTF();
		int x1 = Integer.parseInt(ship)-1;
		
		dataOutStream.writeUTF("\nStart Column: ");
		ship = dataInputStream.readUTF();
		int y1 = Integer.parseInt(ship)-1;
		
		dataOutStream.writeUTF("\nEnd Row: ");
		ship = dataInputStream.readUTF();
		int x2 = Integer.parseInt(ship)-1;
		
		dataOutStream.writeUTF("\nEnd Column: ");
		ship = dataInputStream.readUTF();
		int y2 = Integer.parseInt(ship)-1;
		
		while (!clientP.playerBoard.checkShip(x1, y1, x2, y2, size) || !clientP.playerBoard.checkShipSpace(x1, y1, x2, y2)) {
			dataOutStream.writeUTF("\nIncorrect coordinates. Try again (Length "+size+") \nStart Row: ");
			ship = dataInputStream.readUTF();
			x1 = Integer.parseInt(ship)-1;
			
			dataOutStream.writeUTF("\nStart Column: ");
			ship = dataInputStream.readUTF();
			y1 = Integer.parseInt(ship)-1;
			
			dataOutStream.writeUTF("\nEnd Row: ");
			ship = dataInputStream.readUTF();
			x2 = Integer.parseInt(ship)-1;
			
			dataOutStream.writeUTF("\nEnd Column: ");
			ship = dataInputStream.readUTF();
			y2 = Integer.parseInt(ship)-1;
		}
		clientP.playerBoard.placeShip(x1, y1, x2, y2);
		
	}
		
		// loop that places server ships on server board	
		dataOutStream.writeUTF("Waiting for "+serverName+" to place ships...");
		
		for (int size = 5; size > 1; size--) {
		
		System.out.print(serverP.playerBoard.toString() + "\nEnter coordinates to place ship (Length "+size+")\nStart Row: ");
		int x1 = input.nextInt()-1;
		
		System.out.print("\nStart Column: ");
		int y1 = input.nextInt()-1;
		
		System.out.print("\nEnd Row: ");
		int x2 = input.nextInt()-1;
		
		System.out.print("\nEnd Column: ");
		int y2 = input.nextInt()-1;
		
		while (!serverP.playerBoard.checkShip(x1, y1, x2, y2, size) || !serverP.playerBoard.checkShipSpace(x1, y1, x2, y2)) {
			System.out.print("\nIncorrect coordinates. Try again (Length "+size+") \nStart Row: ");
			x1 = input.nextInt()-1;
			
			System.out.print("\nStart Column: ");
			y1 = input.nextInt()-1;
			
			System.out.print("\nEnd Row: ");
			x2 = input.nextInt()-1;
			
			System.out.print("\nEnd Column: ");
			y2 = input.nextInt()-1;
		}
		serverP.playerBoard.placeShip(x1, y1, x2, y2);
		
	}
		
		while(!serverP.lost() && !clientP.lost()) {
			
			int turn = 1;
			String coordinates;
			
			System.out.println(clientResponse+" is choosing a coordinate to hit...");
			dataOutStream.writeUTF("Turn "+turn+ "\nYour Board:\n"+clientP.returnPlayerBoard()+"\n"+serverName+"'s Board:\n"+clientP.returnHitBoard()+"\nGuess your coordinates ('Row', 'Column'): ");
			coordinates = dataInputStream.readUTF();
			String x = coordinates.split(",")[0];
			String y = coordinates.split(",")[1];
			int row = Integer.parseInt(x)-1;
			int column = Integer.parseInt(y)-1;
			
			if (serverP.Hit(row, column)) {
				dataOutStream.writeUTF("Hit!\n");
				System.out.println("Ship was hit at (" +row+","+column+")!");
			} else {
				dataOutStream.writeUTF("Miss!\n");
				System.out.println(clientResponse+ "'s shot missed!");
			}
			
			dataOutStream.writeUTF(serverName+" is choosing a coordinate to hit...\n");
			System.out.println("Turn "+turn+ "\nYour Board:\n"+serverP.returnPlayerBoard()+"\n"+clientResponse+"'s Board:\n"+serverP.returnHitBoard()+"Guess your coodinates('Row', Column')");
			String coordinatesS = input.nextLine();
			String xS = coordinatesS.split(",")[0];
			String yS = coordinatesS.split(",")[1];
			int rowS = Integer.parseInt(xS)-1;
			int columnS = Integer.parseInt(yS)-1;
			
			if (clientP.Hit(rowS, columnS)) {
				System.out.println("Hit!\n");
				dataOutStream.writeUTF("Ship was hit at (" +row+","+column+")!");
			} else {
				System.out.println("Miss!\n");
				dataOutStream.writeUTF(serverName+ "'s shot missed!");
			}
			
			turn++;
		}
		
		if (clientP.lost()) {
			System.out.println(serverName+ " wins! Thanks for playing, exiting");
			dataOutStream.writeUTF(serverName+ " wins! Thanks for playing, exiting");
		} else {
			System.out.println(clientResponse+ " wins! Thanks for playing, exiting");
			dataOutStream.writeUTF(clientResponse+ " wins! Thanks for playing, exiting");
		}
	}
}