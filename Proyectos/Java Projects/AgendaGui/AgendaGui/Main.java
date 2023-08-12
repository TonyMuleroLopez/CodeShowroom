package AgendaGui;
public class Main {

	public static void main(String[] args) {
		Gui vista = new Gui();
		Controlador manejador = new Controlador(vista);
		vista.manejadorBoton(manejador);

	}

}