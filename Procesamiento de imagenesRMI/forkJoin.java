import java.util.concurrent.RecursiveAction;
import java.awt.image.BufferedImage;

public class forkJoin  extends RecursiveAction {

    //Objetos de las clases
    private escalaGrises objetoEscalaGrises = new escalaGrises();
    private ajusteCanal objetoAjusteCanal = new ajusteCanal();
    private etiquetas objetoEtiquetas = new etiquetas();

    //Variables
    public BufferedImage imagen;

    public forkJoin() {}

    public forkJoin(BufferedImage imagen) {
        this.imagen = imagen;
    }

    @Override
    protected void compute() {
        retornoGris();
        retornoAjusteCanal();
    }

    public void retornoGris() {
        objetoEtiquetas.setImagenGris(objetoEscalaGrises.convertirEscalaGrises(imagen));
    }

    public void retornoAjusteCanal() {
        objetoEtiquetas.setImagenAjusteCanal(objetoAjusteCanal.convertirRojo(imagen));
    }
    
}
