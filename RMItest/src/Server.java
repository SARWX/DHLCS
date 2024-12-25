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

    public boolean isPrime(int number) {
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
        return true; // Number is prime
    }
	
	public static void main(String[] args) throws RemoteException {
		System.out.println("Server started...");
		Server s = new Server();
		Registry re = LocateRegistry.createRegistry(9001);
		re.rebind("server", s);
	}
}
