package AgendaGui;


import java.io.Serializable;
import java.util.Scanner;

/**
 * Esto es una agenda de contactos
 * */

/**
 * Plantilla para gestionar los contactos
 * @author profesor
 *
 */
public class Agenda implements Serializable
{
	/**
	 * atributos
	 */
	// Miembro dato
	Contacto array[];
	int numElem;
	
	//Constructor
	public Agenda()
	{
		numElem = 0;
		array = new Contacto[numElem];
	}
	
	/**
	 *Este metodo solicita memoria para poder guardar un nuevo 
	 *elemento, consevando todos los anteriores.
	 */
	public void unElementoMas(Contacto aux[]) {
		//Pedimos memoria para un elemento más.
		array = new Contacto[numElem + 1];
		//Copiamos los elementos anteriores en la nueva zona de memoria.
		for(int i = 0; i < numElem; i++) {
			array[i] = aux[i];
		}
		//Actualizamos el numero de elementos.
		numElem++;
	}
	
	/**
	 * Metodo para eliminar un contacto de nuestra agenda.
	 * @param Contacto
	 */
	public void unElementoMenos(Contacto aux[]) {
		//Pedimos memoria para un elemento menos.
		array = new Contacto[numElem - 1];
		//Copiamos aaquellos elementos que no estan marcados(!= null).
		//Indice del array de destino.
		int k = 0;
		for(int i = 0; i < numElem; i++) {
			if(aux[i] != null) {
				array[k] = aux[i];
				k++;
			}		
		}
		numElem--;
	}
	
