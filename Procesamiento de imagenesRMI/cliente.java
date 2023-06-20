import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class cliente {

    public static void main(String[] args) {

        try {
            Registry rmi = LocateRegistry.getRegistry("localhost", 8000);
            funcionServer fServer = (funcionServer) rmi.lookup("Imagenes");
            new Thread(new ventana(fServer)).start();

        } catch (Exception e) {
            System.out.println("Error en el main");
            System.out.println(e.getMessage());
        }
    }
}
