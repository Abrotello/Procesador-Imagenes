import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class servidor {
    public static final int PUERTO = 8000;
    public static void main(String[] args) {
        try {

            Registry rmi = LocateRegistry.createRegistry(PUERTO);

            rmi.rebind("Imagenes", (Remote) new implementacionServer());
            System.out.println("Servidor activo...");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}