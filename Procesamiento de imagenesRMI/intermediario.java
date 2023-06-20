import java.rmi.Remote;
import java.rmi.RemoteException;

public interface intermediario extends Remote {
    
    public serializar getImagenJunta() throws RemoteException;
    
}
