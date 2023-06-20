import java.awt.image.BufferedImage;
public class executorService implements Runnable {

    //Objetos de las clases
    private escalaGrises objetoEscalaGrises = new escalaGrises();
    private ajusteCanal objetoAjusteCanal = new ajusteCanal();
    private etiquetas objetoEtiquetas = new etiquetas();

    //Variables
    public BufferedImage imagen;

    public executorService() {}

    public executorService(BufferedImage imagen) {
        this.imagen = imagen;
    }
    
    @Override
    public void run() {
        retornoGris();
        retornoAjusteCanal();
    }

    public void retornoGris() {
        objetoEtiquetas.setImagenGris(objetoEscalaGrises.convertirEscalaGrises(imagen));
    }

    public void retornoAjusteCanal() {
        objetoEtiquetas.setImagenAjusteCanal(objetoAjusteCanal.convertirAzul(imagen));
    }
}