	/**
	 * Metodo para introducir datos.
	 */
	public Contacto introDatos() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduzca los datos del contacto: ");
		System.out.println("Nombre: ");
		String nombre = sc.nextLine();
		System.out.println("Telefono: ");
		String telefono = sc.nextLine();
		System.out.println("Email: ");
		String email = sc.nextLine();
		return new Contacto(nombre, telefono, email);
	}
	
	/**
	 * Añadir un contacto al final de la agenda.
	 */
	public void anadir(Contacto contacto)
	{
		unElementoMas(array);
		insertar(numElem -1, contacto);
	}
	/**
	 * Este metodo inserta un contacto en la posición indicada.
	 */
	public void insertar(int pos, Contacto contacto) 
	{
		//Validamos la posición
		if(pos >= 0 && pos < numElem) {
			array[pos] = contacto;
		}
		else
			System.out.println("La posición no es válida.");
	}
	/**
	 * Hace una búsqueda secuencial en la agenda. 
	 */
	public int busquedaSecuencial(String nombre) 
	{
		for(int i = 0; i < numElem; i++)
		{
			if(array[i].getNombre().equals(nombre))
			{
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Menú de operaciones en la agenda.
	 * Visualiza el menú, valida la opción elegida y la devuelve.
	 */
	public int verMenu()
	{
		System.out.println("Menú de operaciones: ");
		System.out.println("1. Añadir contacto");
		System.out.println("2. Editar contacto");
		System.out.println("3. Eliminar contacto");
		System.out.println("4. Consultar contacto");
		System.out.println("5. Ver toda la agenda");
		System.out.println("6. Abandonar la aplicación");
		System.out.println("Elija la opción: ");
		Scanner sc = new Scanner(System.in);
		boolean opcionValida;
		int opcion;
		do {
			opcion = sc.nextInt();
			opcionValida = (opcion >= 1 && opcion <=6);
			if(!opcionValida)
				System.out.println("Error. La opción debe estar entre 1 y 6");
			
		} while(!opcionValida);
		return opcion;
	}
	
	/**
	 * Punto de entrada donde arranca la aplicación.
	 */
	public void arrancarApp() {
		boolean salir = false;
		do {
			int opcion = verMenu();
			salir = (opcion == 6);
			if(!salir) {
				switch(opcion) {
				case 1:
					nuevoContacto();
					break;
				case 2:
					editarContacto();
					break;
				case 3:
					eliminarContacto();
					break;
				case 4:
					consultarContacto();
					break;
				case 5:
					verAgendaCompleta();
					break;	
				}
			}
		}while(!salir);
		System.out.println("ha salido de la aplicación");
	}
	
	/**
	 * Ingresa un nuevo contacto a la agenda, 
	 * después de comprobar que no existe. 
	 */
	public void nuevoContacto() {
		//Introducimos los datos.
		Contacto contacto = introDatos();
		//Comprobamos si existe.
		int posicion = busquedaSecuencial(contacto.getNombre());
		boolean existe = (posicion != -1);
		if(existe) 
		{
			System.out.println("Contacto ya existente.");
		}
		else {
			anadir(contacto);
		}
	}
	/**
	 * retorna el nombre introducido  por teclado
	 */
	public  String pedirNombre()
	{
		Scanner entrada = new Scanner(System.in);
		System.out.println("introduce el nombre del contacto");
		return entrada.nextLine();
	}
	
	/**
	 * Metodo que elimina un contacto de la agenda.
	 */
	public void eliminarContacto() 
	{
		//Introducimos el nombre
		String nombre = pedirNombre();
		//Comprobamos si existe.
		int posicion = busquedaSecuencial(nombre);
		boolean existe = (posicion != -1);
		if(existe) 
		{
			marcar(posicion); 
			System.out.println("El registro ha sido eliminado.");
		}else 
			{
			System.out.println("El contacto no existe.");
			}
		
	}
	
	/**
	 * Marcamos el elemento  a eliminar.
	 */
	public void marcar(int posicion) 
	{
		array[posicion] = null;
		unElementoMenos(array);
		
	}
	
	
	/**
	 * Comprueba si la agenda esta vacia
	 */
	public boolean agendaVacia()
	{
		return (numElem == 0);
	}
	
	
	/**
	 * Metodo que consulta un contacto
	 */
	public void consultarContacto()
	{
		//Comprobamos si la agenda esta vacia
		if(agendaVacia())
		{
			System.out.println("La agenda esta vacía");
		}
		else {
			//Pedimos el nombre
			String nombre = pedirNombre();
			//Comprobamos si existe.
			int posicion = busquedaSecuencial(nombre);
			boolean existe = (posicion != -1);
			if(existe) 
			{
				Contacto contacto = array[posicion];
				//Si existe mostramos en pantalla los datos
				System.out.println(contacto);
			}
			else {
				//Si no existe
				System.out.println("No existe el contacto.");
			}
		}
	}
	
	// Creamos el metodo para editar los contactos
	
	public void editarContacto() 
	{
		//Pedimos el nombre
		String nombre = pedirNombre();
		//Comprobamos si existe.
		int posicion = busquedaSecuencial(nombre);
		boolean existe = (posicion != -1);
		if(existe)
		{
			boolean salir = false;
			do {
				System.out.println("Elige una opcion: ");
				System.out.println("1. Editar nombre");
				System.out.println("2. Editar telefono");
				System.out.println("3. Editar e-mail");
				System.out.println("4. Salir");
				Scanner sc = new Scanner (System.in);
				int opcion = sc.nextInt();
				salir = (opcion == 4);
				// Limpiamos el buffer
				sc.nextLine();
				if(!salir) {
					switch(opcion) {
					case 1:
						System.out.println("Introduce el nuevo nombre: ");
						String nombreNuevo = sc.nextLine();
						array[posicion].setNombre(nombreNuevo);
						System.out.println(array[posicion].getNombre());
						break;
					case 2:
						System.out.println("Introduce el nuevo telefono: ");
						String telefonoNuevo = sc.nextLine();
						array[posicion].setTelefono(telefonoNuevo);
						System.out.println(array[posicion].getTelefono());
						break;
					case 3:
						System.out.println("Introduce el nuevo email: ");
						String emailNuevo = sc.nextLine();
						array[posicion].setEmail(emailNuevo);
						System.out.println(array[posicion].getEmail());
						break;
					case 4:
						salir = true;
					}
				}
			} while(!salir);
			
		} else {
			System.out.println("Error, el contacto no existe");
		}
	}
	
	
	/**
	 * Metodo para ver toda la agenda
	 */
	
	public void verAgendaCompleta( ) 
	{
		// Comprobamos si la agenda esta vacía
		if (agendaVacia()) {
			System.out.println("La agenda esta vacia");
		} else 
		{
			 ordena();
			 verAgenda();
		}
		 
	}
	/**ordena la agenda alfabéticamente por el nombe del contacto
	 * 
	 */
	public void ordena()
	{
		boolean hayIntercambio;
		do
		{
			hayIntercambio = false;
			for(int i = 0; i < array.length-1; i++)
			{
				
				if(array[i].compareTo(array[i+1]) > 0)
				{
					//Intercambiamos los elementos
					Contacto aux = array[i+1];
					array[i+1] = array[i];
					array[i] = aux;
					hayIntercambio = true;
				}
			}
		}while(hayIntercambio);
	}
	/**visualiza el contenido de la agenda
	 */
	public void verAgenda()
	{
		System.out.println("LISTADO DE LA AGENDA DE CONTACTOS ORDENADO");
		for (int i = 0; i < numElem; i++)
		{
			System.out.println(array[i]);
		}
	}
}