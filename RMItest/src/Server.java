import java.math.BigInteger;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server extends UnicastRemoteObject 
					implements IServer {
	private static final long serialVersionUID = 1L;

	protected Server() throws RemoteException {	}

	public void m() throws RemoteException {
		System.out.println("Hey dude!");		
	}

	public boolean isPrime(BigInteger number) {
	    // Negative, 0, and 1 aren't prime
	    if (number.compareTo(BigInteger.ONE) <= 0) { // number <= 1
	        return false;
	    }

	    // Check divisors from 2 to sqrt number
	    BigInteger sqrt = number.sqrt(); // Efficient computation of sqrt number
	    BigInteger two = BigInteger.TWO;

	    for (BigInteger i = two; i.compareTo(sqrt) <= 0; i = i.add(BigInteger.ONE)) { // i++
	        if (number.mod(i).equals(BigInteger.ZERO)) { // number % i == 0
	            return false; // Divider is found -> number isn't prime
	        }
	    }
	    return true; // Number is prime
	}
	
    public static void main(String[] args) {
        try {
            System.out.println("Server started...");
            Server s = new Server();
            Registry re = LocateRegistry.createRegistry(9001);
            re.rebind("server", s);
            System.out.println("Server is registered in RMI Registry.");
        } catch (RemoteException e) {
            System.err.println("Failed to start the server: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
