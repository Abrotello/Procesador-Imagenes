import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ventana extends JFrame implements ActionListener, Runnable {

    //Objetos independientes
    botones objetoBotones;
    etiquetas objetoEtiquetas;

    //Variables
    JPanel panelPrincipal = new JPanel();
    JPanel panelBotones = new JPanel();
    JPanel panelTiempos = new JPanel();
    JPanel panelImagenes = new JPanel();

    public ventana(funcionServer fServer_) {
        objetoBotones = new botones();
        objetoEtiquetas = new etiquetas(fServer_);
        System.out.println(fServer_);
    }

    protected void iniciarComponentes() {
        //Ventana
        setTitle("Procesamiento de imagenes");
        setSize(1020, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);

        //ActionListener
        objetoBotones.btnSeleccionarImagen.addActionListener(this);
        objetoBotones.btnAplicarFiltroSecuencial.addActionListener(this);
        objetoBotones.btnAplicarFiltroForkJoin.addActionListener(this);
        objetoBotones.btnAplicarFiltroExeceutorService.addActionListener(this);
        objetoBotones.btnLimpiar.addActionListener(this);
        objetoBotones.btnAplicarFiltroSecuencial.setEnabled(false);
        objetoBotones.btnAplicarFiltroForkJoin.setEnabled(false);
        objetoBotones.btnAplicarFiltroExeceutorService.setEnabled(false);

        //Agregar paneles
        this.getContentPane().add(panelPrincipal);

        panelPrincipal.add(objetoEtiquetas.disenoEtiquetas());
        panelPrincipal.add(objetoEtiquetas.disenoImagenes());
        panelPrincipal.add(objetoBotones.disenoBotones());

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == objetoBotones.btnSeleccionarImagen) {
            try {
                objetoEtiquetas.leerImagen();
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
            objetoBotones.btnAplicarFiltroSecuencial.setEnabled(true);
            objetoBotones.btnAplicarFiltroForkJoin.setEnabled(true);
            objetoBotones.btnAplicarFiltroExeceutorService.setEnabled(true);
        }
        if(e.getSource() == objetoBotones.btnAplicarFiltroSecuencial) {
            etiquetas.imagenAjusteCanal.setIcon(null);
            etiquetas.imagenGris.setIcon(null);
            objetoBotones.metodoSecuencial(objetoEtiquetas.getImagen());
        } 
        if(e.getSource() == objetoBotones.btnAplicarFiltroForkJoin) {
            etiquetas.imagenAjusteCanal.setIcon(null);
            etiquetas.imagenGris.setIcon(null);
            objetoBotones.metodoForkJoin(objetoEtiquetas.getImagen());
        }
        if(e.getSource() == objetoBotones.btnAplicarFiltroExeceutorService) {
            etiquetas.imagenAjusteCanal.setIcon(null);
            etiquetas.imagenGris.setIcon(null);
            objetoBotones.metodoExecutorService(objetoEtiquetas.getImagen());
        }
        if(e.getSource() == objetoBotones.btnLimpiar) {
            objetoBotones.limpiar();
            objetoBotones.btnSeleccionarImagen.setEnabled(true);
        }
    }
    
    @Override
    public void run() {
        iniciarComponentes();
    }
}
