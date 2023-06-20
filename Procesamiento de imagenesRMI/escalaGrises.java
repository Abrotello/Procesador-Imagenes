import java.awt.Color;
import java.awt.image.BufferedImage;

public class escalaGrises {

    public BufferedImage imagen;

    public escalaGrises() {}

    public escalaGrises(BufferedImage imagen) {
        this.imagen = imagen;
    }

    protected BufferedImage convertirEscalaGrises(BufferedImage imagen) {
        this.imagen = new BufferedImage(imagen.getWidth(), imagen.getHeight(), 2);
        Color color;
        int Red,Green,Blue;
        int gris;

        for(int columnasPixeles = 0; columnasPixeles < imagen.getWidth(); columnasPixeles++) {
            for(int filasPixeles = 0; filasPixeles < imagen.getHeight(); filasPixeles++) {
                color = new Color(imagen.getRGB(columnasPixeles, filasPixeles), true);
                Red = color.getRed();
                Green = color.getGreen();
                Blue = color.getBlue();
                int alpha = color.getAlpha();
                gris = (Red + Green + Blue) / 3;
                this.imagen.setRGB(columnasPixeles, filasPixeles, new Color(gris, gris, gris, alpha).getRGB());
            }
        }
        System.out.println("Se aplico Escala de Grises");
        return this.imagen;
    }
}
