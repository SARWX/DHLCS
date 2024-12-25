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
    

	 public static void main(String[] args) {
	        System.out.println("Server has started...");

	        try (ServerSocket serverSocket = new ServerSocket(9001)) {
	            while (true) {
	                try (Socket clientSocket = serverSocket.accept();
	                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

	                    System.out.println("Client connected: " + clientSocket.getRemoteSocketAddress());

	                    String message;
	                    while ((message = in.readLine()) != null) {
	                        if (message.equals("message")) {
	                            m();
	                        } else if (message.startsWith("isPrime ")) {
	                            try {
	                                int number = Integer.parseInt(message.substring(8).trim());
	                                if (isPrime(number)) {
	                                    System.out.println("The number " + number + " is Prime.");
	                                } else {
	                                    System.out.println("The number " + number + " is not Prime.");
	                                }
	                            } catch (NumberFormatException e) {
	                                System.out.println("Error: can't convert number after 'isPrime'.");
	                            }
	                        } else {
	                            System.out.println("No such command: " + message);
	                        }
	                    }
	                } catch (IOException e) {
	                    System.out.println("Client connection error: " + e.getMessage());
	                }
	            }
	        } catch (IOException e) {
	            System.out.println("Server error: " + e.getMessage());
	        }
	    }
	}
