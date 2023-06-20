import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class funcionesCliente extends UnicastRemoteObject implements intermediario {

    funcionServer server;
    serializar serializar;

    protected funcionesCliente(serializar serializar_, funcionServer fServer_) throws RemoteException {
        super();
        this.serializar = serializar_;
        this.server = fServer_;

        System.out.println(serializar_ + "\n" + fServer_);
    }

    @Override
    public serializar getImagenJunta() throws RemoteException {
        System.out.println("Imagen Junta");

        serializar ser = null;
        
        ser = server.imagenDevueltaServidor(this.serializar);

        return ser;
    }
}
