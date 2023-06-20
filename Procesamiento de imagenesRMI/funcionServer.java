import java.awt.image.BufferedImage;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface funcionServer extends Remote {

    serializar imagenDevueltaServidor(serializar imagenServer) throws RemoteException;
    serializar combinarImagen(BufferedImage imagen1_, BufferedImage imagen2_) throws RemoteException, IOException;
}