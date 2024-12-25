import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client_socket {

	public static void main(String[] args) 
			throws UnknownHostException, IOException {
		Socket so = new Socket("127.0.0.1", 9001);
		BufferedWriter out = new BufferedWriter(
				new OutputStreamWriter(so.getOutputStream()));
		String message = "Kick my ass";
		System.out.println("Action: " + message);
		out.write(message, 0, message.length());
		out.write("\n", 0, 1); // new line

		message = "isPrime 999";
		System.out.println("Action: " + message);
		out.write(message, 0, message.length());
		out.write("\n", 0, 1); // new line
		
		message = "isPrime 17";
		System.out.println("Action: " + message);
		out.write(message, 0, message.length());

		out.newLine();
		out.flush();
		out.close();
		so.close();
	}
}
