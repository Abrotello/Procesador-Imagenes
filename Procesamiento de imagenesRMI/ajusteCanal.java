import java.awt.Color;
import java.awt.image.BufferedImage;

public class ajusteCanal {
    
    public BufferedImage imagen;

    public ajusteCanal() {}

    public ajusteCanal(BufferedImage imagen) {
        this.imagen = imagen;
    }

    protected BufferedImage convertirVerde(BufferedImage imagen) {
        this.imagen = new BufferedImage(imagen.getWidth(), imagen.getHeight(), 2);
        Color color;
        int Red = 0, Green = 255, Blue = 0;
        int alpha;

        for(int columnasPixeles = 0; columnasPixeles < imagen.getWidth(); columnasPixeles++) {
            for(int filasPixeles = 0; filasPixeles < imagen.getHeight(); filasPixeles++) {
                color = new Color(imagen.getRGB(columnasPixeles, filasPixeles), true);
                alpha = color.getAlpha();
                this.imagen.setRGB(columnasPixeles, filasPixeles, new Color(Red, Green, Blue, alpha).getRGB());
            }
        }
        System.out.println("Se aplico Ajuste de Canal Verde");
        return this.imagen;
    }

    protected BufferedImage convertirAzul(BufferedImage imagen) {
        this.imagen = new BufferedImage(imagen.getWidth(), imagen.getHeight(), 2);
        Color color;
        int Red = 0, Green = 0, Blue = 255;
        int alpha;

        for(int columnasPixeles = 0; columnasPixeles < imagen.getWidth(); columnasPixeles++) {
            for(int filasPixeles = 0; filasPixeles < imagen.getHeight(); filasPixeles++) {
                color = new Color(imagen.getRGB(columnasPixeles, filasPixeles), true);
                alpha = color.getAlpha();
                this.imagen.setRGB(columnasPixeles, filasPixeles, new Color(Red, Green, Blue, alpha).getRGB());
            }
        }
        System.out.println("Se aplico Ajuste de Canal Azul");
        return this.imagen;
        
    }

    protected BufferedImage convertirRojo(BufferedImage imagen) {
        this.imagen = new BufferedImage(imagen.getWidth(), imagen.getHeight(), 2);
        Color color;
        int Red = 255, Green = 0, Blue = 0;
        int alpha;

        for(int columnasPixeles = 0; columnasPixeles < imagen.getWidth(); columnasPixeles++) {
            for(int filasPixeles = 0; filasPixeles < imagen.getHeight(); filasPixeles++) {
                color = new Color(imagen.getRGB(columnasPixeles, filasPixeles), true);
                alpha = color.getAlpha();
                this.imagen.setRGB(columnasPixeles, filasPixeles, new Color(Red, Green, Blue, alpha).getRGB());
            }
        }
        System.out.println("Se aplico Ajuste de Canal Rojo");
        return this.imagen;
    }
}
