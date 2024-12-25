import java.math.BigInteger;
//import java.util.ArrayList;
//import java.util.List;
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

	public boolean isPrime(BigInteger n) {
        if (n.compareTo(BigInteger.ONE) <= 0) {
            return false; // filter 1 and negative
        }

        // Check 2, 3 and 5
        if (n.mod(BigInteger.TWO).equals(BigInteger.ZERO)) return n.equals(BigInteger.TWO);
        if (n.mod(BigInteger.valueOf(3)).equals(BigInteger.ZERO)) return n.equals(BigInteger.valueOf(3));
        if (n.mod(BigInteger.valueOf(5)).equals(BigInteger.ZERO)) return n.equals(BigInteger.valueOf(5));

        // Increment cycle
        int[] inc = {4, 2, 4, 6, 2, 6};
        BigInteger k = BigInteger.valueOf(7);
        int i = 0;

        // First position is k = 7
        while (k.multiply(k).compareTo(n) <= 0) {
            if (n.mod(k).equals(BigInteger.ZERO)) {
                return false; // Not prime
            }

            // Increment k
            k = k.add(BigInteger.valueOf(inc[i]));
            i = (i + 1) % inc.length; // Keep cycle order
        }

        return true; // The number is prime
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
