import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server_socket {
	
	private static void m() {
		
		System.out.println("Hello!");
	}
	
	public static boolean isPrime(int number) {
        // Negative, 0 and 1 aren't prime
        if (number <= 1) {
            return false;
        }

        // Check if number has divider between 1 and itself
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false; // Divider is found -> number isn't prime
            }
        }
        return true;
	}// Number is prime
    

	public static void main(String[] args) throws IOException {
		System.out.println("Server has started...");
		ServerSocket s = new ServerSocket(9001);
		Socket so = s.accept();
		
		BufferedReader in = new BufferedReader(
				new InputStreamReader(so.getInputStream()));
		String message = in.readLine();
		
		while(message != null) {
			if (message.equals("message")) {
				m();
			} else if (message.startsWith("isPrime ")) {
			    try {
			        // Parse number
			        int number = Integer.parseInt(message.substring(8).trim());
			        // Check if number is prime
			        if (isPrime(number)) {
			            System.out.println(number + " is Prime.");
			        } else {
			            System.out.println(number + " is not Prime.");
			        }
			    } catch (NumberFormatException e) {
			        System.out.println("Error: can't convert number after 'isPrime'.");
			    }
			} else {
			    System.out.println("No such command: " + message);
			    System.out.println();
			}
			message = in.readLine();
		}
		
		so.close();
		s.close();
	}
}
