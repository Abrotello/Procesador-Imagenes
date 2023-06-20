import java.awt.image.BufferedImage;

public class secuencial {

    //Objetos de las clases
    private escalaGrises objetoEscalaGrises = new escalaGrises();
    private ajusteCanal objetoAjusteCanal = new ajusteCanal();
    private etiquetas objetoEtiquetas = new etiquetas();

    //Variables
    public BufferedImage imagenSecuencial;

    public secuencial() {}

    public secuencial(BufferedImage imagen) {
        this.imagenSecuencial = imagen;
        System.out.println(imagen.toString());
    }

    public void retornoGris() {
        objetoEtiquetas.setImagenGris(objetoEscalaGrises.convertirEscalaGrises(imagenSecuencial));
    }

    public void retornoAjusteCanal() {
        objetoEtiquetas.setImagenAjusteCanal(objetoAjusteCanal.convertirVerde(imagenSecuencial));
    }
}
