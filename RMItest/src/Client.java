import java.math.BigInteger;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws RemoteException, NotBoundException {
		Registry re = LocateRegistry.getRegistry("127.0.0.1", 9001);
		IServer s = (IServer) re.lookup("server");
		s.m();
		
		try(Scanner scanner = new Scanner(System.in)) {
			System.out.print("Enter a number to check if it is prime: ");
	//        int number = scanner.nextInt();
			BigInteger number = new BigInteger(scanner.next());
	
	        if (s.isPrime(number)) {
	            System.out.println("The number " + number + " is prime.");
	        } else {
	            System.out.println("The number " + number + " is not prime.");
	        }
		}	
	}

}
