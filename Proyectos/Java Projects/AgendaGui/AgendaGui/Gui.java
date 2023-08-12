package AgendaGui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Gui {
	JButton añadir;
    JButton eliminar;
    JButton editar;
    JButton buscar;
    JButton guardar;
    JButton fichero;
    JTable tabla;
    JPanel panel;
    JFrame ventana;
    DefaultTableModel modelo;
    boolean ficheroSel = false;
    JPanel panelSur;
    JPanel panelInterno;
    JScrollPane scroll;
    
    public Gui(){
    	crearComponentes();
    	colocarComponentes();
    }
     
    public void crearComponentes() {
        añadir = new JButton("Añadir contacto");
        eliminar = new JButton("Eliminar contacto");
        editar = new JButton("Editar contacto");
        buscar = new JButton("Buscar contacto");
        fichero = new JButton("Cargar Agenda");
        guardar = new JButton("Guardar datos");
        modelo = new DefaultTableModel();
        tabla = new JTable(modelo);
        ventana = new JFrame("Agenda");
        panel = new JPanel();
        panelSur = new JPanel();
        panelInterno = new JPanel(new GridLayout(3,2));
        scroll = new JScrollPane(tabla);
   }
    
    public void colocarComponentes() {
        ventana.setSize(400, 400); 
        ventana.setResizable(true); 
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);
        modelo.addColumn(0, new Object[] {"Nombre"});
        modelo.addColumn(0, new Object[] {"Telefono"});
        modelo.addColumn(0, new Object[] {"Email"});
        tabla.enable(false);
        panel.add(tabla);
        panelInterno.add(añadir);
        panelInterno.add(editar);
        panelInterno.add(eliminar);
        panelInterno.add(buscar);
        panelInterno.add(fichero);
        panelInterno.add(guardar);
        panelSur.add(panelInterno);
        panel.add(scroll);
        ventana.add(scroll);
        ventana.add(panel,BorderLayout.CENTER);
        ventana.add(panelSur,BorderLayout.SOUTH);
        ventana.setVisible(true);
   }
    
    public void manejadorBoton(Controlador controlador) {
    	añadir.addActionListener(controlador);
    	eliminar.addActionListener(controlador);
    	editar.addActionListener(controlador);
    	buscar.addActionListener(controlador);
    	fichero.addActionListener(controlador);
    	guardar.addActionListener(controlador);
    }
    
}