import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class implementacionServer extends UnicastRemoteObject implements funcionServer {

    BufferedImage imagen1 = null, imagen2 = null;
    int index = 0;

    protected implementacionServer() throws RemoteException {
        super();
        //System.out.println("Servidor iniciado...");
    }

    @Override
    public serializar imagenDevueltaServidor(serializar imagenServer) throws RemoteException {
        serializar ser = null;
        System.out.println(imagenServer);

        index++;
        boolean cliente1 = false, cliente2 = false;
        
        while(imagen1 == null || imagen2 == null) {

            if(index == 1) {
                try {
                    System.out.println(index);
                    this.imagen1 = imagenServer.getBufferedImage();
                    System.out.println(imagen1.toString());
                    cliente1 = true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if(index == 2) {
                try {
                    System.out.println(index);
                    this.imagen2 = imagenServer.getBufferedImage();
                    System.out.println(imagen2.toString());
                    cliente2 = true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(imagen1 != null && imagen2 != null) {
                System.out.println("Se ha roto el ciclo");
                break;
            }
        }
        
        try {
            ser = combinarImagen(imagen1, imagen2);
            System.out.println(ser);
            return ser;
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(cliente1 && !cliente2) {
            imagen1 = null;
            imagen2 = null;
            index = 0;
            return ser;
        }

        return ser;
    }

    @Override
    public serializar combinarImagen(BufferedImage imagen1_, BufferedImage imagen2_)
            throws RemoteException, IOException {
                System.out.println("Prueba");
                serializar serializar = null;
        
                int width = Math.max(imagen1_.getWidth(), imagen1_.getHeight());
                int height = Math.max(imagen1_.getHeight(), imagen2_.getWidth());
                BufferedImage combinadas = new BufferedImage(width, height, 5);

                Graphics g = combinadas.getGraphics();
                
                g.drawImage(imagen1_, 0, 0, null);
                g.drawImage(imagen2_,0, height/2, null);
                
                

                System.out.println(combinadas.toString());
                serializar = new serializar(combinadas);
                imagen1_.flush();
                imagen2_.flush();

                return serializar;
    }

}
