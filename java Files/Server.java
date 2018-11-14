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
		Socket socket = serverSocket.accept(); 
	
		// Get a communication stream associated with the socket
		OutputStream outStream = socket.getOutputStream();
		DataOutputStream dataOutStream = new DataOutputStream(outStream);
		InputStream inputStream = socket.getInputStream();
		DataInputStream dataInputStream = new DataInputStream(inputStream);
		Board b = new Board();
		b.fillEmpty();
		// Send a string!
		do {
		dataOutStream.writeUTF(b.toString());
		String sentence = new String (dataInputStream.readUTF());
		
		if (sentence.equals("end")) {
			dataOutStream.writeUTF("");
			System.out.println("Session ended");
			streamOpen = false;
			dataOutStream.close();
			outStream.close();
			dataInputStream.close();
			inputStream.close();
			socket.close();
			serverSocket.close();
			} else {
		System.out.println("Client: " + sentence);
			}
		} while (streamOpen);
	}
}