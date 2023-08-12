package ccuentaFicheros;

import java.io.Serializable;
import java.util.Scanner;

public class Banco implements Serializable{
	
	private int numCuentas;
	CCuenta cuenta [];
	
	public Banco() {
		int numCuentas = 0;
		cuenta = new CCuenta[numCuentas];
	}
	
	public void unElementoMas(CCuenta cuentaAux []) {
		cuenta = new CCuenta[numCuentas + 1];
		for(int i = 0; i < numCuentas; i++) {
			cuenta[i]=cuentaAux[i];
		}
		numCuentas++;
	}
	
	public void insertarEn(CCuenta cuenta1,int posicion) {
		if (posicion >= 0 && posicion < numCuentas) {
			cuenta [posicion]= cuenta1;
		}else {
			System.err.println("ERROR: Posición incorrecta");
		}
	}
	
	public CCuenta getCCuenta(int posicion) {
		if (posicion >= 0 && posicion < numCuentas) {
			return cuenta [posicion];
		}else {
			return null;
		}
	}
	
	public boolean arrayVacio() {
		return numCuentas == 0;
	}
	
	public void añadir(CCuenta cuenta1) {
		unElementoMas(cuenta);
		insertarEn(cuenta1, numCuentas-1);
	}
	
	public boolean eliminar(String numCuenta1) {
		for(int i = 0; i < numCuentas; i++) {
			if(cuenta[i].getCuenta().equals(numCuenta1)) {
				cuenta[i] = null; 
				 unElementoMenos(cuenta);
				 return true;
			}
		}
		return false;
	}
	
	public void unElementoMenos(CCuenta cuentaAux[]) {
		cuenta= new CCuenta[numCuentas-1];
		int j = 0;
		for(int i = 0; i < numCuentas; i++) {
			if(cuentaAux[i] != null) {
				cuenta[j]=cuentaAux[i];
				j++;
			}
		}
		numCuentas--;
	}
	
	public int buscar(String nombre1) {
		for(int i = 0; i < numCuentas; i++) {
			if(cuenta[i].getCuenta().equals(nombre1)) {
				return i;
			}
		}
		return -1;
	}
	
	public int verMenu() {
		Scanner sc = new Scanner(System.in);
		int opcion;
		boolean opcionValida;
		System.out.println("\n 1- CONSULTAR SALDO \n 2- INGRESO \n 3- REINTEGRO \n 4- ALTAS \n 5- BAJAS \n 6- MANTENIMIENTO \n 7- SALIR \n\n Elija una opción: ");
		do {
			opcion = sc.nextInt();
			opcionValida = (opcion > 0 && opcion < 8);
			if(!opcionValida) {
				System.err.println("ERROR: opción no válida");
			}
		}while(!opcionValida);
		
		return opcion;
	}
	
	public void operacionesMenu() {
		int opcion;
		boolean salir;
	do {
		opcion = verMenu();
		salir = (opcion == 7);
			if (!salir) {
				switch (opcion) {
					case 1:
						consultarSaldo();
						break;
					case 2:
						ingreso();
						break;
					case 3: 
						reintegro();
						break;
					case 4:
						altas();
						break;
					case 5:
						bajas();
						break;
					case 6:
						mantenimiento();
						break;
					default:
						break;
				}
			}
	}while(!salir);
	}

	public void consultarSaldo() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduzca su número de cuenta: ");
		String numCuenta = sc.nextLine();
		if (buscar(numCuenta)>=0) {
			System.out.println("Número de Cuenta: " + cuenta[buscar(numCuenta)].getCuenta());
			System.out.println("Nombre: " + cuenta[buscar(numCuenta)].getNombre());
			System.out.println("Saldo: " + cuenta[buscar(numCuenta)].getSaldo());
		}else {
			System.out.println("La cuenta no existe");
		}
	}
	
	public void ingreso() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduzca su número de cuenta: ");
		String numCuenta1 = sc.nextLine();
		if(buscar(numCuenta1)>=0) {
		System.out.println("Introduzca la cantidad que desea añadir: ");
		double cantidad = sc.nextDouble();
		cuenta[buscar(numCuenta1)].ingreso(cantidad);
		}else {
			System.err.println("La cuenta no existe");
		}
	}
	
	public void reintegro() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduzca su número de cuenta: ");
		String numCuenta1 = sc.nextLine();
		if(buscar(numCuenta1)>=0) {
		System.out.println("Introduzca la cantidad que desea retirar: ");
		double cantidad = sc.nextDouble();
		cuenta[buscar(numCuenta1)].reintegro(cantidad);
		}else {
			System.err.println("La cuenta no existe");
			System.out.println("\n");
		}
	}
	
	public void altas() {
		Scanner sc = new Scanner(System.in);
		System.out.println("\n1-CUENTA AHORRO \n2- CUENTA CORRIENTE \n3- CUENTA CORRIENTE CON INTERESES \nElija un tipo de cuenta");
		int opcion = sc.nextInt();
		leerDatos(opcion);	
	}

	public void leerDatos(int opciones) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduzca su nombre");
		String nombre = sc.nextLine();
		System.out.println("Introduzca el número de Cuenta");
		String numCuenta = sc.nextLine();
		System.out.println("Introduzca el saldo");
		double saldo = sc.nextDouble();
		System.out.println("Introduzca el tipo de interes");
		double interes = sc.nextDouble();
		
		switch (opciones) {
		case 1:
			System.out.println("Introduzca la cuota de mantenimiento");
			double cuota = sc.nextDouble();
			CCuenta cuenta1 = new CCuentaAhorro(nombre, numCuenta,interes,saldo, cuota);
			añadir(cuenta1);
			break;
		case 2:
			System.out.println("Introduzca el importe por transaccion");
			double importe = sc.nextDouble();
			System.out.println("Introduzca las transacciones exentas");
			int trans = sc.nextInt();
			CCuenta cuenta2 = new CCuentaCorriente(nombre, numCuenta, interes, saldo, importe, trans);
			añadir(cuenta2);
			break;
		case 3:
			System.out.println("Introduzca el importe por transaccion");
			double importe2 = sc.nextDouble();
			System.out.println("Introduzca las transacciones exentas");
			int trans2 = sc.nextInt();
			CCuenta cuenta3 = new CCuentaCorrienteConIn(nombre, numCuenta, interes, saldo, importe2, trans2);
			añadir(cuenta3);
			break;
		default:
			System.err.println("Opción no válida");
			break;
		}
	}
	

	public void bajas() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduzca su número de cuenta: ");
		String numCuenta1 = sc.nextLine();
		if(eliminar(numCuenta1)) {
			System.out.println("La cuenta ha sido eliminidada");
		}else {
			System.err.println("La cuenta no existe");
			System.out.println("\n");
		}
	}	


	private void mantenimiento() {
		for (int i = 0; i<numCuentas; i++) {
			cuenta[i].comisiones();
			System.out.println("Los interes de la cuenta " + cuenta[i].getCuenta() + " son " + cuenta[i].intereses());
		}
	}
	
}
