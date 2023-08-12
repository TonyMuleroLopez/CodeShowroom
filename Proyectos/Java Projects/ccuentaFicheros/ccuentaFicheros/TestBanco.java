package ccuentaFicheros;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class TestBanco {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		Banco banco = new Banco();
		System.out.println("introduzca nombre de Fichero:");
		String nombre = sc.nextLine();
	    File fichero = new File(nombre);
	    if(fichero.exists())
	    {
		   	try {
			   	ois=new ObjectInputStream(new FileInputStream(fichero));
		  	 	banco=(Banco)ois.readObject();
	  	 	}catch ( ClassNotFoundException e) {
	  	 		System.out.println(e.getMessage());
		    }catch (IOException e) {
		    	System.out.println(e.getMessage());
		    }
	    }
	    banco.operacionesMenu();
		try {
			oos = new ObjectOutputStream(new FileOutputStream(fichero));
			oos.writeObject(banco);
			oos.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
	}

}
