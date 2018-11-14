import java.net.*;
import java.io.*;

public class Battleship {
	
	public static void main(String args[]) throws IOException {
		
		// Open your connection to a server, at port 6789
		String IP = Inet4Address.getLocalHost().getHostAddress();
		System.out.println(IP);
		
		Socket socket = new Socket(IP,6789);
		
		// Get an input file handle from the socket and read the input
//		InputStream inputStream = socket.getInputStream();
//		DataInputStream dataInputStream = new DataInputStream(inputStream);

	}
}
