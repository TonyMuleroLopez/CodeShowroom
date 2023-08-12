package ccuentaFicheros;

import java.util.*;

public class CCuentaAhorro extends CCuenta
{
	private double cuotaMantenimiento;
	public CCuentaAhorro(String nombre, String cuenta, double interes, double saldo, double cuotaMantenimiento)
	{
		super(nombre, cuenta, interes, saldo);
		this.cuotaMantenimiento = cuotaMantenimiento;
	}
	public double getCuotaMantenimiento() 
	{
		return cuotaMantenimiento;
	}
	public void setCuotaMantenimiento(double cuotaMantenimiento) 
	{
		this.cuotaMantenimiento = cuotaMantenimiento;
	}
	//////////////////////////
	public void comisiones() 
	{
		GregorianCalendar fecha = new GregorianCalendar();
		int dia = fecha.get(Calendar.DAY_OF_MONTH);
		if(dia == 13) 
		{
			reintegro(cuotaMantenimiento);
		}
	}
	
	public double intereses() 
	{
		double cantidadIntereses = 0;
		
		GregorianCalendar fecha = new GregorianCalendar();
		int dia = fecha.get(Calendar.DAY_OF_MONTH);
		if(dia == 13)
		{
			cantidadIntereses = ((getSaldo()*getInteres())/12);
			ingreso(cantidadIntereses);
		}
		return cantidadIntereses;
	}
}
