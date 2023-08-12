package AgendaGui;
import java.io.Serializable;

public class Contacto implements Serializable, Comparable
{
	private String nombre;
	private String telefono;
	private String email;
	
	
	public Contacto(String nombre, String telefono, String email)
	{
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
		
	}
	//////////////////////////////////////////
	public String getNombre()
	{
		return nombre;
	}
	/////////////////////////////////////////
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}
	////////////////////////////////////////
	public String getTelefono()
	{
		return telefono;
	}
	public void setTelefono(String telefono)
	{
		this.telefono = telefono;
	}
	//////////////////////////////////
	public String getEmail()
	{
		return email;
	}
	///////////////////////////////////
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	////////////////////////////
	@Override
	public String toString()
	{
		String retorno = "Nombre: " + nombre + "\nTeléfono: " + telefono + "\nEmail: " + email;
		return  retorno;
	}
	
	///////////////////////////////////////
	public boolean equals(Contacto contacto)
	{
		return nombre.equals(contacto.getNombre()) 
				&& telefono.equals(contacto.getTelefono()) && email.equals(contacto.getEmail())
						;	  
	}
	//////////////////////////////////////////////
	@Override
	public int compareTo(Object arg0) 
	{
		Contacto contacto = (Contacto) arg0;
		return nombre.compareTo(contacto.getNombre());
	}
	//////////////////////////////////////////////////
	public int compareTo(Contacto contacto)
	{
		return nombre.compareTo(contacto.getNombre());
	}
	//////////////////////
	/*public int compareTo(Contacto contacto)
	{
		return edad - contacto.getEdad();		
	}*/
	
	
}