import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Client {
	public static void main(String args[]) throws IOException {
		
		Scanner input = new Scanner(System.in);
		boolean streamOpen = true;
		
		// Open your connection to a server, at port 6789
		String ip = "127.0.0.1";
		Socket socket = new Socket(ip,6789);
		// Get an input file handle from the socket and read the input
		InputStream inputStream = socket.getInputStream();
		DataInputStream dataInputStream = new DataInputStream(inputStream);
		OutputStream outStream = socket.getOutputStream();
		DataOutputStream dataOutStream = new DataOutputStream(outStream);
		
		do {

			String sentence = new String (dataInputStream.readUTF());
				System.out.println(sentence);	
				String words = input.nextLine();
		if (words.equals("end")) {
			dataOutStream.writeUTF("end");
			streamOpen = false;
			dataInputStream.close();
			inputStream.close();
			dataOutStream.close();
			outStream.close();
			socket.close();
			} else {

				dataOutStream.writeUTF(words);
			}
		} while (streamOpen);
	}
}