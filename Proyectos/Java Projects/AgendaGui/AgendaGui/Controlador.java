package AgendaGui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Controlador implements ActionListener{
	
	Gui vista;
	Agenda agenda;
	File fichero;
	
	public Controlador(Gui vista) {
		this.vista=vista;
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==vista.añadir ) {
			if(vista.ficheroSel)
				nuevoContacto();
			else
				JOptionPane.showMessageDialog(vista.ventana, "No se ha seleccionado un fichero");
		}else if(e.getSource()==vista.eliminar ) {
			if(vista.ficheroSel)
				eliminarContacto();
			else
				JOptionPane.showMessageDialog(vista.ventana, "No se ha seleccionado un fichero");
		}else if (e.getSource()==vista.editar ) {
			if(vista.ficheroSel)
				editarContacto();
			else
				JOptionPane.showMessageDialog(vista.ventana, "No se ha seleccionado un fichero");
		}else if (e.getSource() == vista.buscar){
			if(vista.ficheroSel)
				consultarContacto();
			else
				JOptionPane.showMessageDialog(vista.ventana, "No se ha seleccionado un fichero");
		}else if (e.getSource() == vista.fichero) {
				seleccionarFichero();
		}else if (e.getSource()== vista.guardar ){
			if(vista.ficheroSel)
				guardarFichero();
			else
				JOptionPane.showMessageDialog(vista.ventana, "No se ha seleccionado un fichero");
		}
	}
	
	public void seleccionarFichero() {
		fichero = new File("DatosAgenda");
		ObjectInputStream ois = null;
		if(fichero.exists()) {
			try {
				ois = new ObjectInputStream(new FileInputStream("Datos"));
				vista.ficheroSel = true;
				try {
					agenda = (Agenda) ois.readObject();
					vista.ficheroSel=true;
					ordenarTabla();
				} catch (ClassNotFoundException e) {
					JOptionPane.showMessageDialog(vista.ventana, "Error Class Not Found");
				}
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(vista.ventana, "FileNotFound Exception");
			} catch (IOException e) {
				JOptionPane.showMessageDialog(vista.ventana, "Error Entrada Salida 1");
			}
			
		}else {
			try {
				fichero.createNewFile();
				vista.ficheroSel=true;
				JOptionPane.showMessageDialog(vista.ventana, "Se ha creado una nueva agenda");
			} catch (IOException e) {
				JOptionPane.showMessageDialog(vista.ventana, "Error Entrada Salida 2");
			}
			agenda = new Agenda();
		}
	}
	
	public void guardarFichero() {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("Datos"));
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(vista.ventana, "Error Entrada Salida");
		}
	     try {
			oos.writeObject(agenda);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(vista.ventana, "Error Entrada Salida");
		}
	     try {
			oos.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(vista.ventana, "Error Entrada Salida");
		}
	}
	
	public void nuevoContacto() {
		Contacto contacto = introDatos();
		int posicion = busquedaSecuencial(contacto.getNombre());
		boolean existe = (posicion != -1);
		if(existe) 
		{
			JOptionPane.showMessageDialog(vista.ventana,"Contacto ya existente");
		}
		else {
			anadir(contacto);
		}
		ordenarTabla();
	}
	
	public Contacto introDatos() {
		JOptionPane.showMessageDialog(vista.ventana,"Introduzca los datos del contacto: ");
		String nombre = JOptionPane.showInputDialog(vista.ventana,"Nombre: ", JOptionPane.QUESTION_MESSAGE);
		String telefono = JOptionPane.showInputDialog(vista.ventana,"Telefono: ", JOptionPane.QUESTION_MESSAGE);
		String email = JOptionPane.showInputDialog(vista.ventana,"E-mail: ", JOptionPane.QUESTION_MESSAGE);
		return new Contacto(nombre, telefono, email);
	}
	
	public int busquedaSecuencial(String nombre) {
		for(int i = 0; i < agenda.numElem; i++)
		{
			if(agenda.array[i].getNombre().equals(nombre))
			{
				return i;
			}
		}
		return -1;
	}
	
	public void anadir(Contacto contacto){
		agenda.unElementoMas(agenda.array);
		agenda.insertar(agenda.numElem -1, contacto);
	}
	
	public void ordenarTabla() {
		if(agenda.numElem>1) {
			vaciarJTable();
		}
		agenda.ordena();
		Contacto contacto = null;
		for(int i = 0; i < agenda.array.length ;i++) {
			contacto = agenda.array[i];
			vista.modelo.insertRow(i+1, new Object[] {contacto.getNombre(),contacto.getTelefono(),contacto.getEmail()} );
		}
		
	}
	
	public void vaciarJTable() {
		if(!agenda.agendaVacia()) {
			while(vista.modelo.getRowCount()>1) {
				vista.modelo.removeRow(1);
			}	
		}
	}
	
	public void eliminarContacto() {
		if(agenda.agendaVacia()){
			JOptionPane.showMessageDialog(vista.ventana,"La agencia está vacía");
			
		}
		else {
			String nombre = JOptionPane.showInputDialog(vista.ventana,"Introduce el nombre del contacto", JOptionPane.QUESTION_MESSAGE);
			int posicion = busquedaSecuencial(nombre);
			boolean existe = (posicion != -1);
			if(existe) 
			{
				agenda.marcar(posicion); 
				vista.modelo.removeRow(posicion+1);
				JOptionPane.showMessageDialog(vista.ventana,"El registro ha sido eliminado.");
			}else 
				{
				JOptionPane.showMessageDialog(vista.ventana,"El contacto no existe.");
				}
		}
	}
	
	
	public void editarContacto() {
		if(agenda.agendaVacia()){
			JOptionPane.showMessageDialog(vista.ventana,"La agencia está vacía");
		}
		String nombre = JOptionPane.showInputDialog(vista.ventana,"Introduce el nombre del contacto", JOptionPane.QUESTION_MESSAGE);
		int posicion = busquedaSecuencial(nombre);
		boolean existe = (posicion != -1);
		if(existe){
			String eleccion = JOptionPane.showInputDialog(vista.ventana,"Elija qué quiere modificar: \n1. Nombre \n2. Telefono \n3. Email", JOptionPane.QUESTION_MESSAGE);
			if (eleccion.equals("1")) {
				String nombreNuevo = JOptionPane.showInputDialog(vista.ventana,"Introduce el nuevo nombre: ", JOptionPane.QUESTION_MESSAGE);
				agenda.array[posicion].setNombre(nombreNuevo);
				vista.modelo.removeRow(posicion+1);
				vista.modelo.insertRow(posicion+1, new Object[] {nombreNuevo,agenda.array[posicion].getTelefono(),agenda.array[posicion].getEmail()} );
				vaciarJTable();
				ordenarTabla();
			}else if (eleccion.equals("2")) {
				String telefonoNuevo = JOptionPane.showInputDialog(vista.ventana,"Introduce el nuevo telefono: ", JOptionPane.QUESTION_MESSAGE);
				agenda.array[posicion].setTelefono(telefonoNuevo);
				vista.modelo.removeRow(posicion+1);
				vista.modelo.insertRow(posicion+1, new Object[] {agenda.array[posicion].getNombre(),telefonoNuevo,agenda.array[posicion].getEmail()} );
			}else if (eleccion.equals("3")) {
				String emailNuevo = JOptionPane.showInputDialog(vista.ventana,"Introduce el nuevo e-mail: ", JOptionPane.QUESTION_MESSAGE);
				agenda.array[posicion].setEmail(emailNuevo);
				vista.modelo.removeRow(posicion+1);
				vista.modelo.insertRow(posicion+1, new Object[] {agenda.array[posicion].getNombre(),agenda.array[posicion].getTelefono(),emailNuevo} );
			}else {
				JOptionPane.showMessageDialog(vista.ventana,"Opción no válida, pruebe de nuevo más tarde");
			}
			
		} else {
			JOptionPane.showMessageDialog(vista.ventana,"No existe el contacto");
		}
	}
	
	
	public void consultarContacto(){
		if(agenda.agendaVacia()){
			JOptionPane.showMessageDialog(vista.ventana,"La agencia está vacía");
			
		}
		else {
			String nombre = JOptionPane.showInputDialog(vista.ventana,"Introduce el nombre del contacto", JOptionPane.QUESTION_MESSAGE);
			int posicion = busquedaSecuencial(nombre);
			boolean existe = (posicion != -1);
			if(existe) {
				Contacto contacto = agenda.array[posicion];
				JOptionPane.showMessageDialog(vista.ventana, contacto.toString());
			}
			else {
				JOptionPane.showMessageDialog(vista.ventana,"No existe el contacto");
			}
		}
	}
		
}
	



	
	
