import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Client1 {
	public static void main(String args[]) throws IOException {
		
		Scanner input = new Scanner(System.in);
		boolean streamOpen = true;
		
		// Open your connection to a server, at port 6789
		String ip = "192.168.1.13";
		Socket socket = new Socket(ip,6790);
		// Get an input file handle from the socket and read the input
		InputStream inputStream = socket.getInputStream();
		DataInputStream dataInputStream = new DataInputStream(inputStream);
		OutputStream outStream = socket.getOutputStream();
		DataOutputStream dataOutStream = new DataOutputStream(outStream);
		
		String sentence = "";
		
		// loop for placing ships
		do {
			sentence = dataInputStream.readUTF();
				System.out.println(sentence);
				
				if(sentence.charAt(sentence.length()-1) == 'g') {
					streamOpen = false;
				}
				
				else if ((sentence.charAt(0) != 'W') && (sentence.charAt(0) != 'H') && (sentence.charAt(0) != 'M') && (sentence.charAt(0) != 'S') && (sentence.charAt(0) != 'C') && (sentence.charAt(sentence.length()-1) != '.')) {
				String words = input.nextLine();
				dataOutStream.writeUTF(words);
				}
			}
		 while (streamOpen);
		
	}
}