import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

public class serializar implements Serializable {
    
    private byte[] imagenBytes;

    public serializar(BufferedImage imagen) throws IOException {
        this.imagenBytes = convertirToByte(imagen);
        //System.out.println(imagenBytes);
    }

    private byte[] convertirToByte(BufferedImage imagen) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(imagen, "jpg", baos);
        return baos.toByteArray();
    }

    public BufferedImage getBufferedImage() throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(imagenBytes);
        BufferedImage bi = ImageIO.read(bais);
        System.out.println(bi.toString());
        return bi;
    }
}
