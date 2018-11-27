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
/*		Board bServer = new Board();
		Board bClient = new Board();
		Board bServerG = new Board();
		Board bClientG = new Board();
		bServer.fillEmpty();
		bClient.fillEmpty();
		bServerG.fillEmpty();
		bClientG.fillEmpty();
		
		System.out.println("Waiting for Client to place ships...");
*/		
		do {

			dataOutStream.writeUTF("Connected with "+serverName+ ". Enter your name: ");
			
			String clientResponse = new String (dataInputStream.readUTF());
			System.out.println(clientResponse + " has connected.");	
/*		for (int size = 5; size > 0; size--) {
			
		
		dataOutStream.writeUTF(bClient.toString() + "\nEnter coordinates to place ship (Length "+size+") ('Start Row', 'Start Column', 'End Row', 'End Column')\n");
		String ship1 = new String (dataInputStream.readUTF());
		int ship1a = ship1.charAt(0);
		int ship1b = ship1.charAt(2);
		int ship1c = ship1.charAt(4);
		int ship1d = ship1.charAt(6);
		
		while (!bClient.checkShip(ship1a, ship1b, ship1c, ship1d, size)) {
			dataOutStream.writeUTF("\nIncorrect coordinates. Try again (Length "+size+") ('Start Row', 'Start Column', 'End Row', 'End Column')\n");
			ship1 = new String (dataInputStream.readUTF());
			ship1a = ship1.charAt(0);
			ship1b = ship1.charAt(2);
			ship1c = ship1.charAt(4);
			ship1d = ship1.charAt(6);
		}
		bClient.placeShip(ship1a, ship1b, ship1c, ship1d);
		}
		
*/	
		} while (streamOpen);
	}
}