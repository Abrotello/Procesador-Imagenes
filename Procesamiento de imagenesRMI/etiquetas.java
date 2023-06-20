import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;


public class etiquetas extends JLabel {

    //Objeto
    funcionServer fServer;
    
    static JLabel tiempoSecuencial = new JLabel("Tiempo de ejecucion secuencial: ");
    static JLabel tiempoForkJoin = new JLabel("Tiempo de ejecucion ForkJoin: ");
    static JLabel tiempoExecutorService = new JLabel("Tiempo de ejecucion ExecutorService: ");

    //Etiqueta de las imagenes
    static JLabel imagenNormal = new JLabel("Sin imagen");
    static JLabel imagenAjusteCanal = new JLabel("Sin imagen");
    static JLabel imagenGris = new JLabel("Sin imagen");

    //FileChooser
    JFileChooser imagenSeleccionada = new JFileChooser("");

    //Panel
    JPanel panel = new JPanel();
    JPanel panelImagenes = new JPanel();

    //Variables
    private String rutaArchivo = null;
    public BufferedImage imagenOriginal = null;
    public BufferedImage imagenRMI = null;

    public etiquetas() {}
    public etiquetas(funcionServer funcionServer_){this.fServer = funcionServer_;}

    protected Component disenoEtiquetas() {

        //Tiempo secuencial
        etiquetas.tiempoSecuencial.setForeground(Color.BLACK);
        etiquetas.tiempoSecuencial.setHorizontalAlignment(CENTER);

        //Tiempo ForkJoin
        etiquetas.tiempoForkJoin.setForeground(Color.BLACK);
        etiquetas.tiempoForkJoin.setHorizontalAlignment(CENTER);

        //Tiempo ExecutorService
        etiquetas.tiempoExecutorService.setForeground(Color.BLACK);
        etiquetas.tiempoExecutorService.setHorizontalAlignment(CENTER);

        
        panel.add(tiempoSecuencial);
        panel.add(tiempoForkJoin);
        panel.add(tiempoExecutorService);

        return panel;
    }

    protected Component disenoImagenes() {

        //Imagenes
        etiquetas.imagenNormal.setHorizontalAlignment(CENTER);
        etiquetas.imagenNormal.setBorder(new LineBorder(Color.BLACK));
        etiquetas.imagenNormal.setPreferredSize(new Dimension(550, 550));

        etiquetas.imagenAjusteCanal.setHorizontalAlignment(CENTER);
        etiquetas.imagenAjusteCanal.setBorder(new LineBorder(Color.BLACK));
        etiquetas.imagenAjusteCanal.setPreferredSize(new Dimension(550, 550));

        etiquetas.imagenGris.setHorizontalAlignment(CENTER);
        etiquetas.imagenGris.setBorder(new LineBorder(Color.BLACK));
        etiquetas.imagenGris.setPreferredSize(new Dimension(550, 550));

        panelImagenes.add(imagenNormal);
        panelImagenes.add(imagenAjusteCanal);
        panelImagenes.add(imagenGris);

        return panelImagenes;
    }

    protected void leerImagen() throws RemoteException{
        ImageIcon imagenIcon = new ImageIcon();
        Image imgEscalada;
        Icon icono;
    
        imagenSeleccionada.setDialogTitle("Selecciona una imagen");
        imagenSeleccionada.setCurrentDirectory(new File("F:/Ceti"));
        
        FileNameExtensionFilter filtroImagenes = new FileNameExtensionFilter("JPG", "jpg");
        imagenSeleccionada.addChoosableFileFilter(filtroImagenes);

        
        int resultado = imagenSeleccionada.showOpenDialog(imagenSeleccionada);
        
        if(resultado == JFileChooser.APPROVE_OPTION) {
            rutaArchivo = imagenSeleccionada.getSelectedFile().getAbsolutePath();
            File archivoSeleccionado = imagenSeleccionada.getSelectedFile();
            
            if(archivoSeleccionado == null || (!archivoSeleccionado.getName().contains(".jpg"))) {
                JOptionPane.showMessageDialog(this, "Archivo no valido", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                //imagenOriginal es la imagen seleccionada por el usuario
                imagenOriginal = ImageIO.read(archivoSeleccionado);

                serializar serializar = new serializar(imagenOriginal);
                System.out.println(imagenOriginal.toString());
                System.out.println(serializar + "\t1");
                funcionRemota(serializar);
                
            } catch (Exception e) {
                   System.out.println("No se pudo cargar la imagen");
            }

            etiquetas.imagenNormal.setText("");
            imagenIcon = new ImageIcon(rutaArchivo);
            imgEscalada = imagenIcon.getImage().getScaledInstance(etiquetas.imagenNormal.getWidth(), etiquetas.imagenNormal.getHeight(), Image.SCALE_SMOOTH);
            icono = new ImageIcon(imgEscalada);
            etiquetas.imagenNormal.setIcon(icono);

            System.out.println(archivoSeleccionado.toString());
            
        }
    }

    public void setImagenGris(BufferedImage imagen) {
        Icon icono;

        etiquetas.imagenGris.setText("");
        icono = new ImageIcon(imagen.getScaledInstance(etiquetas.imagenGris.getWidth(),etiquetas.imagenGris.getHeight(), Image.SCALE_SMOOTH));
        etiquetas.imagenGris.setIcon(icono);
    }

    public void setImagenAjusteCanal(BufferedImage imagen) {
        Icon icono;

        etiquetas.imagenAjusteCanal.setText("");
        icono = new ImageIcon(imagen.getScaledInstance(etiquetas.imagenAjusteCanal.getWidth(),etiquetas.imagenAjusteCanal.getHeight(), Image.SCALE_SMOOTH));
        etiquetas.imagenAjusteCanal.setIcon(icono);
    }
    
    public BufferedImage getImagen() {
        return this.imagenRMI;
    }


    public void funcionRemota(serializar serializar_) throws RemoteException {
        serializar ser;
        funcionesCliente funcionesCliente = new funcionesCliente(serializar_, fServer);
        ser = funcionesCliente.getImagenJunta();
        try {
            imagenRMI = ser.getBufferedImage();
        } catch (IOException e) {
            System.out.println("Error RMI");
        } 
    }
}
