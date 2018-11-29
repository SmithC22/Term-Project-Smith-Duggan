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
		dataOutStream.writeUTF("Your Board:\n"+clientP.returnPlayerBoard()+"\n"+serverName+"'s Board:\n"+clientP.returnHitBoard());
		System.out.print("Your Board:\n"+serverP.returnPlayerBoard()+"\n"+clientResponse+"'s Board:\n"+serverP.returnHitBoard());
		
	}
}