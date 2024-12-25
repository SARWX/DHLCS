import java.math.BigInteger;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServer extends Remote {
	public void m() throws RemoteException;
	public boolean isPrime(BigInteger number) throws RemoteException;
}
