import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class botones implements Serializable {

    JButton btnSeleccionarImagen = new JButton("Seleccionar una imagen");
    JButton btnAplicarFiltroSecuencial = new JButton("Aplicar filtros Secuencial");
    JButton btnAplicarFiltroForkJoin = new JButton("Aplicar filtros ForkJoin");
    JButton btnAplicarFiltroExeceutorService = new JButton("Aplicar filtros ExecutorService");
    JButton btnLimpiar = new JButton("Limpiar");

    //Borders
    Border linea = new LineBorder(Color.BLACK);
    Border margen = new EmptyBorder(2, 10, 2, 10);
    Border border = new CompoundBorder(linea, margen);

    //Panel
    JPanel panel = new JPanel();

    //Imagen
    public BufferedImage imagenAConvertir;

    public botones(BufferedImage imagenRecibida) {this.imagenAConvertir = imagenRecibida; System.out.println(imagenAConvertir.toString());}

    public botones() {}

    protected Component disenoBotones() {


        //Boton seleccionar imagen
        btnSeleccionarImagen.setForeground(Color.BLACK);
        btnSeleccionarImagen.setBackground(Color.WHITE);
        btnSeleccionarImagen.setBorder(border);
        btnSeleccionarImagen.setPreferredSize(new Dimension(250,40));
        

        //Botones de aplicar filtro
        btnAplicarFiltroSecuencial.setForeground(Color.BLACK);
        btnAplicarFiltroSecuencial.setBackground(Color.WHITE);
        btnAplicarFiltroSecuencial.setBorder(border);
        btnAplicarFiltroSecuencial.setPreferredSize(new Dimension(250,40));

        btnAplicarFiltroForkJoin.setForeground(Color.BLACK);
        btnAplicarFiltroForkJoin.setBackground(Color.WHITE);
        btnAplicarFiltroForkJoin.setBorder(border);
        btnAplicarFiltroForkJoin.setPreferredSize(new Dimension(250,40));

        btnAplicarFiltroExeceutorService.setForeground(Color.BLACK);
        btnAplicarFiltroExeceutorService.setBackground(Color.WHITE);
        btnAplicarFiltroExeceutorService.setBorder(border);
        btnAplicarFiltroExeceutorService.setPreferredSize(new Dimension(250,40));

        //Boton de salir
        btnLimpiar.setForeground(Color.BLACK);
        btnLimpiar.setBackground(Color.WHITE);
        btnLimpiar.setBorder(border);
        btnLimpiar.setBounds(50, 0, 200, 50);
        btnLimpiar.setPreferredSize(new Dimension(100,40));

        //Agregar botones al panel
        panel.add(btnSeleccionarImagen);
        panel.add(btnAplicarFiltroSecuencial);
        panel.add(btnAplicarFiltroForkJoin);
        panel.add(btnAplicarFiltroExeceutorService);
        panel.add(btnLimpiar);

        return panel;
    }

    public void metodoSecuencial(BufferedImage imagenSecuencial) {
        System.out.println("Secuencial");
        long tiempoEjecucion = System.currentTimeMillis();
        secuencial objetoSecuencial = new secuencial(imagenSecuencial);
        objetoSecuencial.retornoGris();
        objetoSecuencial.retornoAjusteCanal();
        long tiempoReal = System.currentTimeMillis() - tiempoEjecucion;
        etiquetas.tiempoSecuencial.setText("Tiempo secuencial: " + tiempoReal + " milisegundos");
        imagenSecuencial.flush();
    }
    
    public void metodoForkJoin(BufferedImage imagenForkJoin) {
        System.out.println("ForkJoin");
        long tiempoEjecucion = System.currentTimeMillis();
        forkJoin objetoForkJoin = new forkJoin(imagenForkJoin);
        ForkJoinPool pool = new ForkJoinPool(15);
        pool.invoke(objetoForkJoin);
        long tiempoReal = System.currentTimeMillis() - tiempoEjecucion;
        etiquetas.tiempoForkJoin.setText("Tiempo ForkJoin: " + tiempoReal + " milisegundos");
        pool.shutdown();
        imagenForkJoin.flush();
    }
    
    public void metodoExecutorService(BufferedImage imagenExecutor) {
        System.out.println("ExecutorService");
        long tiempoEjecucion = System.currentTimeMillis();
        executorService objetoExecutorService = new executorService(imagenExecutor);
        ExecutorService executor = Executors.newFixedThreadPool(8);
        executor.execute(objetoExecutorService);
        long tiempoReal = System.currentTimeMillis() - tiempoEjecucion;
        etiquetas.tiempoExecutorService.setText("Tiempo ExecutorService: " + tiempoReal + " milisegundos");
        imagenExecutor.flush();
    }

    protected void limpiar() {
        System.out.println("Limpio");
        etiquetas.imagenAjusteCanal.setText("Sin imagen");
        etiquetas.imagenAjusteCanal.setIcon(null);
        etiquetas.imagenGris.setText("Sin imagen");
        etiquetas.imagenGris.setIcon(null);
        etiquetas.tiempoSecuencial.setText("Tiempo de ejecucion secuencial: ");
        etiquetas.tiempoForkJoin.setText("Tiempo de ejecucion ForkJoin: ");
        etiquetas.tiempoExecutorService.setText("Tiempo de ejecucion ExecutorService: ");
    }
}
